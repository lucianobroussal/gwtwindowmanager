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

import java.util.List;

import org.gwm.client.FramesManager;
import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

/**
 * 
 * @author Marcelo Emanoel
 * @since 21/12/2006
 */
public class DefaultFramesManager implements FramesManager {
	
	private GDesktopPane desktop;
	
	public DefaultFramesManager(){
		desktop = new GDesktopPane();
	}

	public void closeAllFrames() {
		desktop.closeAllInternalFrames();
	}

	public GInternalFrame[] getAllFrames() {
		List allFrames = desktop.getAllGInternalFrames();
		GInternalFrame[] internalFrames = new GInternalFrame[allFrames.size()];
		for(int i=0; i < allFrames.size(); i++){
			internalFrames[i] = (GInternalFrame) allFrames.get(i);
		}
		return internalFrames;
	}

	public GInternalFrame getFrame(String id) {
		return desktop.getGInternalFrame(id);
	}

	public GInternalFrame newFrame() {
		return desktop.newFrame();
	}
}