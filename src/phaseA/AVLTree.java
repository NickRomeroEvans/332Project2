package phaseA;
import providedCode.*;
import phaseA.GArrayStack;


/**
 * TODO: Replace this comment with your own as appropriate.
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
 * TODO: Develop appropriate JUnit tests for your AVLTree (TestAVLTree in 
 *    testA package).
 */
public class AVLTree<E> extends BinarySearchTree<E> {
	//overallRoot
	//comparator
	//size
	public AVLTree(Comparator<? super E> c) {
		super(c);
	}
	
	
	
	public void incCount(E data) {
		 if(overallRoot == null){
			 overallRoot = new AVLNode(data);
			 return;
		 }
		 AVLNode currentNode = (AVLNode)overallRoot;
		 GArrayStack<AVLNode> path = new GArrayStack<AVLNode>();
		 
	     while (true) {
	        // compare the new data with the data at the current node
	        int cmp = comparator.compare(data, currentNode.data);
	        if(cmp == 0) {            // a. Current node is a match
	        	currentNode.count++;
	        	return;
	        }else if(cmp < 0) {       // b. Data goes left of current node
	            if(currentNode.left == null) {
	            	//need to change this
	            	currentNode.left = new AVLNode(data);
	            	verifyAVL(path);
	            	return;
	            }	
	            path.push(currentNode);
	            currentNode = (AVLNode)currentNode.left;
	        }else{                    // c. Data goes right of current node
	        	if(currentNode.right == null) {
	        		//need to change this
	        		currentNode.right = new AVLNode(data);
	        		verifyAVL(path);
	            	return;
	            }
	        	
	        	path.push(currentNode);
	        	currentNode = (AVLNode)currentNode.right;
	         }
	     }
	 }

	 
	 /**
	  * Adjusts heights and ensures that the tree maintains AVL properties
	  *	Note: make private after testing
	  * */
	 public void verifyAVL(GArrayStack<AVLNode> path){
		 
		 while(!path.isEmpty()){
			 AVLNode n = path.pop();
			 int hf = heightFactor(n);
			 if(hf >=2 && hf <= -2){
				 if(hf >= 2){
					 //know that height of left is bigger
					 AVLNode left = (AVLNode) n.left;
					 hf = heightFactor(left);
					 if(hf == -1){
						 rotateLeftRight(n);
					 }else{
						 rotateLeftLeft(n);
					 }
				 }else{
					 //know that height of right side is bigger
					 AVLNode right = (AVLNode) n.right;
					 hf = heightFactor(right);
					 if(hf == -1){
						 rotateRightRight(n);
					 }else{
						 rotateRightLeft(n);
					 }
				 }
			 }else{
				 n.height++;
			 }
		 }
	 }
	 
	 public void rotateLeftLeft(AVLNode n){
		 AVLNode a = n;
		 AVLNode b = (AVLNode)n.left;
		 AVLNode y = (AVLNode)b.right;
		 n= b;
		 b.right = a;
		 a.left = y;
	 }
	 
	 public void rotateRightRight(AVLNode n){
		 AVLNode a = n;
		 AVLNode b = (AVLNode)n.right;
		 AVLNode y = (AVLNode)b.left;
		 n= b;
		 b.left = a;
		 a.right = y;
	 }
	 
	 public void rotateLeftRight(AVLNode n){
		 AVLNode a = n;
		 AVLNode b = (AVLNode)a.left;
		 AVLNode c = (AVLNode)b.right;
		 AVLNode u = (AVLNode)c.left;
		 AVLNode v = (AVLNode)c.right;
		 c.right = a;
		 c.left = b;
		 a.left = v;
		 b.right = u;
	 }
	 
	 public void rotateRightLeft(AVLNode n){
		 AVLNode a = n;
		 AVLNode b = (AVLNode)a.right;
		 AVLNode c = (AVLNode)b.left;
		 AVLNode u = (AVLNode)c.left;
		 AVLNode v = (AVLNode)c.right;
		 c.left = a;
		 c.right = b;
		 a.right = u;
		 b.left = v;
	 }
	 
	 //Compares height of left node with right node
	 private int heightFactor(AVLNode n){
		 int hr,hl;
		 if(n.left != null){
			 hl = ((AVLNode)n.left).height;
		 }else{
			 hl = -1;
		 }
		 
		 if(n.right !=null){
			 hr = ((AVLNode)n.right).height;
		 }else{
			 hr = -1;
		 }
		 return hl - hr;
	 }
	
	// TODO: To-be implemented
	public class AVLNode extends BSTNode{
		public int height;
		//left
		//right
		//data
		//count
		
		
		public AVLNode(E data){
			super(data);
			height = 0;
		}
	}

}
