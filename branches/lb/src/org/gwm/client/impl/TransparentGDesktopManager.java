/**
 * 
 */
package org.gwm.client.impl;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GFrameEvent;
import org.gwm.client.GInternalFrame;

/**
 * @author Marcelo Emanoel
 * @since 22/12/2006
 */
public class TransparentGDesktopManager extends DefaultGDesktopManager {

	public TransparentGDesktopManager(GDesktopPane desktopPane) {
		super(desktopPane);
	}
	
	public void frameMinimizing(GFrameEvent evt) {
		captureTheProperties(evt.getSource());		
	}
	
	public void minimize(GInternalFrame internalFrame) {
		internalFrame.fireFrameMinimizing();
		internalFrame.setHeight(internalFrame.getMinimumHeight());
		internalFrame.setWidth(internalFrame.getMinimumWidth());
		internalFrame.fireFrameMinimized();
	}
}
