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

package org.gwm.client;

import java.util.List;

public interface GDesktopPane {

	
    /**
     * Adds a new GInternalFrame to this GDesktopPane and select it if its the 
     * first one.
     * @param internalFrame The GInternalFrame to be added.
     */
    public void addFrame(GInternalFrame internalFrame);
    
    /**
     * Remove a GInternalFrame from this GDesktopPane and select it if its the 
     * first one.
     * @param internalFrame The GInternalFrame to be removed.
     */
    public void removeFrame(GInternalFrame internalFrame);
	
    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames();

    /**
     * Returns all GInternalFrames currently displayed in the desktop.
     */
    public List getAllFrames();
	
    /**
     * Minimize a determinated window.
     * @param internalFrame
     */
    public void iconify(GInternalFrame internalFrame);

    /**
     * Restore the minimized window to its original state.
     * @param minimizedWindow
     */
    public void deIconify(GInternalFrame minimizedWindow);
	
    
    /**
     * Sets the currently active JInternalFrame in this JDesktopPane.
     */
    public void setSelectedFrame(GInternalFrame newSelectedFrame);
    
    public GInternalFrame getSelectedFrame();

     
}
