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

import org.gwm.splice.client.form.ColumnInfo;
import org.gwm.splice.client.form.DynaFormWindow;
import org.gwm.splice.client.form.ResultListWindow;
import org.gwm.splice.client.service.IRemoteDataService;

public class UserResultListWindow extends ResultListWindow {

	static ColumnInfo[] columns;
	
	static {
		columns = new ColumnInfo[4];
		columns[0] = new ColumnInfo("id","ID", 60, false, false);
		columns[1] = new ColumnInfo("name", "Name", 150, true, true);
		columns[2] = new ColumnInfo("username", "Username", 150, false, false);
		columns[3] = new ColumnInfo("email", "Email", 150, false, false);
	}

	public UserResultListWindow(String name, IRemoteDataService service) {
		super(name, columns, service, null, true, true, "apps/userconfig");
		show();
	}

	// //////////////////////////////////////////////////////////////////////////////////
	 
	/* (non-Javadoc)
	 * @see org.gwm.splice.client.form.ResultListWindow#onNewItem()
	 */
	protected void onNewItem() {
		DynaFormWindow win = new DynaFormWindow("New User", JUser.getObjectSchema());
		win.show();
	}


	
	
	
}
