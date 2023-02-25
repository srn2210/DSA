import java.util.Arrays;
class QuickSort {
    // QuickSort - Divide and Conquer Algorithm

    // It works by randomly picking any pivot element from the given array and then placing the pivot into its sorted position, and placing -
    // - the values smaller than it to its left, and the values greater than it to its right. We then recursively call this partition and sort -
    // - technique to all the elements in the range to the left of the pivot and to the elements in the range right of the pivot.

    // Time Complexity - Average Case - O(n log n), Worst Case - O(n ^ 2)
    // Space Complexity - Average Case - O(log n), Worst Case - O(n) (This space is for the recursion call stack, otherwise it is constant or in-place)

    void swap(int i, int j, int[] arr) {                                // Utility function for the swap
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    void divide(int[] arr, int left, int right) {                       // Divides the array based on the pivot
        if(left >= right) return;                                       // Base Case - only one element in the array or left bounds exceed the right bounds
        int pivot = (int) (Math.random()*(right-left+1)) + left;        // Calculating the pivot index randomly
        int pivotVal = arr[pivot];

        swap(right, pivot, arr);                                        // Swap the pivot element with the right element to partition the values effectively
        int l = left;
        int r = right-1;                                                // Initialize the right pointer to right-1 to avoid the pivot element
        while(l <= r) {
            if(arr[l] > pivotVal) swap(l, r--, arr);                    // If a greater element is to the left, we swap it with the right
            else l++;
        }
        swap(right, l, arr);                                            // Swap the right element which is the pivot into its sorted position

        divide(arr, left, l-1);                                         // We know now that pivot is in its sorted position and the elements to its -
        divide(arr, l+1, right);                                        // - left are smaller and the elements in the right are greater, so we recurse again.
    }
    void quickSort(int[] arr) {                                         // Wrapper function for the quick sort algorithm
        divide(arr, 0, arr.length-1);
    }
    public static void main(String[] args) {
        QuickSort obj = new QuickSort();                                // Creating the class object, alternatively, we can make the other functions static
        int[] arr = new int[]{101, 29, 33, 107, 95, 96};                // Initializing the array to be sorted array
        obj.quickSort(arr);                                             // QuickSort function call
        System.out.println(Arrays.toString(arr));                       // Printing the array using the toString method in the Arrays class
    }
}