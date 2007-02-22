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
package org.gwm.splice.client;

import org.gwm.splice.client.desktop.DesktopManager;
import org.gwm.splice.client.dialog.MessageBox;
import org.gwm.splice.client.form.ColumnInfo;
import org.gwm.splice.client.form.ResultListWindow;
import org.gwm.splice.client.icon.DesktopIcon;
import org.gwm.splice.client.icon.IconManager;
import org.gwm.splice.client.icon.UrlLaunchIcon;
import org.gwm.splice.client.service.GenericDataService;
import org.gwm.splice.client.service.IResponseHandler;
import org.gwm.splice.client.service.ResponseHandler;
import org.gwm.splice.client.service.data.RemoteObject;
import org.gwm.splice.client.service.data.RemoteStatus;
import org.gwm.splice.client.service.data.ResultList;
import org.gwm.splice.client.service.query.Filter;
import org.gwm.splice.client.service.query.SortOrder;
import org.gwm.splice.client.toolbar.ToolBar;
import org.gwm.splice.client.user.JUser;
import org.gwm.splice.client.user.User;
import org.gwm.splice.client.user.UserResultListWindow;
import org.gwm.splice.client.util.js.JSUtils;
import org.gwm.splice.client.window.AbstractWindow;
import org.gwm.splice.client.window.UrlWindow;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Splice implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

//		if (true) {
//			return;
//		}

		init_stuff();

		final Button button = new Button("Click me");

		DesktopManager dt = DesktopManager.getInstance();

		dt.setBottomMargin(0);
		dt.setTopMargin(37);
		dt.setLeftMargin(10);
		dt.setRightMargin(10);
		final IconManager im = dt.getIconManager();

		button.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				im.arrangeIcons(IconManager.ARRANGE_TOP, IconManager.SORT_ASC);
			}
		});

//		HorizontalPanel hp = new HorizontalPanel();
//		hp.setHeight("32px");
//		hp.setWidth("100%");
//		ToolBar toolbar = new ToolBar();
//		toolbar.setVisible(true);
//		toolbar.addButton("actions/filenew", "newfile");
//		toolbar.addButton("actions/edit_user", "edituser");
//		hp.add(toolbar);
//		RootPanel.get().add(hp);

		im.addIcon(new UrlLaunchIcon("Groklaw", "http://groklaw.net"));
		im.addIcon(new UrlLaunchIcon("Slashdot", "http://slashdot.org"));
		im.addIcon(new UrlLaunchIcon("Linux Today", "http://linuxtoday.com"));
		im.addIcon(new UrlLaunchIcon("BPIR Home", "http://www.bpir.com"));


		GenericDataService svc = new GenericDataService("generic");

		svc.setController("user");
		svc.setScriptDir("controllers");
		svc.setScriptExtension(".php");
		
		/*********************************************************
		 * Make sure you set this to point to your remote app server correctly.
		 * *Note this has nothing to do with GWT - its your PHP/Ruby etc server.
		 * This is the url used by the proxy servlet to forwared remote service requests.
		 */
		svc.setHostedModeTargetBaseUrl("http://bpir.wingnut.com/_clicksplice");

		UserResultListWindow usrw = new UserResultListWindow("Users", svc);

	}

	private static native void killContextMenu() /*-{
	 $doc.oncontextmenu = function() { return false; };

	 
	 }-*/;

	private static native void init_stuff() /*-{
	 $wnd.hello = function(str) {
	 @org.gwm.splice.client.DeskTop::hello(Ljava/lang/String;)(str);
	 }
	 }-*/;

	public static void hello(String str) {
		MessageBox.showInfo("Hello from: " + str);
	}

}
