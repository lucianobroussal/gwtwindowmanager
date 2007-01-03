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
public class GwtInternalFrame extends PopupPanel implements GInternalFrame, EventListener, ClickListener  {

  private String id;
  private String title;
  private TopBar topBar;
  private ResizeImage resizeImage;
  private Label caption;
  private FocusPanel mainPanel;
  private Widget myContent;
  private int dragStartX, dragStartY;
  private int resizeStartX, resizeStartY;
  private boolean dragging;
  private static final int DEFAULT_WIDTH  = 200;
  private static final int DEFAULT_HEIGHT = 300;
  private static final String DEFAULT_STYLE = "theme1";
  private Element closeElement;
  private Element minElement;
  private Element maxElement;
  private int maxWidth, maxHeight;
  private int minWidth, minHeight;
  private int width = -1;
  private int height = -1;
  private String currentStyle;
  private boolean closable, maximizable, minimizable, draggable, resizable;
  private boolean maximized, minimized;
  private boolean resizing;
  Label imgTopLeft;
  Label imgTopRight;
  Label imgBotLeft;
  Label imgBotRight;
  private int previousWidth, restoreWidth;
  private int previousHeight, restoreHeight;


  private FlexTable panel = new FlexTable();

  public GwtInternalFrame () {
    this ("unknown");
  }

  public GwtInternalFrame (String id) {
    this.id = id;
    this.currentStyle = DEFAULT_STYLE;
    this.title = "Frame "+id;
    this.myContent = new HTML ("MY CONTENT");
    this.closable = true;
    this.minimizable = true;
    this.maximizable = true;
    this.resizable = true;
    initializeListeners();
    buildGui();
    sinkEvents (Event.ONCLICK | Event.MOUSEEVENTS);
  }

  private void initializeListeners () {
    // resizeHTML = new HTML ("&nbsp;");
    // resizeHTML.addMouseListener (this);
    resizeImage = new ResizeImage(this);
    // resizeImage.addStyleName("lodgon-ResizeWindowImage");
    this.caption = new Label (title);
    this.caption.addStyleName (currentStyle+"_title");
    this.caption.setHorizontalAlignment (HasHorizontalAlignment.ALIGN_CENTER);
    topBar = new TopBar(this);
    this.mainPanel = new FocusPanel();
    this.mainPanel.setWidget (myContent);
    this.mainPanel.addClickListener (this);
    imgTopLeft = new Label();
    imgTopLeft.addStyleName (this.currentStyle+"_nw");
    imgTopRight = new Label();
    imgTopRight.addStyleName (this.currentStyle+"_ne");
    imgBotLeft = new Label();
    imgBotLeft.addStyleName (this.currentStyle+"_sw");
    imgBotRight = new Label();
    imgBotRight.addStyleName (this.currentStyle+"_se");
    this.maxWidth = Window.getClientWidth();
    this.maxHeight = Window.getClientHeight();
    this.minWidth = 240;
    this.minHeight = 40;
  }

