/*
 * Copyright (c) 2006 gwtwindowmanager.org (http://www.gwtwindowmanager.org)
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

package org.gwm.samples.gwmdemo.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.DefaultGDesktopPane;
import org.gwm.client.impl.FramesManagerFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GwmDemo implements EntryPoint {

    private GDesktopPane desktop;

    private GInternalFrame menuFrame;

    private FramesManager framesManager;

    public void onModuleLoad() {
        framesManager = new FramesManagerFactory().createFramesManager();
        buildUI();
        menuFrame.setSize(150, 300);
        Window.enableScrolling(false);
    }

    private void buildUI() {
        desktop = new DefaultGDesktopPane();
        buildMenu();
        RootPanel.get().add((Widget) desktop);
    }

    private void buildMenu() {
        menuFrame = framesManager.newFrame("Samples");
        menuFrame.setTheme("alphacube");
        menuFrame.setClosable(false);
        menuFrame.setMinimizable(false);
        menuFrame.setResizable(false);
        desktop.addFrame(menuFrame);
        menuFrame.setVisible(true);
        menuFrame.setLocation(5, 5);
        VerticalPanel menuLayout = new VerticalPanel();
        menuLayout.setStyleName("gwmdemo-Menu");
        menuLayout.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        menuLayout.add(buildMenu("Frames"));
        menuLayout.add(buildMenuItem(new SimpleWindowWithURLScenarii(
                framesManager)));
        menuLayout.add(buildMenuItem(new SimpleWindowWithTextScenarii(
                framesManager)));
        menuLayout.add(buildMenuItem(new SimpleWindowWithWidgetScenarii(
                framesManager)));
        menuLayout.add(buildMenu("Frame Listener"));
        menuLayout.add(buildMenuItem(new EventScenarii(framesManager)));
        menuLayout.add(buildMenu("Frames Manager"));
        menuLayout.add(buildMenuItem(new FramesManagerListAllFramesScenarii(
                framesManager)));
        menuLayout.add(buildMenu("Themes"));
        menuLayout.add(buildMenuItem(new ThemesScenarii(framesManager)));
        menuLayout.add(buildMenuItem(new ChangingThemeInLiveScenarii(framesManager)));
        menuLayout.add(buildMenu("Desktop"));
        menuLayout
                .add(buildMenuItem(new DesktopScenarii(framesManager, desktop)));
        menuLayout.add(buildMenu("Dialog"));
        menuLayout.add(buildMenuItem(new InputDialogScenarii(framesManager)));
        menuLayout.add(buildMenuItem(new WarningDialogScenarii(framesManager)));
        menuLayout.add(buildMenuItem(new ErrorDialogScenarii(framesManager)));
        menuLayout.add(buildMenuItem(new ConfirmDialogScenarii(framesManager)));
        menuLayout.add(buildMenu("Tools"));
        menuLayout.add(buildMenuItem(new WindowEditorScenarii(framesManager)));

        menuFrame.setContent(menuLayout);
    }

    private Widget buildMenu(String name) {
        HorizontalPanel menuLayout = new HorizontalPanel();
        menuLayout.setSpacing(3);
        menuLayout.add(new Label(name));
        menuLayout.setStyleName("gwmdemo-MenuSection");
        return menuLayout;
    }

    private Widget buildMenuItem(Scenarii scenarii) {
        HorizontalPanel menuItemLayout = new HorizontalPanel();
        menuItemLayout.setSpacing(2);
        menuItemLayout.add(scenarii.getLink());
        menuItemLayout.setStyleName("gwmdemo-MenuItem");
        return menuItemLayout;
    }

    //    
    // Hyperlink windowEditorLink = new Hyperlink("WindowEditor",
    // "windoweditor");
    // windowEditorLink.addClickListener(new ClickListener() {
    //
    // public void onClick(Widget sender) {
    // displayWindowEditor();
    //
    // }
    //
    // });

}
