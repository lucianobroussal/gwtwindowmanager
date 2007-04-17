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
package org.gwm.client.impl;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.util.GWmConstants;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT-based implementation of <code>GInternalFrame</code>
 */
public class DefaultGInternalFrame extends DefaultGFrame implements
        GInternalFrame {

    private static final String DEFAULT_TITLE = "GInternalFrame";

    private String inactiveTheme;

    boolean active = false;

    public DefaultGInternalFrame() {
        this(DEFAULT_TITLE);
    }

    public DefaultGInternalFrame(String caption) {
        super(caption);
        this.inactiveTheme = GWmConstants.getDefaultTheme() + "-off";

    }

    private GDesktopPane desktopPane;

    public void setDesktopPane(GDesktopPane pane) {
        this.desktopPane = pane;
    }

    public GDesktopPane getDesktopPane() {
        return this.desktopPane;
    }

    public void minimize() {
        if (desktopPane == null) {
            throw new IllegalStateException(
                    "This method can be used only if the GInternalFrame has been already attached to the parent Desktop.");
        }
        if (desktopPane != null) {
            desktopPane.iconify(this);
            minimized = true;
            fireFrameMinimized();
        }
    }

    public void maximize() {
        if (desktopPane == null) {
            throw new IllegalStateException(
                    "This method can be used only if the GInternalFrame has been already attached to the parent Desktop.");
        }

        if (maxWidth == 0) {
            this.width = desktopPane.getFramesContainer().getOffsetWidth();
        } else {
            this.width = maxWidth;
        }
        if (maxHeight == 0) {
            this.height = desktopPane.getFramesContainer().getOffsetHeight();
        } else {
            this.height = maxHeight;
        }
        this.previousTop = getAbsoluteTop() - ((Widget)desktopPane).getAbsoluteTop();
        this.previousLeft = getAbsoluteLeft() - ((Widget)desktopPane).getAbsoluteLeft();
        this.previousWidth = getWidth();
        this.previousHeight = getHeight();
        setLocation(0, 0);
        setSize(width, height);
        this.maximized = true;
        fireFrameMaximized();
    }

    public void setLocation(int top, int left) {
        if (desktopPane != null) {
            super.setLocation(top, left);
            if (selectBoxManager instanceof SelectBoxManagerImplIE6)
                desktopPane.setWidgetLocation(selectBoxManager
                        .getBlockerWidget(), left, top);
            this.top = top;
            this.left = left;
        }
    }

    public void setVisible(boolean visible) {
        if (desktopPane == null) {
            throw new IllegalStateException(
                    "This method can be used only if the GInternalFrame has been already attached to the parent Desktop.");
        }
        if(minimized){
            throw new IllegalStateException(
            "The Frame is minimized : use the getDesktopPane().deIconify() intead to restore the frame.");            
        }
        super.setVisible(visible);
    }

    public void restore() {
        if (!minimized) {
            super.restore();
            return;
        }
        minimized = false;
        topBar.setRestored();
        setVisible(true);
        fireFrameRestored();
    }

    public void setInactiveTheme(String theme) {
        this.inactiveTheme = theme;
        if (desktopPane != null) {
            if (desktopPane.getActiveFrame() != this) {
                this.currentTheme = theme;
                applyTheme();
            }
        }
    }

    protected void _show() {
        selectBoxManager.setBlockerDeepLayer(++layerOfTheTopWindow);
        DOM.setIntStyleAttribute(getElement(), "zIndex", ++layerOfTheTopWindow);
        if (desktopPane.getActiveFrame() != null) {
            GInternalFrame oldActiveFrame = desktopPane.getActiveFrame();
            desktopPane.setActivateFrame(null);
            oldActiveFrame.setTheme(oldActiveFrame.getTheme());
        }
        getDesktopPane().setActivateFrame(this);
        this.setTheme(currentTheme);
        topFrame = this;
        fireFrameSelected();
    }

    public void setTheme(String theme) {
        if (!theme.endsWith("-off"))
            this.inactiveTheme = theme + "-off";
        if (desktopPane != null) {
            if (getDesktopPane().getActiveFrame() == this) {
                if(theme.endsWith("-off")){
                    String activeTheme = theme.substring(0 , theme.length()-4);
                    super.setTheme(activeTheme);
                }else{
                    super.setTheme(theme);
                }
            } else {
                super.setTheme(inactiveTheme);
            }
        } else {
            super.setTheme(theme);
        }
    }

    public void close() {
        if(!minimized){
            super.close();
        }
        if(desktopPane != null){
            desktopPane.removeFrame(this);
        }
       
    }
    
}
