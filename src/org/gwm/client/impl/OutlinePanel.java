package org.gwm.client.impl;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.SimplePanel;

public class OutlinePanel extends SimplePanel {

    public OutlinePanel() {
        initUI();
    }

    private void initUI() {
        DOM.setStyleAttribute(getElement(), "backgroundColor",
                "#DFF2FF");
        DOM.setStyleAttribute(getElement(), "position", "absolute");
        DOM.setStyleAttribute(getElement(), "filter",
                "progid:DXImageTransform.Microsoft.Alpha(opacity=50)");
        DOM.setStyleAttribute(getElement(), "-mozOpacity", "0.5");
        DOM.setStyleAttribute(getElement(), "opacity", "0.5");
        DOM
                .setStyleAttribute(getElement(), "border",
                        "1px dotted gray");
    }

    public void setTop(int top) {
        DOM.setStyleAttribute(getElement(), "top", top + "px");
    }

    public void setLeft(int left) {
        DOM.setStyleAttribute(getElement(), "left", left + "px");
    }

    public void setDeep(int deep) {
        DOM.setIntStyleAttribute(getElement(), "zIndex", deep);
    }
}
