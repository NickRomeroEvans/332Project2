package testA;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;

import org.junit.Test;

import phaseA.MoveToFrontList;
import providedCode.Comparator;
import providedCode.DataCount;
import providedCode.DataCounter;
import providedCode.SimpleIterator;
import test.TestDataCounter;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * TestMoveToFrontList uses JUnit testing to test our implementation of MoveToFrontList.
 */
public class TestMoveToFrontList extends TestDataCounter{
	private static final int TIMEOUT = 2000; // 2000ms = 2sec

	/** Creates a MoveToFrontList before each test cases **/
	@Override
	public DataCounter<Integer> getDataCounter() {
		dcClass = "List";
		return new MoveToFrontList<Integer>(new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) { return e1 - e2; }
		});
	}
	
	/** Iterator methods ======================================================================================**/	

	@Test(timeout = TIMEOUT)
	public void test_move_to_front(){
		int[] testInsertArray = {0,2,4,6,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		dc.incCount(4);
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		assertEquals("Front element should be 4.", 4, (int)iter.next().data);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_move_to_front_twice(){
		int[] testInsertArray = {0,2,4,6,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		dc.incCount(4);
		dc.incCount(4);
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		assertEquals("Front element should be 4.", 4, (int)iter.next().data);
	}
	
	public void test_move_to_front_2_elements() {
		int[] testInsertArray = {0,2,4,6,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		dc.incCount(4);
		dc.incCount(8);
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		assertEquals("Front element should be 8.", 8, iter.next());
	}
	
	public void test_move_to_front_alternate_elements() {
		int[] testInsertArray = {0,2,4,6,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		dc.incCount(4);
		dc.incCount(8);
		dc.incCount(4);
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		assertEquals("Front element should be 4.", 4, iter.next());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_order_after_getSize(){
		int[] testInsertArray = {0,2,4,6,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		dc.getSize();
		int[] expectedArr = {8,6,4,2,0};
		int[] testRetrieveArray = iterateAndFillArray(expectedArr.length);
		assertEquals("Added " + Arrays.toString(testInsertArray) + ". Order should be " + Arrays.toString(expectedArr) + ".",
					Arrays.toString(expectedArr),
					Arrays.toString(testRetrieveArray));		
	}
	
	@Test(timeout = TIMEOUT)
	public void test_order_after_unique_incCout(){
		int[] testInsertArray = {0,2,4,6,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		int[] expectedArr = {8,6,4,2,0};
		int[] testRetrieveArray = iterateAndFillArray(expectedArr.length);
		
		assertEquals("Added " + Arrays.toString(testInsertArray) + ". Order should be " + Arrays.toString(expectedArr) + ".",
					Arrays.toString(expectedArr),
					Arrays.toString(testRetrieveArray));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_order_after_same_incCouts(){
		int[] testInsertArray = {0,0,2,2,4,4,6,6,8,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		int[] expectedArr = {8,6,4,2,0};
		int[] testRetrieveArray = iterateAndFillArray(expectedArr.length);
		
		assertEquals("Added " + Arrays.toString(testInsertArray) + ". Order should be " + Arrays.toString(expectedArr) + ".",
					Arrays.toString(expectedArr),
					Arrays.toString(testRetrieveArray));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_order_after_alternate_incCouts(){
		int[] testInsertArray = {0,2,0,6,0,8};
		for (int element : testInsertArray) dc.incCount(element);
		
		
		int[] expectedArr = {8,0,6,2};
		int[] testRetrieveArray = iterateAndFillArray(expectedArr.length);
		
		assertEquals("Added " + Arrays.toString(testInsertArray) + ". Order should be " + Arrays.toString(expectedArr) + ".",
					Arrays.toString(expectedArr),
					Arrays.toString(testRetrieveArray));
	}
	
	/** Private methods =======================================================================================**/
	/*
	private int[] iterateAndFillArray(int len){
		int[] arr = new int[len];
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		int i = 0;
		while (iter.hasNext()) { arr[i++] = iter.next().data;	}
		
		return arr;
	}

	private void addAndTestSize(String message, int[] input, int unique){
		for(int num : input) { dc.incCount(num); }
		assertEquals(message, unique, dc.getSize());
	}
	*/
}
