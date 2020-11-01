package my.algorithms;

import java.util.HashMap;
import java.util.Map;

public class MaxProductRodCutting {

    public static long maxProductCuts(int size) {
        return maxProductCuts(size, true, new HashMap<>());
    }

    private static long maxProductCuts(int size, boolean cutMandatory, Map<Integer, Long> cache) {
        if (cache.containsKey(size)) {
            return cache.get(size);
        }

        long maxProduct = 1;
        int limit = (cutMandatory) ? size - 1 : size;
        for (int i = 1; i <= limit; i++) {
            maxProduct = Math.max(maxProduct, i * maxProductCuts(size - i, false, cache));
        }

        cache.put(size, maxProduct);
        return maxProduct;
    }

    public static void main(String[] args) {
        System.out.println(maxProductCuts(100));
    }
}
