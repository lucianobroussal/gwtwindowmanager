/*
 * Copyright (c) 2007 gwtwindowmanager.org (http://www.gwtwindowmanager.org)
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

package org.gwm.client.event;

import org.gwm.client.GInternalFrame;


/**
 * This class is an event object and has a GInternalFrame as the source.
 */
public class GInternalFrameEvent {
	private GInternalFrame source;
	
	/**
	 * Build a GInternalFrameEvent from a GInternalFrame as the source.
	 * @param source
	 */
	public GInternalFrameEvent(GInternalFrame source){
		this.source = source;
	}
	
	/**
	 * Returns the source of the event.
	 * @return
	 */
	public GInternalFrame getGInternalFrame(){
		return this.source;
	}
}
