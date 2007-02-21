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

import org.gwm.client.GFrame;
import org.gwm.client.impl.DefaultGFrame;
import org.gwm.client.tools.DebugWindow;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.Hyperlink;

public class EventScenarii extends AbstractScenarii {

    public EventScenarii() {
        super();

    }

    public void runScenarii() {
        GFrame window = new DefaultGFrame("Play with me ");
        window.setWidth(530);
        window.setHeight(250);
        window.setTheme("alphacube");
        window.setCaption("Window with an HTML text inside");
        GwmUtilities.displayAtParentCenter(window);
        window.setContent("<img src='images/logo-mini.png' >"
                + "Play with the win to see the windows events fired!");

        DebugWindow debugWindow = new DebugWindow();
        GFrame debugUI = debugWindow.getUI();
        debugUI.setLocation(window.getTop(), window.getLeft()
                + window.getWidth());
        debugUI.setVisible(true);
        window.addFrameListener(debugWindow);

    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("Frame events", "frame_events");
        return simpleDemo;
    }

}
