package org.gwm.client;

import java.util.List;

public interface GDesktopManager {

	
	/**
	 * Adds a new GInternalFrame to this GDesktopPane and select it if its the 
	 * first one.
	 * @param internalFrame The GInternalFrame to be added.
	 */
	public void addFrame(GInternalFrame internalFrame);
	
	
	/**
	 * Closes all GInternalFrames contained in this GDesktopPane.
	 */
	public void closeAllGInternalFrames();

	/**
	 * Permits to retrieve a GInternalFrame by its id.
	 * Returns null if there is no GInternalFrame with this id.
	 * @param id
	 *            The id of the window
	 * @return GInternalFrame 
	 */
	public GInternalFrame getGInternalFrame(String id);
	/**
	 * Returns all GInternalFrames currently displayed in the desktop.
	 */
	public List getAllGInternalFrames();
	

	/**
	 * Sets the currently active JInternalFrame in this JDesktopPane.
	 */
	public void setSelectedGInternalFrame(GInternalFrame newSelectedFrame);
	
	/**
	 * Restore the minimized window to its original state.
	 * @param minimizedWindow
	 */
	public void deIconify(GInternalFrame minimizedWindow);
	
    /**
     * Brings current window in front of all others.
     */
    public void toFront(GInternalFrame internalFrame);

    /**
     * Brings current window behind of all others.
     */
    public void toBack(GInternalFrame internalFrame);
    
    /**
     * Maximize a determinated window.
     * @param internalFrame
     */
	public void maximize(GInternalFrame internalFrame);
	
	/**
	 * Minimize a determinated window.
	 * @param internalFrame
	 */
	public void iconify(GInternalFrame internalFrame);

}
