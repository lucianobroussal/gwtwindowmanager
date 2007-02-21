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

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.DOM;

/**
 * GWT-based implementation of <code>GInternalFrame</code>
 */
public class DefaultGInternalFrame extends DefaultGFrame implements
        GInternalFrame {

    public DefaultGInternalFrame(String caption) {
        super(caption);
    }

    private GDesktopPane desktopPane;

    public void setDesktopPane(GDesktopPane pane) {
        this.desktopPane = pane;
    }

    public GDesktopPane getDesktopPane() {
        return this.desktopPane;
    }

    public void minimize() {
        if (desktopPane != null) {
            desktopPane.iconify(this);
            minimized = true;
            fireFrameMinimized();
        }
    }

    public void restore() {
    }

    public void setLocation(int top, int left) {
        if (desktopPane != null) {
            ((DefaultGDesktopPane) desktopPane).setWidgetPosition(this, left,
                    top);
            this.top = top;
            this.left = left;
        }
    }

    public GDesktopPane getParentDesktop() {
        // TODO Auto-generated method stub
        return null;
    }

    protected void _show() {
        DOM.setIntStyleAttribute(getElement(), "zIndex", ++layerOfTheTopWindow);
        topFrame = this;
        fireFrameOpened();

    }

}
