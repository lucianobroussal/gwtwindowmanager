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
    GInternalFrame gif = new GwtInternalFrame();
    gif.setContent (new Button ("a button"));
    gif.show(true);
    GInternalFrame gif2 = new GwtInternalFrame();
    gif2.setContent (new HTML ("A second frame"));
    gif2.setLocation (100,180);
    gif2.show(true);
    // pane.addGInternalFrame (gif);
  }

}
