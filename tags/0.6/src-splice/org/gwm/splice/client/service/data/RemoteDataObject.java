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




public class RemoteDataObject extends RemoteObject implements IRemoteObjectPrototype {
	
	protected String dataType;
	protected Attributes attributes;
	
	public RemoteDataObject() {
		this(null, null);
	}

	public RemoteDataObject(String dataType) {
		this(dataType, null);
	}

	public RemoteDataObject(String dataType, Attributes attributes) {
		super();
		this.dataType = dataType;
		this.attributes = attributes;
		if(this.attributes == null) {
			this.attributes = new Attributes();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onInitialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onInitialize(Attributes attributes) {
		this.dataType = attributes.getStringValue("dataType");
		this.attributes = initialize(attributes.get("attributes"));
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onSerialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onSerialize(Attributes attributes) {
		attributes.put("dataType", dataType);
		attributes.put("attributes", serialize(attributes));
	}

	//////////////////////////////////////////////////////////////////////////////////
	
	public Object getAttribute(String name) {
		if(attributes == null) {
			return null;
		}
		return attributes.get(name);
	}

	public void setAttribute(String name, Object value) {
		if(attributes == null) {
			return;
		}
		attributes.put(name, value);
	}
	
	//////////////////////////////////////////////////////////////////////////////////

	public RemoteObject newInstance() {
		return new RemoteDataObject();
	}
	
	//////////////////////////////////////////////////////////////////////////////////

	public String getDataType() {
		return dataType;
	}
	public void setDataType(String objectType) {
		this.dataType = objectType;
	}
	public Attributes getAttributes() {
		if(attributes == null) {
			attributes = new Attributes();
		}
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}


}
