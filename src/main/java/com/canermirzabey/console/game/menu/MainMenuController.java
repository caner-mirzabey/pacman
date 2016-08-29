package com.canermirzabey.console.game.menu;

import com.canermirzabey.console.ui.*;

import org.jnativehook.keyboard.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class MainMenuController implements Controller<MainMenuView> {

	@Override
	public MainMenuView handleKeyPress(MainMenuView view, NativeKeyEvent keyEvent) {
		return view;
	}

}
