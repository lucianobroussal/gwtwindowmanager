/*
 * Copyright (c) 2006 gwtwindowmanager.org (http://www.gwtwindowmanager.org)
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

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import org.gwm.client.*;

/**
 * GWT-based implementation of <code>GInternalFrame</code>
 *
 * @author Johan Vos 
 */
public class GwtInternalFrame extends PopupPanel implements GInternalFrame, MouseListener, EventListener  {

  private String id;
  private String title;
  private HorizontalPanel topBar;
  private Label caption;
  private Widget myContent;
  private int dragStartX, dragStartY;
  private int resizeStartX, resizeStartY;
  private boolean dragging;
  private static final int DEFAULT_WIDTH  = 200;
  private static final int DEFAULT_HEIGHT = 300;
  private static final String DEFAULT_STYLE = "dialog";
  private Element closeElement;
  private Element minElement;
  private Element maxElement;
  private int maxWidth, maxHeight;
  private int minWidth, minHeight;
  private int width = -1;
  private int height = -1;
  private String currentStyle;
  private boolean closable, maximizable, minimizable, draggable, resizable;
  private HTML resizeHTML;
  private Element resizeElement;
  private boolean resizing;


  private FlexTable panel = new FlexTable();

  public GwtInternalFrame (String id) {
    this.id = id;
    this.currentStyle = DEFAULT_STYLE;
    this.title = "Frame "+id;
    this.myContent = new HTML ("MY CONTENT");
    this.closable = true;
    this.minimizable = true;
    this.maximizable = true;
    this.resizable = true;
    buildGui();
    sinkEvents (Event.ONCLICK | Event.MOUSEEVENTS);
  }

  private void buildGui() {
    this.panel = new FlexTable();
    String cm = currentStyle + "_close";
    String min = currentStyle + "_minimize";
    String max = currentStyle + "_maximize";
    this.caption = new Label (title);
    this.caption.addStyleName (currentStyle+"_title");
    this.caption.setHorizontalAlignment (HasHorizontalAlignment.ALIGN_CENTER);
    Element parentElement = panel.getElement();
    if (closable) {
      closeElement = DOM.createDiv();
      DOM.setInnerHTML (closeElement, "<div class=\""+cm+"\"/>");
      DOM.appendChild (parentElement, closeElement);
      DOM.sinkEvents (closeElement, Event.ONCLICK);
      DOM.setEventListener (closeElement, this);
    }
    if (minimizable) {
      minElement = DOM.createDiv();
      DOM.setInnerHTML (minElement, "<div class=\""+min+"\"/>");
      DOM.appendChild (parentElement, minElement);
      DOM.sinkEvents (minElement, Event.ONCLICK);
      DOM.setEventListener (minElement, this);
    }
    if (maximizable) {
      maxElement = DOM.createDiv();
      DOM.setInnerHTML (maxElement, "<div class=\""+max+"\"/>");
      DOM.appendChild (parentElement, maxElement);
      DOM.sinkEvents (maxElement, Event.ONCLICK);
      DOM.setEventListener (maxElement, this);
    }
    if (this.width < 0) {
      this.width = DEFAULT_WIDTH;
    }
    if (this.height < 0) {
      this.height = DEFAULT_HEIGHT;
    }
    this.panel.setSize (this.width+"px", this.height+"px");
    panel.setHTML(0, 0, "&nbsp;");
    panel.getCellFormatter().setStyleName(0,0, currentStyle+ "_nw");
    panel.setWidget(0, 1, caption);
    panel.getCellFormatter().setStyleName(0,1, currentStyle+ "_n");
    panel.setHTML(0, 2, "&nbsp;");
    panel.getCellFormatter().setStyleName(0,2, currentStyle+ "_ne");
    panel.setHTML(1, 0, "&nbsp;");
    panel.getCellFormatter().setStyleName(1,0, currentStyle+ "_w");
    panel.setWidget (1, 1, myContent);
    panel.getCellFormatter().setStyleName(1,1, currentStyle+ "_content");
    panel.setHTML(1, 2, "&nbsp;");
    panel.getCellFormatter().setStyleName(1,2, currentStyle+ "_e");

    panel.setHTML(2, 0, "&nbsp;");
    panel.getCellFormatter().setStyleName(2,0, currentStyle+ "_sw");

    panel.setHTML(2, 1, "&nbsp;");
    panel.getCellFormatter().setStyleName(2,1, currentStyle+ "_s");

    if (resizable) {
      panel.getCellFormatter().setStyleName(2,2, currentStyle+ "_sizer");
      resizeHTML = new HTML ("&nbsp;");
      panel.setWidget(2, 2, resizeHTML);
      resizeElement = resizeHTML.getElement ();
      DOM.sinkEvents (resizeElement, Event.ONCLICK);
      DOM.setEventListener (resizeElement, this);
    }
    else {
      panel.getCellFormatter().setStyleName(2,2, currentStyle+ "_se");
      panel.setHTML(2, 2, "&nbsp;");
    }
    panel.setBorderWidth(0);
    panel.setCellPadding(0);
    panel.setCellSpacing(0);
    panel.getCellFormatter().setHeight(1, 1, "100%");
    panel.getCellFormatter().setWidth(1, 1, "100%");
    panel.getCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
    setStyleName("gwt-DialogBox");
    this.caption.addMouseListener (this);
    super.setWidget (panel);
    this.maxWidth = Window.getClientWidth();
    this.maxHeight = Window.getClientHeight();
    this.minWidth = 100;
    this.minHeight = 120;
  }

