package DP.Kadane;

/*https://medium.com/@prnv1009/solving-maximum-alternating-sum-problem-using-recursion-and-memoization-6077633f19d4*/
/*check*/

//bottom-up DP approach similar to Kadane's algorithm
/*
LeetCode 1911 — Maximum Alternating Subsequence Sum
Pick any subsequence of the given array so that when you compute + - + - + ... on it, the result is maximum
The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of the elements at odd indices.

For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
Given an array nums, return the maximum alternating sum of any subsequence of nums
 */
public class MaxAlternatingSum {

    // ===== LeetCode 1911 Solution =====
    public static long maxAlternatingSum(int[] nums) {

        long even = 0; // best alternating sum if next chosen element will be added
        long odd = 0;  // best alternating sum if next chosen element will be subtracted

        for (int x : nums) {
            //take it as not even or odd
            //if x is not taken then consider previous even
            //if x is even then consider previous as odd and add current
            long newEven = Math.max(even, odd + x);

            //if x is odd then consider previous as even and subtract current
            long newOdd = newEven - x;
            //can write long newOdd = Math.max(odd, even - x)
            //mathamatically both are same - newEven - x = max(even, odd + x) - x

            even = newEven;
            odd = newOdd;
        }

        return even;  // always the best final alternating subsequence sum
    }

    // ===== Main method to test =====
    public static void main(String[] args) {

        int[] nums = {4, 2, 5, 3};
        long result = maxAlternatingSum(nums);

        System.out.println("Maximum Alternating Subsequence Sum = " + result);
    }
}
//New 2nd approach
//add bottum up using dp array


//public class MaxAlternatingSum {
//
//    // -------- Bottom-Up DP (dp[i][0], dp[i][1]) --------
//    public static long maxAlternatingSum(int[] nums) {
//
//        int n = nums.length;
//        long[][] dp = new long[n][2];
//
//        // Base initialization
//        dp[0][0] = nums[0];      // taking nums[0] as even index (+)
//        dp[0][1] = -nums[0];     // taking nums[0] as odd index  (-)
//
//        // Fill DP table bottom-up
//        for (int i = 1; i < n; i++) {
//           // For dp[i][0],
//           //we can take nums[i] as even index (add nums[i] to dp[i-1][1] previously odd)
//           // or skip nums[i] as even (carry forward dp[i-1][0] as consider previous as even)


//            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + nums[i]);


//            // For dp[i][1],
//            //if we take nums[i] as odd index (subtract nums[i] from dp[i-1][0] which is previously even)
             //we can skip nums[i] as odd (carry forward dp[i-1][1] as consider previous as odd)

//            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - nums[i]);
//        }
//
//        return dp[n-1][0];  // best alternating sum always ends in even state
//        //if we end at odd state, it means we subtracted last element, which is not optimal
//    }
//
//    // -------- Main method for testing --------
//    public static void main(String[] args) {
//
//        // Sample input
//        int[] nums = {4, 2, 5, 3};
//
//        long result = maxAlternatingSum(nums);
//
//        System.out.println("--Maximum Alternating Subsequence Sum = " + result);
//    }
//}
//time complexity: O(n)
//space complexity: O(n*2)








//public class MaxAlternatingSum {
//    public long maxAltSum(int[] nums) {
//        int n = nums.length;
//        Long[][] memo = new Long[n][2];
//        for (int i = 0; i < n; i++) Arrays.fill(memo[i], null);
//        return solve(nums, 0, n, false, memo);
//    }
//
//    public static long solve(int[] nums, int index, int n, boolean signneg, Long[][] memo) {
//        if (index >= n) return 0;
//        if (memo[index][signneg ? 1 : 0] != null) return memo[index][signneg ? 1 : 0];
//        //skip
//        long skip = solve(nums, index + 1, n, signneg, memo);
//        //take
//        long value = nums[index];
//        if (signneg) {
//            value = -1 * value;
//        }
//        long take = value + solve(nums, index + 1, n, !signneg, memo);
//        memo[index][signneg ? 1 : 0] = Math.max(skip, take);
//        return memo[index][signneg ? 1 : 0];
//    }
//    public static void main(String[] args) {
//        MaxAlternatingSum solution = new MaxAlternatingSum();
//
//        // Example input
//        int[] nums = {4, 2, 5, 3};
//
//        // Calculate the maximum alternating sum
//        long result = solution.maxAltSum(nums);
//
//        // Output the result
//        System.out.println("Maximum Alternating Sum: " + result);
//    }
//}
//time complexity: O(n*2)
//space complexity: O(n*2) + O(n) for recursion stack
