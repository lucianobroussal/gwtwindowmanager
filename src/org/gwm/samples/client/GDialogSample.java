package org.gwm.samples.client;

import org.gwm.client.event.GDialogChoiceListener;
import org.gwm.client.impl.GDialog;
import org.gwm.client.impl.GwtInternalFrame;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class GDialogSample implements EntryPoint {

    private VerticalPanel menuLayout  = new VerticalPanel();
    
    public void onModuleLoad() {
         GwtInternalFrame menuBlock = new GwtInternalFrame("Menu");
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
                GDialog.setDialogTheme("darkX");
                GDialog.showConfirmDialog(null, "Have you pets?" ,null);
            }
            
        });
        menuLayout.add(button);
        
        
    }

    private void setUpSimpleMessage() {
        Button button = new Button("Show Message");
        button.addClickListener(new ClickListener(){

            public void onClick(Widget sender) {
                GDialog.setDialogTheme("darkX");
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