  public String getId () {
    return this.id;
  }

  public void setStyle (String v) {
    this.currentStyle = v;
    buildGui();
  }

  public String getStyle () {
    return this.currentStyle;
  }

  public void show (boolean modal) {
    super.show();
  }

  public void showCenter (boolean modal) {
    super.show();
  }

  public void setContent(Widget widget) {
    myContent = widget;
    buildGui();
  }

  public void setContent(String content) {
    myContent = new HTML (content);
    buildGui();
  }

  public void minimize () {
    this.width = minWidth;
    this.height = minHeight;
    buildGui();
  }

  public void maximize () {
    this.setPopupPosition (0,0);
    this.width = maxWidth;
    this.height = maxHeight;
    buildGui();
  }

  public void toFront () {
  }

  public void destroy () {
  }

  public boolean isMinimized () {
    return false;
  }

  public boolean isMaximized () {
    return false;
  }

  public void setDestroyOnClose () {
  }

  public void setLocation(int top, int left) {
    setPopupPosition (top, left);
  }

  public void setSize(int width, int height) {
    panel.setSize (width+"px", height+"px");
  }

  public void setWidth(int width) {
    panel.setWidth (width+"px");
  }

  public void setHeight(int height) {
    panel.setHeight (height+"px");
  }

  public void setMinimumWidth(int minWidth) {
    this.minWidth = minWidth;
  }

  public void setMinimumHeight(int minHeight) {
    this.minHeight = minHeight;
  }

  public void setMaximumWidth(int maxWidth) {
    this.maxWidth = maxWidth;
  }

  public void setMaximumHeight(int maxHeight) {
    this.maxHeight = maxHeight;
  }

  public void setTop(int top) {
    setPopupPosition (top, getPopupLeft());
  }

  public void setLeft(int left) {
    setPopupPosition (getPopupTop(), left);
  }

  public void updateWidth() {
  }

  public void updateHeight() {
  }

  public void setTitle(String title) {
    this.title = title;
    this.caption.setText (title);
  }

  public String getTitle() {
    return this.title;
  }

  public void setUrl(String url) {
    myContent = new HTML ("<iframe src=\""+url+"\" height=\""+DEFAULT_HEIGHT+"\" width=\""+DEFAULT_WIDTH+"\" frameborder=\"0\"/>");
    buildGui();
  }

  public void setResizable(boolean resizable) {
    this.resizable = resizable;
  }

  public void setClosable(boolean closable) {
    this.closable = closable;
  }

  public void setMinimizable(boolean minimizable) {
    this.minimizable = minimizable;
  }

  public void setMaximizable(boolean maximizable) {
    this.maximizable = maximizable;
  }

  public void setDraggable(boolean draggable) {
    this.draggable = draggable;
  }

   public void onMouseDown(Widget sender, int x, int y) {
    dragging = true;
    DOM.setCapture(caption.getElement());
    dragStartX = x;
    dragStartY = y;
  }

  public void onMouseEnter(Widget sender) {
  }

  public void onMouseLeave(Widget sender) {
  }

  public void onMouseMove(Widget sender, int x, int y) {
    if (dragging) {
      int absX = x + getAbsoluteLeft();
      int absY = y + getAbsoluteTop();
      setPopupPosition(absX - dragStartX, absY - dragStartY);
    }
  }

  public void onMouseUp(Widget sender, int x, int y) {
    dragging = false;
    DOM.releaseCapture(caption.getElement());
  }

  public void onBrowserEvent(Event event) {
    Element target = DOM.eventGetTarget (event);
    int type = DOM.eventGetType (event);
    if (type == Event.ONCLICK) {
      Element parent = DOM.getParent (target);
      if (parent.equals (closeElement)) {
        this.hide();
        return;
      }
      if (parent.equals (minElement)) {
        this.minimize();
        return;
      }
      if (parent.equals (maxElement)) {
        this.maximize();
        return;
      }
      Element myself = DOM.getParent (myContent.getElement());
      if (myself.equals (target)) {
        this.hide();
        this.show();
      }
      return;
    }
    if ((type == Event.ONMOUSEDOWN) && (target.equals (resizeElement))) {
      this.resizing = true;
      resizeStartX = DOM.eventGetClientX (event);
      resizeStartY = DOM.eventGetClientY (event);
    }
    if (type == Event.ONMOUSEUP) {
      this.resizing = false;
    }
    if ((type == Event.ONMOUSEMOVE) && this.resizing) {
      int nx = DOM.eventGetClientX (event);
      int ny = DOM.eventGetClientY (event);
      int newWidth = this.width + nx - resizeStartX;
      int newHeight = this.height + ny - resizeStartY;
      if (newWidth > minWidth) {
        this.width = this.width + nx - resizeStartX;
        resizeStartX = nx;
      }
      if (newHeight > minHeight) {
        this.height = this.height + ny - resizeStartY;
        resizeStartY = ny;
      }
      buildGui();
    }
  }

  public boolean onEventPreview (Event evt) {
    boolean answer = super.onEventPreview(evt);
    return true;
  }

}
