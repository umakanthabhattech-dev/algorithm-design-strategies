package DP.recursive;

/*
leetcode problem 198 : House Robber
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses
have security systems connected and it will automatically contact the police if two adjacent houses
were broken into on the same night.

Given an integer array nums representing the amount of money of each house,
return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

 */
public class HouseRobber {

    int rob(int[] nums) {
        // If only 1 element, just return it
        if (nums.length < 2)
            return nums[0];

        // IMP : Create array to store the maximum loot at each index
        int[] dp = new int[nums.length];

        // Memoize maximum loots at first 2 indexes
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]); // Max loot at index 1 can be either robbing house 0 or house 1

        // Use them to fill complete array
        for (int i = 2; i < nums.length; i++) {
            // Core logic
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();

        // Example test case
        int[] houses = {2, 7, 3, 1, 4, 2, 1,8};
        int maxLoot = robber.rob(houses);

        // Print the result
        System.out.println("Maximum loot possible: " + maxLoot);
    }
}
/*
Time complexity = O(n)
Space complexity = O(n)
 */
/* More space optimization to O(1)
 int rob(int[] nums) {
        // If there are no houses, return 0
        if (nums.length == 0) return 0;
        // If only 1 house, return its value
        if (nums.length == 1) return nums[0];

        // Variables to store the maximum loot up to the previous two houses
        int prev1 = 0; // Max loot up to house i-1
        int prev2 = 0; // Max loot up to house i-2

        for (int num : nums) {
            // Calculate the maximum loot for the current house
            int temp = prev1; // Store the previous max loot
            prev1 = Math.max(prev2 + num, prev1); // Update prev1 to the new max
            prev2 = temp; // Move prev1 to prev2 for the next iteration
        }
        return prev1; // The last computed max loot
    }
 For  2D array DP approach
    class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[][] dp = new int[n][2];

        // Base case
        dp[0][0] = 0;         // not rob first house
        dp[0][1] = nums[0];   // rob first house

        for (int i = 1; i < n; i++) {
            // If we do NOT rob house i
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);

            // If we DO rob house i
            dp[i][1] = nums[i] + dp[i-1][0];
        }

        // ------- PRINT DP ARRAY -------
        System.out.println("DP Array:");
        for (int i = 0; i < n; i++) {
            System.out.println("i=" + i + " -> [not rob=" + dp[i][0]
                               + ", rob=" + dp[i][1] + "]");
        }
        // -------------------------------

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}


}
 */
