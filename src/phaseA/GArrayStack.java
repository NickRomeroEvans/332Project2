/** GArrayStack:
 * This class implements a stack using an array to hold its generic elements.
 * @author Austin Briggs
 * 1/16/14
 * CSE 332 AB (Sam Wilson)
 * Project 1B
 */
package phaseA;
import java.util.EmptyStackException;

import providedCode.GStack;

public class GArrayStack<T> implements GStack<T> {
    private static final int RESIZE_FACTOR = 2;	//when resizing, multiply/divide by this constant
	private static final int INITIAL_SIZE = 10;	//the initial size of the stack 
	private int top;							//the index of the stack's top element
    private T[] dElements;					//the elements of the stack (doubles)
	
	/** Constructor - initializes an empty stack of size 10.
     */
	public GArrayStack() {
		top = 0;
		dElements = (T[]) new Object[INITIAL_SIZE];
	}
	
	/** Checks to see if the stack is empty.
     * @return top == 0
     */
	@Override
    public boolean isEmpty() {
    	return top == 0;
    }

    /** Pushes a new element onto the stack. Resizes if appropriate.
     * @param d the new element to push onto the stack
     * @effects increments top
     * @effects adds an element to dElements
     */
	@Override
    public void push(T d) {
    	if (top == dElements.length - 1) resize();
    	dElements[top] = d;
    	top++;
    }

    /** Doubles the size of dElements if it's full.
     * @throws EmptyStackException if stack is empty
     */
    private void resize() {
    	if (isEmpty()) throw new EmptyStackException();
    	
		int newLen = dElements.length * RESIZE_FACTOR;
		
		// Copy elements over to bigger array
		T[] copyElements = (T[]) new Object[newLen];
		for (int i = 0; i < dElements.length; i++) {
			copyElements[i] = dElements[i];
		}    	
		dElements = copyElements;
	}

	/** Pop an element from the stack.
     * @return the deleted value
     * @throws EmptyStackException if stack is empty
     * @effects decrements top
     */
	@Override
    public T pop() {
    	if (isEmpty()) throw new EmptyStackException();
    	
    	top--;
    	return dElements[top];
    }

	/** Peek at the top element of the stack without deleting it.
     * @throws EmptyStackException if stack is empty
     */
	@Override
    public T peek() {
    	if (isEmpty()) throw new EmptyStackException();
    	
    	return dElements[top - 1];
    }
}
