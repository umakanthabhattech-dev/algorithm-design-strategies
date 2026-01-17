package Greedy;

/*

You are given an integer array nums. You are initially positioned at the array's first index,
and each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.
Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
which makes it impossible to reach the last index.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReachable = 0; // The farthest index we can reach
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReachable) {
                return false; // If we can't reach this index, return false
            }
            maxReachable = Math.max(maxReachable, i + nums[i]); // Update the farthest reachable index
            if (maxReachable >= nums.length - 1) {
                return true; // If we can reach or exceed the last index, return true
            }
        }
        return false; // If we finish the loop without reaching the last index
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jumpGame.canJump(nums)); // Output: true
    }
}
/*
Time complexity O(n)
space complexity O(1)
 */
//logic is to keep track of the maximum reachable index at each step
//if at any point the current index exceeds the maximum reachable index, return false
//if we reach or exceed the last index, return true
//this greedy approach ensures we always make the optimal choice at each step
//thus guaranteeing the correct result


