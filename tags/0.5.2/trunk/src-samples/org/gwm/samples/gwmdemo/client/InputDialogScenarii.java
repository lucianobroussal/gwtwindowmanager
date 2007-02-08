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
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.impl.GDialog;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.Hyperlink;

public class InputDialogScenarii extends AbstractScenarii {

    public InputDialogScenarii(FramesManager framesManager) {
        super(framesManager);
    }

    public void runScenarii() {
        GDialog.showInputDialog(null, "What is your favorite hobby?",
                "Asking ...", "", new GDialogChoiceListener() {
                    public void onChoice(GDialog dialog) {
                        if (dialog.getSelectedOption() == GDialog.OK_OPTION) {
                            if (dialog.getSelectedValue() != null
                                    && !((String) dialog.getSelectedValue())
                                            .trim().equals("")) {
                                displayResponse("Your input is : <br/>"
                                        + dialog.getSelectedValue());
                            } else {
                                displayResponse("Your input is empty.");
                            }
                        } else {
                            displayResponse("You didn't enter anything.");
                        }
                    }

                    private void displayResponse(String response) {
                        GInternalFrame responseWin = framesManager
                                .newFrame("Response");
                        responseWin.setTheme("alphacube");
                        responseWin.setMinimizable(false);
                        responseWin.setMaximizable(false);
                        responseWin.setResizable(false);
                        responseWin.setContent(response);
                        GwmUtilities.diplayAtScreenCenter(responseWin);
                    }
                });
    }

    protected Hyperlink createLink() {
        Hyperlink desktopDemoLink = new Hyperlink("Input Dialog",
                "input_dialog");
        return desktopDemoLink;
    }

}
