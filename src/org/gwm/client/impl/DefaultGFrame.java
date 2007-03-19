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

import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GFrame;
import org.gwm.client.event.GFrameEvent;
import org.gwm.client.event.GFrameListener;
import org.gwm.client.util.GwmUtilities;
import org.gwm.client.util.widget.OverlayLayer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
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
import com.gwt.components.client.Effects;
import com.gwt.components.client.Effects.Effect;

/**
 * GWT-based implementation of <code>GFrame</code>
 */
public class DefaultGFrame extends SimplePanel implements GFrame, EventPreview {

    
    protected static GFrame topFrame;

    protected SelectBoxManagerImpl selectBoxManager;

    protected static int layerOfTheTopWindow;

    private String title;

    private TopBar topBar;

    private ResizeImage resizeImage;

    private Widget myContent;

    private String url;

    private Widget savedContentDuringDraging;

    private boolean visible;

    private static final int DEFAULT_WIDTH = 200;

    private static final int DEFAULT_HEIGHT = 40;

    private static final String DEFAULT_STYLE = "theme1";

    private static final String DEFAULT_TITLE = "GFrame";

    private int maxWidth, maxHeight;

    private int minWidth, minHeight;

    private int width = -1;

    private int height = -1;

    private String currentTheme;

    private boolean closable, maximizable, minimizable, resizable;

    protected boolean maximized, minimized;

    private Label imgTopLeft;

    private Label imgTopRight;

    private Label imgBotLeft;

    private int previousWidth;

    private int previousHeight;

    private int previousTop, previousLeft;

    private List listeners;

    protected int left;

    protected int top;

    private Frame frame;

    private FlexTable ui;

    private FlexTable topRow;

    private FlexTable centerRow;

    private FlexTable bottomRow;

    private boolean freeminimized;

    private boolean modalMode;

    private boolean outlineDragMode;

    private static OverlayLayer overlayLayer;

    private boolean closed = false;

    private SimplePanel panelContent;

    public DefaultGFrame() {
        this(DEFAULT_TITLE);
    }

    public DefaultGFrame(String caption) {
        selectBoxManager = (SelectBoxManagerImpl) GWT
                .create(SelectBoxManagerImpl.class);
        this.currentTheme = DEFAULT_STYLE;
        this.title = caption;
        this.myContent = new HTML("");
        this.closable = true;
        this.minimizable = true;
        this.maximizable = true;
        this.resizable = true;
        initializeFrame();
        buildGui();
        sinkEvents(Event.ONCLICK | Event.MOUSEEVENTS);
        super.setVisible(false);
        RootPanel.get().add(this);
        addFrameListener(selectBoxManager.getFrameListener());
    }

    private void initializeFrame() {
        ui = new FlexTable();
        topRow = new FlexTable();
        centerRow = new FlexTable();
        bottomRow = new FlexTable();
        listeners = new ArrayList();
        resizeImage = new ResizeImage(this);
        // topBar = new TopBarFF();

        topBar = (TopBar) GWT.create(TopBarFF.class);
        topBar.init(this);
        imgTopLeft = new Label();
        imgTopRight = new Label();
        imgBotLeft = new Label();
        // TODO BLOCKER
        this.maxWidth = Window.getClientWidth();
        this.maxHeight = Window.getClientHeight();
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        DOM.setStyleAttribute(getElement(), "position", "absolute");

    }

