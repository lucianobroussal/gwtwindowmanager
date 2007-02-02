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

package org.gwm.samples.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.FramesManagerFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThemesSample implements EntryPoint {

    private ListBox listBox;

    private GInternalFrame frame;

    protected FramesManager framesManager;

    public void onModuleLoad() {
        framesManager = new FramesManagerFactory().createFramesManager();
        testThemes();
    }

    private void testThemes() {
        listBox = new ListBox();
        listBox.addItem("default", "default");
        listBox.addItem("theme1", "theme1");
        listBox.addItem("alphacube", "alphacube");
        listBox.addItem("spread", "spread");
        listBox.addItem("alert", "alert");
        listBox.addItem("darkX", "darkX");

        listBox.addChangeListener(new ChangeListener() {

            public void onChange(Widget sender) {
                String theme = getCurrentTheme();
                frame.setTheme(theme);

            }

        });
        listBox.setEnabled(false);
        RootPanel.get().add(listBox);

        Button button = new Button("Test Frame  Theme");
        button.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                if (!listBox.isEnabled()) {
                    listBox.setEnabled(true);
                }
                frame = framesManager.newFrame("http://www.google.com");
                // frame.setUrl("http://www.google.com");
                frame.setContent(new Label("http://www.google.com"));
                frame.setSize(300, 200);
                frame.setLocation(200, 200);
                frame.setResizable(true);
                frame.setTheme(getCurrentTheme());
                frame.setVisible(true);
            }

        });
        RootPanel.get().add(button);

    }

    private String getCurrentTheme() {
        String theme = listBox.getItemText(listBox.getSelectedIndex());
        return theme;
    }

}
