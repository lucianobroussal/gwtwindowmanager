package org.gwm.client.impl;

import org.gwm.client.event.GFrameAdapter;
import org.gwm.client.event.GFrameListener;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;


public class SelectBoxManagerImpl {
    public SelectBoxManagerImpl() {
    }

    
    public void setBlockerVisible(boolean visible) {
    }


    public void setLocation(int top, int left) {
    }


    public void setBlockerSize(int width, int height) {
    }


    public void setBlockerDeepLayer(int i) {
    }


    public void onParentDragEnd(DefaultGFrame parent) {
        setSelectionActive(true);
        Widget content = parent.getContent();
        if(content instanceof Frame){
            content.setVisible(true);
        }
    }


    public void onParentDragStart(DefaultGFrame parent) {
        setSelectionActive(false);
        Widget content = parent.getContent();
        if(content instanceof Frame){
            content.setVisible(false);
        }
    }


    public void removeBlocker() {
    }


    public GFrameListener getFrameListener() {
        return new GFrameAdapter();
    }
    
    public native void  setSelectionActive(boolean active)/*-{
        if(active){
            $doc.body.ondrag = function () { $wnd.alert('drag'); return false; };
            $doc.body.onselectstart = function () { return false; };
        }else{
            $doc.body.ondrag = null;
            $doc.body.onselectstart = null;
        }
    }-*/;


    public Widget getFrameFinalUI(FlexTable ui) {
        return ui;
    }
    
    
}
