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

package org.gwm.samples.gwmdemo.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.Hyperlink;

public class SimpleWindowWithTextScenarii extends AbstractScenarii {

    public SimpleWindowWithTextScenarii(FramesManager framesManager) {
        super(framesManager);

    }

    public void runScenarii() {
        GInternalFrame window = framesManager.newFrame("");
        window.setWidth(530);
        window.setHeight(250);
        window.setTheme("alphacube");
        window.setCaption("Window with an HTML text inside");
        GwmUtilities.displayAtParentCenter(window);
        window
                .setContent("<img src='images/logo-mini.png' >"
                        + "<h2>Simple window with HTML inside</h2>"
                        + "<b>"
                        + "This sample opens a simple window with an HTML text inside."
                        + "</b>"
                        + "<p> Source code </p>"
                        + "<table align=center><tr><td><div class=sample1>"
                        + "FramesManager framesManager = new FramesManagerFactory().createFramesManager();</br>"
                        + "...<br/>"
                        + "GInternalFrame frame = framesManager.newFrame();<br/>"
                        + "frame.setWidth(550);<br/>"
                        + "frame.setHeight(300);<br/>"
                        + "frame.setStyle(\"alphacube\");<br/>"
                        + "frame.showCenter(false);<br/>"
                        + "<span style=color:blue>"
                        + "<xmp>frame.setContent("
                        + "<img src='images/logo-mini.png'>"
                        + "<h2>Simple window with HTML inside</h2>"
                        + "<b>"
                        + "This sample opens a simple window with an HTML text inside."
                        + "</b>);</xmp></span>" + "</div></td></tr></table>"

                );

    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("With HTML content", "simple");
        return simpleDemo;
    }

}
