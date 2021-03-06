/* Austin Briggs and Nick Evans
 * 2/13/14
 * CSE 332 AB
 * Sam Wilson
 * Project 2B - Shake n Bacon
 */
package phaseB;

import java.io.IOException;

import main.Sorter;
import main.WordCount;
import phaseA.AVLTree;
import phaseA.MoveToFrontList;
import phaseA.StringComparator;
import providedCode.BinarySearchTree;
import providedCode.DataCount;
import providedCode.DataCounter;
import providedCode.FileWordReader;

/**
 * Correlator uses a user-specified (through command line args) DataCounter to determine how
 * correlated 2 specified files are. It uses Euclidean distance calculations to do so.
 */
public class Correlator {
	
	
	public static void main(String[] args) {
    	if (args.length != 3) {
            System.err.println("Usage: [-DataCounter implementation] [filename1] [filename2]");
            System.exit(1);
        }

        //------- Choose the DataCounter implementation -------------------------------------- //
        DataCounter<String> counter  = null;
        DataCounter<String> counter2 = null;
        if 		(args[0].equals("-b")) { 
        	counter  = new BinarySearchTree<String>(new StringComparator()); 
        	counter2 = new BinarySearchTree<String>(new StringComparator());
        } else if (args[0].equals("-a")) { 
        	counter  = new AVLTree<String>(new StringComparator()); 
        	counter2 = new AVLTree<String>(new StringComparator());
        } else if (args[0].equals("-m")) { 
        	counter  = new MoveToFrontList<String>(new StringComparator()); 
        	counter2 = new MoveToFrontList<String>(new StringComparator());
        } else if (args[0].equals("-h")) { 
        	counter  = new HashTable<String>(new StringComparator(), new StringHasher());
        	counter2 = new HashTable<String>(new StringComparator(), new StringHasher());
        }
        else { 
        	System.err.println("Must use -b (BinarySearchTree), -a (AVLTree), -m (MoveToFrontList), " +
        					   "or -h (HashTable) for argument 1.");
        	System.exit(1);
        }
        
        
        //------- Get arrays of word counts from both files and sort them alphabetically ------------- //
        //Needed variables
        String file1 = args[1];
        String file2 = args[2];
        DataCountAlphabeticalComparator dcComparator = new DataCountAlphabeticalComparator();
        
        //File 1
        int file1TotalWordCounts = wordCount(file1, counter);
        DataCount<String>[] file1Counts = WordCount.getCountsArray(counter);
        Sorter.heapSort(file1Counts, dcComparator);
        
        
        //File 2
        int file2TotalWordCounts = wordCount(file2, counter2);
        DataCount<String>[] file2Counts = WordCount.getCountsArray(counter2);
        Sorter.heapSort(file2Counts, dcComparator);

        
        //------ Calculate the Euclidean differences ------------------------------------------------ //
        StringComparator strComparator= new StringComparator();
        double variance = 0.0;

        int arr1Index = 0;
        int arr2Index = 0;
        while (arr1Index < file1Counts.length && arr2Index < file2Counts.length) {
        	double freq1 = 1.0 * file1Counts[arr1Index].count / file1TotalWordCounts;
        	double freq2 = 1.0 * file2Counts[arr2Index].count / file2TotalWordCounts;
        	
        	//Are the current words useful?
        	boolean freq1Useful = freq1 <= 0.01 && freq1 >= 0.0001;
        	boolean freq2Useful = freq2 <= 0.01 && freq2 >= 0.0001;

        	//Compare the words
        	String word1 = file1Counts[arr1Index].data;
        	String word2 = file2Counts[arr2Index].data;
        	int compareResult = strComparator.compare(word1, word2);
        	
        	//If both words are useful and are the same word, use them in the Euclidean calculation
        	if (freq1Useful && freq2Useful && compareResult == 0) {
        		double difference = freq1 - freq2;
            	variance += Math.pow(difference, 2);
        		arr1Index++;
        		arr2Index++;
        	}
        	//If both words are useful but not the same, iterate 
        	//forward from the smaller (alphabetically) of the two
        	else if (freq1Useful && freq2Useful && compareResult != 0) {
        		if 		(compareResult == -1) arr1Index++;
        		else if (compareResult ==  1) arr2Index++;
        	}
        	//If one or both words are not useful, increment forward from the unuseful words
        	else if (!freq1Useful || !freq2Useful) {
        		if (!freq1Useful) arr1Index++;
        		if (!freq2Useful) arr2Index++;
        	}
        }

    	System.out.println(variance);  // IMPORTANT: Do not change printing format. Just print the double.
    }
    
	//Count the total amount of words in a file and individual word counts as well.
    private static int wordCount(String file, DataCounter<String> counter) {
	    int numWords = 0;
    	try {
	        FileWordReader reader = new FileWordReader(file);
	        String word = reader.nextWord();
	        while (word != null) {
	        	numWords++;
	            counter.incCount(word);
	            word = reader.nextWord();
	        }
	    }catch (IOException e) {
	        System.err.println("Error processing " + file + " " + e);
	        System.exit(1);
	    }
    	return numWords;
    }
}
