package network.dataStructures;

public interface ConcatenableQueue<E> extends Queue<E> {

    void append(ConcatenableQueue<E> queue);
}
