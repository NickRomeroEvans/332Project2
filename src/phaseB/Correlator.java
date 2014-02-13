package phaseB;

import main.Sorter;
import main.WordCount;
import phaseA.AVLTree;
import phaseA.MoveToFrontList;
import phaseA.StringComparator;
import providedCode.BinarySearchTree;
import providedCode.DataCount;
import providedCode.DataCountStringComparator;
import providedCode.DataCounter;


public class Correlator {
	
	
    public static void main(String[] args) {
    	if (args.length != 3) {
            System.err.println("Usage: [-DataCounter implementation] [filename1] [filename2]");
            System.exit(1);
        }

        //------- Choose the DataCounter implementation -------------------------------------- //
        DataCounter<String> counter = null;
        if 		(args[0].equals("-b")) { counter = new BinarySearchTree<String>(new StringComparator()); }
        else if (args[0].equals("-a")) { counter = new AVLTree<String>(new StringComparator()); } 
        else if (args[0].equals("-m")) { counter = new MoveToFrontList<String>(new StringComparator()); }
        else if (args[0].equals("-h")) { counter = new HashTable<String>(new StringComparator(), new StringHasher()); } //implemented in phase B
        else { 
        	System.err.println("Must use -b (BinarySearchTree), -a (AVLTree), -m (MoveToFrontList), " +
        					   "or -h (HashTable) for argument 1.");
        	System.exit(1);
        }
        
        //------- Get arrays of word counts from both files and sort them --------------------- //
        //Needed variables
        String file1 = args[1];
        String file2 = args[2];
        DataCountStringComparator dcStringComparator = new DataCountStringComparator();
        
        //File 1
        WordCount.countWords(file1, counter);
        DataCount<String>[] file1Counts = WordCount.getCountsArray(counter);
        Sorter.insertionSort(file1Counts, dcStringComparator);
        
        //File 2
        WordCount.countWords(file2, counter);
        DataCount<String>[] file2Counts = WordCount.getCountsArray(counter);
        Sorter.insertionSort(file2Counts, dcStringComparator);

        
    	double variance = 0.0;  // TODO: Compute this variance
    	
    	System.out.println(variance);  // IMPORTANT: Do not change printing format. Just print the double.
    }
}
