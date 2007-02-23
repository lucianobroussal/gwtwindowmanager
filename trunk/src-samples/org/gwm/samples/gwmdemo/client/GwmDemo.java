/*
 * Copyright (c) 2006-2007 Luciano Broussal, Johan Vos, Andy Scholz, Marcelo Emanoel  (http://www.gwtwindowmanager.org)
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

import org.gwm.client.GDesktopPane;
import org.gwm.client.impl.DefaultGDesktopPane;
import org.gwm.client.impl.DefaultGInternalFrame;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GwmDemo implements EntryPoint {

    private GDesktopPane desktop;

    private DefaultGInternalFrame menuFrame;

    public void onModuleLoad() {
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
        menuFrame = new DefaultGInternalFrame("Samples");
        menuFrame.setTheme("alphacube");
        menuFrame.setClosable(false);
        menuFrame.setMaximizable(false);
        menuFrame.setResizable(false);
        desktop.addFrame(menuFrame);
        menuFrame.setVisible(true);
        menuFrame.setLocation(5, 5);
        VerticalPanel menuLayout = new VerticalPanel();
        menuLayout.setStyleName("gwmdemo-Menu");
        menuLayout.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        menuLayout.add(buildMenu("Frames"));
        menuLayout.add(buildMenuItem(new SimpleWindowWithURLScenarii()));
        menuLayout.add(buildMenuItem(new SimpleWindowWithHTMLScenarii()));
        menuLayout.add(buildMenuItem(new SimpleWindowWithWidgetScenarii()));
        menuLayout.add(buildMenu("Frame Listener"));
        menuLayout.add(buildMenuItem(new EventScenarii()));
        menuLayout.add(buildMenu("Themes"));
        menuLayout.add(buildMenuItem(new ThemesScenarii()));
        menuLayout.add(buildMenuItem(new ChangingThemeInLiveScenarii()));
        menuLayout.add(buildMenu("Desktop"));
        menuLayout.add(buildMenuItem(new DesktopScenarii(desktop)));
        menuLayout.add(buildMenu("Dialog"));
        menuLayout.add(buildMenuItem(new InputDialogScenarii()));
        menuLayout.add(buildMenuItem(new WarningDialogScenarii()));
        menuLayout.add(buildMenuItem(new ErrorDialogScenarii()));
        menuLayout.add(buildMenuItem(new ConfirmDialogScenarii()));
        menuLayout.add(buildMenuItem(new CustomizedDialogScenarii()));
        menuLayout.add(buildMenu("Tools"));
        menuLayout.add(buildMenuItem(new WindowEditorScenarii()));

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

}
