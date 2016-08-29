package com.canermirzabey.console.ui;

import com.canermirzabey.console.*;

import org.fusesource.jansi.*;
import org.fusesource.jansi.Ansi.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class Pixel {
	private char displayCharacter;
	private Position   position        = new Position();
	private Ansi.Color foregroundColor = Color.DEFAULT;
	private Ansi.Color backgroundColor = Color.DEFAULT;

	public Pixel(char displayCharacter, Position position, Color foregroundColor, Color backgroundColor) {
		this.displayCharacter = displayCharacter;
		this.position = position;
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public char getDisplayCharacter() {
		return displayCharacter;
	}

	public void setDisplayCharacter(char displayCharacter) {
		this.displayCharacter = displayCharacter;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
