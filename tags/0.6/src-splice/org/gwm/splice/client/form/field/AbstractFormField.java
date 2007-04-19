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
import org.gwm.splice.client.schema.AttributeSchema;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractFormField extends SimplePanel implements IFormField {

	protected int labelWidth;

	protected AttributeSchema schema;

	protected int layoutType = LAYOUT_HORIZONTAL;

	protected Panel fieldPanel;

	protected Label label = new Label();

	protected Widget fieldWidget;
	
	boolean enabled = true;

	// ////////////////////////////////////////////////////////////////////////////////

	protected abstract Widget getFieldWidget();

	// ////////////////////////////////////////////////////////////////////////////////

	public int getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(int pixels) {
		if(labelWidth != pixels) {
			label.setWidth(String.valueOf(pixels) + "px");
			this.labelWidth = pixels;
		}
	}

	public void setSchema(AttributeSchema schema) {
		this.schema = schema;
		init();
	}

	public AttributeSchema getSchema() {
		return schema;
	}

	public int getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(int val) {
		if (this.layoutType != val) {
			this.layoutType = val;
			init();
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	// ////////////////////////////////////////////////////////////////////////////////

	private void init() {
		clear();
		
		if(schema == null) {
			return;
		}
		
		if (layoutType == LAYOUT_VERTICAL) {
			fieldPanel = new VerticalPanel();
		} else {
			fieldPanel = new HorizontalPanel();
		}
		label.setText(schema.title);
		fieldPanel.add(label);
		fieldWidget = getFieldWidget();
		fieldPanel.add(fieldWidget);
		add(fieldPanel);
		if(schema.isRequired()) {
			label.setStyleName("labelRequired");
		}
		else {
			label.setStyleName("label");
		}
		setStyleName("formField");
	}

}
