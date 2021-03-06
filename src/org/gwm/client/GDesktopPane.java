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

import org.gwm.client.impl.LayoutConstant;

import com.google.gwt.user.client.ui.Widget;

/**
 * A container used to create a multiple-document interface or a virtual
 * desktop. You create {@link GFrame} objects and add them to the
 * {@link GDesktopPane}.
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
     *            the frame to be added.
     */
    public void addFrame(GInternalFrame internalFrame);

    /**
     * Adds a new frame to this desktop
     * 
     * @param internalFrame
     *            the frame to be removed.
     */
    public void removeFrame(GInternalFrame internalFrame);

    /**
     * Closes all frames contained in this desktop.
     */
    public void closeAllFrames();

    /**
     * Gets all GInternalFrames currently displayed in the desktop.
     * 
     * @return all GInternalFrames currently displayed in the desktop.
     */
    public List getAllFrames();

    /**
     * Minimizes a frame.
     * 
     * @param internalFrame
     *            the frame to minimize
     */
    public void iconify(GFrame internalFrame);

    /**
     * Restores a minimized frame.
     * 
     * @param internalFrame
     *            the minimized frame to restore
     */
    public void deIconify(GFrame internalFrame);

    /**
     * Allows the add to any widget into the desktop.
     * 
     * @param widget
     *            the widget to add
     * @param top
     *            the top location
     * @param left
     *            the left location
     */
    public void addWidget(Widget widget, int left, int top);

    /**
     * Allows to set a widget position.
     * 
     * @param widget
     *            the widget for the position to be set
     * @param top
     *            the top location
     * @param left
     *            the left location
     */
    public void setWidgetPosition(Widget widget, int left, int top);

    public Widget getFramesContainer();

    /**
     * Gets the actual activated frame.
     * 
     * @return the actual active frame or null if no frame is active
     */
    public GInternalFrame getActiveFrame();

    /**
     * Activates a frame.
     * 
     * @param internalFrame
     *            the frame to be activated
     */
    public void setActivateFrame(GInternalFrame internalFrame);

    /**
     * Sets the desktop theme. This theme is propagated to all the
     * {@link GInternalFrame} of the desktop.
     * 
     * Desktop dedicated CSS attributes: gwm-theme-GDesktopPane
     * gwm-theme-GDesktopPane-FrameContainer gwm-theme-GDesktopPane-TaskBar
     * 
     * 
     * @param theme
     *            the desktop theme
     * 
     */
    public void setTheme(String theme);

    /**
     * If it is activated and a GInternalframe is taller than the desktop heigh
     * or if a GInternalFrame is moved bellow the desktop then scrollbars
     * appear. This feature is useful if some of your windows are very tall.
     * (you can also manage this behavior by putting your wide content into a
     * scrollable panel inside your GInternalFrame. By default this feature is
     * desactivated.
     * 
     * @param scrollable
     *            indicates if the frame area is scrollable.
     */
    public void enableScrolling(boolean scrollable);

    public void setMinimizedWindowBarDirection(LayoutConstant direction);

}
