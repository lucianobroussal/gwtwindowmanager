package org.gwm.client.test;

public class WindowEvent {
	private CoreWindow source;
	
	public WindowEvent(CoreWindow source){
		this.source = source;
	}
	
	public CoreWindow getSource(){
		return this.source;
	}
}
