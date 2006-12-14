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

public class GWTInternalFrame extends PopupPanel implements MouseListener, GInternalFrame{
	
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
	
	public GWTInternalFrame(String caption){
		inicializa();
		monta();
		listeners();
		setSise();
		setupStyle();

		setCaption(caption);
	}
	private void inicializa() {
		listeners = new ArrayList();
		windowLayout = new DockPanel();
		top = new HorizontalPanel();
		bottom = new HorizontalPanel();
		contentPane = new BlankComposite();
		
		/*
		 * Imagens.
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
		
		topGradient.setHTML(getCaption());
		
		firstTimeShow = true;
		setResizable(true);
		setMaximizable(false);
		setMinimizable(false);
		setClosable(true);
	}
	public void setModal(boolean modal){
		this.modal = modal;
	}
	public boolean isModal(){
		return this.modal;
	}
	private void setSise() {
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

	private void setupStyle() {
		setStyleName("com_core_window");
		
		top.setStyleName("com_core_window_top");
		bottom.setStyleName("com_core_window_bottom");
		left.setStyleName("com_core_window_left");
		right.setStyleName("com_core_window_right");
		leftTopCorner.setStyleName("com_core_window_top_left_corner");
		rightTopCorner.setStyleName("com_core_window_top_right_corner");
		leftBottomCorner.setStyleName("com_core_window_bottom_left_corner");
		rightBottomCorner.setStyleName("com_core_window_bottom_right_corner");
		bottomGradient.setStyleName("com_core_window_bottom_gradient");
		topGradient.setStyleName("com_core_window_top_gradient");
		minButton.setStyleName("com_core_window_min_button");
		maxButton.setStyleName("com_core_window_max_button");
		closeButton.setStyleName("com_core_window_close_button");
		contentPane.setStyleName("com_core_window_content");
		topGradient.addStyleName("com_core_window_caption");
	}

	private void monta() {
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

	private void listeners() {
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
				//soltou o botao do mouse..
				if(resizable){
					resizing = false;
					DOM.releaseCapture(sender.getElement());
				}
			}
		
			public void onMouseMove(Widget sender, int x, int y) {
				//ta mexendo com o botao apertado...
				//TODO CONSERTAR O RESIZE!!!!
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
		
			public void onMouseLeave(Widget sender) {}
		
			public void onMouseEnter(Widget sender) {}
		
			public void onMouseDown(Widget sender, int x, int y) {
				//apertou o botao do mouse...
				if(resizable){
					resizing = true;
					DOM.setCapture(sender.getElement());
					dragStartX = x;
					dragStartY = y;
				}
			}
		});
	}

	public void minimize() {
		setPopupPosition(0, Window.getClientHeight()-44);
		setPixelHeight(44);
		fireWindowMinimized();
	}

	public void maximize() {
		setPopupPosition(0, 0);
		int diferencaY = Window.getClientHeight() - getPixelHeight() - 70;
		int diferencaX = Window.getClientWidth() - getPixelWidth() - 65;
		setPixelHeight(getPixelHeight() + diferencaY);
		setPixelWidth(getPixelWidth() + diferencaX);
		fireWindowMaximized();
	}
	protected void close() {
		fireWindowClosing();
		hide();
		fireWindowClosed();
	}
	public void show() {
		super.show();
		if(firstTimeShow)
			ajusta();
		if(!modal){
			DOM.removeEventPreview(this);
		}
		firstTimeShow = false;
		fireWindowActivated();
	}
	private void fireWindowActivated() {
		for(int i=0; i < listeners.size();i++){
			GFrameListener listener = (GFrameListener) listeners.get(i);
			listener.frameActivated(new GFrameEvent(this));
		}
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
	public void onWindowResized(int width, int height) {
		
	}
	public void destroy() {

		// TODO Auto-generated method stub
		
	}
	public String getId() {

		// TODO Auto-generated method stub
		return null;
	}
	public boolean isMaximized() {

		// TODO Auto-generated method stub
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

		// TODO Auto-generated method stub
		
	}
	public void show(boolean modal) {

		// TODO Auto-generated method stub
		
	}
	public void showCenter(boolean modal) {

		// TODO Auto-generated method stub
		
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