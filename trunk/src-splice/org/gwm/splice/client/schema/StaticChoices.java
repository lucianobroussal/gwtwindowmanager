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

import java.util.ArrayList;
import java.util.Iterator;

import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.IRemoteObjectPrototype;
import org.gwm.splice.client.service.data.RemoteObject;
import org.gwm.splice.client.service.data.Attributes.Attribute;


public class StaticChoices extends RemoteObject implements IChoices, IRemoteObjectPrototype {
	private ArrayList list = new ArrayList();
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.schema.IChoices#iterator()
	 */
	public Iterator iterator() {
		return list.iterator();
	}
	public void addChoice(String displayText, Object value) {
		list.add(new Choice(displayText, value));
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onInitialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onInitialize(Attributes attributes) {
		for (Iterator iter = attributes.iterator(); iter.hasNext();) {
			Attribute attr = (Attribute) iter.next();
			addChoice(attr.getName(), attr.getValue());
		}
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onSerialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onSerialize(Attributes attributes) {
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Choice choice = (Choice) iter.next();
			attributes.put(choice.getDisplayText(), choice.getValue());
		}
	}
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.IRemoteObjectPrototype#newInstance()
	 */
	public RemoteObject newInstance() {
		return new StaticChoices();
	}
}
