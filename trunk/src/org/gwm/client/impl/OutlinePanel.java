/*
 * Copyright (c) 2006-2007 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtwindowmanager.org)
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
import com.google.gwt.user.client.ui.SimplePanel;

public class OutlinePanel extends SimplePanel {

    public OutlinePanel() {
        initUI();
    }

    private void initUI() {
        DOM.setStyleAttribute(getElement(), "backgroundColor", "#DFF2FF");
        DOM.setStyleAttribute(getElement(), "position", "absolute");
        DOM.setStyleAttribute(getElement(), "filter",
                "progid:DXImageTransform.Microsoft.Alpha(opacity=50)");
        DOM.setStyleAttribute(getElement(), "-mozOpacity", "0.5");
        DOM.setStyleAttribute(getElement(), "opacity", "0.5");
        DOM.setStyleAttribute(getElement(), "border", "1px dotted gray");
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
