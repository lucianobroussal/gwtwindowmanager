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
package org.gwm.splice.client.config;

import org.gwm.splice.client.service.data.Attributes;

/**
 * Implemented by classes that support persistent configuration.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 *
 */
public interface IRestorable {
	
	/**
	 * Restores the object to a previous state.
	 * 
	 * @param attributes an {@link Attributes} collection as returned by {@link IRestorable#getConfigAttributes()}.
	 */
	void restore(Attributes attributes);

	/**
	 * Returns a set of configuration attributes.
	 * 
	 * @return an {@link Attributes} collection that can be used in a {@link IRestorable#restore(Attributes)} operation.
	 */
	Attributes getConfigAttributes();
}
