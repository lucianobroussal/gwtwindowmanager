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

import org.gwm.splice.client.service.data.RemoteObject;
import org.gwm.splice.client.service.query.Filter;

public interface IRemoteDataService {

	/**
	 * Executes the 'get' method on the controller.
	 * 
	 * @param id the unique id (primary key) of the object to fetch.
	 * @param handler the {@link IResponseHandler} to handle the returned data.
	 */
	void get(Object id, IResponseHandler handler);

	/**
	 * Executes the 'save' method on the controller.
	 * <p>
	 * If the object has an id set, it will update, else it will add a new item.
	 * 
	 * @param dataObject the object to save.
	 * @param handler the {@link IResponseHandler} to handle the returned data.
	 */
	void save(RemoteObject dataObject, IResponseHandler handler);

	/**
	 * Executes the 'delete' method on the controller.
	 * 
	 * @param id the unique id (primary key) of the object to delete.
	 * @param handler the {@link IResponseHandler} to handle the server response.
	 */
	void delete(Object id, IResponseHandler handler);

	/**
	 * Executes the 'list' method on the controller.
	 * 
	 * @param handler the {@link IResponseHandler} to handle the returned data.
	 */
	void list(IResponseHandler handler);

	/**
	 * Executes the 'list' method on the controller.
	 * 
	 * @param startPage the first page of results to return.
	 * @param handler the {@link IResponseHandler} to handle the returned data.
	 */
	void list(Filter filter, IResponseHandler handler);

}