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

import org.gwm.splice.client.service.data.Attributes;
import org.gwm.splice.client.service.data.IRemoteObjectPrototype;
import org.gwm.splice.client.service.data.RemoteObject;

/**
 * Provides filter information for a list or query operation.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 * 
 */
public class Filter extends RemoteObject implements IRemoteObjectPrototype {

	private int pageSize;

	private int startPage;

	private SortOrder sortOrder;

	private String[] columns;

	private String filter;

	// //////////////////////////////////////////////////////////////////////////////////

	public Filter() {
		super();
	}

	
	// //////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.IRemoteObjectPrototype#newInstance()
	 */
	public RemoteObject newInstance() {
		return new Filter();
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onInitialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onInitialize(Attributes attributes) {
		setPageSize(attributes.getIntValue("pageSize"));
		setStartPage(attributes.getIntValue("startPage"));
		setFilter(attributes.getStringValue("filter"));
		Attributes aso = attributes.getAttributes("sortOrder");
		if(aso != null) {
			sortOrder = new SortOrder(aso.getStringValue("columnName"), aso.getIntValue("direction"));
		}
		Object[] ca = (Object[]) attributes.get("columns");
		if(ca != null) {
			columns = new String[ca.length];
			for (int i = 0; i < ca.length; i++) {
				columns[i] = (String) ca[i];
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onSerialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onSerialize(Attributes attributes) {
		attributes.put("pageSize", pageSize);
		attributes.put("startPage", startPage);
		if(filter != null) {
			attributes.put("filter", filter);
		}
		if(sortOrder != null) {
			Attributes attrs = new Attributes();
			attrs.put("columnName", sortOrder.getColumnName());
			attrs.put("direction", sortOrder.getDirection());
			attributes.put("sortOrder", attrs);
		}
		if(columns != null) {
			attributes.put("columns", columns);
		}
	}


	// //////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns an {@link Attributes} collection suitable for url query parameters conversion.
	 * <p>
	 * If a value is null or <= 0, it is exlcluded.
	 * <p>
	 * The SortOrder and columns values are converted to comma-sperated lists.
	 * <p>
	 * *Note values are NOT URL-encoded/escaped.
	 * 
	 * @return an {@link Attributes} collection.
	 */
	public Attributes toParameters() {
		Attributes attrs = new Attributes();
		
		if(pageSize > 0) {
			attrs.put("pageSize", pageSize);
		}
		if(startPage > 0) {
			attrs.put("startPage", startPage);
		}
		if(sortOrder != null) {
			attrs.put("sortOrder", sortOrder.toString());
		}
		if(filter != null) {
			attrs.put("filter", filter);
		}
		
		return attrs;
	}

	// //////////////////////////////////////////////////////////////////////////////////

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

}
