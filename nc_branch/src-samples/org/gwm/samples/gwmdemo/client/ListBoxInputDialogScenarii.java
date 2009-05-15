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

import org.gwm.client.GDialog;
import org.gwm.client.GFrame;
import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.impl.DefaultGDialog;
import org.gwm.client.impl.DefaultGFrame;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class ListBoxInputDialogScenarii extends AbstractScenarii {

	public ListBoxInputDialogScenarii() {
		super();
	}

	@Override
	public void runScenarii() {

		DefaultGDialog.setWrapStringMessage(false);
		DefaultGDialog.showInputDialog(null, "What is your favorite color? (blue is the default value)", "Asking ...", "blue", new Object[] { "green", "red",
				"blue", "white", "black", "orange" }, GDialog.QUESTION_MESSAGE, null, new GDialogChoiceListener() {
			public void onChoice(GDialog dialog) {
				DefaultGDialog.setWrapStringMessage(true);
				if (dialog.getSelectedOption() == DefaultGDialog.OK_OPTION) {
					if (dialog.getSelectedValue() != null && !((String) dialog.getSelectedValue()).trim().equals("")) {
						displayResponse("Your favorite color is : <br/>" + dialog.getSelectedValue());
					} else {
						displayResponse("Your input is empty.");
					}
				} else {
					displayResponse("You didn't enter anything.");
				}
			}

			private void displayResponse(String response) {
				GFrame responseWin = new DefaultGFrame("Response");
				responseWin.setMinimizable(false);
				responseWin.setMaximizable(false);
				responseWin.setResizable(false);
				responseWin.setContent(new HTML(response));
				GwmUtilities.displayAtScreenCenter(responseWin);
			}
		});
	}

	@Override
	protected Hyperlink createLink() {
		Hyperlink desktopDemoLink = new Hyperlink("Built-in selection dialog", "");
		return desktopDemoLink;
	}

}
