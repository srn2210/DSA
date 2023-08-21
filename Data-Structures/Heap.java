import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Heap {
    List<Integer> arr;
    Heap() {
        arr = new ArrayList<>();
    }
    int parent(int num) {
        return (num-1)/2;
    }
    int left(int num) {
        return (2 * num) + 1;
    }
    int right(int num) {
        return (2 * num) + 2;
    }
    void swap(int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    void siftUp(int idx) {
        while(idx != 0) {
            int par = parent(idx);
            int curr = arr.get(idx);
            int parVal = arr.get(par);
            if(parVal > curr) {
                swap(par, idx);
                idx = par;
            }
            else break;
        }
    }
    void siftDown(int num) {
        while(num < arr.size()) {
            int left = left(num);
            int right = right(num);
            int idx = num;
            int curr = arr.get(num);
            int leftVal = left < arr.size() ? arr.get(left) : (int)1e9;
            int rightVal = right < arr.size() ? arr.get(right) : (int)1e9;
            if(leftVal < curr) {
                curr = leftVal;
                idx = left;
            }
            if(rightVal < curr) {
                idx = right;
            }
            if(idx != num) {
                swap(idx, num);
                num = idx;
            }
            else break;
        }
    }
    void add(int num) {
        arr.add(num);
        int last = arr.size()-1;
        siftUp(last);
    }
    int remove() {
        int ans = arr.get(0);
        swap(0, arr.size()-1);
        arr.remove(arr.size()-1);
        siftDown(0);
        return ans;
    }
    int peek() {
        return arr.get(0);
    }
    int size() {
        return arr.size();
    }
    boolean empty() {
        return size() == 0;
    }
    public String toString() {
        return arr.toString();
    }
    void siftDown(int num, int[] heap) {
        while(num < heap.length) {
            int left = left(num);
            int right = right(num);
            int idx = num;
            int curr = heap[num];
            int leftVal = left < heap.length ? heap[left] : (int)1e9;
            int rightVal = right < heap.length ? heap[right] : (int)1e9;
            if(leftVal < curr) {
                curr = leftVal;
                idx = left;
            }
            if(rightVal < curr) {
                idx = right;
            }
            if(idx != num) {
                int t = heap[idx];
                heap[idx] = heap[num];
                heap[num] = t;
                num = idx;
            }
            else break;
        }
    }
    void heapify(int[] heap) {
        for(int i=heap.length/2-1; i>=0; i--) {
            siftDown(i, heap);
        }
    }

    public static void main(String[] args) {
        Heap pq = new Heap();
        pq.add(67);
        pq.add(2);
        pq.add(99);
        pq.add(1);
        System.out.println(pq.peek());
        pq.add(-1);
        pq.add(99);
        pq.add(99);
        System.out.println(pq);
        while(!pq.empty()) {
            System.out.println(pq.remove());
        }
        int[] arr = new int[]{1,-1, 99, 54, 65, 35, 32, 22, 101, 102, -101, 79};
        pq.heapify(arr);
        System.out.println(Arrays.toString(arr));
    }
}
