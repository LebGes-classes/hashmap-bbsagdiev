import java.util.ArrayList;
import java.util.List;

class KeyValuePair<K, V> {
    private final K key;
    private V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

public class HashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private List<List<KeyValuePair<K, V>>> buckets;
    private int size;

    public HashMap() {
        buckets = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets.add(null);
        }
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.size());
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (buckets.get(index) == null) {
            buckets.set(index, new ArrayList<>());
        }
        List<KeyValuePair<K, V>> bucket = buckets.get(index);
        for (KeyValuePair<K, V> pair : bucket) {
            if (pair.getKey().equals(key)) {
                pair.setValue(value);
                return;
            }
        }
        bucket.add(new KeyValuePair<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(index);
        if (bucket != null) {
            for (KeyValuePair<K, V> pair : bucket) {
                if (pair.getKey().equals(key)) {
                    return pair.getValue();
                }
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(index);
        if (bucket != null) {
            for (KeyValuePair<K, V> pair : bucket) {
                if (pair.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (List<KeyValuePair<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (KeyValuePair<K, V> pair : bucket) {
                    if (pair.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void remove(K key) {
        int index = hash(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(index);
        if (bucket != null) {
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).getKey().equals(key)) {
                    bucket.remove(i);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("cat", 5);
        hashMap.put("dog", 7);
        System.out.println(hashMap.get("cat"));  // Выведет: 5
        System.out.println(hashMap.containsKey("dog"));  // Выведет: true
        System.out.println(hashMap.containsValue(7));  // Выведет: true
        hashMap.remove("dog");
        System.out.println(hashMap.containsKey("dog"));  // Выведет: false
        System.out.println(hashMap.size());  // Выведет: 1
    }
}
