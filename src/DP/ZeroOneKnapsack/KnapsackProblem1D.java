package DP.ZeroOneKnapsack;
/*
Tabulation method using 1D array to optomise space complexity
space complexity reduced from O(N×C) to O(C)
where “N” represents the total items and “C” is the maximum capacity
Time complexity O(N∗C)
 */
class KnapsackProblem1D {
    public static int knapSack(int profits[], int profitsLength, int weights[], int weightsLength, int capacity) {
        // basic checks
        if (capacity <= 0 || profitsLength == 0 || weightsLength != profitsLength)
            return 0;

        int[] lookupTable = new int[capacity + 1];

        // if we have only one weight, we will take it if it is not more than the capacity
        for (int c = 0; c <= capacity; c++) {
            if (weights[0] <= c)
                lookupTable[c] = profits[0];
        }
        // process all sub-arrays for all the capacities
        for (int i = 1; i < profitsLength; i++) {
            for (int c = capacity; c >= 0; c--) {
                int profit1 = 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if (weights[i] <= c)
                    profit1 = profits[i] + lookupTable[c - weights[i]];
                // exclude the item
                profit2 = lookupTable[c];
                // take maximum
                lookupTable[c] = Math.max(profit1, profit2);
            }
        }
        return lookupTable[capacity];
    }

    public static void main(String args[]) {
        int profits[] = {1, 6, 10, 16}; // The values of the jewelry
        int weights[] = {1, 2, 3, 5}; // The weight of each
        System.out.println("Total knapsack profit ---> " + knapSack(profits, 4, weights, 4, 7));
        System.out.println("Total knapsack profit ---> " + knapSack(profits, 4, weights, 4, 6));
    }
};