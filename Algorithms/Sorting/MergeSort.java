package Sorting;

import java.util.Arrays;
class MergeSort {
    // MergeSort - Divide and Conquer Algorithm

    // MergeSort works by dividing the array into halves recursively until the array can no longer be divided -
    // - and then merges the all divided arrays in sorted order. This divide and conquer method results in a -
    // - very efficient running time and is also very stable.

    // Time Complexity - O(n log n) (O(log n) for the divide and O(n) for the merge)
    // Space Complexity - O(n) (for an auxiliary array for the merging and also the recursion call stack)

    void conquer(int[] arr, int[] aux, int l, int mid, int r) {                         // Merge function for the two sorted arrays
        for(int i=l; i<=r; i++) aux[i] = arr[i];                                        // Copying over all values into the auxiliary array
        int left = l;                                                                   // Left bounds and right bounds of the sorted array1
        int right = mid;
        l = mid+1;                                                                      // Left bounds and right bounds of the sorted array2
        int i = left;                                                                   // Current index of the original array, which will have -
                                                                                        // - the sorted output at the end.
        while(l <= r && left <= right) {                                                // Merging two sorted arrays logic
            if(aux[l] < aux[left]) arr[i++] = aux[l++];                                 // We copy over the lower value at left pointer of array1
            else arr[i++] = aux[left++];                                                // - or array2 to current index of the original array -
        }                                                                               // - and increment the current and left pointer
        while(l <= r) arr[i++] = aux[l++];                                              // We then check if any of the values are leftover, and -
        while(left <= right) arr[i++] = aux[left++];                                    // - copy them over, because they are already in sorted order.
    }
    void divide(int[] arr, int[] aux, int l, int r) {                                   // Dividing the array into halves
        if(l >= r) return;                                                              // Base Case - only one element in the array
        int mid = l + (r-l) / 2;                                                        // Dividing the array based on the middle index
        divide(arr, aux, l, mid);                                                       // Dividing the array into left and right parts -
        divide(arr, aux, mid+1, r);                                                     // - and merging the resulting sorted array in the -
        conquer(arr, aux, l, mid, r);                                                   // - conquer function call, implemented above.
    }
    void mergeSort(int[] arr) {                                                         // Wrapper function for the MergeSort algorithm
        int[] aux = new int[arr.length];                                                // Creating the auxiliary array
        divide(arr, aux, 0, arr.length-1);                                            // Initial bounds encompassing all the values in the array
    }
    public static void main(String[] args) {
        MergeSort obj = new MergeSort();                                                // Class object creation, alternative being static methods
        int[] arr = new int[]{199, 28, 191, 20, 100};                                   // Initializing the array to be sorted
        obj.mergeSort(arr);                                                             // MergeSort function call
        System.out.println(Arrays.toString(arr));                                       // Printing the sorted array
    }
}