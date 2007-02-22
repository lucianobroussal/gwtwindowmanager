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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.gwm.splice.client.schema.AttributeSchema;
import org.gwm.splice.client.schema.ObjectSchema;
import org.gwm.splice.client.service.data.RemoteDataObject;
import org.gwm.splice.client.service.data.Attributes.Attribute;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DynaForm extends VerticalPanel implements IDataForm {
	
	public static final int DEFALUT_LABEL_WIDTH = 100;

	private ObjectSchema schema;
	private RemoteDataObject data;
	private ArrayList listeners = new ArrayList();
	private int labelWidth = DEFALUT_LABEL_WIDTH; 
	
	/////////////////////////////////////////////////////////////////////////////////////////

	public DynaForm(ObjectSchema schema) {
		this(schema, null);
	}

	public DynaForm(ObjectSchema schema, RemoteDataObject data) {
		this.schema = schema;
		this.data = data;
		init();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	private void init() {
		clear();
		FormFieldFactory factory = FormFieldFactory.getInstance();
		
		List list = new ArrayList();
		for (Iterator iter = schema.getAttributes().iterator(); iter.hasNext();) {
			Attribute attr = (Attribute) iter.next();
			list.add(attr.getValue());
		}
			
		Collections.sort(list);
		
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			AttributeSchema attr = (AttributeSchema) iter.next();
			
			if(attr.isHidden()) {
				continue;
			}
			
			IFormField field = null;
			if(data != null) {
				Object value = data.getAttributes().get(attr.name);
				field = factory.createField(attr, value);
			}
			else {
				field = factory.createField(attr);
			}
			field.setLabelWidth(labelWidth);
			add((Widget) field);
		}
		
		setStyleName("formPanel");
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	public void addListener(IDataFormListener listener) {
		listeners.add(listener);
	}
	public void removeListener(IDataFormListener listener) {
		listeners.remove(listener);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////

	public ObjectSchema getSchema() {
		return schema;
	}

	public void setData(RemoteDataObject data) {
		init();
		this.data = data;
	}

	public RemoteDataObject getData() {
		return data;
	}

	public void setSchema(ObjectSchema schema) {
		init();
		this.schema = schema;
	}

	public int getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(int labelWidth) {
		this.labelWidth = labelWidth;
	}

}
