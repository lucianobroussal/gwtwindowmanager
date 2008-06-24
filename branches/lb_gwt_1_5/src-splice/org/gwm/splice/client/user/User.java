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

public class User extends RemoteObject {

	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String middleName1;
	private String middleName2;
	private String lastName;
	private String salutation;
	private String email;
	private String altEmail;
	private String phone;
	private String cellPhone;
	private String address;
	private String city;
	private String state;
	private String postCode;
	private String country;
	private String fax;
	private String description;
	private int authenticationMode;
	private String sessionAttributes;


	protected void onInitialize(Attributes attributes) {
		id = attributes.getLongValue("id");
		userName = attributes.getStringValue("userName");
		password = attributes.getStringValue("password");
		firstName = attributes.getStringValue("firstName");
		middleName1 = attributes.getStringValue("middleName1");
		middleName2 = attributes.getStringValue("middleName2");
		lastName = attributes.getStringValue("lastName");
		salutation = attributes.getStringValue("salutation");
		email = attributes.getStringValue("email");
		altEmail = attributes.getStringValue("altEmail");
		phone = attributes.getStringValue("phone");
		cellPhone = attributes.getStringValue("cellPhone");
		address = attributes.getStringValue("address");
		city = attributes.getStringValue("city");
		state = attributes.getStringValue("state");
		postCode = attributes.getStringValue("postCode");
		country = attributes.getStringValue("country");
		fax = attributes.getStringValue("fax");
		description = attributes.getStringValue("description");
		authenticationMode = attributes.getIntValue("authenticationMode");
		sessionAttributes = attributes.getStringValue("sessionAttributes");
	}

	protected void onSerialize(Attributes attributes) {
		attributes.put("id", id);
		attributes.put("userName", userName);
		attributes.put("password", password);
		attributes.put("firstName", firstName);
		attributes.put("middleName1", middleName1);
		attributes.put("middleName2", middleName2);
		attributes.put("lastName", lastName);
		attributes.put("salutation", salutation);
		attributes.put("email", email);
		attributes.put("altEmail", altEmail);
		attributes.put("phone", phone);
		attributes.put("cellPhone", cellPhone);
		attributes.put("address", address);
		attributes.put("city", city);
		attributes.put("state", state);
		attributes.put("postCode", postCode);
		attributes.put("country", country);
		attributes.put("fax", fax);
		attributes.put("description", description);
		attributes.put("authenticationMode", authenticationMode);
		attributes.put("sessionAttributes", sessionAttributes);
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the altEmail
	 */
	public String getAltEmail() {
		return altEmail;
	}

	/**
	 * @param altEmail the altEmail to set
	 */
	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
	}

	/**
	 * @return the authenticationMode
	 */
	public int getAuthenticationMode() {
		return authenticationMode;
	}

	/**
	 * @param authenticationMode the authenticationMode to set
	 */
	public void setAuthenticationMode(int authenticationMode) {
		this.authenticationMode = authenticationMode;
	}

	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @param cellPhone the cellPhone to set
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the middleName1
	 */
	public String getMiddleName1() {
		return middleName1;
	}

	/**
	 * @param middleName1 the middleName1 to set
	 */
	public void setMiddleName1(String middleName1) {
		this.middleName1 = middleName1;
	}

	/**
	 * @return the middleName2
	 */
	public String getMiddleName2() {
		return middleName2;
	}

	/**
	 * @param middleName2 the middleName2 to set
	 */
	public void setMiddleName2(String middleName2) {
		this.middleName2 = middleName2;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * @param salutation the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	/**
	 * @return the sessionAttributes
	 */
	public String getSessionAttributes() {
		return sessionAttributes;
	}

	/**
	 * @param sessionAttributes the sessionAttributes to set
	 */
	public void setSessionAttributes(String sessionAttributes) {
		this.sessionAttributes = sessionAttributes;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}