    private void buildGui() {
        this.ui = new FlexTable();
        if (freeminimized) {
            this.ui.setWidget(0, 0, topBar);
            super.setWidget(ui);
            return;
        }
        if (this.width < this.minWidth) {
            this.width = this.minWidth;
        }
        if (this.height < this.minHeight) {
            this.height = this.minHeight;
        }
        // this.ui.setSize(this.width + "px", this.height + "px");
        setSize(this.width, this.height);
        topRow.setWidget(0, 0, imgTopLeft);
        topRow.setWidget(0, 1, topBar);
        topRow.setWidget(0, 2, imgTopRight);
        bottomRow.setWidget(0, 0, imgBotLeft);
        bottomRow.setHTML(0, 1, "&nbsp;");
        bottomRow.setHTML(0, 2, "&nbsp;");
        if (url != null) {
            setUrl(url);
        }
        centerRow.setHTML(0, 0, "&nbsp;");
        panelContent = new SimplePanel();
        panelContent.setWidget(myContent);
        centerRow.setWidget(0, 1, panelContent);
        centerRow.getFlexCellFormatter().setHorizontalAlignment(0, 1,
                HasHorizontalAlignment.ALIGN_CENTER);
        centerRow.setHTML(0, 2, "&nbsp;");

        setResizable(resizable);

        ui.getCellFormatter().setHeight(1, 0, "100%");
        ui.getCellFormatter().setWidth(1, 0, "100%");
        ui.getCellFormatter().setAlignment(1, 0,
                HasHorizontalAlignment.ALIGN_CENTER,
                HasVerticalAlignment.ALIGN_MIDDLE);
        //ui.setBorderWidth(0);
        ui.setCellPadding(0);
        ui.setCellSpacing(0);
        ui.setWidget(0, 0, topRow);
        ui.setWidget(1, 0, centerRow);
        ui.setWidget(2, 0, bottomRow);
        super.setWidget(selectBoxManager.getFrameFinalUI(ui));
        setTheme(currentTheme);

        topRow.setCellPadding(0);
        topRow.setCellSpacing(0);
        topRow.setHeight("100%");
        topRow.getCellFormatter().setWidth(0, 1, "100%");
        centerRow.setCellPadding(0);
        centerRow.setCellSpacing(0);
        centerRow.setWidth("100%");
        centerRow.setHeight("100%");
        centerRow.getCellFormatter().setWidth(0, 1, "100%");
        centerRow.setBorderWidth(0);

        bottomRow.setCellPadding(0);
        bottomRow.setCellSpacing(0);
        bottomRow.setWidth("100%");
        bottomRow.getCellFormatter().setWidth(0, 1, "100%");

    }

    public void setTheme(String theme) {
        this.currentTheme = theme;
        applyTheme();
    }

