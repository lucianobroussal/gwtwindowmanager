package org.gwm.client;

/**
 * This class is an event object and has a GInternalFrame as the source.
 * @author Marcelo Emanoel
 * @since 14/12/2006
 */
public class GFrameEvent {
	private GInternalFrame source;
	
	/**
	 * Build a GFrameEvent from a GInternalFrame as the source.
	 * @param source
	 */
	public GFrameEvent(GInternalFrame source){
		this.source = source;
	}
	
	/**
	 * Returns the source of the event.
	 * @return
	 */
	public GInternalFrame getSource(){
		return this.source;
	}
}
