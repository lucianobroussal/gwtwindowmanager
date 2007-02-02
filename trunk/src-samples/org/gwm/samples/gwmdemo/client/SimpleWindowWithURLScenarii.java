package org.gwm.samples.gwmdemo.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.Hyperlink;

public class SimpleWindowWithURLScenarii extends AbstractScenarii {

	
	
	public SimpleWindowWithURLScenarii(FramesManager framesManager) {
		super(framesManager);

	}

	public void runScenarii() {
		GInternalFrame window = framesManager.newFrame("");
		window.setWidth(620);
		window.setHeight(335);
		window.setTheme("alphacube");
		window.setCaption("Window with an URL inside");
		window.setUrl("site/demo/simplescenarii.html");
        GwmUtilities.diplayAtScreenCenter(window);

	}

	protected Hyperlink createLink() {
		Hyperlink simpleDemo = new Hyperlink("With URL content", "simple");
		return simpleDemo;
	}
	
	
	
	
	
	
		
}
