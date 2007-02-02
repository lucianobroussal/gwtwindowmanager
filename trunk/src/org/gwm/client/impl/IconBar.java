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

package org.gwm.client.impl;

import java.util.HashMap;
import java.util.Map;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class IconBar extends FlowPanel {

    GDesktopPane parent;
    Map buttonFrame;
    Map buttonIcon;

    public IconBar (GDesktopPane parent) {
        super ();
        this.parent = parent;
        this.buttonFrame = new HashMap();
        this.buttonIcon = new HashMap();
    }

    public void addWindow (GInternalFrame gframe) {
        DefaultGInternalFrame frame = (DefaultGInternalFrame)(gframe);
        HorizontalPanel icon = new HorizontalPanel();
        icon.addStyleName (frame.getTheme()+"_topBar_iconButton");
        Label label = new Label (frame.getCaption());
        label.addStyleName (frame.getTheme()+"_topBar_icon");
        Label restoreButton = new Label("");
        restoreButton.setStyleName(frame.getTheme() + "_topBar_restore");
        icon.add (label);
        icon.add (restoreButton);
        buttonFrame.put (restoreButton, frame);
        buttonIcon.put (restoreButton, icon);
        this.add (icon);
        restoreButton.addClickListener (new ClickListener() {
            public void onClick (Widget sender) {
                GInternalFrame myFrame = (GInternalFrame)buttonFrame.get (sender);
                buttonFrame.remove (sender);
                HorizontalPanel hpicon = (HorizontalPanel)buttonIcon.get (sender);
                remove (hpicon);
                parent.deIconify(myFrame);
              
            }
        });
    }

    public void adjustSize () {
    }

}
