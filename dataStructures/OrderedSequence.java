package dataStructures;

public interface OrderedSequence<E extends Comparable<E>> {
    /**
     *
     * Inserts an element to the list, following the order provided by the element's "compareTo", making an ordered sequence
     * @param element The element we want to insert in the sequence
     * @pre element != null
     */
    void insert(E element);

    /**
     *
     * Removes an element from the list, if it exists in the list
     * @param element The element we want to remove from the sequence
     * @return true if it has successfully removed the element from the sequence, false if otherwise.
     * @pre element != null && contains(element)
     */
    boolean remove(E element);

    /**
     * Checks if a certain element exists within the element using its "equals" method
     * @param element element we're using to find the element within the list
     * @return true if the list contains an element that satisfies the "equals" method, false if otherwise
     * @pre element != null
     */
    boolean contains(E element);

    /**
     * Looks throughout the list looking for an element that satisfies "equals(element)", being element the template for
     * looking for the element in the list and returns that element.
     * @param element The template we're using to find for the element in the list
     * @return the element within the list that is equals to the template, null if there's no element in the list that
     * is equals to the template
     * @pre element != null
     */
    E get(E element);

    /**
     * Initializes an iterator within the list of the ordered sequence
     * @return Iterator throughout the list in the ordered sequence
     */
    Iterator<E> iterator();

    /**
     * Checks if the ordered sequence has no elements inserted in it.
     * @return true if the list has no elements in it, false if it has at least one element.
     */
    boolean isEmpty();

    /**
     * Returns current size of the ordered sequence
     * @return Number of elements present in the sequence
     */
    int getCurrentSize();

}
