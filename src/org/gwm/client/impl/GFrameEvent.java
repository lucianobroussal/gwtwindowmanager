package org.gwm.client.impl;

public class GFrameEvent {
	private GFrame source;
	
	public GFrameEvent(GFrame source){
		this.source = source;
	}
	
	public GFrame getSource(){
		return this.source;
	}
}
