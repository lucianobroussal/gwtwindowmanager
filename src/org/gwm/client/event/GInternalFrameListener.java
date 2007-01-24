/**
 * 
 */
package org.gwm.client.event;


/**
 * This is the interface should be implemented for those objects who want to
 * know about the events of a GInternalFrame.
 * 
 * @author Marcelo Emanoel
 * @since 30/11/2006
 */
public interface GInternalFrameListener {

    public void frameResized(GInternalFrameEvent evt);

    public void frameOpened(GInternalFrameEvent evt);

    public void frameClosed(GInternalFrameEvent evt);

    public void frameMaximized(GInternalFrameEvent evt);

    public void frameMinimized(GInternalFrameEvent evt);

    public void frameIconified(GInternalFrameEvent evt);

    public void frameRestored(GInternalFrameEvent evt);

    public void frameMoved(GInternalFrameEvent evt);

}
