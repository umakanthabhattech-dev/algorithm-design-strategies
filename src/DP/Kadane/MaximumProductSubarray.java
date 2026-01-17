package DP.Kadane;

/*
leetcode 152. Maximum Product Subarray

 */
public class MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];
        int globalMax = nums[0];

        System.out.println("Initial values: ");
        System.out.println("maxEndingHere: " + maxEndingHere);
        System.out.println("minEndingHere: " + minEndingHere);
        System.out.println("globalMax: " + globalMax);
        System.out.println();

        /*
        If current number is negative

        If you multiply the current maximum product (maxEndingHere) by a negative number,
        it will become the new minimum product.
        Conversely, if you multiply the current minimum product (minEndingHere) by the same negative number,
        it will become the new maximum product.
         */

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                // Swap max and min when the current number is negative
                int temp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }

            maxEndingHere = Math.max(nums[i], maxEndingHere * nums[i]);
            minEndingHere = Math.min(nums[i], minEndingHere * nums[i]);

            globalMax = Math.max(globalMax, maxEndingHere);

            // Print the values at each iteration
            System.out.println("Iteration " + i + ": ");
            System.out.println("Current number: " + nums[i]);
            System.out.println("maxEndingHere: " + maxEndingHere);
            System.out.println("minEndingHere: " + minEndingHere);
            System.out.println("globalMax: " + globalMax);
            System.out.println();
        }

        return globalMax;
    }

    /*
       public static int maxProductKadane(int[] a) {
        int n = a.length;
        if (n == 0) return 0;

        int maxEndingHere = a[0];
        int minEndingHere = a[0]; // min because negative * negative -> positive
        int best = a[0];

        for (int i = 1; i < n; i++) {
            int v = a[i];

            if (v < 0) {
                // swap max and min when v is negative
                int tmp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = tmp;
            }

            // either start at v or extend previous product
            maxEndingHere = Math.max(v, maxEndingHere * v);
            minEndingHere = Math.min(v, minEndingHere * v);

            best = Math.max(best, maxEndingHere);
        }
        return best;
    }
     */


    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        int result = maxProduct(nums);
        System.out.println("The maximum product subarray is: " + result); // Output: 6
    }
}
/*
Time Complexity: O(n)
Space Complexity: O(1)
 */

/*
import java.util.*;

public class tUf {
    public static int maxProductSubArray(int[] arr) {
        int n = arr.length; //size of array.
        int pre = 1, suff = 1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (pre == 0) pre = 1;
            if (suff == 0) suff = 1;
            pre *= arr[i];
            suff *= arr[n - i - 1];
            ans = Math.max(ans, Math.max(pre, suff));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, -3, 0, -4, -5};
        int answer = maxProductSubArray(arr);
        System.out.println("The maximum product subarray is: " + answer);
    }
}
 */

