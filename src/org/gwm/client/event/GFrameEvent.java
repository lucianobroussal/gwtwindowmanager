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
package org.gwm.client.event;

import org.gwm.client.GFrame;

/**
 * This class is an event object and has a GFrame as the source.
 */
public class GFrameEvent {
    private GFrame source;
    private GFrame oldFrame;

    /**
     * Build a GFrameEvent from a GFrame as the source.
     * 
     * @param source
     */
    public GFrameEvent(GFrame source) {
        this(source , source);
    }
    
    /**
     * Build a GFrameEvent from a GFrame as the source and the old GFrame implied by the event.
     * 
     * @param source
     */
    public GFrameEvent(GFrame source , GFrame oldSource) {
        this.source = source;
        this.oldFrame = source;
    }

    /**
     * Returns the source of the event.
     * 
     * @return
     */
    public GFrame getGFrame() {
        return this.source;
    }

    public GFrame getOldFrame() {
        return oldFrame;
    }
}
