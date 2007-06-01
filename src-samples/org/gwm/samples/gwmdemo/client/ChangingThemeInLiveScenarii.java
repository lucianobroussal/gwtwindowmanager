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

import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.DefaultGInternalFrame;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChangingThemeInLiveScenarii extends AbstractScenarii {
    
    
    private GInternalFrame window;
    private ListBox themesList;

    public ChangingThemeInLiveScenarii() {
        super();

    }

    public void runScenarii() {
        
        
        window = new DefaultGInternalFrame("");
        window.setWidth(200);
        window.setHeight(100);
        window.setTheme("default");
        window.setCaption("Live theme change");
        
        themesList =new ListBox();
        themesList.addItem("default", "default");
        themesList.addItem("theme1", "theme1");
        themesList.addItem("alphacube", "alphacube");
        themesList.addItem("spread", "spread");
        themesList.addItem("darkX", "darkX");
        themesList.addItem("mac", "mac");
        themesList.addItem("sky", "sky");
        themesList.addItem("citrus", "citrus");
        themesList.addItem("cloudy", "cloudy");
        
        VerticalPanel contentLayout = new VerticalPanel();
        contentLayout.add(new Label("Change my theme!"));
        contentLayout.add(themesList);
        window.setContent(contentLayout);
        
        themesList.addChangeListener(new ChangeListener() {

            public void onChange(Widget sender) {
                String theme = getCurrentTheme();
                window.setTheme(theme);
              GwmDemo.getDesktop().setTheme(theme);
            }

        });

        
        
       GwmDemo.getDesktop().addFrame(window);
       GwmUtilities.diplayAtScreenCenter(window);

    }
    
    private String getCurrentTheme() {
        String theme = themesList.getItemText(themesList.getSelectedIndex());
        return theme;
    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("Live theme change", "live-theme-change");
        return simpleDemo;
    }
    



}
