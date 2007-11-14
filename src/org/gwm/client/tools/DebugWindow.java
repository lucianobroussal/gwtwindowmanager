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

package org.gwm.client.tools;

import org.gwm.client.GFrame;
import org.gwm.client.event.GFrameEvent;
import org.gwm.client.event.GFrameListener;
import org.gwm.client.impl.DefaultGFrame;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DebugWindow {

    private DockPanel dock;

    private VerticalPanel panel;

    private Button clearLog;

    private GFrame frame;
    
     

    public DebugWindow() {
        frame = new DefaultGFrame("Debug Window");
        this.dock = new DockPanel();
        this.panel = new VerticalPanel();
        this.clearLog = new Button("Clear window", new ClickListener() {
            public void onClick(Widget w) {
                clearPanel();
            }
        });
        this.clearLog.addStyleName("gwm_DebugWindow-ClearLogButton");
        this.dock.addStyleName("gwm-DebugWindow");
        this.dock.add(clearLog, DockPanel.NORTH);
        this.dock.add(panel, DockPanel.CENTER);
        this.dock.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        this.dock.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        this.dock.setCellHorizontalAlignment(clearLog,
                HasHorizontalAlignment.ALIGN_CENTER);
        this.dock.setCellVerticalAlignment(clearLog,
                HasVerticalAlignment.ALIGN_TOP);
        frame.setContent(this.dock);
    }

    private void clearPanel() {
        this.dock.remove(panel);
        this.panel = new VerticalPanel();
        this.dock.add(panel, DockPanel.CENTER);
    }

   

    private void addEvent(GFrameEvent evt, String action) {
        if(panel.getWidgetCount() % 10 == 0){
            clearPanel();
        }
        
        final GFrame myFrame = evt.getGFrame();
        String title = myFrame.getCaption();
        Label l = new Label(action + " " + title);
        l.addStyleName("gwm-DebugEvent");
        this.panel.add(l);
        ((DefaultGFrame)frame).fireFrameResized();
    }

    public GFrame getUI() {
        return frame;
    }

    public GFrameListener getFrameListener() {
       return  new GFrameListener(){
            public void frameResized(GFrameEvent evt) {
                addEvent(evt, "Resized");
            }

            public void frameOpened(GFrameEvent evt) {
                addEvent(evt, "Opened");
            }

            public void frameClosed(GFrameEvent evt) {
                addEvent(evt, "Closed");
            }

            public void frameMaximized(GFrameEvent evt) {
                addEvent(evt, "Maximize");
            }

            public void frameMinimized(GFrameEvent evt) {
                addEvent(evt, "Minimized");
            }

            public void frameIconified(GFrameEvent evt) {
                addEvent(evt, "Iconified");
            }

            public void frameRestored(GFrameEvent evt) {
                addEvent(evt, "Restored");
            }

            public void frameMoved(GFrameEvent evt) {
                addEvent(evt, "Moved");
            }
            public void frameMoving(GFrameEvent evt) {
                addEvent(evt, "Moving");
            }
            
            public void frameSelected(GFrameEvent evt) {
                addEvent(evt, "Selected");
            }

            public void frameGhostMoving(int top, int left, GFrameEvent evt) {
                addEvent(evt , "Ghost moving");
            }

            public void frameGhostMoved(int top, int left, GFrameEvent evt) {
                addEvent(evt , "Ghost moved");
                
            }

            public boolean onFrameClosing(GFrameEvent evt) {
                addEvent(evt , "On closing");
                return true;
            }

        };
    }

}
