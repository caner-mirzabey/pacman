package com.canermirzabey.console.ui;

/**
 * Created by ecanmir on 28.08.2016.
 */
public enum DisplayCharacter {
	;
	//	° ± ² Û ß Ü İ Ş ş ú
	//	░ ▒ ▓ █ ▀ ▄ ▌ ▐ ■ ·
	public static final char DOTTED_LOW  = '°';
	public static final char DOTTED_MID  = '±';
	public static final char DOTTED_HIGH = '²';

	public static final char FULL        = 'Û';
	public static final char TOP_HALF    = 'ß';
	public static final char BOTTOM_HALF = 'Ü';
	public static final char LEFT_HALF   = 'İ';
	public static final char RIGHT_HALF  = 'Ş';
	public static final char MIDDLE_HALF = 'ş';

	public static final char MIDDLE_DOT = 'ú';

	private char character;

	private DisplayCharacter(char character) {
		this.character = character;
	}
}
