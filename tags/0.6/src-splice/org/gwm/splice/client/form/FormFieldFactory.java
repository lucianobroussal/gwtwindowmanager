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

import java.util.HashMap;
import java.util.Map;

import org.gwm.splice.client.form.field.TextBoxFormField;
import org.gwm.splice.client.form.field.YesNoFormField;
import org.gwm.splice.client.schema.AttributeSchema;

public class FormFieldFactory {
	
	private static FormFieldFactory instance;
	
	private Map registry = new HashMap();
	
	public static FormFieldFactory getInstance() {
		if(instance == null) {
			instance = new FormFieldFactory();
		}
		return instance;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	private FormFieldFactory() {
		registerType("_default_", new TextBoxFormField());
		registerType("name", new TextBoxFormField());
		registerType("yesno", new YesNoFormField());
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	public IFormField createField(AttributeSchema schema) {
		return createField(schema, null);
	}
	public IFormField createField(AttributeSchema schema, Object value) {
		String type = schema.syntax;
		if(type == null) {
			type = "_default_";
		}
		IFormFieldPrototype proto = (IFormFieldPrototype) registry.get(type);
		if(proto == null) {
			proto = (IFormFieldPrototype) registry.get("_default_");
		}
		return proto.newInstance(schema, value);
	}

	//////////////////////////////////////////////////////////////////////////////////////

	public void registerType(String syntax, IFormFieldPrototype prototype) {
		registry.put(syntax, prototype);
	}
	
	public void unregisterType(String syntax) {
		registry.remove(syntax);
	}
}
