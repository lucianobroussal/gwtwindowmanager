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
import org.gwm.client.GInternalFrameListener;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-based implementation of <code>GInternalFrame</code>
 * 
 * @author Johan Vos
 */
public class GwtInternalFrame extends PopupPanel implements GInternalFrame,
        EventListener, ClickListener {

    private String id;

    private String title;

    private TopBar topBar;

    private ResizeImage resizeImage;

    private Label caption;

    private FocusPanel mainPanel;

    private Widget myContent;

    private int dragStartX, dragStartY;

    private int resizeStartX, resizeStartY;

    private boolean dragging;
    
    private boolean visible;

    private static final int DEFAULT_WIDTH = 200;

    private static final int DEFAULT_HEIGHT = 300;

    private static final String DEFAULT_STYLE = "theme1";

    private int maxWidth, maxHeight;

    private int minWidth, minHeight;

    private int width = -1;

    private int height = -1;

    private String currentStyle;

    private boolean closable, maximizable, minimizable, draggable, resizable;

    private boolean maximized, minimized;

    private boolean resizing;

    Label imgTopLeft;

    Label imgTopRight;

    Label imgBotLeft;

    Label imgBotRight;

    private int previousWidth, restoreWidth;

    private int previousHeight, restoreHeight;

    private int previousTop, previousLeft;

    private FlexTable panel = new FlexTable();

    private GDesktopPane desktopPane;
    
    List listeners;

    public GwtInternalFrame(String id , String title) {
        this.currentStyle = DEFAULT_STYLE;
        this.title = title;
        this.myContent = new HTML("MY CONTENT");
        this.closable = true;
        this.minimizable = true;
        this.maximizable = true;
        this.resizable = true;
        initializeListeners();
        buildGui();
        sinkEvents(Event.ONCLICK | Event.MOUSEEVENTS);
    }

    private void initializeListeners() {
        listeners = new ArrayList();
        resizeImage = new ResizeImage(this);
        this.caption = new Label(title);
        this.caption.addStyleName(currentStyle + "_title");
        this.caption
                .setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        topBar = new TopBar(this);
        this.mainPanel = new FocusPanel();
        this.mainPanel.setWidget(myContent);
        this.mainPanel.addClickListener(this);
        imgTopLeft = new Label();
        imgTopLeft.addStyleName(this.currentStyle + "_nw");
        imgTopRight = new Label();
        imgTopRight.addStyleName(this.currentStyle + "_ne");
        imgBotLeft = new Label();
        imgBotLeft.addStyleName(this.currentStyle + "_sw");
        imgBotRight = new Label();
        imgBotRight.addStyleName(this.currentStyle + "_se");
        this.maxWidth = Window.getClientWidth();
        this.maxHeight = Window.getClientHeight();
        this.minWidth = 240;
        this.minHeight = 40;
    }

    void buildGui() {
        this.panel = new FlexTable();
        if (minimized) {
            showIcon();
            return;
        }
        if (this.width < 0) {
            this.width = DEFAULT_WIDTH;
        }
        if (this.height < 0) {
            this.height = DEFAULT_HEIGHT;
        }
        this.panel.setSize(this.width + "px", this.height + "px");
        panel.setWidget(0, 0, imgTopLeft);
        panel.setWidget(0, 1, topBar);
        panel.getCellFormatter().setStyleName(0, 1, currentStyle + "_n");
        panel.setWidget(0, 2, imgTopRight);
        panel.setWidget(2, 0, imgBotLeft);
        panel.setWidget(2, 2, imgBotRight);
        panel.setWidget(1, 1, myContent);
        panel.getCellFormatter().setStyleName(1, 1, currentStyle + "_content");
        panel.setHTML(1, 0, "&nbsp;");
        panel.getCellFormatter().setStyleName(1, 0, currentStyle + "_w");
        panel.setHTML(1, 2, "&nbsp;");
        panel.getCellFormatter().setStyleName(1, 2, currentStyle + "_e");

        panel.setHTML(2, 0, "&nbsp;");
        panel.getCellFormatter().setStyleName(2, 0, currentStyle + "_sw");

        panel.setHTML(2, 1, "&nbsp;");
        panel.getCellFormatter().setStyleName(2, 1, currentStyle + "_s");

        if (resizable) {
            panel.setWidget(2, 2, resizeImage);
        } else {
            panel.getCellFormatter().setStyleName(2, 2, currentStyle + "_se");
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

    private void showIcon() {
        Label l = new Label(title);
        Label i = new Label("");
        l.addMouseListener(topBar);
        i.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                GwtInternalFrame.this.minimized = false;
                buildGui();
            }
        });
        i.addStyleName("icon_button");
        l.addStyleName("icon_title");
        panel.setWidget(0, 0, i);
        panel.setWidget(0, 1, l);
        panel.setBorderWidth(0);
        panel.setCellPadding(0);
        panel.setCellSpacing(0);
        super.setWidget(panel);
    }

    public Object getId() {
        return this.id;
    }

    public void setStyle(String v) {
        this.currentStyle = v;
        buildGui();
    }

    public String getStyle() {
        return this.currentStyle;
    }

    public void show(boolean modal) {
        super.show();
    }

    public void showCenter(boolean modal) {
        super.show();
    }

    public void setContent(Widget widget) {
        myContent = widget;
        buildGui();
    }

    public void setContent(String content) {
        myContent = new HTML(content);
        buildGui();
    }

    public void minimize() {
        desktopPane.getDesktopManager().iconify(this);
        this.minimized = !this.minimized;
        //buildGui();
        fireFrameIconified();
    }

    public void maximize() {
//        if (!this.maximized) {
//            this.previousTop = getPopupTop();
//            this.previousLeft = getPopupLeft();
//            this.setPopupPosition(0, 0);
//            this.previousWidth = getWidth();
//            this.previousHeight = getHeight();
//            this.width = maxWidth;
//            this.height = maxHeight;
//            this.maximized = true;
//            buildGui();
//        } else {
//            this.width = previousWidth;
//            this.height = previousHeight;
//            this.maximized =  false;
//            buildGui();
//            setLocation(this.previousTop, this.previousLeft);
//        }
        getParentDesktop().getDesktopManager().maximize(this);
        fireFrameMaximized();
    }

    public void toFront() {
    }

    public void dispose() {
    }

    public boolean isMinimized() {
        return false;
    }

    public boolean isMaximized() {
        return this.maximized;
    }

    public void setOperationOnClose() {
    }

    public void setLocation(int top, int left) {
        setPopupPosition(top, left);
    }

    public void setSize(int width, int height) {
        panel.setSize(width + "px", height + "px");
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

    public void updateWidth() {
    }

    public void updateHeight() {
    }

    public void setTitle(String title) {
        this.title = title;
        this.caption.setText(title);
    }

    public String getTitle() {
        return this.title;
    }

    public String getCaption() {
        return this.title;
    }

    public void setUrl(String url) {
        myContent = new HTML("<iframe src=\"" + url + "\" height=\""
                + DEFAULT_HEIGHT + "\" width=\"" + DEFAULT_WIDTH
                + "\" frameborder=\"0\"/>");
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
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public void onBrowserEvent(Event event) {
        Element target = DOM.eventGetTarget(event);
        int type = DOM.eventGetType(event);
        if (type == Event.ONCLICK) {
            Element myself = DOM.getParent(myContent.getElement());
            if (myself.equals(target)) {
                this.hide();
                this.show();
            }
            return;
        }
    }

    public void onClick(Widget sender) {
        this.hide();
        this.show();
    }

    public boolean onEventPreview(Event evt) {
        super.onEventPreview(evt);
        return true;
    }

    public void addGFrameListener(GInternalFrameListener listener){
        listeners.add(listener);
    }
    
    public void removeGFrameListener(GInternalFrameListener listener){
        listeners.remove(listener);
    }
    

    public void setIconified(boolean v) {
        if(isMaximizable()){
            if(v){
                if(minimized)
                    return;
                desktopPane.getDesktopManager().iconify(this);
                
            }else{
                if(!minimized)
                    return;
                desktopPane.getDesktopManager().deiconifyFrame(this);
            }
        minimized = v;
        }
        
    }

    public void setMaximized(boolean v) {
    }

    // """"""""""""""
    
    /**
     * Fires the event of the activation of this frame to its listeners.
     */
    private void fireFrameActivated() {
        for(int i=0; i < listeners.size(); i++){
            GInternalFrameListener listener = (GInternalFrameListener) listeners.get(i);
            listener.frameActivated(new GInternalFrameEvent(this));
        }
    }
    
    /**
     * Fires the event of the resizing of this frame to its listeners.
     */
    private void fireFrameResized(){
        for(int i=0; i < listeners.size(); i++){
            GInternalFrameListener listener = (GInternalFrameListener) listeners.get(i);
            listener.frameResized(new GInternalFrameEvent(this));
        }
    }
    
    
    
    /**
     * Fires the closed event of this frame to its listeners.
     */
    private void fireFrameClosed(){
        for(int i=0; i < listeners.size(); i++){
            GInternalFrameListener listener = (GInternalFrameListener) listeners.get(i);
            listener.frameClosed(new GInternalFrameEvent(this));
        }
    }
    
    
    
    /**
     * Fires the frameMaximized event of this frame to its listeners.
     */
    public void fireFrameMaximized(){
        for(int i=0; i < listeners.size(); i++){
            GInternalFrameListener listener = (GInternalFrameListener) listeners.get(i);
            listener.frameMaximized(new GInternalFrameEvent(this));
        }
    }
    
    
    /**
     * Fires the frameMinimized event of this frame to its listeners.
     */
    public void fireFrameIconified(){
        for(int i=0; i < listeners.size(); i++){
            GInternalFrameListener listener = (GInternalFrameListener) listeners.get(i);
            listener.frameIconified(new GInternalFrameEvent(this));
        }
    }
    
    /**
     * Fires the frameMoved event of this frame to its listeners.
     */
    private void fireFrameMoved(){
        for(int i=0; i < listeners.size(); i++){
            GInternalFrameListener listener = (GInternalFrameListener) listeners.get(i);
            listener.frameIconified(new GInternalFrameEvent(this));
        } 
    }
    
    // """""""""""""""""""""
    
    public boolean isMaximizable() {
        return false;
    }

    public boolean isMinimizable() {
        return false;
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
        return null;
    }

    // public void setContent (Widget c) {
    // }

    public void addInternalFrameListener(GInternalFrameListener l) {
    }

    public void removeInternalFrameListener(GInternalFrameListener l) {
    }

    public boolean isModal() {
        return false;
    }

    public void setModal(boolean v) {
    }

    public GDesktopPane getParentDesktop() {
        return desktopPane;
    }

    public void setClosed(boolean flag) {
        // TODO Auto-generated method stub
        
    }

    

    public void setMaximum(boolean flag) {
        // TODO Auto-generated method stub
        
    }

    public void setMinimum(boolean flag) {
        // TODO Auto-generated method stub
        
    }

    public void setOperationOnClose(int operation) {
        // TODO Auto-generated method stub
        
    }

    public void setTheme(String theme) {
        setStyle(theme);
        
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if(visible){
            if(isVisible()){
                return;
            }
            show();
        }else{
            if(!isVisible()){
                return;
            }
            hide();
        }
    }

    

}
