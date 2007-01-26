package org.gwm.client.tools;

import java.util.*;

import com.google.gwt.user.client.ui.*;
import org.gwm.client.*;
import org.gwm.client.event.*;
import org.gwm.client.impl.*;

public class DebugWindow extends GwtInternalFrame implements GInternalFrameListener {

    private List frames;
    private DockPanel dock;
    private VerticalPanel panel;
    private Button clearLog;
    private static DebugWindow instance;

    public DebugWindow () {
        super ("Debug");
        this.frames = GwtInternalFrame.getAllFrames();
        this.dock = new DockPanel();
        this.panel = new VerticalPanel();
        this.clearLog = new Button ("Clear window", new ClickListener () {
            public void onClick (Widget w) {
                clearPanel();
            }
        });
        this.clearLog.addStyleName (getTheme()+"_clearlog");
        this.dock.addStyleName (getTheme()+"_debugwindow");
        this.dock.add (clearLog, DockPanel.NORTH);
        this.dock.add (panel, DockPanel.CENTER);
        this.dock.setHorizontalAlignment (dock.ALIGN_CENTER);
        this.dock.setVerticalAlignment (dock.ALIGN_TOP);
        this.dock.setCellHorizontalAlignment (clearLog, HasHorizontalAlignment.ALIGN_CENTER);
        this.dock.setCellVerticalAlignment (clearLog, HasVerticalAlignment.ALIGN_TOP);
        setContent (this.dock);
        registerListeners();
        instance = this;
    }

    public static void addFrame(GwtInternalFrame f) { // should change in pull/push/listener
        if (instance != null) {
            instance.frames.add (f);
            f.addInternalFrameListener (instance);
        }
    }

    public static void removeFrame(GwtInternalFrame f) { // should change in pull/push/listener
        if (instance != null) {
            instance.frames.remove (f);
            f.removeInternalFrameListener (instance);
        }
    }

    private void clearPanel () {
        this.dock.remove (panel);
        this.panel = new VerticalPanel();
        this.dock.add (panel, DockPanel.CENTER);
    }

    private void registerListeners () {
        Iterator it = frames.iterator();
        while (it.hasNext()) {
            GInternalFrame f = (GInternalFrame)(it.next());
            f.addInternalFrameListener (this);
        }
    }

    public void frameResized(GInternalFrameEvent evt) {
        addEvent (evt, "Resized");
    }

    public void frameOpened(GInternalFrameEvent evt) {
        addEvent (evt, "Opened");
    }

    public void frameClosed(GInternalFrameEvent evt) {
        addEvent (evt, "Closed");
    }

    public void frameMaximized(GInternalFrameEvent evt) {
        addEvent (evt, "Maximize");
    }

    public void frameMinimized(GInternalFrameEvent evt) {
        addEvent (evt, "Minimized");
    }

    public void frameIconified(GInternalFrameEvent evt) {
        addEvent (evt, "Iconified");
    }

    public void frameRestored(GInternalFrameEvent evt) {
        addEvent (evt, "Restored");
    }

    public void frameMoved(GInternalFrameEvent evt) {
        addEvent (evt, "Moved");
    }

    private void addEvent (GInternalFrameEvent evt, String action) {
        final GInternalFrame myFrame = evt.getGInternalFrame();
        String title = myFrame.getCaption();
        Label l = new Label (action+" "+title);
        l.addStyleName (getTheme()+"_debug");
        l.addClickListener (new ClickListener () {
            public void onClick (Widget sender) {
                // myFrame.toFront();
            }
        });
        this.panel.add (l);
    }


}
