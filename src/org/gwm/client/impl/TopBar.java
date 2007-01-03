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

public class TopBar extends FlowPanel implements ClickListener, MouseListener {

  private Label caption;
  private String currentStyle;
  private String title;
  private GwtInternalFrame parent;
  private Image imgClose;
  private Image imgMaximize;
  private Image imgMinimize;
  private int dragStartX, dragStartY;
  private boolean dragging;


  public TopBar (GwtInternalFrame parent) {
    super ();
    this.parent = parent;
    buildGui();
    sinkEvents (Event.MOUSEEVENTS);
  }

  private void buildGui() {
    this.currentStyle = parent.getStyle();
    this.title = parent.getTitle();
    addStyleName ("topBar");
    caption = new Label (title);
    caption.addStyleName ("caption");
    caption.addMouseListener (this);
    imgClose = new Image();
    imgClose.setUrl ("themes/"+this.currentStyle+"/close.gif");
    imgClose.addStyleName ("float");
    imgClose.addClickListener (this);
    imgMinimize = new Image();
    imgMinimize.setUrl ("themes/"+this.currentStyle+"/minimize.gif");
    imgMinimize.addStyleName ("float");
    imgMinimize.addClickListener (this);
    imgMaximize = new Image();
    imgMaximize.setUrl ("themes/"+this.currentStyle+"/maximize.gif");
    imgMaximize.addStyleName ("float");
    imgMaximize.addClickListener (this);
    add (caption);
    add (imgMinimize);
    add (imgMaximize);
    add (imgClose);
    System.out.println ("width = "+this.getOffsetWidth());
  }

  public void onClick (Widget w) {
    if (w.equals (imgClose)) {
      parent.hide();
    }
    if (w.equals (imgMaximize)) {
      parent.maximize();
    }
    if (w.equals (imgMinimize)) {
      parent.minimize();
    }
  }

  public void onBrowserEvent (Event e) {
System.out.println ("top-bar: "+ DOM.eventGetType (e));
    int type = DOM.eventGetType (e);
    Element em = caption.getElement();
    int x = DOM.eventGetClientX(e) - DOM.getAbsoluteLeft(em);
    int y = DOM.eventGetClientY(e) - DOM.getAbsoluteTop(em);
    if (type == Event.ONMOUSEDOWN) {
      onMouseDown (this, x, y);
    }
    if (type == Event.ONMOUSEMOVE) {
      onMouseMove (this, x, y);
    }
    if (type == Event.ONMOUSEUP) {
      onMouseUp (this, x, y);
    }
    super.onBrowserEvent (e);
  }

  public void onMouseDown(Widget sender, int x, int y) {
System.out.println ("x = " +x);
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
      parent.setPopupPosition(absX - dragStartX, absY - dragStartY);
    }
  }

  public void onMouseUp(Widget sender, int x, int y) {
    dragging = false;
    DOM.releaseCapture(caption.getElement());
  }



}
