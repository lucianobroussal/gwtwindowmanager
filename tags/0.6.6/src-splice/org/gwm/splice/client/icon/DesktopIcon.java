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
package org.gwm.splice.client.icon;

import java.util.ArrayList;
import java.util.Iterator;

import org.gwm.splice.client.util.js.DragDropUtils;
import org.gwm.splice.client.util.js.IMouseClickListener;
import org.gwm.splice.client.util.js.MouseUtils;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DesktopIcon extends SimplePanel {

	private String name;

	private String imgSource;

	private VerticalPanel panel = new VerticalPanel();

	private ArrayList clickListeners = new ArrayList();

	// ////////////////////////////////////////////////////////////////////

	public DesktopIcon(String name, String imgSource) {
		this(name, imgSource, null);
	}
	public DesktopIcon(String name, String imgSource, ClickListener clickListener) {
		super();
		this.name = name;
		this.imgSource = imgSource;
		
		if(clickListener != null) {
			addClickListener(clickListener);
		}

		setStyleName("desktopIcon");
		
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		Image img = new Image(imgSource);
		img.setPixelSize(32, 32);
		panel.add(img);
		panel.add(new Label(name));

		setWidth("64px");

		add(panel);

		DragDropUtils.makeDraggable(this, new IMouseClickListener() {
			public void onClick(int x, int y, int button) {
				switch(button) {
				case MouseUtils.LEFT_BUTTON:
					nofifyClicked();
					break;
				case MouseUtils.RIGHT_BUTTON:
					break;
				}
			}
		});

	}

	// ////////////////////////////////////////////////////////////////////

	public void addClickListener(ClickListener listener) {
		clickListeners.add(listener);
	}

	public void removeClickListener(ClickListener listener) {
		clickListeners.remove(listener);
	}

	private void nofifyClicked() {
		for (Iterator iter = clickListeners.iterator(); iter.hasNext();) {
			ClickListener listener = (ClickListener) iter.next();
			listener.onClick(this);
		}
	}
	
	/**
	 * Sets style to 'absolute' and sets postion.
	 */
	public void setPosition(int x, int y) {
		DragDropUtils.setWidgetPosition(this, x, y);
	}
	

	
	// ////////////////////////////////////////////////////////////////////

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	// ////////////////////////////////////////////////////////////////////
}
