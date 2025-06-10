package LLDProjects.HashMapImplementation;
class Entry<K, V> {
    K key;
    V value;
    Entry next;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
public class HashMapImpl<K, V> {

    private static int INITIAL_SIZE = 1 << 4;
    private static int MAX_CAPACITY = 1 << 30;

    Entry[] hashtable;

    HashMapImpl() {
        hashtable = new Entry[INITIAL_SIZE];
    }

    HashMapImpl(int cap) {
        hashtable = new Entry[findCap(cap-1)];
    }

    private int findCap(int cap) {
        int n = cap - 1;
        n = n | n >>> 1;
        n = n | n >>> 2;
        n = n | n >>> 4;
        n = n | n >>> 8;
        n = n | n >>> 16;

        if ( n < 0)
            return 1;
        else if (n >= MAX_CAPACITY)
            return MAX_CAPACITY;
        else
            return n + 1;
    }

    public void put(K key, V value) {
        int hashcode = key.hashCode() % hashtable.length;
        Entry node = hashtable[hashcode];

        if(node == null) {
            hashtable[hashcode] = new Entry(key, value);
        }
        else {
            Entry prevNode = node;

            while(node != null) {
                if(node.getKey() == key) {
                    node.value = value;
                    hashtable[hashcode] = node;
                }
                else {
                    prevNode = node;
                    node = node.next;
                }
            }

            prevNode.next = new Entry(key, value);
        }
    }

    public V get(K key) {
        int hashcode = key.hashCode() % hashtable.length;
        Entry node = hashtable[hashcode];

        if(node == null)
            return null;

        while(node != null) {
            if(node.getKey() == key)
                return (V) node.getValue();
            node = node.next;
        }

        return null;
    }

}
