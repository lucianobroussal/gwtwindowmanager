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
/**
 * 
 */
package org.gwm.client.impl;

import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GFrame;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class DefaultGDesktopPane extends Composite implements
        WindowResizeListener, GDesktopPane {

    private AbsolutePanel frameContainer;

    private FlexTable desktopWidget;

    private IconBar buttonBar;

    private List frames;
    
    private GInternalFrame activeFrame;

    public DefaultGDesktopPane() {
        initialize();
        setupUI();
        setupListeners();
    }

    private void initialize() {
        this.frames = new ArrayList();
    }

    private void setupUI() {
        desktopWidget = new FlexTable();
        desktopWidget.setBorderWidth(0);
        desktopWidget.setCellPadding(0);
        desktopWidget.setCellSpacing(0);
        frameContainer = new AbsolutePanel();
        frameContainer.setWidth("100%");
        frameContainer.setHeight("100%");
        buttonBar = new IconBar(this);
        desktopWidget.getFlexCellFormatter().setHeight(0, 0, "100%");
        desktopWidget.setWidget(0, 0, frameContainer);
        frameContainer.setStyleName("gwm-GDesktopPane-FrameContainer");

        desktopWidget.setWidget(1, 0, buttonBar);
        desktopWidget.getFlexCellFormatter().setStyleName(1, 0,
                "gwm-GDesktopPane-TaskBar");

        initWidget(desktopWidget);
        setStyleName("gwm-GDesktopPane");

    }

    private void setupListeners() {

    }

    public void iconify(GFrame theWindow) {
        theWindow.setVisible(false);
        buttonBar.addWindow(theWindow);
    }

    public void deIconify(GFrame theWindow) {
        theWindow.restore();
    }

    /**
     * Add a GFrame to this GDesktopPane.
     * 
     * @param internalFrame
     */
    public void addFrame(GInternalFrame internalFrame) {
        internalFrame.setDesktopPane(this);
        int spos = (frames.size() + 1) * 30;
        int left = frameContainer.getAbsoluteLeft() + spos;
        int top = frameContainer.getAbsoluteTop() + spos;
        SelectBoxManagerImpl selectBoxManager = ((DefaultGFrame) internalFrame)
                .getSelectBoxManager();
        if (selectBoxManager instanceof SelectBoxManagerImplIE6) {
            frameContainer.add(selectBoxManager.getBlockerWidget(), left, top);
        }
        frameContainer.add((Widget)internalFrame);
        internalFrame.setLocation(left, top);
        frames.add(internalFrame);
        
        
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames() {
        for (int i = 0; i < frames.size(); i++) {
            ((GFrame) frames.get(i)).close();
        }
    }

    public List getAllFrames() {
        return frames;
    }

    public void onWindowResized(int width, int height) {
        buttonBar.adjustSize();
    }

    
    public void setWidgetLocation(Widget widget, int left, int top) {
        frameContainer.remove(widget);
        frameContainer.add(widget);
        frameContainer.setWidgetPosition(widget, left, top);
    }

    public Widget getFramesContainer() {
        return frameContainer;
    }

    public void setActivateFrame(GInternalFrame internalFrame) {
        activeFrame = internalFrame;
    }

    public GInternalFrame getActiveFrame() {
        return activeFrame;
    }


}
