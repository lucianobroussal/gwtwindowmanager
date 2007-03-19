package org.gwm.client.impl;

import org.gwm.client.util.widget.OverlayLayer;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public class TopBarFF extends TopBar implements EventPreview {

    private static OverlayLayer overlayLayer = new OverlayLayer();

    TopBarFF() {
        super();
//        Window.alert("FF-topbar");
        sinkEvents(Event.MOUSEEVENTS);
    }

    public void onMouseDown(int x, int y) {
        DOM.addEventPreview(this);
        if (draggable) {
            // overlayLayer.show("alphacube");
            dragging = true;
            // DOM.addEventPreview(parent);
            // DOM.setCapture(caption.getElement());
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
                        DefaultGFrame.getLayerOfTheTopWindow() + 50);
            } else {
                DOM.setStyleAttribute(parent.getElement(), "filter",
                        "progid:DXImageTransform.Microsoft.Alpha(opacity=100)");
                DOM
                        .setStyleAttribute(parent.getElement(), "-mozOpacity",
                                "0.9");
                DOM.setStyleAttribute(parent.getElement(), "opacity", "1");
                parent.getSelectBoxManager().onParentDragStart(parent);
                DOM.setIntStyleAttribute(parent.getElement(), "zIndex",
                        DefaultGFrame.getLayerOfTheTopWindow() + 50);
            }
        }
    }

    public void onMouseMove(int x, int y) {
        addLog("Mouse Move " + x + " / " + y);

        if (dragging) {

            int absX = x + parent.getLeft();
            int absY = y + parent.getTop();

            System.out.println("Start X:" + dragStartX + " Y:" + dragStartY);
            System.out.println("Move TO X:" + x + " Y:" + y);
            System.out.println("Parent L:" + parent.getLeft() + " T:"
                    + parent.getTop());
            System.out.println("ABS X:" + absX + " Y:" + absY);
            System.out.println("Final Location:L" + (absX - dragStartX) + " T:"
                    + (absY - dragStartY));
            System.out.println("***************************************");

            if (parent.isDragOutline()) {

                DOM.setStyleAttribute(outline.getElement(), "left", parent
                        .getLeft()
                        + x - dragStartX + "px");
                DOM.setStyleAttribute(outline.getElement(), "top", parent
                        .getTop()
                        + y - dragStartY + "px");
            } else {
                // parent.setLocation(absY - dragStartY, absX - dragStartX);
                parent.setLocation(parent.getTop() + y - dragStartY, parent
                        .getLeft()
                        + x - dragStartX);
                dragStartX = x;
                dragStartY = y;

            }

        }
    }

    public void onMouseUp(int x, int y) {
        DOM.removeEventPreview(this);
        addLog("Mouse Up" + x + " / " + y);
        if (draggable) {
            // overlayLayer.hide();
            dragging = false;
            // DOM.releaseCapture(caption.getElement());
            // DOM.removeEventPreview(parent);
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

    public boolean onEventPreview(Event evt) {
        int x = DOM.eventGetClientX(evt);
        int y = DOM.eventGetClientY(evt);
        System.out.println("Position " + x + " ; " + y);
        if (DOM.eventGetType(evt) == Event.ONMOUSEMOVE) {
            onMouseMove(x, y);
        } else if (DOM.eventGetType(evt) == Event.ONMOUSEUP) {
            onMouseUp(x, y);
        } else {
            return true;
        }
        System.out.println("FALSE");
        return true;
    }

    public void onBrowserEvent(Event evt) {
        int x = DOM.eventGetClientX(evt);
        int y = DOM.eventGetClientY(evt);
        Element target = DOM.eventGetTarget(evt);
        if (DOM.eventGetType(evt) == Event.ONMOUSEDOWN) {
            if (target.equals(closeArea.getElement())) {
                parent.close();
            } else if (target.equals(maximizeArea.getElement())) {
                if (parent.isMaximized()) {
                    parent.restore();
                    updateTopBar();
                } else {
                    parent.maximize();
                    if (parent.isMinimizable()) {
                        minimizeArea.setVisible(false);
                    }

                }
            } else if (target.equals(minimizeArea.getElement())) {
                if (parent.isMinimized()) {
                    parent.restore();
                } else {
                    parent.minimize();
                }
            } else if (target.equals(restoreButton.getElement())) {
                setRestored();
            } else {
                onMouseDown(x, y);
            }
        }
    }

    protected void initListeners() {
    }

    public void onMouseDown(Widget sender, int x, int y) {
    }

    public void onMouseEnter(Widget sender) {
    }

    public void onMouseLeave(Widget sender) {
    }

    public void onMouseMove(Widget sender, int x, int y) {
    }

    public void onMouseUp(Widget sender, int x, int y) {
    }

}
