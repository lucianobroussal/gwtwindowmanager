package org.gwm.client.impl;

import java.util.List;

import org.gwm.client.GDesktopManager;
import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.DOM;

public class DefaultGDesktopManager implements GDesktopManager
         {

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

    /**
     * Constructs a new GDesktopPane with the default settings.
     */
    public DefaultGDesktopManager(GDesktopPane desktopPane) {
        setSelectedGInternalFrame(null);
        this.desktop = desktopPane;
    }

    /**
     * Adds a new GInternalFrame to this GDesktopPane and select it if its the
     * first one.
     * 
     * @param internalFrame
     *            The GInternalFrame to be added.
     */
    public void addGInternalFrame(GInternalFrame internalFrame) {
        desktop.getAllFrames().add(internalFrame);
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllGInternalFrames() {
        List allFrames = desktop.getAllFrames();
        for (int i = 0; i < allFrames.size(); i++) {
            ((GInternalFrame) allFrames.get(i)).dispose();
        }
    }

    /**
     * Returns all GInternalFrames currently displayed in the desktop.
     */
    public List getAllGInternalFrames() {
        return desktop.getAllFrames();
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
        if (internalFrame.isMaximizable()) {
            internalFrame.getParentDesktop();
            internalFrame.setLocation(desktop.getAbsoluteTop(), 0);
            internalFrame.setHeight(desktop.getOffsetHeight() - 30);
            internalFrame.setWidth(desktop.getOffsetWidth());
            internalFrame.setMaximized(true);
            internalFrame.setIconified(false);
        }
    }

     public void iconify(GInternalFrame internalFrame) {
        internalFrame.hide();
        internalFrame.getParentDesktop().addInTaskBar(internalFrame);
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
