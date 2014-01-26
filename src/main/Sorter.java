package main;
import phaseA.FourHeap;
import providedCode.Comparator;


/** 
 *  TODO: Replace this comment with your own as appropriate.
 *  Implement the sorting methods below. Do not change the provided method signature,
 *  but you may add as many other methods as you want.
 */
public class Sorter {
	
	/**
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be ordered according to the comparator.
     * This code uses insertion sort. The code is generic, but in this project
     * we use it with DataCount<String> and DataCountStringComparator.
     * @param counts array to be sorted.
	 * @param comparator for comparing elements.
     */
    public static <E> void insertionSort(E[] array, Comparator<E> comparator) {
        for (int i = 1; i < array.length; i++) {
            E x = array[i];
            int j;
            for (j=i-1; j>=0; j--) {
                if (comparator.compare(x,array[j]) >= 0) { break; }
                array[j + 1] = array[j];
            }
            array[j + 1] = x;
        }
    }
    
    public static <E> void heapSort(E[] array, Comparator<E> comparator) {
    	/*heapSort: Consists of two steps*/
    	
    	/* 1) Insert each element to be sorted into a heap (FourHeap)*/
    	int i = 0;
    	FourHeap<E> fh = new FourHeap<E>(comparator); 
    	while(i < array.length){
    		fh.insert(array[i]);
    		i++;
    	}

    	/*2) Remove each element from the heap, storing them in order in the output array.*/
    	i =0;
    	while(!fh.isEmpty()){
    		array[i] = fh.deleteMin();
    		i++;
    	}
    }
    
    public static <E> void topKSort(E[] array, Comparator<E> comparator, int k) {
    	// TODO: To-be implemented (the order of elements at index >= k does not matter)
    }
    
    public static <E> void otherSort(E[] array, Comparator<E> comparator) {
    	// TODO: To-be implemented (either mergeSort or QuickSort)
    }

}
