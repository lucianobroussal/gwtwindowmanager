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

import java.util.HashMap;
import java.util.Map;


import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;


public class ToolBar extends ActionBar {

	public ToolBar() {
		super();
		setStyleName("toolbar");
		setHorizontalAlignment(ALIGN_LEFT);
		setVerticalAlignment(ALIGN_MIDDLE);
	}
	
	public void addButton(String image, String name) {
		addButton(image, name, 0, null, null, false);
	}
	
	public void addButton(String image, String name, int actionID) {
		addButton(image, name, actionID, null, null, false);
	}
	
	public void addButton(String image, String name, int actionID, String labelText, String tooltip, boolean showBorders) {
		ToolBarButton btn = new ToolBarButton(image, name, actionID, labelText, tooltip, showBorders);
		widgets.put(name, btn);
		addWidget(btn);
	}
	
	public void addSeperator() {
		HTML sep = new HTML("");
		sep.setStyleName("toolbarSeperator");
		panel.add(sep);
	}
	
	
//	public void addMenu(String name, MenuBar menuBar) {
//		widgets.put(name, menuBar);
//		addWidget(menuBar);
//	}

}
