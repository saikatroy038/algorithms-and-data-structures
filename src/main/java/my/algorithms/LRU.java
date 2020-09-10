package my.algorithms;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Thread safe, fixed size LRU cache using LinkedHashMap
 *
 * @author github.com/saikatroy038
 */
public class LRU<K, V> extends LinkedHashMap<K, V> {

    private int maxSize;

    public LRU(int maxSize) {
        super(maxSize, 0.75f, true);
        this.maxSize = maxSize;
        Collections.synchronizedMap(this);
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() == maxSize;
    }
}
