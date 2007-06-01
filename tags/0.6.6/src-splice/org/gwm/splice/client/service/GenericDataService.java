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
package org.gwm.splice.client.service;

import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.RemoteObject;
import org.gwm.splice.client.service.query.Filter;


public class GenericDataService extends GenericRemoteService implements IRemoteDataService {
	
	public static final String ACTION_GET = "get";
	public static final String ACTION_SAVE = "save";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_LIST = "list";
	
	private String objectType = null;
	private Filter filter = null;
	
	public GenericDataService() {
	}
	public GenericDataService(String objectType) {
		this.objectType = objectType;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IRemoteDataService#get(java.lang.Object, org.gwm.splice.client.service.IResponseHandler)
	 */
	public void get(Object id, IResponseHandler handler) {
		Attributes attrs = new Attributes();
		attrs.put("id", id);
		addObjectTypeParam(attrs);
		execute(ACTION_GET, attrs, handler);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IRemoteDataService#save(org.gwm.splice.client.service.data.RemoteObject, org.gwm.splice.client.service.IResponseHandler)
	 */
	public void save(RemoteObject dataObject, IResponseHandler handler) {
		execute(ACTION_SAVE, dataObject, handler);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IRemoteDataService#delete(java.lang.Object, org.gwm.splice.client.service.IResponseHandler)
	 */
	public void delete(Object id, IResponseHandler handler) {
		Attributes attrs = new Attributes();
		attrs.put("id", id);
		addObjectTypeParam(attrs);
		execute(ACTION_DELETE, attrs, handler);
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IRemoteDataService#list(org.gwm.splice.client.service.IResponseHandler)
	 */
	public void list(IResponseHandler handler) {
		list(null, handler);
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IRemoteDataService#list(org.gwm.splice.client.service.query.Filter, org.gwm.splice.client.service.IResponseHandler)
	 */
	public void list(Filter filter, IResponseHandler handler) {
		Attributes attrs;
		
		if(filter != null) {
			attrs = filter.toParameters();
		}
		else if(this.filter != null) {
			attrs = this.filter.toParameters();
		}
		else {
			attrs = new Attributes();
		}
		addObjectTypeParam(attrs);
		execute(ACTION_LIST, attrs, handler);
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/**
	 * Helper only adds remoteObjectType to params if not null.
	 * @param attrs
	 */
	private void addObjectTypeParam(Attributes attrs) {
		if(objectType != null) {
			attrs.put("objectType", objectType);
		}
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	
}
