package Backtracking;

/*
leetcode-51: N-Queens
Given an integer n, return all distinct solutions to the n-queens puzzle.
You may return the answer in any order.
A solution to the n-queens puzzle is a configuration of the n-queens on
an n x n chessboard such that no two queens threaten each other.
This is done using backtracking approach.

The n-queens puzzle is the problem of placing
n queens on an n x n chessboard such that no
two queens attack each other.
Given an integer n, return all distinct solutions
to the n-queens puzzle. You may return the answer in any order.
Each solution contains a distinct board configuration of the
n-queens' placement, where 'Q' and '.' both indicate
a queen and an empty space, respectively.
Input: n = 4
Output:
[[".Q..","...Q","Q...","..Q."],
["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct
solutions to the 4-queens puzzle as shown above
 */
//public class NQueen {
//    public static void print(int[] Q, int n) {
//        for (int i = 0; i < n; i++) {
//            System.out.print(" " + Q[i]);
//        }
//        System.out.println(" ");
//    }
//
//    public static boolean feasible(int[] Q, int k) {
//        //i indicates previous row positions of queens
//        for (int i = 0; i < k; i++) {
//            if (Q[k] == Q[i] || Math.abs(Q[i] - Q[k]) == Math.abs(i - k)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static void nQueens(int[] Q, int k, int n) {
//        if (k == n) {
//            print(Q, n);
//            return;
//        }
//        //i indicates column position
//        for (int i = 0; i < n; i++) {
//            Q[k] = i;
//            if (feasible(Q, k)) {
//                nQueens(Q, k + 1, n);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        /*int[] Q = new int[8]; //it can be 4 also
//        nQueens(Q, 0, 8);*/
//        //Q indicates the column position of queen in each row
//        int[] Q = new int[4]; //it can be 4 also
//        //k indicates the current row
//        nQueens(Q, 0, 4);
//
//    }
//}

public class NQueen {

    static int N = 4;

    public static void main(String[] args) {
        int[] board = new int[N];
        solve(board, 0);
    }

    static void solve(int[] board, int row) {
        // Base case: all queens placed
        if (row == N) {
            printBoard(board);
            System.out.println();
            return;
        }

        // Try each column in current row
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;      // place queen
                solve(board, row + 1); // recurse
                // backtracking happens automatically
            }
        }
    }

    static boolean isSafe(int[] board, int row, int col) {
        for (int r = 0; r < row; r++) {
            // same column
            if (board[r] == col)
                return false;

            // diagonal check
            //   |col1 - col2| == |row1 - row2|
            //old(r) placed queen  => column=board[r] && row=r
            //new => column=col && row=row
            //difference must be equal to be in diagonal
            if (Math.abs(board[r] - col) == Math.abs(r - row))
                return false;
        }
        return true;
    }

    static void printBoard(int[] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}
//trace the input n=4 for N-Queens for above code
//first call: solve([], 0)
// 1st level (row=0):
//   col=0: place Q at (0,0) -> solve([0, _, _, _], 1)
//     2nd level (row=1):
//       col=0: not safe (same column)
//       col=1: not safe (diagonal)
//       col=2: place Q at (1,2) -> solve([0, 2, _, _], 2)
//         3rd level (row=2):
//           col=0: not safe (same column)
//           col=1: not safe (diagonal)
//           col=2: not safe (same column)
//           col=3: not safe (diagonal)
//         backtrack to [0, 2, _, _]
//       col=3: place Q at (1,3) -> solve([0, 3, _, _], 2)
//         3rd level (row=2):
//           col=0: not safe (same column)
//           col=1: place Q at (2,1) -> solve([0, 3, 1, _], 3)
//             4th level (row=3):
//               col=0: not safe (same column)
//               col=1: not safe (same column)
//               col=2: not safe (diagonal)
//               col=3: not safe (same column)
//             backtrack to [0, 3, 1, _]
//           col=2: not safe (diagonal)
//           col=3: not safe (same column)
//         backtrack to [0, 3, _, _]
//     backtrack to [0, _, _, _]
//   col=1: place Q at (0,1) -> solve([1, _, _, _], 1)
//     2nd level (row=1):
//       col=0: place Q at (1,0) -> solve([1, 0, _, _], 2)
//         3rd level (row=2):
//           col=0: not safe (same column)
//           col=1: not safe (same column)
//           col=2: not safe (diagonal)
//           col=3: place Q at (2,3) -> solve([1, 0, 3, _], 3)
//             4th level (row=3):
//               col=0: not safe (same column)
//               col=1: not safe (diagonal)
//               col=2: place Q at (3,2) -> solve([1, 0, 3, 2], 4)
//                 Base case met -> print board
//               backtrack to [1, 0, 3, _]
//               col=3: not safe (same column)
//             backtrack to [1, 0, _, _]
//           backtrack to [1, 0, _, _]
//       col=1: not safe (same column)
//       col=2: not safe (diagonal)
//       col=3: place Q at (1,3) -> solve([1, 3, _, _], 2)
//         3rd level (row=2):
//           col=0: place Q at (2,0) -> solve([1, 3, 0, _], 3)
//             4th level (row=3):
//               col=0: not safe (same column)
//               col=1: not safe (diagonal)
//               col=2: not safe (same column)
//               col=3: not safe (diagonal)
//             backtrack to [1, 3, 0, _]
//           col=1: not safe (same column)
//           col=2: place Q at (2,2) -> solve([1, 3, 2, _], 3)
//             4th level (row=3):
//               col=0: not safe (diagonal)
//               col=1: place Q at (3,1) -> solve([1, 3, 2, 1], 4)
//                 Base case met -> print board
//               backtrack to [1, 3, 2, _]
//               col=2: not safe (same column)
//               col=3: not safe (same column)
//             backtrack to [1, 3, 2, _]
//         backtrack to [1, 3, _, _]
//     backtrack to [1, _, _, _]
//   col=2: place Q at (0,2) -> solve([2, _, _, _], 1)
//     2nd level (row=1):
//       col=0: place Q at (1,0) -> solve([2, 0, _, _], 2)
//         3rd level (row=2):
//           col=0: not safe (same column)

