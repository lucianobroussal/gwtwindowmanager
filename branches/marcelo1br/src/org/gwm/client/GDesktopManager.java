package org.gwm.client;

public interface GDesktopManager {
	/**
	 * Generally, indicate that this frame has focus.
	 */
	public void activateFrame(GInternalFrame f);

	/**
	 * This method is normally called when the user has indicated that they will
	 * begin dragging a component around.
	 */
	public void beginDraggingFrame(GInternalFrame f);

	/**
	 * This methods is normally called when the user has indicated that they
	 * will begin resizing the frame.
	 */
	public void beginResizingFrame(GInternalFrame f, int direction);

	/**
	 * Generally, this call should remove the frame from it's parent.
	 */
	public void closeFrame(GInternalFrame f);

	/**
	 * Generally, indicate that this frame has lost focus.
	 */
	public void deactivateFrame(GInternalFrame f);

	/**
	 * Generally, remove any iconic representation that is present and restore
	 * the frame to it's original size and location.
	 */
	public void deiconifyFrame(GInternalFrame f);

	/**
	 * The user has moved the frame.
	 */
	public void dragFrame(GInternalFrame f, int newX, int newY);

	/**
	 * This method signals the end of the dragging session.
	 */
	public void endDraggingFrame(GInternalFrame f);

	/**
	 * This method signals the end of the resize session.
	 */
	public void endResizingFrame(GInternalFrame f);

	/**
	 * Generally, remove this frame from it's parent and add an iconic
	 * representation.
	 */
	public void iconifyFrame(GInternalFrame f);

	/**
	 * Generally, the frame should be resized to match it's parents bounds.
	 */
	public void maximizeFrame(GInternalFrame f);

	/**
	 * Generally, this indicates that the frame should be restored to it's size
	 * and position prior to a maximizeFrame() call.
	 */
	public void minimizeFrame(GInternalFrame f);

	/**
	 * If possible, display this frame in an appropriate location.
	 */
	public void openFrame(GInternalFrame f);

	/**
	 * The user has resized the component.
	 */
	public void resizeFrame(GInternalFrame f, int newX, int newY, int newWidth,
			int newHeight);

	/**
	 * This is a primitive reshape method.
	 */
	public void setBoundsForFrame(GInternalFrame f, int newX, int newY,
			int newWidth, int newHeight);
}