package DP.FibonacciSequence;

import java.util.ArrayList;
import java.util.Arrays;

/*
leetcode 1137. N-th Tribonacci Number
Tribonacci numbers are a sequence of numbers where each number is the sum of the three preceding numbers.
Your task is to find the nth Tribonacci number.
T0 = 0, T1 = 1, T2 = 1, and Tn = Tn-1 + Tn-2 + Tn-3 for n >= 3.
The input number, n, is a non-negative integer.
SAMPLE 1
Let’s say you have to find the fifth Tribonacci number in the sequence.
From the sequence defined above, we know that
The sequence will be:
0,1,1,2,4,7
Therefore, the fifth term will be 7.
 */
class Tribonacci {
    public static long tribonacci(int n) {
        // Base cases
        if (n == 0)
            return 0;
        else if (n == 1 || n == 2)
            return 1;

        // Creating an array
        long[] dp = new long[n + 1];
        Arrays.fill(dp, 0);

        // First three tribonacci nmbers
        dp[0] = 0; dp[1] = 1; dp[2] = 1;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        ArrayList < Integer > n = new ArrayList < Integer > (
                Arrays.asList(4, 5, 25, 17, 19)
        );

        // Let's uncomment this and check the effect of dynamic programming using tabulation

        // n.add(45);

        int index = 0;
        for (int input: n) {
            System.out.println((++index) + ".\tThe " + input + "th Tribonacci number is:  " +
                    tribonacci(input));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
/*
The time complexity is O(n) because we are looping n-2 times.
The space complexity is O(n) as we are storing the Tribonacci numbers in an array of size n+1.
 */