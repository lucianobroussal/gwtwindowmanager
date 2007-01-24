package org.gwm.client.impl;

import java.util.*;

import com.google.gwt.user.client.ui.*;
import org.gwm.client.*;
import org.gwm.client.event.*;

public class DebugWindow extends GwtInternalFrame implements GInternalFrameListener {

    private GDesktopPane pane;
    private List frames;
    private DockPanel dock;
    private VerticalPanel panel;
    private Button clearLog;

    public DebugWindow (GDesktopPane pane) {
        super ("Debug");
        this.pane = pane;
        this.frames = pane.getAllFrames();
        this.dock = new DockPanel();
        this.panel = new VerticalPanel();
        this.clearLog = new Button ("Clear window", new ClickListener () {
            public void onClick (Widget w) {
                clearPanel();
            }
        });
        this.dock.add (panel, DockPanel.CENTER);
        this.dock.add (clearLog, DockPanel.SOUTH);
        setContent (this.dock);
        registerListeners();
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

    public void frameDeiconified(GInternalFrameEvent evt) {
        addEvent (evt, "DeIconified");
    }

    public void frameMoved(GInternalFrameEvent evt) {
        addEvent (evt, "Moved");
    }

    private void addEvent (GInternalFrameEvent evt, String action) {
        final GInternalFrame myFrame = evt.getGInternalFrame();
        String title = myFrame.getCaption();
        Label l = new Label (action+title);
        l.addClickListener (new ClickListener () {
            public void onClick (Widget sender) {
                // myFrame.toFront();
            }
        });
        this.panel.add (l);
    }


}
