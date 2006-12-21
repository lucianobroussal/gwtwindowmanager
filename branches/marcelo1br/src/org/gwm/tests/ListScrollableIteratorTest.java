/**
 * 
 */
package org.gwm.tests;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.gwm.client.ListScrollableIterator;

/**
 * @author Marcelo Emanoel
 * @since 20/12/2006
 */
public class ListScrollableIteratorTest extends TestCase {
	
	private ListScrollableIterator orderedScroll;
	private ListScrollableIterator emptyScroll;
	private ArrayList orderedList;
	private ArrayList emptyList;
	private Integer one;
	private Integer two;
	private Integer three;
	private Integer four;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		orderedList = new ArrayList();
		emptyList = new ArrayList();
		
		one = new Integer(1);
		two = new Integer(2);
		three = new Integer(3);
		four = new Integer(4);
		
		orderedList.add(one);
		orderedList.add(two);
		orderedList.add(three);
		orderedList.add(four);
		
		orderedScroll = new ListScrollableIterator(orderedList);
		emptyScroll = new ListScrollableIterator(emptyList);
	}

	/**
	 * Test method for {@link ListScrollableIterator#hasPrevious()}.
	 */
	public void testHasPrevious() {
		assertEquals(orderedList.size(), 4);
		assertFalse(orderedScroll.hasPrevious());
		assertTrue(orderedScroll.hasNext());
		Integer next = (Integer) orderedScroll.next();
		assertEquals(one, next);
		assertFalse(orderedScroll.hasPrevious());
		assertTrue(orderedScroll.hasNext());
		Integer nextOne = (Integer) orderedScroll.next();
		assertEquals(two, nextOne);
		assertTrue(orderedScroll.hasPrevious());
		Integer previous = (Integer) orderedScroll.previous();
		assertEquals(one, previous);
	}

	/**
	 * Test method for {@link ListScrollableIterator#previous()}.
	 */
	public void testPrevious() {
	}

	/**
	 * Test method for {@link ListScrollableIterator#hasNext()}.
	 */
	public void testHasNext() {
		assertFalse(emptyScroll.hasNext());
		try{
			emptyScroll.next();
			fail("Shouldn't pass from this point.");
		}
		catch(Exception e){}
	}

	/**
	 * Test method for {@link ListScrollableIterator#next()}.
	 */
	public void testNext() {

		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link ListScrollableIterator#remove()}.
	 */
	public void testRemove() {
		try{
			emptyScroll.remove();
			fail("Shouldn't work.");
		}
		catch(Exception e){}
		
		try{
			orderedScroll.remove();
			fail("Must iterate first.");
		}
		catch(Exception e){}
		
		assertTrue(orderedScroll.hasNext());
		Integer next = (Integer) orderedScroll.next();
		assertEquals(one, next);
		assertEquals(4, orderedList.size());
		orderedScroll.remove();
		assertEquals(3, orderedList.size());
		assertTrue(orderedScroll.hasNext());
		Integer nextOne = (Integer) orderedScroll.next();
		assertNotSame(next, nextOne);
		assertFalse(orderedScroll.hasPrevious());
	}

}
