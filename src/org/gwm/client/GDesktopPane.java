/**
 * 
 */
package org.gwm.client;

import java.util.List;

import org.gwm.client.impl.DefaultGDesktopManager;

import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author Marcelo Emanoel
 * @since 18/12/2006
 */
public class GDesktopPane extends Composite implements WindowResizeListener{
	private GDesktopManager desktopManager;
	private HTML centerWidget;
	private DockPanel layout;
	private MinimizedButtonBar buttonBar;
	
	private GFrameAdapter adapter = new GFrameAdapter(){
		public void frameMinimized(GFrameEvent evt) {
			minimize(evt.getSource());
		}
	};
	
	public GDesktopPane(){
		initialize();
		setupLayout();
		setupListeners();
	}
	
	private void initialize() {
		setDesktopManager(new DefaultGDesktopManager(this));
		centerWidget = new HTML();
		layout = new DockPanel();
		buttonBar = new MinimizedButtonBar(this);
		
		this.initWidget(layout);
		
		layout.setStyleName("org-gwm-GDesktopPane");
	}
	
	private void setupLayout() {
		this.setSize("100%", "100%");
		layout.setSize("100%", "100%");
		
		layout.add(centerWidget, DockPanel.CENTER);
		layout.setSize("100%", "100%");
		
		layout.add(buttonBar, DockPanel.SOUTH);
		layout.setCellHeight(buttonBar, "30px");
		
	}
	
	private void setupListeners() {
		
	}
	
	/**
	 * Create a button from the window and add it the minimized windows bar.
	 * @param theWindow
	 */
	private void minimize(GInternalFrame theWindow){
		buttonBar.addWindow(theWindow);
//		Button theButton = makeAButtonFromAWindow(theWindow);
//		minimizedWindows.add(theButton);
		
	}
	
	/**
	 * @return the manager
	 */
	public GDesktopManager getDesktopManager() {
		return desktopManager;
	}
	
	
	/**
	 * @param manager the manager to set
	 */
	public void setDesktopManager(GDesktopManager manager) {
		this.desktopManager = manager;
	}
	
	/**
	 * Creates a new GInternalFrame to be used on this GDesktopPane. 
	 * <strong>Important note:</strong> This doesn't add the frame to this 
	 * GDesktopPane, it only give a reference to a new GInternalFrame.
	 * @return
	 */
	public GInternalFrame newFrame(){
		GInternalFrame newFrame = desktopManager.newFrame();
		newFrame.addGFrameListener(adapter);
		return newFrame;
	}
	
	/**
	 * Add a GInternalFrame to this GDesktopPane.
	 * @param internalFrame
	 */
	public void addGInternalFrame(GInternalFrame internalFrame){
		desktopManager.addGInternalFrame(internalFrame);
	}
	
	/**
	 * Add a GInternalFrame to this GDesktopPane.
	 * @param internalFrame The GInternalFrame to be added.
	 * @param selected True to select the GInternalFrame, False otherwise.
	 */
	public void addGInternalFrame(GInternalFrame internalFrame, boolean selected){
		desktopManager.addGInternalFrame(internalFrame, selected);
	}
	
	/**
	 * Add a GInternalFrame to this GDesktopPane.
	 * @param internalFrame The GInternalFrame to be added.
	 * @param selected True to select the GInternalFrame, False otherwise.
	 * @param layer Specify the layer of the GInternalFrame should use on this 
	 * GDesktopPane.
	 */
	public void addGInternalFrame(GInternalFrame internalFrame, boolean selected, int layer){
		desktopManager.addGInternalFrame(internalFrame, selected, layer);
	}
	
	/**
	 * Closes all GInternalFrames contained in this GDesktopPane.
	 */
	public void closeAllInternalFrames(){
		desktopManager.closeAllInternalFrames();
	}
	
	/**
	 * Permits to retrieve a GInternalFrame by its id.
	 * Returns null if there is no GInternalFrame with this id.
	 * @param id
	 *            The id of the window
	 * @return GInternalFrame 
	 */
	public GInternalFrame getGInternalFrame(String id){
		return desktopManager.getGInternalFrame(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#getAllGInternalFrames()
	 */
	public List getAllGInternalFrames() {
		return desktopManager.getAllGInternalFrames();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#getAllGInternalFramesInLayer(int)
	 */
	public List getAllGInternalFramesInLayer(int layer) {
		return desktopManager.getAllGInternalFramesInLayer(layer);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#getDragMode()
	 */
	public int getDragMode() {
		return desktopManager.getDragMode();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#getSelectedFrame()
	 */
	public GInternalFrame getSelectedFrame() {
		return desktopManager.getSelectedFrame();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#layerCount()
	 */
	public int layerCount() {
		return desktopManager.layerCount();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#restore(org.gwm.client.GInternalFrame)
	 */
	public void restore(GInternalFrame minimizedWindow) {
		desktopManager.restore(minimizedWindow);
	}

	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#setDragMode(int)
	 */
	public void setDragMode(int dragMode) {
		desktopManager.setDragMode(dragMode);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#setSelectedGInternalFrame(org.gwm.client.GInternalFrame)
	 */
	public void setSelectedGInternalFrame(GInternalFrame newSelectedFrame) {
		desktopManager.setSelectedGInternalFrame(newSelectedFrame);
	}

	public void onWindowResized(int width, int height) {
		buttonBar.adjustSize();
	}
}