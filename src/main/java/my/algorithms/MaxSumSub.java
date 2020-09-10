package my.algorithms;

/**
 * Max sum of sub-array in an array using divide and conquer. <br />
 * time complexity O(nlog(n)) <br />
 *
 * Kadane's algorithm provides better complexity.
 *
 * @author github.com/saikatroy038
 */
public class MaxSumSub {

    public static int maxSumOfSubArray(int[] nums, int start, int end) {
        // base condition
        if (start == end) {
            return nums[start];
        }

        int mid = (start + end) / 2;

        // Finding max sum subArray including mid element
        int maxSumIncludeMid = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= start; i--) {
            sum += nums[i];
            maxSumIncludeMid = Math.max(sum, maxSumIncludeMid);
        }

        // Finding max sum subArray including (mid + 1)th element
        int maxSumIncludeMidPlus1 = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += nums[i];
            maxSumIncludeMidPlus1 = Math.max(sum, maxSumIncludeMidPlus1);
        }

        // max sum of subArray going through the mid
        int maxIncludingMid = maxSumIncludeMid + maxSumIncludeMidPlus1;

        int maxIn1stHalf = maxSumOfSubArray(nums, start, mid);
        int maxIn2ndHalf = maxSumOfSubArray(nums, mid + 1, end);

        // max of (maxIn1stHalf, maxIn2ndHalf, maxSumIncludeMid)
        return Math.max(Math.max(maxIn1stHalf, maxIn2ndHalf), maxIncludingMid);
    }

    public static void main(String[] args) {
        int nums[] = new int[] {-2, -3, -4, 3, -1, 1, -2, 4};
        System.out.println(maxSumOfSubArray(nums, 0, nums.length - 1));
    }
}
