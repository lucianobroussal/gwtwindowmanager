/*
 * Copyright (c) 2006-2007 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtframemanager.org)
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

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * Provides the behavior handles to interact with a frame.
 */
public interface GFrame {

    /**
     * Change the frame look & feel theme.
     * 
     * @param theme
     */
    public void setTheme(String theme);
    
   
    /**
     * @return the actual frame look & feel theme.
     */
    public String getTheme();

    /**
     * Shows the frame at its current position.
     * 
     */
    public void setVisible(boolean isVisible);

    /**
     * Sets the frame content using an existing Widget.
     * 
     * @param widget
     *            the content to show into the frame
     * @see <a
     *      href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.Widget.html">GWT
     *      Widget</a>
     */
    public void setContent(Widget theContent);

    /**
     * Sets the frame content using an Plain-Text or Html code.
     * 
     * @param content
     *            the content to show into the frame
     */
    public void setContent(String content);

    /**
     * Return the content of this frame.
     * 
     * @return
     */
    public Widget getContent();

    /**
     * The frame destructor.
     */
    public void close();

    /**
     * Sets if the frame is resizable.
     * 
     * @param resizable
     */
    public void setResizable(boolean resizable);

    /**
     * Sets if the frame is closable.
     * 
     * @param closable
     */
    public void setClosable(boolean closable);

    /**
     * Sets the frame's minimizable state.
     * 
     * @param minimizable
     */
    public void setMinimizable(boolean minimizable);

    /**
     * Returns the frame minimizable state.
     */
    public boolean isMinimizable();

    /**
     * Sets the frame's maximizable state.
     * 
     * @param maximizable
     */
    public void setMaximizable(boolean maximizable);

    /**
     * Returns the frame draggable state.
     * 
     * @return
     */
    public boolean isDraggable();

    /**
     * Returns the frame maximizable state.
     * 
     * @return
     */
    public boolean isMaximizable();

    /**
     * Sets if the frame is draggable.
     * 
     * @param draggable
     */
    public void setDraggable(boolean draggable);

    /**
     * Provides the frame minimized status.
     * 
     * @return Returns true if the frame is minimized
     */
    public boolean isMinimized();

    /**
     * Provides the frame maximized status.
     * 
     * @return Returns true if the frame is maximized
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
     * Sets frame top-left position.
     * 
     * @param top
     *            Top position in pixels
     * @param left
     *            Left position in pixels
     */
    public void setLocation(int top, int left);

    /**
     * Sets frame content size.
     * 
     * @param width
     *            Width in pixels
     * @param height
     *            Height in pixels
     */
    public void setSize(int width, int height);

    /**
     * Sets frame width.
     * 
     * @param width
     *            Width in pixels
     */
    public void setWidth(int width);

    /**
     * Returns the frame's width.
     * 
     * @return
     */
    public int getWidth();

    /**
     * Sets frame height.
     * 
     * @param height
     *            Height in pixels
     */
    public void setHeight(int height);

    /**
     * Returns the frame's height.
     * 
     * @return
     */
    public int getHeight();

    /**
     * Sets the minimum frame width.
     * 
     * @param minWidth
     *            minWidth in pixels
     */
    public void setMinimumWidth(int minWidth);

    /**
     * Sets the minimum frame height.
     * 
     * @param minHeight
     *            minHeight in pixels
     */
    public void setMinimumHeight(int minHeight);

    /**
     * Sets the maximum frame width.
     * 
     * @param maxWidth
     *            maxWidth in pixels
     */
    public void setMaximumWidth(int maxWidth);

    /**
     * Sets the maximum frame height.
     * 
     * @param maxHeight
     *            maxHeight in pixels
     */
    public void setMaximumHeight(int maxHeight);

    /**
     * Returns the frame's minimum height.
     * 
     * @return
     */
    public int getMinimumHeight();

    /**
     * Returns the frame's minimum width.
     * 
     * @return
     */
    public int getMinimumWidth();

    /**
     * Returns the frame's maximum height.
     * 
     * @return
     */
    public int getMaximumHeight();

    /**
     * Returns the frame's maximum width.
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
     * Sets frame title.
     * 
     * @param caption
     *            frame title
     */
    public void setCaption(String caption);

    /**
     * Gets frame title.
     * 
     * @return title frame title
     */
    public String getCaption();

    /**
     * Sets the URL of frame content.
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
    
    /**
     * Show the frame with modal effect.
     */
    public void showModal();
    
    /**
     * Set the frame drag mode
     * @param outline true displays a ghost during frame dragging. (default:true)
     * false the full frame itself is visible during the drag action.
     */
    
    public void setOutlineDragMode(boolean outline);
    
    /**
     * Return the frame visibility during the drag action.
     * @return
     */
    public boolean isDragOutline();
    
    
    /**
     * Updates the frame size (usefull specially on IE if you dynamically change the content of the frame).  
     */
    public void updateSize();
    
        
}
