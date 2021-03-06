/* Austin Briggs and Nick Evans
 * 2/13/14
 * CSE 332 AB
 * Sam Wilson
 * Project 2B - Shake n Bacon
 */
package testB;
import static org.junit.Assert.*;

import org.junit.Test;

import phaseB.HashTable;
import phaseB.StringHasher;
import providedCode.Comparator;
import providedCode.DataCounter;
import providedCode.Hasher;

import test.TestDataCounter;

/**
 * TestHashTable uses JUnit testing to test our implementation of HashTable.
 */
public class TestHashTable extends TestDataCounter {
	private static final int TIMEOUT = 2000; // 2000ms = 2sec
	Hasher<Integer> hasher;
	
	/** Creates a HashTable before each test cases **/
	@Override
	public DataCounter<Integer> getDataCounter() {
		dcClass = "HashTable";
		hasher = new Hasher<Integer>() {
			public int hash(Integer e) {
				// Add 79 to get positives results for negative e's
				return (e + 79) % 79;
			}
		};
		return new HashTable<Integer>(new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) { return e1 - e2; }
		}, hasher);
	}

	/** Test resize	 *********************************************************/
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
	
	
	/** Test collisions  *****************************************************/
	@Test(timeout = TIMEOUT)
	public void test_one_collision() {
		int[] insertArray = {1, 1};
		for(int num : insertArray) { dc.incCount(num); }
		
		int[] expectedArray = {1};
		int[] returnArray = iterateAndFillArray(expectedArray.length);
		assertArrayEquals(expectedArray, returnArray);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_two_different_collisions() {
		int[] insertArray = {0, 1, 0, 1};
		for(int num : insertArray) { dc.incCount(num); }
		
		int[] expectedArray = {0, 1};
		int[] returnArray = iterateAndFillArray(expectedArray.length);
		assertArrayEquals(expectedArray, returnArray);
	}
	
	@Test(timeout = TIMEOUT)
	public void test_two_of_same_collision() {
		int[] insertArray = {1, 1, 1};
		for(int num : insertArray) { dc.incCount(num); }
		
		int[] expectedArray = {1};
		int[] returnArray = iterateAndFillArray(expectedArray.length);
		assertArrayEquals(expectedArray, returnArray);
	}
	
	
	
	/** Test Integer hash function	*******************************************/
	@Test(timeout = TIMEOUT)
	public void test_hash_0() {
		assertEquals(0, hasher.hash(0));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_hash_neg_number() {
		assertEquals(78, hasher.hash(-1));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_hash_max() {
		assertEquals(0, hasher.hash(79));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_hash_past_max() {
		assertEquals(4, hasher.hash(83));
	}
	
	/** Test String hash function  ***********************************************/
	@Test(timeout = TIMEOUT)
	public void test_string_hash_0() {
		Hasher<String> stringHasher = new StringHasher();
		assertEquals(0, stringHasher.hash("0"));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_string_hash_max() {
		Hasher<String> stringHasher = new StringHasher();
		//0*0 + 9*1 + 9*2 + 9*3 + 0*4 + 5*5 = 79
		assertEquals(79, stringHasher.hash("099905"));
	}
	
	@Test(timeout = TIMEOUT)
	public void test_string_hash_past_max() {
		Hasher<String> stringHasher = new StringHasher();
		//0*0 + 9*1 + 9*2 + 9*3 + 0*4 + 5*7 = 89
		assertEquals(89, stringHasher.hash("099907"));
	}
}
