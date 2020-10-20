package dataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    public static final String CHEESE = "Cheese";
    public static final String ONIONS = "Onions";

    <E> List<E> testObject(){
        return new SinglyLinkedList<E>();
    }

    @Test
    void addLast() {
        List<String> list = testObject();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.addLast("Cheese");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        list.addLast("Onions");
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        for (int i = 0; i < 10; i++) {
            list.addLast(ONIONS);
        }
        assertEquals(12, list.size());
    }

    @Test
    void addFirst() {
        List<String> list = testObject();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.addFirst("Cheese");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        list.addFirst("Onions");
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        for (int i = 0; i < 10; i++) {
            list.addFirst(ONIONS);
        }
        assertEquals(12, list.size());
    }

    @Test
    /**
     * @dependsOn addLast
     */
    void iterator() {
        int count = 20;
        List<Integer> list = testObject();
        for(int i = 0; i < count; i++){
            list.addLast(i);
        }
        Iterator<Integer> iterator = list.iterator();
        for(int i = 0; i < count; i++){
            assertTrue(iterator.hasNext());
            assertEquals(i, iterator.next());
        }
    }

    @Test
    /**
     * @dependsOn addLast, addFirst
     */
    void find() {
        List<String> list = testObject();
        assertEquals(-1, list.find(ONIONS));
        list.addLast(ONIONS);
        assertEquals(-1, list.find(CHEESE));
        assertEquals(0, list.find(ONIONS));
        list.addLast(CHEESE);
        assertEquals(0, list.find(ONIONS));
        list.addLast(CHEESE);
        assertEquals(0, list.find(ONIONS));
        list.addLast(CHEESE);
        assertEquals(0, list.find(ONIONS));
        int count = 3;
        for (int i = 0; i < count; i++) {
            assertEquals(i, list.find(ONIONS));
            list.addFirst(CHEESE);
        }
        assertEquals(count, list.find(ONIONS));
        list.addLast(ONIONS);
        assertEquals(count, list.find(ONIONS));
        list.add(count+1, ONIONS);
        assertEquals(count, list.find(ONIONS));
        list.addFirst(ONIONS);
        assertEquals(0, list.find(ONIONS));
    }


    @Test
    /**
     * @dependsOn addLast, addFirst
     */
    void getLastOrFirst() {
        List<Integer> list = testObject();
        for(int i = 0; i < 10; i++){
            list.addFirst(i);
            assertEquals(0, list.getLast());
            assertEquals(i, list.getFirst());
        }
        list = testObject();
        for(int i = 0; i < 10; i++){
            list.addLast(i);
            assertEquals(i, list.getLast());
            assertEquals(0, list.getFirst());
        }
    }

    @Test
    /**
     * @dependsOn addLast
     */
    void get() {
        List<String> list = testObject();
        list.addLast(CHEESE);
        assertEquals(CHEESE, list.get(0));
        list.addLast(ONIONS);
        assertEquals(CHEESE, list.get(0));
        assertEquals(ONIONS, list.get(1));
        assertEquals(CHEESE, list.get(0));
        assertFalse(list.isEmpty());
    }

    @Test
    /**
     * @dependsOn addLast, find
     */
    void addAndRemove() {
        List<String> list = testObject();
        list.addLast(ONIONS);
        list.addLast(ONIONS);
        list.addLast(ONIONS);
        list.addLast(ONIONS);
        list.addLast(ONIONS);
        assertEquals(5, list.size());
        list.add(3, CHEESE);
        assertEquals(6, list.size());
        assertEquals(3, list.find(CHEESE));
        list.remove(3);
        assertEquals(-1, list.find(CHEESE));
        assertEquals(5, list.size());
    }

    @Test
    /**
     * @dependsOn addLast, addFirst, getFirst, getLast
     */
    void removeFirst() {
        List<String> list = testObject();
        assertTrue(list.isEmpty());
        list.addLast(ONIONS);
        assertEquals(1, list.size());
        list.removeFirst();
        assertTrue(list.isEmpty());
        list.addLast(CHEESE);
        list.addLast(CHEESE);
        list.addLast(CHEESE);
        list.addLast(CHEESE);
        list.addFirst(ONIONS);
        assertEquals(ONIONS, list.getFirst());
        list.removeFirst();
        assertNotEquals(ONIONS, list.getFirst());
        assertEquals(-1, list.find(ONIONS));
    }

    @Test
    /**
     * @dependsOn addLast, addFirst, getFirst, getLast
     */
    void removeLast() {
        List<String> list = testObject();
        assertTrue(list.isEmpty());
        list.addLast(ONIONS);
        assertEquals(1, list.size());
        list.removeLast();
        assertTrue(list.isEmpty());
        list.addLast(CHEESE);
        list.addLast(CHEESE);
        list.addLast(CHEESE);
        list.addLast(CHEESE);
        list.addLast(ONIONS);
        assertEquals(ONIONS, list.getLast());
        list.removeLast();
        assertNotEquals(ONIONS, list.getLast());
        assertEquals(-1, list.find(ONIONS));
    }

}