  void buildGui() {
    this.panel = new FlexTable();
    if (minimized) {
      showIcon();
      return;
    }
    if (this.width < 0) {
      this.width = DEFAULT_WIDTH;
    }
    if (this.height < 0) {
      this.height = DEFAULT_HEIGHT;
    }
    this.panel.setSize (this.width+"px", this.height+"px");
    panel.setWidget (0, 0, imgTopLeft);
    panel.setWidget(0, 1, topBar);
    panel.getCellFormatter().setStyleName(0,1, currentStyle+ "_n");
    panel.setWidget (0, 2, imgTopRight);
    panel.setWidget (2, 0, imgBotLeft);
    panel.setWidget (2, 2, imgBotRight);
    panel.setWidget (1, 1, myContent);
    panel.getCellFormatter().setStyleName(1,1, currentStyle+ "_content");
    panel.setHTML(1, 0, "&nbsp;");
    panel.getCellFormatter().setStyleName(1,0, currentStyle+ "_w");
    panel.setHTML(1, 2, "&nbsp;");
    panel.getCellFormatter().setStyleName(1,2, currentStyle+ "_e");
  
    panel.setHTML(2, 0, "&nbsp;");
    panel.getCellFormatter().setStyleName(2,0, currentStyle+ "_sw");
  
    panel.setHTML(2, 1, "&nbsp;");
    panel.getCellFormatter().setStyleName(2,1, currentStyle+ "_s");
  
    if (resizable) {
      panel.setWidget(2, 2, resizeImage);
    }
    else {
      panel.getCellFormatter().setStyleName(2,2, currentStyle+ "_se");
      panel.setHTML(2, 2, "&nbsp;");
    }
    panel.getCellFormatter().setHeight(1, 1, "100%");
    panel.getCellFormatter().setWidth(1, 1, "100%");
    panel.getCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
    panel.setBorderWidth(0);
    panel.setCellPadding(0);
    panel.setCellSpacing(0);
    setStyleName("gwt-DialogBox");
    super.setWidget (panel);
  }

  private void showIcon () {
    Label l = new Label (title);
    Label i = new Label ("");
    l.addMouseListener (topBar);
    i.addClickListener (new ClickListener() {
        public void onClick (Widget sender) {
          GwtInternalFrame.this.minimized = false;
          buildGui();
        }
      }
    );
    i.addStyleName ("icon_button");
    l.addStyleName ("icon_title");
    // this.panel.setSize (this.minWidth+"px", this.minHeight+"px");
    panel.setWidget (0, 0, i);
    panel.setWidget (0, 1, l);
    panel.setBorderWidth(0);
    panel.setCellPadding(0);
    panel.setCellSpacing(0);
    super.setWidget (panel);
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
System.out.println ("ready to show");
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
    this.minimized = !this.minimized;
    buildGui();
  }

  public void maximize () {
    if (!this.maximized) {
      this.setPopupPosition (0,0);
      this.previousWidth = getWidth();
      this.previousHeight = getHeight();
      this.width = maxWidth;
      this.height = maxHeight;
      this.maximized = true;
    }
    else {
      this.width = previousWidth;
      this.height = previousHeight;
      this.maximized = false;
    }
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
    return this.maximized;
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
    this.width = width;
    panel.setWidth (width+"px");
  }

  public int getWidth () {
    return this.width;
  }

  public void setHeight(int height) {
    this.height = height;
    panel.setHeight (height+"px");
  }

  public int getHeight () {
    return this.height;
  }

  public void setMinimumWidth(int minWidth) {
    this.minWidth = minWidth;
  }

  public int getMinimumWidth () {
    return this.minWidth;
  }

  public void setMinimumHeight(int minHeight) {
    this.minHeight = minHeight;
  }

