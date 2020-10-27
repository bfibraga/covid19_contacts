package dataStructures;

public class OrderedSequenceClass<E extends Comparable<E>> implements OrderedSequence<E>{

    private List<E> list;

    public OrderedSequenceClass(){
        list = new DoublyLinkedList<>();
    }
    @Override
    public void insert(E element) {
        Iterator<E> it = iterator();
        E current;
        boolean equalsOrHigher = false;
        int pos = 0;

        if(!list.isEmpty()){
            while(it.hasNext() && !equalsOrHigher){
                current = it.next();
                if(current.compareTo(element)>=0)
                    equalsOrHigher = true;
                else pos++;
            }
        }
        list.add(pos, element);
    }

    @Override
    public boolean remove(E element) {
        return list.remove(element);
    }

    @Override
    public boolean contains(E element) {
        E result = get(element);
        return result != null;
    }

    @Override
    public E get(E element) {
        Iterator<E> it = iterator();
        E result;
        while(it.hasNext()){
            result = it.next();
            if(result.equals(element)) return result;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * Binary search to find for the element. Takes O(n) * amount of times the "get" method of List is called, which is
     * also O(n) so it's not the best way to find for the element
     * @param first first element of the list
     * @param last last element of the list
     * @param elem elem that we're looking for
     * @return the element that is equal to the elem we're using for looking or null if it didn't find it in the list
     */
    private E binarySearch(int first, int last, E elem){
        if(last < first) return null;
        int mid = first + (last-first)/2;
        E element = list.get(mid);
        if (element.equals(elem)) return element;
        if (element.compareTo(elem) > 0) return binarySearch(first, mid-1, elem);
        return binarySearch(mid+1, last, elem);
    }
}
