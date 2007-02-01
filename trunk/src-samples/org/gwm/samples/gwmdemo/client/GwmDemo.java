package org.gwm.samples.gwmdemo.client;

import org.gwm.client.GDesktopPane;
import org.gwm.client.impl.DefaultGDesktopPane;
import org.gwm.client.impl.GwtInternalFrame;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class GwmDemo implements EntryPoint {

    private GDesktopPane desktop;
    private GwtInternalFrame menuFrame;
    
    public void onModuleLoad() {
        buildUI();
        menuFrame.setSize(150,300);
        Window.enableScrolling(false);
    }

    private void buildUI() {
        desktop = new DefaultGDesktopPane();
        buildMenu();
        RootPanel.get().add((Widget)desktop);
    }

    private void buildMenu() {
       menuFrame = new GwtInternalFrame("Samples");
       menuFrame.setTheme("alphacube");
       menuFrame.setClosable(false);
       menuFrame.setMinimizable(false);
       menuFrame.setResizable(false);
       desktop.addFrame(menuFrame);
       menuFrame.setVisible(true);
       menuFrame.setLocation(5,5); 
       VerticalPanel menuLayout = new VerticalPanel();
       menuLayout.setStyleName("gwmdemo-Menu");
       menuLayout.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
       menuLayout.add(buildMenu("Frames"));
       menuLayout.add(buildMenuItem(new SimpleWindowWithURLScenarii(null)));
       menuLayout.add(buildMenuItem(new SimpleWindowWithTextScenarii(null)));
       menuLayout.add(buildMenuItem(new SimpleWindowWithWidgetScenarii(null)));
       menuLayout.add(buildMenu("Frame Listener"));
       menuLayout.add(buildMenuItem(new EventScenarii(null)));
       menuLayout.add(buildMenu("Themes"));
       menuLayout.add(buildMenuItem(new ThemesScenarii(null)));
       menuLayout.add(buildMenu("Desktop"));
       menuLayout.add(buildMenuItem(new DesktopScenarii(null , desktop)));
       menuLayout.add(buildMenu("Dialog"));
       menuLayout.add(buildMenuItem(new InputDialogScenarii(null)));
       menuLayout.add(buildMenuItem(new WarningDialogScenarii(null)));
       menuLayout.add(buildMenu("Tools"));
       menuLayout.add(buildMenuItem(new WindowEditorScenarii(null)));

       menuFrame.setContent(menuLayout);
    }

    private Widget buildMenu(String name){
        HorizontalPanel menuLayout = new HorizontalPanel();
        menuLayout.setSpacing(3);
        menuLayout.add(new Label(name));
        menuLayout.setStyleName("gwmdemo-MenuSection");
        return menuLayout;
    }
    
    private Widget buildMenuItem(Scenarii scenarii){
        HorizontalPanel menuItemLayout = new HorizontalPanel();
        menuItemLayout.setSpacing(2);
        menuItemLayout.add(scenarii.getLink());
        menuItemLayout.setStyleName("gwmdemo-MenuItem");
        return menuItemLayout;
    }
   
    
//    
//    Hyperlink windowEditorLink = new Hyperlink("WindowEditor",
//    "windoweditor");
//windowEditorLink.addClickListener(new ClickListener() {
//
//public void onClick(Widget sender) {
//    displayWindowEditor();
//
//}
//
//});
    
    
}
