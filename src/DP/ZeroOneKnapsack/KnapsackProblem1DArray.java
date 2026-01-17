package DP.ZeroOneKnapsack;

/*
Tabulation method
Time complexity O(N∗C)
where “N” represents the total items and “C” is the maximum capacity
Space O(N×C) can be optimised using 1D array
O/p Total knapsack profit ---> 22
*/

class KnapsackProblem1DArray {
    // Returns the maximum value that can be put in a knapsack of capacity W
    public static int knapSack(int[] profits, int[] weights, int capacity) {
        int n = profits.length;
        // Create a 1D DP array to store the maximum profit for each capacity
        int[] dp = new int[capacity + 1];

        // Build the array in a bottom-up manner
        for (int i = 0; i < n; i++) {
            for (int w = capacity; w >= weights[i]; w--) {
                // Include the item and see if it gives a better profit
                dp[w] = Math.max(dp[w], profits[i] + dp[w - weights[i]]);
            }
        }
//-----------
        //Print selected items code
        int currentCapacity = capacity;
        System.out.print("Selected Items (indices): ");
        for (int i = n - 1; i >= 0; i--) {//0,1,2,3
            // Check if the item was included
            if (currentCapacity >= weights[i] &&
                    dp[currentCapacity] == profits[i] + dp[currentCapacity - weights[i]]) {
                System.out.print(i + " "); // Print the index of the included item
                currentCapacity -= weights[i]; // Reduce the capacity
            }
        }
//-------------


        // The maximum profit will be in the last cell of the array
        return dp[capacity];
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
/*
if we use this logic instead of w >= weights[i]
if weight[i] > current capacity w then we cannot include that item we add profit of previous item dp[w]
 else we check max of include and exclude

 for (int w = capacity; w >= 0; w--) {
    if (w >= weights[i]) {
        // Include the item and see if it gives a better profit
        dp[w] = Math.max(dp[w], profits[i] + dp[w - weights[i]]);
    }
  }
 */


//Time Complexity: O(N * C)
//Space Complexity: O(C)

/*
Capacity: 7
Result = 22 = dp[7];
logic : item selection
DP Array After Calculation: {0, 1, 6, 10, 16, 17, 22, 22}
Iteration 1 (Item 3):
Current Item: Item 3 (Weight = 5, Value = 16).
Current Capacity: 7.
DP Value at Current Capacity: dp[7] = 22.
Comparison: We check if dp[7] == profits[3] + dp[7 - weights[3]], i.e., 22 == 16 + dp[2]. Since dp[2] = 6, we have 22 == 16 + 6, which is true.
Item Included: Yes, Item 3 is included.
Update Current Capacity: currentCapacity -= weights[3] → currentCapacity = 2.
 */

