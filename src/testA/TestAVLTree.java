package testA;
import static org.junit.Assert.*;
import org.junit.Test;

import phaseA.AVLTree;
import providedCode.BinarySearchTree;
import providedCode.Comparator;
import providedCode.DataCounter;


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
	public void test(){
		
	}
}
