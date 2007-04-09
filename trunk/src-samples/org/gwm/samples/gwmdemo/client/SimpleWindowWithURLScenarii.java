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

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Hyperlink;

public class SimpleWindowWithURLScenarii extends AbstractScenarii {

    public SimpleWindowWithURLScenarii() {
        super();

    }

    public void runScenarii() {
        GInternalFrame window = new DefaultGInternalFrame("GWT");
        window.setWidth(800);
        window.setHeight(400);
        window.setUrl("http://code.google.com/webtoolkit/");
        GwmDemo.getDesktop().addFrame(window);
        window.setLocation(50, 180);
        window.setVisible(true);
        final GInternalFrame window1 = new DefaultGInternalFrame("GWm");
        window1.setWidth(800);
        window1.setHeight(400);
        window1.setUrl("http://code.google.com/p/gwtwindowmanager/");
        GwmDemo.getDesktop().addFrame(window1);
        window1.setLocation(100, 220);
        window1.setVisible(true);
        new Timer(){

            public void run() {
                window1.setLocation(0,0);
                
            }
            
        }.schedule(5000);
    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("With URL content", "");
        return simpleDemo;
    }

}
