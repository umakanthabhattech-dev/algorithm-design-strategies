package DP.LIS;

/*
This efficient time complexity makes the algorithm suitable for larger datasets
This is the basic intuition of the algorithm. If we make a new LIS array at every junction,
it will take up a lot of space. Now the question arises, do we need to store all these extra LIS arrays
in a data structure to keep track of all the LIS formed as we traverse the array?

The answer is No, We can maintain a single array (say temp) and rewrite this temp array again in order
to find the length of the LIS. We are concerned about the length of the LIS rather than the LIS itself.
The ‘temp’ array is not the LIS. It is a simple array we maintain to figure out the length of the LIS of the given array.
It will always have one property that the elements that are present inside it will always be sorted.
 */
//class LISBinarySearch {
//    public int lengthOfLIS(int[] nums) {
//        if (nums.length == 0) return 0;
//
//        // 'increasingSub' array stores the smallest tail of all increasing subsequences found so far.
//        int[] increasingSub = new int[nums.length + 1];
//        int currSize = 0;
//
//        for (int num : nums) {
//            // If the current number is greater than the last number in 'increasingSub', append it.
//            if (currSize == 0 || num > increasingSub[currSize]) {
//                increasingSub[++currSize] = num;
//            } else {
//                // Perform binary search to find the correct position to replace.
//                int left = 1, right = currSize;
//                System.out.println("-- start num = " + num);
//                System.out.println("left = " + left + ", right = " + right);
//                while (left < right) {
//                    int mid = (left + right) >> 1; // Equivalent to (left + right) / 2
//                    System.out.println("mid = " + mid);
//                    if (increasingSub[mid] >= num) {
//                        right = mid;
//                    } else {
//                        left = mid + 1;
//                    }
//                    System.out.println("End of the loop left = " + left + ", right = " + right);
//                    System.out.println("-- end num = " + num);
//                    System.out.println("\n");
//                }
//                // Update the 'heights' array with the current number at the correct position.
//                increasingSub[left] = num;
//            }
//        }
//        // Return the length of the longest increasing subsequence.
//        System.out.println("increasingSub " + Arrays.toString(increasingSub));
//        return currSize;
//    }
//
//    public static void main(String[] args) {
//        LISBinarySearch solution = new LISBinarySearch();
//        //int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
//        int[] nums = {1, 7, 8, 4, 5, 6, -1, 9};
//        System.out.println("Length of Longest Increasing Subsequence is " + solution.lengthOfLIS(nums));
//    }
//}
/*
logic is
If the current number is greater than the last number in 'increasingSub', append it.
Otherwise, perform binary search to find the correct position to replace.

Combining these steps, the overall time complexity can be expressed as:
Sorting: O(N log N)
Iteration with binary search: O(N log N) (since we perform a binary search for each of the N elements)
Thus, the total time complexity is:
O(NlogN)+O(NlogN)=O(NlogN)

Space = O(N)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
leetcode problem 300
 */
public class LISBinarySearch {

    public static int lengthOfLIS(int[] nums) {
        // This list will store the smallest possible tail
        // of all increasing subsequences of different lengths
        List<Integer> temp = new ArrayList<>();

        // Step 1: Put the first element
        temp.add(nums[0]);

        // Step 2: Process remaining elements
        for (int i = 1; i < nums.length; i++) {
            System.out.println("i is " + i + " with value " + nums[i]);

            // If current element is greater than the last element in temp,
            // it can extend the longest increasing subsequence
            if (nums[i] > temp.get(temp.size() - 1)) {
                temp.add(nums[i]);
            }
            // Otherwise, find the position to replace using binary search
            else {
                int idx = Collections.binarySearch(temp, nums[i]);
                System.out.println(idx + " for " + nums[i]);
                // If element not found, binarySearch returns:
                // -(insertion point) - 1
                // insertion point is the index of the first element greater than the key
                if (idx < 0) {
                    idx = -(idx + 1);
                }

                // Replace the element at idx
                System.out.println("setting index " + idx + " with value " + nums[i]);
                temp.set(idx, nums[i]);
            }
            System.out.println("current temp: " + temp);
            System.out.println("i becomes " + (i+1));
            System.out.println("-------------------------------------------------");
        }

        // Length of temp = length of LIS
        return temp.size();
    }

    // Driver code
    public static void main(String[] args) {
        int[] nums = {1, 7, 8, 4, 5, 6, -1, 9};
        System.out.println("Length of LIS = " + lengthOfLIS(nums));
    }
}
/*
Maintain an array temp where temp[i] is the smallest possible ending value of an increasing subsequence of length i+1.

For each number, if it is greater than the last element, append it to temp.

Otherwise, use binary search (lower bound) to find the first element ≥ current number and replace it.

The size of temp at the end is the length of the Longest Increasing Subsequence.
 */

/*
Among all increasing subsequences of the same length, we keep the one whose LAST number is as small as possible.
Smaller last number = more chances to add bigger numbers later

ex:
For the same height:
Staircase ending at 3 is better than one ending at 7
Why? Because you can step to 4, 5, 6… from 3
From 7, fewer options exist

 */