package org.gwm.samples.client;

import org.gwm.client.impl.GwtInternalFrame;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThemesSample implements EntryPoint {

    private ListBox listBox;

    private GwtInternalFrame frame;

    public void onModuleLoad() {

        testThemes();
    }

    private void testThemes() {
        listBox = new ListBox();
        listBox.addItem("default", "default");
        listBox.addItem("theme1", "theme1");
        listBox.addItem("alphacube", "alphacube");
        listBox.addItem("spread", "spread");
        listBox.addItem("alert", "alert");
        listBox.addItem("darkX", "darkX");

        listBox.addChangeListener(new ChangeListener() {

            public void onChange(Widget sender) {
                String theme = getCurrentTheme();
                frame.setTheme(theme);

            }

        });
        listBox.setEnabled(false);
        RootPanel.get().add(listBox);

        Button button = new Button("Test Frame  Theme");
        button.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                if (!listBox.isEnabled()) {
                    listBox.setEnabled(true);
                }
                frame = new GwtInternalFrame("http://www.google.com");
                //frame.setUrl("http://www.google.com");
                frame.setContent(new Label("http://www.google.com"));
                frame.setSize(300, 200);
                frame.setLocation(200, 200);
                frame.setResizable(true);
                frame.setTheme(getCurrentTheme());
                frame.setVisible(true);
            }

        });
        RootPanel.get().add(button);

    }

    private String getCurrentTheme() {
        String theme = listBox.getItemText(listBox.getSelectedIndex());
        return theme;
    }

}
