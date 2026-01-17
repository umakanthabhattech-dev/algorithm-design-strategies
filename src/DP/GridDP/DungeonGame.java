package DP.GridDP;
/*
174. Dungeon Game
The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
The dungeon consists of m x n rooms laid out in a 2D grid.
Our valiant knight was initially positioned in the top-left room and
must fight his way through dungeon to rescue the princess.
The knight has an initial health point represented by a positive integer.
If at any point his health point drops to 0 or below, he dies immediately.
Some of the rooms are guarded by demons (represented by negative integers),
so the knight loses health upon entering these rooms;
other rooms are either empty (represented as 0) or
contain magic orbs that increase the knight's health (represented by positive integers).
To reach the princess as quickly as possible,
the knight decides to move only rightward or downward in each step.
Return the knight's minimum initial health so that he can rescue the princess.
Note that any room can contain threats or power-ups,
even the first room the knight enters and the bottom-right room where the princess is imprisoned.
Input: dungeon = [[-2,-3,3],
                  [-5,-10,1],
                  [10,30,-5]]
Output: 7
Explanation: The initial health of the knight must be at least 7
if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
Best youtube link refered -https://www.youtube.com/watch?v=LbC0ejgACkE
 */

public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        int r = dungeon.length;
        int c = dungeon[0].length;

        int[][] dp = new int[r][c];

        // 1. Bottom-right cell (princess cell)
        if (dungeon[r-1][c-1] >= 0) {
            dp[r-1][c-1] = 1;
        } else {
            dp[r-1][c-1] = 1 - dungeon[r-1][c-1];
        }

        // 2. Last column
        for (int i = r - 2; i >= 0; i--) {
            dp[i][c - 1] = Math.max(1, dp[i + 1][c - 1] - dungeon[i][c - 1]);
        }

        // 3. Last row
        for (int j = c - 2; j >= 0; j--) {
            dp[r - 1][j] = Math.max(1, dp[r - 1][j + 1] - dungeon[r - 1][j]);
        }

        // 4. Fill remaining cells
        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 2; j >= 0; j--) {
                int minNext = Math.min(dp[i+1][j], dp[i][j+1]);
                dp[i][j] = Math.max(1, minNext - dungeon[i][j]);
            }
        }

        return dp[0][0]; // minimum HP required at start
    }

    // ------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------
    public static void main(String[] args) {
        DungeonGame obj = new DungeonGame();

        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };

        int result = obj.calculateMinimumHP(dungeon);
        System.out.println("Minimum Initial Health Required: " + result);
    }
}
//time

//public class DungeonGame {
//
//    public static int calculateMinimumHP(int[][] dungeon) {
//        int m = dungeon.length;
//        int n = dungeon[0].length;
//
//        int[][] dp = new int[m][n];
//
//        // Base case: princess cell
//        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
//
//        // Last row
//        for (int j = n - 2; j >= 0; j--) {
//            dp[m - 1][j] = Math.max(1,
//                    dp[m - 1][j + 1] - dungeon[m - 1][j]);
//        }
//
//        // Last column
//        for (int i = m - 2; i >= 0; i--) {
//            dp[i][n - 1] = Math.max(1,
//                    dp[i + 1][n - 1] - dungeon[i][n - 1]);
//        }
//
//        // Remaining cells
//        for (int i = m - 2; i >= 0; i--) {
//            for (int j = n - 2; j >= 0; j--) {
//                int minNext = Math.min(dp[i + 1][j], dp[i][j + 1]);
//                dp[i][j] = Math.max(1, minNext - dungeon[i][j]);
//            }
//        }
//
//        return dp[0][0];
//    }
//
//    public static void main(String[] args) {
//        int[][] dungeon = {
//                {-2, -3,  3},
//                {-5, -10, 1},
//                {10, 30, -5}
//        };
//
//        System.out.println(calculateMinimumHP(dungeon)); // Output: 7
//    }
//}

