/**
 * 
 */
package org.gwm.client.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwm.client.*;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Marcelo Emanoel
 * @since 20/12/2006
 */
public class MinimizedButtonBar extends Composite {
	private DockPanel layout;
	private VerticalPanel navigatorPanel;
//	private Stack barStack;
	private HTML forwardButton;
	private HTML backwardButton;
	private List buttonBarList;
	private HorizontalPanel currentButtonList;
	private GDesktopPane owner;
	private int currentPosition;
	private Map windowMap;
	

	public MinimizedButtonBar(GDesktopPane theOwner){
		setOwner(theOwner);
		initialize();
		mount();
		setupListeners();
	}

	private void initialize() {
//		barStack = new Stack();
		windowMap = new HashMap();
		layout = new DockPanel();
		navigatorPanel = new VerticalPanel();
		buttonBarList = new ArrayList();
		currentButtonList = new HorizontalPanel();
		forwardButton = new HTML("^");
		backwardButton = new HTML("v");
//		barStack.push(currentButtonList);
		buttonBarList.add(currentButtonList);
		currentPosition = 0;
		
		setupSizes();
		initWidget(layout);
	}

	private void setupSizes() {
		DeferredCommand.add(new Command() {
			public void execute() {
				adjustSize();
			}
		});
	}

	private void mount() {
		
		navigatorPanel.add(forwardButton);
		navigatorPanel.add(backwardButton);
		
		layout.add(navigatorPanel, DockPanel.EAST);
		layout.setCellHeight(navigatorPanel, "20px");
		layout.setCellWidth(navigatorPanel, "15px");
		setCurrentButtonList(currentButtonList);
		
		layout.setBorderWidth(1);
	}

	/**
	 * 
	 */
	private void setCurrentButtonList(HorizontalPanel panel) {
		if(panel != null){
//			if(!barStack.contains(panel)){
//				barStack.push(panel);
//			}
			if(!buttonBarList.contains(panel)){
				buttonBarList.add(panel);
			}
			layout.remove(currentButtonList);
			layout.add(panel, DockPanel.CENTER);
			layout.setCellHorizontalAlignment(panel, HorizontalPanel.ALIGN_LEFT);
			currentButtonList = panel;
		}
	}

	private void setupListeners() {
		forwardButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				forward();
			}
		});
		backwardButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				backward();
			}
		});
	}
	
	/**
	 * @return the owner
	 */
	public GDesktopPane getOwner() {
		return owner;
	}
	
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(GDesktopPane owner) {
		this.owner = owner;
	}

	public void adjustSize() {
		DeferredCommand.add(new Command() {
			public void execute() {
				String width = getOwner().getOffsetWidth()+"px";
				String height = "20px";
				
				setSize(width, height);
				layout.setSize(width, height);
			}
		});
	}

	/**
	 * @param i
	 */
	public void addWindow(GInternalFrame theWindow) {
		final Button newButton = makeButton(theWindow.getCaption());
		windowMap.put(newButton, theWindow);
		currentButtonList.add(newButton);
		DeferredCommand.add(new Command() {
			public void execute() {
				int buttonListWidth = currentButtonList.getOffsetWidth();
				int offsetWidth = getWidth();
				int buttonWidth = newButton.getOffsetWidth();
				
				if((buttonListWidth+buttonWidth) > offsetWidth){
					currentButtonList.remove(newButton);
					HorizontalPanel newButtonList = new HorizontalPanel();
					setCurrentButtonList(newButtonList);
					newButtonList.add(newButton);
				}

			}

		
		});
	}
	private int getWidth(){
		return getOwner().getOffsetWidth();
	}
	private Button makeButton(String caption) {
		Button button = new Button(caption);
		button.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				restoreWindow((Button)sender);
			}
		});
		return button;
	}

	private void restoreWindow(Button button) {
		GInternalFrame minimizedWindow = (GInternalFrame) windowMap.get(button);
		getOwner().deIconify(minimizedWindow);
		HorizontalPanel panel = (HorizontalPanel) button.getParent();
		button.removeFromParent();
		if(panel.getWidgetCount() == 0){
			panel.removeFromParent();
			backward();
		}
		windowMap.remove(button);
	}

	/**
	 * 
	 */
	private void forward() {
//		if(barStack.size() > currentPosition + 1){
		if(buttonBarList.size() > (currentPosition + 1)){
			currentPosition++;
			HorizontalPanel nextButtonList = (HorizontalPanel) 
									buttonBarList.get(currentPosition);
			setCurrentButtonList(nextButtonList);
		}
	}

	/**
	 * 
	 */
	private void backward() {
		if(currentPosition > 0){
			currentPosition --;
			HorizontalPanel previousButtonList = (HorizontalPanel) 
									buttonBarList.get(currentPosition);
			setCurrentButtonList(previousButtonList);
		}
	}
}
