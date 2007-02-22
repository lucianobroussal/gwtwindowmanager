/*
 * Copyright (c) 2006/2007 Flipperwing Ltd. (http://www.flipperwing.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwm.splice.client.form;

import java.util.ArrayList;
import java.util.List;

import org.gwm.splice.client.service.IRemoteService;
import org.gwm.splice.client.service.RawResponseHandler;
import org.gwm.splice.client.window.AbstractWindow;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Attr;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

public class HtmlForm extends AbstractWindow {

	private String html;
	private DeckPanel formPanel = new DeckPanel();
	private Widget waitMsg = new HTML("Loading form.....");
	private List formNodes = new ArrayList();
	private HTML[] formPages;
	private HTML formPageAfter;
	private int currentPage = 0;
	
	/////////////////////////////////////////////////////////////////////////////////////////////

	public HtmlForm(String name, String title, IRemoteService service) {
		super(title, false, false, true);
		this.controlbar.addButton("actions/2leftarrow", "previous", 0, "Previous", "Previous");
		this.controlbar.addButton("actions/2rightarrow", "next", 0, "Next", "Next");
		this.controlbar.addButton("actions/ok", "finish", 0, "Finish", "Finish");
		setHeight(400);
		setWidth(600);
		setResizable(false);
		setMaximizable(false);
		setMinimizable(false);
		
		controlbar.getButton("previous").setDisabled(true);
		
		service.execute(name, null, new RawResponseHandler() {
			public void onCompletion(String data) {
				html = data;
				init();
			}
		}, true);
		formPanel.setStyleName("htmlFormPanel");
		formPanel.add(waitMsg);
		setWidget(formPanel);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	private void init() {
		
		Document doc = XMLParser.parse(html);
		NodeList nodeList = doc.getElementsByTagName("body");
		Node node = nodeList.item(0);
		node = node.getFirstChild();
		for(;;) {
			if(node.getNodeName().equalsIgnoreCase("div")) {
				String attrVal = getAttribute(node, "id");
				if(attrVal != null && attrVal.equals("pageAfter")) {
					formPageAfter = new HTML(node.toString());
				}
				else { 
					formNodes.add(node);
				}
			}
			node = node.getNextSibling();
			if(node == null) {
				break;
			}
		}

		formPanel.remove(waitMsg);
		
		formPages = new HTML[formNodes.size()];
		for (int i = 0; i < formPages.length; i++) {
			formPages[i] = new HTML(formNodes.get(i).toString());
			formPanel.add(formPages[i]);
		}
		formPanel.showWidget(0);
	}
	
	private void nextPage() {
		if(currentPage >= (formPages.length-1)) {
			return;
		}
		currentPage += 1;
		formPanel.showWidget(currentPage);
		if(currentPage >= (formPages.length-1)) {
			controlbar.getButton("next").setDisabled(true);
		}
		controlbar.getButton("previous").setDisabled(false);
	}
	
	private void previousPage() {
		if(currentPage == 0) {
			return;
		}
		currentPage -= 1;
		formPanel.showWidget(currentPage);
		if(currentPage == 0) {
			controlbar.getButton("previous").setDisabled(true);
		}
		controlbar.getButton("next").setDisabled(false);
	}
	
	private String getAttribute(Node node, String attrName) {
		NamedNodeMap attrs = node.getAttributes();
		Node attr = attrs.getNamedItem(attrName);
		if(attr == null) {
			return null;
		}
		return attr.getNodeValue();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.window.AbstractWindow#onActionClicked(com.google.gwt.user.client.ui.Widget, java.lang.String, int)
	 */
	public boolean onActionClicked(Widget widget, String name, int actionID) {
		if(name.equals("next")) {
			nextPage();
			return false;
		}
		if(name.equals("previous")) {
			previousPage();
			return false;
		}
		if(name.equals("finish")) {
			close();
			return false;
		}
		return true;
	}
}
