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
package org.gwm.splice.client.form;

public class ColumnInfo {

	private String columnName;
	private String title;
	private int defaultWidth;
	private boolean clickable = false;
	private boolean filterField = false;
	
	// //////////////////////////////////////////////////////////////////////////////////

	public ColumnInfo(String columnName, String title, int defaultWidth, boolean clickable, boolean filterField) {
		super();
		this.columnName = columnName;
		this.title = title;
		this.defaultWidth = defaultWidth;
		this.clickable = clickable;
		this.filterField = filterField;
	}

	
	
	// //////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the clickable
	 */
	public boolean isClickable() {
		return clickable;
	}
	/**
	 * @param clickable the clickable to set
	 */
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the defaultWidth
	 */
	public int getDefaultWidth() {
		return defaultWidth;
	}
	/**
	 * @param defaultWidth the defaultWidth to set
	 */
	public void setDefaultWidth(int defaultWidth) {
		this.defaultWidth = defaultWidth;
	}
	/**
	 * @return the filterField
	 */
	public boolean isFilterField() {
		return filterField;
	}
	/**
	 * @param filterField the filterField to set
	 */
	public void setFilterField(boolean filterField) {
		this.filterField = filterField;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
