package phaseB;
import providedCode.Hasher;

/**
 * StringHasher is an implementation of the Hasher class that
 * calculates hash codes for Strings.
 * @author Austin
 */
public class StringHasher implements Hasher<String> {
	/**
	 * Calculates and returns a hash code for a given String
	 * @param s the String to get the hash code of
	 */
	@Override
	public int hash(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) { 
			sum += (int) s.charAt(i) * i; 
		}
		return sum % 79;
	}
}
