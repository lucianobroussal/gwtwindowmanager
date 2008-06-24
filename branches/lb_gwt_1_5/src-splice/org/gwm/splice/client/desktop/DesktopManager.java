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
package org.gwm.splice.client.desktop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.gwm.splice.client.icon.IconManager;
import org.gwm.splice.client.logger.LogWindow;
import org.gwm.splice.client.logger.Logger;
import org.gwm.splice.client.logger.ILogger;
import org.gwm.splice.client.window.AbstractWindow;
import org.gwm.splice.client.window.IWindowManagerListener;
import org.gwm.splice.client.window.WindowManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class DesktopManager implements IWindowManagerListener {

	private static DesktopManager instance;
	
	public static DesktopManager getInstance() {
		if(instance == null) {
			instance = new DesktopManager();
		}
		return instance;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////

	private IconManager iconManager;
	private WindowManager windowManager;
	private ILogger logger;
	private String iconPath = "images/icons";
	private String iconTheme = "crystalsvg";
	private String defaultIconExtension = ".png";
	private Map services = new HashMap();
	
	private int topMargin = 0;
	private int leftMargin = 0;
	private int rightMargin = 0;
	private int bottomMargin = 0;
	
	private DesktopManager() {
		windowManager = new WindowManager(this);
		windowManager.addWindowManagerListener(this);
		iconManager = new IconManager(this);
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean canDrop(Widget widget) {
		
		RootPanel rp = RootPanel.get();
		
		int x = widget.getAbsoluteLeft();
		int y = widget.getAbsoluteTop();
		int h = widget.getOffsetHeight();
		int w = widget.getAbsoluteLeft();
		
		boolean overlapX = false;
		boolean overlapY = false;
		
		for (Iterator iter = rp.iterator(); iter.hasNext();) {
			Widget child = (Widget) iter.next();

			if(child == widget) {
				continue;
			}
			
			int cx = child.getAbsoluteLeft();
			int cy = child.getAbsoluteTop();
			int ch = child.getOffsetHeight();
			int cw = child.getOffsetWidth();
			
			if(x == cx || y == cy) {
				return false;
			}
			if(x > cx) {
				if(x <= (cx + cw)) {
					overlapX = true;
				}
			}
			else if(x < cx) {
				if((x + w) >= cx) {
					overlapX = true;
				}
			}
			if(y > cy) {
				if(y <= (cy + ch)) {
					overlapY = true;
				}
			}
			else if(y < cy) {
				if((y + h) >= cy) {
					overlapY = true;
				}
			}
			
			if(overlapX && overlapY) {
				return false;
			}
		}
		
		return true;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public IconManager getIconManager() {
		return iconManager;
	}

	public WindowManager getWindowManager() {
		return windowManager;
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	public void registerService(String name, RemoteService service) {
		services.put(name, service);
	}
	
	public void unregisterService(String name) {
		services.remove(name);
	}
	
	public void showLogWindow() {
		new LogWindow();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public void windowAdded(AbstractWindow window) {
		// TODO Auto-generated method stub
	}

	public void windowRemoved(AbstractWindow window) {
		// TODO Auto-generated method stub
		
	}

	public ILogger getLogger() {
		if(logger == null) {
			logger = Logger.getInstance();
		}
		return logger;
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}

	public String getIconTheme() {
		return iconTheme;
	}

	public void setIconTheme(String iconTheme) {
		this.iconTheme = iconTheme;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getDefaultIconExtension() {
		return defaultIconExtension;
	}

	public void setDefaultIconExtension(String defaultIconExtension) {
		this.defaultIconExtension = defaultIconExtension;
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	
	public String getSmallIconPath() {
		return iconPath + "/" + iconTheme + "/16x16"; 
	}
	public String getLargeIconPath() {
		return iconPath + "/" + iconTheme + "/32x32"; 
	}

	public String getSmallIconUrl(String name) {
		return GWT.getModuleBaseURL() + "/" + getSmallIconPath() + "/" + name + defaultIconExtension;
	}
	public String getLargeIconUrl(String name) {
		return GWT.getModuleBaseURL() + "/" + getLargeIconPath() + "/" + name + defaultIconExtension;
	}

	/**
	 * @return the bottomMargin
	 */
	public int getBottomMargin() {
		return bottomMargin;
	}

	/**
	 * @param bottomMargin the bottomMargin to set
	 */
	public void setBottomMargin(int bottomMargin) {
		this.bottomMargin = bottomMargin;
	}

	/**
	 * @return the rightMargin
	 */
	public int getRightMargin() {
		return rightMargin;
	}

	/**
	 * @param rightMargin the rightMargin to set
	 */
	public void setRightMargin(int rightMargin) {
		this.rightMargin = rightMargin;
	}

	/**
	 * @return the leftMargin
	 */
	public int getLeftMargin() {
		return leftMargin;
	}

	/**
	 * @param leftMargin the leftMargin to set
	 */
	public void setLeftMargin(int leftMargin) {
		this.leftMargin = leftMargin;
	}

	/**
	 * @return the topMargin
	 */
	public int getTopMargin() {
		return topMargin;
	}

	/**
	 * @param topMargin the topMargin to set
	 */
	public void setTopMargin(int topMargin) {
		this.topMargin = topMargin;
	}
}
