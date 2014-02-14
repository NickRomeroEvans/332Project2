/* Austin Briggs and Nick Evans
 * 2/13/14
 * CSE 332 AB
 * Sam Wilson
 * Project 2A - Shake n Bacon
 */
package phaseA;
import providedCode.*;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * FourHeap is a class that implements the FourHeap data structure, with no deletion capabilities.
 */

/**
 * 1. It is exactly like the binary heap we studied, except nodes should have 4 children 
 * 	  instead of 2. Only leaves and at most one other node will have fewer children. 
 * 2. Use array-based implementation, beginning at index 0 (Root should be at index 0). 
 *    Hint: Complete written homework #2 before attempting this.   
 * 3. Throw appropriate exceptions in FourHeap whenever needed. For example, 
 * 	  when deleteMin on an empty heap, you could use UnderFlowException as is done in the Weiss text, 
 *    or you could use NoSuchElementException (in which case it will be fine if you want to import it). 
 */
public class FourHeap<E> extends Heap<E> {
	private Comparator<? super E> comparator;	//comparator
	private E[] heap;							//heap array
	private static int RESIZE_FACTOR = 2;		//used when the array needs to be resized
	private static int INTIAL_SIZE = 10;		//initial size of the array
	private int lastIndex;						//heap size - 1
	
	/** Constructor - initializes the comparator, the index of the last element, 
	 * and the heap array.
	 * @param c The passed comparator object
	 */
	public FourHeap(Comparator<? super E> c) {
		this.comparator = c;
		this.lastIndex = -1;
		this.heap = (E[]) new Object[INTIAL_SIZE];
	}

	/** Insert an item into the heap and sort the heap.
	 * @param item The item to add to the heap
	 */
	@Override
	public void insert(E item) {
		//Check to see if can be contained in array else double array size
		if(lastIndex == heap.length-1){
			int newLen = heap.length * RESIZE_FACTOR;
		
			// Copy elements over to bigger array
			E[] copyElements = (E[]) new Object[newLen];
			for (int i = 0; i < heap.length; i++) {
				copyElements[i] = heap[i];
			}
			heap = copyElements;
		}
		//Next percolateUp
		lastIndex++;
		
		int index = percolateUp(lastIndex, item);
		heap[index] = item;
		
	}
	public int lastdex(){
		return lastIndex;
	}
	/** Return the minimum value of the heap (the root).
	 * @throws UnsupportedOperationException if the heap is empty
	 * @return The root of the heap
	 */
	@Override
	public E findMin() {
		if (isEmpty()) { throw new UnsupportedOperationException("Cannot find the minimum value of an empty heap."); }
		return heap[0];
	}
	
	/** Delete the minimum value in the heap (the root).
	 * @throws UnsupportedOperationException if the heap is empty
	 * @return The deleted value
	 */
	@Override
	public E deleteMin() {
		//Hold array[0]. then move item in nextIndex -1 then percalateDown NOTE: special case for only one element.
		if(isEmpty()){
			throw new UnsupportedOperationException("Cannot delete the minimum value of an empty heap.");
		}
		lastIndex--;
		E val = heap[0];
		int	index= percolateDown(0, heap[lastIndex + 1]);
		heap[index] = heap[lastIndex+1];
		return val;
	}

	/** Returns whether or not the heap is empty.
	 * @return Whether or not there are no elements in the heap
	 */
	@Override
	public boolean isEmpty() {
		return lastIndex ==-1;
	}
	
	/* Find and return the index of the hole for which a specified item 
	 * will be placed in the heap, going upwards. This is done to maintain heap structure.
	 * 
	 * Parameters:
	 * index is the index of the item to percolate upwards in the heap
	 * item is the item to percolate upwards
	 */
	private int percolateUp(int index, E item){
		while(index > 0 && comparator.compare(item, heap[(index-1)/4])<0){
			heap[index] = heap[(index-1)/4];
			index = (index-1)/4;
		}
		return index; 
	}
	
	/* Find and return the index for the hole for which a specified item 
	 * will be placed in the heap, going downwards. This is done to maintain heap structure.
	 * 
	 * Parameters:
	 * index is the index of the item to percolate downwards in the heap
	 * item is the item to percolate downwards
	 */
	private int percolateDown(int index, E item){
		while(((4*index)+1) <= lastIndex){
			//Note this is brute force and needs to be optimized
			int mindex = 4*index+1;
			
			int track = (lastIndex - ((4*index)+4));
			if(track <0){
				//Know track is either -1 -2 -3
				track = track + 3;
			}else{
				track = 3;
			}		
			//Loop through finding the index of the smallest value
			for(int i =1; i<=track; i++){
				if(comparator.compare(heap[mindex],heap[index*4+1+i])>0){
					mindex = index*4+1+i;
					
				}
			}

			if(comparator.compare(heap[mindex], item)<0){
				heap[index] = heap[mindex];
				index = mindex;
				
			}else{
				break;
			}
		}
		return index;
	}

}
