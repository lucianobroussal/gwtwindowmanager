package org.gwm.samples.client;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.GwtInternalFrame;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;

public class ShowGwtFrameSample implements EntryPoint {

    private GDesktopPane pane;

    public void onModuleLoad() {
        if (true) {
            testWindow();
            return;
        }
        testPane();
    }

    private void testPane() {
        pane = new GDesktopPane();
        GInternalFrame gif = new GwtInternalFrame("1", "ssss");
        gif.setContent(new Button("a button"));
        pane.addFrame(gif);
        gif.setVisible(true);

    }

    private void testWindow() {
        GInternalFrame gif = new GwtInternalFrame("1", "ssss");
        gif.setContent(new Button("a button"));
        gif.setVisible(true);

    }

}
