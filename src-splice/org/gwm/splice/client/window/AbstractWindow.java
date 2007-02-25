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
package org.gwm.splice.client.window;

import java.util.ArrayList;
import java.util.Iterator;

import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.DefaultGFrame;
import org.gwm.splice.client.desktop.DesktopManager;
import org.gwm.splice.client.logger.LogItem;
import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.ISerializable;
import org.gwm.splice.client.toolbar.ControlBar;
import org.gwm.splice.client.toolbar.IActionListener;
import org.gwm.splice.client.toolbar.ToolBar;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractWindow extends DefaultGFrame implements ISerializable, IActionListener {
	
	protected Widget widget = null;
	protected String name = null;
	protected boolean useWindowManager = true;
	
	private DockPanel panel = new DockPanel();
	
	protected ControlBar controlbar = new ControlBar(this);
	protected ToolBar toolbar = new ToolBar();
	
	
	private boolean _showMenubar;
	private boolean _showControlbar;
	private boolean _showToolbar;
	private String _theme = null;

	
	private ArrayList listeners = new ArrayList();
	
	String url = null;
	int width = 0;
	int height = 0;
	
	public AbstractWindow() {
		this(null);
	}

	public AbstractWindow(String title) {
		init(title, false, false, false);
	}

	public AbstractWindow(String title, boolean showMenubar, boolean showToolbar, boolean showControlbar) {
		init(title, showMenubar, showToolbar, showControlbar);
	}

	private void init(String title, boolean showMenubar, boolean showToolbar, boolean showControlbar) {
		_showControlbar = showControlbar;
		_showToolbar = showToolbar;
		name = title;
	}

	public void show() {
		
		if(useWindowManager) {
			// do this now in case window manager needs to change name (e.g. "my window (2)")
			// or set default theme
			DesktopManager.getInstance().getWindowManager().addWindow(this);
		}
		
		if(_theme != null) {
			super.setTheme(_theme);
		}

		setCaption(name);

		if(this.url != null) {
			super.setUrl(url);
			setVisible(true);
			return;
		}
		
		panel.setHeight("100%");
		panel.setWidth("100%");
		
		panel.add(toolbar, DockPanel.NORTH);
		panel.setCellHeight(toolbar, "26px");
		
		panel.add(widget, DockPanel.CENTER);
		panel.setCellVerticalAlignment(widget, VerticalPanel.ALIGN_TOP);
		panel.setCellHeight(widget, "100%");
		
		panel.add(controlbar, DockPanel.SOUTH);
		panel.setCellHeight(controlbar, "32px");
		panel.setCellHorizontalAlignment(controlbar, HorizontalPanel.ALIGN_CENTER);
		panel.setCellVerticalAlignment(controlbar, VerticalPanel.ALIGN_MIDDLE);
		
		panel.setHeight("100%");
		
		
		showToolbar(_showToolbar);
		showControlbar(_showControlbar);
		
		toolbar.addListener(this);
		
		super.setContent(panel);
		
		setVisible(true);
	}
	
	////////////////////////////////////////////////////////////////////////////////

	public void addWindowEventListener(IWIndowEventListener listener) {
		listeners.add(listener);
	}
	
	public void removeWindowEventListener(IWIndowEventListener listener) {
		listeners.remove(listener);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	public void setName(String name) {
		this.name = name;
		setCaption(name);
	}
	
	
	public void setTheme(String theme) {
		this._theme = theme;
		super.setTheme(theme);
	}

	public String getTheme() {
		return _theme;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	public void setContent(Widget widget) {
		this.widget = widget;
	}

	public void setWidget(Widget w) {
		this.widget = w;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * @param show
	 */
	public void showToolbar(boolean show) {
		toolbar.setVisible(show);
		if(!show) {
			panel.setCellHeight(toolbar, "0px");
		}
		else {
			panel.setCellHeight(toolbar, "26px");
		}
	}
	
	/**
	 * @param show
	 */
	public void showControlbar(boolean show) {
		controlbar.setVisible(show);
		if(!show) {
			panel.setCellHeight(controlbar, "0px");
		}
		else {
			panel.setCellHeight(controlbar, "26px");
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	// utility log methods..
	
	protected void log(int level, String message) {
		DesktopManager.getInstance().getLogger().log(level, message, this);
	}
	
	protected void logInfo(String message) {
		log(LogItem.INFO, message);
	}
	
	protected void logWarning(String message) {
		log(LogItem.WARN, message);
	}
	
	protected void logError(String message) {
		log(LogItem.ERROR, message);
	}
	
	protected void logSevere(String message) {
		log(LogItem.SEVERE, message);
	}
	protected void logFatal(String message) {
		log(LogItem.FATAL, message);
	}

	////////////////////////////////////////////////////////////////////////////////

	public String toString() {
		return this.name;
	}

	public ControlBar getControlBar() {
		return controlbar;
	}

	public ToolBar getToolBar() {
		return toolbar;
	}

	public void setPanelStyleName(String style) {
		panel.setStyleName(style);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.ISerializable#initialize(org.gwm.splice.client.service.data.Attributes)
	 */
	public void initialize(Attributes attributes) {
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.ISerializable#serialize()
	 */
	public Attributes serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.toolbar.IActionListener#onActionClicked(com.google.gwt.user.client.ui.Widget, java.lang.String, int)
	 */
	public boolean onActionClicked(Widget widget, String name, int actionID) {
		return true;
	}

	public void close() {
		DesktopManager.getInstance().getWindowManager().removeWindow(this);
		super.close();
	}
	
	
}
