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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-based implementation of <code>GInternalFrame</code>
 * 
 * @author Johan Vos
 */
public class GwtInternalFrame extends SimplePanel implements GInternalFrame,
        EventListener, EventPreview {

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

    private static final int DEFAULT_WIDTH = 200;

    private static final int DEFAULT_HEIGHT = 40;

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


    private GDesktopPane desktopPane;

    private List listeners;

    private int left;

    private int top;

    private Frame frame;
    
    private FlexTable ui;
    
    private FlexTable topRow;
    private FlexTable centerRow;
    private FlexTable bottomRow;
    
    

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
        ui = new FlexTable();
        topRow = new FlexTable();
        centerRow = new FlexTable();
        bottomRow = new FlexTable();
        listeners = new ArrayList();
        resizeImage = new ResizeImage(this);
        this.caption = new Label(title);
        this.caption
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        topBar = new TopBar(this);
        imgTopLeft = new Label();
        imgTopRight = new Label();
        imgBotLeft = new Label();
        imgBotRight = new Label();
        this.maxWidth = Window.getClientWidth();
        this.maxHeight = Window.getClientHeight();
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }

    private void buildGui() {
        this.ui = new FlexTable();
        if (this.width < this.minWidth) {
            this.width = this.minWidth;
        }
        if (this.height < this.minHeight) {
            this.height = this.minHeight;
        }
        this.ui.setSize(this.width + "px", this.height + "px");
        topRow.setWidget(0, 0, imgTopLeft);
        topRow.setWidget(0, 1, topBar);
        topRow.setWidget(0, 2, imgTopRight);
        bottomRow.setWidget(0, 0,  imgBotLeft);
        bottomRow.setHTML(0, 1, "&nbsp;");
        bottomRow.setHTML(0, 2,  "&nbsp;");
        if (url != null) {
            setUrl(url);
        }
        centerRow.setHTML(0, 0, "&nbsp;");
        centerRow.setWidget(0, 1, myContent);
        centerRow.setHTML(0, 2, "&nbsp;");

        setResizable(resizable);

        ui.getCellFormatter().setHeight(1, 0, "100%");
        ui.getCellFormatter().setWidth(1, 0, "100%");
        ui.getCellFormatter().setAlignment(1, 0,
                HasHorizontalAlignment.ALIGN_CENTER,
                HasVerticalAlignment.ALIGN_MIDDLE);
        ui.setBorderWidth(0);
        ui.setCellPadding(0);
        ui.setCellSpacing(0);
        ui.setWidget(0,0,topRow);
        ui.setWidget(1,0 , centerRow);
        ui.setWidget(2,0 , bottomRow);
        super.setWidget(ui);
        setTheme(currentTheme);
        
        topRow.setCellPadding(0);
        topRow.setCellSpacing(0);
        topRow.setHeight("100%");
        topRow.getCellFormatter().setWidth(0,1, "100%");
        centerRow.setCellPadding(0);
        centerRow.setCellSpacing(0);
        centerRow.setWidth("100%");
        centerRow.setHeight("100%");
        centerRow.getCellFormatter().setWidth(0,1, "100%");
        
        bottomRow.setCellPadding(0);
        bottomRow.setCellSpacing(0);
        bottomRow.setWidth("100%");
        bottomRow.getCellFormatter().setWidth(0,1, "100%");
    }

    public void setParentDesktop(GDesktopPane pane) {
        this.desktopPane = pane;
        if (topBar != null) {
            topBar.setDesktopPane((DefaultGDesktopPane) pane);
        }
    }

    GDesktopPane getDesktopPane() {
        return this.desktopPane;
    }

    public void setTheme(String theme) {
        this.currentTheme = theme;
        // buildGui();
        applyTheme();
    }

    private void applyTheme() {
        topBar.setTheme(currentTheme);
        resizeImage.setTheme(currentTheme);
        this.caption.setStyleName(currentTheme + "_title");
        imgTopLeft.setStyleName(this.currentTheme + "_nw");
        imgTopRight.setStyleName(this.currentTheme + "_ne");
        imgBotLeft.setStyleName(this.currentTheme + "_sw");
        topRow.getCellFormatter().setStyleName(0, 1, currentTheme + "_n");
        centerRow.getCellFormatter().setStyleName(0, 1, currentTheme + "_content");
        centerRow.getCellFormatter().setStyleName(0, 0, currentTheme + "_w");
        centerRow.getCellFormatter().setStyleName(0, 2, currentTheme + "_e");
        bottomRow.getCellFormatter().setStyleName(0, 1, currentTheme + "_s");
        topRow.getCellFormatter().setVerticalAlignment(0,0 , HasVerticalAlignment.ALIGN_BOTTOM);
        topRow.getCellFormatter().setVerticalAlignment(0,2 , HasVerticalAlignment.ALIGN_BOTTOM);
        bottomRow.getCellFormatter().setVerticalAlignment(0,0 , HasVerticalAlignment.ALIGN_TOP);
        bottomRow.getCellFormatter().setVerticalAlignment(0,2 , HasVerticalAlignment.ALIGN_TOP);
        if (resizable) {
            resizeImage.setTheme(currentTheme);
        } else {
            bottomRow.getCellFormatter().setStyleName(0, 2, currentTheme + "_se");
        }

    }

    protected String getTheme() {
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
        fireFrameMinimized();
    }

    public void maximize() {
        this.previousTop = getAbsoluteTop();
        this.previousLeft = getAbsoluteLeft();
        this.setLocation(0, 0);
        this.previousWidth = getWidth();
        this.previousHeight = getHeight();
        this.width = maxWidth;
        this.height = maxHeight;
        this.maximized = true;
        buildGui();
        fireFrameMaximized();
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

    public boolean isDraggable() {
        return topBar.isDraggable();
    }

    public boolean isMaximized() {
        return this.maximized;
    }

    public void setLocation(int left, int top) {
        if (desktopPane != null)
            ((DefaultGDesktopPane) desktopPane).setWidgetPosition(this, left,
                    top);
        else {
            Element elem = getElement();
            DOM.setStyleAttribute(elem, "left", left + "px");
            DOM.setStyleAttribute(elem, "top", top + "px");
        }
        this.left = left;
        this.top = top;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        ui.setSize(width + "px", height + "px");
        fireFrameResized();
    }

    public void setWidth(int width) {
        setSize(width, height);
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int height) {
        setSize(width, height);
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
        setLocation(top, left);
    }

    public void setLeft(int left) {
        setLocation(top, left);
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
        myContent = getFrame();
        ((Frame) myContent).setUrl(url);
        centerRow.setWidget(0, 1, myContent);
    }

    private Frame getFrame() {
        if (frame == null) {
            frame = new Frame(url);
            frame.setWidth("100%");
            frame.setHeight("100%");
            DOM.setStyleAttribute(frame.getElement(), "border", "none");
        }
        return frame;

    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
        if (resizable) {
            bottomRow.setWidget(0, 2, resizeImage);
            resizeImage.setTheme(currentTheme);
        } else {
            bottomRow.setHTML(0, 2, "&nbsp;");
            bottomRow.getCellFormatter().setStyleName(0, 2, currentTheme + "_se");
        }
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

    public void setMaximized(boolean v) {
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
    public void fireFrameMinimized() {
        for (int i = 0; i < listeners.size(); i++) {
            GInternalFrameListener listener = (GInternalFrameListener) listeners
                    .get(i);
            listener.frameMinimized(new GInternalFrameEvent(this));
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
        return left;
    }

    public int getTop() {
        return top;
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
        listeners.add(l);
    }

    public void removeInternalFrameListener(GInternalFrameListener l) {
        listeners.remove(l);
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
            DOM.addEventPreview(this);
        } else {
            super.setVisible(false);
            DOM.removeEventPreview(this);
        }
        this.visible = visible;
    }

    public boolean isCloseable() {
        return closable;
    }

    public void startResizing() {
        if (url != null) {
            this.previousUrl = url;
            this.myContent = new Label("");
            centerRow.setWidget(0, 1, myContent);
        }
        this.url = null;
    }

    public void stopResizing() {
        this.url = this.previousUrl;
        buildGui();
    }

    public void show() {
        if (desktopPane == null) {
            DOM.setStyleAttribute(getElement(), "position", "absolute");
            RootPanel.get().add(this);
        }
        super.setVisible(true);
        DOM.setIntStyleAttribute(getElement(), "zIndex", ++layerOfTheTopWindow);
        topFrame = this;
    }

}
