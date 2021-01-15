package network.dataStructures;

public class AdvancedBSTree <K extends Comparable<K>, V> 
	extends BinarySearchTree<K,V>{

		// Metodos comuns a arvores binarias de pesquisa auto-equilibradas.
		// Operacoes basicas para alterar a forma da arvore tratando
		// de reduzir a sua altura.
		
		 /**
	     * Performs a single right rotation rooted at Y node.
	     * Node X was a  left  child  of Y before the  rotation,  
	     * then Y becomes the right child of X after the rotation.
	     * @param Y - root of the rotation
	     * @pre: Y has a left child
	     *    Y				X
	     *   /	turns into:	 \
	     *  X 				  Y
	     */
	    protected void rotateRight( BSTNode<K,V> Y){
	    	BSTNode<K,V> parent = Y.getParent();
	        BSTNode<K,V> X = Y.getLeft();
			BSTNode<K,V> rightChildOfX = X.getRight();

			X.setRight(Y);
			Y.setParent(X);
			Y.setLeft(rightChildOfX);

			if(rightChildOfX != null) rightChildOfX.setParent(Y);
			if(parent != null){
				if(parent.getLeft() == Y) parent.setLeft(X);
				else parent.setRight(X);
			} else{
				root = X;
				root.setParent(null);
			}

	    }
	    
	    /**
	     * Performs a single left rotation rooted at Y node.
	     * Node X was a  right  child  of Y before the  rotation,  
	     * then Y becomes the left child of X after the rotation.
	     * @param Y - root of the rotation
	     * @pre: Y has a right child
	     *    Y				      X
	     *     \  turns into:	 /
	     *      X 				Y
	     */
	    protected void rotateLeft( BSTNode<K,V> Y){
			BSTNode<K,V> parent = Y.getParent();
			BSTNode<K,V> X = Y.getRight();
			BSTNode<K,V> leftChildOfX = X.getLeft();

			X.setLeft(Y);
			Y.setParent(X);
			Y.setRight(leftChildOfX);

			if(leftChildOfX != null) leftChildOfX.setParent(Y);
			if(parent != null){
				if(parent.getLeft() == Y) parent.setLeft(X);
				else parent.setRight(X);
			} else{
				root = X;
			}

		}
	    
	   /** 
	   * Performs a tri-node restructuring (a single or double rotation).
	   * Assumes the nodes are in one of following configurations:
	   *
	   * @param x - a node that has both a parent y and a grandparent z
	   * <pre>
	   *          z=c       z=c        z=a         z=a
	   *         /  \      /  \       /  \        /  \
	   *       y=b  t4   y=a  t4    t1  y=c     t1  y=b
	   *      /  \      /  \           /  \         /  \
	   *    x=a  t3    t1 x=b        x=b  t4       t2 x=c
	   *   /  \          /  \       /  \             /  \
	   *  t1  t2        t2  t3     t2  t3           t3  t4
	   * </pre>
	   * @return the new root of the restructured subtree
	   */
	    protected BSTNode<K,V> restructure (BSTNode<K,V> x) {
	    	// the modification of a tree T caused by a trinode restructuring operation
	    	// can be implemented through case analysis either as a single rotation or as a double rotation.
	    	// The double rotation arises when position x has the middle of the three relevant keys
	    	// and is first rotated above its parent y, and then above what was originally its grandparent z. 
	    	// In any of the cases, the trinode restructuring is completed with O(1)running time.
	    	// [Goodrich et al., 2015]
	    	BSTNode<K,V> parent = x.getParent();
	    	BSTNode<K,V> grandparent = parent.getParent();
			BSTNode<K,V> grandGrandParent = grandparent.getParent();

			if(parent.getLeft() == x && grandparent.getLeft() == parent){
				rotateRight(grandparent);
				parent.setParent(grandGrandParent);
				return parent;
			}

			if(parent.getRight() == x && grandparent.getRight() == parent) {
				rotateLeft(grandparent);
				parent.setParent(grandGrandParent);
				return parent;
			}
			if(parent.getLeft() == x && grandparent.getRight() == parent){
				rotateRight(parent);
				rotateLeft(grandparent);
				x.setParent(grandGrandParent);
				return x;
			}
			rotateLeft(parent);
			rotateRight(grandparent);
			x.setParent(grandGrandParent);
			return x;
	    }
}

