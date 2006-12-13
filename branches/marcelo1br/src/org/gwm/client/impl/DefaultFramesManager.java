/*
 * Copyright (c) 2006 gwtwindowmanager.org (http://www.gwtwindowmanager.org)
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

package org.gwm.client.impl;

import java.util.HashMap;
import java.util.Iterator;

import org.gwm.client.GInternalFrame;

/**
 * 
 * @author lb
 * 
 */
public class DefaultFramesManager {

	private HashMap windows;

	private long windowCount;

	/** *************************************************************** */
	/** ****************************** PUBLIC ************************* */
	/** *************************************************************** */

	public DefaultFramesManager() {

		windows = new HashMap();
	}

	public GInternalFrame getFrame(String id) {

		return (GInternalFrame) windows.get(id);
	}

	public GInternalFrame[] getAllFrames() {

		GInternalFrame[] allWindows = new GInternalFrame[windows.values()
				.size()];
		int index = 0;
		for (Iterator iter = windows.values().iterator(); iter.hasNext();) {
			GInternalFrame window = (GInternalFrame) iter.next();
			allWindows[index] = window;
			index++;
		}
		return allWindows;
	}

	public GInternalFrame newFrame() {

		String windowId = "win_" + windowCount++;
		GInternalFrame window = new DefaultGInternalFrame(windowId);
		windows.put(windowId, window);
		return window;
	}

	public void closeAllFrames() {

		closeAll();
		windows.clear();
	}

	/** *************************************************************** */
	/** ****************************** PRIVATE ************************ */
	/** *************************************************************** */
	private native void closeAll()/*-{
	 $wnd.Windows.closeAll();
	 }-*/;

}
