/*
 * Copyright (c) 2006 gwtwindowmanager.org (http://www.gwtwindowmanager.org)
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

package org.gwm.client;

/**
 * FramesManager provides methods to create, access, delete GInternalFrames
 * from your application.
 * 
 * @author luciano broussal
 *
 * Type FramesManager, created on 27 nov. 06 at 16:18:58
 *
 */
public interface FramesManager {

    /**
     * Creates a new instance of GInternalFrame.
     * @return GInternalFrame
     */
	public abstract GInternalFrame newFrame();

    /**
     * Closes All windows managed by FramesManager.
     *
     */
	public abstract void closeAllFrames();

    /**
     * Permits to retrieve a GInternalFrame by its id.
     * 
     * @param id The id of the window
     * @return GInternalFrame
     */
	public abstract GInternalFrame getFrame(String id);

    /**
     * Permits to retrieve all GInternalFrame managed by FramesManager.
     * 
     * @return GInternalFrame[]
     */
	public abstract GInternalFrame[] getAllFrames();

}