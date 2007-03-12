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
package org.gwm.splice.client.schema;

import java.util.Iterator;

/**
 * Interface represents a list of choices a user may enter for a field.
 * 
 * @author andy
 *
 */
public interface IChoices {
	
	/**
	 * Returns an Iterator of all of the choices available.
	 * 
	 * @return
	 */
	Iterator iterator();
	
	/**
	 * Returns an Iterator of filtered choices available.
	 * <p>
	 * The filter is implementation dependent, and may be ignored by a provider
	 * if not supported.
	 * 
	 * @return
	 */
	Iterator iterator(String filter);
	
}
