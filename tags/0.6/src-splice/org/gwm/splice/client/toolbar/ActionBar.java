/*
 * Copyright (c) 2006/2007 Flipperwing Ltd. (http://www.flipperwing.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwm.splice.client.toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

public abstract class ActionBar extends HorizontalPanel implements ClickListener {
	
	ArrayList listeners = new ArrayList();
	Map handlers = new HashMap();
	Map widgets = new HashMap();
	protected HorizontalPanel panel = new HorizontalPanel();
	
	ActionBar() {
		add(panel);
		setWidth("100%");
		setVisible(false);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////
	
	public void addWidget(IActionWidget widget) {
		widgets.put(widget.getName(), widget);
		widget.addClickListener(this);
		panel.add((Widget) widget);
	}
	
	public void removeWidget(String name) {
		Widget widget = (Widget) widgets.get(name);
		if(widget == null) {
			return;
		}
		panel.remove(widget);
		widgets.remove(name);
	}
	
	public Widget getWidget(String name) {
		return (Widget) widgets.get(name);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////
	
	private class ActionListenerWrapper {
		String widgetName;
		IActionListener listener;
		
		private ActionListenerWrapper(String widgetName, IActionListener listener) {
			this.widgetName = widgetName;
			this.listener = listener;
		}
	}
	
	// //////////////////////////////////////////////////////////////////////////////////
	
	public void addListener(IActionListener listener) {
		listeners.add(new ActionListenerWrapper(null, listener));
	}

	public void addListener(String widgetName, IActionListener listener) {
		listeners.add(new ActionListenerWrapper(widgetName, listener));
	}

	public void removeListener(IActionListener listener) {
		for (Iterator iter = listeners.iterator(); iter.hasNext();) {
			ActionListenerWrapper aw = (ActionListenerWrapper) iter.next();
			if(aw.listener.equals(listener)) {
				listeners.remove(aw);
			}
		}
	}

	public void addDefaultHandler(String widgetName, IActionListener listener) {
		handlers.put(widgetName, listener);
	}

	public void removeDefaultHandler(String widgetName) {
		handlers.remove(widgetName);
	}

	protected boolean notifyActionClicked(Widget widget, String name, int actionID) {
		boolean doDefault = true;
		for (Iterator iter = listeners.iterator(); iter.hasNext();) {
			ActionListenerWrapper aw = (ActionListenerWrapper) iter.next();
			if(aw.widgetName == null || aw.widgetName.equals(name)) {
				if(!aw.listener.onActionClicked(widget, name, actionID)) {
					doDefault = false;
				}
			}
		}
		return doDefault;
	}

	protected void notifyDefaultHandler(Widget widget, String name, int actionID) {
		IActionListener listener = (IActionListener) handlers.get(name);
		if(listener != null) {
			listener.onActionClicked(widget, name, actionID);
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget w) {
		IActionWidget iw = (IActionWidget) w;
		if(notifyActionClicked(w, iw.getName(), iw.getActionID())) {
			notifyDefaultHandler(w, iw.getName(), iw.getActionID());
		}
	}


}
