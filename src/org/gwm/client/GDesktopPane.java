/*
 * Copyright (c) 2006-2007 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtwindowmanager.org)
 * 
 * Main Contributors :
 *      Johan Vos,Andy Scholz,Marcelo Emanoel  
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

import com.google.gwt.user.client.ui.Widget;

/**
 * A container used to create a multiple-document interface or a virtual
 * desktop. You create <code>GFrame</code> objects and add them to the
 * <code>GDesktopPane</code>.
 * 
 * CSS attributes: gwm-theme-GDesktopPane gwm-theme-GDesktopPane-FrameContainer
 * gwm-theme-GDesktopPane-TaskBar
 * 
 */
public interface GDesktopPane {

    /**
     * Adds a new frame to this desktop
     * 
     * @param internalFrame
     *            The frame to be added.
     */
    public void addFrame(GInternalFrame internalFrame);

    /**
     * Adds a new frame to this desktop
     * 
     * @param internalFrame
     *            The frame to be removed.
     */
    public void removeFrame(GInternalFrame internalFrame);

    /**
     * Closes all frames contained in this desktop.
     */
    public void closeAllFrames();

    /**
     * Returns all GInternalFrames currently displayed in the desktop.
     */
    public List getAllFrames();

    /**
     * Minimizes a frame.
     * 
     * @param internalFrame
     */
    public void iconify(GFrame internalFrame);

    /**
     * Restores a minimized frame.
     * 
     * @param minimizedWindow
     */
    public void deIconify(GFrame internalFrame);

    /**
     * Allows the add to any widget into the desktop.
     * 
     * @param widget
     * @param top
     * @param left
     */
    public void setWidgetLocation(Widget widget, int top, int left);

    public Widget getFramesContainer();

    /**
     * @return the actual active frame or null
     */
    public GInternalFrame getActiveFrame();

    /**
     * Activates a frame.
     * 
     * @param internalFrame the frame to be activated
     */
    public void setActivateFrame(GInternalFrame internalFrame);

    /**
     * Sets the desktop theme. This theme is propagated to all the ginternalframes of the
     * desktop. 
     * 
     * Desktop dedicated CSS attributes: 
     * gwm-theme-GDesktopPane
     * gwm-theme-GDesktopPane-FrameContainer 
     * gwm-theme-GDesktopPane-TaskBar
     * 
     * 
     * @param theme the desktop theme
     * 
     */
    public void setTheme(String theme);

}
