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
package org.gwm.splice.client.schema.def;

import org.gwm.splice.client.schema.AttributeSchema;
import org.gwm.splice.client.schema.DATATYPE;

public class EmailFieldSchema extends AttributeSchema {

	public EmailFieldSchema(int displayOrder, boolean required) {
		
		this.displayOrder = displayOrder;
		this.required = required;
		
		name = "email";
		title = "Email";
		datatype = DATATYPE.STRING;
		min = 3;
		max = 128;
		syntax = "email";
		description = "A valid email address";
		
	}

}
