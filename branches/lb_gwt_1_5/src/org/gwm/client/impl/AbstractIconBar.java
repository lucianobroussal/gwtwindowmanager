/*
 * Copyright (c) 2006-2008 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtwindowmanager.org)
 * 
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GFrame;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractIconBar implements IconBar{
    protected GDesktopPane parent;

    protected Map buttonFrame;

    protected Map buttonByFrame;

    protected Map iconByFrame;

    protected Map buttonIcon;

    public AbstractIconBar(GDesktopPane parent) {
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
        restoreButton.setStyleName(getItemTheme(gframe, "Frame-TopBar-RestoreButton"));

        Image titleIcon = frame.getTitleIcon();
        if (titleIcon != null) {
            iconLayout.add(titleIcon);
            iconLayout.setCellVerticalAlignment(titleIcon, HasVerticalAlignment.ALIGN_MIDDLE);
        }
        iconLayout.add(label);
        icon.add(iconLayout);
        icon.add(restoreButton);
        buttonFrame.put(restoreButton, frame);
        buttonIcon.put(restoreButton, icon);
        buttonByFrame.put(frame, restoreButton);
        iconByFrame.put(frame, icon);
        addMinimizedWindow(icon);
        restoreButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                GFrame myFrame = (GFrame) buttonFrame.get(sender);
                parent.deIconify(myFrame);
            }
        });
    }

   
   

    public void removeFrame(GFrame frame) {
        iconByFrame.remove(frame);
        Object button = buttonByFrame.remove(frame);
        if (button != null) {
            buttonFrame.remove(button);
            HorizontalPanel hpicon = (HorizontalPanel) buttonIcon.remove(button);
            removeMinimizedWindow(hpicon);
        }
    }

    String getItemTheme(GFrame parent, String item) {
        return "gwm-" + parent.getTheme() + "-" + item;
    }
    
    public List getGFrames() {
        return new ArrayList(buttonFrame.values());
    }

    public void adjustSize(){
        
    }
}
