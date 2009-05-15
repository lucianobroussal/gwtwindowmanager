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

import org.gwm.splice.client.schema.ObjectSchema;
import org.gwm.splice.client.service.data.RemoteDataObject;
import org.gwm.splice.client.window.AbstractWindow;

import com.google.gwt.user.client.ui.ScrollPanel;

public class DynaFormWindow extends AbstractWindow {
	
	private DynaForm form;

	public DynaFormWindow(String title, ObjectSchema schema) {
		this(title, schema, null);
	}
	public DynaFormWindow(String title, ObjectSchema schema, RemoteDataObject data) {
		super(title, false, false, true);
		this.form = new DynaForm(schema, data);
		setWidget(new ScrollPanel(form));
		setWidth(400);
		setHeight(400);
		setResizable(false);
		controlbar.addButton("actions/ok", "save", 0, "Save", "Save");
		controlbar.addCloseButton();
		setPanelStyleName("windowPanel");
	}

}
