package testB;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import phaseA.MoveToFrontList;
import phaseB.HashTable;
import phaseB.StringHasher;
import providedCode.Comparator;
import providedCode.DataCounter;
import providedCode.Hasher;

import test.TestDataCounter;


public class TestHashTable extends TestDataCounter {
	private static final int TIMEOUT = 2000; // 2000ms = 2sec
	
	/** Creates a HashTable before each test cases **/
	@Override
	public DataCounter<Integer> getDataCounter() {
		dcClass = "HashTable";
		
		return new HashTable<Integer>(new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) { return e1 - e2; }
		}, new Hasher<Integer>() {
			public int hash(Integer e) {
				int sum = 0;
				for (int i = 0; i < e.toString().length(); i++) { 
					sum += (int) e.toString().charAt(i) * i; 
				}
				return sum % 79;
			}
		});
	}

	@Test(timeout = TIMEOUT)
	public void test_structure_after_resize() {
		int[] testInsertArray = {0,1,2,3,4,5};
		for (int element : testInsertArray) dc.incCount(element);
		
		int[] expectedArray = {0,1,2,3,4,5};
		int[] returnArray = iterateAndFillArray(testInsertArray.length);
		assertArrayEquals(expectedArray, returnArray);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_structure_after_two_resizes() {
		int[] expectedArray = { 0,  1,  2,  3,  4,  5,  6,  7,  8,  9,
							   10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
							   20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
							   30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
							   40, 41, 42, 43, 44, 45, 46, 47, 48, 49};
		for(int num : expectedArray) { dc.incCount(num); }
		
		int [] returnArray = iterateAndFillArray(expectedArray.length);
		assertArrayEquals(expectedArray, returnArray);
	}
}
