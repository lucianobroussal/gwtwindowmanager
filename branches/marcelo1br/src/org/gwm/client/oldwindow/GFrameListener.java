/**
 * 
 */
package org.gwm.client.oldwindow;

/**
 * This is the interface should be implemented for those objects who want to
 * know about the events of a GInternalFrame.
 * 
 * @author Marcelo Emanoel
 * @since 30/11/2006
 */
public interface GFrameListener {
	/**
	 * This method tells the listener when GInternalFrame is resized
	 * 
	 * @param evt
	 *            the event containing the source GInternalFrame.
	 */
	public void frameResized(GFrameEvent evt);

	/**
	 * This method tells the listener when the GInternalFrame is about to close.
	 * Generally this is the point where you stop the closing sequence.
	 * 
	 * @param evt
	 *            the event containing the source GInternalFrame.
	 */
	public void frameClosing(GFrameEvent evt);

	/**
	 * This method is called when the GInternalFrame has been closed.
	 * 
	 * @param evt
	 *            The event containing the source GInternalFrame.
	 */
	public void frameClosed(GFrameEvent evt);

	/**
	 * This method is called when the GInternalFrame has been maximized.
	 * 
	 * @param evt
	 *            The event containing the source GInternalFrame.
	 */
	public void frameMaximized(GFrameEvent evt);

	/**
	 * This method is called when the GInternalFrame has been minimized.
	 * 
	 * @param evt
	 *            The event containing the source GInternalFrame.
	 */
	public void frameMinimized(GFrameEvent evt);

	/**
	 * When the GInternalFrame is moved either horizontally or vertically this
	 * method is called.
	 * 
	 * @param evt
	 *            The event containing the source GInternalFrame.
	 */
	public void frameMoved(GFrameEvent evt);

	/**
	 * When the GInternalFrame is built and just after the show() method is
	 * called.
	 * 
	 * @param evt
	 *            The event containing the source GInternalFrame.
	 */
	public void frameActivated(GFrameEvent evt);
}
