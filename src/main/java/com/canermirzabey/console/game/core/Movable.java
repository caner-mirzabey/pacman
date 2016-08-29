package com.canermirzabey.console.game.core;

import com.canermirzabey.console.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public abstract class Movable {
	protected Position  position;
	protected Direction direction;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	enum Direction {
		UPWARD,
		DOWNWARD,
		LEFT,
		RIGHT
	}
}
