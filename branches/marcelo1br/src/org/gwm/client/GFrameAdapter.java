/**
 * 
 */
package org.gwm.client;

/**
 * A convenience class for use as a GFrameListener that don't need to implement
 * all the methods "useless" at the moment.
 * 
 * @author Marcelo Emanoel
 * @since 18/12/2006
 */
public class GFrameAdapter implements GFrameListener {

	/* (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameActivated(org.gwm.client.GFrameEvent)
	 */
	public void frameActivated(GFrameEvent evt) {}

	/* (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameClosed(org.gwm.client.GFrameEvent)
	 */
	public void frameClosed(GFrameEvent evt) {}

	/* (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameClosing(org.gwm.client.GFrameEvent)
	 */
	public void frameClosing(GFrameEvent evt) {}

	/* (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameMaximized(org.gwm.client.GFrameEvent)
	 */
	public void frameMaximized(GFrameEvent evt) {}

	/* (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameMinimized(org.gwm.client.GFrameEvent)
	 */
	public void frameMinimized(GFrameEvent evt) {}

	/* (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameMoved(org.gwm.client.GFrameEvent)
	 */
	public void frameMoved(GFrameEvent evt) {}

	/* (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameResized(org.gwm.client.GFrameEvent)
	 */
	public void frameResized(GFrameEvent evt) {}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameMaximizing(org.gwm.client.GFrameEvent)
	 */
	public void frameMaximizing(GFrameEvent evt) {}

	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GFrameListener#frameMinimizing(org.gwm.client.GFrameEvent)
	 */
	public void frameMinimizing(GFrameEvent evt) {}

}
