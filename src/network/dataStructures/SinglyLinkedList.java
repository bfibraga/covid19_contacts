package network.dataStructures;

/**
 * Singly linked list Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class SinglyLinkedList<E> implements List<E> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	static class SListNode<E>{
		// Element stored in the node.
		protected E element;
		// (Pointer to) the next node.
		protected SListNode<E> next;

		public SListNode( E elem, SListNode<E> theNext ){
			element = elem;
			next = theNext;
		}

		public SListNode( E theElement ){
			this(theElement, null);
		}

		public E getElement( ){
			return element;
		}

		public SListNode<E> getNext( ){
			return next;
		}

		public void setElement( E newElement ){
			element = newElement;
		}

		public void setNext( SListNode<E> newNext ){
			next = newNext;
		}

	}

	// Node at the head of the list.
	protected SListNode<E> head;

	// Node at the tail of the list.
	protected SListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;


	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public Iterator<E> iterator() throws NoElementException {
		return new SinglyLLIterator<>(head);
	}

	@Override
	public int find(E element) {
		SListNode<E> auxNode = head; // We start from the beginning of the list
		if (head == null) return -1;
		if(head.getElement().equals(element)) return 0; // If the first element is the element we want, return index 0
		E elem;
		for(int i = 1; i < size()-1; i++){ // Else, we iterate throughout the list to find the element we want
			auxNode = auxNode.getNext(); // We advance to the next node in the list
			elem = auxNode.getElement(); // We get the element that the node contains
			if(elem.equals(element)) return i; // If the element is the same as the one we are seeking, return the correspondent index.
			                                   // Else, we go back to the loop until we either the element we want or we are out of nodes.
		}
		return -1; // Return -1 if we never find the element in the list
	}

	@Override
	public E getFirst() throws NoElementException {
		if (head == null) throw new NoElementException();
		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		if (tail == null) throw new NoElementException();
		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if (position >= size() || position < 0) throw new InvalidPositionException();
		SListNode<E> auxNode = head;
		for (int i = 0; i < position; i++){
			auxNode = auxNode.getNext();
		}
		return auxNode.getElement();
	}

	@Override
	public void addFirst(E element) {
		SListNode<E> toInsert = new SListNode<>(element, head);
		head = toInsert;
		if(size() == 0) tail = toInsert;
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		SListNode<E> toInsert = new SListNode<>(element);
		if(tail != null) tail.setNext(toInsert);
		tail = toInsert;
		if(size() == 0) head = toInsert;
		currentSize++;
	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position >= size() || position < 0) throw new InvalidPositionException(); // Position gets out of the list or it's negative, we dont proceed with the add

		if (position == 0) addFirst(element); //If the position is 0, corresponds to AddFirst

		else if (position == size()) addLast(element); // If the position is size(), corresponds to AddLast

		else {
			SListNode<E> previous = head; // Start the iteration from the beginning of the list
			for (int i = 0; i < position - 1; i++) {
				previous = previous.getNext(); // Advance through the list until we reach the node that is 1 position away from the actual position
			}
			SListNode<E> next = previous.getNext(); // We collect the node linked by the previous node and call it "next"
			SListNode<E> toInsert = new SListNode<E>(element, next); // We create a node that is linked to "next" with said element
			previous.setNext(toInsert); // We change the node's link from pointing towards "next" to the new node.
			currentSize++; // We have added a new element so we increase the list's size.
		}
	}

	@Override
	public E removeFirst() throws NoElementException {
		if(isEmpty()) throw new NoElementException();

		E elem = head.getElement();
		if(size() != 1){
			head = head.getNext();
		} else{
			head = null;
		}

		currentSize--;
		return elem;
	}

	@Override
	public E removeLast() throws NoElementException {
		if(isEmpty()) throw new NoElementException();
		E elem = tail.getElement();

		if(size() != 1) {
			int pos = 0;
			SListNode<E> auxNode = head;
			while (pos < currentSize-2) {
				auxNode = auxNode.getNext();
				pos++;
			}
			auxNode.setNext(null);
			tail = auxNode;
		}else {
			tail = null;
		}
		currentSize--;
		return elem;
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if (position >= size() || position < 0) throw new InvalidPositionException();
		E elem;
		if(position == 0) elem = removeFirst();
		else if (position == size()-1) elem = removeLast();
		else {
			SListNode<E> auxNode = head;

			for (int i = 0; i < position - 1; i++) {
				auxNode = auxNode.getNext();
			}

			SListNode<E> previous = auxNode;

			auxNode = auxNode.getNext();

			elem = auxNode.getElement();

			previous.setNext(auxNode.getNext());
			currentSize--;
		}

		return elem;

	}

	@Override
	public boolean remove(E element) {
		int pos = find(element);
		if (pos == -1) return false;
		remove(pos);

		return true;

	}
}
