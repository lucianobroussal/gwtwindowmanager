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

package org.gwm.samples.gwmdemo.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SimpleWindowWithWidgetScenarii extends AbstractScenarii {

    public SimpleWindowWithWidgetScenarii(FramesManager framesManager) {
        super(framesManager);

    }

    public void runScenarii() {
        GInternalFrame window = framesManager.newFrame("");
        window.setWidth(380);
        window.setHeight(440);
        window.setLocation(65, 10);
        window.setTheme("alphacube");
        window.setCaption("Simple window with widget");
        window.setVisible(true);
        window.setContent(buildContentWidget());

    }

    private Widget buildContentWidget() {
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(new Image("images/logo-mini.png"));
        Hyperlink showSrcLink = new Hyperlink("Show source", "show_source");
        showSrcLink.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                showSourceCode();

            }
        });
        verticalPanel.add(showSrcLink);
        verticalPanel.add(new HTML("<h2>Simple window with HTML inside</h2>"));
        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        for (int i = 1; i <= 18; i++) {
            HorizontalPanel panel = new HorizontalPanel();
            Image ligne = new Image("images/bar" + (i % 3 + 1) + ".jpg");
            ligne.setWidth(i * 15 + "px");
            ligne.setHeight(18 + "px");
            Image ligne1 = new Image("images/bar" + ((i + 1) % 3 + 1) + ".jpg");
            ligne1.setWidth((270 - (i * 15)) + "px");
            ligne1.setHeight(18 + "px");
            panel.add(ligne);
            Label label = new Label(i * 15 + "px");
            label.setWidth(35 + "px");
            label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
            panel.add(label);

            panel.add(ligne1);
            verticalPanel.add(panel);
        }
        return verticalPanel;

    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("With Widget", "simple");
        return simpleDemo;

    }

    protected void showSourceCode() {
        GInternalFrame window = framesManager.newFrame("");
        window.setWidth(800);
        window.setHeight(440);
        window.setLocation(65, 440);
        window.setTheme("alphacube");
        window.setCaption("Source code of window with GWT widget inside");
        window.setUrl("site/demo/simplewithwidgetscenarii.html");
        window.setVisible(true);

    }

}
