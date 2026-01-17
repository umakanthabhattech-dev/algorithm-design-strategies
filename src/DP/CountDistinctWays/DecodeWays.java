package DP.CountDistinctWays;

/*
leetcode number 91 medium
You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
"1" -> 'A'
"2" -> 'B'
..
"25" -> 'Y'
"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because
some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:
"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.
Given a string s containing only digits, return the number of ways to decode it.
If the entire string cannot be decoded in any valid way, return 0.
 */
public class DecodeWays {
    int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));

            if (oneDigit >= 1)
                dp[i] += dp[i - 1];

            if (twoDigits >= 10 && twoDigits <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[n];
    }
    public static void main(String[] args) {
        DecodeWays decoder = new DecodeWays();
        String input = "226";
        int result = decoder.numDecodings(input);
        System.out.println("Number of ways to decode '" + input + "' is: " + result);
    }
}
//Time Complexity: O(n) where n is the length of the input string s.
//Space Complexity: O(n) for the dp array used to store the number of ways to decode up to each position in the string.
//
// However, we can optimize the space complexity to O(1) by using two variables to store the last two computed values
// instead of the entire dp array.
//how to optimize space to O(1)
// We can optimize the space complexity to O(1) by using two variables to keep track of the last two computed values
// instead of maintaining the entire dp array. Here's how you can do it:
/*
public int numDecodings(String s) {
    int n = s.length();
    if (n == 0) return 0;
    int prev2 = 1; // dp[0]
    int prev1 = s.charAt(0) == '0' ? 0 : 1; // dp[1]
    for (int i = 2; i <= n; i++) {
        int current = 0;
        int oneDigit = Integer.valueOf(s.substring(i - 1, i));
        int twoDigits = Integer.valueOf(s.substring(i - 2, i));

        if (oneDigit >= 1)
            current += prev1;

        if (twoDigits >= 10 && twoDigits <= 26)
            current += prev2;

        prev2 = prev1;
        prev1 = current;
    }
 */