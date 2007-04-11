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

public abstract class GWmConstants {

    private static String defaultTheme = "default";

    private static Image windowTitleIcon = new PNGImage(
            "gwm/images/window.png", 12, 12);

    private static boolean overlayLayerDisplayOnDragAction = true;

    /**
     * Defines the default application icon displayed in the frame topbar. Not
     * supported
     * 
     * @param windowTitleIcon
     */
    public static void setDefaultFrameTitleIcon(Image windowTitleIcon) {
        GWmConstants.windowTitleIcon = windowTitleIcon;
    }

    public static Image getWindowTitleIcon() {
        return windowTitleIcon;
    }

    public static void setWindowTitleIcon(Image windowTitleIcon) {
        GWmConstants.windowTitleIcon = windowTitleIcon;
    }

    /**
     * Defines the default theme used for new frames.
     * 
     * @param defaultTheme
     */
    public static void setDefaultTheme(String defaultTheme) {
        GWmConstants.defaultTheme = defaultTheme;
    }

    public static String getDefaultTheme() {
        return defaultTheme;
    }

    /**
     * Defines if an overlay panel is diplayed with the frames drag action.
     * 
     * @param overlay
     */
    public static void setOverlayLayerDisplayOnDragAction(boolean overlay) {
        GWmConstants.overlayLayerDisplayOnDragAction = overlay;
    }

    public static boolean isOverlayLayerDisplayOnDragAction() {
        return overlayLayerDisplayOnDragAction;
    }

}
