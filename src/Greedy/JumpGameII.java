package Greedy;

/*
Leetcode Problem 45: Jump Game II
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 */
public class JumpGameII {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int jumps = 0; // Number of jumps made
        int currentEnd = 0; // The farthest point that can be reached with the current number of jumps
        int farthest = 0; // The farthest point that can be reached with the next jump

        for (int i = 0; i < nums.length - 1; i++) {
            // Update the farthest point that can be reached
            farthest = Math.max(farthest, i + nums[i]);

            // If we have come to the end of the current jump
            if (i == currentEnd) {
                jumps++; // Increment the jump count
                currentEnd = farthest; // Update the current end to the farthest point reached

                // If the current end can reach or exceed the last index, break
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        JumpGameII jumpGame = new JumpGameII();
        //int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {2, 3, 1, 1, 4, 1};
        System.out.println("Minimum jumps needed: " + jumpGame.jump(nums)); // Output: 2
    }
}
//logic is to keep track of the farthest point that can be reached with the current number of jumps
//and the farthest point that can be reached with the next jump
//when we reach the end of the current jump, we increment the jump count and update the current end to the farthest point reached
//this greedy approach ensures we always make the optimal choice at each step
//thus guaranteeing the correct result
//time complexity O(n)
//space complexity O(1)
