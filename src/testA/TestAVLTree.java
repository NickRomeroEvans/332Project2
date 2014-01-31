package testA;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import phaseA.AVLTree;
import providedCode.BinarySearchTree;
import providedCode.Comparator;
import providedCode.DataCount;
import providedCode.DataCounter;
import providedCode.SimpleIterator;


public class TestAVLTree extends TestBinarySearchTree {
	/** Creates AVLTree before each test cases **/
	@Override
	public DataCounter<Integer> getDataCounter() {
		dcClass = "Tree";
		return new AVLTree<Integer>(new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) { return e1 - e2; }
		});
	}
	
	@Test(timeout = TIMEOUT)
	public void test_incCount_LeftLeft(){
		int[] startArray = {7,8,4,5,3,2};
		for(int i = 0; i < startArray.length; i++) { dc.incCount(startArray[i]); }
		int[] expected = {2,3,4,5,7,8};
		
		int i = 0;
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		int[] actual = new int[expected.length];
		while(iter.hasNext()) { actual[i++] = iter.next().data;
		}
		
		// Sort and test
		assertTrue(((AVLTree)dc).checkRep());
		Arrays.sort(actual);
		assertArrayEquals("Added " + Arrays.toString(startArray), expected, actual);
		
	}
	@Test(timeout = TIMEOUT)
	public void test_incCount_LeftRight(){
		int[] startArray = {6,2,3};
		for(int i = 0; i < startArray.length; i++) { dc.incCount(startArray[i]); }
		int[] expected = {2,3,6};
		
		int i = 0;
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		int[] actual = new int[expected.length];
		while(iter.hasNext()) {actual[i++] = iter.next().data; }
		
		// Sort and test
		assertTrue(((AVLTree)dc).checkRep());
		Arrays.sort(actual);
		assertArrayEquals("Added " + Arrays.toString(startArray), expected, actual);
		
	}
	@Test(timeout = TIMEOUT)
	public void test_incCount_RightLeft(){
		int[] startArray = {8,11,9};
		for(int i = 0; i < startArray.length; i++) { dc.incCount(startArray[i]); }
		int[] expected = {8,9,11};
		
		int i = 0;
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		int[] actual = new int[expected.length];
		while(iter.hasNext()) { actual[i++] = iter.next().data; }
		
		// Sort and test
		Arrays.sort(actual);
		assertTrue(((AVLTree)dc).checkRep());
		assertArrayEquals("Added " + Arrays.toString(startArray), expected, actual);
		
	}
	@Test(timeout = TIMEOUT)
	public void test_incCount_RightRight(){
		int[] startArray = {8,9,11};
		for(int i = 0; i < startArray.length; i++) { dc.incCount(startArray[i]); }
		int[] expected = {8,9,11};
		
		int i = 0;
		SimpleIterator<DataCount<Integer>> iter = dc.getIterator();
		int[] actual = new int[expected.length];
		while(iter.hasNext()) { actual[i++] = iter.next().data; }
		
		// Sort and test
		Arrays.sort(actual);
		assertTrue(((AVLTree)dc).checkRep());
		assertArrayEquals("Added " + Arrays.toString(startArray), expected, actual);
		
	}
	
}
