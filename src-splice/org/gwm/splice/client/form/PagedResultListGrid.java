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

import org.gwm.splice.client.desktop.DesktopManager;
import org.gwm.splice.client.service.data.ResultList;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PagedResultListGrid extends VerticalPanel {

//	private String systemIconPath = DesktopManager.getInstance().getSystemIconPath();
	private ResultListGrid grid;
	
	
	public PagedResultListGrid(ColumnInfo[] columns, ResultList resultList, boolean selectable) {
		super();
		
		grid = new ResultListGrid(columns, resultList, selectable);
		grid.setWidth("100%");
		
		add(grid);
		setCellVerticalAlignment(grid, ALIGN_TOP);
		setCellHorizontalAlignment(grid, ALIGN_LEFT);
		setHeight("100%");
	}
	
}
