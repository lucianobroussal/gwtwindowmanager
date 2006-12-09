package org.gwm.client.oldwindow;

public class WindowEvent {
	private CoreWindow source;
	
	public WindowEvent(CoreWindow source){
		this.source = source;
	}
	
	public CoreWindow getSource(){
		return this.source;
	}
}
