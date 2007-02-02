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

package org.gwm.client.util;

import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

public class GwmUtilities {

    static public void displayAtParentCenter(GInternalFrame frame) {
        if(frame== null)
            throw new IllegalArgumentException("The frame can't be null");
        if(frame.getParentDesktop()!=null){
            diplayAtRelativeGivenWidgetCenter(frame , (UIObject)frame.getParentDesktop());
        }else{
            diplayAtScreenCenter(frame);
        }
    }

    static public void diplayAtScreenCenter(GInternalFrame frame) {
        if(frame== null)
            throw new IllegalArgumentException("The frame can't be null");
        int frameLeft = (Window.getClientWidth() - frame.getWidth())/2;
        int frameTop = (Window.getClientHeight() - frame.getHeight())/2;
        frame.setLocation(frameTop, frameLeft);
        frame.setVisible(true);
        
    }

    static public void diplayAtRelativeGivenWidgetCenter(GInternalFrame frame , UIObject absoluteParent){
        if(frame== null || absoluteParent == null )
            throw new IllegalArgumentException("The parameters can't be null");
        int frameLeft =  (frame.getWidth() + absoluteParent.getOffsetWidth()) /2;
        int frameTop =  (frame.getHeight() + absoluteParent.getOffsetHeight()) /2;
        frame.setLocation(frameTop, frameLeft);
        frame.setVisible(true);
        
    }
}
