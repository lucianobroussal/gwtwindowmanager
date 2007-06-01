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

public class Query {

	IQueryExpression expression;
	SortOrder[] sortOrders;
	int page = 1;
	int pageSize = 0;
	
	public Query() {
	}
	public Query(IQueryExpression expression) {
		this.expression = expression;
	}
	public Query(IQueryExpression expression, int page) {
		this.expression = expression;
		this.page = page;
	}
	public Query(IQueryExpression expression, SortOrder[] sortOrders) {
		this.expression = expression;
		this.sortOrders = sortOrders;
	}
	public Query(IQueryExpression expression, SortOrder sortOrder) {
		this.expression = expression;
		this.sortOrders = new SortOrder[]{ sortOrder };
	}
	public Query(IQueryExpression expression, SortOrder sortOrder, int page) {
		this.expression = expression;
		this.sortOrders = new SortOrder[]{ sortOrder };
		this.page = page;
	}
	public Query(IQueryExpression expression, SortOrder[] sortOrders, int page) {
		this.expression = expression;
		this.sortOrders = sortOrders;
		this.page = page;
	}
	public Query(IQueryExpression expression, SortOrder[] sortOrders, int page, int pageSize) {
		this.expression = expression;
		this.sortOrders = sortOrders;
		this.page = page;
		this.pageSize = pageSize;
	}

	/////////////////////////////////////////////////////////////////////////////////

	public SortOrder getSortOrder() {
		if(sortOrders != null && sortOrders.length > 0) {
			return sortOrders[0];
		}
		return null;
	}
	
	public void setSortOrder(SortOrder sortOrder) {
		sortOrders = new SortOrder[]{ sortOrder };
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	public IQueryExpression getExpression() {
		return expression;
	}
	public void setExpression(IQueryExpression expression) {
		this.expression = expression;
	}
	public SortOrder[] getSortOrders() {
		return sortOrders;
	}
	public void setSortOrders(SortOrder[] sortOrders) {
		this.sortOrders = sortOrders;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
