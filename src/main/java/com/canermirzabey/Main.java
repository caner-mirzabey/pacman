package com.canermirzabey;

import com.canermirzabey.console.*;
import com.canermirzabey.console.game.menu.*;
import com.canermirzabey.console.ui.component.menu.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class Main {
	public static void main(String[] args) {
		MainMenuView     mainMenuView     = new MainMenuView("mainMenu", 150, 100);
		MenuItem         createCharacter  = new MenuItem(1, "Create Character");
		MenuItem         newGame          = new MenuItem(2, "New Game");
		MenuItemListGrid menuItemListGrid = new MenuItemListGrid("Game Menu");
		menuItemListGrid.addMenuItem(createCharacter);
		menuItemListGrid.addMenuItem(newGame);
		mainMenuView.addComponent(menuItemListGrid);

		MainMenuController mainMenuController = new MainMenuController();

		ConsoleApplication application = new ConsoleApplication(150, 100);
		application.addView(mainMenuView, mainMenuController);

		application.start(mainMenuView.getViewId());

	}
}
