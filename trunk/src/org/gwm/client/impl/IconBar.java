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

import java.util.HashMap;
import java.util.Map;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GFrame;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The IconBar implementation used from {@link DefaultGDesktopPane} 
 *
 */
public class IconBar extends FlowPanel {

    GDesktopPane parent;

    Map buttonFrame;

    Map buttonByFrame;

    Map iconByFrame;

    Map buttonIcon;

    public IconBar(GDesktopPane parent) {
        super();
        this.parent = parent;
        this.buttonFrame = new HashMap();
        this.buttonIcon = new HashMap();
        this.buttonByFrame = new HashMap();
        this.iconByFrame = new HashMap();

    }

    public void addFrame(GFrame gframe) {
        DefaultGFrame frame = (DefaultGFrame) (gframe);
        HorizontalPanel icon = new HorizontalPanel();

        HorizontalPanel iconLayout = new HorizontalPanel();
        icon.setStyleName(getItemTheme(frame, "DeskTop-MinimizedFrameBar"));
        Label label = new Label(frame.getCaption());
        label.setStyleName(getItemTheme(gframe, "Frame-TopBar-minimized"));
        Label restoreButton = new Label("");
        restoreButton.setStyleName(getItemTheme(gframe,
                "Frame-TopBar-RestoreButton"));

        Image titleIcon = frame.getTitleIcon();
        if (titleIcon != null) {
            iconLayout.add(titleIcon);
            iconLayout.setCellVerticalAlignment(titleIcon,
                    HasVerticalAlignment.ALIGN_MIDDLE);
        }
        iconLayout.add(label);
        icon.add(iconLayout);
        icon.add(restoreButton);
        buttonFrame.put(restoreButton, frame);
        buttonIcon.put(restoreButton, icon);
        buttonByFrame.put(frame, restoreButton);
        iconByFrame.put(frame, icon);
        this.add(icon);
        restoreButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                GFrame myFrame = (GFrame) buttonFrame.get(sender);
                parent.deIconify(myFrame);
            }
        });
    }

    public void adjustSize() {
    }

    public void removeFrame(GFrame frame) {
        iconByFrame.remove(frame);
        Object button = buttonByFrame.remove(frame);
        buttonFrame.remove(button);
        HorizontalPanel hpicon = (HorizontalPanel) buttonIcon.remove(button);
        remove(hpicon);
    }

    private String getItemTheme(GFrame parent, String item) {
        return "gwm-" + parent.getTheme() + "-" + item;
    }
}
