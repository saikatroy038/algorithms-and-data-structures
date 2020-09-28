package my.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a value N, if we want to make change for N cents,
 * and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins.
 * How many ways can we make the change?
 */
public class CoinChange {

    public static long getCombinationCountOptimised(int[] denomination, int amount) {
        return getCombinationCountOptimised(denomination, 0, amount, new HashMap<>());
    }

    private static long getCombinationCountOptimised(int[] denomination, int startIndex, int amount, Map<String, Long> cache) {
        long combIncluding = 0;
        int deductedAmount = amount - denomination[startIndex];
        if (deductedAmount == 0) {
            combIncluding = 1;
        } else if (deductedAmount < 0) {
            combIncluding = 0;
        } else {
            String key = startIndex + ":" + deductedAmount;
            if (cache.containsKey(key)) {
                combIncluding = cache.get(key);
            } else {
                combIncluding = getCombinationCountOptimised(denomination, startIndex, deductedAmount, cache);
                cache.put(key, combIncluding);
            }
        }

        long combExcluding = 0;
        if (startIndex != denomination.length - 1) {
            String key = (startIndex + 1) + ":" + amount;
            if (cache.containsKey(key)) {
                combExcluding = cache.get(key);
            } else {
                combExcluding = getCombinationCountOptimised(denomination, startIndex + 1, amount, cache);
                cache.put(key, combExcluding);
            }
        }

        return combExcluding + combIncluding;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 25, 30, 40, 50, 75, 100, 150, 200, 300, 500};
        int n = 1000;
        System.out.println(getCombinationCountOptimised(arr, n));
    }
}
