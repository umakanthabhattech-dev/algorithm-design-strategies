package Sorting;

public class QuickSort {

    // Main function to sort an array using QuickSort
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Partition the array
            int pivotIndex = partition(array, low, high);
            // Recursively sort elements before and after partition
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Function to partition the array
    private static int partition(int[] array, int low, int high) {
        // Choosing the first element as pivot
        int pivot = array[low];
        int left = low + 1;
        int right = high;

        while (left <= right) {
            // Increment left index while the element is less than the pivot
            while (left <= high && array[left] <= pivot) {
                left++;
            }
            // Decrement right index while the element is greater than the pivot
            while (right >= low && array[right] > pivot) {
                right--;
            }
            // Swap elements if left is less than right
            if (left < right) {
                swap(array, left, right);
            }
        }
        // Swap the pivot element with the right index
        swap(array, low, right);
        return right; // Return the pivot index
    }

    // Function to swap two elements in the array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Main method to test the QuickSort implementation
    public static void main(String[] args) {
        int[] array = {15, 19, 34, 41, 2, 8, 23};
        quickSort(array, 0, array.length - 1);
        System.out.println("Sorted array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
/*
Time Complexity:
Average and Best Case: O(nlogn) If pivote element sorted at middle always

Worst Case:
O(n2) (occurs when the pivot is consistently the largest or smallest element).

Space Complexity:
O(logn) due to recursive stack usage. its a tree height
 */