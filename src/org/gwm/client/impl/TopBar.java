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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

public class TopBar extends FlowPanel implements ClickListener, MouseListener {

    private Label caption;

    private String currentStyle;

    private String title;

    private GwtInternalFrame parent;

    private Image imgClose;

    private Image imgMaximize;

    private Image imgMinimize;

    private int dragStartX, dragStartY;

    private boolean dragging;

    private boolean draggable;

    private DefaultGDesktopPane pane;

    public TopBar(GwtInternalFrame parent) {
        super();
        this.parent = parent;
        this.draggable = true;
        buildGui();
        sinkEvents(Event.MOUSEEVENTS);
    }

    void setDesktopPane (DefaultGDesktopPane pane) {
        this.pane = (pane);
    }

    private void buildGui() {
        this.currentStyle = parent.getTheme();
        this.title = parent.getCaption();
        addStyleName("topBar");
        caption = new Label(title);
        caption.addStyleName("caption");
        caption.addMouseListener(this);
        imgClose = new Image();
        imgClose.setUrl("themes/" + this.currentStyle + "/close.gif");
        imgClose.addStyleName("float");
        imgClose.addStyleName("button");
        imgClose.addClickListener(this);
        imgMinimize = new Image();
        imgMinimize.setUrl("themes/" + this.currentStyle + "/minimize.gif");
        imgMinimize.addStyleName("float");
        imgMinimize.addStyleName("button");
        imgMinimize.addClickListener(this);
        imgMaximize = new Image();
        imgMaximize.setUrl("themes/" + this.currentStyle + "/maximize.gif");
        imgMaximize.addStyleName("float");
        imgMaximize.addStyleName("button");
        imgMaximize.addClickListener(this);
        add(caption);
        add(imgMinimize);
        add(imgMaximize);
        add(imgClose);
    }

    public void onClick(Widget w) {
        if (w.equals(imgClose)) {
            parent.close();
        }
        if (w.equals(imgMaximize)) {
            if (parent.isMaximized()) {
                parent.restore();
            } else {
                parent.maximize();
            }
        }
        if (w.equals(imgMinimize)) {
            if (parent.isMinimized()) {
                parent.restore();
            } else {
                parent.minimize();
            }
        }
    }

    public void onBrowserEvent(Event e) {
        if (draggable) {
            int type = DOM.eventGetType(e);
            if (type != Event.ONMOUSEMOVE
                    || (type == Event.ONMOUSEMOVE && dragging)) {
                Element em = caption.getElement();
                int x = DOM.eventGetClientX(e) - DOM.getAbsoluteLeft(em);
                int y = DOM.eventGetClientY(e) - DOM.getAbsoluteTop(em);

                if (type == Event.ONMOUSEMOVE) {
                    onMouseMove(this, x, y);
                    // super.onBrowserEvent(e);
                    return;
                }
                if (type == Event.ONMOUSEDOWN) {
                    onMouseDown(this, x, y);
                    // super.onBrowserEvent(e);
                    return;
                }
                if (type == Event.ONMOUSEUP) {
                    onMouseUp(this, x, y);
                    // super.onBrowserEvent(e);
                    return;
                }
            }
        }
    }

    public void onMouseDown(Widget sender, int x, int y) {
        if (draggable) {
            dragging = true;
            DOM.setCapture(sender.getElement());
            dragStartX = x;
            dragStartY = y;
        }
    }

    public void onMouseEnter(Widget sender) {
    }

    public void onMouseLeave(Widget sender) {
    }

    public void onMouseMove(Widget sender, int x, int y) {
        if (draggable && dragging) {
            int absX = x + sender.getAbsoluteLeft();
            int absY = y + sender.getAbsoluteTop();
            int newLeft = absX - dragStartX;
            int newTop = absY - dragStartY;
            int pl = pane.getAbsoluteLeft();
            int pt = pane.getAbsoluteTop();
            int nr = newLeft + parent.getOffsetWidth();
            int nb = newTop + parent.getOffsetHeight();
            int pr = pl + pane.getOffsetWidth();
            int pb = pt + pane.getOffsetHeight();
            if ((newLeft > pl) && (newTop > pt) && (nr < pr) && (nb < pb)) {
                parent.setPopupPosition(newLeft, newTop);
            }
        }
    }

    public void onMouseUp(Widget sender, int x, int y) {
        dragging = false;
        DOM.releaseCapture(sender.getElement());
    }

    public void setCaption(String caption) {
        this.caption.setText(caption);
    }

    public void updateTopBar() {
        imgMaximize.setVisible(parent.isMaximizable());
        imgMinimize.setVisible(parent.isMinimizable());
        imgClose.setVisible(parent.isCloseable());

    }

    public void setCaption(Label caption) {
        this.caption = caption;
    }

    public void setCurrentStyle(String currentStyle) {
        this.currentStyle = currentStyle;
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

    public void setImgClose(Image imgClose) {
        this.imgClose = imgClose;
    }

    public void setImgMaximize(Image imgMaximize) {
        this.imgMaximize = imgMaximize;
    }

    public void setImgMinimize(Image imgMinimize) {
        this.imgMinimize = imgMinimize;
    }

    public void setParent(GwtInternalFrame parent) {
        this.parent = parent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDraggable() {
        return draggable;
    }

}