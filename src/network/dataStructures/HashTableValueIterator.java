package network.dataStructures;

import network.Exceptions.NoSuchElementException;

public class HashTableValueIterator<K,V> implements Iterator<V> {
    private Dictionary<K,V>[] table;
    private Iterator<Entry<K,V>> entryIterator;
    private int currentIndex;
    private Entry<K,V> next;
    public HashTableValueIterator(Dictionary<K, V>[] table) {
        this.table = table;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public V next() throws NoSuchElementException {
        V toReturn = next.getValue();
        if(entryIterator.hasNext()) next = entryIterator.next();
        else next = findListInTable();
        return toReturn;
    }

    @Override
    public void rewind() {
        currentIndex = 0;
        next = findListInTable();

    }

    private Entry<K, V> findListInTable(){
        for (int i = currentIndex; i < table.length; i++){
            if (!table[i].isEmpty()){
                entryIterator = table[i].iterator();
                currentIndex = i+1;
                return entryIterator.next();
            }
        }
        return null;
    }
}
