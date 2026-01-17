package DP.LIS;

/*
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi]
represents the width and the height of an envelope.
uses longest increasing subsequences logic
Example 1:

Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//public class RussianDollEnvelopesBS {
//
//    public static int maxEnvelopes(int[][] envelopes) {
//        if (envelopes.length == 0) return 0;
//
//        // Step 1: Sort
//        Arrays.sort(envelopes, (a, b) -> {
//            if (a[0] == b[0]) {
//                return b[1] - a[1]; // height desc if width same
//            }
//            return a[0] - b[0]; // width asc
//        });
//
//        // Step 2: LIS on heights
//        int[] lis = new int[envelopes.length];
//        int size = 0;
//
//        for (int[] env : envelopes) {
//            int height = env[1];
//
//            int index = Arrays.binarySearch(lis, 0, size, height);
//            if (index < 0) {
//                index = -(index + 1);
//            }
//
//            lis[index] = height;
//
//            // If height is larger than any element in lis
//            if (index == size) {
//                size++;
//            }
//        }
//
//        return size;
//    }
//
//    public static void main(String[] args) {
//        int[][] envelopes = {
//                {5,4}, {6,4}, {6,7}, {2,3}
//        };
//
//        System.out.println(maxEnvelopes(envelopes)); // Output: 3
//    }
//}


/*
leetcode problem 354
354. Russian Doll Envelopes
You are given a 2D array of integers
envelopes where envelopes[i] = [wi, hi]
represents the width and the height of an envelope.
One envelope can fit into another if and only
if both the width and height of one envelope are
greater than the other envelope's width and height.
Return the maximum number of envelopes you can
Russian doll (i.e., put one inside the other).
Note: You cannot rotate an envelope
Example 1:
Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes
you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopesBS {

    public static int maxEnvelopes(int[][] envelopes) {

        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        // Step 1: Sort envelopes
        // width ascending, height descending (VERY IMPORTANT)
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // height descending
            }
            return a[0] - b[0];     // width ascending
        });

        // Step 2: Apply LIS on heights using List + Binary Search
        List<Integer> temp = new ArrayList<>();

        for (int[] env : envelopes) {
            int height = env[1];

            int idx = Collections.binarySearch(temp, height);

            // If height not found, get insertion index
            if (idx < 0) {
                idx = -(idx + 1);
            }

            // Extend LIS
            if (idx == temp.size()) {
                temp.add(height);
            }
            // Replace to keep smallest possible tail
            else {
                temp.set(idx, height);
            }
        }

        // Length of LIS = maximum envelopes
        return temp.size();
    }

    public static void main(String[] args) {

        int[][] envelopes = {
            {5, 4},
            {6, 4},
            {6, 7},
            {2, 3}
        };

        int result = maxEnvelopes(envelopes);

        System.out.println("Maximum Russian Doll Envelopes: " + result);
    }
}
//time complexity O(N log N)