  public int getMinimumHeight () {
    return this.minHeight;
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

  public String getCaption () {
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

  public void onBrowserEvent(Event event) {
    Element target = DOM.eventGetTarget (event);
    int type = DOM.eventGetType (event);
    if (type == Event.ONCLICK) {
      Element myself = DOM.getParent (myContent.getElement());
      if (myself.equals (target)) {
        this.hide();
        this.show();
      }
      return;
    }
  }

/*
  public void onMouseDown(Widget sender, int x, int y) {
System.out.println ("MOUSEDOWN");
    resizing = true;
    DOM.setCapture(resizeHTML.getElement());
    resizeStartX = x;
    resizeStartY = y;
  }

  public void onMouseEnter(Widget sender) {
  }

  public void onMouseLeave(Widget sender) {
  }

  public void onMouseMove(Widget sender, int x, int y) {
System.out.println ("MOUSEMOVE");
    if (resizing) {
int newHeight = this.resizeHTML.getAbsoluteTop() + y - this.panel.getAbsoluteTop();
System.out.println ("x = "+x+", y = "+y+" top = "+this.panel.getAbsoluteTop()+", left = "+this.panel.getAbsoluteLeft());
int newWidth = this.resizeHTML.getAbsoluteLeft() + x - this.panel.getAbsoluteLeft();
      // int newWidth = this.width + x - resizeStartX;
      // int newHeight = this.height + y - resizeStartY;
System.out.println ("newwidth = "+newWidth);
      if (newWidth > minWidth) {
        // this.width = this.width + x - resizeStartX;
        // this.width = newWidth;
        // resizeStartX = x;
      }
      if (newHeight > minHeight) {
        // this.height = this.height + y - resizeStartY;
        // this.height = newHeight;
        // resizeStartY = y;
      }
int absX = x + getAbsoluteLeft();
int absY = y + getAbsoluteTop();
this.setPopupPosition (absX-resizeStartX, absY-resizeStartY);
      // buildGui();
    }
  }

  public void onMouseUp(Widget sender, int x, int y) {
System.out.println ("MOUSEUP");
    resizing = false;
Window.alert ("Release capture");
    DOM.releaseCapture(resizeHTML.getElement());
    buildGui();
  }
*/

  public void onClick (Widget sender) {
System.out.println ("Clicked on "+this.title);
    this.hide();
    this.show();
  }

  public boolean onEventPreview (Event evt) {
    boolean answer = super.onEventPreview(evt);
    return true;
  }

/*
  class ResizeImage extends Label {
    private int dragStartX;
    private int dragStartY;

    private int initialWidth;
    private int initialHeight;

    public ResizeImage() {
      setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
      sinkEvents(Event.MOUSEEVENTS);
    }

    public void onBrowserEvent(Event e) {
System.out.println ("RESIZEIMG GOT EVENT ");
      Element target = DOM.eventGetTarget(e);
      int x = DOM.eventGetClientX(e);
      int y = DOM.eventGetClientY(e);
      int type = DOM.eventGetType (e);
      if (Event.ONMOUSEDOWN == type) {
        resizing = true;
        DOM.setCapture(this.getElement());
        resizeStartX = x;
        resizeStartY = y;
      }
      if (Event.ONMOUSEUP == type) {
        resizing = false;
Window.alert ("release capture");
        DOM.releaseCapture(this.getElement());
        buildGui();
      }
      if (Event.ONMOUSEMOVE == type) {
        if (resizing) {
          int newHeight = GwtInternalFrame.this.height + y - resizeStartY;
          int newWidth = GwtInternalFrame.this.width + x - resizeStartX;
          if (newWidth > minWidth) {
            GwtInternalFrame.this.width = newWidth;
            resizeStartX = x;
          }
          if (newHeight > minHeight) {
            GwtInternalFrame.this.height = newHeight;
            resizeStartY = y;
          }
          int absX = x + getAbsoluteLeft();
          int absY = y + getAbsoluteTop();
          buildGui();
        }
      }
    }
  }
*/

  public void fireFrameMaximized() {
  }

  public void fireFrameMinimized() {
  }

  public void fireFrameMaximizing() {
  }

  public void fireFrameMinimizing() {
  }

  public void setMinimized (boolean v) {
  }

  public void setMaximized (boolean v) {
  }

  public boolean isMaximizable () {
    return false;
  }

  public boolean isMinimizable () {
    return false;
  }

  public int getLeft () {
    return 0;
  }

  public int getTop () {
    return 0;
  }

  public int getMaximumWidth () {
    return 0;
  }

  public int getMaximumHeight () {
    return 0;
  }

  public Widget getContent () {
    return null;
  }

  // public void setContent (Widget c) {
  // }

  public void addGFrameListener (GFrameListener l) {
  }

  public void removeGFrameListener (GFrameListener l) {
  }

  public boolean isModal () {
    return false;
  }

  public void setModal (boolean v) {
  }

  public GDesktopPane getParentDesktop () {
    return null;
  }

}
