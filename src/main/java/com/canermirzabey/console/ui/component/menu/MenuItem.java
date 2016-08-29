package com.canermirzabey.console.ui.component.menu;

import com.canermirzabey.console.ui.component.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public class MenuItem extends TextField implements Selectable {
	private final int     index;
	private       boolean selected;

	public MenuItem(int index, String text) {
		super(index + ". " + text);
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int compareTo(Component o) {
		if (o != null && o instanceof MenuItem) {
			MenuItem comparaTo = (MenuItem) o;
			if (index < comparaTo.getIndex()) {
				return -1;
			}
			else if (index == comparaTo.getIndex()) {
				return 0;
			}
			else {
				return 1;
			}
		}
		return super.compareTo(o);
	}
}
