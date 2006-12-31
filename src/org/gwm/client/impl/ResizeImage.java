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

public class ResizeImage extends FlowPanel implements MouseListener {

  private int resizeStartX;
  private int resizeStartY;

  private int initialWidth;
  private int initialHeight;
  private boolean resizing;
  private GwtInternalFrame parent;
  private String currentStyle;
  private Label label;

  public ResizeImage (GwtInternalFrame parent) {
    super();
    this.parent = parent;
    buildGui();
    sinkEvents(Event.MOUSEEVENTS);
  }

  private void buildGui() {
    this.currentStyle = parent.getStyle();
    label = new Label("");
    label.addStyleName ("resizeCorner");
    label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    label.addMouseListener (this);
    add (label);
  }

  public void onMouseDown(Widget sender, int x, int y) {
System.out.println ("start resize");
    resizing = true;
    DOM.setCapture(label.getElement());
    resizeStartX = x;
    resizeStartY = y;
  }

  public void onBrowserEvent (Event e) {
    super.onBrowserEvent (e);
  }


  public void onMouseUp(Widget sender, int x, int y) {
    resizing = false;
    DOM.releaseCapture (label.getElement());
  }

  public void onMouseEnter(Widget sender) {
  }

  public void onMouseLeave(Widget sender) {
  }

  public void onMouseMove(Widget sender, int x, int y) {
System.out.println ("mousemove");
    if (resizing) {
/*
      int absX = x + parent.getAbsoluteLeft();
      int absY = y + parent.getAbsoluteTop();
      parent.setPopupPosition(absX - resizeStartX, absY - resizeStartY);
*/

System.out.println ("x = "+x+", resizeStartx = "+resizeStartX);
      int newHeight = parent.getHeight() + y - resizeStartY;
      int newWidth = parent.getWidth() + x - resizeStartX;
System.out.println ("nw = "+newWidth);
      if (newWidth > parent.getMinimumWidth()) {
        parent.setWidth (newWidth);
System.out.println ("REALLY nw = "+newWidth);
        // resizeStartX = x;
      }
      if (newHeight > parent.getMinimumHeight()) {
        parent.setHeight (newHeight);
        // resizeStartY = y;
      }
      // parent.buildGui();
    }
  }

  public boolean onEventPreview (Event evt) {
    return true;
  }

}
