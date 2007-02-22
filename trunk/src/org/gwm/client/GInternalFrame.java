/*
 * Copyright (c) 2006-2007 Luciano Broussal, Johan Vos, Andy Scholz, Marcelo Emanoel  (http://www.gwtwindowmanager.org)
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
 * Provides the behavior handles to interact with a frame.
 */
public interface GInternalFrame extends GFrame {
    /**
     * Returns the GDesktopPane which owns this GInternalFrame
     * 
     * @return
     */
    public GDesktopPane getDesktopPane();

    /**
     * Sets the GDesktopPane which owns this GInternalFrame
     */
    public void setDesktopPane(GDesktopPane desktopPane);

}
