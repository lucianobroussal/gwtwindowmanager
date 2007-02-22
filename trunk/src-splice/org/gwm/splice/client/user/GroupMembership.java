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

public class GroupMembership extends RemoteObject {

	private long id;
	private long groupId;
	private long userId;
	private String position;


	protected void onInitialize(Attributes attributes) {
		id = attributes.getLongValue("id");
		groupId = attributes.getLongValue("groupId");
		userId = attributes.getLongValue("userId");
		position = attributes.getStringValue("position");
	}

	protected void onSerialize(Attributes attributes) {
		attributes.put("id", id);
		attributes.put("groupId", groupId);
		attributes.put("userId", userId);
		attributes.put("position", position);
	}

	/**
	 * @return the groupId
	 */
	public long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
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
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

}



