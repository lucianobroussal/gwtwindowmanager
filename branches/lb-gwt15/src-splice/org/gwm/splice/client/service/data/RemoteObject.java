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

import java.util.Iterator;

import org.gwm.splice.client.service.data.Attributes.Attribute;

import com.google.gwt.core.client.GWT;

/**
 * The base class for all remote objects.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 * 
 */
public abstract class RemoteObject implements ISerializable {

	protected String remoteObjectType;

	/**
	 * Constructor which initializes the {@link RemoteObject#remoteObjectType}
	 * value.
	 * <p>
	 * It uses {@link GWT#getTypeName(Object)} to set the remoteObjectType value.
	 * <p>
	 * Sub classes should ensure to call this constructor.
	 */
	public RemoteObject() {
		this.remoteObjectType = GWT.getTypeName(this);
	}

	// ////////////////////////////////////////////////////////////////////////////////

	/**
	 * Must be over-ridden by super classes to initialize their fields from the
	 * supplied {@link Attributes} collection.
	 * 
	 * @param attributes
	 *          a valid {@link Attributes} instance.
	 */
	protected abstract void onInitialize(Attributes attributes);

	/**
	 * Must be over-ridden by super classes to write their fields to the supplied
	 * {@link Attributes} collection.
	 * 
	 * @param attributes
	 *          a valid {@link Attributes} instance.
	 */
	protected abstract void onSerialize(Attributes attributes);

	// //////////////////////////////////////////////////////////////////////////////////

	public boolean isRemoteObject(Object val) {
		if (val instanceof Attributes) {
			Attributes attributes = (Attributes) val;
			return isRemoteObject(attributes);
		}
		return false;
	}

	/**
	 * Determines if the supplied attributes collection has a 'magic' field name
	 * as defined by {@link RemoteObjectFactory#MAGIC_TYPE_ATTR_NAME}.
	 * 
	 * @param attributes
	 *          a valid {@link Attributes} instance.
	 * @return true if field found (and therefore attributes represents a valid
	 *         {@link RemoteObject}} instance).
	 */
	public boolean isRemoteObject(Attributes attributes) {
		String s = attributes.getStringValue(RemoteObjectFactory.MAGIC_TYPE_ATTR_NAME);
		return s != null;
	}

	/**
	 * Helper method that iterates thru the supplied {@link Attributes} collection to find any
	 * {@link RemoteObject} instances, in which case their {@link RemoteObject#serialize()} method is
	 * called to convert it to a valid {@link Attributes} object.
	 * 
	 * @param attributes a valid {@link Attributes} object.
	 * @return a copy of the supplied {@link Attributes} with any {@link RemoteObject}s converted.
	 */
	public Attributes serialize(Attributes attributes) {
		Attributes attrs = new Attributes();
		for (Iterator iter = attributes.iterator(); iter.hasNext();) {
			Attribute attr = (Attribute) iter.next();
			Object obj = attr.getValue();
			if (obj instanceof RemoteObject) {
				obj = ((RemoteObject) obj).serialize();
			} else if (obj instanceof Attributes) {
				obj = serialize((Attributes) obj);
			}
			attrs.put(attr.getName(), obj);
		}
		return attrs;
	}

	public Attributes initialize(Object value) {
		if (!(value instanceof Attributes)) {
			return null;
		}
		Attributes attrs = (Attributes) value;
		Attributes newAttrs = new Attributes();
		for (Iterator iter = attrs.iterator(); iter.hasNext();) {
			Attribute attr = (Attribute) iter.next();
			newAttrs.put(attr.getName(), convert(attr.getValue()));
		}
		return newAttrs;
	}

	private Object convert(Object value) {
		if (isRemoteObject(value)) {
			return RemoteObjectFactory.getInstance().createObjectInstance((Attributes) value);
		}
		return value;
	}

	protected RemoteObject getRemoteObject(Object value) {
		if (isRemoteObject(value)) {
			return RemoteObjectFactory.getInstance().createObjectInstance((Attributes) value);
		}
		return null;
	}

	protected Attributes serialize(RemoteObject obj) {
		if (obj == null) {
			return null;
		}
		return obj.serialize();
	}

	// ////////////////////////////////////////////////////////////////////////////////

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwm.splice.client.service.data.ISerializable#initialize(org.gwm.splice.client.service.data.Attributes)
	 */
	public final void initialize(Attributes attributes) {
		String type = attributes.getStringValue(RemoteObjectFactory.MAGIC_TYPE_ATTR_NAME);
		if (type != null) {
			this.remoteObjectType = type;
		}
		onInitialize(attributes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwm.splice.client.service.data.ISerializable#serialize()
	 */
	public final Attributes serialize() {
		Attributes attrs = new Attributes();
		attrs.put(RemoteObjectFactory.MAGIC_TYPE_ATTR_NAME, this.remoteObjectType);
		onSerialize(attrs);
		return attrs;
	}

	// ////////////////////////////////////////////////////////////////////////////////

	public String getRemoteObjectType() {
		return remoteObjectType;
	}

	public void setRemoteObjectType(String objectType) {
		this.remoteObjectType = objectType;
	}

}
