package my.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Maximum number of sub-arrays with sum less than given value. <br />
 *
 * Note: the code has a bug which needs to be fixed.
 *
 * @author github.com/saikatroy038
 */
public class SubArraysSumLessThan {

    public static int countSubArraysSumLessThan(int arr[], int start, int end, int k) {
        // base condition
        if (start == end) {
            if (arr[start] < k) {
                return 1;
            } else {
                return 0;
            }
        }

        int mid = (start + end) / 2;

        int count1stHalf = countSubArraysSumLessThan(arr, start, mid, k);
        int count2ndHalf = countSubArraysSumLessThan(arr, mid + 1, end, k);

        int sum = 0;
        Queue<Integer> sums = new LinkedList<>();
        for (int i = mid; i >= start; i--) {
            sum += arr[i];

            if (sum >= k) {
                break;
            } else {
                sums.add(sum);
            }
        }

        sum = arr[mid + 1];
        int countCommon = 0;
        for (int i = mid + 1; ; ) {

            if (sums.isEmpty()) {
                break;
            } else if (sum + sums.peek() < k) {
                countCommon++;
                i++;
                if (i > end) {
                    sums.remove();
                    i = mid + 1;
                }
                sum += arr[i];
            } else {
                sums.remove();
                i = mid + 1;
            }
        }

        return  count1stHalf + count2ndHalf + countCommon;
    }

    public static int countSubArraysWithSumLessThan(int[] arr, int k) {
        if (arr == null) {
            return 0;
        }
        return countSubArraysSumLessThan(arr, 0, arr.length - 1, k);
    }

    public static void main(String[] args) {
        System.out.println(bruteForce(new int[] { 1, 1, 6, 1, 1}, 10));

        int array[] = { 9, 9, 9, 9, 9 };
        int k = 10;
        int size = array.length;
        int count = countSubarray(array, k);
        System.out.println(count);
    }

    public static int bruteForce(int[] arr, int k) {
        int count = 0;
        upperLoop:
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum < k) {
                    count++;
                } else {
                    break;
                }
            }
        }

        return count;
    }

    // Function to find number
    // of subarrays having sum
    // less than k.
    static int countSubarray(int arr[], int k) {
        int start = 0, end = 0;
        int count = 0, sum = arr[0];

        while (start < arr.length && end < arr.length) {

            if (sum < k) {
                end++;

                if (end >= start)
                    count += end - start;

                if (end < arr.length)
                    sum += arr[end];
            }

            else {
                sum -= arr[start];
                start++;
            }
        }

        return count;
    }
}
