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

import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

public class ToolBarMenu implements IActionListener {

	private String name;
	private int actionID;
	private MenuBar menuBar;
	
	// //////////////////////////////////////////////////////////////////////////////////

	public ToolBarMenu(String name, int actionID) {
		super();
		this.name = name;
		this.actionID = actionID;
	}


	// //////////////////////////////////////////////////////////////////////////////////


	
	// //////////////////////////////////////////////////////////////////////////////////

	public boolean onActionClicked(Widget widget, String name, int actionID) {
		// TODO Auto-generated method stub
		return false;
	}

}
