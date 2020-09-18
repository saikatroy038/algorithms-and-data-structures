package my.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LFU<K, V> {

    private static class WrappedDataCount<E> {
        E data;
        long count;
    }

    // TODO: impl
    private static class CountNode {
        Long count;
        CountNode next;
        CountNode prev;
    }

    private Map<K, WrappedDataCount<V>> keyValMap;
    private Map<Long, Set<V>> countValuesMap;
    private long maxSize;
    private long minCount = 0;
    private Map<Long, CountNode> countsMap;

    public LFU(long maxSize) {
        this.maxSize = maxSize;
        keyValMap = new HashMap<>();
        countValuesMap = new HashMap<>();
        countValuesMap = new HashMap<>();
    }

    /**
     * With every get, count is incremented.
     * @param key
     * @return
     */
    public V get(K key) {
        if (keyValMap.containsKey(key)) {
            WrappedDataCount<V> wrappedDataCount = keyValMap.get(key);
            countValuesMap.get(wrappedDataCount.count).remove(wrappedDataCount.data);

            if (countValuesMap.get(wrappedDataCount.count).isEmpty()) {
                countValuesMap.remove(wrappedDataCount.count);
                if (minCount == wrappedDataCount.count) {
                    minCount++;
                }
            }

            wrappedDataCount.count++;

            if (countValuesMap.containsKey(wrappedDataCount.count)) {
                Set<V> values = countValuesMap.get(wrappedDataCount.count);
                values.add(wrappedDataCount.data);
            } else {
                Set<V> values = new HashSet<>();
                values.add(wrappedDataCount.data);
                countValuesMap.put(wrappedDataCount.count, values);
            }

            return wrappedDataCount.data;
        } else {
            return null;
        }
    }

    /**
     * create or update. Count remain same.
     * @param key
     * @param value
     */
    // TODO: Impl deletion policy
    public void put(K key, V value) {
        if (keyValMap.containsKey(key)) {
            // update entry
            keyValMap.get(key).data = value;
        } else {
            // create new entry
            WrappedDataCount<V> wrappedDataCount = new WrappedDataCount<>();
            wrappedDataCount.data = value;
            wrappedDataCount.count = 0;
            keyValMap.put(key, wrappedDataCount);

            if (countValuesMap.containsKey(0l)) {
                countValuesMap.get(0l).add(value);
            } else {
                Set<V> values = new HashSet<>();
                values.add(value);
                countValuesMap.put(0l, values);
            }

            minCount = 0l;
        }
    }

    private void removeOldest() {
        // TODO: Impl
    }
}
