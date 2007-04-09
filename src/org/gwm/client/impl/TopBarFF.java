package org.gwm.client.impl;

import org.gwm.client.GInternalFrame;
import org.gwm.client.util.GWmConstants;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class TopBarFF extends TopBar implements EventPreview {

    

    TopBarFF() {
        super();
    }

    public void onMouseDown(int x, int y) {
        DOM.addEventPreview(this);
        if (draggable) {
            dragStarted = true;
            dragStartX = x;
            dragStartY = y;
            if(GWmConstants.isOverlayLayerDisplayOnDragAction()){
                fixPanelForFrameWithURL.show(parent.getTheme());
            }
            if (parent.isDragOutline()) {
                if (parent instanceof GInternalFrame) {
                    ((GInternalFrame) parent).getDesktopPane()
                            .setWidgetLocation(outline, 0, 0);
                } else {
                    RootPanel.get().add(outline);
                }
                outline.setSize(parent.getWidth() + "px", parent.getHeight()
                        + "px");
                outline.setTop(parent.getTop());
                outline.setLeft(parent.getLeft());
                outline.setDeep(DefaultGFrame.getLayerOfTheTopWindow() + 50);
                outline.setVisible(true);
            } else {
//                DOM.setStyleAttribute(parent.getElement(), "filter",
//                        "progid:DXImageTransform.Microsoft.Alpha(opacity=100)");
//                DOM
//                        .setStyleAttribute(parent.getElement(), "-mozOpacity",
//                                "0.9");
//                DOM.setStyleAttribute(parent.getElement(), "opacity", "1");
//                parent.getSelectBoxManager().onParentDragStart(parent);
//                DOM.setIntStyleAttribute(parent.getElement(), "zIndex",
//                        DefaultGFrame.getLayerOfTheTopWindow() + 2);
            }
        }
    }

    public void onMouseMove(int x, int y) {
        if (dragStartX == x && dragStartY == y) {
            return;
        } else if (dragStarted && !dragging) {
            dragging = true;
            fixPanelForFrameWithURL.show(parent.getTheme());
        }
        if (dragging) {
            int absX = x + parent.getLeft();
            int absY = y + parent.getTop();
            if (parent.isDragOutline()) {

                outline.setTop(absY - dragStartY);
                outline.setLeft(absX - dragStartX);
                parent.fireGhostMoving(absY - dragStartY, absX - dragStartX);
            } else {
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
        if (dragging || dragStarted) {
            fixPanelForFrameWithURL.hide();
            int absX = x + parent.getLeft();
            int absY = y + parent.getTop();

            parent.setLocation(absY - dragStartY, absX - dragStartX);
            if (parent.isDragOutline()) {
                outline.removeFromParent();
                outline.setVisible(false);
                parent.fireGhostMoved(absY - dragStartY, absX - dragStartX);
            } else {
                // TODO BLOCKER
                // parent.getSelectBoxManager().setBlockerVisible(true);
                // DOM.setStyleAttribute(parent.getElement(), "filter",
                // "progid:DXImageTransform.Microsoft.Alpha(opacity=100)");
                // DOM.setStyleAttribute(parent.getElement(), "-mozOpacity",
                // "1");
                // DOM.setStyleAttribute(parent.getElement(), "opacity", "1");
                //                parent.getSelectBoxManager().onParentDragEnd(parent);
            }
            if (dragging)
                parent.fireFrameMoved();

            dragging = false;
            dragStarted = false;
        }
    }

    public boolean onEventPreview(Event evt) {
        int x = DOM.eventGetClientX(evt);
        int y = DOM.eventGetClientY(evt);
        if (DOM.eventGetType(evt) == Event.ONMOUSEMOVE) {
            onMouseMove(x, y);
        } else if (DOM.eventGetType(evt) == Event.ONMOUSEUP) {
            onMouseUp(x, y);
        } else {
            return true;
        }
        return true;
    }

    public void onBrowserEvent(Event evt) {
        super.onBrowserEvent(evt);
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
                    minimized = false;
                    parent.restore();
                } else {
                    minimized = true;
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
        
        sinkEvents(Event.MOUSEEVENTS);
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
