package network.dataStructures;

public class AVLTree <K extends Comparable<K>,V> 
	extends AdvancedBSTree<K,V> {
	
static class AVLNode<K,V> extends BSTNode<K,V> {
		// Height of the node
		protected int height;
		
		public AVLNode(K key, V value) {
			super(key, value);
			height = 1;
		}
		
		public AVLNode(K key, V value, AVLNode<K,V> parent, AVLNode<K,V> left, AVLNode<K,V> right){ 
			super(key, value, parent, left, right);
			height = 1 + Math.max(getHeight(left),getHeight(right));
		}
		
		protected int getHeight(AVLNode<K,V> node) {
			//precisamos deste metodo porque node pode ser null
			if (node==null)
				return 0;
			return node.getHeight();
		}
		
		public int getHeight() {
			return height;
		}

		public boolean isBalance() {
			int dif= getHeight((AVLNode<K,V>)this.getLeft()) -
					 getHeight((AVLNode<K,V>)this.getRight());
			return dif==0 ||dif==-1 ||dif ==1;
		}
		
		public void setHeight() {
			height= 1 + Math.max(getHeight((AVLNode<K,V>)this.getLeft()),
								 getHeight((AVLNode<K,V>)this.getRight()));
		}
		
		/** 
		 * Return the child with greater height
		 */
		protected AVLNode<K,V> tallerChild()  {
			 AVLNode<K,V> leftChild = (AVLNode<K,V>) this.getLeft();
			 AVLNode<K,V> rightChild = (AVLNode<K,V>) this.getRight();
			 int leftChildHeight  = getHeight(leftChild);
			 int rightChildHeight = getHeight(rightChild);
			 
			 if (leftChildHeight > rightChildHeight)
				 return leftChild;
			 else if (leftChildHeight < rightChildHeight)
				 return rightChild;
			return leftChild;
		}
}

	
/**  
 * Rebalance method called by insert and remove.  Traverses the path from 
 * zPos to the root. For each node encountered, we recompute its height 
 * and perform a trinode restructuring if it's unbalanced.
 * the rebalance is completed with O(log n)running time
 */
  protected void rebalance(AVLNode<K,V> zPos) {
    if(zPos.isInternal())
       zPos.setHeight();
    // Melhorar se possivel
	  boolean restructured = false;
    while (zPos.getParent()!=null && !restructured) {  // traverse up the tree towards the root
    	zPos = (AVLNode<K, V>)zPos.getParent();
        zPos.setHeight();
      if (!zPos.isBalance()) {

    	  //perform a trinode restructuring at zPos's tallest grandchild
    	  //If yPos (tallerChild(zPos)) denote the child of zPos with greater height. 
    	  //Finally, let xPos be the child of yPos with greater height
    	  AVLNode<K,V> xPos = zPos.tallerChild().tallerChild();
    	  zPos = (AVLNode<K, V>) restructure(xPos); // tri-node restructure (from parent class)
    	  //note that zPos now may be a different node (the new root of the tri-node)
    	  // recompute heights for these 3 nodes


    	  ((AVLNode<K, V>) zPos.getLeft()).setHeight();  
    	  ((AVLNode<K, V>) zPos.getRight()).setHeight();
    	  zPos.setHeight();
    	  restructured = true;

      }

    }

  } 
 
@Override
public V insert(K key, V value) {
	//TODO
	V valueToReturn=null;
	AVLNode<K,V> newNode= null;// node where the new entry is being inserted (if find(key)==null)
	if(root == null){
		newNode = new AVLNode<>(key, value);
		root = newNode;
		currentSize++;
	}else {
		AVLNode<K, V> parent = (AVLNode<K, V>) findPlaceToInsert(root, key);
		int compResult = key.compareTo(parent.getKey()); // compares the parent's key
		if (compResult == 0) {
			valueToReturn = parent.getValue();
			parent.setValue(value);
		} else {
			AVLNode<K, V> toInsert = new AVLNode<>(key, value, parent, null, null);
			if (compResult < 0) parent.setLeft(toInsert);
			else parent.setRight(toInsert);
			newNode = toInsert;

			currentSize++;
		}
	}
	// insert the new Entry (if find(key)==null)
	// or set the new value (if find(key)!=null)

	if(newNode != null) //(if find(key)==null)
		rebalance(newNode); // rebalance up from the inserted node
	//rebalance checks if it needs to call restructure
	return valueToReturn;
}

@Override
public V remove(K key) {

	V valueToReturn=null;
	AVLNode<K,V> node=null; // node where the key was deleted
	AVLNode<K,V> toRemove = (AVLNode<K,V>) findNode(root,key);
	if(toRemove != null){
		node = toRemove;
		valueToReturn = removeNode(toRemove);
	}

	if(node != null) //(if find(key)==null)
		rebalance(node); // rebalance up from the node
	return valueToReturn;
}

}
