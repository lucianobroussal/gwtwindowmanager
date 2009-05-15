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
/**
 *
 */
package org.gwm.client.impl;

import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GFrame;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GFrameAdapter;
import org.gwm.client.event.GFrameEvent;
import org.gwm.client.util.Gwm;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * The GWT default implementation for {@link GDesktopPane}
 * 
 */
public class DefaultGDesktopPane extends Composite implements WindowResizeListener, GDesktopPane {

	private static int desktopID = 1;

	private AbsolutePanel frameContainer;

	private FlexTable desktopWidget;

	private IconBar buttonBar;

	private final List<GInternalFrame> frames = new ArrayList<GInternalFrame>();

	private GInternalFrame activeFrame;

	private FrameListenerForScrollableDesktop frameListenerForScrollableDesktop;

	private String theme = Gwm.getDefaultTheme();

	private boolean scrollable;

	private int buttonBarX = 0;

	private int buttonBarY = 0;

	private final String name;

	public DefaultGDesktopPane() {
		this("Desktop" + (desktopID++));
	}

	public DefaultGDesktopPane(String name) {
		this.name = name;
		setupUI();
		setupListeners();
	}

	private void setupUI() {
		desktopWidget = new FlexTable();
		desktopWidget.setBorderWidth(0);
		desktopWidget.setCellPadding(0);
		desktopWidget.setCellSpacing(0);
		frameContainer = new AbsolutePanel();
		frameContainer.setWidth("100%");
		frameContainer.setHeight("100%");
		buttonBar = new HorizontalIconBar(this);
		setMinimizedWindowBarDirection(LayoutConstant.SOUTH);
		desktopWidget.getFlexCellFormatter().setStyleName(1, 1, "gwm-" + theme + "-GDesktopPane-FrameContainer");
		desktopWidget.setWidget(1, 1, frameContainer);
		frameContainer.add(new HTML("&nbsp"));

		initWidget(desktopWidget);
		setStyleName("gwm-" + theme + "-GDesktopPane");
		theme = Gwm.getDefaultTheme();

	}

	private void setupListeners() {
		frameListenerForScrollableDesktop = new FrameListenerForScrollableDesktop();
	}

	public String getName() {
		return name;
	}

	public void iconify(GFrame frame) {
		frame.setVisible(false);
		buttonBar.addFrame(frame);
	}

	public void deIconify(GFrame frame) {
		buttonBar.removeFrame(frame);
		frame.restore();
	}

	/**
	 * Add a GFrame to this GDesktopPane.
	 * 
	 * @param internalFrame
	 */
	public void addFrame(GInternalFrame internalFrame) {
		if (scrollable) {
			internalFrame.addFrameListener(frameListenerForScrollableDesktop);
		}
		internalFrame.setDesktopPane(this);
		int spos = (frames.size() + 1) * 30;
		int left = frameContainer.getAbsoluteLeft() + spos;
		int top = frameContainer.getAbsoluteTop() + spos;
		SelectBoxManagerImpl selectBoxManager = ((DefaultGFrame) internalFrame).getSelectBoxManager();
		if (selectBoxManager instanceof SelectBoxManagerImplIE6) {
			frameContainer.add(selectBoxManager.getBlockerWidget(), left, top);
		}
		frameContainer.add((Widget) internalFrame);
		internalFrame.setLocation(left, top);
		frames.add(internalFrame);
		internalFrame.setTheme(theme);
	}

	public void removeFrame(GInternalFrame internalFrame) {
		if (scrollable) {
			internalFrame.removeGFrameListener(frameListenerForScrollableDesktop);
		}
		frameContainer.remove((Widget) internalFrame);
		SelectBoxManagerImpl selectBoxManager = ((DefaultGFrame) internalFrame).getSelectBoxManager();
		if (selectBoxManager instanceof SelectBoxManagerImplIE6) {
			frameContainer.remove(selectBoxManager.getBlockerWidget());
		}
		frames.remove(internalFrame);
		buttonBar.removeFrame(internalFrame);

	}

	/**
	 * Closes all GInternalFrames contained in this GDesktopPane.
	 */
	public void closeAllFrames() {
		for (int i = 0; i < frames.size(); i++) {
			(frames.get(i)).close();
		}
	}

	public List<GInternalFrame> getAllFrames() {
		return frames;
	}

	public void onWindowResized(int width, int height) {
		buttonBar.adjustSize();
	}

	public void addWidget(Widget widget, int left, int top) {
		frameContainer.remove(widget);
		frameContainer.add(widget);
		frameContainer.setWidgetPosition(widget, left, top);
	}

	public void setWidgetPosition(Widget widget, int left, int top) {
		frameContainer.setWidgetPosition(widget, left, top);
	}

