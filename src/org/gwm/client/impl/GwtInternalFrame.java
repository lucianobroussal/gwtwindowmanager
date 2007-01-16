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

import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GInternalFrameEvent;
import org.gwm.client.event.GInternalFrameListener;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.*;

/**
 * GWT-based implementation of <code>GInternalFrame</code>
 * 
 * @author Johan Vos
 */
public class GwtInternalFrame extends PopupPanel implements GInternalFrame, 
        EventListener {

    private static GInternalFrame topFrame;

    private static int layerOfTheTopWindow;

    private String title;

    private TopBar topBar;

    private ResizeImage resizeImage;

    private Label caption;

    private Widget myContent;

    private String url;

    private String previousUrl;

    private boolean visible;

    private static final int DEFAULT_WIDTH = 240;

    private static final int DEFAULT_HEIGHT = 300;

    private static final String DEFAULT_STYLE = "theme1";

    private int maxWidth, maxHeight;

    private int minWidth, minHeight;

    private int width = -1;

    private int height = -1;

    private String currentTheme;

    private boolean closable, maximizable, minimizable, draggable, resizable;

    private boolean maximized, minimized;

    private Label imgTopLeft;

    private Label imgTopRight;

    private Label imgBotLeft;

    private Label imgBotRight;

    private int previousWidth, restoreWidth;

    private int previousHeight, restoreHeight;

    private int previousTop, previousLeft;

    private FlexTable panel = new FlexTable();

    private GDesktopPane desktopPane;

    private List listeners;

    public GwtInternalFrame() {
        this("");
    }

    public GwtInternalFrame(String title) {
        this.currentTheme = DEFAULT_STYLE;
        this.title = title;
        this.myContent = new HTML("");
        this.closable = true;
        this.minimizable = true;
        this.maximizable = true;
        this.resizable = true;
        initializeFrame();
        buildGui();
        sinkEvents(Event.ONCLICK | Event.MOUSEEVENTS);
    }

    private void initializeFrame() {
        listeners = new ArrayList();
        resizeImage = new ResizeImage(this);
        this.caption = new Label(title);
        this.caption.addStyleName(currentTheme + "_title");
        this.caption
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        topBar = new TopBar(this);
        imgTopLeft = new Label();
        imgTopLeft.addStyleName(this.currentTheme + "_nw");
        imgTopRight = new Label();
        imgTopRight.addStyleName(this.currentTheme + "_ne");
        imgBotLeft = new Label();
        imgBotLeft.addStyleName(this.currentTheme + "_sw");
        imgBotRight = new Label();
        imgBotRight.addStyleName(this.currentTheme + "_se");
        this.maxWidth = Window.getClientWidth();
        this.maxHeight = Window.getClientHeight();
        this.minWidth = 240;
        this.minHeight = 40;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }

    private void buildGui() {
        this.panel = new FlexTable();
        if (this.width < this.minWidth) {
            this.width = this.minWidth;
        }
        if (this.height < this.minHeight) {
            this.height = this.minHeight;
        }
        this.panel.setSize(this.width + "px", this.height + "px");
        panel.setWidget(0, 0, imgTopLeft);
        panel.setWidget(0, 1, topBar);
        panel.getCellFormatter().setStyleName(0, 1, currentTheme + "_n");
        panel.setWidget(0, 2, imgTopRight);
        panel.setWidget(2, 0, imgBotLeft);
        panel.setWidget(2, 2, imgBotRight);
        if (url != null) {
            String urlCode = "<iframe src=\"" + url + "\" height=\""
                    + (this.height - 60) + "\" width=\"" + (this.width - 40)
                    + "\" frameborder=\"0\"/>";
            myContent = new HTML(urlCode);
        }
        panel.setWidget(1, 1, myContent);
        panel.getCellFormatter().setStyleName(1, 1, currentTheme + "_content");
        panel.setHTML(1, 0, "&nbsp;");
        panel.getCellFormatter().setStyleName(1, 0, currentTheme + "_w");
        panel.setHTML(1, 2, "&nbsp;");
        panel.getCellFormatter().setStyleName(1, 2, currentTheme + "_e");

        panel.setHTML(2, 0, "&nbsp;");
        panel.getCellFormatter().setStyleName(2, 0, currentTheme + "_sw");

        panel.setHTML(2, 1, "&nbsp;");
        panel.getCellFormatter().setStyleName(2, 1, currentTheme + "_s");

        if (resizable) {
            panel.setWidget(2, 2, resizeImage);
        } else {
            panel.getCellFormatter().setStyleName(2, 2, currentTheme + "_se");
            panel.setHTML(2, 2, "&nbsp;");
        }
        panel.getCellFormatter().setHeight(1, 1, "100%");
        panel.getCellFormatter().setWidth(1, 1, "100%");
        panel.getCellFormatter().setAlignment(1, 0,
                HasHorizontalAlignment.ALIGN_CENTER,
                HasVerticalAlignment.ALIGN_MIDDLE);
        panel.setBorderWidth(0);
        panel.setCellPadding(0);
        panel.setCellSpacing(0);
        setStyleName("gwt-DialogBox");
        super.setWidget(panel);
    }

    public void setParentDesktop(GDesktopPane pane) {
        this.desktopPane = pane;
        if (topBar != null) {
            topBar.setDesktopPane ((DefaultGDesktopPane)(pane));
        }
    }

    GDesktopPane getDesktopPane () {
      return this.desktopPane;
    }

    public void setTheme(String theme) {
        this.currentTheme = theme;
        buildGui();
    }

    protected String getTheme () {
        return this.currentTheme;
    }

    public void setContent(Widget widget) {
        myContent = widget;
        this.url = null;
        buildGui();
    }

    public void setContent(String content) {
        myContent = new HTML(content);
        this.url = null;
        buildGui();
    }

    public void minimize() {
        // TODO verify the desktop exists
        if (desktopPane != null)
            desktopPane.iconify(this);
        else {
            getContent().setVisible(false);
        }
        this.minimized = true;
    }

    public void maximize() {
        this.previousTop = getPopupTop();
        this.previousLeft = getPopupLeft();
        this.setPopupPosition(0, 0);
        this.previousWidth = getWidth();
        this.previousHeight = getHeight();
        this.width = maxWidth;
        this.height = maxHeight;
        this.maximized = true;
        buildGui();
    }

    public void restore() {
        this.width = previousWidth;
        this.height = previousHeight;
        this.maximized = false;
        this.minimized = false;
        setLocation(this.previousLeft, this.previousTop);
        if (!getContent().isVisible()) {
            getContent().setVisible(true);
        }
        buildGui();
    }

    public void close() {
        // TODO to check with Johan !!! how avoiding this window will be showed
        // again ?
        // how releasing resources ?
        setVisible(false);
        removeFromParent();
    }

    public boolean isMinimized() {
        return this.minimized;
    }
    
    public boolean isDraggable(){
        return topBar.isDraggable();
    }

    public boolean isMaximized() {
        return this.maximized;
    }

    public void setLocation(int left, int top) {
        setPopupPosition(left, top);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        buildGui();
    }

    public void setWidth(int width) {
        this.width = width;
        panel.setWidth(width + "px");
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int height) {
        this.height = height;
        panel.setHeight(height + "px");
    }

    public int getHeight() {
        return this.height;
    }

    public void setMinimumWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinimumWidth() {
        return this.minWidth;
    }

    public void setMinimumHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMinimumHeight() {
        return this.minHeight;
    }

    public void setMaximumWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMaximumHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setTop(int top) {
        setPopupPosition(top, getPopupLeft());
    }

    public void setLeft(int left) {
        setPopupPosition(getPopupTop(), left);
    }

    public void setCaption(String title) {
        this.title = title;
        this.topBar.setCaption(title);
    }

    public String getCaption() {
        return this.caption.getText();
    }

    public void setUrl(String url) {
        this.url = url;
        String urlCode = "<iframe src=\"" + url + "\" height=\""
                + getOffsetHeight() + "\" width=\"" + getOffsetWidth()
                + "\" frameborder=\"0\"/>";
        myContent = new HTML(urlCode);
        buildGui();
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    public void setMinimizable(boolean minimizable) {
        this.minimizable = minimizable;
    }

    public void setMaximizable(boolean maximizable) {
        this.maximizable = maximizable;
        topBar.updateTopBar();
    }

    public void setDraggable(boolean draggable) {
        topBar.setDraggable(draggable);
    }

    public void onBrowserEvent(Event event) {
        int type = DOM.eventGetType(event);
        if (type == Event.ONMOUSEDOWN) {
            if (topFrame != this) {
                show();
            }
        }
    }

    public boolean onEventPreview(Event evt) {
        // super.onEventPreview(evt); We don't take this into account
        return true;
    }

    public void addGFrameListener(GInternalFrameListener listener) {
        listeners.add(listener);
    }

    public void removeGFrameListener(GInternalFrameListener listener) {
        listeners.remove(listener);
    }

    public void setMaximized(boolean v) {
    }

    /**
     * Fires the event of the activation of this frame to its listeners.
     */
    private void fireFrameActivated() {
        for (int i = 0; i < listeners.size(); i++) {
            GInternalFrameListener listener = (GInternalFrameListener) listeners
                    .get(i);
            listener.frameActivated(new GInternalFrameEvent(this));
        }
    }

    /**
     * Fires the event of the resizing of this frame to its listeners.
     */
    private void fireFrameResized() {
        for (int i = 0; i < listeners.size(); i++) {
            GInternalFrameListener listener = (GInternalFrameListener) listeners
                    .get(i);
            listener.frameResized(new GInternalFrameEvent(this));
        }
    }

    /**
     * Fires the closed event of this frame to its listeners.
     */
    private void fireFrameClosed() {
        for (int i = 0; i < listeners.size(); i++) {
            GInternalFrameListener listener = (GInternalFrameListener) listeners
                    .get(i);
            listener.frameClosed(new GInternalFrameEvent(this));
        }
    }

    /**
     * Fires the frameMaximized event of this frame to its listeners.
     */
    public void fireFrameMaximized() {
        for (int i = 0; i < listeners.size(); i++) {
            GInternalFrameListener listener = (GInternalFrameListener) listeners
                    .get(i);
            listener.frameMaximized(new GInternalFrameEvent(this));
        }
    }

    /**
     * Fires the frameMinimized event of this frame to its listeners.
     */
    public void fireFrameIconified() {
        for (int i = 0; i < listeners.size(); i++) {
            GInternalFrameListener listener = (GInternalFrameListener) listeners
                    .get(i);
            listener.frameIconified(new GInternalFrameEvent(this));
        }
    }

    /**
     * Fires the frameMoved event of this frame to its listeners.
     */
    private void fireFrameMoved() {
        for (int i = 0; i < listeners.size(); i++) {
            GInternalFrameListener listener = (GInternalFrameListener) listeners
                    .get(i);
            listener.frameIconified(new GInternalFrameEvent(this));
        }
    }

    // """""""""""""""""""""

    public boolean isMaximizable() {
        return maximizable;
    }

    public boolean isMinimizable() {
        return minimizable;
    }

    public int getLeft() {
        return 0;
    }

    public int getTop() {
        return 0;
    }

    public int getMaximumWidth() {
        return 0;
    }

    public int getMaximumHeight() {
        return 0;
    }

    public Widget getContent() {
        return myContent;
    }

    public void addInternalFrameListener(GInternalFrameListener l) {
    }

    public void removeInternalFrameListener(GInternalFrameListener l) {
    }

    public GDesktopPane getParentDesktop() {
        return desktopPane;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if (this.visible == visible)
            return;
        if (visible) {
            this.minimized = false;
            show();
        } else {
            hide();
        }
        this.visible = visible;
    }

    public boolean isCloseable() {
        return closable;
    }

    public void startResizing() {
        if (url != null) {
            this.previousUrl = url;
            this.myContent = new Label ("");
            panel.setWidget (1, 1, myContent);
        }
        this.url = null;
    }

    public void stopResizing() {
        this.url = this.previousUrl;
        buildGui();
    }

    public void show() {
        super.show();
        DOM.setIntStyleAttribute(getElement(), "zIndex", ++layerOfTheTopWindow);
        topFrame = this;
    }

}
