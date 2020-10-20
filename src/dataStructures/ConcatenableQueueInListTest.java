package dataStructures;

/**
 * Rightfully owned by Spike
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcatenableQueueInListTest {
    public static final String CHEESE = "Cheese";
    public static final String ONIONS = "Onions";

    <E> ConcatenableQueue<E> testObject(){
        return new ConcatenableQueueInList<E>();
    }

    @Test
    void enqueue(){
        ConcatenableQueue<String> queue = testObject();
        assertTrue(queue.isEmpty());
        queue.enqueue(CHEESE);
        assertFalse(queue.isEmpty());
        queue.enqueue(ONIONS);
        assertFalse(queue.isEmpty());
    }
    @Test
    void dequeue(){
        ConcatenableQueue<String> queue = testObject();
        assertTrue(queue.isEmpty());
        queue.enqueue(CHEESE);
        assertFalse(queue.isEmpty());
        queue.enqueue(ONIONS);
        assertFalse(queue.isEmpty());
        String elem1 = queue.dequeue();
        assertEquals(CHEESE, elem1);
        elem1 = queue.dequeue();
        assertTrue(queue.isEmpty());
        assertEquals(ONIONS, elem1);
        queue.enqueue(CHEESE);
        queue.enqueue(ONIONS);
        queue.enqueue(ONIONS);
        assertEquals(3, queue.size());
        queue.enqueue(CHEESE);
        assertEquals(4, queue.size());
        elem1 = queue.dequeue();
        assertEquals(CHEESE, elem1);
        elem1 = queue.dequeue();
        assertEquals(ONIONS, elem1);
        elem1 = queue.dequeue();
        assertEquals(ONIONS, elem1);
        elem1 = queue.dequeue();
        assertEquals(CHEESE, elem1);
        assertTrue(queue.isEmpty());
    }
    @Test
    void append(){
        ConcatenableQueue<String> queue = testObject();
        ConcatenableQueue<String> queue2 = testObject();
        queue.enqueue(CHEESE);
        queue2.enqueue(ONIONS);
        queue.append(queue2);
        assertEquals(2, queue.size());
        String elem1 = queue.dequeue();
        assertEquals(ONIONS, elem1);
        elem1 = queue.dequeue();
        assertEquals(CHEESE, elem1);
    }
}