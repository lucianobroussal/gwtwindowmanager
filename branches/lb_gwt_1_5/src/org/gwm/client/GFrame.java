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

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;

/**
 * Provides the behavior handles to interact with a frame.
 */
public interface GFrame {

	/**
     * Changes the frame's look & feel theme.
     * 
     * @param theme
     */
    public void setTheme(String theme);

    /**
     * Gets the frame's look & feel theme.
     * 
     * @return the actual frame's look & feel theme.
     */
    public String getTheme();

    /**
     * Shows the frame at its current position.
     * 
     */
    public void setVisible(boolean isVisible);

    /**
     * Sets the frame's content using an existing Widget.
     * 
     * @param widget
     *            the content to show into the frame
     * @see <a
     *      href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.Widget.html">GWT
     *      Widget</a>
     */
    public void setContent(Widget theContent);

    /**
     * Sets the frame's content using a Plain-Text or Html code.
     * 
     * @param content
     *            the content to show into the frame
     */
    public void setContent(String content);

    /**
     * Return the content of this frame.
     * 
     * @return the content of this frame
     */
    public Widget getContent();

    /**
     * Closes the frame.
     */
    public void close();

    /**
     * Sets if the frame is resizable.
     * 
     * @param resizable
     *            true if the frame is resizable
     */
    public void setResizable(boolean resizable);

    /**
     * Returns true if the frame is resizable.
     * 
     * @return resizable true if the frame is resizable
     */
    public boolean isResizable();

    /**
     * Sets if the frame is closable.
     * 
     * @param closable
     *            true if the frame is closable
     */
    public void setClosable(boolean closable);

    /**
     * Returns true if the frame is closable.
     * 
     * @return closable true if the frame is closable
     */
    public boolean isClosable();

    /**
     * Sets if the frame is minimizable.
     * 
     * @param minimizable
     *            true if the frame is minimizable
     */
    public void setMinimizable(boolean minimizable);

    /**
     * Returns true if the frame is minimizable.
     * 
     * @return true if the frame is minimizable
     */
    public boolean isMinimizable();

    /**
     * Sets if the frame is maximizable.
     * 
     * @param maximizable
     *            true if the frame is maximizable
     */
    public void setMaximizable(boolean maximizable);

    /**
     * Returns true if the frame is draggable.
     * 
     * @return true if the frame is draggable
     */
    public boolean isDraggable();

    /**
     * Returns true if the frame is maximizable.
     * 
     * @return true if the frame is maximizable
     */
    public boolean isMaximizable();

    /**
     * Sets if the frame is draggable.
     * 
     * @param draggable
     *            true if the frame is draggable
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
     * Maximizes the frame size.
     */
    public void maximize();

    /**
     * Minimizes the frame size.
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
     * Gets the frame's width.
     * 
     * @return the widht of the frame in pixels
     */
    public int getWidth();

    /**
     * Sets frame height.
     * 
     * @param height
     *            the height of the frame in pixels
     */
    public void setHeight(int height);

    /**
     * Gets the frame's height.
     * 
     * @return the height of the frame in pixels
     */
    public int getHeight();

    /**
     * Sets the minimum frame width.
     * 
     * @param minWidth
     *            the minimum frame width in pixels
     */
    public void setMinimumWidth(int minWidth);

    /**
     * Sets the minimum frame height.
     * 
     * @param minHeight
     *            the minimum frame height in pixels
     */
    public void setMinimumHeight(int minHeight);

    /**
     * Sets the maximum frame width.
     * 
     * @param maxWidth
     *            the maximum frame width in pixels
     */
    public void setMaximumWidth(int maxWidth);

    /**
     * Sets the maximum frame height.
     * 
     * @param maxHeight
     *            the maximum frame height in pixels
     */
    public void setMaximumHeight(int maxHeight);

    /**
     * Gets the frame's minimum height.
     * 
     * @return the frame's minimum height in pixels
     */
    public int getMinimumHeight();

    /**
     * Gets the frame's minimum width.
     * 
     * @return the frame's minimum width in pixels
     */
    public int getMinimumWidth();

    /**
     * Gets the frame's maximum height.
     * 
     * @return the frame's maximum height in pixels
     */
    public int getMaximumHeight();

    /**
     * Returns the frame's maximum width.
     * 
     * @return the frame's maximum width in pixels
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
     * Gets the position from the top.
     * 
     * @return the position from the top in pixels
     */
    public int getTop();

    /**
     * Sets the position from the left.
     * 
     * @param left
     *            the position from the left in pixels
     */
    public void setLeft(int left);

    /**
     * Returns the position from the left.
     * 
     * @return the position from the left in pixels
     */
    public int getLeft();

    /**
     * Sets the frame title.
     * 
     * @param caption
     *            frame title
     */
    public void setCaption(String caption);

    /**
     * Gets the frame title.
     * 
     * @return title frame title
     */
    public String getCaption();

    /**
     * Sets the URL of the frame content.
     * 
     * @param url
     *            to display.
     * 
     */
    public void setUrl(String url);

    /**
     * Return the URL of the frame content.
     * 
     * @param url
     *            displayed.
     * 
     */
    public String getUrl();

    /**
     * Adds a GFrameListener to this GFrame.
     * 
     * @param listener
     *            to listen for frame events on this frame
     */
    public void addFrameListener(GFrameListener listener);

    /**
     * Removes a GFrameListener from this GFrame.
     * 
     * @param listener
     *            the listener to remove from this frame
     */
    public void removeGFrameListener(GFrameListener listener);

    /**
     * Shows the frame in modal mode.
     */
    public void showModal();

    /**
     * Sets the frame's drag mode
     * 
     * @param outline
     *            true displays a ghost during frame dragging. (default:true)
     *            false the full frame itself is visible during the drag action.
     */

    public void setOutlineDragMode(boolean outline);

    /**
     * Returns the frame's drag mode.
     * 
     * @return true if the frame is displayed as a ghost image during dragging
     *         false if the full frame itself is visible during dragging
     */
    public boolean isDragOutline();

    /**
     * Updates the frame size (usefull specially on IE if you dynamically change
     * the content of the frame).
     */
    public void updateSize();
    
    /**
     * Set the horizontal alignment of the content.
     * @param alignmentConstant
     */
    public void setContentHorizontalAlignment(HasHorizontalAlignment.HorizontalAlignmentConstant alignmentConstant);
    
    /**
     * Set the vertical alignment of the content.
     * @param alignmentConstant
     */
    public void setContentVerticalAlignment(HasVerticalAlignment.VerticalAlignmentConstant alignmentConstant);
    

}
