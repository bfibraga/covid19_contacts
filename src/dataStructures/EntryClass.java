package dataStructures;

public class EntryClass<K, V> implements Entry<K, V>{

    private K key;
    private V value;

    public EntryClass(K key, V value){
        this.key = key;
        this.value = value;
    }
    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    protected void setValue(V newValue){ value = newValue;}

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Entry){
            Entry<K, V> other = (Entry<K, V>) obj;
            return this.getKey().equals(other.getKey());
        }
        return false;
    }
}
