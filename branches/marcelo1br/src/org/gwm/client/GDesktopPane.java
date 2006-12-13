package org.gwm.client;

import java.util.ArrayList;
import java.util.List;

import org.gwm.client.impl.DefaultGDesktopManager;

public class GDesktopPane {

	private GInternalFrame selectedFrame;
	private GDesktopManager manager;
	private List allFrames;
	private int dragStyle;

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
	public GDesktopPane(){
		setSelectedGInternalFrame(null);
		setDragMode(LIVE_DRAG_MODE);
		this.manager = new DefaultGDesktopManager(this);
		this.allFrames = new ArrayList();
	}
	
	public void addGInternalFrame(GInternalFrame internalFrame){
		addGInternalFrame(internalFrame, allFrames.isEmpty());
	}
	public void addGInternalFrame(GInternalFrame internalFrame, boolean selected){
		if(selected){
			selectedFrame = internalFrame;
		}
		allFrames.add(internalFrame);
	}
	/**
	 * Closes all GInternalFrames contained in this GDesktopPane.
	 */
	public void closeAllInternalFrames() {
		for (int i = 0; i < allFrames.size(); i++) {
			manager.closeFrame((GInternalFrame) allFrames.get(i));
		}
	}

	/**
	 * Permits to retrieve a GInternalFrame by its id.
	 * Returns null if there is no GInternalFrame with this id.
	 * @param id
	 *            The id of the window
	 * @return GInternalFrame
	 */
	public GInternalFrame getGInternalFrame(String id) {
		if (selectedFrame.getId().equals(id)) {
			return selectedFrame;
		}
		for (int i = 0; i < allFrames.size(); i++) {
			GInternalFrame frame = (GInternalFrame) allFrames.get(i);
			if (frame.getId().equals(id)) {
				return frame;
			}
		}
		return null;
	}

	/**
	 * Returns all GInternalFrames currently displayed in the desktop.
	 */
	public List getAllGInternalFrames() {
		return this.allFrames;
	}

	/**
	 * Returns all GInternalFrames currently displayed in the specified layer of
	 * the desktop.
	 */
	public List getAllGInternalFramesInLayer(int layer) {
		return null;
	}

	/**
	 * Returns the GDesktopManger that handles desktop-specific UI actions.
	 */
	public GDesktopManager getGDesktopManager() {
		return this.manager;
	}

	/**
	 * Gets the current "dragging style" used by the desktop pane.
	 */
	public int getDragMode() {
		return this.dragStyle;
	}

	/**
	 * Returns the selected frame or null if none is selected.
	 * @return
	 */
	public GInternalFrame getSelectedFrame() {
		return this.selectedFrame;
	}

	/**
	 * Sets the GDesktopManger that will handle desktop-specific UI actions.
	 * @param newManager
	 */
	public void setGDesktopManager(GDesktopManager newManager) {
		this.manager = newManager;
	}

	/**
	 * Sets the "dragging style" used by the desktop pane.
	 */
	public void setDragMode(int dragMode) {
		this.dragStyle = dragMode;
	}

	/**
	 * Sets the currently active JInternalFrame in this JDesktopPane.
	 */
	public void setSelectedGInternalFrame(GInternalFrame newSelectedFrame) {
		this.selectedFrame = newSelectedFrame;
	}
}