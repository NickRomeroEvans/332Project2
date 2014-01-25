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
	
	public FourHeap<Integer> fh() {
		return new FourHeap<Integer>(new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) { return e1 - e2; }
		});
	}
	@Before
	public void stetup(){
		fh = fh();
	}
	
	
	@Test(timeout = TIMEOUT)
	public void test_empty(){
		assertTrue("Heap should be empty when constructed", fh.isEmpty());
	}

	@Test(timeout = TIMEOUT)
	public void test_empty_after_insertion(){
		int test = 1;
		fh.insert(test);
		assertFalse("Heap should not be empty when constructed", fh.isEmpty());
	}
	
	
	
	@Test(timeout = TIMEOUT)
	public void test_deletemin_one_item(){
		int[] test = {1};
		fh.insert(test[0]);
		int[] v = {fh.deleteMin()};
		assertArrayEquals(test, v);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_heap_insert(){
		int[] test = {1,6,3,4,5,2,7};
		for(int i = 0; i< test.length; i++){
			fh.insert(test[i]);
		}
		int[] v = new int[test.length];
		//System.out.println("Array: "+ Arrays.toString(fh.getHeap()));
		for(int i = 0; i< test.length; i++){
			v[i] =fh.getHeap()[i].intValue();
		}
		assertArrayEquals("Expected: " +Arrays.toString(test)+ " Received: " + Arrays.toString(fh.getHeap()),test, v);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_deletemin_multiple_items_inserted_in_order(){
		int[] test = {1,2,3,4,5,6,7};
		for(int i = 0; i< test.length; i++){
			fh.insert(test[i]);
		}
		int[] v = new int[test.length];
		for(int i = 0; i< test.length; i++){
			v[i] =fh.deleteMin();
		}
		assertArrayEquals("Expected: " +Arrays.toString(test)+ " Received: " + Arrays.toString(v),test, v);
	}
}
