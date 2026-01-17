package DP.DigitDP;

/*
357. Count Numbers with Unique Digits
Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10^n.
Example 1:
Input: n = 2
Output: 91
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99

For 1 digit (n = 1) -> dp[1] = 10 (0-9)

For 2 digits
_ _ => _ First digit has 9 options (1-9), second digit has 9 options (0-9 excluding the first digit)
So total for 2 digits = 9 * 9 = 81
 (n = 2) -> dp[2] = dp[1] if only first digit + 9 * 9 if two digits = 10 + 81 = 91


Example 2:
Input: n = 0
Output: 1
 */
/*
Logic to solve the problem:
dp[i] = Total count of all numbers with unique digits that have at most i digits
dp[i] includes all unique-digit numbers of length 1, 2, … up to i
So dp[i] = dp[i-1] + count of unique-digit numbers with exactly i digits
 */
public class CountNumbersWithUniqueDigits {
    public int countUniqueDigits(int n) {
        if (n == 0) return 1; // Base case: Only the number 0 for n = 0.

        // DP array to store results for each number of digits
        int[] dp = new int[n + 1];

        // For n = 1, there are 10 numbers (0 to 9)
        dp[1] = 10;

        //uniqueDigits is the count of unique-digit numbers with exactly i digits
        int uniqueDigits = 9;  // First digit choices (can't be 0)
        //availableDigits is the count of remaining digits that can be used for subsequent positions
        int availableDigits = 9;  // Remaining digits

        for (int i = 2; i <= n; i++) {
            //count of unique-digit numbers with exactly i digits
            uniqueDigits *= availableDigits;  // Calculate numbers with i unique digits
            dp[i] = dp[i - 1] + uniqueDigits; // Add to the previous result
            availableDigits--;  // Decrease available digits for the next iteration
        }
        return dp[n];
    }

    public static void main(String[] args) {
        CountNumbersWithUniqueDigits solution = new CountNumbersWithUniqueDigits();
        int n = 2; // Example input
        System.out.println("Count of numbers with unique digits for n = " + n + ": " + solution.countUniqueDigits(n));
    }
}
/*
Time complexity O(n)
Space complexity O(n)
 */
/*
Another solution for good for space complexity
public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;

        int count = 10; // for n = 1
        int uniqueDigits = 9; // 1-9 for the first digit
        int availableNumbers = 9; // 0-9 excluding the first digit

        for (int i = 2; i <= n; i++) {
            uniqueDigits *= availableNumbers;
            count += uniqueDigits;
            availableNumbers--; // decrease available numbers for next digit
        }

        return count;
    }
 */