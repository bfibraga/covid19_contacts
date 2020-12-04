package dataStructures;

public class CollisionList<K, V> implements Dictionary<K,V> {

    static class CDListNode<K, V>{
        // Element stored in the node.
        protected Entry<K,V> element;
        // (Pointer to) the next node.
        protected CDListNode<K, V> next;
        // (Pointer to) the previous node.
        protected CDListNode<K, V> previous;

        public CDListNode(Entry<K, V> elem, CDListNode<K, V> thePrev, CDListNode<K, V> theNext ){
            element = elem;
            previous = thePrev;
            next = theNext;
        }

        public CDListNode( Entry<K, V> theElement ){
            this(theElement, null, null);
        }

        public Entry<K, V> getElement( ){
            return element;
        }

        public CDListNode<K, V> getNext( ){
            return next;
        }

        public CDListNode<K, V> getPrevious( ){
            return previous;
        }

        public void setElement( Entry<K, V> newElement ){
            element = newElement;
        }

        public void setNext( CDListNode<K, V> newNext ){
            next = newNext;
        }

        public void setPrevious( CDListNode<K, V> newPrevious ){
            previous = newPrevious;
        }

    }
    //(Pointer to) the head node.
    CDListNode<K,V> head;

    //(Pointer to) the tail node.
    CDListNode<K,V> tail;

    //Size of this list.
    int currentSize;

    public CollisionList(){
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
    public V find(K key) {
        CDListNode<K,V> node = head;
        if(node == null) return null;
        if (node.getElement().getKey().equals(key)) return node.getElement().getValue();
        while(node.getNext() != null){
            node = node.getNext();
            if (node.getElement().getKey().equals(key)) return node.getElement().getValue();
        }
        return null;
    }

    @Override
    public V insert(K key, V value) {
        V oldValue = null;
        boolean sameKey = false;
        CDListNode<K,V> node = head;
        if (node == null){
            addFirst(new EntryClass<>(key, value));
            return null;
        }
        if (node.getElement().getKey().equals(key)) sameKey = true;
        while(!sameKey && node.getNext() != null) {
            node = node.getNext();
            if (node.getElement().getKey().equals(key)) sameKey = true;
        }

        if(sameKey){
            oldValue = node.getElement().getValue();
            node.setElement(new EntryClass<>(key, value));
        } else{
            addFirst(new EntryClass<>(key, value));
        }
        return oldValue;
    }

    private void addFirst(Entry<K,V> entry) {
        CDListNode<K,V> toInsert = new CDListNode<>(entry, null, head );
        if(size() != 0)
            head.setPrevious(toInsert);
        head = toInsert;
        currentSize++;

        if (size() == 1) tail = toInsert;
    }

    @Override
    public V remove(K key) {
        CDListNode<K,V> toBeRemoved = null;
        CDListNode<K,V> node = head;
        if(head == null) return null;
        while(node != null){
            if(node.getElement().getKey().equals(key)) toBeRemoved = node;
            node = node.getNext();
        }
        if(toBeRemoved != null){
            if(toBeRemoved == head){
                head = head.getNext();
                if(head != null)
                head.setPrevious(null);
            }
            else if(toBeRemoved == tail){
                tail = tail.getPrevious();
                if(tail != null)
                tail.setNext(null);
            }
            else{
                CDListNode<K,V> previous = toBeRemoved.getPrevious();
                CDListNode<K,V> next = toBeRemoved.getNext();
                previous.setNext(next);
                next.setPrevious(previous);
            }
            currentSize--;
        }
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new CLIterator<>(head, tail);
    }

    @Override
    public Iterator<V> iteratorValue() {
        return new CLIteratorValue<>(head, tail);
    }


}
