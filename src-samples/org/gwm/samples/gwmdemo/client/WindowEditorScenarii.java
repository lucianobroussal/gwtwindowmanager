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
