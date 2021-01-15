package network.dataStructures;

/**
 * Implementation of Iterator for SinglyLinkedList 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
import network.dataStructures.SinglyLinkedList.SListNode;
import network.Exceptions.NoSuchElementException;


public class SinglyLLIterator<E> implements Iterator<E> {
	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;
	
    /** 
     * Node with the first element in the iteration.
     */
    protected SListNode<E> firstNode;

    /**
     * Node with the next element in the iteration.
     */
    protected SListNode<E> nextToReturn;


    public SinglyLLIterator(SListNode<E> first )
    {
        firstNode = first;
        this.rewind();
    }  
	
	@Override
	public boolean hasNext() {
		return nextToReturn != null;
	}

	@Override
	public E next() throws NoSuchElementException {
    	if(!hasNext()) throw new NoSuchElementException();

		E elem = nextToReturn.getElement();
		nextToReturn = nextToReturn.getNext();
		return elem;
	}

	@Override
	public void rewind() {
		nextToReturn = firstNode;

	}

}
