/**
 * 
 */
package org.gwm.client.impl;

/**
 * @author Marcelo Emanoel
 * @since 30/11/2006
 */
public interface GFrameListener {
	public void windowResized(GFrameEvent evt);
	public void windowClosing(GFrameEvent evt);
	public void windowClosed(GFrameEvent evt);
	public void windowMaximized(GFrameEvent evt);
	public void windowMinimized(GFrameEvent evt);
	public void windowMoved(GFrameEvent evt);
	public void windowActivated(GFrameEvent evt);
}
