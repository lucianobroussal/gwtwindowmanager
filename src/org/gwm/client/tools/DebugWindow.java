package org.gwm.client.tools;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GInternalFrameEvent;
import org.gwm.client.event.GInternalFrameListener;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DebugWindow  implements
        GInternalFrameListener {

    private DockPanel dock;

    private VerticalPanel panel;

    private Button clearLog;
    
    private  GInternalFrame frame;


    public DebugWindow(FramesManager  framesManager) {
        frame  = framesManager.newFrame("Debug Window");
        this.dock = new DockPanel();
        this.panel = new VerticalPanel();
        this.clearLog = new Button("Clear window", new ClickListener() {
            public void onClick(Widget w) {
                clearPanel();
            }
        });
        this.clearLog.addStyleName("gwm_DebugWindow-ClearLogButton");
        this.dock.addStyleName("gwm-DebugWindow");
        this.dock.add(clearLog, DockPanel.NORTH);
        this.dock.add(panel, DockPanel.CENTER);
        this.dock.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        this.dock.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        this.dock.setCellHorizontalAlignment(clearLog,
                HasHorizontalAlignment.ALIGN_CENTER);
        this.dock.setCellVerticalAlignment(clearLog,
                HasVerticalAlignment.ALIGN_TOP);
        frame.setContent(this.dock);
    }


    private void clearPanel() {
        this.dock.remove(panel);
        this.panel = new VerticalPanel();
        this.dock.add(panel, DockPanel.CENTER);
    }

    public void frameResized(GInternalFrameEvent evt) {
        addEvent(evt, "Resized");
    }

    public void frameOpened(GInternalFrameEvent evt) {
        addEvent(evt, "Opened");
    }

    public void frameClosed(GInternalFrameEvent evt) {
        addEvent(evt, "Closed");
    }

    public void frameMaximized(GInternalFrameEvent evt) {
        addEvent(evt, "Maximize");
    }

    public void frameMinimized(GInternalFrameEvent evt) {
        addEvent(evt, "Minimized");
    }

    public void frameIconified(GInternalFrameEvent evt) {
        addEvent(evt, "Iconified");
    }

    public void frameRestored(GInternalFrameEvent evt) {
        addEvent(evt, "Restored");
    }

    public void frameMoved(GInternalFrameEvent evt) {
        addEvent(evt, "Moved");
    }

    private void addEvent(GInternalFrameEvent evt, String action) {
        final GInternalFrame myFrame = evt.getGInternalFrame();
        String title = myFrame.getCaption();
        Label l = new Label(action + " " + title);
        l.addStyleName("gwm-DebugEvent");
        l.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                // myFrame.toFront();
            }
        });
        this.panel.add(l);
    }
    
    public GInternalFrame getUI(){
        return frame;
    }

    
}
