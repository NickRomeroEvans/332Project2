package phaseA;

import providedCode.*;
import phaseA.GArrayStack;

/**
 * TODO: Replace this comment with your own as appropriate. AVLTree must be
 * subclass of BinarySearchTree<E> and must use inheritance and calls to
 * superclass methods to avoid unnecessary duplication or copying of
 * functionality. 1. Create a subclass of BSTNode, perhaps named AVLNode. 2.
 * Override incCount method such that it creates AVLNode instances instead of
 * BSTNode instances. 3. Do not "replace" the left and right fields in BSTNode
 * with new left and right fields in AVLNode. This will instead mask the
 * super-class fields (i.e., the resulting node would actually have four node
 * fields, with code accessing one pair or the other depending on the type of
 * the references used to access the instance). Such masking will lead to highly
 * perplexing and erroneous behavior. Instead, continue using the existing
 * BSTNode left and right fields. Cast their values to AVLNode whenever
 * necessary in your AVLTree. Note: This may require many casts, but that is
 * o.k. given that our goal is to reuse methods from BinarySearchTree. 4. Do not
 * override dump method of BinarySearchTree & toString method of DataCounter.
 * They are used for grading. TODO: Develop appropriate JUnit tests for your
 * AVLTree (TestAVLTree in testA package).
 */
public class AVLTree<E> extends BinarySearchTree<E> {
	/**
	 * Constructor. Create a BinarySearchTree by passing a comparator to its constructor.
	 * @param c The comparator to pass to the BinarySearchTree constructor
	 */
	public AVLTree(Comparator<? super E> c) {
		super(c);
	}

	/** {@inheritDoc} */
	@Override
	public void incCount(E data) {
		if (overallRoot == null) {
			overallRoot = new AVLNode(data);
			return;
		}
		AVLNode currentNode = (AVLNode) overallRoot;
		GArrayStack<AVLNode> path = new GArrayStack<AVLNode>();

		while (true) {
			// compare the new data with the data at the current node
			int cmp = comparator.compare(data, currentNode.data);
			if (cmp == 0) { // a. Current node is a match
				currentNode.count++;
				return;
			} else if (cmp < 0) { // b. Data goes left of current node
				if (currentNode.left == null) {
					// need to change this
					currentNode.left = new AVLNode(data);
					verifyAVL(path);
					return;
				}
				path.push(currentNode);
				currentNode = (AVLNode) currentNode.left;
			} else { // c. Data goes right of current node
				if (currentNode.right == null) {
					// need to change this
					currentNode.right = new AVLNode(data);
					verifyAVL(path);
					return;
				}

				path.push(currentNode);
				currentNode = (AVLNode) currentNode.right;
			}
		}
	}

	/**
	 * Adjusts heights and ensures that the tree maintains AVL properties. Note:
	 * make private after testing.
	 * @param path
	 */
	private void verifyAVL(GArrayStack<AVLNode> path) {

		while (!path.isEmpty()) {
			AVLNode n = path.pop();
			int hf = heightFactor(n);
			// AB: changed from (hf >= 2 && hf <= -2)
			if (hf >= 2 || hf <= -2) {
				// abs(difference between left and right subtrees) >= 2, meaning
				// tree is unbalanced
				if (hf >= 2) {
					// know that height of left is bigger
					AVLNode left = (AVLNode) n.left;
					hf = heightFactor(left);
					if (hf == -1) {
						// right tree of n's left tree is bigger by one
						rotateLeftRight(n);
					} else {
						rotateLeftLeft(n);
					}
				} else {
					// know that height of right side is bigger
					AVLNode right = (AVLNode) n.right;
					hf = heightFactor(right);
					if (hf == -1) {
						// right tree of n's right tree is bigger by one
						rotateRightRight(n);
					} else {
						rotateRightLeft(n);
					}
				}
			} else {
				n.height++; // AB: why is this here?
			}
		}
	}

	// Solves a case 1 imbalance at node n through a single rotation.
	private void rotateLeftLeft(AVLNode n) {
		AVLNode a = n;
		AVLNode b = (AVLNode) n.left;
		AVLNode y = (AVLNode) b.right;
		n = b;
		b.right = a;
		a.left = y;
	}

	// Solves a case 4 imbalance at node n through a single rotation.
	private void rotateRightRight(AVLNode n) {
		AVLNode a = n;
		AVLNode b = (AVLNode) n.right;
		AVLNode y = (AVLNode) b.left;
		n = b;
		b.left = a;
		a.right = y;
	}

	// Solves a case 2 imbalance at node n through a double rotation.
	private void rotateLeftRight(AVLNode n) {
		AVLNode a = n;
		AVLNode b = (AVLNode) a.left;
		AVLNode c = (AVLNode) b.right;
		AVLNode u = (AVLNode) c.left;
		AVLNode v = (AVLNode) c.right;
		c.right = a;
		c.left = b;
		a.left = v;
		b.right = u;
	}

	// Solves a case 3 imbalance at node n through a double rotation.
	private void rotateRightLeft(AVLNode n) {
		AVLNode a = n;
		AVLNode b = (AVLNode) a.right;
		AVLNode c = (AVLNode) b.left;
		AVLNode u = (AVLNode) c.left;
		AVLNode v = (AVLNode) c.right;
		c.left = a;
		c.right = b;
		a.right = u;
		b.left = v;
	}

	/**
	 * Compares the heights of a node's left and right nodes.
	 * @param n the node whose children to examine
	 * @return hl - hr < 0 : right tree is bigger 
	 *         hl - hr > 0 : left tree is bigger
	 *         hl - hr == 0: equal heights
	 */
	public int heightFactor(AVLNode n) {
		int hr, hl; // height right, height left
		if (n.left != null) {
			hl = ((AVLNode) n.left).height;
		} else {
			hl = -1; // left is null; height of -1
		}

		if (n.right != null) {
			hr = ((AVLNode) n.right).height;
		} else {
			hr = -1; // right is null; height of -1
		}
		return hl - hr;
	}

	/**
	 * Public access method used to retrieve the height of a node.
	 * @param it the iterator, pointing to the node in question
	 * @return the height of the next node
	 */
	public int getHeight(SimpleIterator<E> it) {
		AVLNode n = (AVLNode) it.next();
		return n.height;
	}

	/* 
	 * AVLNode is a class for a node in an AVLTree. It holds its height, left
	 * and right nodes, count, and data.
	 */
	private class AVLNode extends BSTNode {
		private int height; // the height of this node

		// Constructor. Pass data to the super constructor and initialize height to 0.
		private AVLNode(E data) {
			super(data);
			height = 0;
		}
	}

}
