/*
 * Copyright (c) 2006-2007 Luciano Broussal, Johan Vos, Andy Scholz, Marcelo Emanoel  (http://www.gwtwindowmanager.org)
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

public class SimpleWindowWithURLScenarii extends AbstractScenarii {

    public SimpleWindowWithURLScenarii() {
        super();

    }

    public void runScenarii() {
        GFrame window = new DefaultGFrame("Window with an URL inside");
        window.setWidth(620);
        window.setHeight(335);
        window.setTheme("alphacube");
        window.setUrl("simplescenarii.html");
        GwmUtilities.diplayAtScreenCenter(window);
    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("With URL content", "simple");
        return simpleDemo;
    }

}
