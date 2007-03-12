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

import org.gwm.client.FramesManager;
import org.gwm.client.impl.GDialog;

import com.google.gwt.user.client.ui.Hyperlink;

public class ErrorDialogScenarii extends AbstractScenarii {

    public ErrorDialogScenarii(FramesManager framesManager) {
        super(framesManager);
    }

    public void runScenarii() {
        GDialog.showMessage(null, "Houston we have a problem!", "Error",
                GDialog.ERROR_MESSAGE);
    }

    protected Hyperlink createLink() {
        Hyperlink desktopDemoLink = new Hyperlink("Error Dialog",
                "error_dialog");
        return desktopDemoLink;
    }

}