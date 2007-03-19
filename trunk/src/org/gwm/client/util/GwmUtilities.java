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

package org.gwm.client.util;

import org.gwm.client.GFrame;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

public class GwmUtilities {

    static public void displayAtParentCenter(GFrame frame) {
        if (frame == null)
            throw new IllegalArgumentException("The frame can't be null");
        if (frame instanceof GInternalFrame) {
            GInternalFrame internalFrame = (GInternalFrame) frame;
            if (internalFrame.getDesktopPane() != null)
                diplayAtRelativeGivenWidgetCenter(frame,
                        (UIObject) internalFrame.getDesktopPane());
        } else {
            diplayAtScreenCenter(frame);
        }
    }

    static public void diplayAtScreenCenter(GFrame frame) {
        if (frame == null)
            throw new IllegalArgumentException("The frame can't be null");
        frame.setVisible(true);
        int frameLeft = (Window.getClientWidth() - frame.getWidth()) / 2;
        int frameTop = (Window.getClientHeight() - frame.getHeight()) / 2;
        frame.setLocation(frameTop, frameLeft);

    }

    static public void diplayAtRelativeGivenWidgetCenter(GFrame frame,
            UIObject absoluteParent) {
        if (frame == null || absoluteParent == null)
            throw new IllegalArgumentException("The parameters can't be null");
        int frameLeft = (frame.getWidth() + absoluteParent.getOffsetWidth()) / 2;
        int frameTop = (frame.getHeight() + absoluteParent.getOffsetHeight()) / 2;
        frame.setLocation(frameTop, frameLeft);
        frame.setVisible(true);

    }
}
