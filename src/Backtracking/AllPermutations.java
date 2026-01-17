package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class AllPermutations {

    // Backtracking method
    public static void backtrack(int[] nums,
                                 List<Integer> temp,
                                 boolean[] used,
                                 List<List<Integer>> result) {

        // Base case: one permutation completed
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println("used[i]: " + used[i] + " for i: " + i + " with temp: " + temp);
            if (used[i]) continue; // skip if already used

            // choose
            used[i] = true;
            temp.add(nums[i]);

            // explore
            backtrack(nums, temp, used, result);

            // un-choose (BACKTRACK)
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    // Main method
    public static void main(String[] args) {

        int[] nums = {1, 2, 3};

        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(nums, new ArrayList<>(), used, result);

        // Print permutations
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }
}

//trace the input nums = [1, 2, 3]
// Initial call: backtrack([1, 2, 3], [], [false, false, false], result)
// 1st level:
//   i=0: choose 1 -> backtrack([1, 2, 3], [1], [true, false, false], result)
//     2nd level:
//       i=0: skip (used)
//       i=1: choose 2 -> backtrack([1, 2, 3], [1, 2], [true, true, false], result)
//         3rd level:
//           i=0: skip (used)
//           i=1: skip (used)
//           i=2: choose 3 -> backtrack([1, 2, 3], [1, 2, 3], [true, true, true], result)
//             Base case met -> add [1, 2, 3] to result
//           un-choose 3 -> backtrack to [1, 2]
//         un-choose 2 -> backtrack to [1]
//       i=2: choose 3 -> backtrack([1, 2, 3], [1, 3], [true, false, true], result)
//         3rd level:
//           i=0: skip (used)
//           i=1: choose 2 -> backtrack([1, 2, 3], [1, 3, 2], [true, true, true], result)
//             Base case met -> add [1, 3, 2] to result
//           un-choose 2 -> backtrack to [1, 3]
//         un-choose 3 -> backtrack to [1]
//     un-choose 1 -> backtrack to []
//   i=1: choose 2 -> backtrack([1, 2, 3], [2], [false, true, false], result)
//     2nd level:
//       i=0: choose 1 -> backtrack([1, 2, 3], [2, 1], [true, true, false], result)
//         3rd level:
//           i=0: skip (used)
//           i=1: skip (used)
//           i=2: choose 3 -> backtrack([1, 2, 3], [2, 1, 3], [true, true, true], result
//             Base case met -> add [2, 1, 3] to result
//           un-choose 3 -> backtrack to [2, 1]
//         un-choose 1 -> backtrack to [2]
//       i=1: skip (used)
//       i=2: choose 3 -> backtrack([1, 2, 3], [2, 3], [false, true, true], result)
//         3rd level:
//           i=0: choose 1 -> backtrack([1, 2, 3], [2, 3, 1], [true, true, true], result)
//             Base case met -> add [2, 3, 1] to result
//           un-choose 1 -> backtrack to [2, 3]
//         un-choose 3 -> backtrack to [2]
//     un-choose 2 -> backtrack to []
//   i=2: choose 3 -> backtrack([1, 2, 3], [3], [false, false, true], result)
//     2nd level:
//       i=0: choose 1 -> backtrack([1, 2, 3], [3, 1], [true, false, true], result)
//         3rd level:
//           i=0: skip (used)
//           i=1: choose 2 -> backtrack([1, 2, 3], [3, 1, 2], [true, true, true], result)
//             Base case met -> add [3, 1, 2] to result
//           un-choose 2 -> backtrack to [3, 1]
//         un-choose 1 -> backtrack to [3]
//       i=1: choose 2 -> backtrack([1, 2, 3], [3, 2], [false, true, true], result)
//         3rd level:
//           i=0: choose 1 -> backtrack([1, 2, 3], [3, 2, 1], [true, true, true], result
//             Base case met -> add [3, 2, 1] to result
//           un-choose 1 -> backtrack to [3, 2]
//         un-choose 2 -> backtrack to [3]
//     un-choose 3 -> backtrack to []
// Final result: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
//     }
// }         }
//  recursion tree diagram is

//                     []
//          /            |           \
//        [1]           [2]           [3]
//      /   \          /   \         /   \
//   [1,2] [1,3]    [2,1] [2,3]    [3,1] [3,2]
//    /       \      /      \       /      \
// [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]
/*
        [1, 2, 3]
        [1, 3, 2]
        [2, 1, 3]
        [2, 3, 1]
        [3, 1, 2]
        [3, 2, 1]
*/