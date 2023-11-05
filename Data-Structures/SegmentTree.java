public class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        buildTree(arr, 0, arr.length-1, 0);
    }

    int buildTree(int[] arr, int left, int right, int idx) {
        if(left == right) return tree[idx] = arr[left];
        int mid = (left + right) / 2;
        int leftSum = buildTree(arr, left, mid, 2 * idx + 1);
        int rightSum = buildTree(arr, mid + 1, right, 2 * idx + 2);
        return tree[idx] = leftSum + rightSum;
    }

    int query(int left, int right, int tLeft, int tRight, int idx) {
        if(tLeft > right || tRight < left) return 0;
        if(left <= tLeft && right >= tRight) return tree[idx];
        int tMid = (tLeft + tRight) / 2;
        int leftSum = query(left, right, tLeft, tMid, 2 * idx + 1);
        int rightSum = query(left, right, tMid + 1, tRight, 2 * idx + 2);
        return tree[idx] = leftSum + rightSum;
    }

    int query(int left, int right) {
        return query(left, right, 0, n-1, 0);
    }

    int update(int left, int right, int idx, int val, int index) {
        if(right < index || left > index) return tree[idx];
        if(left == right && left == index) return tree[idx] = val;
        int mid = (left + right) / 2;
        int leftSum = update(left, mid, 2 * idx + 1, val, index);
        int rightSum = update(mid + 1, right, 2 * idx + 2, val, index);
        return tree[idx] = leftSum + rightSum;
    }

    void update(int index, int val) {
        update(0, n-1, 0, val, index);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{234, 5435, 543523, 234234, 6556, 6767, 44354, 423423, 6456567};

        SegmentTree tree = new SegmentTree(arr);

        System.out.println(tree.query(0, 3));

        tree.update(0, 10);

        System.out.println(tree.query(0, 3));

    }
}
