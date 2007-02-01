package org.gwm.client.impl;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import org.gwm.client.*;
import java.util.*;

public class IconBar extends FlowPanel {

    GDesktopPane parent;
    Map buttonFrame;
    Map buttonIcon;

    public IconBar (GDesktopPane parent) {
        super ();
        this.parent = parent;
        this.buttonFrame = new HashMap();
        this.buttonIcon = new HashMap();
    }

    public void addWindow (GInternalFrame gframe) {
        GwtInternalFrame frame = (GwtInternalFrame)(gframe);
        HorizontalPanel icon = new HorizontalPanel();
        icon.addStyleName (frame.getTheme()+"_topBar_iconButton");
        Label label = new Label (frame.getCaption());
        label.addStyleName (frame.getTheme()+"_topBar_icon");
        Label restoreButton = new Label("");
        restoreButton.setStyleName(frame.getTheme() + "_topBar_restore");
        icon.add (label);
        icon.add (restoreButton);
        buttonFrame.put (restoreButton, frame);
        buttonIcon.put (restoreButton, icon);
        this.add (icon);
        restoreButton.addClickListener (new ClickListener() {
            public void onClick (Widget sender) {
System.out.println ("CLICKicon");
                GInternalFrame myFrame = (GInternalFrame)buttonFrame.get (sender);
                buttonFrame.remove (sender);
                HorizontalPanel hpicon = (HorizontalPanel)buttonIcon.get (sender);
                remove (hpicon);
                parent.deIconify(myFrame);
              
            }
        });
    }

    public void adjustSize () {
    }

}
