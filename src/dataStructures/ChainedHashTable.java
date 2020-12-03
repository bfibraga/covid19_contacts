package dataStructures;  

/**
 * Chained Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	//The array of dictionaries.
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public ChainedHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new CollisionList<K,V>();
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }


    @Override
    public V find(K key) {
        return table[hash(key)].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            this.rehash();

        V oldValue = table[hash(key)].insert(key, value);
        if (oldValue == null) currentSize++;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int arraySize = HashTable.nextPrime((int) (1.1 * currentSize));
        Iterator<Entry<K,V>> it = iterator();
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new CollisionList<K,V>();
        maxSize = arraySize;

        while (it.hasNext()){
            Entry<K,V> entry = it.next();
            table[hash(entry.getKey())].insert(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(K key) {
        V value = table[hash(key)].remove(key);
        if (value != null) currentSize--;
        return value;
    }


    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        return new HashTableIterator<>(table);
    }

    @Override
    public Iterator<V> iteratorValue() {
        return new HashTableValueIterator<>(table);
    }

}
































