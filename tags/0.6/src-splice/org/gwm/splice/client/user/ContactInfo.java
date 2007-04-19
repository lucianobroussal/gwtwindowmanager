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
package org.gwm.splice.client.user;

import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.RemoteObject;

public class ContactInfo extends RemoteObject {

	private long id;
	private long parentId;
	private String altEmail;
	private String webSite;
	private String postalAddress;
	private String physicalAddress;
	private String city;
	private String state;
	private String postCode;
	private String country;
	private String phone;
	private String altPhone;
	private String mobilePhone;
	private String fax;
	private String notes;


	protected void onInitialize(Attributes attributes) {
		id = attributes.getLongValue("id");
		parentId = attributes.getLongValue("parentId");
		altEmail = attributes.getStringValue("altEmail");
		webSite = attributes.getStringValue("webSite");
		postalAddress = attributes.getStringValue("postalAddress");
		physicalAddress = attributes.getStringValue("physicalAddress");
		city = attributes.getStringValue("city");
		state = attributes.getStringValue("state");
		postCode = attributes.getStringValue("postCode");
		country = attributes.getStringValue("country");
		phone = attributes.getStringValue("phone");
		altPhone = attributes.getStringValue("altPhone");
		mobilePhone = attributes.getStringValue("mobilePhone");
		fax = attributes.getStringValue("fax");
		notes = attributes.getStringValue("notes");
	}

	protected void onSerialize(Attributes attributes) {
		attributes.put("id", id);
		attributes.put("parentId", parentId);
		attributes.put("altEmail", altEmail);
		attributes.put("webSite", webSite);
		attributes.put("postalAddress", postalAddress);
		attributes.put("physicalAddress", physicalAddress);
		attributes.put("city", city);
		attributes.put("state", state);
		attributes.put("postCode", postCode);
		attributes.put("country", country);
		attributes.put("phone", phone);
		attributes.put("altPhone", altPhone);
		attributes.put("mobilePhone", mobilePhone);
		attributes.put("fax", fax);
		attributes.put("notes", notes);
	}

}

