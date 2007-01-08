package org.gwm.samples.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import org.gwm.client.*;
import org.gwm.client.impl.*;

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
