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

import org.gwm.splice.client.desktop.DesktopManager;
import org.gwm.splice.client.service.data.MimeTypeRegistry;
import org.gwm.splice.client.service.data.ResultList;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

public class ResultListGrid extends Grid implements TableListener {
	
	public static final int CHECKBOX_WIDTH = 22;
	public static final int ICON_WIDTH = 20;
	
	private static final int EVENT_CELL_CLICKED = 0;
	private static final int EVENT_SOMETHING_SELECTED = 1;
	private static final int EVENT_NOTHING_SELECTED = 2;
	
	
	private ColumnInfo[] columns;
	private ResultList resultList;
	private ArrayList listeners = new ArrayList();
	
	private boolean selectable = false;
	private boolean showIcons = false;
	
	private int firstColumn;
	private int lastColumn;

	private int mimeTypeColumn = -1;
	
	private int iconColumn = 0;
	private int checkBoxColumn = 0;
	
	private String defaultIconName;

	public ResultListGrid(ColumnInfo[] columns) {
		this(columns, null, false, false, null);
	}
	
	public ResultListGrid(ColumnInfo[] columns, ResultList resultList) {
		this(columns, resultList, false, false, null);
	}
	
	public ResultListGrid(ColumnInfo[] columns, ResultList resultList, boolean selectable) {
		this(columns, resultList, selectable, false, null);
	}
	
	public ResultListGrid(ColumnInfo[] columns, ResultList resultList, boolean selectable, String defaultIconName) {
		this(columns, resultList, selectable, true, defaultIconName);
	}
	
	public ResultListGrid(ColumnInfo[] columns, ResultList resultList, boolean selectable, boolean showIcons) {
		this(columns, resultList, selectable, showIcons, null);
	}
	
	public ResultListGrid(ColumnInfo[] columns, ResultList resultList, boolean selectable, boolean showIcons, String defaultIconName) {
		super(1, columns.length + (selectable ? 1 : 0) + (showIcons ? 1 : 0));
		this.columns = columns;
		this.resultList = resultList;
		this.selectable = selectable;
		this.showIcons = showIcons;
		this.defaultIconName = defaultIconName;
		init();
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	private void init() {
		addTableListener(this);
		
		clear();
		
		setStyleName("resultList");
//		setWidth(calculateWidth());
		setWidth("100%");
		setCellPadding(0);
		setCellSpacing(0);
		
		firstColumn = (selectable ? 1 : 0) + (showIcons ? 1 : 0);
		lastColumn = columns.length + firstColumn;
		
		if(showIcons) {
			checkBoxColumn = 0;
			iconColumn = (selectable ? 1 : 0);
			
			for (int i = 0; i < columns.length; i++) {
				if("mimeType".equals(columns[i])) {
					mimeTypeColumn = i;
				}
			}
			getCellFormatter().setWidth(0, iconColumn, String.valueOf(ICON_WIDTH) + "px");
		}
		
		if(selectable) {
			CheckBox cb = new CheckBox();
			cb.setTitle("Select or deselect all items");
			cb.addClickListener(new ClickListener() {
				public void onClick(Widget w) {
					CheckBox box = (CheckBox) w;
					boolean checked = box.isChecked();
					for (int i = 1; i <= resultList.size(); i++) {
						CheckBox rcb = (CheckBox) getWidget(i, checkBoxColumn);
						rcb.setChecked(checked);
						notifyEvent(checked ? EVENT_SOMETHING_SELECTED : EVENT_NOTHING_SELECTED, 0, 0);
					}
				}
			});
			setWidget(0, checkBoxColumn, cb);
			getCellFormatter().setWidth(0, checkBoxColumn, String.valueOf(CHECKBOX_WIDTH) + "px");
		}
		
		for (int i = 0; i < columns.length; i++) {
			String title = columns[i].getTitle();
			setText(0, i+firstColumn, title);
			String w = columns[i].getDefaultWidth() != 0 ? String.valueOf(columns[i].getDefaultWidth()) + "px" : "100px";
			getCellFormatter().setWidth(0, i+firstColumn, w);
		}
		
		getRowFormatter().setStyleName(0, "columnsHeader");

		loadData();

	}

	private void loadData() {
		if(resultList == null) {
			return;
		}

		resizeRows(resultList.size() + 1);
		
		Object[] rows = resultList.getRows();
		if(rows == null) {
			return;
		}
		
		for (int r = 1; r <= rows.length; r++) {
			Object[] row = (Object[]) rows[r-1];
			if(selectable) {
				setWidget(r, checkBoxColumn, new CheckBox());
			}
			if(showIcons) {
				MimeTypeRegistry mr = MimeTypeRegistry.getInstance();
				Image img = null;
				if(mimeTypeColumn >= 0 && row[mimeTypeColumn] != null) {
					img = mr.getSmallIcon((String) row[mimeTypeColumn]);
				}
				else {
					if(defaultIconName == null) {
						img = mr.getDefaultSmallIcon();
					}
					else {
						img = new Image(DesktopManager.getInstance().getSmallIconUrl(defaultIconName));
					}
				}
				setWidget(r, iconColumn, img);
			}
			for (int c = 0; c < columns.length; c++) {
				setText(r, c+firstColumn, row[c] != null ? row[c].toString() : "");
				if(columns[c].isClickable() && row[c] != null) {
					getCellFormatter().setStyleName(r, c+firstColumn, "clickableCell");
				}
			}
			getRowFormatter().setStyleName(r, (r & 1) != 0 ? "evenRow" : "oddRow");
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////

	private String calculateWidth() {
		int w = (selectable ? CHECKBOX_WIDTH : 0) + (showIcons ? ICON_WIDTH : 0);
		for (int i = 0; i < columns.length; i++) {
			w += columns[i].getDefaultWidth();
		}
		return String.valueOf(w) + "px";
	}
	
	// //////////////////////////////////////////////////////////////////////////////////

	private void notifyEvent(int event, int row, int col) {
		
	}

	
	public void onCellClicked(SourcesTableEvents arg0, int row, int col) {
		if(this.selectable && row > 0) {
			if(col == 0) {
				CheckBox box = (CheckBox) getWidget(0, 0);
				box.setChecked(false);
			}
		}
		
	}

	// //////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the resultList
	 */
	public ResultList getResultList() {
		return resultList;
	}

	/**
	 * @param resultList the resultList to set
	 */
	public void setResultList(ResultList resultList) {
		this.resultList = resultList;
		loadData();
	}


}
