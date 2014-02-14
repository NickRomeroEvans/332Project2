package writeupExperiment;
import java.io.IOException;

import phaseA.*;
import phaseB.HashTable;
import phaseB.InverseDataComparator;
import phaseB.StringHasher;
import providedCode.*;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * WordCount counts the number of times each word in a file appears and prints those counts. 
 */
public class WordCount {

	
	// counter counts the amount of times each word appears in the given file
	public static void countWords(String file, DataCounter<String> counter) {
        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        }catch (IOException e) {
            System.err.println("Error processing " + file + " " + e);
            System.exit(1);
        }
    }
    
    
    // Gets the DataCounts of each word and puts them into an array which it returns
    public static <E> DataCount<E>[] getCountsArray(DataCounter<E> counter) {
 		DataCount<E>[] dataRay = (DataCount<E>[]) new DataCount[counter.getSize()];
 		SimpleIterator<DataCount<E>> si =  counter.getIterator();
 		int i = 0;
 		while(si.hasNext()){
 			dataRay[i] = (DataCount<E>) si.next();
 			i = i + 1;
 			
 		}
 		return dataRay;
 	}
    
 	
    // IMPORTANT: Used for grading. Do not modify the printing format!
 	// You may modify this method if you want.
    private static void printDataCount(DataCount<String>[] counts) {
    	for (DataCount<String> c : counts) {
            System.out.println(c.count + "\t" + c.data);
        }
    }
    
    private static void printDataCountk(DataCount<String>[] counts, int k){
    	if(k> counts.length){
    		k = counts.length;
    	}
    	for(int i =0; i<k;i++){
    		DataCount<String> c =counts[i];
    		System.out.println(c.count + "\t" + c.data);
    	}
    }
    
    /**
     * The main function. This uses the inputted arguments from the client to decide which DataCounter
     * implementation and which sorting routine to use on the specified file's words. It creates a tally
     * of counts for each word that appears in the specified file and then prints them to the console.
     * 
     * Argument descriptions:
     * arg1: DataCounter implementation. -b for BinarySearchTree (provided), -a for AVLTree (phase A), 
     * 		 -m for MoveToFrontList (phase A), or -h for HashTable (phase B).
     * arg2: Sorting routine. -is for Insertion sort (provided), -hs for Heap sort (phase A), 
     * 		 -os for Other sort (phase B), or -k followed by a number for Top-k sort (phase B).
     * arg3: Input file name.
     * @param args the list of arguments passed by the client
     */
    public static void main(String[] args) {
    	final long startTime = System.currentTimeMillis();
    	
    	if (args.length > 3 && !args[1].equals("-k") || args.length < 3) {
            System.err.println("Usage: [-DataCounter implementation] [-sorting routine] [filename of document to analyze]");
            System.exit(1);
        }

        // DataCounter implementation
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
        // Count the words and retrieve the array representation
        if(!args[1].equals("-k")){
        	countWords(args[2], counter); 
        }else{
        	countWords(args[3], counter);
        }
        DataCount<String>[] counts = getCountsArray(counter);
        
        // Choose the sorting routine and sort
        if (args.length == 3) {
	        if 		(args[1].equals("-is")) { Sorter.insertionSort(counts, new DataCountStringComparator()); }
	        else if (args[1].equals("-hs")) { Sorter.heapSort(counts, new DataCountStringComparator()); } 
	        else if (args[1].equals("-os")) { Sorter.otherSort(counts, new DataCountStringComparator()); }
	        else {
	        	System.err.println("Must use -is (Insertion sort), -hs (Heap sort), -os (Other sort)," +
	        					   " or -k <number> (Top-k sort) for argument 2.");
	        	System.exit(1);
	        }
        } else if (args.length == 4) { //implemented in phase B
        	if (args[1].matches("-k")) { Sorter.topKSort(counts, new InverseDataComparator(), java.lang.Integer.parseInt(args[2])); } //implemented in phase B
        	//else <fail> //implemented in phase B
        } else { 
        	System.err.println("Must use -is (Insertion sort), -hs (Heap sort), -os (Other sort)," +
        					   " or -k <number> (K-Sort) for argument 2.");
        	System.exit(1);
        }
        if(args[1].matches("-k")){
        	printDataCountk(counts,java.lang.Integer.parseInt(args[2]));
        }else{
        	printDataCount(counts);
        }
        
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) );
    }
}
