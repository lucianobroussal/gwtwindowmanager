package org.gwm.client.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwm.client.GDesktopManager;
import org.gwm.client.GDesktopPane;
import org.gwm.client.GFrameEvent;
import org.gwm.client.GFrameListener;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.DOM;

public class DefaultGDesktopManager implements GDesktopManager, GFrameListener{

	private GInternalFrame selectedFrame;
	private Map layers;
	private Map windowConfig; 
	private List allFrames;
	private int dragStyle;
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
	public DefaultGDesktopManager(GDesktopPane desktopPane){
		setSelectedGInternalFrame(null);
		setDragMode(LIVE_DRAG_MODE);
		this.allFrames = new ArrayList();
		this.layers = new HashMap();
		this.windowConfig = new HashMap();
		this.desktop = desktopPane;
	}
	
	/**
	 * Build a new GInternalFrame.
	 */
	public GInternalFrame newFrame() {
		return new DefaultWidgetInternalFrame("", desktop);
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
		internalFrame.addGFrameListener(this);
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
	public int layerCount(){
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
	
	/**
	 * Restore the window's original state.
	 */
	public void restore(GInternalFrame window) {
		Map configurations = (Map) windowConfig.get(window.getId());
		int width = ((Integer)configurations.get(WIDTH)).intValue();
		int height = ((Integer)configurations.get(HEIGHT)).intValue();
		int left = ((Integer)configurations.get(LEFT)).intValue();
		int top = ((Integer)configurations.get(TOP)).intValue();
		
		if(!window.isVisible()){
			window.show(window.isModal());
		}
		window.setLocation(top, left);
		window.setSize(width, height);
		windowConfig.remove(window);
	}

	public void frameActivated(GFrameEvent evt) {}
	public void frameClosed(GFrameEvent evt) {}
	public void frameClosing(GFrameEvent evt) {}
	public void frameMinimized(GFrameEvent evt) {}
	public void frameMoved(GFrameEvent evt) {}
	public void frameResized(GFrameEvent evt) {}
	
	public void frameMaximizing(GFrameEvent evt) {
		captureTheProperties(evt.getSource());
	}

	public void frameMinimizing(GFrameEvent evt) {
		GInternalFrame source = evt.getSource();
		source.setVisible(false);
		captureTheProperties(source);
	}
	
	public void frameMaximized(GFrameEvent evt) {
//		GInternalFrame source = evt.getSource();
//		source.hide();
	}
	/**
	 * Capture the properties of a GInternalFrame and stores on the map.
	 * @param evt
	 */
	private void captureTheProperties(GInternalFrame source) {
		int height = source.getHeight();
		int width = source.getWidth();
		int left = source.getLeft();
		int top = source.getTop();
		Map properties = new HashMap();
		properties.put(HEIGHT, new Integer(height));
		properties.put(WIDTH, new Integer(width));
		properties.put(LEFT, new Integer(left));
		properties.put(TOP, new Integer(top));
		
		windowConfig.put(source.getId(), properties);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#maximize()
	 */
	public void maximize(GInternalFrame internalFrame) {
		if(internalFrame.isMaximizable()){
			internalFrame.fireFrameMaximizing();
			
			internalFrame.setLocation(desktop.getAbsoluteTop(), 0);
			internalFrame.setHeight(desktop.getOffsetHeight() - 30);
			internalFrame.setWidth(desktop.getOffsetWidth());
			internalFrame.setMaximized(true);
			internalFrame.setMinimized(false);
			
			internalFrame.fireFrameMaximized();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#minimize()
	 */
	public void minimize(GInternalFrame internalFrame) {
		if(internalFrame.isMinimizable()){
			internalFrame.fireFrameMinimizing();
			
//			internalFrame.setLocation(desktop.getOffsetHeight() - internalFrame.getMinimumHeight(), 0);
//			internalFrame.setHeight(internalFrame.getMinimumHeight());
			internalFrame.setMinimized(true);
			internalFrame.setMaximized(false);
			
			internalFrame.fireFrameMinimized();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#toFront()
	 */
	public void toFront(GInternalFrame iFrame) {
		//I hope it works :)
		DOM.setIntStyleAttribute(iFrame.getElement(), "zIndex", Integer.MAX_VALUE);
		// TODO What should I put here?
	}
}