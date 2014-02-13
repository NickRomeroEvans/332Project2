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
			//Subtract 48 to get ASCII char value for 0 equal to 0
			int asciiVal = (int) s.charAt(i) - 48;
			//In case ASCII val is less than 48 (prevents a negative hash code)
			if (asciiVal < 0) asciiVal += 79;
			sum += asciiVal * i;
		}
		return sum % 79;
	}
}
