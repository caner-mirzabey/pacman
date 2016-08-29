package com.canermirzabey.console.ui;

import com.canermirzabey.console.*;
import com.canermirzabey.console.ui.component.*;

import org.fusesource.jansi.*;
import org.fusesource.jansi.Ansi.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class ViewRenderer {
	private final View view;
	private       Ansi output;

	public ViewRenderer(View view) {
		this.view = view;
	}

	public Ansi renderView() {
		output = Ansi.ansi(view.getHeight() * view.getWidth());
		output.eraseScreen(Erase.ALL);
		if (view != null && view.getComponents() != null && !view.getComponents().isEmpty()) {
			Pixel[][] pixels = new Pixel[view.getHeight()][view.getWidth()];
			output = renderComponents(output, pixels, view);

		}
		return output;
	}

	public Ansi renderComponents(Ansi ansi, Pixel[][] pixels, Container container) {
		if (container.getComponents() != null && !container.getComponents().isEmpty()) {
			for (Component component : container.getComponents()) {
				if (component instanceof Container) {
					renderComponents(ansi, pixels, (Container) component);
				}
				else {
					Pixel[][] componentDisplay  = component.render();
					Position  componentPosition = component.getPosition();
					boolean   selectable        = component instanceof Selectable;
					boolean   isSelected        = false;

					if (selectable) {
						isSelected = ((Selectable) component).isSelected();
					}

					for (int y = 0; y < componentDisplay.length; y++) {
						ansi.newline();

						for (int x = 0; x < componentDisplay[y].length; x++) {

							Pixel viewDisplayPixel = pixels[y + componentPosition.getY()][x + componentPosition.getX()];
							Pixel componentDisplayPixel = componentDisplay[y][x];
							if (viewDisplayPixel != null) {
								if (viewDisplayPixel.getPosition().getZ() <
								    componentDisplayPixel.getPosition().getZ()) {
									if (selectable && isSelected) {
										ansi = ansi.bgBright(componentDisplayPixel.getBackgroundColor());
									}
									else {
										ansi = ansi.bg(componentDisplayPixel.getBackgroundColor());
									}
									ansi = ansi.fg(componentDisplayPixel.getForegroundColor()).a(componentDisplayPixel
											                                                             .getDisplayCharacter())
											       .reset();
								}

							}
							else {
								if (selectable && isSelected) {
									ansi = ansi.bgBright(componentDisplayPixel.getBackgroundColor());
								}
								else {
									ansi = ansi.bg(componentDisplayPixel.getBackgroundColor());
								}
								ansi = ansi.fg(componentDisplayPixel.getForegroundColor()).a(componentDisplayPixel
										                                                             .getDisplayCharacter())
										       .reset();
							}
						}
					}
				}
			}
		}
		return ansi;
	}
}
