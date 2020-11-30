package dataStructures;
import dataStructures.CollisionList.CDListNode;

/**
 * Implementation of Two Way Iterator for DLList 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */

import dataStructures.DoublyLinkedList.DListNode;

class CLIterator<K,V> implements TwoWayIterator<Entry<K,V>>
{

	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;


    /**
     * Node with the first element in the iteration.
     */
    protected CDListNode<K,V> firstNode;

    /**
     * Node with the last element in the iteration.
     */
    protected CDListNode<K,V> lastNode;

    /**
     * Node with the next element in the iteration.
     */
    protected CDListNode<K,V> nextToReturn;

    /**
     * Node with the previous element in the iteration.
     */
    protected CDListNode<K,V> prevToReturn;


    /**
     * DoublyLLIterator constructor
     * @param first - Node with the first element of the iteration
     * @param last - Node with the last element of the iteration
     */
    public CLIterator(CDListNode<K,V> first, CDListNode<K,V> last )
    {
        firstNode = first;
        lastNode = last;
        this.rewind();
    }      


    @Override
    public void rewind( )
    {
        nextToReturn = firstNode;
        prevToReturn = null;
    }


    @Override
    public void fullForward( )
    {
        prevToReturn = lastNode;
        nextToReturn = null;
    }


    @Override
    public boolean hasNext( )
    {
        return nextToReturn != null;
    }


    @Override
    public boolean hasPrevious( )
    {
        return prevToReturn != null;
    }


    @Override
    public Entry<K,V> next( ) throws NoSuchElementException
    {
        if ( !this.hasNext() )
            throw new NoSuchElementException();

        Entry<K,V> element = nextToReturn.getElement();
        nextToReturn = nextToReturn.getNext();
        return element;
    }


    @Override
    public Entry<K,V> previous( ) throws NoSuchElementException
    {
        if ( !this.hasPrevious() )
            throw new NoSuchElementException();

        Entry<K,V> element = prevToReturn.getElement();
        prevToReturn = prevToReturn.getPrevious();
        return element;
    }


}
