package org.gwm.client.impl;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

public class FrameDragControllerImpl implements EventPreview {
    private DefaultGFrame frame;
    private Widget dragHandle;

    public FrameDragControllerImpl() {

    }

    public void setFrame(DefaultGFrame parent) {
       this.frame = parent;
        
    }

    public void setDragHandleWidget(Widget dragHandle) {
        this.dragHandle = dragHandle;
        
    }

    public boolean onEventPreview(Event arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public void onMouseDown(Widget arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    public void onMouseEnter(Widget arg0) {
        // TODO Auto-generated method stub
        
    }

    public void onMouseLeave(Widget arg0) {
        // TODO Auto-generated method stub
        
    }

    public void onMouseMove(Widget arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    public void onMouseUp(Widget arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }
     public boolean isFFPlatform(){
         return true;
     }
}
