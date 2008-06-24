/*
 * Copyright (c) 2006/2007 Flipperwing Ltd. (http://www.flipperwing.com)
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
package org.gwm.splice.client.logger;

import java.util.Date;

import org.gwm.client.util.GwmUtilities;
import org.gwm.splice.client.desktop.DesktopManager;
import org.gwm.splice.client.window.AbstractWindow;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LogWindow extends AbstractWindow implements ILogListener {

	public LogWindow() {
		super();
		setName("Logger");
		setWidget(new VerticalPanel());
		setWidth(400);
		setHeight(400);
		GwmUtilities.displayAtParentCenter(this);
		DesktopManager.getInstance().getLogger().addLogListener(this);
	}

	public void itemLogged(LogItem item) {
		String html = "<strong>" + new Date().toString() + "::";
		switch (item.status) {
		case LogItem.INFO:
			html += "INFO: ";
			break;

		case LogItem.WARN:
			html += "WARNING: ";
			break;

		case LogItem.ERROR:
			html += "ERROR: ";
			break;

		case LogItem.SEVERE:
			html += "SEVERE: ";
			break;

		case LogItem.FATAL:
			html += "FATAL: ";
			break;

		default:
			html += item.status + ": ";
			break;
		}

		if(item.source != null) {
			html += " [" + item.source.toString() + "]";
		}
		
		html += "</strong><br/>" + item.message;
		
		((VerticalPanel)widget).add(new HTML(html));
	}

	//////////////////////////////////////////////////////////////////////
	// we dont want to log for this window
	
	protected void log(int level, String message) {
	}

	protected void logError(String message) {
	}

	protected void logFatal(String message) {
	}

	protected void logInfo(String message) {
	}

	protected void logSevere(String message) {
	}

	protected void logWarning(String message) {
	}

}
