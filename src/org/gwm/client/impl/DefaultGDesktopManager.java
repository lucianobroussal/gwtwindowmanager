/**
 * 
 */
package org.gwm.client.impl;

import org.gwm.client.GDesktopManager;
import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

/**
 * @author Marcelo Emanoel
 * @since 10/12/2006
 */
public class DefaultGDesktopManager implements GDesktopManager {

//	private GDesktopPane desktop;
	
	public DefaultGDesktopManager(GDesktopPane pane) {
//		this.desktop = pane;
	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#activateFrame(org.gwm.client.GInternalFrame)
	 */
	public void activateFrame(GInternalFrame f) {
//		f.toFront();
	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#beginDraggingFrame(org.gwm.client.GInternalFrame)
	 */
	public void beginDraggingFrame(GInternalFrame f) {
//		f.startDragin();
	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#beginResizingFrame(org.gwm.client.GInternalFrame, int)
	 */
	public void beginResizingFrame(GInternalFrame f, int direction) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#closeFrame(org.gwm.client.GInternalFrame)
	 */
	public void closeFrame(GInternalFrame f) {
//		f.close();
	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#deactivateFrame(org.gwm.client.GInternalFrame)
	 */
	public void deactivateFrame(GInternalFrame f) {
//		f.back();
	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#deiconifyFrame(org.gwm.client.GInternalFrame)
	 */
	public void deiconifyFrame(GInternalFrame f) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#dragFrame(org.gwm.client.GInternalFrame, int, int)
	 */
	public void dragFrame(GInternalFrame f, int newX, int newY) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#endDraggingFrame(org.gwm.client.GInternalFrame)
	 */
	public void endDraggingFrame(GInternalFrame f) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#endResizingFrame(org.gwm.client.GInternalFrame)
	 */
	public void endResizingFrame(GInternalFrame f) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#iconifyFrame(org.gwm.client.GInternalFrame)
	 */
	public void iconifyFrame(GInternalFrame f) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#maximizeFrame(org.gwm.client.GInternalFrame)
	 */
	public void maximizeFrame(GInternalFrame f) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#minimizeFrame(org.gwm.client.GInternalFrame)
	 */
	public void minimizeFrame(GInternalFrame f) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#openFrame(org.gwm.client.GInternalFrame)
	 */
	public void openFrame(GInternalFrame f) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#resizeFrame(org.gwm.client.GInternalFrame, int, int, int, int)
	 */
	public void resizeFrame(GInternalFrame f, int newX, int newY, int newWidth,
			int newHeight) {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.gwm.client.GDesktopManager#setBoundsForFrame(org.gwm.client.GInternalFrame, int, int, int, int)
	 */
	public void setBoundsForFrame(GInternalFrame f, int newX, int newY,
			int newWidth, int newHeight) {

		// TODO Auto-generated method stub

	}

}
