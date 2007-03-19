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
package org.gwm.client.impl;

import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GFrameAdapter;
import org.gwm.client.event.GFrameEvent;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TopBar extends FlexTable implements MouseListener, ClickListener {

    //    
    static DefaultGFrame debug = new DefaultGFrame();

    static VerticalPanel debugContent = new VerticalPanel();

    static int cpt = 0;

    static {
        debugContent.setSize("200", "400");
        debugContent.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        debugContent.setSpacing(0);
        //debug.setSize(300, 400);
        debug.setContent(debugContent);
        debug.setLocation(0, 0);
        debug.setOutlineDragMode(true);
        //debug.setTheme("alphacube");
        //debug.setVisible(true);
    }

    private HTML caption;

    private String currentTheme;

    private String title;

    protected DefaultGFrame parent;

    protected Label closeArea;

    protected Label maximizeArea;

    protected Label minimizeArea;

    protected int dragStartX, dragStartY;

    protected boolean dragging;

    protected boolean draggable;

    protected Label restoreButton;

    protected void addLog(String log) {
        cpt++;
        if (cpt % 10 == 0) {
            debugContent.clear();
        }
        debugContent.add(new HTML(log));
    }

    protected static SimplePanel outline;
    static {
        outline = new SimplePanel();
        DOM.setStyleAttribute(outline.getElement(), "backgroundColor",
                "#DFF2FF");
        DOM.setStyleAttribute(outline.getElement(), "position", "absolute");
        DOM.setStyleAttribute(outline.getElement(), "filter",
                "progid:DXImageTransform.Microsoft.Alpha(opacity=50)");
        DOM.setStyleAttribute(outline.getElement(), "-mozOpacity", "0.5");
        DOM.setStyleAttribute(outline.getElement(), "opacity", "0.5");
        DOM
                .setStyleAttribute(outline.getElement(), "border",
                        "1px dotted gray");
        outline.setVisible(false);
        RootPanel.get().add(outline);
    }

    TopBar() {
        super();
    }

    public void init(DefaultGFrame frame) {
        this.parent = frame;
        this.draggable = true;
        buildGui();
        initListeners();
        setMovingGuard();
//        Window.alert("IE-topbar");
    }

    private void buildGui() {
        this.currentTheme = parent.getTheme();
        this.title = parent.getCaption();
        caption = new HTML(title);
        DOM.setStyleAttribute(caption.getElement(), "whiteSpace", "nowrap");
        closeArea = new Label();
        closeArea.addClickListener(this);
        minimizeArea = new Label();
        minimizeArea.addClickListener(this);
        maximizeArea = new Label();
        maximizeArea.addClickListener(this);
        restoreButton = new Label("");
        restoreButton.setStyleName(this.currentTheme + "_topBar_restore");
        
        
        setWidget(0, 0, caption);
        getCellFormatter().setWidth(0, 0, "100%");
        setWidget(0, 1, minimizeArea);
        setWidget(0, 2, maximizeArea);
        setWidget(0, 3, closeArea);
        setTheme(currentTheme);
        setCellPadding(0);
        setCellSpacing(0);
        setStyleName("topBar");
        updateTopBar();
    }

    public void onClick(Widget w) {
        if (w.equals(closeArea)) {
            parent.close();
        }
        if (w.equals(maximizeArea)) {
            if (parent.isMaximized()) {
                parent.restore();
                updateTopBar();
            } else {
                parent.maximize();
                if (parent.isMinimizable()) {
                    minimizeArea.setVisible(false);
                }

            }
        }
        if (w.equals(minimizeArea)) {
            if (parent.isMinimized()) {
                parent.restore();
            } else {
                parent.minimize();
            }
        }
    }

    public void onMouseDown(Widget sender, int x, int y) {
        if (draggable) {
            dragging = true;
            DOM.addEventPreview(parent);
            DOM.setCapture(caption.getElement());
            dragStartX = x;
            dragStartY = y;

            if (parent.isDragOutline()) {
                outline.setSize(parent.getWidth() + "px", parent.getHeight()
                        + "px");
                DOM.setStyleAttribute(outline.getElement(), "left", parent
                        .getLeft()
                        + "px");
                DOM.setStyleAttribute(outline.getElement(), "top", parent
                        .getTop()
                        + "px");
                outline.setVisible(true);
                DOM.setIntStyleAttribute(outline.getElement(), "zIndex",
                        DefaultGFrame.getLayerOfTheTopWindow() + 1);
            } else {
                DOM.setStyleAttribute(parent.getElement(), "filter",
                        "progid:DXImageTransform.Microsoft.Alpha(opacity=80)");
                DOM
                        .setStyleAttribute(parent.getElement(), "-mozOpacity",
                                "0.9");
                DOM.setStyleAttribute(parent.getElement(), "opacity", "0.8");
                parent.getSelectBoxManager().onParentDragStart(parent);
            }
        }
    }

    public void onMouseEnter(Widget sender) {
        System.out.println("Mouse Enter");

    }

    public void onMouseLeave(Widget sender) {
        System.out.println("Mouse Leave");
    }

    public void onMouseMove(Widget sender, int x, int y) {
        System.out.println("Mouse Move " + x + " / " + y);

        if (dragging) {
            int absX = x + parent.getLeft();
            int absY = y + parent.getTop();
            ;

            if (parent.isDragOutline()) {

                DOM.setStyleAttribute(outline.getElement(), "left", absX
                        - dragStartX + "px");
                DOM.setStyleAttribute(outline.getElement(), "top", absY
                        - dragStartY + "px");
            } else {
                parent.setLocation(absY - dragStartY, absX - dragStartX);
            }
            parent.fireFrameMoving();
        }
    }

    public void onMouseUp(Widget sender, int x, int y) {
        System.out.println("Mouse Up" + x + " / " + y);
        if (draggable) {
            dragging = false;
            DOM.releaseCapture(caption.getElement());
            DOM.removeEventPreview(parent);
            int absX = x + parent.getLeft();
            int absY = y + parent.getTop();

            parent.setLocation(absY - dragStartY, absX - dragStartX);
            if (parent.isDragOutline()) {
                outline.setVisible(false);
            } else {
                // TODO BLOCKER
                // parent.getSelectBoxManager().setBlockerVisible(true);
                DOM.setStyleAttribute(parent.getElement(), "filter",
                        "progid:DXImageTransform.Microsoft.Alpha(opacity=100)");
                DOM.setStyleAttribute(parent.getElement(), "-mozOpacity", "1");
                DOM.setStyleAttribute(parent.getElement(), "opacity", "1");
                parent.getSelectBoxManager().onParentDragEnd(parent);
            }
            parent.fireFrameMoved();
        }
    }

    public void setCaption(String caption) {
        this.title = caption;
        this.caption.setText(caption);
    }

    public void setIconified() {
        clear();
        caption.setStyleName(parent.getTheme() + "_topBar_icon");
        setWidget(0, 0, caption);
        setWidget(0, 1, restoreButton);
    }

    public void setRestored() {
        clear();
        buildGui();
        parent.restore();
    }

    public void updateTopBar() {
        maximizeArea.setVisible(parent.isMaximizable());
        minimizeArea.setVisible(parent.isMinimizable());
        closeArea.setVisible(parent.isCloseable());

    }

    public void setCaption(HTML caption) {
        this.caption = caption;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public void setDragStartX(int dragStartX) {
        this.dragStartX = dragStartX;
    }

    public void setDragStartY(int dragStartY) {
        this.dragStartY = dragStartY;
    }

    public void setParent(DefaultGFrame parent) {
        this.parent = parent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setTheme(String theme) {
        currentTheme = theme;
        caption.setStyleName(currentTheme + "_caption");
        caption.addStyleName("topBar");
        closeArea.setStyleName(this.currentTheme + "_close");
        minimizeArea.setStyleName(this.currentTheme + "_minimize");
        maximizeArea.setStyleName(this.currentTheme + "_maximize");
        setStyleName(this.currentTheme + "_topBar");
    }

    private void setMovingGuard() {
        parent.addFrameListener(new GFrameAdapter() {

            public void frameMoved(GFrameEvent evt) {

                if (evt.getGFrame() instanceof GInternalFrame) {
                    GInternalFrame internalFrame = (GInternalFrame) evt
                            .getGFrame();
                    Widget desktopPane = (Widget) internalFrame
                            .getDesktopPane();
                    if (internalFrame.getTop() < desktopPane.getAbsoluteTop()) {
                        internalFrame.setTop(0);
                    }
                    if (internalFrame.getTop() > desktopPane.getOffsetHeight() - 40) {
                        internalFrame
                                .setTop(desktopPane.getOffsetHeight() - 100);
                    }
                    if (internalFrame.getLeft() + internalFrame.getWidth() < 0) {
                        internalFrame.setLeft(-internalFrame.getWidth() + 100);
                    }
                    if (internalFrame.getLeft() > desktopPane.getOffsetWidth()) {
                        internalFrame
                                .setLeft(desktopPane.getOffsetWidth() - 20);
                    }

                } else {
                    if (evt.getGFrame().getTop() < 0) {
                        evt.getGFrame().setTop(0);
                    }
                    if (evt.getGFrame().getTop() > Window.getClientHeight()) {
                        evt.getGFrame().setTop(Window.getClientHeight() - 20);
                    }
                    if (evt.getGFrame().getLeft() + evt.getGFrame().getWidth() < 0) {
                        evt.getGFrame().setLeft(
                                -evt.getGFrame().getWidth() + 100);
                    }
                    if (evt.getGFrame().getLeft() > Window.getClientWidth()) {
                        evt.getGFrame().setLeft(Window.getClientWidth() - 20);
                    }
                }
            }

        });
    }

    protected void initListeners() {
        caption.addMouseListener(this);
        restoreButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                setRestored();
            }
        });
    }
}
