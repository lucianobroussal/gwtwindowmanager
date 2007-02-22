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
package org.gwm.splice.client.form.field;

import org.gwm.splice.client.form.IFormField;
import org.gwm.splice.client.form.IFormFieldPrototype;
import org.gwm.splice.client.schema.AttributeSchema;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public class YesNoFormField extends AbstractFormField implements IFormFieldPrototype {

	private YesNoWidget widget;
	
	// //////////////////////////////////////////////////////////////////////////////////

	public YesNoFormField() {
	}
	
	public YesNoFormField(AttributeSchema schema) {
		this(schema, null);
	}
	
	public YesNoFormField(AttributeSchema schema, Object value) {
		widget = new YesNoWidget(schema.name);
		setSchema(schema);
		setValue(value);
	}
	
	public IFormField newInstance(AttributeSchema schema) {
		return newInstance(schema, null);
	}

	public IFormField newInstance(AttributeSchema schema, Object value) {
		return new YesNoFormField(schema, value);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public Object getValue() {
		if(widget.rbYes.isChecked()) {
			return new Boolean(true);
		}
		return new Boolean(false);
	}

	public void setValue(Object value) {
		if(value instanceof Boolean) {
//			if(((Boolean)value).booleanValue()) {
//				widget.rbYes.setChecked(true);
//			}
//			else {
//				widget.rbNo.setChecked(true);
//			}
		}
	}

	protected Widget getFieldWidget() {
		return widget;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	private class YesNoWidget extends HorizontalPanel {
		private RadioButton rbYes;
		private RadioButton rbNo;
		
		YesNoWidget(String name) {
			rbYes = new RadioButton(name, "Yes");
			rbNo = new RadioButton(name, "No");
			add(rbYes);
			add(rbNo);
		}
	}
}
