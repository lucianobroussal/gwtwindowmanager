package org.gwm.client.oldwindow;


import java.util.ArrayList;
import java.util.List;

import org.gwm.client.GDesktopPane;
import org.gwm.client.GInternalFrame;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
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
public class DefaultWidgetInternalFrame extends PopupPanel implements MouseListener, GInternalFrame{
	
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
	private int pixelHeight;
	private int pixelWidth;
	private boolean closable;
	private boolean maximizable;
	private boolean minimizable;
	private boolean resizable;
	private boolean dragging;
	private boolean firstTimeShow;
	private boolean resizing;
	private boolean modal;
	private List listeners;
	
	/**
	 * Constructs a DefaultWidgetInternalFrame with the default settings
	 * @param caption The Caption of the window.
	 */
	public DefaultWidgetInternalFrame(String caption){
		initialize();
		mount();
		setupListeners();
		setupSise();
		setupStyle();
		setCaption(caption);
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
				close();
			}
		});
		maxButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				maximize();
			}
		});
		minButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				minimize();
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
					fireWindowResized();
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
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#minimize()
	 */
	public void minimize() {
		setPopupPosition(0, Window.getClientHeight()-44);
		setPixelHeight(44);
		fireWindowMinimized();
	}
	/*
	 * (non-Javadoc)
	 * @see org.gwm.client.GInternalFrame#maximize()
	 */
	public void maximize() {
		setPopupPosition(0, 0);
		int diferencaY = Window.getClientHeight() - getPixelHeight() - 70;
		int diferencaX = Window.getClientWidth() - getPixelWidth() - 65;
		setPixelHeight(getPixelHeight() + diferencaY);
		setPixelWidth(getPixelWidth() + diferencaX);
		fireWindowMaximized();
	}
	/**
	 * Closes this window. Fires two events: frameClosing and frameClosed.
	 * FrameClosing event is fired before the window close and frameClosed just 
	 * after the closing of the window.
	 */
	protected void close() {
		fireWindowClosing();
		hide();
		fireWindowClosed();
	}
	/**
	 * Shows the window. Fires the frameActivated event.
	 */
	public void show() {
		super.show();
		if(firstTimeShow)
			ajusta();
		if(!modal){
			DOM.removeEventPreview(this);
		}
		firstTimeShow = false;
		fireFrameActivated();
	}
	
	public void centraliza(int width, int height) {
		int altura = getOffsetHeight();
		int largura = getOffsetWidth();
		setPopupPosition((width - largura)/2, (height - altura)/2);
	}

	/**
	 * Ajusta os divs para que a janela continue desenhada.
	 */
	private void ajusta() {
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
	private void fireWindowResized(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameResized(new GFrameEvent(this));
		}
	}
	private void fireWindowClosing(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameClosing(new GFrameEvent(this));
		}
	}
	private void fireWindowClosed(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameClosed(new GFrameEvent(this));
		}
	}
	private void fireWindowMaximized(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameMaximized(new GFrameEvent(this));
		}
	}
	private void fireWindowMinimized(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameMinimized(new GFrameEvent(this));
		}
	}
	private void fireWindowMoved(){
		for(int i=0; i < listeners.size(); i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameMoved(new GFrameEvent(this));
		}
	}
	
	/*
	 * Gets and Sets 
	 */
	
	public Composite getContentPane() {
		return contentPane;
	}

	public void setContentPane(Composite contentPane) {
		if(contentPane != null){
			windowLayout.remove(this.contentPane);
			windowLayout.add(contentPane, DockPanel.CENTER);
			contentPane.setWidth("100%");
			contentPane.setHeight("100%");
			contentPane.setStyleName("com_core_window_content");
		}
		this.contentPane = contentPane;
		ajusta();
	}
	
	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		String newCaption = (caption != null ? caption : "");
		topGradient.setHTML(newCaption);
		this.caption = caption;
	}

	public boolean isClosable() {
		return closable;
	}

	public void setClosable(boolean closable) {
		closeButton.setVisible(closable);
		this.closable = closable;
	}

	public boolean isMaximizable() {
		return maximizable;
	}

	public void setMaximizable(boolean maximizable) {
		maxButton.setVisible(maximizable);
		this.maximizable = maximizable;
	}

	public boolean isMinimizable() {
		return minimizable;
	}

	public void setMinimizable(boolean minimizable) {
		minButton.setVisible(minimizable);
		this.minimizable = minimizable;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		String style = "com_core_window_bottom_right_corner";
		if(!resizable){
			style+="_noresize";
		}
		rightBottomCorner.setStyleName(style);
		this.resizable = resizable;
	}
	
	public int getPixelHeight() {
		return pixelHeight;
	}

	public void setPixelHeight(int pixelHeight) {
		this.pixelHeight = pixelHeight;
		this.setHeight(pixelHeight+"px");
//		ajusta();
	}

	public int getPixelWidth() {
		return pixelWidth;
	}

	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
		this.setWidth(pixelWidth+"px");
//		ajusta();
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
			fireWindowMoved();
		}
	}
	
	public void onMouseUp(Widget sender, int x, int y) {
		dragging = false;
		DOM.releaseCapture(sender.getElement());
	}
	
	public void onMouseEnter(Widget sender) {}
	public void onMouseLeave(Widget sender) {}
	
	public void addWindowListener(GFrameListener listener){
		listeners.add(listener);
	}
	
	public void removeWindowListener(GFrameListener listener){
		listeners.remove(listener);
	}
	public void destroy() {
		close();
	}
	public String getId() {
		return getCaption();
	}
	public boolean isMaximized() {
		return false;
	}
	public boolean isMinimized() {

		// TODO Auto-generated method stub
		return false;
	}
	public void setContent(Widget widget) {

		// TODO Auto-generated method stub
		
	}
	public void setContent(String content) {

		// TODO Auto-generated method stub
		
	}
	public void setDestroyOnClose() {

		// TODO Auto-generated method stub
		
	}
	public void setDraggable(boolean draggable) {

		// TODO Auto-generated method stub
		
	}
	public void setHeight(int height) {

		// TODO Auto-generated method stub
		
	}
	public void setLeft(int left) {

		// TODO Auto-generated method stub
		
	}
	public void setLocation(int top, int left) {

		// TODO Auto-generated method stub
		
	}
	public void setMaximumHeight(int maxHeight) {

		// TODO Auto-generated method stub
		
	}
	public void setMaximumWidth(int maxWidth) {

		// TODO Auto-generated method stub
		
	}
	public void setMinimumHeight(int minHeight) {

		// TODO Auto-generated method stub
		
	}
	public void setMinimumWidth(int minWidth) {

		// TODO Auto-generated method stub
		
	}
	public void setSize(int width, int height) {

		// TODO Auto-generated method stub
		
	}
	public void setStyle(String style) {

		// TODO Auto-generated method stub
		
	}
	public void setTop(int top) {

		// TODO Auto-generated method stub
		
	}
	public void setUrl(String url) {

		// TODO Auto-generated method stub
		
	}
	public void setWidth(int width) {
		setPixelWidth(width);
	}
	public void show(boolean modal) {
		setModal(modal);
		show();
	}
	public void showCenter(boolean modal) {
		setModal(modal);
		show();
		centraliza(Window.getClientWidth(), Window.getClientHeight());
	}
	public void toFront() {

		// TODO Auto-generated method stub
		
	}
	public void updateHeight() {

		// TODO Auto-generated method stub
		
	}
	public void updateWidth() {

		// TODO Auto-generated method stub
		
	}
	
	public GDesktopPane getParentDesktop(){
		return null;
	}
}