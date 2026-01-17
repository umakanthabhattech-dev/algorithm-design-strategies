package Greedy.KnapsackFractional;

public class Item {
    int weight;
    int profit;
    double ratio;

    Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double) profit / weight;
    }
}