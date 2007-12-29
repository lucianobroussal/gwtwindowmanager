/*
 * Copyright (c) 2006-2007 Luciano Broussal <luciano.broussal AT gmail.com>
 * (http://www.gwtwindowmanager.org)
 * 
 * Main Contributors :
 *      Johan Vos,Andy Scholz,Marcelo Emanoel  
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
 * This interface should be implemented for those objects who want to
 * know about the events of a GFrame.
 * 
 */
public interface GFrameListener {
    
    public boolean onFrameClosing(GFrameEvent evt);

    public void onFrameMaximizing(GFrameEvent evt);

    public void frameResized(GFrameEvent evt);

    public void frameOpened(GFrameEvent evt);

    public void frameClosed(GFrameEvent evt);

    public void frameMaximized(GFrameEvent evt);

    public void frameMinimized(GFrameEvent evt);

    public void frameIconified(GFrameEvent evt);

    public void frameRestored(GFrameEvent evt);

    public void frameMoved(GFrameEvent evt);

    public void frameMoving(GFrameEvent event);

    public void frameSelected(GFrameEvent event);

    public void frameGhostMoving(int top, int left, GFrameEvent event);

    public void frameGhostMoved(int top, int left, GFrameEvent event);


}
