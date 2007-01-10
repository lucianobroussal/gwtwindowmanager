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
public class DefaultGDesktopPane extends Composite implements WindowResizeListener, GDesktopPane {

    /**
     * Indicates that the entire contents of the item being dragged should
     * appear inside the desktop pane.
     */
    public static final int LIVE_DRAG_MODE = 0;

    /**
     * Indicates that an outline only of the item being dragged should appear
     * inside the desktop pane.
     */
    public static final int OUTLINE_DRAG_MODE = 1;

    private HTML centerWidget;

    private DockPanel layout;

    private IconBar buttonBar;
    private HorizontalPanel iconBar;

    private int uniqueId = 0;

    private GInternalFrameAdapter adapter = new GInternalFrameAdapter() {
        public void internalFrameIconified(GInternalFrameEvent evt) {
            minimize(evt.getGInternalFrame());
        }
    };

    private int dragStyle;

    private GInternalFrame selectedFrame;

    private List frames;
    private HashMap frameMap;

    public DefaultGDesktopPane() {
        initialize();
        setupUI();
        setupListeners();
    }

    private void initialize() {
        centerWidget = new HTML();
        layout = new DockPanel();
        // buttonBar = new MinimizedButtonBar(this);
        buttonBar = new IconBar (this);
        iconBar = new HorizontalPanel ();
        this.initWidget(layout);
        this.frames = new ArrayList();
        this.frameMap = new HashMap();
    }

    private void setupUI() {
        this.setSize("100%", "100%");
        layout.setSize("100%", "100%");

        layout.add(centerWidget, DockPanel.CENTER);
        layout.setSize("100%", "100%");

        layout.add(buttonBar, DockPanel.SOUTH);
        layout.setCellHeight(buttonBar, "30px");

        
        layout.setStyleName("org-gwm-GDesktopPane");
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
System.out.println ("restore "+theWindow);
        theWindow.setVisible (true);
theWindow.show(true);
        theWindow.refresh();
    }

    /**
     * Add a GInternalFrame to this GDesktopPane.
     * 
     * @param internalFrame
     */
    public int addFrame(GInternalFrame internalFrame) {
        uniqueId ++;
        internalFrame.setParentDesktop (this);
        int spos = (frames.size() + 1) * 10;
        internalFrame.setLocation (spos, spos);
        internalFrame.addInternalFrameListener(adapter);
        frames.add (internalFrame);
        frameMap.put (new Integer (uniqueId), internalFrame);
        return uniqueId;
    }

    /**
     * Closes all GInternalFrames contained in this GDesktopPane.
     */
    public void closeAllFrames() {
        for (int i = 0; i < frames.size(); i++) {
            ((GInternalFrame) frames.get(i)).dispose();
        }
        this.frameMap = new HashMap();
    }

    /*
     * (non-Javadoc)
     * 
     */
    public List getAllFrames() {
        return frames;
    }

    /**
     * Gets the current "dragging style" used by the desktop pane.
     */
    public int getDragMode() {
        return this.dragStyle;
    }

    /*
     * (non-Javadoc)
     * 
     */
    public GInternalFrame getSelectedFrame() {
        return selectedFrame;
    }

    /*
     * (non-Javadoc)
     * 
     */
    public void setDragMode(int dragMode) {
        this.dragStyle = dragMode;
    }

    /*
     * (non-Javadoc)
     * 
     */
    public void setSelectedGInternalFrame(GInternalFrame newSelectedFrame) {
        this.selectedFrame = newSelectedFrame;
        this.selectedFrame.setVisible (true);
    }

    public void onWindowResized(int width, int height) {
        buttonBar.adjustSize();
    }

    public GInternalFrame getFrame(int id) {
        // TODO Auto-generated method stub
        return null;
    }

}
