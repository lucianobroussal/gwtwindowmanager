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

import org.gwm.splice.client.service.data.RemoteStatus;
import org.gwm.splice.client.service.data.RemoteObject;
import org.gwm.splice.client.service.data.ValidationError;

/**
 * The interface used to handle responses from {@link IRemoteService} requests.
 * 
 */
public interface IResponseHandler {
	/**
	 * Called when a remote request has been completed successfully.
	 * 
	 * @param data the {@link RemoteObject} returned from the server or null if no data
	 * is available.
	 */
	void onCompletion(RemoteObject data);
	
	/**
	 * Called when a 'raw' remote request has been completed successfully.
	 * 
	 * @param data the data returned from the server or null if no data
	 * is available.
	 */
	void onCompletion(String data);
	
	/**
	 * Called if an error occurs while processing a request.
	 * 
	 * @param error an {@link RemoteStatus} object with the error details.
	 */
	void onError(RemoteStatus error);
	
	/**
	 * Called if a validation error occurs while processing a request.
	 * 
	 * @param error an {@link ValidationError} object with the error details.
	 */
	void onValidationError(ValidationError error);
	
}
