package phaseA;
import providedCode.*;

/**
 * Austin Briggs and Nick Evans
 * CSE 332 AB
 * Project 2A
 * 
 * AVLTree is a class that implements a generic AVLTree data structure, with no
 * deletion capabilities.
 */
/**
 * AVLTree must be subclass of BinarySearchTree<E> and must use inheritance 
 * and calls to superclass methods to avoid unnecessary duplication or copying
 * of functionality.
 * 1. Create a subclass of BSTNode, perhaps named AVLNode.
 * 2. Override incCount method such that it creates AVLNode instances instead 
 *    of BSTNode instances.
 * 3. Do not "replace" the left and right fields in BSTNode with new left and 
 *    right fields in AVLNode. This will instead mask the super-class fields 
 *    (i.e., the resulting node would actually have four node fields, with 
 *    code accessing one pair or the other depending on the type of the
 *    references used to access the instance). Such masking will lead to
 *    highly perplexing and erroneous behavior. Instead, continue using the
 *    existing BSTNode left and right fields. Cast their values to AVLNode 
 *    whenever necessary in your AVLTree. Note: This may require many casts, 
 *    but that is o.k. given that our goal is to reuse methods from BinarySearchTree.
 * 4. Do not override dump method of BinarySearchTree & toString method of 
 * 	  DataCounter. They are used for grading. 
 */
public class AVLTree<E> extends BinarySearchTree<E> {
	public AVLTree(Comparator<? super E> c) {
		super(c);
	}


	/** {@inheritDoc} */
	@Override
	public void incCount(E data) {
		overallRoot = helperInsert((AVLNode)overallRoot,data); 
	}

	//Method that scans through tree inserts node and makes adjustments according to the
	// definition of an AVL Tree.
	private AVLNode helperInsert(AVLNode n, E data){
		if(n == null){
			n = new AVLNode(data);
		}else{
			// compare the new data with the data at the current node
			int cmp = comparator.compare(data, n.data);
			if(cmp == 0) {            // a. Current node is a match
				n.count++;
			}else if(cmp < 0){
				n.left = helperInsert((AVLNode)n.left,data);
				if(heightFactor(n) >= 2||heightFactor(n) <= -2){
					if(comparator.compare(data, n.left.data)< 0){
						n = rotateLeftLeft(n);
					}else{
						n = rotateLeftRight(n);
					}
				}
			}else{
				n.right = helperInsert((AVLNode)n.right, data);
				if(heightFactor(n) >= 2||heightFactor(n) <= -2){
					if(comparator.compare(data, n.right.data)<0){
						n = rotateRightLeft(n);
					}else{
						n = rotateRightRight(n);
					}
				}
			}

		}
		n.height = determineHeight(n);
		return n;
	}

	/**
	 * Checks the representation invariant.
	 * @return whether or not the AVLTree representation has been preserved
	 */
	public boolean checkRep(){
		return verifyAVL((AVLNode)overallRoot);
	}

	/*
	 * Adjusts heights and ensures that the tree maintains AVL properties
	 *	Note: this is a testing method now for testing now rather than method for insert
	 *	
	 * */
	private boolean verifyAVL(AVLNode n){
		// If it's an empty tree it's balanced 
		if(n == null){
			return true;
		}

		if(n.left != null){
			if(n.right!=null){
				// We have two children for this node. Compare their heights. If they differ
				// by more than 1, return false. Otherwise verify the children are also balanced 
				if(heightFactor(n)>=2 || heightFactor(n)<=-2){
					return false;
				}else{
					return verifyAVL((AVLNode)n.left) && verifyAVL((AVLNode)n.right);
				}
			}else{
				// The height of this node must be one since it doesn't have a right child.
				if(n.height !=1){					
					return false;
				}else{
					return verifyAVL((AVLNode)n.left);
				}
			}
		}else if(n.right != null){
			// The height of this node must be one since it doesn't have a left child.
			if(n.height != 1){
				return false;
			}
			return verifyAVL((AVLNode)n.right);
		}
		// Base case. If this node is a leaf node it is balanced.
		return n.height == 0 && n.right == null && n.left == null;
	}

	//Determines the height of the node based upon it's childrens' height.
	private int determineHeight(AVLNode n){
		if(heightFactor(n) > 0){
			return ((AVLNode)n.left).height + 1;
		}else if(n.right != null){
			return((AVLNode)n.right).height + 1;
		}else{
			return 0;
		}
	}

	// Solves a case 1 imbalance at node n through a single rotation.
	private AVLNode rotateLeftLeft(AVLNode n){
		AVLNode b = (AVLNode)n.left;
		n.left = (AVLNode)b.right;
		b.right = n;
		n.height = determineHeight(n);
		b.height = determineHeight(b);
		return b;
	}

	// Solves a case 4 imbalance at node n through a single rotation.
	private AVLNode rotateRightRight(AVLNode n){
		AVLNode b = (AVLNode)n.right;
		n.right = (AVLNode)b.left;
		b.left = n;
		n.height = determineHeight(n);
		b.height = determineHeight(b);
		return b;
	}

	// Solves a case 2 imbalance at node n through a double rotation.
	private AVLNode rotateLeftRight(AVLNode n){
		n.left = rotateRightRight((AVLNode)n.left);
		return rotateLeftLeft(n);
	}

	// Solves a case 3 imbalance at node n through a double rotation.
	private AVLNode rotateRightLeft(AVLNode n){
		n.right = rotateLeftLeft((AVLNode)n.right);
		return rotateRightRight(n);
	}

	/*
	 * Compares the heights of a node n's left and right nodes.
	 * 
	 * The return value is specified as such:
	 * 		hl - hr < 0 : right tree is bigger
	 * 		hl - hr > 0 : left tree is bigger
	 * 		hl - hr == 0: equal heights
	 */
	private int heightFactor(AVLNode n){
		int hr,hl; //height right, height left
		if(n.left != null){
			hl = ((AVLNode)n.left).height;
		}else{
			hl = -1; // left is null; height of -1
		}

		if(n.right !=null){
			hr = ((AVLNode)n.right).height;
		}else{
			hr = -1; // right is null; height of -1
		}

		//System.out.print(hl - hr);
		return hl - hr;
	}

	/**
	 * AVLNode is a class for a node in an AVLTree. It holds its
	 * height, left and right nodes, count, and data.
	 */
	private class AVLNode extends BSTNode{
		private int height;	//the height of this node

		/**
		 * Constructor. Call the super constructor and initialize 
		 * height to 0. 
		 * @param data The data to be stored in this node
		 */
		private AVLNode(E data){
			super(data);
			height = 0;
		}

	}

}
