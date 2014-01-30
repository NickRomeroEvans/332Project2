package testA;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import phaseA.FourHeap;
import providedCode.BinarySearchTree;
import providedCode.Comparator;
import providedCode.DataCounter;

import test.TestDataCounter;
import test.TestSorter;


public class TestFourHeap{
	private static final int TIMEOUT = 2000; // 2000ms = 2sec
	private FourHeap<Integer> fh;
	
	@Before
	public void stetup(){
		fh = new FourHeap<Integer>(new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) { return e1 - e2; }
		});
	}
	
	/** Test isEmpty =======================================================================================**/
	
	@Test(timeout = TIMEOUT)
	public void test_empty(){
		assertTrue("Heap should be empty when constructed.", fh.isEmpty());
	}

	@Test(timeout = TIMEOUT)
	public void test_not_empty_after_insertion(){
		fh.insert(1);
		assertFalse("Heap should not be empty when constructed.", fh.isEmpty());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_empty_after_insert_delete(){
		fh.insert(1);
		fh.deleteMin();
		assertTrue("Heap should be empty after deleting its only element.", fh.isEmpty());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_empty_after_deleting_mult_uniques(){
		int[] test = {1,2,3,4,5,6,7};
		for(int num : test){ fh.insert(num); }
		for (int i = 0; i < 7; i++) { fh.deleteMin(); }
		
		assertTrue("Heap should be empty after deleting all its elements.", fh.isEmpty());
	}
	
	/** Test deleteMin =======================================================================================**/
	
	@Test(timeout = TIMEOUT, expected = java.lang.UnsupportedOperationException.class)
	public void test_delete_empty(){
		fh.deleteMin();
	}
	
	@Test(timeout = TIMEOUT)
	public void test_deletemin_one_item(){
		fh.insert(1);
		assertEquals(1, (int)fh.deleteMin());
	}
	
	@Test(timeout = TIMEOUT, expected = java.lang.UnsupportedOperationException.class)
	public void test_deletemin_twice_one_item(){
		fh.insert(1);
		fh.deleteMin();
		fh.deleteMin();
	}
	
	@Test(timeout = TIMEOUT)
	public void test_delete_one_after_multiple_sorted_insert(){
		int[] test = {1,2,3,4,5,6,7};
		for(int num : test){ fh.insert(num); }
		
		assertEquals(1, (int)fh.deleteMin());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_delete_one_after_multiple_unsorted_insert(){
		int[] test = {5,7,4,3,1,6,2};
		for(int num : test){ fh.insert(num); }
		
		assertEquals(1, (int)fh.deleteMin());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_deletemin_multiple_items_inserted_in_order(){
		int[] test = {1,2,3,4,5,6,7};
		for(int num : test){ fh.insert(num); }
		
		int[] expected = {1,2,3,4,5,6,7};
		int[] received = deleteAndFillArray(expected.length);
		assertArrayEquals("Elements inserted in order should be deleted in order.", expected, received);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_deletemin_multiple_items_inserted_unordered(){
		int[] test = {5,7,4,3,1,6,2};
		for(int num : test){ fh.insert(num); }
		
		int[] expected = {1,2,3,4,5,6,7};
		int[] received = deleteAndFillArray(expected.length);
		assertArrayEquals("Elements inserted out of order should be deleted in order.", expected, received);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_deleteMin_same_as_findMin(){
		fh.insert(1);
		assertEquals(fh.findMin(), fh.deleteMin());
	}
	
	/** Test findMin =======================================================================================**/
	
	@Test(timeout = TIMEOUT, expected = java.lang.UnsupportedOperationException.class)
	public void test_findMin_empty(){
		fh.findMin();	
	}
	
	@Test(timeout = TIMEOUT)
	public void test_findMin_one_element(){
		fh.insert(1);
		assertEquals(1, (int)fh.findMin());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_findMin_mult_sorted_elements(){
		int[] test = {1,2,3,4,5,6,7};
		for(int num : test){ fh.insert(num); }
		
		assertEquals(1, (int)fh.findMin());
	}
	
	@Test(timeout = TIMEOUT)
	public void test_findMin_mult_unsorted_elements(){
		int[] test = {5,7,4,3,1,6,2};
		for(int num : test){ fh.insert(num); }
		
		assertEquals(1, (int)fh.findMin());
	}
	
	/** Private methods =======================================================================================**/
	private int[] deleteAndFillArray(int len) {
		int[] arr = new int[len];
		for(int i = 0; i < len; i++){
			arr[i] = fh.deleteMin();
		}
		return arr;
	}
}
