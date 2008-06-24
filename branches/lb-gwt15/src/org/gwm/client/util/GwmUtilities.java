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

import org.gwm.client.GDesktopPane;
import org.gwm.client.GDialog;
import org.gwm.client.GFrame;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Provides GWT Window Manager util methods. 
 */

public class GwmUtilities {

	/**
	 * Displays the frame at the center of it's parent widget.
	 * For {@link GInternalFrame} this is the {@link GDesktopPane}, for {@link GFrame} and {@link GDialog}
	 * it is the whole screen.
	 * @param frame the frame to display at the center of the parent widget
	 */
    static public void displayAtParentCenter(GFrame frame) {
        if (frame == null)
            throw new IllegalArgumentException("The frame can't be null");
        if (frame instanceof GInternalFrame) {
            GInternalFrame internalFrame = (GInternalFrame) frame;
            if (internalFrame.getDesktopPane() != null)
                diplayAtRelativeGivenWidgetCenter(frame,
                        (UIObject) internalFrame.getDesktopPane());
        } else {
            displayAtScreenCenter(frame);
        }
    }

    /**
     * Displays a frame at the center of the screen.
     * @param frame the frame to display at the center of the screen.
     */
    static public void displayAtScreenCenter(GFrame frame) {
        if (frame == null)
            throw new IllegalArgumentException("The frame can't be null");
        frame.setVisible(true);
        int frameLeft = (Window.getClientWidth() - frame.getWidth()) / 2;
        int frameTop = (Window.getClientHeight() - frame.getHeight()) / 2;
        frame.setLocation(frameTop, frameLeft);

    }

    /**
     * Displays a frame at the center of the specified widget
     * @param frame the frame to display
     * @param absoluteParent the widget at which center the frame will be displayed
     */
    static public void diplayAtRelativeGivenWidgetCenter(GFrame frame,
            UIObject absoluteParent) {
        if (frame == null || absoluteParent == null)
            throw new IllegalArgumentException("The parameters can't be null");
        int frameLeft = (absoluteParent.getOffsetWidth() -frame.getWidth()) /2;
        int frameTop = (absoluteParent.getOffsetHeight() - frame.getHeight())/2;
        frame.setLocation(frameTop, frameLeft);
        frame.setVisible(true);

    }

    public static native void hideSelect()/*-{
         if(navigator.userAgent.indexOf('MSIE') >=0){
             selects=$doc.getElementsByTagName("select");
             for (i = 0; i < selects.length; i++) {
                 selects[i].style.display = 'none';
             }
         }
     }-*/;

    public static native void showSelect()/*-{
         if(navigator.userAgent.indexOf('MSIE') >=0){
             selects=$doc.getElementsByTagName("select");
             for (i = 0; i < selects.length; i++) {
                 selects[i].style.display = '';
             }
         }
     }-*/;
    
    public static native boolean isIEBrowser()/*-{
    return navigator.userAgent.indexOf('MSIE') >=0;
    }-*/;

   public static native boolean isFFBrowser()/*-{
    return navigator.userAgent.indexOf('Firefox') >=0;
    }-*/;
   public static native boolean isSafariBrowser()/*-{
    return navigator.userAgent.indexOf('Safari') >=0;
    }-*/;

   public static native String getUserAgent()/*-{
    return navigator.userAgent;
    }-*/;
    
    
}
