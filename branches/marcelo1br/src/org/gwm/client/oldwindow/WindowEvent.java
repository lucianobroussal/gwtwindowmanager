package org.gwm.client.oldwindow;

public class WindowEvent {
	private GWTInternalFrame source;
	
	public WindowEvent(GWTInternalFrame source){
		this.source = source;
	}
	
	public GWTInternalFrame getSource(){
		return this.source;
	}
}
