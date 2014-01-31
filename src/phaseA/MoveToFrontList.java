package phaseA;
import providedCode.*;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * MoveToFrontList is an implementation of DataCounter, specified in bullets 1-4 below:
 */
/**
 * 1. The list is typically not sorted.
 * 2. Add new items (with a count of 1) to the front of the list.
 * 3. Whenever an existing item has its count incremented by incCount or 
 *    retrieved by getCount, move it to the front of the list. That means 
 *    you remove the node from its current position and make it the first 
 *    node in the list.
 * 4. You need to implement an iterator. The iterator should not move elements
 *    to the front. The iterator should return elements in the order they are 
 *    stored in the list, starting with the first element in the list.
 */
public class MoveToFrontList<E> extends DataCounter<E> {
	private int size; 							//Track size of list
	private ListNode head; 						//Head node
	private Comparator<? super E> comparator;	//comparator
	
	
	/** Constructor - initialize an empty list and set the comparator. */
	public MoveToFrontList(Comparator<? super E> c) {
		this.comparator = c;
		this.head = null;
		this.size = 0;
	}
	
	/** {@inheritDoc} */
	@Override
	public void incCount(E data) {
		if(head == null){
			// We have an empty list - create the first node with data as its value
			head = new ListNode(data);
		}else {
			// We have a non-empty list - let's try to find a matching node
			ListNode current = head;
			// Is the head a match? If so, increment and return
			if(comparator.compare(data, current.value) ==0){
				current.count++;
				return;
			}
			// Look for a node that matches data in the list 
			while(current.next != null && comparator.compare(data, current.next.value) !=0){
				current = current.next;
			}
			// There was no matching node - create a new one
			if(current.next == null){
				ListNode temp = new ListNode(data);
				temp.next = head;
				this.head = temp;
				
			// Found a matching node - move it to the front and increment its count
			}else{
				ListNode temp = current.next;
				current.next = temp.next;
				temp.next = head;
				head = temp;
				head.count++;
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public int getSize() {
		return size;
	}

	/** {@inheritDoc} */
	@Override
	public int getCount(E data) {
		// We traverse the list until the node with data as its value is found, then return its count
		// If no such node is found we return 0
		if(head == null){
			// Empty list has no node with data as its value
			return 0;
		}
		// Does head match? If so, return its count
		ListNode current = head;
		if(comparator.compare(data, current.value) ==0){
			return current.count;
		}
		
		// Search the list for a match
		while(current.next != null && comparator.compare(data, current.next.value) !=0){
			current = current.next;
		}
		// No match was found
		if(current.next == null){
			return 0;
			
		// Found a match. 
		// TODO: is the node supposed to be moved to the front of the list here?
		}else{
			ListNode temp = current.next;
			current.next = temp.next;
			temp.next = head;
			head = temp;
			return head.count;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public SimpleIterator<DataCount<E>> getIterator() {
		return new SimpleIterator<DataCount<E>>(){
			ListNode itr = null;			
			{
				if(head != null){
					this.itr = head;
				}
			}
			
			/** Does the iterator have a next element? 
			 * @return Whether the iterator has any more elements or not
			 */
			public boolean hasNext(){
				return itr != null;
			}
			
			/** Returns the next element the iterator points at and increments the iterator.
			 * @return The element the iterator is pointing at
			 * @throws NoSuchElementException if the iterator is not pointing at a valid element
			 */
			public DataCount<E> next(){
				if(!hasNext()) {
        			throw new java.util.NoSuchElementException();
        		}
				DataCount<E>data = new DataCount<E>(itr.value, itr.count);
				itr = itr.next;
				return data;
			}
		};
	}
	
	/** ListNode is the class used to construct the linked list for MoveToFrontList. */
     public class ListNode{
    	public E value;			//The value stored in a ListNode
    	public int count;		//The count associated with value
    	public ListNode next;	//A pointer to the next ListNode in the list
    	
    	/** Constructs a basic node that contains the provided value 
    	 * and who's pointers are both to null. */
    	ListNode(E d){
    		this.count = 1;
    		this.next = null;
    		this.value = d;
    		size++;
    	}
    	
    }
}
