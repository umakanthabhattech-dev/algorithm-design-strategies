package DP.FibonacciSequence;

/*
 Fibonacci Number means F(n) where
 F(0) = 0
 F(1) = 1
 F(n) = F(n-1) + F(n-2) for n >= 2
 */
public class FibonacciNumber {
    public int fib(int n) {
        if (n == 0) return 0; // Base case for F(0)
        if (n == 1) return 1; // Base case for F(1)

        int[] dp = new int[n + 1]; // Array to store Fibonacci numbers
        dp[0] = 0; // F(0)
        dp[1] = 1; // F(1)

        // Fill the dp array
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // F(n) = F(n-1) + F(n-2)
        }

        return dp[n]; // Return F(n)
    }
    public static void main(String args[]) {
        FibonacciNumber fibNum = new FibonacciNumber();
        System.out.println("fib result = " + fibNum.fib(6));
    }
}
/*time O(n) and space O(n) to improve we can check below code*/
/*
public int fib(int n) {
        if (n == 0) return 0; // Base case for F(0)
        if (n == 1) return 1; // Base case for F(1)

        int a = 0; // F(0)
        int b = 1; // F(1)
        int fibN = 0;

        // Iteratively calculate Fibonacci numbers up to F(n)
        for (int i = 2; i <= n; i++) {
            fibN = a + b; // F(n) = F(n-1) + F(n-2)
            a = b; // Move to the next Fibonacci number
            b = fibN; // Update b to the current Fibonacci number
        }

        return fibN; // Return F(n)
    }
 */