//           col=1: not safe (diagonal)
//           col=2: not safe (same column)
//           col=3: place Q at (2,3) -> solve([2, 0, 3, _], 3)
//             4th level (row=3):
//               col=0: not safe (same column)
//               col=1: place Q at (3,1) -> solve([2, 0, 3, 1], 4)
//                 Base case met -> print board
//               backtrack to [2, 0, 3, _]
//               col=2: not safe (diagonal)
//               col=3: not safe (same column)
//             backtrack to [2, 0, _, _]
//           backtrack to [2, 0, _, _]
//       col=1: not safe (diagonal)
//       col=2: not safe (same column)
//       col=3: place Q at (1,3) -> solve([2, 3, _, _], 2)
//         3rd level (row=2):
//           col=0: place Q at (2,0) -> solve([2, 3, 0, _], 3)
//             4th level (row=3):
//               col=0: not safe (same column)
//               col=1: not safe (diagonal)
//               col=2: place Q at (3,2) -> solve([2, 3, 0, 2], 4)
//                 Base case met -> print board
//               backtrack to [2, 3, 0, _]
//               col=3: not safe (same column)
//             backtrack to [2, 3, _, _]
//           col=1: not safe (same column)
//           col=2: not safe (diagonal)
//           col=3: not safe (same column)
//         backtrack to [2, 3, _, _]
//     backtrack to [2, _, _, _]
//   col=3: place Q at (0,3) -> solve([3, _, _, _], 1)
//     2nd level (row=1):
//       col=0: place Q at (1,0) -> solve([3, 0, _, _], 2)
//         3rd level (row=2):
//           col=0: not safe (same column)
//           col=1: place Q at (2,1) -> solve([3, 0, 1, _], 3)
//             4th level (row=3):
//               col=0: not safe (same column)
//               col=1: not safe (same column)
//               col=2: not safe (diagonal)
//               col=3: not safe (same column)
//             backtrack to [3, 0, 1, _]
//           col=2: not safe (diagonal)
//           col=3: not safe (same column)
//         backtrack to [3, 0, _, _]
//       col=1: not safe (diagonal)
//       col=2: place Q at (1,2) -> solve([3, 2, _, _], 2)
//         3rd level (row=2):
//           col=0: place Q at (2,0) -> solve([3, 2, 0, _], 3)
//             4th level (row=3):
//               col=0: not safe (same column)
//               col=1: not safe (diagonal)
//               col=2: not safe (same column)
//               col=3: not safe (diagonal)
//             backtrack to [3, 2, 0, _]
//           col=1: not safe (same column)
//           col=2: not safe (same column)
//           col=3: place Q at (2,3) -> solve([3, 2, 3, _], 3)
//             4th level (row=3):
//               col=0: not safe (diagonal)
//               col=1: place Q at (3,1) -> solve([3, 2, 3, 1], 4)
//                 Base case met -> print board
//               backtrack to [3, 2, 3, _]
//               col=2: not safe (same column)
//               col=3: not safe (same column)
//             backtrack to [3, 2, _, _]
//         backtrack to [3, 2, _, _]
//       col=3: not safe (same column)
//     backtrack to [3, _, _, _]
//first solution: [1, 3, 0, 2]
//second solution: [2, 0, 3, 1]








//diagram of recursion tree for n=4
//                      []
//          /        /       \        \
//        [0]      [1]      [2]      [3]
//        /         /         \         \
//      [0,2]    [1,3]     [2,0]    [3,0]
//        \         \                     \
//      [0,3,1]   [1,3,0]               [3,0,2]
//        /          /                     /
//   [0,3,1,2]  [1,3,0,2]         [3,0,2,1]
//     (soln)      (soln)              (soln)






/*
0 4 7 5 2 6 1 3
0 5 7 2 6 3 1 4
......
7 2 0 5 1 4 6 3
7 3 0 2 5 1 6 4
*/
