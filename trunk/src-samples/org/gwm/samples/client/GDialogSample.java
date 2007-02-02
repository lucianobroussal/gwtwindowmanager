package org.gwm.samples.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.impl.FramesManagerFactory;
import org.gwm.client.impl.GDialog;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class GDialogSample implements EntryPoint {

    private VerticalPanel menuLayout  = new VerticalPanel();
    private FramesManager framesManager;
    
    public void onModuleLoad() {
         framesManager = new FramesManagerFactory().createFramesManager();
         GInternalFrame menuBlock = framesManager.newFrame("Menu");
         menuBlock.setLocation(10, 100);
         menuBlock.setDraggable(true);
         //menuBlock.setResizable(false);
         menuBlock.setClosable(false);
         menuBlock.setMinimizable(false);
         menuBlock.setMaximizable(false);
         menuBlock.setTheme("default");
         menuBlock.setContent(menuLayout);
         
         
         setUpSimpleMessage();
         setUpSimpleConfirmMessage();
         setUpSimpleInputMessage();
         
         
         menuBlock.setVisible(true);
    }

    private void setUpSimpleConfirmMessage() {
        Button button = new Button("Show Confirm");
        button.addClickListener(new ClickListener(){

            public void onClick(Widget sender) {
                GDialog.setDialogTheme("spread");
                GDialog.showConfirmDialog(null, "Have you pets?" ,null);
            }
            
        });
        menuLayout.add(button);
        
        
    }

    private void setUpSimpleMessage() {
        Button button = new Button("Show Message");
        button.addClickListener(new ClickListener(){

            public void onClick(Widget sender) {
                GDialog.setDialogTheme("alphacube");
                GDialog.showMessage(null, "Simple Message display");
            }
            
        });
        menuLayout.add(button);
        
    }
    
    private void setUpSimpleInputMessage() {
        Button button = new Button("Show Input");
        button.addClickListener(new ClickListener(){

            public void onClick(Widget sender) {
                GDialog.setDialogTheme("theme1");
                GDialog.showInputDialog(null, "How old are you?" ,new GDialogChoiceListener(){

                    public void onChoice(GDialog dialog) {
                        System.out.println(dialog.getSelectedOption());
                        System.out.println(dialog.getSelectedValue());
                        
                    }
                    
                });
            }
            
        });
        menuLayout.add(button);
        
        
    }

}
