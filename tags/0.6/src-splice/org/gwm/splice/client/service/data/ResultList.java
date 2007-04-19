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
package org.gwm.splice.client.service.data;

import java.util.Iterator;

import org.gwm.splice.client.service.query.Filter;



/**
 * A class that 'wraps' a {@link RemoteObject} to facilitate retrieving search results.
 * 
 * @author Andy Scholz (andy.scholz@gmail.com)
 *
 */
public class ResultList extends RemoteObject implements IRemoteObjectPrototype {
	
	Object[] rows;
	long totalRecords;
	Filter filter;
	
	public ResultList() {
		super();
	}

	// //////////////////////////////////////////////////////////////////////////////////
	
	public int size() {
		if(rows != null) {
			return rows.length;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator iterator() {
		return new RowIterator();
	}
	
	/**
	 * Returns the specified row or null if no rows or index out-of-bounds.
	 * 
	 * @param index an integer between 0 and {@link ResultList#size()}.
	 * @return an Object array if valid index.
	 */
	public Object[] getRow(int index) {
		if(index >= size() || rows == null) {
			return null;
		}
		return (Object[]) rows[index];
	}
	
	public int getTotalPages() {
		return 0;
	}
	
	// //////////////////////////////////////////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.IRemoteObjectPrototype#newInstance()
	 */
	public RemoteObject newInstance() {
		return new ResultList();
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onInitialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onInitialize(Attributes attributes) {
		totalRecords = attributes.getIntValue("totalRecords");
		rows = (Object[]) attributes.get("rows");
		filter = (Filter) getRemoteObject(attributes.get("filter"));
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.data.RemoteObject#onSerialize(org.gwm.splice.client.service.data.Attributes)
	 */
	protected void onSerialize(Attributes attributes) {
		attributes.put("totalRecords", totalRecords);
		attributes.put("rows", rows);
		attributes.put("filter", serialize(filter));
	}

	// //////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Implements an Iterator for the the rows. 
	 */
	private class RowIterator implements Iterator {
		int index = 0;

		public boolean hasNext() {
			return index >= size();
		}

		public Object next() {
			return rows[index++];
		}

		public void remove() {
		}
		
	}

	// //////////////////////////////////////////////////////////////////////////////////
	
	public int getCurrentPage() {
		return filter != null ? filter.getStartPage() : 0;
	}
	
	// //////////////////////////////////////////////////////////////////////////////////
	
	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public Object[] getRows() {
		return rows;
	}

	public void setRows(Object[] rows) {
		this.rows = rows;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	
}
