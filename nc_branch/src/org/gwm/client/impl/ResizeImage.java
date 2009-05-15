/*
 * Copyright (c) 2006-2007 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtwindowmanager.org)
 *
 * Main Contributors :
 *      Johan Vos,Andy Scholz,Marcelo Emanoel
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

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

public class ResizeImage extends FlowPanel implements MouseUpHandler, MouseMoveHandler, MouseDownHandler {

	private int resizeStartX;
	private int resizeStartY;
	private boolean resizing;

	private final DefaultGFrame parent;

	private String currentStyle;
	private Label label;

	public ResizeImage(DefaultGFrame parent) {
		super();
		this.parent = parent;
		buildGui();
		sinkEvents(Event.MOUSEEVENTS);
	}

	private void buildGui() {
		this.currentStyle = parent.getTheme();
		label = new Label("");
		setTheme(currentStyle);
		label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		label.addMouseMoveHandler(this);
		label.addMouseUpHandler(this);
		label.addMouseDownHandler(this);
		add(label);
	}

	@Override
	public void onBrowserEvent(Event e) {
		super.onBrowserEvent(e);
	}

	public void setTheme(String currentTheme) {
		label.setStyleName("gwm-" + currentTheme + "-Frame-ResizeButton");
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		resizing = false;
		DOM.releaseCapture(label.getElement());
		parent.stopResizing(); // call this and allow the frame to remove
		// iframes
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (resizing) {
			int newHeight = parent.getHeight() + event.getClientY() - resizeStartY;
			int newWidth = parent.getWidth() + event.getClientX() - resizeStartX;
			if (newWidth > parent.getMinimumWidth()) {
				parent.setWidth(newWidth);
			}
			if (newHeight > parent.getMinimumHeight()) {
				parent.setHeight(newHeight);
			}
		}
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		resizing = true;
		DOM.setCapture(label.getElement());
		resizeStartX = event.getClientX();
		resizeStartY = event.getClientY();
		parent.startResizing(); // call this and allow the frame to remove
		// iframes
	}

}
