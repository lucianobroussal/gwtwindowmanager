package org.gwm.client.impl;

import com.google.gwt.user.client.ui.*;
import org.gwm.client.*;
import java.util.*;

public class IconBar extends FlowPanel {

    GDesktopPane parent;
    Map buttonFrame;

    public IconBar (GDesktopPane parent) {
        super ();
        this.parent = parent;
        this.buttonFrame = new HashMap();
    }

    public void addWindow (GInternalFrame frame) {
        Button button = new Button (frame.getCaption(), new ClickListener() {
            public void onClick(Widget sender) {
                GInternalFrame myFrame = (GInternalFrame)buttonFrame.get (sender);
                buttonFrame.remove (sender);
                remove (sender);
                parent.deIconify(myFrame);
            }
        });

        this.buttonFrame.put (button, frame);
        this.add (button);
    }

    public void adjustSize () {
    }

}
