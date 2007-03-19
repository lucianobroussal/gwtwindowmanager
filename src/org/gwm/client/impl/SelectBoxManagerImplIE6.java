package org.gwm.client.impl;

import org.gwm.client.GFrame;
import org.gwm.client.event.GFrameAdapter;
import org.gwm.client.event.GFrameEvent;
import org.gwm.client.event.GFrameListener;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class SelectBoxManagerImplIE6 extends SelectBoxManagerImpl {

    private Frame selectBlocker;

    public SelectBoxManagerImplIE6() {
        initBlocker();

    }

    private void initBlocker() {
        selectBlocker = new Frame();
        selectBlocker.setWidth("100%");
        selectBlocker.setHeight(Window.getClientHeight()+"px");
        selectBlocker.setUrl("#");
        DOM.setAttribute(selectBlocker.getElement(), "frameBorder", "50");
        DOM.setStyleAttribute(selectBlocker.getElement(), "filter",
                "progid:DXImageTransform.Microsoft.Alpha(opacity=50)");
        DOM.setStyleAttribute(selectBlocker.getElement(), "border",
                "1px solid red");
        DOM.setAttribute(selectBlocker.getElement(), "scrolling", "no");
        DOM.setStyleAttribute(selectBlocker.getElement(), "position",
                "absolute");
//        selectBlocker.setVisible(false);
//        RootPanel.get().add(selectBlocker);
    }

    public void setBlockerVisible(boolean visible) {
        selectBlocker.setVisible(visible);
    }

    public void setLocation(int top, int left) {
        Element selectBlockerElement = selectBlocker.getElement();
        DOM.setStyleAttribute(selectBlockerElement, "left", left + "px");
        DOM.setStyleAttribute(selectBlockerElement, "top", top + "px");
    }

    public void setBlockerSize(int width, int height) {
        selectBlocker.setSize(width + "px", height + "px");
    }

    public void setBlockerDeepLayer(int layer) {
        DOM.setIntStyleAttribute(selectBlocker.getElement(), "zIndex", layer);
    }

    public void removeBlocker() {
        selectBlocker.removeFromParent();
    }

    public GFrameListener getFrameListener() {
        return new GFrameAdapter() {

            public void frameResized(GFrameEvent evt) {
                GFrame frame = evt.getGFrame();
                System.out.println(frame.getWidth() + " / " + frame.getHeight()
                        + "");
                selectBlocker.setSize(frame.getWidth() + "", frame.getHeight()
                        + "");
            }

        };
    }

    public void onParentDragEnd(DefaultGFrame parent) {
    }

    public void onParentDragStart(DefaultGFrame parent) {
    }

    public Widget getFrameFinalUI(FlexTable ui) {
        Window.alert("DIV");
        AbsolutePanel panel = new AbsolutePanel();
        panel.add(selectBlocker, 0,0);
        panel.add(ui, 0,0);
        return panel;
    }

}
