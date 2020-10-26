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
        E result = binarySearch(0, list.size()-1, element);
        return result != null;
    }

    @Override
    public E get(E element) {
        E result = binarySearch(0, list.size()-1, element);
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    private E binarySearch(int first, int last, E elem){
        if(last < first) return null;
        int mid = first + (last-first)/2;
        E element = list.get(mid);
        if (element.equals(elem)) return element;
        if (element.compareTo(elem) > 0) return binarySearch(first, mid-1, elem);
        return binarySearch(mid+1, last, elem);
    }
}
