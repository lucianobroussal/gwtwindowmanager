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
package org.gwm.splice.client.schema;

import org.gwm.splice.client.schema.def.ShortTextSchema;
import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.IRemoteObjectPrototype;
import org.gwm.splice.client.service.data.RemoteObject;


public class AttributeSchema extends RemoteObject implements Comparable, IRemoteObjectPrototype {
	
	public static final String HIDDEN_SYNTAX = "_hidden_";
	
	// //////////////////////////////////////////////////////////////////////////////////
	
	public String name;
	public String title;
	public int datatype;
	public double min;
	public double max;
	public String syntax;
	public String description;
	public boolean required;
	public IChoices choices;
	public boolean readOnly;
	public int displayOrder;
	public Object defaultValue;
	
	public AttributeSchema() {
	}
	
	public AttributeSchema(int datatype, String name, String title, String description, String syntax, double max, int displayOrder, boolean required) {
		super();
		this.datatype = datatype;
		this.name = name;
		this.title = title;
		this.description = description;
		this.syntax = syntax;
		this.max = max;
		this.displayOrder = displayOrder;
		this.required = required;
	}

	////////////////////////////////////////////////////////////////////////////////////
	
	
	public class Schema extends ObjectSchema {

		public Schema() {
			super();
			
			put(new ShortTextSchema("name", "Name", "The name of the attribute", 2, 32, 1, true));
			put(new ShortTextSchema("title", "Title", "The display name for the attribute", 2, 32, 1, true));
		}
		
	}

	////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.IRemoteObjectPrototype#newInstance()
	 */
	public RemoteObject newInstance() {
		return new AttributeSchema();
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onInitialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onInitialize(Attributes attributes) {
		name = attributes.getStringValue("name");
		title = attributes.getStringValue("title");
		syntax = attributes.getStringValue("syntax");
		datatype = attributes.getIntValue("datatype");
		min = attributes.getDoubleValue("min");
		max = attributes.getDoubleValue("max");
		displayOrder = attributes.getIntValue("displayOrder");
		readOnly = attributes.getBooleanValue("readOnly");
		required = attributes.getBooleanValue("required");
		defaultValue = attributes.get("defaultValue");
		choices = (IChoices) getRemoteObject(attributes.get("choices"));
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onSerialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onSerialize(Attributes attributes) {
		attributes.put("name", name);
		attributes.put("title", title);
		attributes.put("syntax", syntax);
		attributes.put("datatype", datatype);
		attributes.put("min", min);
		attributes.put("max", max);
		attributes.put("displayOrder", displayOrder);
		attributes.put("readOnly", readOnly);
		attributes.put("required", required);
		attributes.put("defaultValue", defaultValue);
		attributes.put("choices", serialize((RemoteObject)choices));
}

	////////////////////////////////////////////////////////////////////////////////////

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IChoices getChoices() {
		return choices;
	}

	public void setChoices(IChoices choices) {
		this.choices = choices;
	}
	
	////////////////////////////////////////////////////////////////////////////////////

	public int compareTo(Object other) {
		if(!(other instanceof AttributeSchema)) {
			return 1;
		}
		AttributeSchema oas = (AttributeSchema) other;
		if(oas.displayOrder == displayOrder) {
			return 0;
		}
		if(oas.displayOrder > displayOrder) {
			return -1;
		}
		if(oas.displayOrder < displayOrder) {
			return 1;
		}
		return 0;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isHidden() {
		if(syntax == null) {
			return false;
		}
		return HIDDEN_SYNTAX.equals(syntax);
	}

}
