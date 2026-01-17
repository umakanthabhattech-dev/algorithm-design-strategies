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
 */

class TargetSum1D {
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

        /*
        s1-s2=3 target
        s1+s2=5 sum
        s1+s2 -(s1-s2) = sum-target
        2s2=sum-target
        s2=(sum-target)/2
         */
        /*
        S1 - S2 = Target
        or, S1 - (sum - S1) = Target
        or, 2S1 - sum = Target
        or, S1 = (Target + sum) / 2
         */

        // Compute the subset sum needed for one side of the partition
        int subsetSum = (sum - target) / 2;

        // Initialize a DP array to store the number of ways to reach a particular sum
        int[] dp = new int[subsetSum + 1];

        // There's one way to reach the sum of 0 - by not including any numbers
        dp[0] = 1;

        // Go through every number in nums
        for (int num : nums) {
            // Update the DP table from the end to the start to avoid overcounting
            for (int j = subsetSum; j >= num; j--) {
                // Increase the current dp value by the value from dp[j - num]
                dp[j] += dp[j - num];
            }
        }

        // Return the number of ways to reach the target sum
        return dp[subsetSum];
    }

    public static void main(String[] args) {
        TargetSum1D targetSum = new TargetSum1D();
        //int[] nums = {1, 1, 1, 1, 1};
        int[] nums = {2, 1, 2};
        //int target = 3;
        int target = -1;
        int result = targetSum.findTargetSumWays(nums, target);
        System.out.println("Number of ways to reach target: " + result); // Output: 5
    }
}
/*
Time Complexity: O(n * subsetSum)
Space Complexity: O(subsetSum)
n is the length of num array

s1 - s2 = target -1
s1+s2=total sum  5
sum-target = s1+s2-s1+s2
sum-target = 2s1
s1=sum-target/2
 */

/*
S1 – S2 = target
S1 + S2 = totalSum

2 * S1 = totalSum + target
S1 = (totalSum + target) / 2
if
I/P
[1, 2, 3, 1]
Target: 3

O/P
{2,3}
{1,3,1}

For {2,3}:
S1 = {2,3}           → sum = 5
Then S2 becomes = {1,1}           → sum = 2
Difference = 5 – 2 = 3

For {1,3,1}:
S1 = {1,3,1}         → sum = 5
S2 = {2}             → sum = 2
Difference = 5 – 2 = 3
 */