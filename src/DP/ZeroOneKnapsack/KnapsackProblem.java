package DP.ZeroOneKnapsack;

/*
leetcode 0/1 Knapsack Problem
Given weights and profits of N items,
we are asked to put these items in a knapsack which has a capacity of W.
The goal is to get the maximum profit from the items in the knapsack.
Each item can only be selected once,
which means either we put the complete item in the knapsack or we don’t take it at all.
example:
Input:
Items: { Apple, Orange, Banana, Melon }
Weights: { 1, 2, 3, 5 }
Profits: { 1, 6, 10, 16 }
Capacity: 7
Output: 22
We can take the items: Orange, Banana and Melon with total profit 6 + 10 + 16 = 22

 */
class KnapsackProblem {
    // Returns the maximum value that can be put in a knapsack of capacity W
    public static int knapSack(int[] profits, int[] weights, int capacity) {
        int n = profits.length;
        // Create a DP table to store the maximum profit for each capacity
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the table in a bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // If the weight of the current item is less than or equal to the current capacity
                if (weights[i - 1] <= w) {
                    // Include the item and see if it gives a better profit
                    //max of (without include, include current)
                    dp[i][w] = Math.max(dp[i - 1][w], profits[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    // Exclude the item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find the selected items
        int currentCapacity = capacity;
        System.out.print("Selected Items (indices): ");
        for (int i = n; i > 0; i--) {
            // Check if the item was included
            if (currentCapacity >= weights[i - 1] &&
                    dp[i][currentCapacity] != dp[i - 1][currentCapacity]) {
                System.out.print((i - 1) + " "); // Print the index of the included item
                currentCapacity -= weights[i - 1]; // Reduce the capacity
            }
        }
        //--

        // The maximum profit will be in the bottom-right cell of the table
        return dp[n][capacity];
    }

    // Driver program to test the above function
    public static void main(String[] args) {
//        int[] profits = {1, 6, 10, 16}; // The values of the jewelry
//        int[] weights = {1, 2, 3, 5}; // The weight of each
//        int capacity = 7; // Maximum capacity of the knapsack
        int[] profits = {1, 2, 5, 6}; // The values of the jewelry
        int[] weights = {2, 3, 4, 5}; // The weight of each
        int capacity = 8; // Maximum capacity of the knapsack
        System.out.println("Total knapsack profit ---> " + knapSack(profits, weights, capacity));
    }
}
//time complexity O(N∗C)
//space complexity O(N∗C)
//where “N” represents the total items and “C” is the maximum capacity


