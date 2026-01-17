package DP.Kadane;

/*
Note :
Subarray = continuous (so default in order)
Subsequence = skip allowed but in order
Increasing = strictly rising (everey next element is greater than previous one)
continuous subsequence ==  subarray (continuous)
continuous = adjacent elements -Continuous means no gaps, elements must be next to each other
subset = skip allowed and order not necessary
*/
/*
leetcode 674. Longest Continuous Increasing Subsequence
Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray).
The subsequence must be strictly increasing.

A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l],
nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].

Example 1:

Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
 */
public class LongestIncreasingSubarray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 5, 6, 7, 8, 1, 2};
        findLongestIncreasingSubarray(arr);
    }

    public static void findLongestIncreasingSubarray(int[] arr) {
        if (arr.length == 0) {
            System.out.println("Array is empty.");
            return;
        }

        int maxLength = 1;
        int currentLength = 1;
        int startIndex = 0;
        int maxStartIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStartIndex = startIndex;
                }
                currentLength = 1;
                startIndex = i;
            }
        }

        // Check for the last increasing subarray
        //But what if the array ends while you are still inside an increasing streak?
        //Then the above else block NEVER runs for the last streak.
        //So the final increasing subarray won't be checked. so we check again after the loop.
        if (currentLength > maxLength) {
            maxLength = currentLength;
            maxStartIndex = startIndex;
        }

        System.out.println("Longest increasing subarray length: " + maxLength);
        System.out.print("Subarray: ");
        for (int i = maxStartIndex; i < maxStartIndex + maxLength; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
/*
time = O(n)
space = O(1)
 */