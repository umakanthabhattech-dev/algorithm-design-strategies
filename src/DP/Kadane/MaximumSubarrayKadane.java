package DP.Kadane;

/*
leetcode 53: Maximum Subarray
Given an integer array nums, find the
subarray with the largest sum, and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

// Cubic O(n^3)
    public static int maxSubarrayCubic(int[] a) {
        int n = a.length;
        int best = Integer.MIN_VALUE;
        for (int L = 0; L < n; L++) {
            for (int R = L; R < n; R++) {
                int sum = 0;
                for (int k = L; k <= R; k++) sum += a[k];   // repeated work -> cubic
                best = Math.max(best, sum);
            }
        }
        return best;
    }

    // Quadratic O(n^2)
    public static int maxSubarrayQuadratic(int[] a) {
        int n = a.length;
        int best = Integer.MIN_VALUE;
        for (int L = 0; L < n; L++) {
            int sum = 0;
            for (int R = L; R < n; R++) {
                sum += a[R];               // reuse previous sum -> remove inner-most loop
                best = Math.max(best, sum);
            }
        }
        return best;
    }
 */
public class MaximumSubarrayKadane {

    public static int maxSubarrayKadane(int[] nums) {
        int currentMax = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }

//    public static int maxSubarrayKadane(int[] a) {
//        int best = Integer.MIN_VALUE;
//        int maxEndingHere = 0;
//        for (int v : a) {
//            // either start new at v or extend previous best
//            maxEndingHere = Math.max(v, maxEndingHere + v);
//            best = Math.max(best, maxEndingHere);
//        }
//        return best;
//    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = maxSubarrayKadane(nums);
        System.out.println("The maximum subarray sum is: " + result); // Output: 6
    }
}
/*
time Complexity: O(N), where N = size of the array.
space Complexity: O(1)
 */
/*
Explanation:
one subproblem per index:
maxEndingHere[i] = best possible subarray sum that ends exactly at index i
1.Optimal substructure means ?:
The best answer for a bigger segment depends on the best answer for a smaller segment.
At index i, we have two choices:
“Choice #1: start a new sub-array at this item.
Choice #2: extend the maximum sub-array coming before us.”
This uses:
A smaller optimal subproblem → maxEndingHere[i-1]
To compute a larger subproblem → maxEndingHere[i]
2.overlapping subproblems exist?
This is reused again and again when computing:
maxEndingHere[i]
maxEndingHere[i+1]
etc.
You are not recalculating from scratch; you reuse the previous results.

“This is the essence of dynamic programming… understanding previous subproblems so we can understand the best answer
to our current subproblem.”

3.It is perfect DP because:

✔ It defines a clear subproblem at each index

✔ It builds each answer using previously computed results (optimal substructure)

✔ It avoids recomputation (overlapping subproblems)

✔ It guarantees exploring all possible valid candidates for the optimal solution
 */