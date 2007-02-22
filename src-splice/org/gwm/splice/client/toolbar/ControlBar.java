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

import org.gwm.splice.client.window.AbstractWindow;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class ControlBar extends ActionBar {
	
	AbstractWindow window;
	
	public ControlBar(AbstractWindow window) {
		super();
		this.window = window;
//		setHorizontalAlignment(ALIGN_CENTER);
//		setVerticalAlignment(ALIGN_MIDDLE);
		setStyleName("controlBar");
		panel.setWidth("100%");
		panel.setHorizontalAlignment(ALIGN_CENTER);
		panel.setVerticalAlignment(ALIGN_MIDDLE);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	public void addCloseButton() {
		addWidget(new ControlButton("actions/cancel", "close", -1, "Close", "Close Window", 75));
		addDefaultHandler("close", new IActionListener() {
			public boolean onActionClicked(Widget widget, String name, int actionID) {
				window.close();
				return false;
			}
		});
	}
	
	public void addButton(String imageName, String name, int actionID, String labelText, String toolTip) {
		addWidget(new ControlButton(imageName, name, actionID, labelText, toolTip, 75));
		addListener(name, window);
	}
	
	public ToolBarButton getButton(String name) {
		return (ToolBarButton)getWidget(name);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public class ControlButton extends ToolBarButton implements IActionWidget {
		private String name;
		private int actionID;
		
		public ControlButton(String imageName, String name, int actionID, String labelText, String tooltip, int width) {
			super(imageName, name, actionID, labelText, tooltip, true);
			this.name = name;
			this.actionID = actionID;
			if(width > 0) {
				setWidth(String.valueOf(width) + "px");
			}
			
		}
		
		public int getActionID() {
			return actionID;
		}
		public void setActionID(int actionID) {
			this.actionID = actionID;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
