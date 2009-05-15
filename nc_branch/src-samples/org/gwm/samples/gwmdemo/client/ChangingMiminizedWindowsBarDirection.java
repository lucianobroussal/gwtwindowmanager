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
import org.gwm.client.impl.LayoutConstant;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChangingMiminizedWindowsBarDirection extends AbstractScenarii {
    
    
    private GInternalFrame window;
    private ListBox directionList;

    public ChangingMiminizedWindowsBarDirection() {
        super();

    }

    public void runScenarii() {
        
        
        window = new DefaultGInternalFrame("");
        window.setWidth(300);
        window.setHeight(100);
        window.setTheme("default");
        window.setCaption("Modify Minimized Bar  Direction");
        
        directionList =new ListBox();
        directionList.addItem("South", "south");
        directionList.addItem("East", "east");
        directionList.addItem("West", "west");
        directionList.addItem("North", "north");
        
        VerticalPanel contentLayout = new VerticalPanel();
        contentLayout.add(new Label("Change the Bar Direction!"));
        contentLayout.add(directionList);
        window.setContent(contentLayout);
        
        directionList.addChangeListener(new ChangeListener() {

            public void onChange(Widget sender) {
              GwmDemo.getDesktop().setMinimizedWindowBarDirection(getCurrentDirection());
            }

        });

        
        
       GwmDemo.getDesktop().addFrame(window);
       GwmUtilities.displayAtScreenCenter(window);

    }
    
    private LayoutConstant getCurrentDirection() {
        String direction = directionList.getValue(directionList.getSelectedIndex());
        if(direction.equals("north"))
            return LayoutConstant.NORTH;
        if(direction.equals("east"))
            return LayoutConstant.EAST;
        if(direction.equals("south"))
            return LayoutConstant.SOUTH;
        return LayoutConstant.WEST;
    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("Windows Bar", "bar-direction");
        return simpleDemo;
    }
    



}
