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

import java.util.HashMap;
import java.util.Map;


public class RemoteServiceRegistry {
	
	private static RemoteServiceRegistry instance;
	private Map registry;

	public static RemoteServiceRegistry getInstance() {
		if(instance == null) {
			instance = new RemoteServiceRegistry();
		}
		return instance;
	}
	
	private RemoteServiceRegistry() {
		registry = new HashMap();
	}
	
	public void registerService(String name, IRemoteService service) {
		registry.put(name, service);
	}
	
	public void deregisterService(String name) {
		registry.remove(name);
	}
	
	public IRemoteService getService(String name) {
		return (IRemoteService) registry.get(name);
	}
}
