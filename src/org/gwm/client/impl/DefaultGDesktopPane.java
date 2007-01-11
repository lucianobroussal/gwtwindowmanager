/**
 * 
 */
package org.gwm.client.impl;

import java.util.*;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GInternalFrameAdapter;
import org.gwm.client.event.GInternalFrameEvent;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;

/**
 * @author Marcelo Emanoel
 * @since 18/12/2006
 */
public class DefaultGDesktopPane extends DockPanel implements WindowResizeListener, GDesktopPane {

    private HTML centerWidget;
    private IconBar buttonBar;
    private HorizontalPanel iconBar;

    private GInternalFrameAdapter adapter = new GInternalFrameAdapter() {
        public void internalFrameIconified(GInternalFrameEvent evt) {
            minimize(evt.getGInternalFrame());
        }
    };

    private List frames;

    public DefaultGDesktopPane() {
        initialize();
        setupUI();
        setupListeners();
    }

    private void initialize() {
        centerWidget = new HTML();
        buttonBar = new IconBar (this);
        iconBar = new HorizontalPanel ();
        this.frames = new ArrayList();
    }

    private void setupUI() {
        setSize("100%", "90%");
        add(centerWidget, DockPanel.CENTER);

        add(buttonBar, DockPanel.SOUTH);
        setCellHeight(buttonBar, "30px");

        
        setStyleName("org-gwm-GDesktopPane");
    }

    private void setupListeners() {

    }

    /**
     * Create a button from the window and add it the minimized windows bar.
     * 
     * @param theWindow
     */
    private void minimize(GInternalFrame theWindow) {
        buttonBar.addWindow(theWindow);

    }

    public void maximize (GInternalFrame theWindow) {
        theWindow.maximize();
    }

    public void toFront(GInternalFrame internalFrame) {
        // I hope it works :)
        DOM.setIntStyleAttribute(internalFrame.getElement(), "zIndex",
                Integer.MAX_VALUE);
        // TODO What should I put here?
    }

    public void toBack(GInternalFrame internalFrame) {
        DOM.setIntStyleAttribute(internalFrame.getElement(), "zIndex",
                Integer.MIN_VALUE);
    }


    
    public void iconify(GInternalFrame theWindow) {
        theWindow.hide();
        buttonBar.addWindow(theWindow);
        // Button theButton = makeAButtonFromAWindow(theWindow);
        // minimizedWindows.add(theButton);
    }

    public void deIconify (GInternalFrame theWindow) {
        theWindow.setVisible (true);
        theWindow.show(true);
        theWindow.refresh();
    }

    /**
     * Add a GInternalFrame to this GDesktopPane.
     * 
     * @param internalFrame
     */
    public void addFrame(GInternalFrame internalFrame) {
        internalFrame.setParentDesktop (this);
        int spos = (frames.size() + 1) * 30;
        internalFrame.setLocation (spos, spos);
        internalFrame.addInternalFrameListener(adapter);
        frames.add (internalFrame);
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames() {
        for (int i = 0; i < frames.size(); i++) {
            ((GInternalFrame) frames.get(i)).dispose();
        }
    }

    /*
     * Returns a list of all frames maintained by this desktop
     * 
     */
    public List getAllFrames() {
        return frames;
    }

    public void onWindowResized(int width, int height) {
        buttonBar.adjustSize();
    }

    public GInternalFrame getFrame(int id) {
        // TODO Auto-generated method stub
        return null;
    }

}
