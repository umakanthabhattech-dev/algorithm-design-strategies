package Searching;

/*
Optimal Solution : Hard
Best youtube 1 : https://www.youtube.com/watch?v=_TCw4LXpKq0
Second Best : https://www.youtube.com/watch?v=F9c7LpRZWVQ
Leetcode 4. Median of Two Sorted Arrays
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

    Optimal approach: Use binary search to partition the arrays
                      and find median in O(log(min(m,n))) time and O(1) space.
 */
public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0, high = m;

        while (low <= high) {

            // Partition positions
            int cut1 = (low + high) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;

            // Left and right boundary elements
            int left1  = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2  = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            // Check if correct partition
            if (left1 <= right2 && left2 <= right1) {

                // Odd total length
                if ((m + n) % 2 == 1) {
                    return Math.max(left1, left2);
                }
                // Even total length
                else {
                    return (Math.max(left1, left2) +
                            Math.min(right1, right2)) / 2.0;
                }
            }
            // Move left in nums1
            else if (left1 > right2) {
                high = cut1 - 1;
            }
            // Move right in nums1
            else {
                low = cut1 + 1;
            }
        }

        // Should never reach here for valid input
        return 0.0;
    }

    // Sample test
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        System.out.println(findMedianSortedArrays(nums1, nums2)); // Output: 2.0
    }
}

