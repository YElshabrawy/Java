import java.util.Arrays;

public class WeightedQuickUnionUF {
    // arr[i] is the parent of index i
    private final int[] arr;
    private final int[] sz; // to count the number of objects rooted at i

    public WeightedQuickUnionUF(int N){ // Constructor
        arr = new int[N];
        sz = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = i;
            sz[i] = 1;
        }
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
        return root(p) == root(q); // same as normal Quick-Union
    }

    public void union(int p, int q) {
        // Set parent of the smaller root tree to the larger root tree
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[j] > sz[i]) {
            arr[i] = j;
            sz[j] += sz[i];
        }
        else {
            arr[j] = i;
            sz[i] += sz[j];
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnionUF q = new WeightedQuickUnionUF(10);
        q.union(4, 3);
        q.union(3, 8);
        q.union(6, 5);
        q.union(9, 4);
        q.union(2, 1);
        q.union(5, 0);
        q.union(7, 2);
        q.union(6, 1);
        q.union(7, 3);
        System.out.println(Arrays.toString(q.arr));
        // [6, 2, 6, 4, 6, 6, 6, 2, 4, 4]
        System.out.println(Arrays.toString(q.sz));
        // [1, 1, 3, 1, 4, 1, 10, 1, 1, 1]

    }
}
