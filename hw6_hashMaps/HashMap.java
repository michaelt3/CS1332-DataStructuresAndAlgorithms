import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 *
 * @author Michael Troughton
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            String error = "The key you are trying to add is a value of null";
            throw new IllegalArgumentException(error);
        }
        if (value == null) {
            String error = "The value you are trying to add is a value of null";
            throw new IllegalArgumentException(error);
        }

        // Resize if capacity is >= 0.67
        double num = (double) (size + 1) / (table.length);
        if (num >= MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }

        int hash = Math.abs(key.hashCode()) % table.length;
        V oldValue = null;
        // int quad used for quadratic probing
        int quad = 1;
        // bool addYet is used to see if the value has been added into array yet
        boolean deletedMarker = false;
        // int saved is used as a marker for DELETED
        int saved = 0;
        int temp = hash;
        while (true) {
            for (int i = 0; i < table.length; i++) {
                if (table[hash] == null) {
                    if (!deletedMarker) { // Only insert if no marker
                        table[hash] = new MapEntry<K, V>(key, value);
                        size++;
                        return null;
                    }
                } else if (table[hash].isRemoved() && !deletedMarker) {
                    saved = hash;
                    deletedMarker = true;
                } else if (table[hash].getKey().equals(key)) {
                    oldValue = table[hash].getValue();
                    table[hash].setValue(value);
                    return oldValue;
                }
                hash = (temp + quad * quad++) % table.length;
            }
            // Probed through all values and all were full except for the marker
            if (deletedMarker) {
                table[saved] = new MapEntry<K, V>(key, value);
                size++;
                return oldValue;
            }
            resizeBackingTable(2 * table.length + 1);
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            String error = "Your input to remove is 'null'. Cannot remove null";
            throw new IllegalArgumentException(error);
        }
        int i = Math.abs(key.hashCode()) % table.length;
        int quad = 1;
        int count = 0;
        int temp = i;
        // Loop table.length times
        while (count < table.length) {
            if (table[i] != null && table[i].getKey().equals(key)
                && !table[i].isRemoved()) {
                table[i].setRemoved(true);
                size--;
                return table[i].getValue();
            }
            i = (temp + quad * quad++) % table.length;
            count++;
        }
        String error = "The key you are looking for does not exist in HashMap";
        throw new java.util.NoSuchElementException(error);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            String error = "You are trying to get a null key. Throwing error.";
            throw new IllegalArgumentException(error);
        }
        int i = Math.abs(key.hashCode()) % table.length;
        int quad = 1;
        int count = 0;
        int temp = i;
        // Loop table.length times
        while (count < table.length) {
            if (table[i] != null && table[i].getKey().equals(key)
                && !table[i].isRemoved()) {
                return table[i].getValue();
            }
            i = (temp + quad * quad++) % table.length;
            count++;
        }
        String error = "The key you are trying to get does not exist.";
        throw new java.util.NoSuchElementException(error);
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            String error = "You are trying to get a null key. Throwing error.";
            throw new IllegalArgumentException(error);
        }
        int i = Math.abs(key.hashCode()) % table.length;
        int quad = 1;
        int count = 0;
        int temp = i;
        // Loop table.length times
        while (count < table.length) {
            if (table[i] != null && table[i].getKey().equals(key)
                && !table[i].isRemoved()) {
                return true;
            }
            i = (temp + quad * quad++) % table.length;
            count++;
        }
        return false;
    }

    @Override
    public void clear() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyset = new java.util.HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                keyset.add(table[i].getKey());
            }
        }
        return keyset;
    }

    @Override
    public List<V> values() {
        List<V> list = new java.util.LinkedList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                list.add(table[i].getValue());
            }
        }
        return list;
    }

    @Override
    public void resizeBackingTable(int length) {
        MapEntry[] oldTable = table;
        table = new MapEntry[length];
        size = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && !oldTable[i].isRemoved()) {
                put((K) oldTable[i].getKey(), (V) oldTable[i].getValue());
            }
        }
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
