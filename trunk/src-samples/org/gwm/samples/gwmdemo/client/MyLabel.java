package org.gwm.samples.gwmdemo.client;

import org.gwm.client.impl.TopBar;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class MyLabel extends HTML {
    private TopBar topBar;

    public void onBrowserEvent(Event event) {
       topBar.addLog("Event MyLabel: " + DOM.eventGetTypeString(event));
    }

    public MyLabel(String text, TopBar bar) {
        super();
        this.topBar = topBar;
        setText(text);
        addClickListener(new ClickListener(){

            public void onClick(Widget arg0) {
                Window.alert("clicked");
                
            }
            
        });
    }
}