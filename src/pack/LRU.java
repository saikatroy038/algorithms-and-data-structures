package pack;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K, V> {

    private final Map<K, V> map;
    private final int size;

    public LRU(int size) {
        this.map = new LinkedHashMap<>();
        this.size = size;
    }

    synchronized public V get(K key) {
        if (map.containsKey(key)) {
            V value = map.get(key);
            map.put(key, value); // reinserting to
        }

        return null;
    }


    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(5, 0.75f, true);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");
        map.put("1", "2");
        map.remove("3");

        System.out.println(map);
    }
}
