/*
 * Copyright (c) 2007 gwtwindowmanager.org (http://www.gwtwindowmanager.org)
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

/**
 * A container used to create a multiple-document interface or a virtual
 * desktop. You create <code>GFrame</code> objects and add them to the
 * <code>GDesktopPane</code>.
 */
public interface GDesktopPane {

    /**
     * Adds a new GFrame to this GDesktopPane and select it if its the first
     * one.
     * 
     * @param internalFrame
     *            The GFrame to be added.
     */
    public void addFrame(GInternalFrame internalFrame);

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
     * 
     * @param internalFrame
     */
    public void iconify(GFrame internalFrame);

    /**
     * Restore the minimized window to its original state.
     * 
     * @param minimizedWindow
     */
    public void deIconify(GFrame minimizedWindow);

}
