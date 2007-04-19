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
package org.gwm.splice.client.service.query;


public class SortOrder {
	
	public static final int ASC = 0;
	public static final int DESC = 1;

	String columnName;
	int direction;
	
	public SortOrder(String columnName) {
		this(columnName, ASC);
	}

	public SortOrder(String columnName, int direction) {
		this.columnName = columnName;
		this.direction = direction;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return columnName + "," + String.valueOf(direction);
	}

	/////////////////////////////////////////////////////////////////////////////////
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
