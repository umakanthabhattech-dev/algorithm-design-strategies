package DP.CountDistinctWays;

/*
leetcode number 2266 medium
In order to add a letter, Alice has to press the key of the corresponding digit i times,
where i is the position of the letter in the key.

For example, to add the letter 's', Alice has to press '7' four times. Similarly, to add the letter 'k',
Alice has to press '5' twice.
Note that the digits '0' and '1' do not map to any letters, so Alice does not use them.
However, due to an error in transmission, Bob did not receive Alice's text message but received a string of
pressed keys instead.

For example, when Alice sent the message "bob", Bob received the string "2266622".
Given a string pressedKeys representing the string received by Bob,
return the total number of possible text messages Alice could have sent.

Since the answer may be very large, return it modulo 109 + 7.
Example 1:

Input: pressedKeys = "22233"
Output: 8
Explanation:
The possible text messages Alice could have sent are:
"aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae", and "ce".
Since there are 8 possible messages, we return 8.
Example 2:

Input: pressedKeys = "222222222222222222222222222222222222"
Output: 82876089
Explanation:
There are 2082876103 possible text messages Alice could have sent.
Since we need to return the answer modulo 109 + 7, we return 2082876103 % (109 + 7) = 82876089.
 */
public class CountNumberOfTexts {
    public int countTexts(String pressedKeys) {
        if (pressedKeys == null || pressedKeys.length() == 0) {
            return 0;
        }

        int n = pressedKeys.length();
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case: empty string

     for (int i = 1; i <= n; i++) {
            char currentChar = pressedKeys.charAt(i - 1);
            // Single digit
            dp[i] = dp[i - 1];

            // Check for two digits
            if (i > 1 && pressedKeys.charAt(i - 2) == currentChar) {
                dp[i] += dp[i - 2];
            }

            // Check for three digits
            if (i > 2 && pressedKeys.charAt(i - 2) == currentChar && pressedKeys.charAt(i - 3) == currentChar) {
                dp[i] += dp[i - 3];
            }

            // Check for four digits (only for '7' and '9')
            if (i > 3 && pressedKeys.charAt(i - 2) == currentChar && pressedKeys.charAt(i - 3) == currentChar && pressedKeys.charAt(i - 4) == currentChar) {
                if (currentChar == '7' || currentChar == '9') {
                    dp[i] += dp[i - 4];
                }
            }

            // To avoid overflow, take modulo
            dp[i] %= 1000000007;
        }

        //print dp array for debugging
        for (int i = 0; i <= n; i++) {
            System.out.println("dp[" + i + "] = " + dp[i]);
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {
        CountNumberOfTexts counter = new CountNumberOfTexts();
        String input = "2223";
        int result = counter.countTexts(input);
        System.out.println("Number of ways to type '" + input + "' is: " + result);
    }
}
//logic is similar to decoding ways problem
//time complexity is O(n)
//space complexity is O(n)
/*
Tree Diagram for
"22233"
├─ first part = group the "222" in 4 possible ways
│   ├─ [2][2][2]  => a, a, a
│   │   ├─ then group the "33" as [3][3] => d, d    → leaf: a a a d d   ( "aaadd" )
│   │   └─ then group the "33" as [33]   => e       → leaf: a a a e     ( "aaae" )
│   ├─ [22][2]    => b, a
│   │   ├─ [3][3] => d, d                      → leaf: b a d d     ( "badd" )
│   │   └─ [33]   => e                          → leaf: b a e       ( "bae" )
│   ├─ [2][22]    => a, b
│   │   ├─ [3][3] => d, d                      → leaf: a b d d     ( "abdd" )
│   │   └─ [33]   => e                          → leaf: a b e       ( "abe" )
│   └─ [222]      => c
│       ├─ [3][3] => d, d                      → leaf: c d d       ( "cdd" )
│       └─ [33]   => e                          → leaf: c e         ( "ce" )

 */