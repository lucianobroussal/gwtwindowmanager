package org.gwm.client.tests;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class BlankComposite extends Composite {
		public BlankComposite(){
			initWidget(new HTML());
			setWidth("100%");
			setHeight("100%");
		}
	}