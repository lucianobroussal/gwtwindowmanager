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

import java.util.HashMap;
import java.util.Map;

import org.gwm.splice.client.schema.AttributeSchema;
import org.gwm.splice.client.schema.DynamicChoices;
import org.gwm.splice.client.schema.ObjectSchema;
import org.gwm.splice.client.schema.StaticChoices;
import org.gwm.splice.client.service.ISerializer;
import org.gwm.splice.client.service.query.Filter;
import org.gwm.splice.client.user.JUser;

import com.google.gwt.core.client.GWT;

/**
 * Used by an {@link ISerializer} class to instantiate a {@link RemoteObject} instance.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 *
 */
public class RemoteObjectFactory {
	
	public static final String MAGIC_TYPE_ATTR_NAME = "__remoteObjectType__";
	
	private static RemoteObjectFactory instance;
	private Map registry = new HashMap();
	
	/**
	 * Returns the singleton instance.
	 * 
	 * @return a {@link RemoteObjectFactory} instance.
	 */
	public static RemoteObjectFactory getInstance() {
		if(instance == null) {
			instance = new RemoteObjectFactory();
		}
		return instance;
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	/**
	 * Default constructor registers common types.
	 */
	private RemoteObjectFactory() {
		registerType(new RemoteStatus());
		registerType(new ValidationError());
		registerType(new RemoteDataObject());
		registerType(new AttributeSchema());
		registerType(new StaticChoices());
		registerType(new DynamicChoices());
		registerType(new ObjectSchema());
		registerType(new ResultList());
		registerType(new Filter());
		registerType(new JUser());
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	/**
	 * Registers an {@link IRemoteObjectPrototype} object.
	 * <p>
	 * The remoteObjectType is determined by {@link GWT#getTypeName(Object)}.
	 * 
	 * @param prototype an {@link IRemoteObjectPrototype} instance.
	 */
	public void registerType(IRemoteObjectPrototype prototype) {
		registerType(null, prototype);
	}
	
	/**
	 * Registers an {@link IRemoteObjectPrototype} object.
	 * 
	 * @param remoteObjectType the {@link RemoteObject#remoteObjectType} type name.
	 * @param prototype an {@link IRemoteObjectPrototype} instance.
	 */
	public void registerType(String objectType, IRemoteObjectPrototype prototype) {
		if(objectType == null) {
			registry.put(GWT.getTypeName(prototype), prototype);
		}
		else {
			registry.put(objectType, prototype);
		}
	}
	
	/**
	 * De-registers an {@link IRemoteObjectPrototype} object.
	 * 
	 * @param remoteObjectType the {@link RemoteObject#remoteObjectType} type name.
	 */
	public void deregisterType(String objectType) {
		registry.remove(objectType);
	}
	
	/**
	 * Creates an instance of the specified object type.
	 * 
	 * @param remoteObjectType the {@link RemoteObject#remoteObjectType} type name.
	 * @return a new {@link RemoteObject} instance, or null if not registered.
	 */
	public RemoteObject createObjectInstance(String objectType) {
		if(objectType != null) {
			IRemoteObjectPrototype obj = (IRemoteObjectPrototype) registry.get(objectType);
			if(obj != null) {
				return obj.newInstance();
			}
		}
		return null;
	}

	public RemoteObject createObjectInstance(Attributes attributes) {
		RemoteObject obj = createObjectInstance(attributes.getStringValue(MAGIC_TYPE_ATTR_NAME));
		if(obj == null) {
			// if invalid object type, just create a generic data object & stuff the attributes in it
			obj = new RemoteDataObject(null, attributes);
			return obj;
		}
		obj.initialize(attributes);
		return obj;
	}
}
