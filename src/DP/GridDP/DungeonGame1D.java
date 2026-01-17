package DP.GridDP;

/*
Time Complexity: O(R × C)
Space Complexity: O(min(R, C)) using 1D DP

 */
public class DungeonGame1D {

    public static int calculateMinimumHP(int[][] dungeon) {
        int r = dungeon.length;
        int c = dungeon[0].length;

        int[] dp = new int[c];

        // Base case: bottom-right
        dp[c - 1] = Math.max(1, 1 - dungeon[r - 1][c - 1]);

        // Last row
        for (int j = c - 2; j >= 0; j--) {
            dp[j] = Math.max(1, dp[j + 1] - dungeon[r - 1][j]);
        }

        // Remaining rows (bottom to top)
        for (int i = r - 2; i >= 0; i--) {

            // Last column
            dp[c - 1] = Math.max(1, dp[c - 1] - dungeon[i][c - 1]);

            for (int j = c - 2; j >= 0; j--) {
                int minNext = Math.min(dp[j], dp[j + 1]);
                dp[j] = Math.max(1, minNext - dungeon[i][j]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };

        System.out.println(calculateMinimumHP(dungeon)); // Output: 7
    }
}
/*
| Approach          | Space    |
| ----------------- | -------- |
| 2D DP             | O(R × C) |
| 1D DP             | **O(C)** |
| If columns > rows | Use O(R) |

final space complexity is O(min(R, C))
 */