    private void applyTheme() {
        topBar.setTheme(currentTheme);
        resizeImage.setTheme(currentTheme);
        imgTopLeft.setStyleName(this.currentTheme + "_nw");
        imgTopRight.setStyleName(this.currentTheme + "_ne");
        imgBotLeft.setStyleName(this.currentTheme + "_sw");
        // bottomRow.getCellFormatter().setStyleName(0,0, currentTheme + "_sw");
        bottomRow.getCellFormatter().setStyleName(0, 1, currentTheme + "_s");
        topRow.getCellFormatter().setStyleName(0, 1, currentTheme + "_n");
        centerRow.getCellFormatter().setStyleName(0, 1,
                currentTheme + "_content");
        panelContent.setStyleName(currentTheme + "_content");
        centerRow.getCellFormatter().setStyleName(0, 0, currentTheme + "_w");
        centerRow.getCellFormatter().setStyleName(0, 2, currentTheme + "_e");
        topRow.getCellFormatter().setVerticalAlignment(0, 0,
                HasVerticalAlignment.ALIGN_BOTTOM);
        topRow.getCellFormatter().setVerticalAlignment(0, 2,
                HasVerticalAlignment.ALIGN_BOTTOM);

         bottomRow.getCellFormatter().setVerticalAlignment(0, 0,
         HasVerticalAlignment.ALIGN_TOP);
         bottomRow.getCellFormatter().setVerticalAlignment(0, 2,
         HasVerticalAlignment.ALIGN_TOP);
        if (resizable) {
            resizeImage.setTheme(currentTheme);
            bottomRow.setWidget(0, 2, resizeImage);
        } else {
            bottomRow.getCellFormatter().setStyleName(0, 2,
                    currentTheme + "_se");
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
        this.previousTop = getAbsoluteTop();
        this.previousLeft = getAbsoluteLeft();
        this.previousWidth = getWidth();
        this.previousHeight = getHeight();
        topBar.setIconified();
        //selectBoxManager.setBlockerVisible(false);
        this.freeminimized = true;
        buildGui();
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
        this.freeminimized = false;
        setLocation(this.previousTop, this.previousLeft);
        if (!getContent().isVisible()) {
            getContent().setVisible(true);

        }
        selectBoxManager.setBlockerVisible(true);
        buildGui();
        fireFrameRestored();
    }

    public void close() {
        fireFrameClosed();
        // setVisible(false);
        // TODO BLOCKER
        selectBoxManager.setBlockerVisible(false);
        Effects.Effect("Puff", this, "{duration : 0.6}").addEffectListener(
                new Effects.EffectListener() {

                    public void onAfterFinish(Effect sender) {
                        removeFromParent();
                        selectBoxManager.removeBlocker();
                        closed = true;
                    }

                    public void onAfterUpdate(Effect sender) {
                    }

                    public void onBeforeUpdate(Effect sender) {
                    }

                });
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

    public void setLocation(int top, int left) {

        Element elem = getElement();
        DOM.setStyleAttribute(elem, "left", left + "px");
        DOM.setStyleAttribute(elem, "top", top + "px");

        //selectBoxManager.setLocation(top, left);

        this.left = left;
        this.top = top;

    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        ui.setSize(width + "", height + "");
        ////selectBoxManager.setBlockerSize(width, height);

    }

    public void setWidth(int width) {
        setSize(width, height);
    }

    public int getWidth() {
        if (getOffsetWidth() > 0) {
            return getOffsetWidth();
        }
        try {
            String widthStr = DOM.getStyleAttribute(ui.getElement(), "width");
            // String widthStr = getOffsetWidth();
            widthStr = widthStr.replaceAll("px", "");
            int width = Integer.parseInt(widthStr);
            return width;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        // return getOffsetWidth();
    }

    public void setHeight(int height) {
        setSize(width, height);
    }

    public int getHeight() {
        if (getOffsetHeight() > 0)
            return getOffsetHeight();
        try {
            String heightStr = DOM.getStyleAttribute(ui.getElement(), "height");
            heightStr = heightStr.replaceAll("px", "");
            int height = Integer.parseInt(heightStr);
            return height;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        // return getOffsetHeight();

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
        return this.title;
    }

    public void setUrl(String url) {
        this.url = url;
        myContent = getFrame();
        ((Frame) myContent).setUrl(url);
        DOM.setAttribute(myContent.getElement(), "overflow", "hidden");
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
            // bottomRow.setWidget(0, 2, resizeImage);
            resizeImage.setTheme(currentTheme);
        } else {
            // bottomRow.setHTML(0, 2, "&nbsp;");
            // bottomRow.getCellFormatter().setStyleName(0, 2,
            // currentTheme + "_se");
        }
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
        topBar.updateTopBar();
    }

    public void setMinimizable(boolean minimizable) {
        this.minimizable = minimizable;
        topBar.updateTopBar();
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
                _show();
            }
        }
    }

    public void setMaximized(boolean v) {
    }

    /**
     * Fires the event of the resizing of this frame to its listeners.
     */
    public void fireFrameResized() {
        for (int i = 0; i < listeners.size(); i++) {
            GFrameListener listener = (GFrameListener) listeners.get(i);
            listener.frameResized(new GFrameEvent(this));
        }
    }

    /**
     * Fires the event of the moving of this frame to its listeners.
     */
    void fireFrameMoved() {
        for (int i = 0; i < listeners.size(); i++) {
            GFrameListener listener = (GFrameListener) listeners.get(i);
            listener.frameMoved(new GFrameEvent(this));
        }
    }

    /**
     * Fires the closed event of this frame to its listeners.
     */
    private void fireFrameClosed() {
        for (int i = 0; i < listeners.size(); i++) {
            GFrameListener listener = (GFrameListener) listeners.get(i);
            listener.frameClosed(new GFrameEvent(this));
        }
    }

    /**
     * Fires the frameMaximized event of this frame to its listeners.
     */
    protected void fireFrameOpened() {
        for (int i = 0; i < listeners.size(); i++) {
            GFrameListener listener = (GFrameListener) listeners.get(i);
            listener.frameOpened(new GFrameEvent(this));
        }
    }

    /**
     * Fires the frameMaximized event of this frame to its listeners.
     */
    public void fireFrameMaximized() {
        for (int i = 0; i < listeners.size(); i++) {
            GFrameListener listener = (GFrameListener) listeners.get(i);
            listener.frameMaximized(new GFrameEvent(this));
        }
    }

    /**
     * Fires the frameMinimized event of this frame to its listeners.
     */
    public void fireFrameMinimized() {
        for (int i = 0; i < listeners.size(); i++) {
            GFrameListener listener = (GFrameListener) listeners.get(i);
            listener.frameMinimized(new GFrameEvent(this));
        }
    }

    /**
     * Fires the frameMinimized event of this frame to its listeners.
     */
    public void fireFrameIconified() {
        for (int i = 0; i < listeners.size(); i++) {
            GFrameListener listener = (GFrameListener) listeners.get(i);
            listener.frameIconified(new GFrameEvent(this));
        }
    }

    /**
     * Fires the frameRestored event of this frame to its listeners.
     */
    public void fireFrameRestored() {
        for (int i = 0; i < listeners.size(); i++) {
            GFrameListener listener = (GFrameListener) listeners.get(i);
            listener.frameRestored(new GFrameEvent(this));
        }
    }

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
        return this.maxWidth;
    }

