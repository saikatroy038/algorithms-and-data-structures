package my.algorithms;

/**
 * 0/1 Knapsack problem. <br />
 *
 * @author github.com/saikatroy038
 */
public class Knapsack01Optimised {

    private static class Item {
        int weight;
        int profit;

        public Item(int weight, int profit) {
            this.weight = weight;
            this.profit = profit;
        }
    }


    private static int maxProfitRecur(int[] weights, int[] profits, int capacity) {
        Item[] items = new Item[weights.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(weights[i], profits[i]);
        }

        return maxProfit(items, 0, capacity);
    }

    /**
     * Using brute force. <br />
     *
     * <b> Should be used when w.length << capacity. </b> <br/>
     *
     * time complexity O(2^n). n -> w.length <br/>
     * space complexity O(1) <br />
     */
    private static int maxProfit(Item[] items, int start, int capacity) {
        for (int i = start; i < items.length; i++) {
            if (items[i].weight > capacity) {
                continue;
            } else {
                int profitWithMax = items[i].profit + maxProfit(items, i + 1, capacity - items[i].weight);
                int profitWithoutMax = maxProfit(items, i + 1, capacity);
                return   Math.max(profitWithMax, profitWithoutMax);
            }
        }
        return 0;
    }

    /**
     *  Code to decide whether to use dynamic programming or brute force.
     */
    public static int maxProfit(int[] w, int[] p, int capacity) {
        long stepsRecur = (long) Math.pow(2, w.length);
        long stepsDp = w.length * capacity;

        if (stepsRecur <= stepsDp) {
            return maxProfitRecur(w, p, capacity);
        } else {
            return maxProfitDp(w, p, capacity);
        }
    }

    /**
     * Using dynamic programming. <br />
     *
     * <b> Should be used when w.length >> capacity. </b> <br/>
     *
     * time complexity O(capacity * w.length) <br/>
     * space complexity O(capacity * w.length) <br />
     */
    private static int maxProfitDp(int[] w, int[] p, int capacity) {
        int[][] profits = new int[w.length + 1][capacity + 1];

        for (int i = 0; i <= w.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    profits[i][j] = 0;
                } else if (w[i - 1] <= j) {
                    profits[i][j] = Math.max(profits[i - 1][j],
                            p[i - 1] + profits[i - 1][j - w[i - 1]]);
                } else {
                    profits[i][j] = profits[i - 1][j];
                }
            }
        }

        return profits[w.length][capacity];
    }

    public static void main(String[] args) {
        int val[] = new int[] { 60, 100, 120, 150, 200, 300, 500, 1000, 1100, 1211 };
        int wt[] = new int[] { 10, 20, 30, 40, 50, 80, 100, 300, 350, 353 };
        int W = 100;

        System.out.println(maxProfit(wt, val, W));
    }


}
