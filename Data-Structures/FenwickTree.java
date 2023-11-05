public class FenwickTree {
    int[] bit;

    FenwickTree(int[] arr) {
        int n = arr.length;
        bit = new int[n+1];
        for(int i=0; i<n; i++) {
            update(i, arr[i]);
        }
    }

    void update(int index, int val) {
        index++;

        while(index < bit.length) {
            bit[index] += val;

            index += index & (-index);
        }
    }

    int query(int index) {
        index++;
        int ans = 0;
        while(index > 0) {
            ans += bit[index];
            index -= index & (-index);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 1, 234, 34, 2546};

        FenwickTree obj = new FenwickTree(arr);

        System.out.println(obj.query(5));

        obj.update(5, 10);

        System.out.println(obj.query(5));
    }
}
