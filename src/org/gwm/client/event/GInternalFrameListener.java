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

/**
 * 
 */
package org.gwm.client.event;


/**
 * This is the interface should be implemented for those objects who want to
 * know about the events of a GInternalFrame.
 * 
 * @author Marcelo Emanoel
 * @since 30/11/2006
 */
public interface GInternalFrameListener {

    public void frameResized(GInternalFrameEvent evt);

    public void frameOpened(GInternalFrameEvent evt);

    public void frameClosed(GInternalFrameEvent evt);

    public void frameMaximized(GInternalFrameEvent evt);

    public void frameMinimized(GInternalFrameEvent evt);

    public void frameIconified(GInternalFrameEvent evt);

    public void frameRestored(GInternalFrameEvent evt);

    public void frameMoved(GInternalFrameEvent evt);

}
