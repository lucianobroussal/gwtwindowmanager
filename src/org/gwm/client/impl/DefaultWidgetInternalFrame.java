package org.gwm.client.impl;


import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GFrameEvent;
import org.gwm.client.GFrameListener;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class is intended to be a default implementation of GInternalFrame using
 * only widgets from the GWT itself. By default this internal frame is resizable 
 * and closable but not maximizable and minimizable.
 * The class make use of a combination of layout managers. First of all there is
 * a <a href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.DockPanel.html">DockPanel</a>
 * on the North and South of it there is a <a href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.HorizontalPanel.html">HorizontalPanel</a>
 * on the WEST and EAST there is a <a href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.HTML.html">HTML</a>
 * and finally on the Center there is a <a href="http://code.google.com/webtoolkit/documentation/com.google.gwt.user.client.ui.Composite.html">Composite</a>
 * who plays the part of the content of the window.
 *  It should be like this:
 * <pre>
 *       _______________________
 *      |_________NORTH_________|
 *      | |                   | |
 *      |W|                   |E|
 *      |E|      CENTER       |A|
 *      |S|                   |S|
 *      |T|                   |T|
 *      |_|___________________|_|
 *      |________SOUTH__________|
 * 
 * </pre>
 * 
 * The NORTH HorizontalPanel is built like this:
 * <pre>
 *     ________________________
 *    | |              | | | | |
 *    |1|       2      |3|4|5|6|
 *    |_|______________|_|_|_|_|
 * 
 * </pre>
 * where:
 *    <ol>
 *    	<li>top left corner</li>
 *    	<li>top gradient</li>
 *    	<li>minimize button</li>
 *    	<li>maximize button</li>
 *    	<li>close button</li>
 *    	<li>top right corner</li>
 *    </ol>
 * The SOUTH HorizontalPanel is built like this:
 * <pre>
 *     ________________________
 *    | |                    | |
 *    |1|       2            |3|
 *    |_|____________________|_|
 * 
 * </pre>
 * where:
 *    <ol>
 *    	<li>bottom left corner</li>
 *    	<li>bottom gradient</li>
 *    	<li>bottom right corner</li>
 *    </ol>
 * 
 * 
 * The DefaultWidgetInternalFrame uses the following CSS rules:
 * <pre>
 *      .org-gwm-GInternalFrame{ the window itself }
		.org-gwm-GInternalFrame .top{ the top HorizontalPanel }
		.org-gwm-GInternalFrame .bottom{ the bottom HorizontalPanel }
		.org-gwm-GInternalFrame .left{ the WEST part of the Window }
		.org-gwm-GInternalFrame .right{ the EAST part of the window }
		.org-gwm-GInternalFrame .top_left_corner{ self explanatory }
		.org-gwm-GInternalFrame .top_right_corner{ self explanatory }
		.org-gwm-GInternalFrame .bottom_left_corner{ self explanatory }
		.org-gwm-GInternalFrame .bottom_right_corner{ self explanatory }
		.org-gwm-GInternalFrame .bottom_right_corner_noresize{ same as before but active only when the window isn't resizable }
		.org-gwm-GInternalFrame .bottom_gradient{ the gradient that complements the bottom }
		.org-gwm-GInternalFrame .top_gradient{ the gradient that compelements the top }
		.org-gwm-GInternalFrame .min_button{ self explanatory }
		.org-gwm-GInternalFrame .max_button{ self explanatory }
		.org-gwm-GInternalFrame .close_button{ self explanatory }
		.org-gwm-GInternalFrame .content{ the CENTER part of the window }
		.org-gwm-GInternalFrame .caption{ the text of the window }
 * </pre>
 * 
 * @author Marcelo Emanoel
 * @since 14/12/2006
 */
public class DefaultWidgetInternalFrame extends PopupPanel implements MouseListener, GInternalFrame, ClickListener{
	
	private DockPanel windowLayout;
	private HorizontalPanel top;
	private HorizontalPanel bottom;
	private HTML left;
	private HTML right;
	private HTML leftTopCorner;
	private HTML rightTopCorner;
	private HTML leftBottomCorner;
	private HTML rightBottomCorner;
	private HTML bottomGradient;
	private HTML topGradient;
	private HTML minButton;
	private HTML maxButton;
	private HTML closeButton;
	private Composite contentPane;
	private String caption;
	private int dragStartX;
	private int dragStartY;
	private int height;
	private int width;
	private int minimumHeight;
	private int minimumWidth;
	private int maximumHeight;
	private int maximumWidth;
	private boolean closable;
	private boolean maximizable;
	private boolean minimizable;
	private boolean resizable;
	private boolean draggable;
	private boolean firstTimeShow;
	
	/*
	 * The states of the window
	 */
	private boolean dragging;
	private boolean resizing;
	private boolean minimized;
	private boolean maximized;
	private boolean closed;
	
	private boolean modal;
	private List listeners;
	private GDesktopPane parentDesktop;
	private boolean visible;
	
	/**
	 * Constructs a DefaultWidgetInternalFrame with the default settings
	 * @param caption The Caption of the window.
	 */
	public DefaultWidgetInternalFrame(String caption, GDesktopPane parentDesktop){
		setParentDesktop(parentDesktop);
		initialize();
		mount();
		setupListeners();
		setupSise();
		setupStyle();
		setTitle(caption);
		
	}
	
	/**
	 * Create all the intances needed. 
	 */
	private void initialize() {

		listeners = new ArrayList();
		windowLayout = new DockPanel();
		top = new HorizontalPanel();
		bottom = new HorizontalPanel();
		contentPane = new BlankComposite();
		
		/*
		 * Here are the divs who represents the images.
		 */
		left = makeDiv();
		right = makeDiv();
		leftTopCorner = makeDiv();
		rightTopCorner = makeDiv();
		leftBottomCorner = makeDiv();
		rightBottomCorner = makeDiv();
		bottomGradient = makeDiv();
		topGradient = makeDiv();
		minButton = makeDiv();
		maxButton = makeDiv();
		closeButton = makeDiv();
		
		/*
		 * setup the Window Caption
		 */
		topGradient.setHTML(getCaption());
		
		firstTimeShow = true;
		setResizable(true);
		setMaximizable(false);
		setMinimizable(false);
		setClosable(true);
		
		minimumHeight = 30;
		minimumWidth = 50;
	}
	
	/**
	 * Setup all the sizes of the images.
	 */
	private void setupSise() {
		String topHeight = "44px";
		String bottomHeight = "26px";
		
		leftTopCorner.setWidth("28px");
		minButton.setWidth("21px");
		maxButton.setWidth("23px");
		closeButton.setWidth("21px");
		rightTopCorner.setWidth("23px");
		
		leftTopCorner.setHeight(topHeight);
		minButton.setHeight(topHeight);
		maxButton.setHeight(topHeight);
		closeButton.setHeight(topHeight);
		rightTopCorner.setHeight(topHeight);
		
		leftBottomCorner.setWidth("28px");
		rightBottomCorner.setWidth("23px");
		
		leftBottomCorner.setHeight(bottomHeight);
		rightBottomCorner.setHeight(bottomHeight);
		
		left.setWidth("28px");
		right.setWidth("23px");
	}
	
	/**
	 * Setups the style names of each image.
	 */
	private void setupStyle() {
		setStyleName("org-gwm-GInternalFrame");
		
		top.setStyleName("top");
		bottom.setStyleName("bottom");
		left.setStyleName("left");
		right.setStyleName("right");
		leftTopCorner.setStyleName("top_left_corner");
		rightTopCorner.setStyleName("top_right_corner");
		leftBottomCorner.setStyleName("bottom_left_corner");
		rightBottomCorner.setStyleName("bottom_right_corner");
		bottomGradient.setStyleName("bottom_gradient");
		topGradient.setStyleName("top_gradient");
		minButton.setStyleName("min_button");
		maxButton.setStyleName("max_button");
		closeButton.setStyleName("close_button");
		contentPane.setStyleName("content");
		topGradient.addStyleName("caption");
	}
	
	/**
	 * This method build up the window inserting the images at the panels
	 */
	private void mount() {
		this.add(windowLayout);

		top.add(leftTopCorner);
		top.add(topGradient);
		top.add(minButton);
		top.add(maxButton);
		top.add(closeButton);
		top.add(rightTopCorner);
		
		bottom.add(leftBottomCorner);
		bottom.add(bottomGradient);
		bottom.add(rightBottomCorner);

		bottomGradient.setWidth("100%");
		bottom.setHeight("100%");
		top.setHeight("100%");
		left.setHeight("100%");
		right.setHeight("100%");

		top.setSpacing(0);
		bottom.setSpacing(0);
		windowLayout.setSpacing(0);

//		bottom.setBorderWidth(1);
//		top.setBorderWidth(1);
//		windowLayout.setBorderWidth(1);

		top.setVerticalAlignment(VerticalPanel.ALIGN_BOTTOM);
		top.setCellVerticalAlignment(topGradient, VerticalPanel.ALIGN_TOP);
		bottom.setCellVerticalAlignment(bottomGradient, VerticalPanel.ALIGN_TOP);
		
		windowLayout.add(top, DockPanel.NORTH);
		windowLayout.add(bottom, DockPanel.SOUTH);
		windowLayout.add(left, DockPanel.WEST);
		windowLayout.add(right, DockPanel.EAST);
		windowLayout.add(contentPane, DockPanel.CENTER);

		windowLayout.setCellHeight(contentPane, "100%");
		windowLayout.setSize("100%", "100%");
	}
	/**
	 *  Setup the listeners of the window.
	 */
	private void setupListeners() {
		topGradient.addMouseListener(this);
		leftTopCorner.addMouseListener(this);
		rightTopCorner.addMouseListener(this);
		
		closeButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				destroy();
			}
		});
		maxButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				maximizar();
			}
		});
		minButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				minimizar();
			}
		});
		rightBottomCorner.addMouseListener(new MouseListener() {
			public void onMouseUp(Widget sender, int x, int y) {
				//just released the mouse button..
				if(resizable){
					resizing = false;
					DOM.releaseCapture(sender.getElement());
				}
			}
		
			public void onMouseMove(Widget sender, int x, int y) {
				//moving with the pressed button...
				//TODO fix/improve this!!!!
				if(resizable && resizing){
					int xDelta = x - dragStartX;
					int yDelta = y - dragStartY; 
					int contentPaneHeight = contentPane.getOffsetHeight() + yDelta;
					int contentPaneWidth = contentPane.getOffsetWidth() + xDelta;
					int topGradientWidth = topGradient.getOffsetWidth() + xDelta;
					int bottomGradientWidth = bottomGradient.getOffsetWidth() + xDelta;
					int leftHeight = left.getOffsetHeight() + yDelta;
					int rightHeight = right.getOffsetHeight() + yDelta;
					
					contentPane.setHeight(contentPaneHeight+"px");
					contentPane.setWidth(contentPaneWidth+"px");

					topGradient.setWidth(topGradientWidth+"px");
					bottomGradient.setWidth(bottomGradientWidth+"px");
					left.setHeight(leftHeight+"px");
					right.setHeight(rightHeight+"px");
					
					setMaximized(false);
					setMinimized(false);
					fireFrameResized();
				}
			}
		
			public void onMouseLeave(Widget sender) {
				//do nothing...
			}
			public void onMouseEnter(Widget sender) {
				//do nothing...
			}
		
			public void onMouseDown(Widget sender, int x, int y) {
				//just pressed the mouse button...
				if(resizable){
					resizing = true;
					DOM.setCapture(sender.getElement());
					dragStartX = x;
					dragStartY = y;
				}
			}
		});
		topGradient.addClickListener(this);
		leftTopCorner.addClickListener(this);
		rightTopCorner.addClickListener(this);
		left.addClickListener(this);
		right.addClickListener(this);
		bottomGradient.addClickListener(this);
		leftBottomCorner.addClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setModal(boolean)
	 */
	public void setModal(boolean modal){
		if(modal && this.isVisible()){
			DOM.addEventPreview(this);
		}
		else
			DOM.removeEventPreview(this);
		
		this.modal = modal;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#isModal()
	 */
	public boolean isModal(){
		return this.modal;
	}
	
	/**
	 * Alter the minimized state of the window.
	 * @param b
	 */	
	public void setMinimized(boolean b) {
		this.minimized = b;
	}

	/**
	 * Alter the maximized state of the window.
	 * @param b
	 */
	public void setMaximized(boolean b) {
		this.maximized = b;
	}

	/**
	 * Shows the window. Fires the frameActivated event.
	 */
	public void show() {
		super.show();
		if(firstTimeShow)
			update();
		if(!modal){
			DOM.removeEventPreview(this);
		}
		firstTimeShow = false;
		setVisible(true);
		fireFrameActivated();
	}
	
	public void center() {
		int altura = getHeight();
		int largura = getWidth();
		setLocation((Window.getClientHeight() - altura)/2 , (Window.getClientWidth() - largura)/2);
	}

	/**
	 * Ajusts the sizes of the gradients.
	 */
	private void update() {
		DeferredCommand.add(new Command() {
			public void execute() {
				int width = getOffsetWidth();
				int complemento = 0;
				complemento += (minButton.isVisible()? 21 : 0);
				complemento += (maxButton.isVisible()? 23 : 0);
				complemento += (closeButton.isVisible()? 21 : 0);
				topGradient.setWidth(width + "px");
				bottomGradient.setWidth((width+complemento)+"px");
				windowLayout.setCellWidth(contentPane, (width+complemento)+"px");
			}
		});
	}
	
	/*
	 * Factory Methods
	 */
	
	private HTML makeDiv() {
		HTML html = new HTML();
		return html;
	}
	
	/**
	 * Fires the event of the activation of this frame to its listeners.
	 */
	private void fireFrameActivated() {
		for(int i=0; i < listeners.size();i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameActivated(new GFrameEvent(this));
		}
	}
	
	/**
	 * Fires the event of the resizing of this frame to its listeners.
	 */
	private void fireFrameResized(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameResized(new GFrameEvent(this));
		}
	}
	
	/**
	 * Fires the closing event of this frame to its listeners.
	 */
	private void fireFrameClosing(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameClosing(new GFrameEvent(this));
		}
	}
	
	/**
	 * Fires the closed event of this frame to its listeners.
	 */
	private void fireFrameClosed(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameClosed(new GFrameEvent(this));
		}
	}
	
	/**
	 * Fires the frameMaximized event of this frame to its listeners.
	 */
	public void fireFrameMaximizing(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameMaximizing(new GFrameEvent(this));
		}
	}
	
	/**
	 * Fires the frameMaximized event of this frame to its listeners.
	 */
	public void fireFrameMaximized(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameMaximized(new GFrameEvent(this));
		}
	}
	
	/**
	 * Fires the frameMinimized event of this frame to its listeners.
	 */
	public void fireFrameMinimizing(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameMinimizing(new GFrameEvent(this));
		}
	}
	
	/**
	 * Fires the frameMinimized event of this frame to its listeners.
	 */
	public void fireFrameMinimized(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameMinimized(new GFrameEvent(this));
		}
	}
	
	/**
	 * Fires the frameMoved event of this frame to its listeners.
	 */
	private void fireFrameMoved(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameMoved(new GFrameEvent(this));
		}
	}
	
	/*
	 * Gets and Sets 
	 */
	/**
	 * Returns the content of this window.
	 */
	public Composite getContentPane() {
		return contentPane;
	}
	/**
	 * Sets the contentPane of this window.
	 * @param contentPane
	 */
	public void setContentPane(Composite contentPane) {
		if(contentPane != null){
			windowLayout.remove(this.contentPane);
			windowLayout.add(contentPane, DockPanel.CENTER);
			contentPane.setWidth("100%");
			contentPane.setHeight("100%");
			contentPane.setStyleName("content");
		}
		this.contentPane = contentPane;
		update();
	}
	/**
	 * Returns the Caption text of this window.
	 * @return
	 */
	public String getCaption() {
		return caption;
	}
	
	/**
	 * Sets the Caption text of this window.
	 * @param caption
	 */
	public void setTitle(String caption) {
		String newCaption = (caption != null ? caption : "");
		topGradient.setHTML(newCaption);
		this.caption = caption;
	}
	
	/**
	 * Returns wether this is window is closable or not.
	 * @return
	 */
	public boolean isClosable() {
		return closable;
	}
	
	/**
	 * Sets the closable property of this window.
	 */
	public void setClosable(boolean closable) {
		closeButton.setVisible(closable);
		this.closable = closable;
	}
	
	/**
	 * Returns wether this window is maximizable or not.
	 * @return
	 */
	public boolean isMaximizable() {
		return maximizable;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setMaximizable(boolean)
	 */
	public void setMaximizable(boolean maximizable) {
		maxButton.setVisible(maximizable);
		this.maximizable = maximizable;
	}
	
	public boolean isMinimizable() {
		return minimizable;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setMinimizable(boolean)
	 */
	public void setMinimizable(boolean minimizable) {
		minButton.setVisible(minimizable);
		this.minimizable = minimizable;
	}
	
	public boolean isResizable() {
		return resizable;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setResizable(boolean)
	 */
	public void setResizable(boolean resizable) {
		String style = "com_core_window_bottom_right_corner";
		if(!resizable){
			style+="_noresize";
		}
		rightBottomCorner.setStyleName(style);
		this.resizable = resizable;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	/*
	 * Methods for dragging mode. 
	 */
	public void onMouseDown(Widget sender, int x, int y) {
		dragging = true;
		DOM.setCapture(sender.getElement());
		dragStartX = x;
		dragStartY = y;
	}

	public void onMouseMove(Widget sender, int x, int y) {
		if (dragging) {
			int absX = x + getAbsoluteLeft();
			int absY = y + getAbsoluteTop();
			setPopupPosition(absX - dragStartX, absY - dragStartY);
			fireFrameMoved();
		}
	}
	
	public void onMouseUp(Widget sender, int x, int y) {
		dragging = false;
		DOM.releaseCapture(sender.getElement());
	}
	
	public void onMouseEnter(Widget sender) {/* Do nothing here */}
	public void onMouseLeave(Widget sender) {/* Do nothing here */}
	
	public void addGFrameListener(GFrameListener listener){
		listeners.add(listener);
	}
	
	public void removeGFrameListener(GFrameListener listener){
		listeners.remove(listener);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#destroy()
	 */
	public void destroy() {
		fireFrameClosing();
		hide();
		fireFrameClosed();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#getId()
	 */
	public String getId() {
		return getCaption();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#isMaximized()
	 */
	public boolean isMaximized() {
		return this.maximized;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#isMinimized()
	 */
	public boolean isMinimized() {
		return this.minimized;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setContent(com.google.gwt.user.client.ui.Composite)
	 */
	public void setContent(Composite theContent) {
		setContentPane(theContent);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#getContent()
	 */
	public Composite getContent(){
		return getContentPane();
	}
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setContent(java.lang.String)
	 */
	public void setContent(String content) {
		Composite newContentPane = new BlankComposite(content);
		setContent(newContentPane);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setDestroyOnClose()
	 */
	public void setDestroyOnClose() {
		//TODO what should I put here?
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setDraggable(boolean)
	 */
	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setHeight(int)
	 */
	public void setHeight(int height) {
		this.height = height;
		this.setHeight(height+"px");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setLeft(int)
	 */
	public void setLeft(int left) {
		setPopupPosition(left, getAbsoluteTop());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#getLeft()
	 */
	public int getLeft() {
		return getAbsoluteLeft();
	}

	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setLocation(int, int)
	 */
	public void setLocation(int top, int left) {
		setPopupPosition(left, top);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setMaximumHeight(int)
	 */
	public void setMaximumHeight(int maxHeight) {
		this.maximumHeight = maxHeight;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setMaximumWidth(int)
	 */
	public void setMaximumWidth(int maxWidth) {
		this.maximumWidth = maxWidth;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setMinimumHeight(int)
	 */
	public void setMinimumHeight(int minHeight) {
		this.minimumHeight = minHeight;
		if(getHeight() < minHeight){
			setHeight(minHeight);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setMinimumWidth(int)
	 */
	public void setMinimumWidth(int minWidth) {
		this.minimumWidth = minWidth;
		if(getWidth() < minWidth){
			setWidth(minWidth);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setSize(int, int)
	 */
	public void setSize(int width, int height) {
		setPixelSize(width, height);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setStyle(java.lang.String)
	 */
	public void setStyle(String style) {
		//TODO what should I put here??
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setTop(int)
	 */
	public void setTop(int top) {
		setPopupPosition(getPopupLeft(), top);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#getTop()
	 */
	public int getTop() {
		return getAbsoluteTop();
	}

	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setUrl(java.lang.String)
	 */
	public void setUrl(String url) {
		Frame theFrame = new Frame(url);
		Composite theComposite = new WidgetComposite(theFrame);
		setContent(theComposite);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#setWidth(int)
	 */
	public void setWidth(int width) {
		this.width = width;
		this.setWidth(width+"px");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#show(boolean)
	 */
	public void show(boolean modal) {
		setModal(modal);
		show();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#showCenter(boolean)
	 */
	public void showCenter(boolean modal) {
		setModal(modal);
		show();
		center();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#updateHeight()
	 */
	public void updateHeight() {

		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#updateWidth()
	 */
	public void updateWidth() {

		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#getParentDesktop()
	 */
	public GDesktopPane getParentDesktop(){
		return this.parentDesktop;
	}

	/**
	 * @return the closed
	 */
	public boolean isClosed() {
	
		return closed;
	}

	/**
	 * @param closed the closed to set
	 */
	public void setClosed(boolean closed) {
	
		this.closed = closed;
	}

	/**
	 * @return the draggable
	 */
	public boolean isDraggable() {
	
		return draggable;
	}

	/**
	 * @return the maximumHeight
	 */
	public int getMaximumHeight() {
	
		return maximumHeight;
	}

	/**
	 * @return the maximumWidth
	 */
	public int getMaximumWidth() {
	
		return maximumWidth;
	}

	/**
	 * @return the minimumHeight
	 */
	public int getMinimumHeight() {
	
		return minimumHeight;
	}

	/**
	 * @return the minimumWidth
	 */
	public int getMinimumWidth() {
	
		return minimumWidth;
	}

	public void onClick(Widget sender) {
		getParentDesktop().getDesktopManager().toFront(this);
	}

	/**
	 * @param parentDesktop the parentDesktop to set
	 */
	private void setParentDesktop(GDesktopPane parentDesktop) {
		this.parentDesktop = parentDesktop;
	}

	/**
	 * 
	 */
	private void maximizar() {
		getParentDesktop().getDesktopManager().maximize(this);
	}

	/**
	 * 
	 */
	private void minimizar() {
//		this.hide();
		getParentDesktop().getDesktopManager().minimize(this);
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		super.setVisible(visible);
	}
	
	public boolean isVisible(){
		return this.visible;
	}
}