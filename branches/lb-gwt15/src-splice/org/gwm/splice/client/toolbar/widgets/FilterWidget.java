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
package org.gwm.splice.client.toolbar.widgets;

import org.gwm.splice.client.toolbar.IActionWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ClickListenerCollection;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FilterWidget extends HorizontalPanel implements IActionWidget, KeyboardListener {
	
	private String name;
	private int actionID;
	private ClickListenerCollection listeners = new ClickListenerCollection();
	private Label label;
	private TextBox textbox; 
	
	// //////////////////////////////////////////////////////////////////////////////////

	public FilterWidget(String name, int actionID, String label) {
		this.name = name;
		this.actionID = actionID;
		
		setStyleName("filterWidget");
		setVerticalAlignment(ALIGN_MIDDLE);
		
		this.label = new Label(label);
		add(this.label);
		textbox = new TextBox();
		
		textbox.addKeyboardListener(this);
		
		add(textbox);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	public String getText() {
		return textbox.getText();
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	public void addClickListener(ClickListener listener) {
		listeners.add(listener);
	}

	public int getActionID() {
		return actionID;
	}

	public String getName() {
		return name;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public void onKeyDown(Widget w, char c, int mod) {
		if(c == 0x0d) {
			listeners.fireClick(this);
		}
	}

	public void onKeyPress(Widget arg0, char arg1, int arg2) {
	}

	public void onKeyUp(Widget arg0, char arg1, int arg2) {
	}

}
