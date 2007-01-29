package org.gwm.samples.gwmdemo.client;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractScenarii implements Scenarii {
	
	protected Object framesManager;

	public AbstractScenarii(Object windowManager){
		//this.framesManager = windowManager;
	}

	public Hyperlink getLink() {
		Hyperlink hyperlink = createLink();
		hyperlink.addClickListener(new ClickListener(){

			public void onClick(Widget sender) {
				runScenarii();
				
			}
			
		});
		return hyperlink;
	}

	public void runScenarii() {

	}
	
	protected abstract Hyperlink createLink();

}