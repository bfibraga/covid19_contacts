package dataStructures;

public class ConcatenableQueueInList<E> extends QueueInList<E> implements ConcatenableQueue<E>{

    public ConcatenableQueueInList(){
        super();
    }

    @Override
    public void append(ConcatenableQueue<E> queue) {
        if(queue instanceof ConcatenableQueueInList){
            DoublyLinkedList<E> l1 = (DoublyLinkedList<E>) this.list;
            DoublyLinkedList<E> l2 = (DoublyLinkedList<E>)((ConcatenableQueueInList) queue).list;
            l1.append(l2);
        }
    }

}
