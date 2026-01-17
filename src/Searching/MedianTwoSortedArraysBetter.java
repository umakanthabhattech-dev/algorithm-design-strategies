package Searching;

/*
leetcode 4. Median of Two Sorted Arrays
    Given two sorted arrays nums1 and nums2 of size m and n respectively,
    return the median of the two sorted arrays.

    The overall run time complexity should be O(log (m+n)).
    You may assume nums1 and nums2 cannot be both empty.

    Example 1:
    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
    Example 2:
    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

    Bruitforce approach: Merge two sorted arrays and find median and find median but it takes O(m+n) time
                         and O(m+n) space.
    Better approach: Use two pointers to traverse both arrays
                     and find median in O(m+n) time and O(1) space.
    For optimal approach refer MedianTwoSortedArraysOptimal.java using
    binary search whose time complexity is O(log(min(m,n))) and space complexity is O(1).

    Here we use better approach not storing merged array just finding median while merging.

 */
public class MedianTwoSortedArraysBetter {

    public static double findMedian(int[] a, int[] b) {

        int n1 = a.length, n2 = b.length;
        int n = n1 + n2;

        int idx1 = n / 2;
        int idx2 = idx1 - 1;

        int i = 0, j = 0, count = 0;
        int elem1 = -1, elem2 = -1;

        while (i < n1 && j < n2) {
            int val;
            if (a[i] <= b[j]) {
                val = a[i++];
            } else {
                val = b[j++];
            }

            if (count == idx2) elem1 = val;
            if (count == idx1) elem2 = val;
            count++;
        }

        while (i < n1) {
            int val = a[i++];
            if (count == idx2) elem1 = val;
            if (count == idx1) elem2 = val;
            count++;
        }

        while (j < n2) {
            int val = b[j++];
            if (count == idx2) elem1 = val;
            if (count == idx1) elem2 = val;
            count++;
        }

        if (n % 2 == 0) {
            return (elem1 + elem2) / 2.0;
        }
        return elem2;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 6, 7, 10};
        int[] b = {2, 3, 12, 15};

        System.out.println(findMedian(a, b)); // Output: 5.0
    }
}
/*
Time complexity: O(n1 + n2), where n1 and n2 are the lengths of the two input arrays.
Space complexity: O(1)

 */

