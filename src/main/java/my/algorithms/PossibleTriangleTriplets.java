package my.algorithms;

import java.util.Arrays;

/**
 * Given an array of numbers representing the size of edges. Find the number of triplets that can form a triangle.
 */
public class PossibleTriangleTriplets {

    /**
     * brute force O(n^3)
     */
    public static int countPossibleTrianglesBruteForce(int[] edges) {
        int count = 0;

        for (int i = 0; i < edges.length - 2; i++) {
            for (int j = i + 1; j < edges.length - 1; j++) {
                for (int k  = j + 1; k < edges.length; k++) {
                    if (edges[i] + edges[j] < edges[k] || edges[i] + edges[k] < edges[j] || edges[j] + edges[k] < edges[i]) {
                        continue;
                    }
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Optimised solution <br />
     * Time complexity O(n^2log(n))
     */
    public static int countPossibleTriangles(int[] edges) {
        Arrays.sort(edges);

        int count = 0;

        for (int i = 0; i < edges.length - 2; i++) {
            for (int j = i + 1; j < edges.length - 1; j++) {
                int sum = edges[i] + edges[j];

                int maxIndex = findIndexOfElementLessThanEqual(sum, edges, j + 1, edges.length - 1);

                if (maxIndex == -1) {
                    continue;
                }

                count += maxIndex - j;
            }
        }

        return count;
    }

    public static int findIndexOfElementLessThanEqual(int e, int[] elements, int start, int end) {
        if (elements[start] > e) {
            // doesn't exist
            return -1;
        }

        int mid = (start + end) / 2;

        if (elements[mid] == e) {
            if (mid < end && elements[mid + 1] == e) {
                return findIndexOfElementLessThanEqual(e, elements, mid + 1, end);
            } else {
                return mid;
            }
        } else if ((mid == end && elements[mid] < e) || (mid < end && elements[mid] < e && elements[mid + 1] > e)) {
            return mid;
        } else if (elements[mid] > e) {
            return findIndexOfElementLessThanEqual(e, elements, start, mid - 1);
        } else {
            return findIndexOfElementLessThanEqual(e, elements, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] nums2 = {1, 100, 1000, 6542, 100, 200, 300, 1, 1, 1, 1, 1, 1, 1,3 ,3, 4,4 ,4 ,4 ,4565, 6565, 6,56,56, 5,65, 6,56, 56, 0, -1, 3, -9, -7};

        System.out.println(countPossibleTrianglesBruteForce(nums2));
        System.out.println(countPossibleTriangles(nums2));

    }
}
