package my.algorithms;

import java.util.TreeSet;

public class MaxInEachSubArraysOfFixedSize {

    /**
     * Brut force approach. <br />
     * Time complexity O(n * k) <br />
     * @param ar array
     * @param k length of subArray
     * @return array with maxElements in each subArray in order
     */
    public static int[] maxInSubArraysBruteForce(int[] ar, int k) {
        if (ar.length < k) {
            throw new RuntimeException("Invalid parameters");
        }

        int[] maxElements = new int[ar.length - k + 1];
        for (int i = 0; i <= ar.length - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, ar[j]);
            }
            maxElements[i] = max;
        }
        return maxElements;
    }

    public static int[] maxInSubArraysUsingBST(int[] ar, int k) {
        if (ar.length < k) {
            throw new RuntimeException("Invalid parameters");
        }

        int[] maxElements = new int[ar.length - k + 1];
        TreeSet<Integer> nums = new TreeSet<>();

        for (int i = 0, j = 0; i < ar.length; i++) {
            if (nums.size() < 3) {
                nums.add(ar[i]);
                continue;
            }

            maxElements[j++] = nums.last();
            nums.remove(ar[i - k]);

            nums.add(ar[i]);
        }
        maxElements[maxElements.length - 1] = nums.last();

        return maxElements;
    }

    public static void main(String[] args) {
        int[] maxElements = maxInSubArraysBruteForce(new int[] {1, 6, 3, 2, -1, 8, 9, 4, 1, 0, -1}, 3);
        for (int i : maxElements) {
            System.out.print(i + " ");
        }

        System.out.println();

        maxElements = maxInSubArraysUsingBST(new int[] {1, 6, 3, 2, -1, 8, 9, 4, 1, 0, -1}, 3);
        for (int i : maxElements) {
            System.out.print(i + " ");
        }
    }
}
