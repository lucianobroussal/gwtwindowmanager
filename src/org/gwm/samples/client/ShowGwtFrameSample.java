package org.gwm.samples.client;

import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.impl.DefaultGDesktopPane;
import org.gwm.client.impl.GDialog;
import org.gwm.client.impl.GwtInternalFrame;
import org.gwm.client.impl.GDialog.Option;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;


public class ShowGwtFrameSample implements EntryPoint {

    private DefaultGDesktopPane pane;

    public void onModuleLoad() {

        // if (true) {
         // testWindow();
        // return;
        // }
         testPane();
        // testGDialog();
        // testWindow();
//        if (false) {
//            //testWindow();
//            return;
//        }
  //      testPane();
    }

    private void testPane() {
        pane = new DefaultGDesktopPane();
        GInternalFrame gif = new GwtInternalFrame("Getting started");
        gif.setUrl ("http://www.gwtwindowmanager.org/site/gettingstarted.html");
        pane.addFrame(gif);
        gif.setVisible(true);

        GInternalFrame gif2 = new GwtInternalFrame("LodgON");
        gif2.setUrl ("http://www.lodgon.com");
        gif2.setSize (400,300);
        pane.addFrame(gif2);
        gif2.setVisible(true);

        RootPanel root = RootPanel.get("pane");
        root.add (pane);

    }

    private void testWindow() {
        GInternalFrame gif = new GwtInternalFrame("ssss");
        gif.setContent(new Button("a button"));
        gif.setVisible(true);

    }

    private void testGDialog() {
        GDialog.setTheme("theme1");
//         GDialog.showMessage("TUTUUTUU");
        GDialog.showConfirmDialog("Alors ??????\nL1\nL2\nL3", "Mon titre" , GDialog.YES_NO_CANCEL_OPTION , GDialog.PLAIN_MESSAGE, new GDialogChoiceListener() {
            public void onChoice(Option option, Object inputValue) {
                if (option != null) {
                    Window.alert("Option : "
                            + option.getLabel()
                            + "\nInput : "
                            + ((inputValue != null) ? inputValue.toString()
                                    : null));
                }
            }
        });
//         GDialog.showInputDialog("C la question?", "default value.", new GDialogChoiceListener(){
//
//            public void onChoice(Option option, Object inputValue) {
//                if (option != null) {
//                  Window.alert("Option : "
//                          + option.getLabel()
//                          + "\nInput : "
//                          + ((inputValue != null) ? inputValue.toString()
//                                  : null));
//              }
//                
//            }
//             
//         });
    }

}
