package org.gwm.client;

import java.util.List;

public interface GDesktopManager {

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
	 * Build a new GInternalFrame
	 * @return
	 */
	public GInternalFrame newFrame();
	
	/**
	 * Adds a new GInternalFrame to this GDesktopPane and select it if its the 
	 * first one.
	 * @param internalFrame The GInternalFrame to be added.
	 */
	public void addGInternalFrame(GInternalFrame internalFrame);
	
	/**
	 * Adds a new GInternalFrame to this GDesktopPane.
	 * @param internalFrame The GInternalFrame to be added.
	 * @param selected True to select the frame and False otherwise.
	 */
	public void addGInternalFrame(GInternalFrame internalFrame, boolean selected);
	
	/**
	 * Add a new Frame to this GDesktopPane on the layer.
	 * @param internalFrame The GInternalFrame to be added.
	 * @param selected True to select the frame, False otherwise.
	 * @param layer The number of the layer of the GInternalFrame.
	 */
	public void addGInternalFrame(GInternalFrame internalFrame, boolean selected, int layer);
	
	/**
	 * Closes all GInternalFrames contained in this GDesktopPane.
	 */
	public void closeAllInternalFrames();

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
	 * Count the layers of this GDesktopPane
	 * @return the number of layers.
	 */
	public int layerCount();
	
	/**
	 * Returns all GInternalFrames currently displayed in the specified layer of
	 * the desktop.
	 */
	public List getAllGInternalFramesInLayer(int layer);

	/**
	 * Gets the current "dragging style" used by the desktop pane.
	 */
	public int getDragMode();
	/**
	 * Returns the selected frame or null if none is selected.
	 * @return
	 */
	public GInternalFrame getSelectedFrame();
	/**
	 * Sets the "dragging style" used by the desktop pane.
	 */
	public void setDragMode(int dragMode);

	/**
	 * Sets the currently active JInternalFrame in this JDesktopPane.
	 */
	public void setSelectedGInternalFrame(GInternalFrame newSelectedFrame);
	
	/**
	 * Restore the minimized window to its original state.
	 * @param minimizedWindow
	 */
	public void restore(GInternalFrame minimizedWindow);
	
    /**
     * Brings current window in front of all others.
     */
    public void toFront(GInternalFrame internalFrame);
    
    /**
     * Maximize a determinated window.
     * @param internalFrame
     */
	public void maximize(GInternalFrame internalFrame);
	
	/**
	 * Minimize a determinated window.
	 * @param internalFrame
	 */
	public void minimize(GInternalFrame internalFrame);

}