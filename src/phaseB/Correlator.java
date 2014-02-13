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

        
        //------- Calculate frequencies of each word ------------------------------------------ //
        //Calculate total words for file 1
        int file1TotalWordCounts = 0;
        for (int i = 0; i < file1Counts.length; i++) {
        	file1TotalWordCounts += file1Counts[i].count;
        }
        //Calculate frequencies of each word in file 1
        WordFrequency[] file1WordFrequencies = new WordFrequency[file1Counts.length];
        for (int i = 0; i < file1WordFrequencies.length; i++) {
        	String word = file1Counts[i].data;
        	int wordCount = file1Counts[i].count;
        	file1WordFrequencies[i] = new WordFrequency(word, file1TotalWordCounts, wordCount);
        }
        
        //Calculate total words for file 2
        int file2TotalWordCounts = 0;
        for (int i = 0; i < file2Counts.length; i++) {
        	file2TotalWordCounts += file2Counts[i].count;
        }
        //Calculate frequencies of each word in file 2
        WordFrequency[] file2WordFrequencies = new WordFrequency[file1Counts.length];
        for (int i = 0; i < file2WordFrequencies.length; i++) {
        	String word = file1Counts[i].data;
        	int wordCount = file1Counts[i].count;
        	file2WordFrequencies[i] = new WordFrequency(word, file2TotalWordCounts, wordCount);
        }
        
    	double variance = 0.0;  // TODO: Compute this variance
    	
    	System.out.println(variance);  // IMPORTANT: Do not change printing format. Just print the double.
    }
}
