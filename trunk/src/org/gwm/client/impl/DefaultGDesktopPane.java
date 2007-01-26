/**
 * 
 */
package org.gwm.client.impl;

import java.util.*;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GInternalFrameAdapter;
import org.gwm.client.event.GInternalFrameEvent;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;

/**
 * @author Marcelo Emanoel
 * @since 18/12/2006
 */
public class DefaultGDesktopPane extends Composite implements WindowResizeListener, GDesktopPane {

    private AbsolutePanel frameContainer;
    private DockPanel desktopWidget;
    private IconBar buttonBar;

    private GInternalFrameAdapter adapter = new GInternalFrameAdapter() {
        public void internalFrameIconified(GInternalFrameEvent evt) {
            minimize(evt.getGInternalFrame());
        }
    };

    private List frames;

    public DefaultGDesktopPane() {
        initialize();
        setupUI();
        setupListeners();
    }

    private void initialize() {
        this.frames = new ArrayList();
    }

    private void setupUI() {
        desktopWidget = new DockPanel();
        frameContainer = new AbsolutePanel();
        buttonBar = new IconBar (this);
        desktopWidget.add(frameContainer, DockPanel.CENTER);
        frameContainer.setStyleName("gwm-GDesktopPane-FrameContainer");

        desktopWidget.add(buttonBar, DockPanel.SOUTH);
        desktopWidget.setCellHeight(buttonBar, "30px");
        
        initWidget(desktopWidget);
        setStyleName("gwm-GDesktopPane");
        
    }

    private void setupListeners() {

    }

    /**
     * Create a button from the window and add it the minimized windows bar.
     * 
     * @param theWindow
     */
    private void minimize(GInternalFrame theWindow) {
        buttonBar.addWindow(theWindow);

    }

    public void maximize (GInternalFrame theWindow) {
        //TODO Auto-generated method stub
    }

        
    public void iconify(GInternalFrame theWindow) {
        theWindow.setVisible(false);
        buttonBar.addWindow(theWindow);
    }

    public void deIconify (GInternalFrame theWindow) {
        theWindow.setVisible (true);
        ((GwtInternalFrame)theWindow).fireFrameRestored();
    }

    /**
     * Add a GInternalFrame to this GDesktopPane.
     * 
     * @param internalFrame
     */
    public void addFrame(GInternalFrame internalFrame) {
        internalFrame.setParentDesktop (this);
        int spos = (frames.size() + 1) * 30;
        frameContainer.add((GwtInternalFrame)internalFrame, frameContainer.getAbsoluteLeft() + spos, frameContainer.getAbsoluteTop() + spos);
        internalFrame.setLocation(frameContainer.getAbsoluteLeft() + spos, frameContainer.getAbsoluteTop() + spos);
        internalFrame.addInternalFrameListener(adapter);
        frames.add (internalFrame);
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames() {
        for (int i = 0; i < frames.size(); i++) {
            ((GInternalFrame) frames.get(i)).close();
        }
    }

    /*
     * Returns a list of all frames maintained by this desktop
     * 
     */
    public List getAllFrames() {
        return frames;
    }

    public void onWindowResized(int width, int height) {
        buttonBar.adjustSize();
    }


    public GInternalFrame getSelectedFrame() {
        // TODO Auto-generated method stub
        return null;
    }

    public void removeFrame(GInternalFrame internalFrame) {
        // TODO Auto-generated method stub
        
    }

    public void setSelectedFrame(GInternalFrame newSelectedFrame) {
        // TODO Auto-generated method stub
        
    }
    
    public void setWidgetPosition(GwtInternalFrame frame, int left, int top){
        frameContainer.setWidgetPosition(frame, left, top);
    }

}
