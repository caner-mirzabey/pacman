package com.canermirzabey.console.ui.component;

import com.canermirzabey.console.*;
import com.canermirzabey.console.ui.*;

import org.fusesource.jansi.*;
import org.fusesource.jansi.Ansi.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public abstract class Component implements Comparable<Component> {
	protected Position   position = new Position();
	private   Ansi.Color fgColor  = Color.WHITE;
	private   Ansi.Color bgColor  = Color.BLACK;

	public Component() {
	}

	public Component(Position position) {
		this.position = position;
	}

	public Component(Color fgColor, Color bgColor) {
		this.fgColor = fgColor;
		this.bgColor = bgColor;
	}

	public Component(Position position, Color fgColor, Color bgColor) {
		this.position = position;
		this.fgColor = fgColor;
		this.bgColor = bgColor;
	}

	public abstract Pixel[][] render();

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Color getFgColor() {
		return fgColor;
	}

	public void setFgColor(Color fgColor) {
		this.fgColor = fgColor;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	@Override
	public int compareTo(Component o) {
		if (o != null) {
			if (getPosition().getZ() < o.getPosition().getZ()) {
				return -1;
			}
			else if (getPosition().getZ() == o.getPosition().getZ()) {
				return 0;
			}
		}
		return 1;
	}
}
