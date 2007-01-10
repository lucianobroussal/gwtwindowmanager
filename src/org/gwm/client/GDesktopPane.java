package org.gwm.client;

import java.util.List;

public interface GDesktopPane {

	
    /**
     * Adds a new GInternalFrame to this GDesktopPane and select it if its the 
     * first one.
     * @param internalFrame The GInternalFrame to be added.
     * @return a unique identifier for this frame
     */
    public int addFrame(GInternalFrame internalFrame);
	
    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames();

    /**
     * Permits to retrieve a GInternalFrame by its id.
     * Returns null if there is no GInternalFrame with this id.
     * @param id
     *            The id of the window
     * @return GInternalFrame 
     */
    public GInternalFrame getFrame(int id);

    /**
     * Returns all GInternalFrames currently displayed in the desktop.
     */
    public List getAllFrames();
	

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

    public int getOffsetWidth();

}
