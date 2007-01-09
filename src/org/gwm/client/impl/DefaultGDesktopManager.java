package org.gwm.client.impl;

import java.util.*;

import org.gwm.client.GDesktopManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GInternalFrameEvent;

import com.google.gwt.user.client.DOM;

public class DefaultGDesktopManager {

    private GDesktopPane desktop;

    private static final String HEIGHT = "height";

    private static final String WIDTH = "width";

    private static final String LEFT = "left";

    private static final String TOP = "top";

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

    private List frames;

    /**
     * Constructs a new GDesktopPane with the default settings.
     */
    public DefaultGDesktopManager(GDesktopPane desktopPane) {
        // setSelectedGInternalFrame(null);
        this.desktop = desktopPane;
        this.frames = new ArrayList ();
    }

    /**
     * Adds a new GInternalFrame to this GDesktopPane and select it if its the
     * first one.
     * 
     * @param internalFrame
     *            The GInternalFrame to be added.
     */
    public void addGInternalFrame(GInternalFrame internalFrame) {
        frames.add(internalFrame);
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllGInternalFrames() {
        for (int i = 0; i < frames.size(); i++) {
            ((GInternalFrame) frames.get(i)).dispose();
        }
    }

    /**
     * Returns all GInternalFrames currently displayed in the desktop.
     */
    public List getAllGInternalFrames() {
        return frames;
    }

    /**
     * Returns the selected frame or null if none is selected.
     * 
     * @return
     */
    public GInternalFrame getSelectedFrame() {
        return desktop.getSelectedFrame();
    }

    /**
     * Sets the currently active JInternalFrame in this JDesktopPane.
     */
    public void setSelectedGInternalFrame(GInternalFrame newSelectedFrame) {
        newSelectedFrame.setVisible(true);
        newSelectedFrame.getParentDesktop().setSelectedGInternalFrame(newSelectedFrame);
    }

    /**
     * Restore the window's original state.
     */
    public void deiconifyFrame(GInternalFrame window) {
        if (!window.isVisible()) {
            window.setVisible(true);
        }
    }

    public void internalFrameActivated(GInternalFrameEvent evt) {
    }

    public void internalFrameClosed(GInternalFrameEvent evt) {
    }

    public void internalFrameClosing(GInternalFrameEvent evt) {
    }

    public void internalFrameIconified(GInternalFrameEvent evt) {
    }

    public void maximize(GInternalFrame internalFrame) {
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

    public GInternalFrame getGInternalFrame(String id) {
        // TODO Auto-generated method stub
        return null;
    }



}
