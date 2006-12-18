package org.gwm.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GDesktopPane {

	private GInternalFrame selectedFrame;
	private Map layers;
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
		this.allFrames = new ArrayList();
		this.layers = new HashMap();
	}
	
	/**
	 * Adds a new GInternalFrame to this GDesktopPane and select it if its the 
	 * first one.
	 * @param internalFrame The GInternalFrame to be added.
	 */
	public void addGInternalFrame(GInternalFrame internalFrame){
		addGInternalFrame(internalFrame, allFrames.isEmpty());
	}
	
	/**
	 * Adds a new GInternalFrame to this GDesktopPane.
	 * @param internalFrame The GInternalFrame to be added.
	 * @param selected True to select the frame and False otherwise.
	 */
	public void addGInternalFrame(GInternalFrame internalFrame, boolean selected){
		addGInternalFrame(internalFrame, selected, 0);
	}
	
	/**
	 * Add a new Frame to this GDesktopPane on the layer.
	 * @param internalFrame The GInternalFrame to be added.
	 * @param selected True to select the frame, False otherwise.
	 * @param layer The number of the layer of the GInternalFrame.
	 */
	public void addGInternalFrame(GInternalFrame internalFrame, boolean selected, int layer){
		if(selected){
			selectedFrame = internalFrame;
		}
		allFrames.add(internalFrame);
		Integer theLayerNumber = new Integer(layer);
		if(layers.containsKey(theLayerNumber)){
			List theLayer = (List) layers.get(theLayerNumber);
			theLayer.add(internalFrame);
		}
		else{
			List newLayer = new ArrayList();
			newLayer.add(internalFrame);
			layers.put(theLayerNumber, newLayer);
		}
	}
	
	/**
	 * Closes all GInternalFrames contained in this GDesktopPane.
	 */
	public void closeAllInternalFrames() {
		for (int i = 0; i < allFrames.size(); i++) {
			((GInternalFrame) allFrames.get(i)).destroy();
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
	 * Count the layers of this GDesktopPane
	 * @return the number of layers.
	 */
	public int countLayers(){
		return layers.size();
	}
	
	/**
	 * Returns all GInternalFrames currently displayed in the specified layer of
	 * the desktop.
	 */
	public List getAllGInternalFramesInLayer(int layer) {
		Integer theLayer = new Integer(layer);
		if(layers.containsKey(theLayer)){
			return (List) layers.get(theLayer);
		}
		return new ArrayList();
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