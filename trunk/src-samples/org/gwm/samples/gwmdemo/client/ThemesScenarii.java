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

package org.gwm.samples.gwmdemo.client;

import org.gwm.client.GFrame;
import org.gwm.client.impl.DefaultGFrame;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class ThemesScenarii extends AbstractScenarii {

    public ThemesScenarii() {
        super();

    }

    public void runScenarii() {

        GFrame window = new DefaultGFrame("");
        window.setWidth(480);
        window.setHeight(450);
        window.setLocation(65, 20);

        window.setTheme("alphacube");
        window.setCaption("Themes");
        window.setContent(buildThemesWindowContent());
        window.setVisible(true);

    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("Themes", "themes");
        return simpleDemo;
    }

    private Hyperlink buildLinkTheme(final String theme) {
        Hyperlink themeLink = new Hyperlink("View", null);
        themeLink.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                GFrame window = new DefaultGFrame("");
                window.setWidth(400);
                window.setHeight(150);
                window.setLocation(200, 640);
                if (theme != null)
                    window.setTheme(theme);
                window.setCaption("Window with theme : "
                        + (theme != null ? theme : "dialog"));
                window.setUrl("themecontent.html");
                GwmUtilities.displayAtParentCenter(window);

            }

        });
        return themeLink;
    }

    private Widget buildThemesWindowContent() {
        FlexTable contentLayout = new FlexTable();
        contentLayout
                .setHTML(
                        0,
                        0,
                        "<img src=images/logo-mini.png ><h2>Overview</h2>"
                                + "<p class=black>Creating a new theme is very easy. You need to describe window's look and feel in a CSS file.Name your CSS file <span style=color:red>THEME_NAME.css</span> with thoses CSS definition:"
                                + "<br/>By convention the images used in the CSS files are in <span style=color:red>THEME_NAME</span> directory <br/>"
                                + "<br/><div class=sample style=color:#6600CC>A window is divided into 9 parts, 4 corners, 4 borders and the main content in the middle.</div></p>");

        contentLayout.setHTML(1, 0, "<h2>Layout Template</h2>");
        contentLayout
                .setHTML(
                        3,
                        0,
                        "<p class=black>View the detail template CSS file (you can copy-paste it to start a new theme). See <a target=_blank href='http://art.gnome.org/themes/metacity'>Art.Gnome.Org</a> for cool designs.it's easy to integrate in the class</p><br/>");
        Hyperlink viewTemplateLink = new Hyperlink("View a template",
                "view_template");
        viewTemplateLink.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                showTemplate();

            }

            private void showTemplate() {
                GFrame window = new DefaultGFrame("");
                window.setWidth(600);
                window.setHeight(460);
                window.setLocation(65, 560);
                window.setTheme("alphacube");
                window.setCaption("Theme template CSS file");
                window.setUrl("theme_template.html");
                window.setVisible(true);

            }

        });
        contentLayout.setWidget(2, 0, viewTemplateLink);
        contentLayout.setHTML(4, 0, "<h2>Themes included in this release</h2>");
        contentLayout.getFlexCellFormatter().setColSpan(0, 0, 2);

        FlexTable themesTable = new FlexTable();

        themesTable.setHTML(0, 1, "<p>Default</p>");
        themesTable.setWidget(0, 0, buildLinkTheme("default"));

        themesTable
                .setHTML(
                        1,
                        1,
                        "<p>Alphacube from <a href=http://art.gnome.org/themes/metacity/1171 target=_blank>Art.Gnome.Org</a></p>");
        themesTable.setWidget(1, 0, buildLinkTheme("alphacube"));

        themesTable.setHTML(2, 1,
                "<p>Spread (Alphacube with a different color scheme)</p>");
        themesTable.setWidget(2, 0, buildLinkTheme("spread"));

        themesTable
                .setHTML(
                        3,
                        1,
                        "<p>DarkX from <a href=http://art.gnome.org/themes/metacity/708 target=_blank>Art.Gnome.Org</a><p>");
        themesTable.setWidget(3, 0, buildLinkTheme("darkX"));

        themesTable.setHTML(4, 1, "<p>Theme1<p>");
        themesTable.setWidget(4, 0, buildLinkTheme("theme1"));

        contentLayout.setWidget(5, 0, themesTable);
        contentLayout.getFlexCellFormatter().setHorizontalAlignment(5, 0,
                HasHorizontalAlignment.ALIGN_CENTER);
        contentLayout.getFlexCellFormatter().setColSpan(5, 0, 2);
        themesTable.setStyleName("gwmtest-ThemeOverviewContent");
        themesTable.setCellSpacing(0);
        themesTable.setCellPadding(5);

        return contentLayout;
    }

}
