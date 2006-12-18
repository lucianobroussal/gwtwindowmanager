package org.gwm.tests;

import org.gwm.client.GFrameEvent;
import org.gwm.client.GFrameListener;
import org.gwm.client.GInternalFrame;
import org.gwm.client.impl.DefaultWidgetInternalFrame;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Window;

public class GInternalFrameTest extends GWTTestCase {
	private GInternalFrame internalFrame;
	private static final int NORMAL_WIDTH = 400;
	private static final int NORMAL_HEIGHT = 300;
	private static final int MAX_WIDTH = 800;
	private static final int MAX_HEIGHT = 600;
	private static final int MIN_WIDTH = 200;
	private static final int MIN_HEIGHT = 30;
		
	public String getModuleName() {
		return "org.gwm.GwtWindowManager";
	}
	
	public void testMaximize(){
		int MIDDLE_HEIGHT = (Window.getClientHeight() - NORMAL_HEIGHT) / 2 ;
		int MIDDLE_WIDTH = (Window.getClientWidth() - NORMAL_WIDTH) / 2;	
		internalFrame = new DefaultWidgetInternalFrame("Testing Frame 1");
		MyPersonalListener listener = new MyPersonalListener();
		
		internalFrame.setMaximizable(false);
		
		assertFalse(internalFrame.isMaximizable());
		
		internalFrame.setWidth(NORMAL_WIDTH);
		internalFrame.setHeight(NORMAL_HEIGHT);
		internalFrame.setMaximumWidth(MAX_WIDTH);
		internalFrame.setMaximumHeight(MAX_HEIGHT);
		internalFrame.showCenter(false);
		
		assertEquals(NORMAL_WIDTH, internalFrame.getWidth());
		assertEquals(NORMAL_HEIGHT, internalFrame.getHeight());
		assertEquals(MIDDLE_WIDTH, internalFrame.getLeft());
		assertEquals(MIDDLE_HEIGHT, internalFrame.getTop());
		
		internalFrame.maximize();
		assertFalse(listener.isMaximized());
		
		assertEquals(NORMAL_WIDTH, internalFrame.getWidth());
		assertEquals(NORMAL_HEIGHT, internalFrame.getHeight());
		
		internalFrame.setMaximizable(true);
		internalFrame.addGFrameListener(listener);
		assertTrue(internalFrame.isMaximizable());
		
		internalFrame.maximize();
		assertTrue(listener.isMaximized());
		
		assertEquals(MAX_WIDTH, internalFrame.getWidth());
		assertEquals(MAX_HEIGHT, internalFrame.getHeight());
		assertEquals(0, internalFrame.getLeft());
		assertEquals(0, internalFrame.getTop());
		
		listener.setMaximized(false);
		assertFalse(listener.isMaximized());
		
		internalFrame.setSize(NORMAL_WIDTH, NORMAL_HEIGHT);
		internalFrame.removeGFrameListener(listener);
		internalFrame.maximize();
		
		assertFalse(listener.isMaximized());
		
		internalFrame.addGFrameListener(listener);
		internalFrame.maximize();
		assertTrue(listener.isMaximized());
	}
	
	public void testMinimized(){
//		int MIDDLE_HEIGHT = (Window.getClientHeight() - NORMAL_HEIGHT) / 2 ;
//		int MIDDLE_WIDTH = (Window.getClientWidth() - NORMAL_WIDTH) / 2;	
		internalFrame = new DefaultWidgetInternalFrame("");
		MyPersonalListener listener = new MyPersonalListener();
		
		internalFrame.setMinimumHeight(MIN_HEIGHT);
		internalFrame.setMinimumWidth(MIN_WIDTH);
		internalFrame.setWidth(NORMAL_WIDTH);
		internalFrame.setHeight(NORMAL_HEIGHT);
		
		assertEquals(NORMAL_HEIGHT, internalFrame.getHeight());
		assertEquals(NORMAL_WIDTH, internalFrame.getWidth());
		assertEquals(MIN_HEIGHT, internalFrame.getMinimumHeight());
		assertEquals(MIN_WIDTH, internalFrame.getMinimumWidth());
		assertFalse(listener.isMinimized());
		
		internalFrame.minimize();
		assertTrue(internalFrame.isMinimized());
		assertFalse(listener.isMinimized());
		
		internalFrame.addGFrameListener(listener);
		internalFrame.minimize();
		assertTrue(internalFrame.isMinimized());
		assertTrue(listener.isMinimized());
		
	}
}

/**
 * Just a Simple GFrameListener.
 * @author Marcelo Emanoel
 * @since 17/12/2006
 */
class MyPersonalListener implements GFrameListener{
	private boolean maximized;
	private boolean minimized;
	
	public MyPersonalListener(){
		setMaximized(false);
		setMinimized(false);
	}
	
	public void frameActivated(GFrameEvent evt) {}

	public void frameClosed(GFrameEvent evt) {}

	public void frameClosing(GFrameEvent evt) {}

	public void frameMaximized(GFrameEvent evt) {
		setMaximized(true);
	}

	public void frameMinimized(GFrameEvent evt) {
		setMinimized(true);
	}

	public void frameMoved(GFrameEvent evt) {}

	public void frameResized(GFrameEvent evt) {}
	
	public boolean isMaximized(){
		return this.maximized;
	}
	public void setMaximized(boolean maximized){
		this.maximized = maximized;
	}
	public void setMinimized(boolean minimized){
		this.minimized = minimized;
	}
	public boolean isMinimized(){
		return this.minimized;
	}

	public void frameMaximizing(GFrameEvent evt) {}

	public void frameMinimizing(GFrameEvent evt) {}
}