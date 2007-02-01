package org.gwm.samples.gwmdemo.client;

import org.gwm.client.impl.GDialog;

import com.google.gwt.user.client.ui.Hyperlink;

public class ErrorDialogScenarii extends AbstractScenarii {


    public ErrorDialogScenarii(Object object) {
        super(object);
    }

    public void runScenarii() {
        GDialog.showMessage(null, "Houston we have a problem!", "Error", GDialog.ERROR_MESSAGE);
    }

    protected Hyperlink createLink() {
        Hyperlink desktopDemoLink = new Hyperlink("Error Dialog",
                "error_dialog");
        return desktopDemoLink;
    }

   

}
