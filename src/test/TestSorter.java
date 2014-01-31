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
}
