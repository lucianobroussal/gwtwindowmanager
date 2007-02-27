package org.gwm.client.util.widget;

import org.gwm.client.impl.DefaultGFrame;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class OverlayLayer extends ComplexPanel {
    public OverlayLayer() {
        setElement(DOM.createDiv());
    }

    public void show(String theme) {
        DOM.setStyleAttribute(getElement(), "position", "absolute");
        DOM.setStyleAttribute(getElement(), "left", "0px");
        DOM.setStyleAttribute(getElement(), "top", "0px");
        DOM.setStyleAttribute(getElement(), "width", "100%");
        DOM.setStyleAttribute(getElement(), "height", "100%");
        Window.enableScrolling(false);

        setStyleName("overlay_" + theme);

        DOM.setIntStyleAttribute(getElement(), "zIndex", DefaultGFrame
                .getLayerOfTheTopWindow() + 1);

        RootPanel.get().add(this);
        setVisible(true);
    }

    public void hide() {
        RootPanel.get().remove(this);
    }

}
