package org.gwm.samples.client;

import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.GDesktopPane;
import org.gwm.client.impl.GwtInternalFrame;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;

public class ShowGwtFrameSample implements EntryPoint {

    private GDesktopPane pane;

    public void onModuleLoad() {
        if (false) {
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
        RootPanel root = RootPanel.get("pane");
        root.add (pane);

    }

    private void testWindow() {
        GInternalFrame gif = new GwtInternalFrame("1", "ssss");
        gif.setContent(new Button("a button"));
        gif.setVisible(true);

    }

}
