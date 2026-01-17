package DP.ZeroOneKnapsack;

/*
You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before
each integer in nums and then concatenate all the integers.
Return the number of different expressions that you can build, which evaluates to target.
Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

or
For example, we have an array [2, 1, 2] and target=-1
+2+1+2=5
+2−1+2=3
−2+1+2=1
−2−1+2=−1
+2+1−2=1
+2−1−2=−1
−2+1−2=−3
−2−1−2=−5
answer=2
 */
class TargetSum2D {
    public int findTargetSumWays(int[] nums, int target) {
        // Initialize the sum of all numbers in nums
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If the sum is less than the target or (sum - target) is odd, it's not possible to partition
        if (sum < target || (sum - target) % 2 != 0) {
            return 0;
        }
        // Compute the subset sum needed for one side of the partition
        int subsetSum = (sum - target) / 2;

        // Initialize a 2D DP array
        int[][] dp = new int[nums.length + 1][subsetSum + 1];

        // There's one way to reach the sum of 0 - by not including any numbers
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= subsetSum; j++) {
                // If the current number is less than or equal to the current sum
                if (nums[i - 1] <= j) {
                    // Include the number or exclude it
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    // Exclude the number
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Return the number of ways to reach the target sum
        return dp[nums.length][subsetSum];
    }

    public static void main(String[] args) {
        TargetSum2D targetSum = new TargetSum2D();
        int[] nums = {2, 1, 2};
        int target = -1;
        int result = targetSum.findTargetSumWays(nums, target);
        System.out.println("Number of ways to reach target: " + result);
    }
}
/*
for {2, 1, 2}
target -1;
+2+1+2=5
+2−1+2=3
−2+1+2=1
−2−1+2=−1 --
+2+1−2=1
+2−1−2=−1 --
−2+1−2=−3
−2−1−2=−5
Final dp
        0  1  2  3
0    0 [1, 0, 0, 0]
2    1 [1, 0, 1, 0]
1    2 [1, 1, 1, 1]
2    3 [1, 1, 2, 2]
Time Complexity: O(n * subsetSum) or O(n * sum) in the worst case.
Space Complexity: O(n * subsetSum) or O(n * sum) in the worst case.
s1 - s2 = target -1
s1+s2=total sum  5
sum-target = s1+s2-s1+s2
sum-target = 2s1
s1=sum-target/2
 */
