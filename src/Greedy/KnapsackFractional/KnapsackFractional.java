package Greedy.KnapsackFractional;

import java.util.Arrays;

/*
Fractional Knapsack Problem using Greedy Approach
problem statment is to maximize the total profit in the knapsack
by selecting items with given weights and profits, allowing for fractional selection of items.
example from video:
Items (weight, profit):
(1, 6), (2, 10), (4, 18), (1, 3), (2, 5), (5, 15), (3, 7)
Knapsack Capacity: 15
Expected Output: Maximum Profit = 55.0
 */
public class KnapsackFractional {

    public static double getMaxProfit(Item[] items, int capacity) {

        // Step 1: Sort items by P/W ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0.0;
        int remaining = capacity;

        // Step 2: Pick items greedily
        for (Item item : items) {
            if (remaining == 0) break;

            if (item.weight <= remaining) {
                // Take the whole item
                totalProfit += item.profit;
                remaining -= item.weight;
            } else {
                // Take fractional part
                double fraction = (double) remaining / item.weight;
                totalProfit += item.profit * fraction;
                remaining = 0;
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        // Example from video
        Item[] items = {
                new Item(1, 6),
                new Item(2, 10),
                new Item(4, 18),
                new Item(1, 3),
                new Item(2, 5),
                new Item(5, 15),
                new Item(3, 7)
        };

        int capacity = 15;

        double maxProfit = getMaxProfit(items, capacity);
        System.out.println("Maximum Profit = " + maxProfit);
    }
}
//time complexity: O(n log n) due to sorting
//space complexity: O(1)
