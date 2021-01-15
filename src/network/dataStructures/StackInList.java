package network.dataStructures;

import network.Exceptions.NoElementException;

public class StackInList<E> implements Stack<E>
{

    static final long serialVersionUID = 0L;


    // Memory of the stack: a list.
    protected List<E> list;                     


    public StackInList( )
    {     
        list = new DoublyLinkedList<>();
    }


    // Returns true iff the stack contains no elements.
    public boolean isEmpty( )
    {     
        return list.isEmpty();
    }


    // Returns the number of elements in the stack.
    public int size( )
    {     
        return list.size();
    }


    // Returns the element at the top of the stack.
    public E top( ) throws NoElementException
    {     
        if ( list.isEmpty() )
            throw new NoElementException();
        
        return list.getFirst();
    }


    // Inserts the specified element onto the top of the stack.
    public void push( E element )
    { 
        list.addFirst(element);
    }


    // Removes and returns the element at the top of the stack.
    public E pop( ) throws NoElementException 
    {     
        if ( list.isEmpty() )
            throw new NoElementException();

        return list.removeFirst();
    }


}
