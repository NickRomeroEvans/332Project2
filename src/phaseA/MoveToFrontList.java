package phaseA;
import providedCode.*;


/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. The list is typically not sorted.
 * 2. Add new items (with a count of 1) to the front of the list.
 * 3. Whenever an existing item has its count incremented by incCount or 
 *    retrieved by getCount, move it to the front of the list. That means 
 *    you remove the node from its current position and make it the first 
 *    node in the list.
 * 4. You need to implement an iterator. The iterator should not move elements
 *    to the front. The iterator should return elements in the order they are 
 *    stored in the list, starting with the first element in the list.
 * TODO: Develop appropriate JUnit tests for your MoveToFrontList.
 */
public class MoveToFrontList<E> extends DataCounter<E> {
	private int size; 							//Track size constant?
	private ListNode head; 						//Head node
	private Comparator<? super E> comparator;	//comparator
	
	/**
	 * Constructor
	 * 
	 * 
	 * */
	public MoveToFrontList(Comparator<? super E> c) {
		this.comparator = c;
		this.head = null;
		this.size = 0;
	}
	
	/**	Increment counter, looks through list searching for data if found increments the count of the node and moves to front
	 * If not found moves value to front of the list
	 * 
	 * 
	 * */
	@Override
	public void incCount(E data) {
		if(head == null){
			head = new ListNode(data);
			size++;
		}else {
			ListNode current = head;
			if(comparator.compare(data, current.value) ==0){
				current.count++;
				return;
			}
			while(current.next != null && comparator.compare(data, current.next.value) !=0){
				current = current.next;
			}
			if(current.next == null){
				ListNode temp = new ListNode(data);
				temp.next = head;
				this.head = temp; 
				size++;
			}else{
				ListNode temp = current.next;
				current.next = temp.next;
				temp.next = head;
				head = temp;
				head.count++;
			}
		}
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int getCount(E data) {
		// TODO Auto-generated method stub
		//Traverse data till find data return the count at that point
		// Else return zero
		if(head == null){
			return 0;
		}
		ListNode current = head;
		if(comparator.compare(data, current.value) ==0){
			return current.count;
		}
		while(current.next != null && comparator.compare(data, current.next.value) !=0){
			current = current.next;
		}
		if(current.next == null){
			return 0;
		}else{
			ListNode temp = current.next;
			current.next = temp.next;
			temp.next = head;
			head = temp;
			return head.count;
		}
	}
	
	
	@Override
	public SimpleIterator<DataCount<E>> getIterator() {
		return new SimpleIterator<DataCount<E>>(){
			ListNode itr = null;			
			{
				if(head != null){
					this.itr = head;
				}
			}
			
			public boolean hasNext(){
				return itr != null;
			}
			
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
	
	/* ListNode is the class used to construct the linked list for
	 * MoveToFrontList
	 * */
     public class ListNode{
    	public E value;
    	public int count;
    	public ListNode next;
    	
    	/*Constructs a basic node that contains the provided value 
    	 * and who's pointers are both to null
    	 * */
    	ListNode(E d){
    		this.count = 1;
    		this.next = null;
    		this.value = d;
    		size++;
    	}
    	
    }
}
