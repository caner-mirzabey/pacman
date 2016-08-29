package com.canermirzabey.console.ui.component;

import com.canermirzabey.console.ui.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class TextField extends Component {
	private String text;

	public TextField(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Pixel[][] render() {
		Pixel[][] display = null;
		if (text != null && !text.isEmpty()) {
			display = new Pixel[1][text.length()];
			for (int i = 0; i < text.length(); i++) {
				display[0][i] = new Pixel(text.charAt(i), position, getFgColor(), getBgColor());
			}
		}
		return display;
	}

	@Override
	public int compareTo(Component o) {
		return 0;
	}
}
