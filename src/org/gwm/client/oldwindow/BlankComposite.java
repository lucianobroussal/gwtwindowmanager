package org.gwm.client.oldwindow;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

/**
 * The BlankComposite class is exactly what its name says. A composite without a
 * content.
 * 
 * @author Marcelo Emanoel
 * @since 14/12/2006
 */
public class BlankComposite extends Composite {

	/**
	 * Build a BlankComposite.
	 */
	public BlankComposite() {

		initWidget(new HTML());
		setWidth("100%");
		setHeight("100%");
	}
}