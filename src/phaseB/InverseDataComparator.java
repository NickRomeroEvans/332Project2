package phaseB;

import providedCode.DataCount;
import providedCode.DataCountStringComparator;

/**
 * InverseDataComparator returns the opposite result of DataCountStringComparator.
 * This is used to be able to implement a maxHeap for our Top-K Sort.
 */
public class InverseDataComparator extends  DataCountStringComparator{
	/**
	 * Returns the opposite result of comparing Strings c1 and c2 in
	 * DataCountStringComparator.
	 */
	public int compare(DataCount<String> c1, DataCount<String> c2) {
		return -super.compare(c1, c2);
	}
}
