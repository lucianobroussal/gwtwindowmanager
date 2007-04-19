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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class MouseUtils {
	
	public static final int LEFT_BUTTON = 0;
	public static final int CENTER_BUTTON = 1;
	public static final int RIGHT_BUTTON = 2;
	
	
	public static void setMouseMoveHandler(Widget w, IMouseHandler handler) {
		_setMouseMoveHandler(w.getElement(), handler);
	}
	
	public static void setMouseMoveHandler(IMouseHandler handler) {
		_setMouseMoveHandler(null, handler);
	}
	
	public static void setMouseUpHandler(Widget w, IMouseHandler handler) {
		_setMouseUpHandler(w.getElement(), handler);
	}
	
	public static void setMouseUpHandler(IMouseHandler handler) {
		_setMouseUpHandler(null, handler);
	}
	
	public static void setMouseDownHandler(Widget w, IMouseHandler handler) {
		_setMouseDownHandler(w.getElement(), handler);
	}
	
	public static void setMouseDownHandler(IMouseHandler handler) {
		_setMouseDownHandler(null, handler);
	}
	

	////////////////////////////////////////////////////////////////////////////////////////////////

	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static native void _setMouseMoveHandler(Element obj, IMouseHandler handler) /*-{
	var target = obj;
	if(!target) target = $doc;
	target.onmousemove = function(event) {
		var x = event.clientX;
		var y = event.clientY;
		return handler.@org.gwm.splice.client.util.js.IMouseHandler::onMouseMove(II)(x,y);
	}
}-*/; 

	private static native void _setMouseUpHandler(Element obj, IMouseHandler handler) /*-{
	var target = obj;
	if(!target) target = $doc;
	target.onmouseup = function(event) {
		var x = event.clientX;
		var y = event.clientY;
		var btn = event.button;
		return handler.@org.gwm.splice.client.util.js.IMouseHandler::onMouseUp(III)(x,y,btn);
	}
}-*/; 

	private static native void _setMouseDownHandler(Element obj, IMouseHandler handler) /*-{
	var target = obj;
	if(!target) target = $doc;
	target.onmousedown = function(event) {
		var x = event.clientX;
		var y = event.clientY;
		var btn = event.button;
		return handler.@org.gwm.splice.client.util.js.IMouseHandler::onMouseDown(III)(x,y,btn);
	}
}-*/; 

}
