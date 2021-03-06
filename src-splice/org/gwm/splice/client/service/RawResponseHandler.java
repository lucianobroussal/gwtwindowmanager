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

import org.gwm.splice.client.dialog.MessageBox;
import org.gwm.splice.client.service.data.RemoteObject;
import org.gwm.splice.client.service.data.RemoteStatus;
import org.gwm.splice.client.service.data.ValidationError;

/**
 * Utility class that implements common methods.
 *  
 * @author Andy Scholz (andy.scholz@gmail.com)
 *
 */
public abstract class RawResponseHandler implements IResponseHandler {

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IResponseHandler#onError(org.gwm.splice.client.service.data.RemoteStatus)
	 */
	public void onError(RemoteStatus error) {
		MessageBox.showError(error.getMessage());
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IResponseHandler#onValidationError(org.gwm.splice.client.service.data.ValidationError)
	 */
	public void onValidationError(ValidationError error) {
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IResponseHandler#onCompletion(org.gwm.splice.client.service.data.RemoteObject)
	 */
	public void onCompletion(RemoteObject data) {
	}

}
