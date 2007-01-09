package org.gwm.samples.client;

import org.gwm.client.*;
import org.gwm.client.impl.*;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;

public class ShowGwtFrameSample implements EntryPoint {

    private DefaultGDesktopPane pane;

    public void onModuleLoad() {
        if (false) {
            testWindow();
            return;
        }
        testPane();
    }

    private void testPane() {
        pane = new DefaultGDesktopPane();
        GInternalFrame gif = new GwtInternalFrame("ssss");
        gif.setContent(new Button("a button"));
        pane.addFrame(gif);
        gif.setVisible(true);
        RootPanel root = RootPanel.get("pane");
        root.add (pane);

    }

    private void testWindow() {
        GInternalFrame gif = new GwtInternalFrame("ssss");
        gif.setContent(new Button("a button"));
        gif.setVisible(true);

    }

}
