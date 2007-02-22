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
package org.gwm.splice.client.logger;

public class LogItem {

	public static final int INFO = 0;
	public static final int WARN = 1;
	public static final int ERROR = 2;
	public static final int SEVERE = 3;
	public static final int FATAL = 4;
	
	//////////////////////////////////////////////////////////////////////
	
	public int status;
	public String message;
	public Object source;
	
	public LogItem(int status, String message, Object source) {
		super();
		this.status = status;
		this.message = message;
		this.source = source;
	}

	public LogItem(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getSource() {
		return source;
	}
	public void setSource(Object source) {
		this.source = source;
	}
	
	
}
