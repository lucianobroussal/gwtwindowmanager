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

import java.util.ArrayList;

import org.gwm.splice.client.dialog.MessageBox;
import org.gwm.splice.client.service.IRemoteDataService;
import org.gwm.splice.client.service.IResponseHandler;
import org.gwm.splice.client.service.data.RemoteObject;
import org.gwm.splice.client.service.data.RemoteStatus;
import org.gwm.splice.client.service.data.ResultList;
import org.gwm.splice.client.service.data.ValidationError;
import org.gwm.splice.client.service.query.Filter;
import org.gwm.splice.client.toolbar.widgets.FilterWidget;
import org.gwm.splice.client.window.AbstractWindow;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResultListWindow extends AbstractWindow implements ClickListener, IResponseHandler {

	private ArrayList listeners = new ArrayList();
	private String defaultIconName;
	private ResultList resultList;
	private IRemoteDataService service;
	private Filter filter;
	private	ResultListGrid grid;
	private ColumnInfo[] columns;
	

	// //////////////////////////////////////////////////////////////////////////////////

	public ResultListWindow(String name, ColumnInfo[] columns, ResultList resultList, boolean selectable) {
		this(name, columns, resultList, selectable, false, null);
	}
		
	public ResultListWindow(String name, ColumnInfo[] columns, ResultList resultList, boolean selectable, String defaultIconName) {
		this(name, columns, resultList, selectable, true, defaultIconName);
		
	}
	public ResultListWindow(String name, ColumnInfo[] columns, ResultList resultList, boolean selectable, boolean showIcons) {
		this(name, columns, resultList, selectable, showIcons, null);
	}
	public ResultListWindow(String name, ColumnInfo[] columns, ResultList resultList, boolean selectable, boolean showIcons, String defaultIconName) {
		
		super(null, false, true, true);
		this.resultList = resultList;
		init(name, columns, selectable, showIcons, defaultIconName);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	public ResultListWindow(String name, ColumnInfo[] columns, IRemoteDataService service, Filter filter, boolean selectable, boolean showIcons, String defaultIconName) {
		
		super(null, false, true, true);
		
		this.service = service;
		this.filter = filter;
		
		init(name, columns, selectable, showIcons, defaultIconName);
		
		if(this.filter == null) {
			this.filter = new Filter();
		}
		
		String[] cols = new String[columns.length];
		for (int i = 0; i < columns.length; i++) {
			cols[i] = columns[i].getColumnName();
		}
		this.filter.setColumns(cols);
		service.list(this.filter, this);
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	protected void init(String name, ColumnInfo[] columns, boolean selectable, boolean showIcons, String defaultIconName) {
	
		this.defaultIconName = defaultIconName;
		this.columns = columns;

//		String strPageOfPages =
//			" - Page " + ((resultList.getCurrentPage())+1)
//			+ " of " + resultList.getTotalPages() ;
//		this.setName(name + strPageOfPages);

		this.setName(name);

		if(defaultIconName == null) {
			grid = new ResultListGrid(columns, resultList, selectable, showIcons);
		}
		else {
			grid = new ResultListGrid(columns, resultList, selectable, defaultIconName);
		}
		
		grid.setWidth("100%");
		
		toolbar.addButton("actions/filenew", "new", 0);
//		toolbar.addButton("actions/editcopy", "copy", 0);
//		toolbar.addButton("actions/editcut", "cut", 0);
//		toolbar.addButton("actions/editpaste", "paste", 0);
		toolbar.addButton("actions/editdelete", "delete", 0);
		
		toolbar.addSeperator();
		
		toolbar.addWidget(new FilterWidget("filter", 0, "filter:"));
		
		controlbar.addButton("actions/ok", "ok", 0, "OK", "Just do it!");
		controlbar.addCloseButton();
		
		setWidth(600);
		setHeight(400);
		setContent(grid);
		
	}
	
	// ////////////////////////////////////////////////////////////////////////////////
	
	public void onClick(Widget widget) {
// ColumnLabel cl = (ColumnLabel) widget;
// Object[] row = (Object[]) rows[cl.rowIndex];
// for (Iterator iter = listeners.iterator(); iter.hasNext();) {
// IResultListWindowListener listener = (IResultListWindowListener) iter.next();
// listener.onItemClicked(row, cl.colIndex);
// }
	}
	
	public void addListener(IResultListWindowListener listener) {
		listeners.add(listener);
	}
	public void removeListener(IResultListWindowListener listener) {
		listeners.remove(listener);
	}

	// ////////////////////////////////////////////////////////////////////////////////

	
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.window.AbstractWindow#onActionClicked(com.google.gwt.user.client.ui.Widget, java.lang.String, int)
	 */
	public boolean onActionClicked(Widget widget, String name, int actionID) {
		
		if("filter".equals(name)) {
			String text = ((FilterWidget)widget).getText();
			
			String filt = "";
			for (int i = 0; i < columns.length; i++) {
				if(columns[i].isFilterField()) {
					if(filt.length() > 0) {
						filt += " or ";
					}
					filt += columns[i].getColumnName() + " like '%" + text + "%'";
				}
			}
			filter.setFilter(filt);
			service.list(filter, this);
			
			return false;
		}
		else if("new".equals(name)) {
			onNewItem();
		}
		
		return true;
	}
	
	protected void onNewItem() {
		
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IResponseHandler#onCompletion(org.gwm.splice.client.service.data.RemoteObject)
	 */
	public void onCompletion(RemoteObject data) {
		grid.setResultList((ResultList)data);
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IResponseHandemail like '%com%'ler#onCompletion(java.lang.String)
	 */
	public void onCompletion(String data) {
		// not used
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IResponseHandler#onError(org.gwm.splice.client.service.data.RemoteStatus)
	 */
	public void onError(RemoteStatus error) {
		MessageBox.showError("Error loading list: " + error.getMessage());
	}

	/* (non-Javadoc)
	 * @see org.gwm.splice.client.service.IResponseHandler#onValidationError(org.gwm.splice.client.service.data.ValidationError)
	 */
	public void onValidationError(ValidationError error) {
		// not used
	}

}
