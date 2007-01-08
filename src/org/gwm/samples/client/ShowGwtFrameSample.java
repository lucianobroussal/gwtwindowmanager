package org.gwm.samples.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import org.gwm.client.*;
import org.gwm.client.impl.*;

public class ShowGwtFrameSample implements EntryPoint {

  private GDesktopPane pane;

  public void onModuleLoad() {
    pane = new GDesktopPane();
    GInternalFrame gif = new GwtInternalFrame("1" , "ssss" , null);
    gif.setContent (new Button ("a button"));
    gif.show(true);
    //pane.addGInternalFrame (gif);
  }

}
