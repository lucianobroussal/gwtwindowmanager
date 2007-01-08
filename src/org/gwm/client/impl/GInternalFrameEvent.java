package org.gwm.client.impl;

import org.gwm.client.GInternalFrame;


/**
 * This class is an event object and has a GInternalFrame as the source.
 * @author Marcelo Emanoel
 * @since 14/12/2006
 */
public class GInternalFrameEvent {
	private GInternalFrame source;
	
	/**
	 * Build a GInternalFrameEvent from a GInternalFrame as the source.
	 * @param source
	 */
	public GInternalFrameEvent(GInternalFrame source){
		this.source = source;
	}
	
	/**
	 * Returns the source of the event.
	 * @return
	 */
	public GInternalFrame getGInternalFrame(){
		return this.source;
	}
}
