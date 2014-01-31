package testA;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import providedCode.*;
import test.TestDataCounter;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * TestBinarySearchTree uses JUnit testing to test the implemenation of BinarySearchTree.
 * Tests common to TestBinarySearchTree subclasses are also found here. 
 */
/**
 * Things to note if you're new to unit testing:
 * 1. Test names tend to be long. This lets us get away with fewer comments. 
 * 2. assertEquals(x,y) is preferable to assertTrue(x == y).
 * 3. Tests tend to be short and usually should only have 1 assertion. 
 * 4. There may be several tests for 1 method (eg. test add x, test add y, ...)
 * 5. Eliminate redundancy with private helper methods. 
 * 6. Use timeouts to prevent being stuck in infinite loops
 */

public class TestBinarySearchTree extends TestDataCounter {	
	/** Creates BinarySearchTree before each test cases **/
	@Override
	public DataCounter<Integer> getDataCounter() {
		dcClass = "Tree";
		return new BinarySearchTree<Integer>(new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) { return e1 - e2; }
		});
	}	
	
	/** Test Iterator =======================================================================================**/
	
	@Test(timeout = TIMEOUT)
	public void test_iterator_get_all_data() {
		int[] startArray = {7, -5, -5, -6, 6, 10, -9, 4, 8, 6};
		
		// Expected array has no duplicates and is sorted
		for(int i = 0; i < startArray.length; i++) { dc.incCount(startArray[i]); }
		int[] expected = {-9, -6, -5, 4, 6, 7, 8, 10};
		
		// Actual array returned by the iterator
		int i = 0;
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		int[] actual = new int[expected.length];
		while(iter.hasNext()) { actual[i++] = iter.next().data; }
		
		// Sort and test
		Arrays.sort(actual);
		assertArrayEquals("Added " + Arrays.toString(startArray), expected, actual);
	}
	
	
	
	/** Private methods =======================================================================================**/

}