    public int getMaximumHeight() {
        return this.maxHeight;
    }

    public Widget getContent() {
        return myContent;
    }

    public void addFrameListener(GFrameListener l) {
        listeners.add(l);
    }

    public void removeGFrameListener(GFrameListener l) {
        listeners.remove(l);
    }

    public boolean isVisible() {
        return visible;
    }

    public void showModal() {
        if (overlayLayer == null) {
            overlayLayer = new OverlayLayer();
        }
        overlayLayer.show(currentTheme);
        setMaximizable(false);
        setMinimizable(false);
        setResizable(false);
        GwmUtilities.diplayAtScreenCenter(this);
        modalMode = true;
    }

    public void setVisible(boolean visible) {

        // buildUI();
        // if(true){
        // return;
        // }
        //        
        if (closed) {
            throw new IllegalStateException(
                    "This is window has been closed. You can work anymore with a closed window. the garbage collector as released the allocated resources.");
        }
        if (this.visible == visible)
            return;
        if (visible) {
            this.minimized = false;
            // super.setVisible(true);
            Effects.Effect("BlindDown", this, "{duration:0.5}")
                    .addEffectListener(new Effects.EffectListenerAdapter() {
                        public void onAfterFinish(Effect sender) {
                            setSize(getOffsetWidth(), getOffsetHeight());
                        }
                    });
           /// selectBoxManager.setBlockerVisible(true);
            _show();
            // DOM.addEventPreview(this);
        } else {
            super.setVisible(false);
            // Effects.Effect("SwitchOff" , this);
          ///selectBoxManager.setBlockerVisible(false);
            // DOM.removeEventPreview(this);
            if (modalMode) {
                modalMode = false;
                overlayLayer.hide();
            }
        }
        this.visible = visible;
    }

    public boolean isCloseable() {
        return closable;
    }

    public void startResizing() {
        if (url != null) {
            savedContentDuringDraging = this.myContent;
            this.myContent = new Label("");
            centerRow.setWidget(0, 1, myContent);
        }
        // this.url = null;
    }

    public void stopResizing() {
        if (url != null) {
            this.myContent = savedContentDuringDraging;
            centerRow.setWidget(0, 1, myContent);
            buildGui();
        }
        fireFrameResized();
    }

