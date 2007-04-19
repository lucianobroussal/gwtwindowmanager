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
package org.gwm.splice.client.service.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A utility class for managing a collection of attributes.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 *
 */
public class Attributes {
	
	private Map attributes = new HashMap();
	
	// //////////////////////////////////////////////////////////////////////////////////

	/**
	 * Sets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @param value the value to set.
	 */
	public void put(String name, boolean value) {
		attributes.put(name, new Boolean(value));
	}
	
	/**
	 * Sets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @param value the value to set.
	 */
	public void put(String name, int value) {
		attributes.put(name, new Integer(value));
	}
	
	/**
	 * Sets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @param value the value to set.
	 */
	public void put(String name, long value) {
		attributes.put(name, new Long(value));
	}
	
	/**
	 * Sets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @param value the value to set.
	 */
	public void put(String name, double value) {
		attributes.put(name, new Double(value));
	}
	
	/**
	 * Sets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @param value the value to set.
	 */
	public void put(String name, Object value) {
		attributes.put(name, value);
	}
	
	/**
	 * Sets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @param value the value to set.
	 */
	public void put(String name, Date value) {
		attributes.put(name, value != null ? new Long(value.getTime()) : null);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Gets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @return the attributes value, or null if attribute does not exist.
	 */
	public Object get(String name) {
		return attributes.get(name);
	}

	/**
	 * Gets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @return the attributes value, or null if attribute does not exist.
	 */
	public String getStringValue(String name) {
		Object val = attributes.get(name);
		if(val != null) {
			return val.toString();
		}
		return null;
	}

	/**
	 * Gets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @return the attributes value, or 0 if attribute does not exist.
	 */
	public int getIntValue(String name) {
		Object val = attributes.get(name);
		if(val instanceof Number) {
			return((Number)val).intValue();
		}
		return 0;
	}

	/**
	 * Gets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @return the attributes value, or 0 if attribute does not exist.
	 */
	public long getLongValue(String name) {
		Object val = attributes.get(name);
		if(val instanceof Number) {
			return((Number)val).longValue();
		}
		return 0;
	}

	/**
	 * Gets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @return the attributes value, or 0 if attribute does not exist.
	 */
	public Date getDateValue(String name) {
		Object val = attributes.get(name);
		if(val instanceof Number) {
			return new Date(((Number)val).longValue());
		}
		return null;
	}

	/**
	 * Gets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @return the attributes value, or 0 if attribute does not exist.
	 */
	public double getDoubleValue(String name) {
		Object val = attributes.get(name);
		if(val instanceof Number) {
			return((Number)val).doubleValue();
		}
		return 0;
	}

	/**
	 * Gets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @return the attributes value, or false if attribute does not exist.
	 */
	public boolean getBooleanValue(String name) {
		Object val = attributes.get(name);
		if(val instanceof Boolean) {
			return((Boolean)val).booleanValue();
		}
		return false;
	}

	/**
	 * Gets the value of a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 * @return the attributes value, or null if attribute does not exist.
	 */
	public Attributes getAttributes(String name) {
		Object val = attributes.get(name);
		if(val instanceof Attributes) {
			return(Attributes)val;
		}
		return null;
	}

	/**
	 * Removes a specified attribute.
	 * 
	 * @param name the name of the attribute.
	 */
	public void remove(String name) {
		attributes.remove(name);
	}

	/**
	 * Removes all attributes.
	 */
	public void clear() {
		attributes.clear();
	}
	
	/**
	 * Gets the number of attributes.
	 * 
	 * @return the total number of attributes in the collection.
	 */
	public int size() {
		return attributes.size();
	}
	
	/**
	 * Returns an Iterator of {@link Attributes.Attribute} objects.
	 * 
	 * @return an Iterator of {@link Attributes.Attribute} objects.
	 */
	public Iterator iterator() {
		return new AttributeIterator();
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	public class Attribute {
		private String name;
		private Object value;
		
		private Attribute(String name, Object value) {
			this.name = name;
			this.value = value;
		}
		
		private Attribute(Map.Entry entry) {
			this.name = (String) entry.getKey();
			this.value = entry.getValue();
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
	}

	private class AttributeIterator implements Iterator {
		private Iterator itr;
		
		private AttributeIterator() {
			itr = attributes.entrySet().iterator();
		}

		public boolean hasNext() {
			return itr.hasNext();
		}

		public Object next() {
			Map.Entry entry = (Entry) itr.next();
			if(entry != null) {
				return new Attribute(entry);
			}
			return null;
		}

		public void remove() {
		}
		
	}
	
	public List getList() {
		return new ArrayList(attributes.values());
	}
}
