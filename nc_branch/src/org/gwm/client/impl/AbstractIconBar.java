/*
 * Copyright (c) 2006-2008 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtwindowmanager.org)
 *
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
package org.gwm.client.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GFrame;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractIconBar implements IconBar {
	protected GDesktopPane parent;

	protected Map<Widget, GFrame> buttonFrame = new HashMap<Widget, GFrame>();
	protected Map<GFrame, Widget> buttonByFrame = new HashMap<GFrame, Widget>();
	protected Map<GFrame, Widget> iconByFrame = new HashMap<GFrame, Widget>();
	protected Map<Widget, Widget> buttonIcon = new HashMap<Widget, Widget>();

	public AbstractIconBar(GDesktopPane parent) {
		this.parent = parent;
	}

	public void addFrame(GFrame frame) {
		HorizontalPanel icon = new HorizontalPanel();

		HorizontalPanel iconLayout = new HorizontalPanel();
		icon.setStyleName(getItemTheme(frame, "DeskTop-MinimizedFrameBar"));
		Label label = new Label(frame.getCaption());
		label.setStyleName(getItemTheme(frame, "Frame-TopBar-minimized"));
		final Label restoreButton = new Label("");
		restoreButton.setStyleName(getItemTheme(frame, "Frame-TopBar-RestoreButton"));

		Image titleIcon = frame.getTitleIcon();
		if (titleIcon != null) {
			iconLayout.add(titleIcon);
			iconLayout.setCellVerticalAlignment(titleIcon, HasVerticalAlignment.ALIGN_MIDDLE);
		}
		iconLayout.add(label);
		icon.add(iconLayout);
		icon.add(restoreButton);
		buttonFrame.put(restoreButton, frame);
		buttonIcon.put(restoreButton, icon);
		buttonByFrame.put(frame, restoreButton);
		iconByFrame.put(frame, icon);
		addMinimizedWindow(icon);
		restoreButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				GFrame myFrame = buttonFrame.get(restoreButton);
				parent.deIconify(myFrame);
			}
		});
	}

	public void removeFrame(GFrame frame) {
		iconByFrame.remove(frame);
		Object button = buttonByFrame.remove(frame);
		if (button != null) {
			buttonFrame.remove(button);
			HorizontalPanel hpicon = (HorizontalPanel) buttonIcon.remove(button);
			removeMinimizedWindow(hpicon);
		}
	}

	public String getItemTheme(GFrame parent, String item) {
		return "gwm-" + parent.getTheme() + "-" + item;
	}

	public List<GFrame> getGFrames() {
		return new ArrayList<GFrame>(buttonFrame.values());
	}

	public void adjustSize() {
	}
}
