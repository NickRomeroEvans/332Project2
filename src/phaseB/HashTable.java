package phaseB;
import providedCode.*;


/**
 * 1. You may implement any kind of HashTable discussed in class; the only 
 *    restriction is that it should not restrict the size of the input domain 
 *    (i.e., it must accept any key) or the number of inputs (i.e., it must 
 *    grow as necessary).
 * 2. You should use this implementation with the -h option.
 * 3. To use your HashTable for WordCount, you will need to be able to hash 
 *    strings. Implement your own hashing strategy using charAt and length. 
 *    Do not use Java's hashCode method.
 */
public class HashTable<E> extends DataCounter<E> {
	private Comparator<? super E> comparator;	//this HashTable's comparator
	private Hasher<E> hasherH;					//this HashTable's primary hash function
	private Hasher<E> hasherG;					//this HashTable's secondary hash function
	private DataCount<E>[] table;				//holds the elements of the HashTable
	private E[] keyArr;							//holds a list of the keys in this HashTable
	private int numItems;						//number of elements in table
	private double loadFactor;					//avg elements/bucket
	
	private final int[] primes = {11, 47, 197, 797, 3203,	//array of prime numbers, used
								  13001, 53003, 213019};	//for sizing of the table
	
	/** Constructor - initializes the HashTable array, comparator,
	 * and hash functions
	 * @param c the comparator for this HashTable
	 * @param h the primary hash function for this HashTable
	 */
	public HashTable(Comparator<? super E> c, Hasher<E> h) {
		comparator = c;
		hasherH = h;
		hasherG = new Hasher<E>() {
			public int hash(E s) {
				int sum = 0;
				for (int i = 0; i < s.toString().length(); i++) { 
					sum += (int) s.toString().charAt(i) * i; 
				}
				return 17 - (sum % 17);
			}
		};
		
		table = (DataCount<E>[]) new DataCount[primes[0]]; 
		keyArr = (E[]) new Object[primes[0]];
		loadFactor = 0.0;
		numItems = 0;
	}
	
	/** {@inheritDoc} */
	@Override
	public void incCount(E data) {
		//First check to see if data is already present in the HashTable
		//Get hashcode
		int hashCode = hasherH.hash(data) % table.length;
		//If data is already present, find it in the table and increment its count
			//It may not be in the first place we look - if so, use the secondary hash function to find it
			while (table[hashCode] != null && comparator.compare(table[hashCode].data, data) != 0) {
				hashCode = (hashCode + hasherG.hash(data)) % table.length;
			}
			
			
			if(table[hashCode] == null){								//Add new word
				DataCount<E> dc = new DataCount<E>(data, 1);
				table[hashCode] = dc;
				keyArr[numItems] = data;
				numItems++;
				loadFactor = 1.0 * numItems / table.length;
				if (loadFactor >= 0.5) resizeTable();
			}else if(comparator.compare(table[hashCode].data, data) == 0){//Found it - increment the count
				table[hashCode].count++;
			}
		//If data is not already present, find a blank spot in the table and insert a new DataCount for it
		//else {

			//Make a new DataCount to put in the table

			
			//Find an empty spot and insert the new DataCount element

			
			//Update keyArr, numItems, and loadFactor, and then check if a table resize is needed

		//}
	}
	
	// Resizes the table and key array to a new length, copying elements over to the new table 
	// and key array. The length will be prime.
	private void resizeTable() {
		//Get the new length from our primes array
		int newLen;
		int i = 0;
		while (primes[i] <= table.length) i++;
		newLen = primes[i];
		//Hold Old key and table array
		int oldItems = numItems;
		DataCount<E>[] oldTable = table;
		E[] oldKeyArr = keyArr;
		//Create the new table and key array and copy over elements
		table = (DataCount<E>[]) new DataCount[newLen];
		keyArr = (E[]) new Object[newLen];
		numItems = 0;
		for (i = 0; i < oldItems; i++) {
			E nextData = oldKeyArr[i];
			int hashFind = hasherH.hash(nextData) % oldTable.length;
			while(comparator.compare(oldTable[hashFind].data, nextData) != 0){
				hashFind = (hashFind + hasherG.hash(nextData)) % oldTable.length;
			}
			
			E data =oldTable[hashFind].data;
			incCount(data);
			int hashCode = hasherH.hash(data)% table.length;
			while(comparator.compare(table[hashCode].data, data) != 0){
				hashCode = (hashCode + hasherG.hash(data)) % table.length;
			}
			table[hashCode].count = oldTable[hashFind].count;
			
		}
	}

	/** {@inheritDoc} */
	@Override
	public int getSize() {
		return numItems;
	}

	/** {@inheritDoc} */
	@Override
	public int getCount(E data) {
		//Check to see if data is in the table
		int hashCode = hasherH.hash(data) % table.length;
		while (table[hashCode] != null && comparator.compare(table[hashCode].data, data) != 0) {
			hashCode = (hashCode + hasherG.hash(data)) % table.length;
		}
		if(table[hashCode] == null){
			return 0;
		}else{
			return table[hashCode].count;
		}
	}

	/** {@inheritDoc} */
	@Override
	public SimpleIterator<DataCount<E>> getIterator() {
		return new SimpleIterator<DataCount<E>>(){
			int itrIndex = -1;
			
			/** Does the iterator have a next element? 
			 * @return Whether the iterator has any more elements or not
			 */
			public boolean hasNext(){
				return keyArr[itrIndex + 1] != null;
			}
			
			/** Returns the next element the iterator points at and increments the iterator.
			 * @return The element the iterator is pointing at
			 * @throws NoSuchElementException if the iterator has no more valid elements
			 */
			public DataCount<E> next(){
				if(!hasNext()) {
        			throw new java.util.NoSuchElementException();
        		}
				//The iterator does have another valid element - increment to it
				itrIndex++;
				
				//Get the hashCode for the next element and find it in the HashTable
				int hashCode = hasherH.hash(keyArr[itrIndex]) % table.length;
				E data = keyArr[itrIndex];
				while (table[hashCode] == null || comparator.compare(table[hashCode].data, data) != 0) {
					hashCode = (hashCode + hasherG.hash(data)) % table.length;
				}
				
				//Create and return a copy of the element
				DataCount<E> next = new DataCount<E>(data, table[hashCode].count);
				return next;
			}
		};
	}

}