	public Widget getFramesContainer() {
		return frameContainer;
	}

	public void setActivateFrame(GInternalFrame internalFrame) {
		activeFrame = internalFrame;
	}

	public GInternalFrame getActiveFrame() {
		return activeFrame;
	}

	public void setTheme(String theme) {
		this.theme = theme;
		for (int x = 0; x < frames.size(); x++) {
			GInternalFrame theFrame = frames.get(x);
			theFrame.setTheme(theme);
		}
		frameContainer.setStyleName("gwm-" + theme + "-GDesktopPane-FrameContainer");

		desktopWidget.getFlexCellFormatter().setStyleName(1, 0, "gwm-" + theme + "-GDesktopPane-TaskBar");
		setStyleName("gwm-" + theme + "-GDesktopPane");
	}

	private class FrameListenerForScrollableDesktop extends GFrameAdapter {

		@Override
		public void onFrameMaximizing(GFrameEvent evt) {
			DOM.setStyleAttribute(frameContainer.getElement(), "overflow", "hidden");
		}

		@Override
		public void frameRestored(GFrameEvent evt) {
			DOM.setStyleAttribute(frameContainer.getElement(), "overflow", "auto");
		}
	}

	public void enableScrolling(boolean scrollable) {
		this.scrollable = scrollable;
		if (scrollable) {
			if (frameListenerForScrollableDesktop == null) {
				frameListenerForScrollableDesktop = new FrameListenerForScrollableDesktop();
			} else {
				if (frameListenerForScrollableDesktop != null) {
					for (GFrame frame : frames) {
						frame.removeGFrameListener(frameListenerForScrollableDesktop);
					}
				}
			}
			DOM.setStyleAttribute(frameContainer.getElement(), "overflow", "auto");
		}

	}

	public void setMinimizedWindowBarDirection(LayoutConstant direction) {

		int newbuttonBarX = getButtonBarX(direction);
		int newbuttonBarY = getButtonBarY(direction);
		desktopWidget.getFlexCellFormatter().setStyleName(buttonBarY, buttonBarX, "");

		if (direction == LayoutConstant.NORTH || direction == LayoutConstant.SOUTH) {
			if (buttonBar instanceof HorizontalIconBar) {
				desktopWidget.setWidget(newbuttonBarY, newbuttonBarX, buttonBar.getUI());
				desktopWidget.getFlexCellFormatter().setStyleName(newbuttonBarY, newbuttonBarX, buttonBar.getTheme(theme));
			} else {
				IconBar newButtonBar = new HorizontalIconBar(this);
				copyMinimizedWindow(buttonBar, newButtonBar);
				desktopWidget.getFlexCellFormatter().setStyleName(newbuttonBarY, newbuttonBarX, newButtonBar.getTheme(theme));
				desktopWidget.setWidget(newbuttonBarY, newbuttonBarX, newButtonBar.getUI());
				buttonBar = newButtonBar;
			}
		}

		if (direction == LayoutConstant.EAST || direction == LayoutConstant.WEST) {
			if (buttonBar instanceof VerticalIconBar) {
				desktopWidget.setWidget(newbuttonBarY, newbuttonBarX, buttonBar.getUI());
				desktopWidget.getFlexCellFormatter().setStyleName(newbuttonBarY, newbuttonBarX, buttonBar.getTheme(theme));
			} else {
				IconBar newButtonBar = new VerticalIconBar(this);
				desktopWidget.getFlexCellFormatter().setStyleName(newbuttonBarY, newbuttonBarX, newButtonBar.getTheme(theme));
				desktopWidget.setWidget(newbuttonBarY, newbuttonBarX, newButtonBar.getUI());
				copyMinimizedWindow(buttonBar, newButtonBar);
				buttonBar = newButtonBar;
			}

		}
		buttonBarX = newbuttonBarX;
		buttonBarY = newbuttonBarY;

	}

	private void copyMinimizedWindow(IconBar source, IconBar newButtonBar) {
		for (GFrame frame : source.getGFrames()) {
			newButtonBar.addFrame(frame);
			source.removeFrame(frame);
		}

	}

	private int getButtonBarX(LayoutConstant direction) {
		if (direction == LayoutConstant.NORTH)
			return 1;
		if (direction == LayoutConstant.EAST)
			return 2;
		if (direction == LayoutConstant.SOUTH)
			return 1;
		return 0;
	}

	private int getButtonBarY(LayoutConstant direction) {
		if (direction == LayoutConstant.NORTH)
			return 0;
		if (direction == LayoutConstant.EAST)
			return 1;
		if (direction == LayoutConstant.SOUTH)
			return 2;
		return 1;
	}

}
