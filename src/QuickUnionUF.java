import java.util.Arrays;

public class QuickUnionUF {
    // arr[i] is the parent of index i
    private final int[] arr;

    public QuickUnionUF(int N){ // Constructor
        arr = new int[N];
        for(int i = 0; i < N; i++)
            arr[i] = i;
    }

    private int root(int i) {
        // Returns the root of node i
        /* will loop through all parents until we find
        * one parent with arr[i] == i, thus its a root */
        while (arr[i] != i)
            i = arr[i];
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        // Set parent of p's root to the q's root
        int i = root(p);
        arr[i] = root(q);
    }

    public static void main(String[] args) {
        QuickUnionUF q = new QuickUnionUF(10);
        q.union(4, 3);
        q.union(3, 8);
        q.union(6, 5);
        q.union(9, 4);
        q.union(2, 1);
        System.out.println(q.connected(8,9)); // true
        System.out.println(q.connected(4,9)); // true
        System.out.println(q.connected(5,4)); // false
        q.union(5, 0);
        q.union(7, 2);
        q.union(6, 1);
        q.union(7, 3);
        System.out.println(Arrays.toString(q.arr));
        // [1, 8, 1, 8, 3, 0, 5, 1, 8, 8]
    }
}
