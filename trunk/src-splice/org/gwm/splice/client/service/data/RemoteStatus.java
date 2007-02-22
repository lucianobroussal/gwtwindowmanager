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


/**
 * A generic error class.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 *
 */
public class RemoteStatus extends RemoteObject implements IRemoteObjectPrototype {
	
	public static final int STATUS_OK = 0;
	public static final int STATUS_WARNING = 1;
	public static final int STATUS_ERROR = -1;
	public static final int STATUS_VALIDATION_ERROR = -2;
	
	protected int status = 0;
	protected String message;
	protected Attributes attributes;

	public RemoteStatus() {
		super();
	}

	public RemoteStatus(String message) {
		super();
		this.message = message;
	}

	public RemoteStatus(int errorCode, String message) {
		super();
		this.status = errorCode;
		this.message = message;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onInitialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onInitialize(Attributes attributes) {
		status = attributes.getIntValue("status");
		message = attributes.getStringValue("message");
		this.attributes = initialize(attributes.get("attributes"));
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onSerialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onSerialize(Attributes attributes) {
		attributes.put("status", status);
		attributes.put("message", message);
		attributes.put("attributes", serialize(attributes));
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.IRemoteObjectPrototype#newInstance()
	 */
	public RemoteObject newInstance() {
		return new RemoteStatus();
	}

	// //////////////////////////////////////////////////////////////////////////////////

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
	
	// //////////////////////////////////////////////////////////////////////////////////
	
	public boolean isError() {
		return status < 0;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

}
