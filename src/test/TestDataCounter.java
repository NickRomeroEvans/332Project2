package test;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.*;

import providedCode.DataCount;
import providedCode.DataCounter;
import providedCode.SimpleIterator;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * TestDataCounter uses JUnit tests to test our implementation of DataCounter objects.
 * The common testing methods for each subclass of DataCounter are found here.
 */

public abstract class TestDataCounter {
	protected static final int TIMEOUT = 2000; // 2000ms = 2sec
	protected DataCounter<Integer> dc;
	protected abstract DataCounter<Integer> getDataCounter();
	protected String dcClass;
	
	@Before
	public void setUp() {
		dc = getDataCounter();
	}
	
	/** Test Size =======================================================================================**/
	
	@Test(timeout = TIMEOUT)
	public void test_init_empty() {
		assertEquals("" + dcClass + " should have size 0 when constructed", 0, dc.getSize());
	}

	@Test(timeout = TIMEOUT)
	public void test_size_after_adding_single_num() {
		addAndTestSize("" + dcClass + " should have size 1 after adding single 5", new int[]{5}, 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_size_after_adding_many_same_num() {
		addAndTestSize("" + dcClass + " should have size 1 after adding multiple 5", new int[]{5,5,5}, 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_size_after_adding_unique_nums() {
		int[] testArray = {0,1,2,3,4};
		addAndTestSize("Added " + Arrays.toString(testArray) + ". " + dcClass + " should have size 5", testArray, 5);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_size_after_adding_duplicate_nums(){
		int[] testArray = {0,0,1,1,2,2,3,3,4,4};
		addAndTestSize("Added " + Arrays.toString(testArray) + ". " + dcClass + " should have size 5", testArray, 5);
	}
	
	/** Test getCount =======================================================================================**/
	
	@Test(timeout = TIMEOUT)
	public void test_get_count_after_adding_many_same_num(){
		int key = 9;
		int[] testArray = {9, 9, 9, 9, 9, 9, 9};
		addAndGetCount("Added " + Arrays.toString(testArray) + ", key=" + key, testArray, key, 7);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_get_count_after_adding_many_diff_nums(){
		int key = 5;
		int[] testArray = {0, 5, -1, 5, 1, 5, 2};
		addAndGetCount("Added " + Arrays.toString(testArray) + ", key=" + key, testArray, key, 3);
	}
	
	/** Test Iterator =======================================================================================**/

	// ------ Null iterator tests
	@Test(timeout = TIMEOUT)
	public void test_init_non_null_it(){
		assertFalse("The iterator should not be null once an empty " + dcClass + " is created.", dc.getIterator() == null);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_non_null_it(){
		dc.incCount(5);
		assertFalse("The iterator should not be null once an element is added to the " + dcClass + ".", dc.getIterator() == null);
	}
	
	// ------ hasNext tests
	@Test(timeout = TIMEOUT)
	public void test_hasNext_empty(){
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		assertFalse("The iterator should not have a next element on an empty " + dcClass + ".", iter.hasNext());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_hasNext_non_empty(){
		dc.incCount(5);
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		assertTrue("The iterator should have a next element on an uniterated non-empty " + dcClass + ".", iter.hasNext());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_hasNext_fully_iterated(){
		int[] testInsertArray = {0,2,4,6,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		for (int i = 0; i < 5; i++) { iter.next(); }
		
		assertFalse("The iterator should not have a next element after fully iterating.", iter.hasNext());
	}
	
	// ------ next tests
	@Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
	public void test_next_empty_iterator(){
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		iter.next();
	}
	
	@Test(timeout = TIMEOUT)
	public void test_next_non_empty(){
		dc.incCount(5);
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		assertEquals("The iterator's next element should be 5.", 5, (int)iter.next().data);
	}
	
	@Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
	public void test_next_fully_iterated(){
		int[] testInsertArray = {0,2,4,6,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		for (int i = 0; i < 5; i++) { iter.next(); }
		iter.next();
	}
	
	
	/** Helper methods =======================================================================================**/
	
	protected int[] iterateAndFillArray(int len){
		int[] arr = new int[len];
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		int i = 0;
		while (iter.hasNext()) { arr[i++] = (java.lang.Integer) iter.next().data;	}
		
		return arr;
	}

	protected void addAndTestSize(String message, int[] input, int unique){
		for(int num : input) { dc.incCount(num); }
		assertEquals(message, unique, dc.getSize());
	}
	
	protected void addAndGetCount(String message, int[] input, int key, int expected){
		for(int num : input) { dc.incCount(num); }
		assertEquals(message, expected, dc.getCount(key));
	}

}
