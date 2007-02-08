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

/**
 * 
 */
package org.gwm.client.impl;

import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class DefaultGDesktopPane extends Composite implements
        WindowResizeListener, GDesktopPane {

    private AbsolutePanel frameContainer;

    private FlexTable desktopWidget;

    private IconBar buttonBar;

    private List frames;

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
        // int tw = Window.getClientWidth();
        // int th = Window.getClientHeight()-50;
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

    public void iconify(GInternalFrame theWindow) {
        theWindow.setVisible(false);
        buttonBar.addWindow(theWindow);
    }

    public void deIconify(GInternalFrame theWindow) {
        theWindow.setVisible(true);
        ((DefaultGInternalFrame) theWindow).fireFrameRestored();
    }

    /**
     * Add a GInternalFrame to this GDesktopPane.
     * 
     * @param internalFrame
     */
    public void addFrame(GInternalFrame internalFrame) {
        internalFrame.setParentDesktop(this);
        int spos = (frames.size() + 1) * 30;
        frameContainer.add((DefaultGInternalFrame) internalFrame,
                frameContainer.getAbsoluteLeft() + spos, frameContainer
                        .getAbsoluteTop()
                        + spos);
        internalFrame.setLocation(frameContainer.getAbsoluteLeft() + spos,
                frameContainer.getAbsoluteTop() + spos);
        frames.add(internalFrame);
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames() {
        for (int i = 0; i < frames.size(); i++) {
            ((GInternalFrame) frames.get(i)).close();
        }
    }

    public List getAllFrames() {
        return frames;
    }

    public void onWindowResized(int width, int height) {
        buttonBar.adjustSize();
    }

    public void setWidgetPosition(DefaultGInternalFrame frame, int left, int top) {
        frameContainer.setWidgetPosition(frame, left, top);
    }

}
