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
package org.gwm.splice.client.service.json;

import java.util.Iterator;
import java.util.Set;

import org.gwm.splice.client.service.ISerializer;
import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.ISerializable;
import org.gwm.splice.client.service.data.Attributes.Attribute;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JSONSerializer implements ISerializer {
	
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.ISerializer#deserialize(java.lang.String)
	 */
	public Attributes deserialize(String json) {

		JSONObject jobj = (JSONObject) JSONParser.parse(json);
		
		Attributes attributes = new Attributes();
		convertAttributes(jobj, attributes);
		
		return attributes;
	}

	private void convertAttributes(JSONObject obj, Attributes attrs) {
		Set keys = obj.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			JSONValue val = obj.get(name);
			attrs.put(name, convertValue(val));
		}
	}
	
	private Object convertValue(JSONValue val) {
		if(val.isBoolean() != null) {
			return new Boolean(val.isBoolean().booleanValue());
		}
		else if(val.isNumber() != null) {
			return new Double(val.isNumber().getValue());
		}
		else if(val.isString() != null) {
			return val.isString().stringValue();
		}
		else if(val.isObject() != null) {
			Attributes attrs = new Attributes();
			convertAttributes(val.isObject(), attrs);
			return attrs;
		}
		else if(val.isArray() != null) {
			JSONArray ja = val.isArray();
			Object[] array = new Object[ja.size()];
			for (int i = 0; i < ja.size(); i++) {
				array[i] = convertValue(ja.get(i));
			}
			return array;
		}
		return null;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.ISerializer#serialize(org.gwm.splice.client.service.data.RemoteObject)
	 */
	public String serialize(Attributes attributes) {
		
		JSONObject jobj = new JSONObject();
		
		convertAttributes(attributes, jobj);
		
		return jobj.toString();
	}
	
	//...

	private void convertAttributes(Attributes attrs, JSONObject jobj) {
		for (Iterator iter = attrs.iterator(); iter.hasNext();) {
			Attribute attr = (Attribute) iter.next();
			jobj.put(attr.getName(), convertValue(attr.getValue()));
		}
	}
	
	private JSONValue convertValue(Object val) {
		if(val instanceof Boolean) {
			return JSONBoolean.getInstance(((Boolean)val).booleanValue());
		}
		if(val instanceof Number) {
			return new JSONNumber(((Number)val).doubleValue());
		}
		if(val instanceof String) {
			return new JSONString(val.toString());
		}
		if(val instanceof Attributes) {
			JSONObject jobj = new JSONObject();
			convertAttributes((Attributes)val, jobj);
			return jobj;
		}
		if(val instanceof Object[]) {
			JSONArray array = new JSONArray();
			Object[] va = (Object[]) val;
			for (int i = 0; i < va.length; i++) {
				array.set(i, convertValue(va[i]));
			}
			return array;
		}
		if(val instanceof ISerializable) {
			Attributes attrs = ((ISerializable)val).serialize();
			JSONObject jobj = new JSONObject();
			convertAttributes(attrs, jobj);
			return jobj;
		}
		return JSONNull.getInstance();
	}
}
