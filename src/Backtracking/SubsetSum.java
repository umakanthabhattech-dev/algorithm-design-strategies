package Backtracking;

/*
leetcode problem 416. Partition Equal Subset Sum
 */
public class SubsetSum {
    static void printSubset(boolean flags[], int arr[], int size) {
        for (int i = 0; i < size; i++) {
            if (flags[i])
                System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static void subsetSum(int arr[], int n, int target) {
        boolean[] flags = new boolean[n];
        subsetSum(arr, n, flags, 0, 0, target);
    }

    static void subsetSum(int arr[], int n, boolean flags[], int sum, int curr, int target) {
        if (target == sum) {
            printSubset(flags, arr, n); // Solution found.
            return;
        }

        if (curr >= n || sum > target) { // constraint check and Backtracking.
            return;
        }

        // Current element included.
        flags[curr] = true;
        subsetSum(arr, n, flags, sum + arr[curr], curr + 1, target);
        // Current element excluded.
        flags[curr] = false;
        subsetSum(arr, n, flags, sum, curr + 1, target);
    }

    public static void main(String[] args) {
//        int arr[] = { 15, 22, 14, 26, 32, 9, 16, 8 };
//        int target = 53;
        int arr[] = { 5, 10, 12, 13, 15, 18};
        int target = 30;
        int n = arr.length;
        subsetSum(arr, n, target);
    }
}
//compexity are same exponential height of graph is 6 so 2 power 6
//recursion tree diagram
//                        sum=0 curr=0
//                   /                      \
//          include 5                     exclude 5
//          sum=5 curr=1                  sum=0 curr=1
//        /        \                     /         \
//   include10   exclude10          include10    exclude10
//   sum=15 curr=2  sum=5 curr=2     sum=10 curr=2  sum=0 curr=2
//   /     \        /     \          /     \         /     \
//..       ..    ..       ..      ..       ..     ..       ..
//until sum=target or curr==n or sum>target
//if sum==target print the subset
//example output for arr[] = { 5, 10, 12, 13, 15, 18}; target = 30
//5 10 15
//5 12 13
//12 18


