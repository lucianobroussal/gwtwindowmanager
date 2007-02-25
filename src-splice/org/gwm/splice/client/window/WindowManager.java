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
import java.util.HashMap;
import java.util.Iterator;

import org.gwm.splice.client.desktop.DesktopManager;


public class WindowManager {
	
	static final int EVENT_WINDOW_REMOVED = 0;
	static final int EVENT_WINDOW_ADDED = 1;

	HashMap windows = new HashMap();
	String defaultWindowTheme = "alphacube";
	DesktopManager desktop;
	ArrayList listeners = new ArrayList();
	
	public WindowManager(DesktopManager desktop) {
		this.desktop = desktop;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	AbstractWindow addWindow(AbstractWindow window) {
		
		String name = window.name;
		AbstractWindow w = (AbstractWindow) windows.get(name);
		if(w != null) {
			int i = 2;
			String n = null;
			// get a unique name
			while(w != null) {
				n = name + " (" + i + ")";
				w = (AbstractWindow) windows.get(n);
				++i;
			}
			window.name = n;
		}
		
		
			// always set the default theme
			// gwm sets "theme1" on init 
//		if(window.getTheme() == null) {
			window.setTheme(defaultWindowTheme);
//		}
		
		windows.put(window.name, window);
		notify(EVENT_WINDOW_ADDED, window);
		return window;
	}
	
	void removeWindow(AbstractWindow window) {
		AbstractWindow w = (AbstractWindow) windows.get(window.name);
		if(w != null) {
			windows.remove(window.name);
			notify(EVENT_WINDOW_REMOVED, window);
		}
	}

	private void notify(int type, AbstractWindow window) {
		for (Iterator iter = listeners.iterator(); iter.hasNext();) {
			IWindowManagerListener listener = (IWindowManagerListener) iter.next();
			if(type == EVENT_WINDOW_ADDED)
				listener.windowAdded(window);
			else if(type == EVENT_WINDOW_REMOVED)
				listener.windowRemoved(window);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	public void addWindowManagerListener(IWindowManagerListener listener) {
		listeners.add(listener);
	}

	public void removeWindowManagerListener(IWindowManagerListener listener) {
		listeners.remove(listener);
	}

	public String getDefaultWindowTheme() {
		return defaultWindowTheme;
	}

	public void setDefaultWindowTheme(String defaultWindowTheme) {
		this.defaultWindowTheme = defaultWindowTheme;
	}


}
