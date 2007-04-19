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
package org.gwm.splice.client.dialog;

import org.gwm.splice.client.desktop.DesktopManager;
import org.gwm.splice.client.window.AbstractWindow;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class MessageBox extends AbstractWindow implements ClickListener {
	public static final int ICON_INFO = 0;
	public static final int ICON_WARNING = 1;
	public static final int ICON_ERROR = 2;
	
	public static final int BUTTONS_OK = 0;
	public static final int BUTTONS_CANCEL = 1;
	public static final int BUTTONS_OK_CANCEL = 2;
	public static final int BUTTONS_YES_NO = 3;
	
	
	public static void showInfo(String message) {
		new MessageBox(ICON_INFO, "Information", message, BUTTONS_OK);
	}
	
	public static void showWarning(String message) {
		new MessageBox(ICON_WARNING, "Warning", message, BUTTONS_OK);
	}
	
	public static void showError(String message) {
		new MessageBox(ICON_ERROR, "Error", message, BUTTONS_OK);
	}
	
	
	MessageBox(int icon, String title, String message, int buttons) {
		super(title);
		
		DockPanel panel =  new DockPanel();
		panel.setSpacing(7);

		panel.add(getIconImage(icon), DockPanel.WEST);
		panel.add(new HTML(message), DockPanel.CENTER);
		
		Button okButton = new Button("OK", this);
		panel.add(okButton, DockPanel.SOUTH);
		
		panel.setCellHorizontalAlignment(okButton, DockPanel.ALIGN_CENTER);
		panel.setCellVerticalAlignment(okButton, DockPanel.ALIGN_BOTTOM);
		
		panel.setHeight("100%");
		panel.setWidth("100%");
		setWidget(panel);
		
		setMaximizable(false);
		setMinimizable(false);
//		setResizable(false);
		setWidth(300);
		setHeight(100);
	}

	public void onClick(Widget arg0) {
		close();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	private Image getIconImage(int type) {
		String iconPath = DesktopManager.getInstance().getLargeIconPath() + "/actions/";
		switch(type) {
		case ICON_INFO:
			return new Image(iconPath + "messagebox_info.png");
		case ICON_WARNING:
			return new Image(iconPath + "messagebox_warning.png");
		case ICON_ERROR:
			return new Image(iconPath + "messagebox_critical.png");
		}
		return new Image(iconPath + "messagebox_info.png");
	}
}
