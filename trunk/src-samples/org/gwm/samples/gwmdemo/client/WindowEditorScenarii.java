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

package org.gwm.samples.gwmdemo.client;

import org.gwm.client.FramesManager;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.ui.Hyperlink;

public class WindowEditorScenarii extends AbstractScenarii {

	
	
	public WindowEditorScenarii(FramesManager framesManager) {
		super(framesManager);

	}
 
	public void runScenarii() {
        WindowEditor editor = new WindowEditor(null);
        GInternalFrame editorWindow =framesManager.newFrame("");
        editorWindow.setTheme("alphacube");
        editorWindow.setWidth(280);
        editorWindow.setHeight(460);
        editorWindow.setCaption("Window editor");
        editorWindow.setTop(60);
        editorWindow.setLeft(10);
        editorWindow.setContent(editor);
        editorWindow.setVisible(true);
	}

	protected Hyperlink createLink() {
		Hyperlink simpleDemo = new Hyperlink("WindowEditor", "WindowEditor");
		return simpleDemo;
	}
	
	
	
	
	
	
		
}
