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

import org.gwm.client.event.GFrameListener;

import com.google.gwt.user.client.ui.Widget;

/**
 * Provides the behavior handles to interact with a frame.
 */
public interface GFrame {

    /**
     * Permits to change the property style on a window.
     * 
     * @param theme
     */
    public void setTheme(String theme);

    /**
     * Shows window at its current position.
     * 
     */
    public void setVisible(boolean isVisible);

    /**
     * Sets window content using an existing Widget.
     * 
     * @param widget
     *            the content to show into the window
     * @see <a
     *      href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.Widget.html">GWT
     *      Widget</a>
     */
    public void setContent(Widget theContent);

    /**
     * Sets window content using an Plain-Text or Html code.
     * 
     * @param content
     *            the content to show into the window
     */
    public void setContent(String content);

    /**
     * Return the content of this window.
     * 
     * @return
     */
    public Widget getContent();

    /**
     * Window destructor.
     */
    public void close();

    /**
     * Sets if the window is resizable.
     * 
     * @param resizable
     */
    public void setResizable(boolean resizable);

    /**
     * Sets if the window is closable.
     * 
     * @param closable
     */
    public void setClosable(boolean closable);

    /**
     * Sets the window's minimizable state.
     * 
     * @param minimizable
     */
    public void setMinimizable(boolean minimizable);

    /**
     * Returns the window minimizable state.
     */
    public boolean isMinimizable();

    /**
     * Sets the window's maximizable state.
     * 
     * @param maximizable
     */
    public void setMaximizable(boolean maximizable);

    /**
     * Returns the window draggable state.
     * 
     * @return
     */
    public boolean isDraggable();

    /**
     * Returns the window maximizable state.
     * 
     * @return
     */
    public boolean isMaximizable();

    /**
     * Sets if the window is draggable.
     * 
     * @param draggable
     */
    public void setDraggable(boolean draggable);

    /**
     * Provides the window minimized status.
     * 
     * @return Returns true if the window is minimized
     */
    public boolean isMinimized();

    /**
     * Provides the window maximized status.
     * 
     * @return Returns true if the window is maximized
     */
    public boolean isMaximized();

    /**
     * Maximize the frame size.
     */
    public void maximize();

    /**
     * Minimize the frame size.
     */
    public void minimize();

    /**
     * Restore the previous state of the frame after a minimize or maximize
     * action.
     */
    public void restore();

    /**
     * Sets window top-left position.
     * 
     * @param top
     *            Top position in pixels
     * @param left
     *            Left position in pixels
     */
    public void setLocation(int top, int left);

    /**
     * Sets window content size.
     * 
     * @param width
     *            Width in pixels
     * @param height
     *            Height in pixels
     */
    public void setSize(int width, int height);

    /**
     * Sets window width.
     * 
     * @param width
     *            Width in pixels
     */
    public void setWidth(int width);

    /**
     * Returns the window's width.
     * 
     * @return
     */
    public int getWidth();

    /**
     * Sets window height.
     * 
     * @param height
     *            Height in pixels
     */
    public void setHeight(int height);

    /**
     * Returns the window's height.
     * 
     * @return
     */
    public int getHeight();

    /**
     * Sets the minimum window width.
     * 
     * @param minWidth
     *            minWidth in pixels
     */
    public void setMinimumWidth(int minWidth);

    /**
     * Sets the minimum window height.
     * 
     * @param minHeight
     *            minHeight in pixels
     */
    public void setMinimumHeight(int minHeight);

    /**
     * Sets the maximum window width.
     * 
     * @param maxWidth
     *            maxWidth in pixels
     */
    public void setMaximumWidth(int maxWidth);

    /**
     * Sets the maximum window height.
     * 
     * @param maxHeight
     *            maxHeight in pixels
     */
    public void setMaximumHeight(int maxHeight);

    /**
     * Returns the window's minimum height.
     * 
     * @return
     */
    public int getMinimumHeight();

    /**
     * Returns the window's minimum width.
     * 
     * @return
     */
    public int getMinimumWidth();

    /**
     * Returns the window's maximum height.
     * 
     * @return
     */
    public int getMaximumHeight();

    /**
     * Returns the window's maximum width.
     * 
     * @return
     */
    public int getMaximumWidth();

    /**
     * Sets the position from the top.
     * 
     * @param top
     *            top in pixels
     */
    public void setTop(int top);

    /**
     * Returns the position from the top.
     * 
     * @return
     */
    public int getTop();

    /**
     * Sets the position from the left.
     * 
     * @param left
     *            left in pixels
     */
    public void setLeft(int left);

    /**
     * Returns the position from the left.
     * 
     * @return
     */
    public int getLeft();

    /**
     * Sets window title.
     * 
     * @param caption
     *            Window title
     */
    public void setCaption(String caption);

    /**
     * Gets window title.
     * 
     * @return title Window title
     */
    public String getCaption();

    /**
     * Sets the URL of window content.
     * 
     * @param url
     *            to display.
     * 
     */
    public void setUrl(String url);

    
    /**
     * Add a GFrameListener from this GFrame.
     * 
     * @param listener
     */
    public void addFrameListener(GFrameListener listener);

    /**
     * Remove a GFrameListener from this GFrame.
     * 
     * @param listener
     */
    public void removeGFrameListener(GFrameListener listener);

}
