package org.gwm.samples.gwmdemo.client;

import org.gwm.client.impl.GDialog;

import com.google.gwt.user.client.ui.Hyperlink;

public class WarningDialogScenarii extends AbstractScenarii {


    public WarningDialogScenarii(Object object) {
        super(object);
    }

    public void runScenarii() {
        GDialog.showMessage(null, "Don't forget your umbrella!. It's raining", "Warning", GDialog.WARNING_MESSAGE);
    }

    protected Hyperlink createLink() {
        Hyperlink desktopDemoLink = new Hyperlink("Warning Dialog",
                "warning_dialog");
        return desktopDemoLink;
    }

   

}
