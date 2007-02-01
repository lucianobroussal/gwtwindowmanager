package org.gwm.samples.gwmdemo.client;

import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.GwtInternalFrame;
import org.gwm.client.tools.DebugWindow;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.ui.Hyperlink;

public class EventScenarii extends AbstractScenarii {

	public EventScenarii(Object windowManager) {
		super(windowManager);

	}

	public void runScenarii() {
		GInternalFrame window = new GwtInternalFrame("Play with me ");
		window.setWidth(530);
		window.setHeight(250);
		window.setTheme("alphacube");
		window.setCaption("Window with an HTML text inside");
        GwmUtilities.displayAtParentCenter(window);
		window.setContent("<img src='images/logo-mini.png' >"
				+ "Play with the win to see the windows events fired!"
		);
        
        
        DebugWindow debugWindow = new DebugWindow();
        debugWindow.setLocation(window.getTop(),window.getLeft() +  window.getWidth());
        debugWindow.setVisible(true);
        window.addInternalFrameListener(debugWindow);
        

	}

	protected Hyperlink createLink() {
		Hyperlink simpleDemo = new Hyperlink("Frame events",
				"frame_events");
		return simpleDemo;
	}

}
