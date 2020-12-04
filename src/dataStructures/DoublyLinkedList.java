package dataStructures;


import network.Exceptions.InvalidPositionException;
import network.Exceptions.NoElementException;

/**
 * Doubly linked list Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class DoublyLinkedList<E> implements List<E>  {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	static class DListNode<E>{
		// Element stored in the node.
		protected E element;
		// (Pointer to) the next node.
		protected DListNode<E> next;
		// (Pointer to) the previous node.
		protected DListNode<E> previous;

		public DListNode( E elem, DListNode<E> thePrev, DListNode<E> theNext ){
			element = elem;
			previous = thePrev;
			next = theNext;
		}
		
		public DListNode( E theElement ){
			this(theElement, null, null);
		}

		public E getElement( ){
			return element;
		}

		public DListNode<E> getNext( ){
			return next;
		}

		public DListNode<E> getPrevious( ){
			return previous;
		}

		public void setElement( E newElement ){
			element = newElement;
		}

		public void setNext( DListNode<E> newNext ){
			next = newNext;
		}

		public void setPrevious( DListNode<E> newPrevious ){
			previous = newPrevious;
		}

	}


	// Node at the head of the list.
	protected DListNode<E> head;

	// Node at the tail of the list.
	protected DListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;

	public DoublyLinkedList( ){
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}


	@Override
	public int find(E element) {
		DListNode<E> auxNo = head;
		if (head.getElement().equals(element)) return 0;
		for(int i = 1; i < size() ; i++){
            auxNo = auxNo.getNext();
            if(auxNo.getElement().equals(element)) return i;
        }

		return -1;
	}


	@Override
	public E getFirst() throws NoElementException {
		if (isEmpty()) throw new NoElementException();
		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		if (isEmpty()) throw new NoElementException();
		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if (position<0 || position>=currentSize) 
			throw new InvalidPositionException();
		return getNode(position).getElement();
	}
	
	private DListNode<E> getNode(int position){
		DListNode<E> auxNode=head;
		for(int i=1;i<=position;i++)
			auxNode = auxNode.getNext();
		return auxNode;
	}
	

	@Override
	public void addFirst(E element) {
	    DListNode<E> toInsert = new DListNode<>(element, null, head );
	    if(size() != 0)
	    head.setPrevious(toInsert);
		head = toInsert;
		currentSize++;

		if (size() == 1) tail = toInsert;

	}

	@Override
	public void addLast(E element) {
		DListNode<E> toInsert = new DListNode<>(element, tail, null);
		if(size() != 0)
		tail.setNext(toInsert);
		tail = toInsert;
		currentSize++;

		if(size() == 1) head = toInsert;
	}

	private void addMiddle(int position, E element) {
		DListNode<E> auxNode=getNode(position);
		DListNode<E> previous = auxNode.getPrevious();
		DListNode<E> toInsert = new DListNode<>(element, previous, auxNode);

		previous.setNext(toInsert);
		auxNode.setPrevious(toInsert);

	}
	
	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position<0 || position >currentSize) 
			throw new InvalidPositionException();
		if (position==0) 
			addFirst(element);
		else if (position==currentSize) 
			addLast(element);
		else {
			addMiddle(position,element);
		}

	}

    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    private void removeFirstNode( )
    {
		if (size() == 1) {
		    head = null;
		    tail = null;
        }else head = head.getNext();
		currentSize--;
    }


    @Override
    public E removeFirst( ) throws NoElementException
    {
        if (isEmpty())
            throw new NoElementException();

        E element = head.getElement();
        this.removeFirstNode();
        return element;
    }
	
    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    private void removeLastNode( )
    {
        if (size() == 1) {
            head = null;
            tail = null;
        }else tail = tail.getPrevious();
        currentSize--;
    }


    @Override 
    public E removeLast( ) throws NoElementException
    {
        if (isEmpty())
            throw new NoElementException();

        E element = tail.getElement();
        this.removeLastNode();
        return element;
    }
    
    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     * @param node - middle node to be removed
     */
    private void removeMiddleNode(DListNode<E> node)
    {
      	DListNode<E> previous = node.getPrevious();
      	DListNode<E> next = node.getNext();

      	previous.setNext(next);
      	next.setPrevious(previous);

      	currentSize--;
    }
    
	private E removeMiddle(int position) {
		DListNode<E> nodeToRemove = this.getNode(position);
		this.removeMiddleNode(nodeToRemove);
		return nodeToRemove.getElement();
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if(position<0 || position>=currentSize)
			throw new InvalidPositionException();
		if (position==0)
			return removeFirst();
		if (position==currentSize-1)
			return removeLast();
		return removeMiddle(position);
	}

	@Override
	public boolean remove(E element)
	{
		DListNode<E> node = this.findNode(element);
		if ( node == null )
			return false;
		else
		{
			if ( node == head )
				this.removeFirstNode();
			else if ( node == tail )
				this.removeLastNode();
			else
				this.removeMiddleNode(node);
			return true;
		}
	}

	/**
	 * Find the <code>DListNode</code> with this <code>element</code>
	 * @param element -
	 * @return DListNode with this <code>element</code>, otherwise returns null
	 */
    private DListNode<E> findNode(E element) {
        DListNode<E> auxNode = head;

        if (auxNode == null)
        	return null;

        while (auxNode.getNext() != null){
			if(auxNode.getElement().equals(element)) return auxNode;
			auxNode = auxNode.getNext();
		}

        return null;
    }

    @Override
	public Iterator<E> iterator() {
		return new DoublyLLIterator<E>(head,tail);
	}

    /**
     * Removes all of the elements from the specified list and
     * inserts them at the end of the list (in proper sequence).
     * @param list - list to be appended to the end of this
     */
    public void append( DoublyLinkedList<E> list )
    {
    	if (!list.isEmpty()){
			if(this.isEmpty()){
				this.head = list.head;
			} else {

				this.tail.setNext(list.head);
				list.head.setPrevious(this.tail);
			}

			this.tail = list.tail;
			this.currentSize = size() + list.size();

			list.head = null;
			list.tail = null;
			list.currentSize = 0;
		}
    }

}
