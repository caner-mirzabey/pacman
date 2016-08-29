package com.canermirzabey.console.ui.component;

import java.util.*;

/**
 * Created by ecanmir on 28.08.2016.
 */
public interface Container {
	<T extends Component> Collection<T> getComponents();
}
