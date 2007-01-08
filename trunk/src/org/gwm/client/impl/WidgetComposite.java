package org.gwm.client.impl;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
/**
 * Build up a Composite holding a widget.
 * @author Marcelo Emanoel
 * @since 15/12/2006
 */
public class WidgetComposite extends Composite {
	private Widget widget;

    public WidgetComposite(Widget aWidget) {
	    initWidget(aWidget);
		setWidget(aWidget);
		setSize("100%", "100%");
	}

	/**
	 * @return the widget
	 */
	public Widget getWidget() {
		return widget;
	}

	/**
	 * @param widget the widget to set
	 */
	public void setWidget(Widget widget) {
		this.widget = widget;
	}
}
