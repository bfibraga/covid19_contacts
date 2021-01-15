package network.dataStructures;

import network.Exceptions.NoSuchElementException;

public class HashTableIterator<K,V> implements Iterator<Entry<K, V>> {
    private Dictionary<K,V>[] table;
    private Iterator<Entry<K,V>> entryIterator;
    private int currentIndex;
    private Entry<K,V> next;
    public HashTableIterator(Dictionary<K, V>[] table) {
        this.table = table;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        Entry<K,V> toReturn = next;
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
