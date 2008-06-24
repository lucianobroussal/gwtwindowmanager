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
package org.gwm.splice.client.util.js;

import org.gwm.splice.client.desktop.DesktopManager;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

public class DragDropUtils {

	private static boolean initialized = false;
	private static Element dragElement = null;
	private static int startX;
	private static int startY;
	private static int offsetX;
	private static int offsetY;
	private static int height;
	private static int width;
	
	private static boolean elementMoved = false;
	private static int orgZindex;
	
	private static int minLeft;
	private static int minTop;
	private static int maxRight;
	private static int maxBottom;
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void makeDraggable(Widget w) {
		makeDraggable(w, null);
	}
	public static void makeDraggable(Widget w, IMouseClickListener listener) {
		init();
		ElementMouseHandler handler = new ElementMouseHandler(w, listener);
		MouseUtils.setMouseDownHandler(w, handler);
		MouseUtils.setMouseUpHandler(w, handler);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void init() {
		if(initialized) {
			return;
		}
		IMouseHandler handler = new PageMouseHandler();
		MouseUtils.setMouseMoveHandler(handler);
		MouseUtils.setMouseUpHandler(handler);
	}
	
	private static class ElementMouseHandler extends MouseHandler {
		Widget w;
		IMouseClickListener listener;
		
		ElementMouseHandler(Widget w, IMouseClickListener listener) {
			this.w = w;
			this.listener = listener;
		}
		
		public boolean onMouseDown(int x, int y, int button) {
			if(button == MouseUtils.LEFT_BUTTON) {
				// only on left button
				dragElement = w.getElement();
				startX = x;
				startY = y;
				width = w.getOffsetWidth();
				height = w.getOffsetHeight();
				offsetX = x - w.getAbsoluteLeft();
				offsetY = y - w.getAbsoluteTop();
				_setElementZIndex(dragElement, 100);
				
				DesktopManager dt = DesktopManager.getInstance();
				minLeft = dt.getLeftMargin();
				minTop = dt.getTopMargin();
				maxRight = Window.getClientWidth() - dt.getRightMargin();
				maxBottom = Window.getClientHeight() - dt.getBottomMargin();
				
				return false;
			}
			return true;
		}

		public boolean onMouseUp(int x, int y, int button) {
			_setElementZIndex(dragElement, orgZindex);
			dragElement = null;
			if(elementMoved) {
				elementMoved = false;
				return false;
			}
			elementMoved = false;
			if(listener != null) {
				listener.onClick(x, y, button);
			}
			return true;
		}
	}
	
	private static class PageMouseHandler extends MouseHandler {

		public boolean onMouseMove(int x, int y) {
			if(dragElement == null || (x == startX && y == startY)) {
				return true;
			}
			int left = x - offsetX;
			int top = y - offsetY;
			int right = left + width;
			int bottom = top + height;
			
			if(left <= minLeft || top <= minTop || right >= maxRight || bottom >= maxBottom) {
				dragElement = null;
				return true;
			}
			
			// move stuff here
			_setElementPosition(dragElement, x - offsetX, y - offsetY);
			elementMoved = true;
			return false;
		}

		
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Sets style to 'absolute' and sets postion.
	 */
	public static void setWidgetPosition(Widget widget, int x, int y) {
		_setElementPosition(widget.getElement(), x, y);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static native void _setElementPosition(Element obj, int x, int y) /*-{
	obj.style.position = 'absolute';
	obj.style.top = y;
	obj.style.left = x;
}-*/; 

	private static native void _setElementZIndex(Element obj, int val) /*-{
	obj.style.zIndex = val;
}-*/; 


}
