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
            while(it.hasNext() && equalsOrHigher){
                current = it.next();
                if(element.compareTo(current)>=0)
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
        Iterator<E> it = iterator();

        while(it.hasNext()){
            E elem = it.next();
            if(elem.equals(element)) return true;
        }
        return false;
    }

    @Override
    public E get(E element) {
        Iterator<E> it = iterator();
        E elem;
        while(it.hasNext()){
            elem = it.next();
            if(element.equals(elem)) return elem;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

}
