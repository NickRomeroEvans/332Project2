/* Austin Briggs and Nick Evans
 * 2/13/14
 * CSE 332 AB
 * Sam Wilson
 * Project 2B - Shake n Bacon
 */
package phaseB;

import phaseA.StringComparator;
import providedCode.Comparator;
import providedCode.DataCount;
/**
 * DataCountAlphabeticalComparator implements Comparator by comparing two Strings and
 * determining which comes first alphabetically
 *
 */
public class DataCountAlphabeticalComparator implements Comparator<DataCount<String>>{
	StringComparator alphabetical = new StringComparator();
	
	/** Compare two Strings and return an integer representing which comes first alphabetically.
	 * @param c1 The first String to compare
	 * @param c2 The second String to compare
	 * @throws IllegalArgumentException - if c1 or c2 is null
	 * @return -1 if c1 comes first alphabetically
	 * 			0 if c1 and c2 are identical 
	 * 		   +1 if c2 comes first alphabetically
	 */
	public int compare(DataCount<String> c1, DataCount<String> c2) {
		return alphabetical.compare(c1.data, c2.data);
	}
}
