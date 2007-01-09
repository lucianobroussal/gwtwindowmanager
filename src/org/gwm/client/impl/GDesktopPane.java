/**
 * 
 */
package org.gwm.client.impl;

import java.util.*;

import org.gwm.client.GDesktopManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GInternalFrameAdapter;
import org.gwm.client.event.GInternalFrameEvent;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;

/**
 * @author Marcelo Emanoel
 * @since 18/12/2006
 */
public class GDesktopPane extends Composite implements WindowResizeListener, GDesktopManager {

    /**
     * Indicates that the entire contents of the item being dragged should
     * appear inside the desktop pane.
     */
    public static final int LIVE_DRAG_MODE = 0;

    /**
     * Indicates that an outline only of the item being dragged should appear
     * inside the desktop pane.
     */
    public static final int OUTLINE_DRAG_MODE = 1;

    private HTML centerWidget;

    private DockPanel layout;

    private MinimizedButtonBar buttonBar;
    private HorizontalPanel iconBar;

    private GInternalFrameAdapter adapter = new GInternalFrameAdapter() {
        public void internalFrameIconified(GInternalFrameEvent evt) {
            minimize(evt.getGInternalFrame());
        }
    };

    private int dragStyle;

    private GInternalFrame selectedFrame;

    private List frames;

    public GDesktopPane() {
        initialize();
        setupUI();
        setupListeners();
    }

    private void initialize() {
        // setDesktopManager(new DefaultGDesktopManager(this));
        centerWidget = new HTML();
        layout = new DockPanel();
        buttonBar = new MinimizedButtonBar(this);
        iconBar = new HorizontalPanel ();
        this.initWidget(layout);
        this.frames = new ArrayList();
    }

    private void setupUI() {
        this.setSize("100%", "100%");
        layout.setSize("100%", "100%");

        layout.add(centerWidget, DockPanel.CENTER);
        layout.setSize("100%", "100%");

        layout.add(buttonBar, DockPanel.SOUTH);
        layout.setCellHeight(buttonBar, "30px");

        
        layout.setStyleName("org-gwm-GDesktopPane");
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
        // Button theButton = makeAButtonFromAWindow(theWindow);
        // minimizedWindows.add(theButton);

    }

    public void maximize (GInternalFrame theWindow) {
        theWindow.maximize();
    }

    public void toFront(GInternalFrame internalFrame) {
        // I hope it works :)
        DOM.setIntStyleAttribute(internalFrame.getElement(), "zIndex",
                Integer.MAX_VALUE);
        // TODO What should I put here?
    }

    public void toBack(GInternalFrame internalFrame) {
        DOM.setIntStyleAttribute(internalFrame.getElement(), "zIndex",
                Integer.MIN_VALUE);
    }


    
    public void iconify(GInternalFrame theWindow) {
        theWindow.hide();
        buttonBar.addWindow(theWindow);
        // Button theButton = makeAButtonFromAWindow(theWindow);
        // minimizedWindows.add(theButton);
    }

    public void deIconify (GInternalFrame theWindow) {
        theWindow.setVisible (true);
    }

    /**
     * @return the manager
     */
    // public GDesktopManager getDesktopManager() {
        // return desktopManager;
    // }

    /**
     * @param manager
     *            the manager to set
     */
    // public void setDesktopManager(GDesktopManager manager) {
        // this.desktopManager = manager;
    // }

    /**
     * Add a GInternalFrame to this GDesktopPane.
     * 
     * @param internalFrame
     */
    public void addFrame(GInternalFrame internalFrame) {
        internalFrame.setParentDesktop (this);
        int spos = (frames.size() + 1) * 10;
        internalFrame.setLocation (spos, spos);
        internalFrame.addInternalFrameListener(adapter);
        frames.add (internalFrame);
        // desktopManager.addGInternalFrame(internalFrame);
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames() {
        for (int i = 0; i < frames.size(); i++) {
            ((GInternalFrame) frames.get(i)).dispose();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwm.client.GDesktopManager#getAllGInternalFrames()
     */
    public List getAllGInternalFrames() {
        return frames;
    }

    /**
     * Gets the current "dragging style" used by the desktop pane.
     */
    public int getDragMode() {
        return this.dragStyle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwm.client.GDesktopManager#getSelectedFrame()
     */
    public GInternalFrame getSelectedFrame() {
        return selectedFrame;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwm.client.GDesktopManager#setDragMode(int)
     */
    public void setDragMode(int dragMode) {
        this.dragStyle = dragMode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gwm.client.GDesktopManager#setSelectedGInternalFrame(org.gwm.client.GInternalFrame)
     */
    public void setSelectedGInternalFrame(GInternalFrame newSelectedFrame) {
        this.selectedFrame = newSelectedFrame;
        this.selectedFrame.setVisible (true);
        // desktopManager.setSelectedGInternalFrame(newSelectedFrame);
    }

    public void onWindowResized(int width, int height) {
        buttonBar.adjustSize();
    }

    public GInternalFrame getGInternalFrame(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllGInternalFrames() {
        for (int i = 0; i < frames.size(); i++) {
            ((GInternalFrame) frames.get(i)).dispose();
        }
    }



}