    // public void _show() {
    // if (desktopPane == null) {
    // DOM.setStyleAttribute(getElement(), "position", "absolute");
    // RootPanel.get().add(this);
    // }
    // super.setVisible(true);
    // DOM.setIntStyleAttribute(getElement(), "zIndex", ++layerOfTheTopWindow);
    // topFrame = this;
    // fireFrameOpened();
    // }

    protected void _show() {

        selectBoxManager.setBlockerDeepLayer(++layerOfTheTopWindow);

        DOM.setIntStyleAttribute(ui.getElement(), "zIndex", ++layerOfTheTopWindow);
        topFrame = this;
        fireFrameOpened();

    }

    public static int getLayerOfTheTopWindow() {
        return layerOfTheTopWindow;
    }

    public String toString() {
        return this.title;
    }

    public boolean onEventPreview(Event event) {
        int type = DOM.eventGetType(event);
        switch (type) {

        case Event.ONMOUSEDOWN:
        case Event.ONMOUSEUP:
        case Event.ONMOUSEMOVE:
        case Event.ONCLICK:
        case Event.ONDBLCLICK: {
            if (DOM.getCaptureElement() == null) {
                Element target = DOM.eventGetTarget(event);
                if (!DOM.isOrHasChild(getElement(), target)) {
                    return false;
                }
            }
            break;
        }
        }
        return true;
    }

    public SelectBoxManagerImpl getSelectBoxManager() {
        return selectBoxManager;
    }

    public boolean isDragOutline() {
        return outlineDragMode;
    }

    public void setOutlineDragMode(boolean outline) {
        this.outlineDragMode = outline;
    }
	public void fireFrameMoving() {
		for(int i = 0;i < listeners.size(); i++ ){
			GFrameListener frameListener = (GFrameListener) listeners.get(i);
			frameListener.frameMoving(new GFrameEvent(this));
		}
		
	}
    // private void buildUI() {
    // String html = "<table class='top table_window'>"
    // + "<tbody>"
    // + "<tr>"
    // + "<td class='alphacube_nw'></td>"
    // + "<td class='alphacube_n'>"
    // + "<div class='alphacube_title alphacube_window'>Sample window</div>"
    // + "</td>"
    // + "<td class='alphacube_ne'></td>"
    // + "</tr>"
    // + "</tbody>" + "</table>";
    // html+= "<table class='top table_window'>"
    // + "<tbody>"
    // + "<tr>"
    // + "<td class='alphacube_w'></td>"
    // + "<td class='alphacube_content'>"
    // + "<div class='alphacube_content'>Content</div>"
    // + "</td>"
    // + "<td class='alphacube_e'></td>"
    // + "</tr>"
    // + "</tbody>" + "</table>";
    //        
    // html+="<table class='top table_window'>"
    // + "<tbody>"
    // + "<tr>"
    // + "<td class='alphacube_sw'></td>"
    // +"<td class='alphacube_s'>"
    // +"<div class='status_bar'>"
    // + "<span style='float: left; width: 1px; height: 1px;'></span>"
    // +"</div>"
    // +"</td>"
    // +"<td class='alphacube_sizer' ></td>"
    // + "</tr>"
    // + "</tbody>" + "</table>";
    // SimplePanel p = new SimplePanel();
    // p.setStyleName("dialog");
    // DOM.setInnerHTML(p.getElement(), html);
    // DOM.setStyleAttribute(p.getElement(), "position", "absolute");
    // DOM.setStyleAttribute(p.getElement(), "top", layerOfTheTopWindow * 100 +
    // "px");
    // DOM.setStyleAttribute(p.getElement(), "left", layerOfTheTopWindow * 100 +
    // "px");
    // DOM.setIntStyleAttribute(p.getElement(), "zIndex",
    // ++layerOfTheTopWindow);
    // RootPanel.get().add(p);
    // // RootPanel.get().add(new HTML(html));
    // }

}
