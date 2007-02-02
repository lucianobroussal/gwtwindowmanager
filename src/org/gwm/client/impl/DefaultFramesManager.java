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

package org.gwm.client.impl;

import java.util.HashMap;
import java.util.Iterator;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;
import org.gwm.client.event.GInternalFrameEvent;
import org.gwm.client.event.GInternalFrameListener;

/**
 * 
 * @author lb
 * 
 */
public class DefaultFramesManager implements FramesManager , GInternalFrameListener {

    private HashMap frames;

    private long windowCount;

    /** *************************************************************** */
    /** ****************************** PUBLIC ************************* */
    /** *************************************************************** */

    public DefaultFramesManager() {
        frames = new HashMap();
    }

    public GInternalFrame getFrame(String id) {
        return (GInternalFrame) frames.get(id);
    }

    public GInternalFrame[] getAllFrames() {
        GInternalFrame[] allWindows = new GInternalFrame[frames.values().size()];
        int index = 0;
        for (Iterator iter = frames.values().iterator(); iter.hasNext();) {
            GInternalFrame window = (GInternalFrame) iter.next();
            allWindows[index] = window;
            index++;
        }
        return allWindows;
    }

    public GInternalFrame newFrame(String title) {
        String windowId = "GwmFrame_" + windowCount++;
        GInternalFrame window = new DefaultGInternalFrame(windowId);
        window.setCaption(title);
        frames.put(windowId, window);
        window.addInternalFrameListener(this);
        return window;
    }

    public void frameClosed(GInternalFrameEvent evt) {
        frames.remove(evt.getGInternalFrame().getId());
    }

    public void frameIconified(GInternalFrameEvent evt) {
    }

    public void frameMaximized(GInternalFrameEvent evt) {
    }

    public void frameMinimized(GInternalFrameEvent evt) {
    }

    public void frameMoved(GInternalFrameEvent evt) {
    }

    public void frameOpened(GInternalFrameEvent evt) {
    }

    public void frameResized(GInternalFrameEvent evt) {
    }

    public void frameRestored(GInternalFrameEvent evt) {
    }
}
