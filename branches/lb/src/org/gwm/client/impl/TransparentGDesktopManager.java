/**
 * 
 */
package org.gwm.client.impl;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

/**
 * @author Marcelo Emanoel
 * @since 22/12/2006
 */
public class TransparentGDesktopManager extends DefaultGDesktopManager {

	public TransparentGDesktopManager(GDesktopPane desktopPane) {
		super(desktopPane);
	}
	
	public void frameMinimizing(GInternalFrameEvent evt) {
	}
	
	public void iconify(GInternalFrame internalFrame) {
	}
}
