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

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TextBoxFormField extends AbstractFormField implements IFormFieldPrototype {
	
	private TextBox textbox;

	// //////////////////////////////////////////////////////////////////////////////////

	public TextBoxFormField() {
	}
	
	public TextBoxFormField(AttributeSchema schema, Object value) {
		setSchema(schema);
		setValue(value);
	}

	public IFormField newInstance(AttributeSchema schema) {
		return newInstance(schema, null);
	}

	public IFormField newInstance(AttributeSchema schema, Object value) {
		return new TextBoxFormField(schema, value);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	protected Widget getFieldWidget() {
		
		textbox = new TextBox();
//		int maxLength = getSchema().max;
//		if(maxLength > 0) {
//			textbox.setMaxLength(maxLength);
//		}
		
		return textbox;
	}

	public Object getValue() {
		return textbox.getText();
	}

	public void setValue(Object value) {
		if(value == null) {
			return;
		}
		textbox.setText(value.toString());
	}

}
