package org.gwm.splice.test.client;

import org.gwm.splice.client.form.HtmlForm;
import org.gwm.splice.client.service.GenericRemoteService;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HtmlFormTest implements EntryPoint {

	static GenericRemoteService formService = new GenericRemoteService();

	/**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final Button button = new Button("Show Form");

		// init the service to get html forms
		formService.setController("simple");
		formService.setScriptDir("forms");
		formService.setScriptExtension(".html");
//		formService.setHostedModeTargetBaseUrl("file:///home/andy/wstu/GWM/src-splice/org/gwm/splice/test/public/forms");

		button.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
    		HtmlForm form = new HtmlForm("subscribe", "Test Html Form", formService);
    		form.setHeight(400);
    		form.setWidth(600);
    		form.show();
       }
    });

    RootPanel.get("slot1").add(button);
  }
}
