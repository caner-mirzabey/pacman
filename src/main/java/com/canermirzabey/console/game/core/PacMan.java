package com.canermirzabey.console.game.core;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class PacMan extends Movable implements Fighter {
	private byte superiority;
	private int  score;

	@Override
	public byte getSuperiority() {
		return superiority;
	}

	public void setSuperiority(byte superiority) {
		this.superiority = superiority;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
