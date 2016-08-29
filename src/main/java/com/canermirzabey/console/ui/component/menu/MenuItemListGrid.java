package com.canermirzabey.console.ui.component.menu;

import com.canermirzabey.console.ui.*;
import com.canermirzabey.console.ui.component.*;

import java.util.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class MenuItemListGrid extends Component implements Container {
	private String        title;
	private Set<MenuItem> menuItems;

	public MenuItemListGrid(String title) {
		this.title = title;
		menuItems = new TreeSet<>();
	}

	@Override
	public Collection<MenuItem> getComponents() {
		return menuItems;
	}

	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}

	@Override
	public Pixel[][] render() {
		return new Pixel[0][];
	}

}
