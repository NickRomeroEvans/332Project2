package phaseA;
import providedCode.*;


/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. It is exactly like the binary heap we studied, except nodes should have 4 children 
 * 	  instead of 2. Only leaves and at most one other node will have fewer children. 
 * 2. Use array-based implementation, beginning at index 0 (Root should be at index 0). 
 *    Construct the FourHeap by passing appropriate argument to superclass constructor.
 *    Hint: Complete written homework #2 before attempting this.   
 * 3. Throw appropriate exceptions in FourHeap whenever needed. For example, 
 * 	  when deleteMin on an empty heap, you could use UnderFlowException as is done in the Weiss text, 
 *    or you could use NoSuchElementException (in which case it will be fine if you want to import it). 
 * TODO: Develop appropriate JUnit tests for your FourHeap.
 */
public class FourHeap<E> extends Heap<E> {
	private Comparator<? super E> comparator;	//comparator
	private E[] heap;							//heap array
	private static int RESIZE_FACTOR = 2;		//
	private static int INTIAL_SIZE = 10;		//Intial size of the array
	private int lastIndex;
	
	public FourHeap(Comparator<? super E> c) {
		// TODO: To-be implemented. Replace dummy parameter to superclass constructor
		this.comparator = c;
		this.lastIndex = -1;
		this.heap = (E[]) new Object[INTIAL_SIZE];
	}

	@Override
	public void insert(E item) {
		// TODO Auto-generated method stub
		//Check to see if can be contained in array else double array size
		if(lastIndex == heap.length-1){
			int newLen = heap.length * RESIZE_FACTOR;
		
			// Copy elements over to bigger array
			E[] copyElements = (E[]) new Object[newLen];
			for (int i = 0; i < heap.length; i++) {
				copyElements[i] = heap[i];
			}
		}
		//Next percalateUp
		lastIndex++;
		int index = percolateUp(lastIndex, item);
		heap[index] = item;
		
	}

	@Override
	public E findMin() {
		// TODO Auto-generated method stub
		return heap[0];
	}

	@Override
	public E deleteMin() {
		// TODO Auto-generated method stub
		//Hold array[0]. then move item in nextIndex -1 then percalateDown NOTE: special case for only one element.
		if(isEmpty()){
			//throws something here
		}
		lastIndex--;
		E val = heap[0];
		int index = 0;
		if(lastIndex!=0){
			index= percolateDown(0, heap[lastIndex + 1]);
		}
		heap[index] = heap[lastIndex+1];
		return val;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return lastIndex ==-1;
	}
	
	
	private int percolateUp(int index, E item){
		while(index > 0 && comparator.compare(item, heap[index])<0){
			heap[index] = heap[(index-1)/4];
			index = (index-4)/4;
		}
		return index; 
		
	}
	
	private int percolateDown(int index, E item){
		while(4*index+1 <= size){
			//Note this is brute force and needs to be optimized
			int[] indexs = {4*index+1, 4*index+2,4*index+3,4*index+4};
			int mindex;
			//if(indexs[1])
		}
		return index;
	}

}
