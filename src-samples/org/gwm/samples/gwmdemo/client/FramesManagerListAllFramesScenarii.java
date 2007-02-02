package org.gwm.samples.gwmdemo.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.util.GwmUtilities;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FramesManagerListAllFramesScenarii extends AbstractScenarii {

    
    

    public FramesManagerListAllFramesScenarii(FramesManager framesManager) {
        super(framesManager);

    }

    public void runScenarii() {
        GInternalFrame[] windows = framesManager.getAllFrames();
        VerticalPanel vPanel = new VerticalPanel();
        for (int i = 0; i < windows.length; i++) {
            Label label = getCorrespondingFrameOpenedEvent(windows[i], i);
            vPanel.add(label);
            
            

        }
        vPanel.setSpacing(2);
        GInternalFrame listWindow = framesManager.newFrame("Windows list ...");
        
        GwmUtilities.displayAtParentCenter(listWindow);
        listWindow.setContent(vPanel);

    }

    private Label getCorrespondingFrameOpenedEvent(GInternalFrame frame, int i) {
        Label label = new Label((i + 1) + " - " + frame.getCaption());
        DOM.setStyleAttribute(label.getElement(), "backgroundColor",
                getRandomColor());
        DOM.setStyleAttribute(label.getElement(), "color", "white");
        DOM.setStyleAttribute(label.getElement(), "padding", "3px");
        return label;
    }

    protected Hyperlink createLink() {
        Hyperlink simpleDemo = new Hyperlink("List frames", "list_frames");
        return simpleDemo;
    }

    private String getColorBit() {
        return (int) (Math.random() * 10) + "";
    }

    private String getRandomColor() {
        String color = "#";
        for (int i = 0; i < 6; i++) {
            color += getColorBit() + "";
        }
        return color;
    }

  
}
