package my.algorithms;

/**
 * Sliding window approach
 *
 * @author github.com/saikatroy038
 */
public class MaxSumSubArrayOfFixedSize {

    public static int maxSum(int arr[], int len) {
        if (arr.length < len) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0, j = 0; i < arr.length - len && j < arr.length; ) {
            if (j != i + len) {
                sum += arr[j++];
                continue;
            }

            max = Math.max(max, sum);

            sum -= arr[i++];
            sum += arr[j++];
        }
        max = Math.max(max, sum);

        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[] {1, 2, 3, 5, 1, 9, 2, 5, 1, 1, 8, 5, 2}, 3));
    }
}
