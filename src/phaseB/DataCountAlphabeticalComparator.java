package phaseB;

import phaseA.StringComparator;
import providedCode.Comparator;
import providedCode.DataCount;

public class DataCountAlphabeticalComparator implements Comparator<DataCount<String>>{
	StringComparator alphabetical = new StringComparator();
	
	public int compare(DataCount<String> c1, DataCount<String> c2) {
		return alphabetical.compare(c1.data, c2.data);
	}
}
