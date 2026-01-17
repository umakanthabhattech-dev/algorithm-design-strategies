package DP.GridDP;

/*
Leetcode 120. Triangle
Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row,
you may move to either index i or index i + 1 on the next row.
Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 */
import java.util.ArrayList;
import java.util.List;

public class TriangleLt120 {

    int minimumTotal(List<List<Integer>> triangle) {

        int height = triangle.size();
        int[][] dp = new int[height + 1][height + 1];

        for (int level = height - 1; level >= 0; level--) {


            for (int i = 0; i <= level; i++) {

                // Add the minimum amongst 2 adjacent elements
                // from bottom level
                System.out.println(dp[level + 1][i] + " " + dp[level + 1][i + 1]);
                dp[level][i] = triangle.get(level).get(i)
                        + Math.min(
                        dp[level + 1][i], dp[level + 1][i + 1]);
            }

        }
        //print dp array
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j <= i; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        return dp[0][0];
    }
    public static void main(String args[]) {
        TriangleLt120 triangleLt = new TriangleLt120();
        List<List<Integer>> triangle = new ArrayList<>();

        triangle.add(List.of(2));
        triangle.add(List.of(3,4));
        triangle.add(List.of(6,5,7));
        triangle.add(List.of(4,1,8,3));

        int result = triangleLt.minimumTotal(triangle);
        System.out.println("Minimum path sum: " + result);
    }
}
