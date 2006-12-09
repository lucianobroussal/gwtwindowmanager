/**
 * 
 */
package org.gwm.client.tests;

/**
 * @author Marcelo Emanoel
 * @since 30/11/2006
 */
public interface WindowListener {
	public void windowResized(WindowEvent evt);
	public void windowClosing(WindowEvent evt);
	public void windowClosed(WindowEvent evt);
	public void windowMaximized(WindowEvent evt);
	public void windowMinimized(WindowEvent evt);
	public void windowMoved(WindowEvent evt);
	public void windowActivated(WindowEvent evt);
}
