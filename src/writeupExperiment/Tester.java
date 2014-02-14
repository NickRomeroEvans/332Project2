package writeupExperiment;

import phaseA.FourHeap;
import phaseA.StringComparator;
import providedCode.Heap;

public class Tester {
	private static final int NUM_TEST = 50;
	private static final int NUM_WARMUP = 5;
	
	private static String[] sorts = {"-is", "-os", "-hs"};
	private static String[] dcs = {"-b", "-a", "-m", "-h"};

	
	public static void main(String[] args) {
		//test9();
		//test9d();
		test10();
	}
	
	private static void test9d() {
		double[] runtimes = new double[3];
		int i = 0;
		for (String sort : sorts) {
			String[] arr = {"-h", sort, "hamlet.txt"};
			runtimes[i] = getAverageRuntime(arr);
			i++;
		}
		for (double runtime : runtimes) {
			System.out.println(runtime);
		}
	}

	private static void test10() {
		//Run top-K and heap sort
		int[] ns = {1, 4, 16, 64, 256, 1024, 4096, 16384};
		double[] runtimes1 = new double[9];
		double[] runtimes2 = new double[9];
		
		int i = 0;
		for (int n : ns) {
			String[] arr1 = {"-b", "-k", ""+n, "dictionary.txt"};
			runtimes1[i] = getAverageRuntime(arr1);
			String[] arr2 = {"-b", "-hs", ""+n, "dictionary.txt"};
			runtimes2[i] = getAverageRuntime(arr2);
			i++;
		}
		for (i = 0; i < ns.length; i++) {
			System.out.println("Top-K runtime for     k = " + ns[i] + ":\t" + runtimes1[i]);
			System.out.println("Heap sort runtime for k = " + ns[i] + ":\t" + runtimes2[i]);
			System.out.println();
		}
		
	}

	private static double getAverageRuntime(String[] args) {
		    double totalTime = 0;
		    for(int i=0; i<NUM_TEST; i++) {
		      long startTime = System.currentTimeMillis();
		      WordCount.main(args);
		      long endTime = System.currentTimeMillis();
		      if(NUM_WARMUP <= i) {                    // Throw away first NUM_WARMUP runs to encounter JVM warmup
	    totalTime += (endTime - startTime);
	  }
	}
	return totalTime / (NUM_TEST-NUM_WARMUP);  // Return average runtime.
	  }

	private static void test9() {
		
		double[] runtimes = new double[4];
		int i = 0;
		for (String sort : sorts) {
			//for (String dc : dcs) {
				String[] arr = {"-h", sort, "hamlet.txt"};
				runtimes[i] = getAverageRuntime(arr);
				i++;
				System.out.println("-h" + " " + sort + ": " + getAverageRuntime(arr));
			//}
		}
		
		for (i = 0; i < runtimes.length; i++) {
			// -is
			/*
			if (i < 4) {
				// -b
				if (i % 4 == 0) System.out.println(dcs[0] + sorts[0] + ": " + runtimes[i]);
				else if (i % 4 == 1) System.out.println(dcs[1] + sorts[0] + ": " + runtimes[i]);
				else if (i % 4 == 2) System.out.println(dcs[2] + sorts[0] + ": " + runtimes[i]);
				else System.out.println(dcs[3] + sorts[0] + ": " + runtimes[i]);
			}
			// -os
			else if (i < 8) {
				if (i % 4 == 0) System.out.println(dcs[0] + sorts[1] + ": " + runtimes[i]);
				else if (i % 4 == 1) System.out.println(dcs[1] + sorts[1] + ": " + runtimes[i]);
				else if (i % 4 == 2) System.out.println(dcs[2] + sorts[1] + ": " + runtimes[i]);
				else System.out.println(dcs[3] + sorts[1] + ": " + runtimes[i]);
			}
			// -hs
			else {
				if (i % 4 == 0) System.out.println(dcs[0] + sorts[2] + ": " + runtimes[i]);
				else if (i % 4 == 1) System.out.println(dcs[1] + sorts[2] + ": " + runtimes[i]);
				else if (i % 4 == 2) System.out.println(dcs[2] + sorts[2] + ": " + runtimes[i]);
				else System.out.println(dcs[3] + sorts[2] + ": " + runtimes[i]);
			}
			System.out.println(dcs[0] + sorts[i] + ": " + runtimes[i]);*/
			System.out.println("-h " + sorts[i] + ": " + runtimes[i]);
		}
	
	}
}