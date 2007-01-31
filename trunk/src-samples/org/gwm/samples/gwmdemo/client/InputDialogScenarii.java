package org.gwm.samples.gwmdemo.client;

import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.impl.GDialog;

import com.google.gwt.user.client.ui.Hyperlink;

public class InputDialogScenarii extends AbstractScenarii {


    public InputDialogScenarii(Object object) {
        super(object);
    }

    public void runScenarii() {
        GDialog.showInputDialog(null, "What is your favorite hobby?" , "Asking ..." , "", new GDialogChoiceListener(){
            public void onChoice(GDialog dialog) {
            }
        });
    }

    protected Hyperlink createLink() {
        Hyperlink desktopDemoLink = new Hyperlink("Input Dialog",
                "input_dialog");
        return desktopDemoLink;
    }

   

}
