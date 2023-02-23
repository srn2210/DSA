import java.util.Arrays;
class MergeSort {
    void conquer(int[] arr, int[] aux, int l, int mid, int r) {
        for(int i=l; i<=r; i++) aux[i] = arr[i];
        int left = l;
        int right = mid;
        l = mid+1;
        int i = left;
        while(l <= r && left <= right) {
            if(aux[l] < aux[left]) arr[i++] = aux[l++];
            else arr[i++] = aux[left++];
        }
        while(l <= r) arr[i++] = aux[l++];
        while(left <= right) arr[i++] = aux[left++];
    }
    void divide(int[] arr, int[] aux, int l, int r) {
        if(l >= r) return;
        int mid = l + (r-l) / 2;
        divide(arr, aux, l, mid);
        divide(arr, aux, mid+1, r);
        conquer(arr, aux, l, mid, r);
    }
    void mergeSort(int[] arr) {
        int[] aux = new int[arr.length];
        divide(arr, aux, 0, arr.length-1);
    }
    public static void main(String[] args) {
        MergeSort obj = new MergeSort();
        int[] arr = new int[]{199, 28, 191, 20, 100};
        obj.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}