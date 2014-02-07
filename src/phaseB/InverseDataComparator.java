package phaseB;

import providedCode.DataCount;
import providedCode.DataCountStringComparator;

public class InverseDataComparator extends  DataCountStringComparator{
	public int compare(DataCount<String> c1, DataCount<String> c2) {
		return -super.compare(c1, c2);
	}
}
