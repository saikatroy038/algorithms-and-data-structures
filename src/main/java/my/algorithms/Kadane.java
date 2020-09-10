package my.algorithms;



/**
 * Kadane's algorithm to find maximum sum sub-array in an array.  <br />
 * O(n) time complexity. <br />
 * O(1) space complexity. <br />
 *
 * @author github.com/saikatroy038
 */
public class Kadane {

    public static int[] maxSumInSubArray(int[] arr) {
        int start = 0;
        int end = 0;

        int max = Integer.MIN_VALUE;

        int prevMax = Integer.MIN_VALUE;
        int prevMaxStart = 0;

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            if (prevMax > 0) {
                prevMax += num;
            } else {
                prevMax = num;
                prevMaxStart = i;
            }

            if (prevMax > max) {
                max = prevMax;
                start = prevMaxStart;
                end = i;
            }
        }

        int[] maxSumSubArray = new int[end - start + 1];
        for (int i = start, j = 0; i <= end; i++, j++) {
            maxSumSubArray[j] = arr[i];
        }

        return maxSumSubArray;
    }

    public static void main(String[] args) {
        int nums[] = new int[] {-2, -3, -4, 3, -1, 1, -2, 4};
        nums = maxSumInSubArray(nums);

        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}
