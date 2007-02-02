package org.gwm.samples.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.DefaultGDesktopPane;
import org.gwm.client.impl.FramesManagerFactory;
import org.gwm.client.tools.DebugWindow;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ShowGwtFrameSample implements EntryPoint {

    private GInternalFrame gif2;

    private FramesManager framesManager;

    public void onModuleLoad() {
        framesManager = new FramesManagerFactory().createFramesManager();
        testPane();
    }

    private void testPane() {

        GDesktopPane pane = new DefaultGDesktopPane();
        GInternalFrame gif = framesManager.newFrame("LODGON");
        gif.setUrl("http://www.google.com");

        RootPanel root = RootPanel.get("pane");
        // to be done after adding windows!!!! if not JVM crash TO BE FIX
        root.add((Widget) pane);
        pane.addFrame(gif);
        gif.setSize(300, 200);
        gif.setVisible(true);
        gif2 = framesManager.newFrame("GWT");
        gif2.setUrl("http://www.google.com");
        gif2.setContent(new Label("GWT LABEL Content"));
        gif2.setSize(300, 200);
        pane.addFrame(gif2);
        gif2.setVisible(true);
        gif2.setLocation(400, 100);

        GInternalFrame debug = new DebugWindow (framesManager).getUI();
        debug.setSize (300,300);
        debug.setVisible (true);
        pane.addFrame (debug);
        Button button = new Button("Test free Frame :)");
        button.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                GInternalFrame gif = framesManager.newFrame(
                        "http://www.google.com");
                gif.setUrl("http://www.google.com");
                gif.setSize(300, 200);
                gif.setLocation(200, 200);
                gif.setTheme("theme1");
                gif.setVisible(true);
            }

        });
        RootPanel.get().add(button);

    }

}
