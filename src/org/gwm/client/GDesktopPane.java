package org.gwm.client;

import java.util.List;

public interface GDesktopPane {

	
    /**
     * Adds a new GInternalFrame to this GDesktopPane and select it if its the 
     * first one.
     * @param internalFrame The GInternalFrame to be added.
     */
    public void addFrame(GInternalFrame internalFrame);
    
    /**
     * Remove a GInternalFrame from this GDesktopPane and select it if its the 
     * first one.
     * @param internalFrame The GInternalFrame to be removed.
     */
    public void removeFrame(GInternalFrame internalFrame);
	
    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames();

    /**
     * Returns all GInternalFrames currently displayed in the desktop.
     */
    public List getAllFrames();
	
    /**
     * Minimize a determinated window.
     * @param internalFrame
     */
    public void iconify(GInternalFrame internalFrame);

    /**
     * Restore the minimized window to its original state.
     * @param minimizedWindow
     */
    public void deIconify(GInternalFrame minimizedWindow);
	
    
    /**
     * Sets the currently active JInternalFrame in this JDesktopPane.
     */
    public void setSelectedFrame(GInternalFrame newSelectedFrame);
    
    public GInternalFrame getSelectedFrame();

     
}
