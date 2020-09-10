package my.algorithms;

/**
 * Recursive brute force approach for 0/1 Knapsack problem with unlimited supplies.
 *
 * @author github.com/saikatroy038
 */
public class KnapsackUnlimitedSupply {

    private static class Item {
        int weight;
        int profit;

        public Item(int weight, int profit) {
            this.weight = weight;
            this.profit = profit;
        }
    }


    public static int maxProfit(int[] weights, int[] profits, int capacity) {
        Item[] items = new Item[weights.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(weights[i], profits[i]);
        }

        return maxProfit(items, 0, items.length - 1, capacity);
    }


    private static int maxProfit(Item[] items, int start, int end, int capacity) {
        for (int i = start; i <= end; i++) {
            if (items[i].weight > capacity) {
                continue;
            } else {
                int profitWithMax = items[i].profit + maxProfit(items, i, end, capacity - items[i].weight);
                int profitWithoutMax = maxProfit(items, i + 1, end, capacity);
                return Math.max(profitWithMax, profitWithoutMax);
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        int val[] = new int[] { 60, 100, 120 };
        int wt[] = new int[] { 10, 20, 30 };
        int W = 50;

        System.out.println(maxProfit(wt, val, W));
    }
}
