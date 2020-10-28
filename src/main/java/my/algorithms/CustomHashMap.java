package my.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Iterable
public class CustomHashMap<K, V> {

    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static double loadFactor = 0.75;

    private int size;
    private List<LinkedList<Node<K, V>>> buckets;
    private int capacity;

    public CustomHashMap() {
        this.size = 0;
        this.capacity = 16;

        this.buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            this.buckets.add(null);
        }
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    public V get(K key) {
        int hashVal = hash(key);
        LinkedList<Node<K, V>> bucket = buckets.get(hashVal);

        if (bucket == null || bucket.isEmpty()) {
            return null;
        }

        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    public void put(K key, V value) {
        if (full()) {
            resize();
        }

        int hashVal = hash(key);

        LinkedList<Node<K, V>> bucket = buckets.get(hashVal);
        if (bucket == null) {
            bucket = new LinkedList<>();
            bucket.add(new Node<>(key, value));
            buckets.add(hashVal, bucket);
        } else {
            for (Node<K, V> node : bucket) {
                if (node.key.equals(key)) {
                    // if exist then replace
                    node.value = value;
                    return;
                }
            }
            bucket.add(new Node<>(key, value));
        }

        this.size++;
    }

    public int size() {
        return this.size;
    }

    private boolean full() {
        if (loadFactor <= (double) this.size / (double) this.capacity) {
            return true;
        } else {
            return false;
        }
    }

    private void resize() {
        this.capacity = this.capacity * 2;
        List<LinkedList<Node<K, V>>> resizedBuckets = new ArrayList<>();
        for (int i = 0; i < this.capacity; i++) {
            resizedBuckets.add(null);
        }

        for (LinkedList<Node<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Node<K, V> node : bucket) {
                    int hashVal = hash(node.key);
                    if (resizedBuckets.get(hashVal) == null) {
                        resizedBuckets.add(hashVal, new LinkedList<>());
                    }
                    resizedBuckets.get(hashVal).add(node);
                }
            }
        }

        buckets = resizedBuckets;
    }


    public static void main(String[] args) {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "b");
        map.put("1", "d");

        for (int i = 0; i < 100; i++) {
            map.put("k" + i, "v" + i);
        }

        System.out.println(map.get("a")); // null
        System.out.println(map.get("1")); // d
        System.out.println(map.get("3")); // b
    }
}
