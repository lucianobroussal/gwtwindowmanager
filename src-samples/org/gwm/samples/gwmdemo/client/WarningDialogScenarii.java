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

import org.gwm.client.impl.DefaultGDialog;

import com.google.gwt.user.client.ui.Hyperlink;

public class WarningDialogScenarii extends AbstractScenarii {

    public WarningDialogScenarii() {
        super();
    }

    public void runScenarii() {
        DefaultGDialog.showMessage(null, "Don't forget your umbrella!. It's raining",
                "Warning",  DefaultGDialog.WARNING_MESSAGE , null);
    }

    protected Hyperlink createLink() {
        Hyperlink desktopDemoLink = new Hyperlink("Warning Dialog",
                "warning_dialog");
        return desktopDemoLink;
    }

}
