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

import org.gwtwidgets.client.ui.PNGImage;

import com.google.gwt.user.client.ui.Image;
/**
 * Overrides the globals GWT Window Manager behaviors. 
 */
public abstract class Gwm {

    private static String defaultTheme = "default";

    private static Image windowTitleIcon = new PNGImage(
            "gwm/images/window.png", 12, 12);

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
     * @param defaultTheme the default frame used for new frames.
     */
    public static void setDefaultTheme(String defaultTheme) {
        Gwm.defaultTheme = defaultTheme;
    }

    /**
     * Gets the default theme for new frames.
     * @return the default theme for new frames
     */
    public static String getDefaultTheme() {
        return defaultTheme;
    }

    /**
     * Defines if an overlay panel is displayed during the frames drag action.
     * 
     * @param overlay true if an overlay panel is displayed during frame drag actions 
     */
    public static void setOverlayLayerDisplayOnDragAction(boolean overlay) {
        Gwm.overlayLayerDisplayOnDragAction = overlay;
    }

    /**
     * Return if an overlay panel is displayed during the frames drag action.
     * @return true if am overlay panel is displayed during the frames drag action
     */
    public static boolean isOverlayLayerDisplayOnDragAction() {
        return overlayLayerDisplayOnDragAction;
    }
    
    public static boolean isAppletCompliant() {
        return appletCompliant;
    }
    
    public static void setAppletCompliant(boolean compliant){
        appletCompliant = compliant;
    }

}
