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

import com.google.gwt.user.client.ui.Widget;

/**
 * Provides methods to interact with a frame.
 * 
 * @author luciano broussal
 * 
 * Type GInternalFrame, created on 28 nov. 06 at 16:35:31
 * 
 */
public interface GInternalFrame {

    /**
     * Retrieves the window's id.
     * 
     * @return String id.
     */
    public String getId();

    /**
     * Permits to change the property style on a window.
     * 
     * @param style
     */
    public void setStyle(String style);

    /**
     * Shows window at its current position.
     * 
     * @param modal
     *            if true the screen is disabled
     */
    public void show(boolean modal);

    /**
     * Shows window in page's center.
     * 
     * @param modal
     *            if true the screen is disabled
     */
    public void showCenter(boolean modal);

    /**
     * Sets window content using an existing Widget.
     * 
     * @param widget
     *            the content to show into the window
     * @throws IllegalStateException
     *             if it is called before a call of one showXXX(...) method.
     * @see <a
     *      href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.Widget.html">GWT
     *      Widget</a>
     * 
     */
    public void setContent(Widget widget);

    /**
     * Sets window content using an Plain-Text or Html code.
     * 
     * @param content
     *            the content to show into the window
     */
    public void setContent(String content);

    /**
     * Minimizes the window, only top bar will be displayed.
     */
    public void minimize();

    /**
     * Maximizes the window, the window will fit the viewable area of the page.
     */
    public void maximize();

    /**
     * Brings current window in front of all others.
     */
    public void toFront();

    /**
     * Window destructor.
     */
    public void destroy();

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
     * The window will be destroy by clicking on close button instead of being
     * hidden.
     */
    public void setDestroyOnClose();

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
     * Sets window height.
     * 
     * @param height
     *            Height in pixels
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setHeight(int height);

    /**
     * Sets the minimum window width.
     * 
     * @param minWidth
     *            minWidth in pixels
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setMinimumWidth(int minWidth);

    /**
     * Sets the minimum window height.
     * 
     * @param minHeight
     *            minHeight in pixels
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setMinimumHeight(int minHeight);

    /**
     * Sets the maximum window width.
     * 
     * @param maxWidth
     *            maxWidth in pixels
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setMaximumWidth(int maxWidth);

    /**
     * Sets the maximum window height.
     * 
     * @param maxHeight
     *            maxHeight in pixels
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setMaximumHeight(int maxHeight);

    /**
     * Sets the position from the top.
     * 
     * @param top
     *            top in pixels
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setTop(int top);

    /**
     * Sets the position from the left.
     * 
     * @param left
     *            left in pixels
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setLeft(int left);

    /**
     * Recomputes window width, useful when you change window content and do not
     * want scrolling.
     */
    public void updateWidth();

    /**
     * Recomputes window height, useful when you change window content and do
     * not want scrolling.
     */
    public void updateHeight();

    /**
     * Sets window title.
     * 
     * @param title
     *            Window title
     */
    public void setTitle(String title);

    /**
     * Gets window title.
     * 
     * @return title Window title
     */
    public String getTitle();

    /**
     * Sets the URL of window content.
     * 
     * @param url
     *            to display.
     * 
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setUrl(String url);

    /**
     * Sets if the window is resizable.
     * 
     * @param resizable
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setResizable(boolean resizable);

    /**
     * Sets if the window is closable.
     * 
     * @param closable
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setClosable(boolean closable);

    /**
     * Sets if the window is minimizable.
     * 
     * @param minimizable
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setMinimizable(boolean minimizable);

    /**
     * Sets if the window is maximizable.
     * 
     * 
     * @param maximizable
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setMaximizable(boolean maximizable);

    /**
     * Sets if the window is draggable.
     * 
     * @param draggable
     * @throws IllegalStateException
     *             if it is called after a call of one showXXX(...) method
     */
    public void setDraggable(boolean draggable);
    
    /**
     * Returns the GDesktopPane who owns this GInternalFrame
     * @return
     */
    public GDesktopPane getParentDesktop();
}
