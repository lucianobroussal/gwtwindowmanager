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

import java.util.Date;

import org.gwm.splice.client.schema.AttributeSchema;
import org.gwm.splice.client.schema.ObjectSchema;
import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.IRemoteObjectPrototype;
import org.gwm.splice.client.service.data.RemoteObject;


public class JUser extends RemoteObject implements IRemoteObjectPrototype {

	private int id;
	private String name;
	private String username;
	private String email;
	private String password;
	private String usertype;
	private boolean block;
	private boolean sendEmail;
	private boolean gid;
	private Date registerDate;
	private Date lastvisitDate;
	private String activation;
	private String params;

	///////////////////////////////////////////////////////////////////////////////////////

	public static ObjectSchema getObjectSchema() {
		ObjectSchema os = new ObjectSchema();
		os.put(new AttributeSchema(2, "id", "ID", null, "id", 11, 0, false));
		os.put(new AttributeSchema(0, "name", "Name", null, "name", 50, 1, true));
		os.put(new AttributeSchema(0, "username", "Username", null, "name", 25, 2, true));
		os.put(new AttributeSchema(0, "email", "Email", null, "email", 100, 3, true));
		os.put(new AttributeSchema(0, "password", "Password", null, "password", 100, 4, true));
		os.put(new AttributeSchema(0, "usertype", "Usertype", null, "name", 25, 5, true));
		os.put(new AttributeSchema(5, "block", "Block?", null, "yesno", 4, 6, true));
		os.put(new AttributeSchema(5, "sendEmail", "Send Email?", null, "yesno", 0, 7, true));
		os.put(new AttributeSchema(5, "gid", "gid", null, "_hidden_", 3, 8, false));
		os.put(new AttributeSchema(6, "registerDate", "Register Date", null, "date", 0, 9, false));
		os.put(new AttributeSchema(6, "lastvisitDate", "Last Visit Date", null, "date", 0, 10, false));
		os.put(new AttributeSchema(0, "activation", "activation", null, "_hidden_", 100, 11, false));
		os.put(new AttributeSchema(0, "params", "params", null, "_hidden_", 0, 12, false));

		return os;
	}

	///////////////////////////////////////////////////////////////////////////////////////



	protected void onInitialize(Attributes attributes) {
		id = attributes.getIntValue("id");
		name = attributes.getStringValue("name");
		username = attributes.getStringValue("username");
		email = attributes.getStringValue("email");
		password = attributes.getStringValue("password");
		usertype = attributes.getStringValue("usertype");
		block = attributes.getBooleanValue("block");
		sendEmail = attributes.getBooleanValue("sendEmail");
		gid = attributes.getBooleanValue("gid");
		registerDate = attributes.getDateValue("registerDate");
		lastvisitDate = attributes.getDateValue("lastvisitDate");
		activation = attributes.getStringValue("activation");
		params = attributes.getStringValue("params");
	}

	protected void onSerialize(Attributes attributes) {
		attributes.put("id", id);
		attributes.put("name", name);
		attributes.put("username", username);
		attributes.put("email", email);
		attributes.put("password", password);
		attributes.put("usertype", usertype);
		attributes.put("block", block);
		attributes.put("sendEmail", sendEmail);
		attributes.put("gid", gid);
		attributes.put("registerDate", registerDate);
		attributes.put("lastvisitDate", lastvisitDate);
		attributes.put("activation", activation);
		attributes.put("params", params);
	}

	/**
	 * @return the activation
	 */
	public String getActivation() {
		return activation;
	}

	/**
	 * @param activation the activation to set
	 */
	public void setActivation(String activation) {
		this.activation = activation;
	}

	/**
	 * @return the block
	 */
	public boolean isBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(boolean block) {
		this.block = block;
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
	 * @return the gid
	 */
	public boolean isGid() {
		return gid;
	}

	/**
	 * @param gid the gid to set
	 */
	public void setGid(boolean gid) {
		this.gid = gid;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the lastvisitDate
	 */
	public Date getLastvisitDate() {
		return lastvisitDate;
	}

	/**
	 * @param lastvisitDate the lastvisitDate to set
	 */
	public void setLastvisitDate(Date lastvisitDate) {
		this.lastvisitDate = lastvisitDate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
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
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * @return the sendEmail
	 */
	public boolean isSendEmail() {
		return sendEmail;
	}

	/**
	 * @param sendEmail the sendEmail to set
	 */
	public void setSendEmail(boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}

	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public RemoteObject newInstance() {
		return new JUser();
	}

}

