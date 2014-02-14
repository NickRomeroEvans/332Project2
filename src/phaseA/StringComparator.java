/* Austin Briggs and Nick Evans
 * 2/13/14
 * CSE 332 AB
 * Sam Wilson
 * Project 2A - Shake n Bacon
 */
package phaseA;
import providedCode.*;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * StringComparator compares two Strings, specified below:  
 */
/**
 * This comparator is used by the provided code for both data-counters and 
 * sorting. Because of how the output must be sorted in the case of ties, 
 * your implementation should return a negative number when the first argument
 * to compare comes first alphabetically. Do not use any String comparison 
 * provided in Java's standard library; the only String methods you should 
 * use are length and charAt.
 */
public class StringComparator implements Comparator<String>{

	/** Compare two Strings and return an integer representing which comes
	 *  first alphabetically.
	 *
	 * @param s1 The first String to compare
	 * @param s2 The second String to compare
	 * @throws IllegalArgumentException if s1 or s2 is null
	 * @return -1 if s1 comes first alphabetically
	 *		    0 if s1 and s2 are identical
	 *		   +1 if s2 comes first alphabetically
	 */
	@Override
	public int compare(String s1, String s2) {
		int i; 				//loop variable
		int numCompLetters; //how high will i increment?
		
		// Check for null strings
		if (s1 == null || s2 == null) throw new IllegalArgumentException("Cannot compare null Strings.");
		
		if (s1.length() > s2.length()) numCompLetters = s2.length();
		else 					   numCompLetters = s1.length();
		
		// If any letter does not match return +/-1
		for (i = 0; i < numCompLetters; i++) {
			if 		(s1.charAt(i) < s2.charAt(i)) return -1;
			else if (s1.charAt(i) > s2.charAt(i)) return 1;
		}
		// Thus far s1 and s2 are matching. Return +/-1 depending upon
		// length of strings (the shorter word comes first alphabetically)
		if 		(numCompLetters == s1.length() && numCompLetters < s2.length()) return -1;
		else if (numCompLetters == s2.length() && numCompLetters < s1.length()) return 1;
		
		// The strings match so we return 0
		return 0;
	}
}
