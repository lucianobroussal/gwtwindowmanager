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
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.Hyperlink;

public class SimpleWindowWithHTMLScenarii extends AbstractScenarii {

    public SimpleWindowWithHTMLScenarii() {
        super();

    }

    public void runScenarii() {
        GFrame window = new DefaultGFrame("Window with an HTML text inside");
        window.setWidth(580);
        window.setHeight(400);
        window.setTheme("alphacube");
       
        window
                .setContent("<img src='images/logo-mini.png' >"
                        + "<h2>Simple window with HTML inside</h2>"
                        + "<b>"
                        + "This sample opens a simple window with an HTML text inside."
                        + "</b>"
                        + "<p> Source code </p>"
                        + "<table align=center><tr><td><div class=sample1>"
                        + "GFrame frame = new DefaultGFrame(\"Window with an HTML text inside\");<br/>"
                        + "frame.setWidth(580);<br/>"
                        + "frame.setHeight(250);<br/>"
                        + "frame.setStyle(\"alphacube\");<br/>"
                        + "<span style=color:blue>"
                        + "<xmp>frame.setContent("
                        + "<img src='images/logo-mini.png'>"
                        + "<h2>Simple window with HTML inside</h2>"
                        + "<b>"
                        + "This sample opens a simple window with an HTML text inside."
                        + "</b>);</xmp></span>"
                        + "<br/>GwmUtilities.diplayAtScreenCenter(frame);<br/>"
                        +"</div></td></tr></table>");

                
        GwmUtilities.diplayAtScreenCenter(window);
    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("With HTML content", "simple");
        return simpleDemo;
    }

}
