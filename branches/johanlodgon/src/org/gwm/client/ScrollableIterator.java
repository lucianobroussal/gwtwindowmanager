/**
 * 
 */
package org.gwm.client;

import java.util.Iterator;

/**
 * An iterator over a collection. Iterate forward and backward on the collection.
 * @author Marcelo Emanoel
 * @since 20/12/2006
 */
public interface ScrollableIterator extends Iterator {
	
	/**
	 * Returns true if the iteration has previous elements. 
	 * @return
	 */
	public boolean hasPrevious();
	
	/**
	 * Returns the previous element in the iteration.
	 * @return
	 */
	public Object previous();
}
