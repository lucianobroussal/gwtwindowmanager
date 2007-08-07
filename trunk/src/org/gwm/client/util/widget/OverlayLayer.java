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
package org.gwm.client.util.widget;

import org.gwm.client.impl.DefaultGFrame;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class OverlayLayer extends ComplexPanel {
    public OverlayLayer() {
        setElement(DOM.createDiv());
        initProperties();
        setVisible(false);
        RootPanel.get().add(this);
    }

    public void show(String theme) {
        show(theme ,1);
    }

    public void show(String theme, int deep) {
        setStyleName("gwm-" + theme + "-ActionOverlay");
        DOM.setIntStyleAttribute(getElement(), "zIndex", DefaultGFrame
                .getLayerOfTheTopWindow()
                + deep);
        setVisible(true);
    }

    public void show() {
        show("default");
    }

    public void show(int deep) {
        show("default" , deep);
    }

    private void initProperties() {
        DOM.setStyleAttribute(getElement(), "position", "absolute");
        DOM.setStyleAttribute(getElement(), "left", "0px");
        DOM.setStyleAttribute(getElement(), "top", "0px");
        DOM.setStyleAttribute(getElement(), "width", "100%");
        DOM.setStyleAttribute(getElement(), "height", "100%");
    }

    public void hide() {
        //RootPanel.get().remove(this);
        setVisible(false);
    }
    
    public void finalize(){
            RootPanel.get().remove(this);
    }

}
