package my.algorithms;

import java.util.HashMap;
import java.util.Map;

public class RodCutting {

    public static int maxProfit(int[] profits, int size) {
        return maxProfit(profits, size, 0, new HashMap<>());
    }

    private static int maxProfit(int[] profits, int size, int includeFrom, Map<String, Integer> cache) {
        if (size == 0) {
            return 0;
        } else if (includeFrom >= profits.length || size < 0) {
            return -1; // not possible flag
        }

        String key = includeFrom + ":" + size;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int profitIncludingCurrent = maxProfit(profits, size - includeFrom - 1, includeFrom, cache) + profits[includeFrom];
        int profitExcludingCurrent = maxProfit(profits, size, includeFrom + 1, cache);

        int maxProfit = 0;
        if (profitIncludingCurrent < profits[includeFrom]) {
            maxProfit = profitExcludingCurrent;
        } else if (profitExcludingCurrent < 0) {
            maxProfit = profitIncludingCurrent;
        } else {
            maxProfit = Math.max(profitExcludingCurrent, profitIncludingCurrent);
        }

        cache.put(key, maxProfit);
        return maxProfit;
    }

    public static void main(String[] args) {
        int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(maxProfit(arr, arr.length));
    }
}
