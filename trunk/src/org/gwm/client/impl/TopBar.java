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

package org.gwm.client.impl;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

public class TopBar extends FlexTable implements ClickListener, MouseListener {

    private Label caption;

    private String currentTheme;

    private String title;

    private DefaultGFrame parent;

    private Label closeArea;

    private Label maximizeArea;

    private Label minimizeArea;

    private int dragStartX, dragStartY;

    private boolean dragging;

    private boolean draggable;

    private boolean moving;


    TopBar(DefaultGFrame parent) {
        super();
        this.parent = parent;
        this.draggable = true;
        buildGui();
        sinkEvents(Event.MOUSEEVENTS);
        
    }


    private void buildGui() {
        this.currentTheme = parent.getTheme();
        this.title = parent.getCaption();
        caption = new Label(title);
        caption.addMouseListener(this);
        closeArea = new Label();
        closeArea.addClickListener(this);
        minimizeArea = new Label();
        minimizeArea.addClickListener(this);
        maximizeArea = new Label();
        maximizeArea.addClickListener(this);
        setWidget(0,0,caption);
        getCellFormatter().setWidth(0,0, "100%");
        setWidget(0,1,minimizeArea);
        setWidget(0,2,maximizeArea);
        setWidget(0,3,closeArea);
        setTheme(currentTheme);
        setCellPadding(0);
        setCellSpacing(0);
        setStyleName("topBar");
    }

    public void onClick(Widget w) {
        if (w.equals(closeArea)) {
            parent.close();
        }
        if (w.equals(maximizeArea)) {
            if (parent.isMaximized()) {
                parent.restore();
            } else {
                parent.maximize();
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

    public void onBrowserEvent(Event e) {
        if (draggable) {
            int type = DOM.eventGetType(e);
            if (type != Event.ONMOUSEMOVE
                    || (type == Event.ONMOUSEMOVE && dragging)) {
                int x = DOM.eventGetClientX(e);
               int y = DOM.eventGetClientY(e);

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
        if (draggable && this == sender) {
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
            int newLeft = x - dragStartX + parent.getLeft();
            int newTop = y - dragStartY + parent.getTop();
            dragStartX =x;
            dragStartY =y;
            moving = true;
            parent.setLocation(newTop, newLeft);
        }
    }

    public void onMouseUp(Widget sender, int x, int y) {
        if (dragging) {
            dragging = false;
            if (moving) {
                moving = false;
                parent.fireFrameMoved();
            }
        }
        DOM.releaseCapture(sender.getElement());
    }

    public void setCaption(String caption) {
        this.title = caption;
        this.caption.setText(caption);
    }

    public void setIconified () {
        clear();
        Label l = new Label (title);
        l.addStyleName(parent.getTheme()+"_topBar_icon");
        Label restoreButton = new Label("");
        restoreButton.setStyleName(this.currentTheme + "_topBar_restore");
        restoreButton.addClickListener (new ClickListener() {
          public void onClick (Widget sender) {
            setRestored();
          }
        });
        setWidget (0, 0, l);
        setWidget (0, 1, restoreButton);
    }

    public void setRestored () {
        clear();
        buildGui();
        parent.restore();
    }

    public void updateTopBar() {
        maximizeArea.setVisible(parent.isMaximizable());
        minimizeArea.setVisible(parent.isMinimizable());
        closeArea.setVisible(parent.isCloseable());

    }

    public void setCaption(Label caption) {
        this.caption = caption;
    }


    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public void setDragging(boolean dragging) {
System.out.println ("set dragging to "+dragging);
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
        caption.setStyleName(currentTheme+ "_caption");
        closeArea.setStyleName(this.currentTheme + "_close");
        minimizeArea.setStyleName(this.currentTheme + "_minimize");
        maximizeArea.setStyleName(this.currentTheme + "_maximize");
        setStyleName(this.currentTheme + "_topBar");
    }

}
