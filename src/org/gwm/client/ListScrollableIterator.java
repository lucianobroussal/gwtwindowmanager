package org.gwm.client;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * 
 */

/**
 * @author Marcelo Emanoel
 * @since 20/12/2006
 */
public class ListScrollableIterator implements ScrollableIterator{
	private List collection;
	private Object previous;
	private Object next;
	private Object currentObject;
	private int currentPosition;
	
	public ListScrollableIterator(List theList){
		collection = theList;
		previous = null;
		next = (collection.size() > 0 ? collection.get(0) : null);
		currentObject = null;
		currentPosition = 0;
	}

	public boolean hasPrevious() {
		return (currentPosition > 1);
	}

	public Object previous() {
		if(hasPrevious()){
			currentPosition--;
			next = currentObject;
			currentObject = previous;
			previous = (collection.get(currentPosition));
		}
		return currentObject;
	}

	public boolean hasNext() {
		return (currentPosition < collection.size());
	}

	public Object next(){
		if(hasNext()){
			currentPosition++;
			previous = currentObject;
			currentObject = (currentPosition == 0 && next == null?collection.get(currentPosition):next);
			next = collection.get(currentPosition);
		}
		else
			throw new NoSuchElementException("No such object on the collection.");
		return currentObject;
	}

	public void remove() {
		collection.remove(currentPosition);
		currentObject = next;
		next = collection.get(currentPosition);
	}
}
