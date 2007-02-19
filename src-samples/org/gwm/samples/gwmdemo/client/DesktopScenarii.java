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

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.DefaultGInternalFrame;

import com.google.gwt.user.client.ui.Hyperlink;

public class DesktopScenarii extends AbstractScenarii {

    private GDesktopPane desktop;

    public DesktopScenarii( GDesktopPane desktop) {
        super();
        this.desktop = desktop;
    }

    public void runScenarii() {
        buildScreenShotFrame("Screen1", "images/sc1.png");
        buildScreenShotFrame("Screen2", "images/sc2.png");
        buildScreenShotFrame("Screen3", "images/sc3.png");
    }

    protected Hyperlink createLink() {
        Hyperlink desktopDemoLink = new Hyperlink("Frames in desktop",
                "desktop");
        return desktopDemoLink;
    }

    private void buildScreenShotFrame(final String linkCaption, final String url) {
        GInternalFrame window = new DefaultGInternalFrame(linkCaption);
        window.setSize(800, 500);
        window.setTheme("default");
        window.setUrl(url);
        desktop.addFrame(window);
        window.setVisible(true);
    }

}
