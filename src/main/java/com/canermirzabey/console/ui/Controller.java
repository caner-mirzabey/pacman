package com.canermirzabey.console.ui;

import org.jnativehook.keyboard.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public interface Controller<T extends View> {
	/**
	 * @param keyEvent
	 * @return view-id
	 */
	T handleKeyPress(T view, NativeKeyEvent keyEvent);
}
