package writeupExperiment;
import phaseA.FourHeap;
import providedCode.Comparator;


/** 
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * Sorter is a class that implements Insertion sort, Heap sort, Other sort, and Top-K sort algorithms. 
 */
public class Sorter {
	
	/**
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be ordered according to the comparator.
     * This code uses insertion sort. The code is generic, but in this project
     * we use it with DataCount<String> and DataCountStringComparator.
     * @param array array to be sorted.
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
    
    /**
     * Sort a FourHeap.
     * @param array The FourHeap's array representation
     * @param comparator A comparator used to compare values in array
     */
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
    	if(k>=array.length){
    		k = array.length;
    	}
    	if(k<0){
    		throw new UnsupportedOperationException("-k <number> number must be equal or greater than zero.");
    	}
    		//k is less than or equal array.length
    	FourHeap<E> fh = new FourHeap<E>(comparator);
   		int i = 0;
   		while(i<k){
   			fh.insert(array[i++]);
   		}
   		if(k!=0){
   			while(i<array.length){
   				if(comparator.compare(fh.findMin(),array[i])<0){
   				fh.deleteMin();
    				fh.insert(array[i]);
   				}
   				i++;
   			}
   		}
   		int v = k-1;
   		while(!fh.isEmpty()){
   			array[v] = fh.deleteMin();
   			v--;
   		}
   	}
   
    
    public static <E> void otherSort(E[] array, Comparator<E> comparator) {
    	E[] temp = (E[]) new Object[array.length];
    	mergeSort(array,temp,0,array.length-1,comparator);
    }
    
    private static <E> void mergeSort(E[] array, E[] temp,int begin, int end, Comparator<E> comparator){
    	if(begin-end<0){
    		int middle = (begin+end)/2;
    		mergeSort(array, temp,begin,middle, comparator);
    		mergeSort(array, temp,middle+1,end, comparator);
    		merge(array,temp, begin,end,comparator);
    	}
    }
    
    /* Merge sort
     * 
     * Goes through makes call ensures both left and right side are sorted then
     * we use the temporary array to place the values and place them in the original 
     * index using the begin index as the starting point
     * 
     * */
    private static <E> void merge(E[] array, E[] temp,int begin, int end, Comparator<E> comparator){

        int i = begin;
        int tracker = begin;
        int middex = (begin+end)/2;
        int rightstart = middex+1;
        
        // Goes through and compares the two segments of the array that we are focusing on.
        while(i <= middex && rightstart <= end){
        	if(comparator.compare(array[i], array[rightstart])<=0){
        		temp[tracker++] = array[i++];
        	}else{
        		temp[tracker++] = array[rightstart++];
       		}
       	}
     
       	while(i <= middex){ //Means right completed first so shifts all into temp
       		temp[tracker++] = array[i++];
       	}
       	
       	//Number of elements in temp array that need to be put into array.
       	int elements = tracker -begin;
       	for(int j =0; j < elements; j++){
       		array[begin+j] = temp[begin+j];
       	}
    }

    
}
