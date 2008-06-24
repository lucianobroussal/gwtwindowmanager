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

import org.gwm.client.impl.GWmImageBundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;

/**
 * Overrides the globals GWT Window Manager behaviors.
 */
public abstract class Gwm {

    private static String defaultTheme = "default";
    
    private static GWmImageBundle imageBundle = (GWmImageBundle) GWT.create(GWmImageBundle.class);

    private static Image windowTitleIcon = imageBundle.window_icon().createImage();

    private static boolean overlayLayerDisplayOnDragAction = true;

    private static boolean appletCompliant;

    /**
     * Defines the default application icon displayed in the frame topbar. Not
     * supported
     * 
     * @param windowTitleIcon
     */
    public static void setDefaultFrameTitleIcon(Image windowTitleIcon) {
        Gwm.windowTitleIcon = windowTitleIcon;
    }

    public static Image getWindowTitleIcon() {
        return windowTitleIcon;
    }

    public static void setWindowTitleIcon(Image windowTitleIcon) {
        Gwm.windowTitleIcon = windowTitleIcon;
    }

    /**
     * Sets the default theme used for new frames.
     * 
     * @param defaultTheme
     *            the default frame used for new frames.
     */
    public static void setDefaultTheme(String defaultTheme) {
        Gwm.defaultTheme = defaultTheme;
    }

    /**
     * Gets the default theme for new frames.
     * 
     * @return the default theme for new frames
     */
    public static String getDefaultTheme() {
        return defaultTheme;
    }

    /**
     * Defines if an overlay panel is displayed during the frames drag action.
     * 
     * @param overlay
     *            true if an overlay panel is displayed during frame drag
     *            actions
     */
    public static void setOverlayLayerDisplayOnDragAction(boolean overlay) {
        Gwm.overlayLayerDisplayOnDragAction = overlay;
    }

    /**
     * Return if an overlay panel is displayed during the frames drag action.
     * 
     * @return true if am overlay panel is displayed during the frames drag
     *         action
     */
    public static boolean isOverlayLayerDisplayOnDragAction() {
        return overlayLayerDisplayOnDragAction;
    }


    /**
     * Indicates if you are using a mixin GWm configuration (Applets and GWT widgets) 
     * @return
     */
    public static boolean isAppletCompliant() {
        return appletCompliant;
    }
    
    /**
     * Indicates you will you use a mixin configuration (Applets and GWT widgets)
     * <b style='color:red'>IMPORTANT </b>: if you don't use Applet you should never call this feature since it has performance and rendering effect especially on FF)!  
     * @param compliant
     */
    public static void setAppletCompliant(boolean compliant){
        appletCompliant = compliant;
    }

    public static GWmImageBundle getImageBundle() {
        return imageBundle;
    }

   

}
