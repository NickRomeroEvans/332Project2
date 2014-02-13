package test;
import static org.junit.Assert.*;

import main.Sorter;

import org.junit.Test;

import providedCode.Comparator;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * TestSorter uses JUnit testing to test our sorting algorithms in the Sorter class.
 */

public class TestSorter {
	protected static final int TIMEOUT = 2000; // 2000ms = 2sec
	private Comparator<Integer> comparator = new Comparator<Integer>() {
		public int compare(Integer e1, Integer e2) { return e1 - e2; }
	};
	
	/** Test heapSort =======================================================================================**/
	
	@Test(timeout = TIMEOUT)
	public void test_heapSort_empty(){
		Integer[] test = {};
		Sorter.heapSort(test, comparator);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_heapSort_one_element(){
		Integer[] expected = {1};
		Integer[] test = expected;
		Sorter.heapSort(test, comparator);
		assertArrayEquals(test, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_heapSort_sorted_unique_inserts(){
		Integer[] expected = {0, 1, 2, 3, 4};
		Integer[] test = expected;
		Sorter.heapSort(test, comparator);
		assertArrayEquals(test, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_heapSort_unsorted_unique_inserts(){
		Integer[] expected = {0, 1, 2, 3, 4};
		Integer[] test = {3, 1, 2, 4, 0};
		Sorter.heapSort(test, comparator);
		assertArrayEquals(test, expected);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_MergeSort_Empty(){
		Integer[] test = {};
		Sorter.otherSort(test, comparator);
		Integer[] expected = {};
		assertEquals(test,expected);
	}
	@Test(timeout = TIMEOUT)
	public void test_MergeSort_one_element(){
		Integer[] expected = {1};
		Integer[] test = expected;
		Sorter.otherSort(test, comparator);
		assertArrayEquals(test, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_MergeSort_sorted_unique_inserts(){
		Integer[] expected = {0, 1, 2, 3, 4};
		Integer[] test = expected;
		Sorter.otherSort(test, comparator);
		assertArrayEquals(test, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_MergeSort_unsorted_unique_inserts(){
		Integer[] expected = {0, 1, 2, 3, 4};
		Integer[] test = {3, 1, 2, 4, 0};
		Sorter.otherSort(test, comparator);
		assertArrayEquals(test, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_MergeSort_Bad(){
		Integer[] expected = {0, 1, 2, 3, 4, 10, 15, 20,25, 30};
		Integer[] test = 	 {3, 25 ,1, 30, 2, 20, 10, 4, 15, 0};
		Sorter.otherSort(test, comparator);
		assertArrayEquals(test, expected);
	}
	@SuppressWarnings("deprecation")
	@Test
	public void test_TopkSort_Empty(){
		Integer[] test = {};
		Sorter.topKSort(test, comparator,0);
		Integer[] expected = {};
		assertEquals(test,expected);
	}
	@Test(timeout = TIMEOUT)
	public void test_TopkSort_one_element(){
		Integer[] expected = {1};
		Integer[] test = {1};
		Sorter.topKSort(test, comparator,1);
		assertArrayEquals(test, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_TopkSort_sorted_unique_inserts(){
		Integer[] expected = {4,3,2,1,0};
		Integer[] test = {0, 1, 2, 3, 4};
		Sorter.topKSort(test, comparator,5);
		assertArrayEquals(test, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_TopkSort_unsorted_unique_inserts(){
		Integer[] expected = {4,3,2,1,0};
		Integer[] test = {3, 1, 2, 4, 0};
		Sorter.topKSort(test, comparator,5);
		assertArrayEquals(test, expected);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_TopKSort_max(){
		Integer expected = 5;
		Integer[] test = {0,1,2,3,4,5};
		Sorter.topKSort(test, comparator, 1);
		assertEquals(test[0],expected);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void test_TopKSort_negative_k(){
		Integer[] test = {0};
		Sorter.topKSort(test, comparator, -1);
	}
	
	@Test
	public void test_TopKSort_K_Greater_Length(){
		Integer[] expected = {5, 4, 3 ,2, 1,0};
		Integer[] test = {0,1,2,3,4,5};
		Sorter.topKSort(test, comparator, 100);
		assertArrayEquals(test, expected);
	}
	@Test
	public void test_TopKSort_K_Equals_Zero(){
		Integer[] expected = {0,1,7,3,20,5};
		Integer[] test = {0,1,7,3,20,5};
		Sorter.topKSort(test, comparator, 0);
		assertArrayEquals(test, expected);
	}
}
