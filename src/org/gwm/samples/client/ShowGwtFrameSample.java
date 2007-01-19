package org.gwm.samples.client;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;
import org.gwm.client.GInternalFrameFactory;
import org.gwm.client.impl.DefaultGDesktopPane;
import org.gwm.client.impl.GDialog;
import org.gwm.client.impl.GwtInternalFrame;

import asquare.gwt.tk.client.ui.GlassPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class ShowGwtFrameSample implements EntryPoint {

    private DefaultGDesktopPane pane;
    private GlassPanel glassPanel;

    public void onModuleLoad() {

        
    //    testGDialog();
        testPane();
        //testPane2();
    }

    
    
    
    
   


    private void testPane() {
        
       
        
        GDesktopPane pane = new DefaultGDesktopPane();
        GInternalFrame gif = new GwtInternalFrame("Getting started");
        gif.setUrl ("http://www.gwtwindowmanager.org/site/gettingstarted.html");
        
        RootPanel root = RootPanel.get("pane");
        //to be done after adding windows!!!! if not JVM crash TO BE FIX
        root.add((Widget)pane);
        pane.addFrame(gif);
        gif.setVisible(true);
        

        GInternalFrame gif2 = new GwtInternalFrame("LodgON");
        gif2.setUrl ("http://www.lodgon.com");
        gif2.setSize (300,200);
        pane.addFrame(gif2);
        gif2.setVisible(true);
        gif2.setLocation(0, 0);
        Button button = new Button("Test free Frame :)");
        button.addClickListener(new ClickListener(){

            public void onClick(Widget sender) {
                GInternalFrame gif = new GwtInternalFrame("http://www.google.com");
                gif.setUrl ("http://www.google.com");
                gif.setSize(300, 200);
                gif.setLocation(0, 0);
                gif.setVisible(true);
                
//                PopupPanel p = new PopupPanel();
//                p.setWidget(new Label("hjdhj"));
//                p.setPopupPosition(0, 0);
//                DOM.setStyleAttribute(p.getElement(), "border", "1px solid red");
//                p.show();
            }
            
        });
        RootPanel.get().add(button);

        
        

    }

    private void testWindow() {
        GInternalFrame gif = new GwtInternalFrame("ssss");
        gif.setContent(new Button("a button"));
        gif.setVisible(true);

    }

    private void testGDialog() {
        DialogBox box = new DialogBox();
        box.setTitle("jkxjzkjkzjckx");
        box.setHTML("kjxdjzckxjvckvjvkc");
        box.show();
        
        GInternalFrame frame = new GInternalFrameFactory().newGInternalFrame("NEW TEST !!!", GwtInternalFrame.class);
        frame.setVisible(true);
        
        Button button = new Button("Click me");
        button.addClickListener(new ClickListener(){

            public void onClick(Widget sender) {
//                GDialog.showInputDialog(null, "message", "title", "deux", new Object[]{"un", "deux" , "trois" , "quatre"}, GDialog.QUESTION_MESSAGE , null, new GDialogChoiceListener(){
//
//                    public void onChoice(Option option, Object inputValue) {
//                        if(GDialog.OK_OPTION == option){
//                                Window.alert((inputValue != null ? inputValue.toString(): "N/A"));
//                        }
//                    }
//                    
//                });   
 //               GDialog.showConfirmDialog(null, "coucou", "titre a la noeud " ,GDialog.YES_NO_OPTION , GDialog.QUESTION_MESSAGE , null);
            }
            
        });
        
        
        Button button1 = new Button("Click me1");
        button1.addClickListener(new ClickListener(){

            public void onClick(Widget sender) {
                glassPanel.hide();
                
            }
            
        });
        
        
        
        
        RootPanel.get("btn").add(button);
        
//        DialogBox b = new DialogBox();
//        b.setHTML("jkxjkwjk");
//        b.setPopupPosition(300,300);
//        b.show();
        GwtInternalFrame frame1 = new GwtInternalFrame("back");
        frame1.setTheme("theme1");
        frame1.setContent(button1);
        frame1.show();
//
//        GwtInternalFrame frame = new GwtInternalFrame("kjk");
//        frame.setTheme("theme1");
//        frame.setContent(button1);
//        glassPanel = new GlassPanel();
//        glassPanel.show();
//        frame.show();
        //frame.setModal(true);
        //DOM.setCapture(button1.getElement());
        
//        RootPanel.get().add(button1);
        GDialog.setDialogTheme("theme1");
         
    }

}
