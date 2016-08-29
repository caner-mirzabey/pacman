package com.canermirzabey.console.ui;

import com.canermirzabey.console.ui.component.*;

import java.util.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public abstract class View implements Container {
	private final String viewId;
	private final int    height;
	private final int    width;
	private Set<Component> components = new TreeSet<>();

	public View(String viewId, int height, int width) {
		this.viewId = viewId;
		this.height = height;
		this.width = width;
	}

	public String getViewId() {
		return viewId;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Set<Component> getComponents() {
		return components;
	}

	public void setComponents(Set<Component> components) {
		this.components = components;
	}

	public <T extends Component> void addComponent(T component) {
		components.add(component);
	}
}
