package DP.LIS;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence1DPrint {
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;

        int n = nums.length;
        int[] lis = new int[n];
        int[] prevIndex = new int[n]; // To track the previous index in the LIS
        for (int i = 0; i < n; i++) {
            lis[i] = 1; // Initialize LIS values for all indexes
            prevIndex[i] = -1; // Initialize previous index for maintaining the subsequences
        }

        // Compute optimized LIS values in bottom-up manner
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                    prevIndex[i] = j; // Update the previous index
                }
            }
        }

        // Find the maximum length of LIS and its index
        int maxLength = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (lis[i] > maxLength) {
                maxLength = lis[i];
                maxIndex = i;
            }
        }

        // Print the LIS
        printLIS(nums, prevIndex, maxIndex);

        return maxLength;
    }

    private static void printLIS(int[] nums, int[] prevIndex, int maxIndex) {
        List<Integer> subsequence = new ArrayList<>();
        for (int i = maxIndex; i >= 0; i = prevIndex[i]) {
            subsequence.add(nums[i]);
            if (prevIndex[i] == -1) break; // Stop if there's no previous index
        }
        // Since we added elements in reverse order, we need to reverse it
        StringBuilder sb = new StringBuilder("Longest Increasing Subsequence: ");
        for (int i = subsequence.size() - 1; i >= 0; i--) {
            sb.append(subsequence.get(i)).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) {
        //int[] arr = {10, 22, 9, 33, 21, 50, 41, 60};
        //int[] arr = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        int[] arr = {1, 5, 4, 3, 2, 6, 7, 10,8,9};

        System.out.println("Length of LIS is " + lengthOfLIS(arr));
    }
}

