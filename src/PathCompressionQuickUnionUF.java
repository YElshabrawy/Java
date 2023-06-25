public class PathCompressionQuickUnionUF {
    // arr[i] is the parent of index i
    private final int[] arr;
    private final int[] sz; // to count the number of objects rooted at i

    public PathCompressionQuickUnionUF(int N){ // Constructor
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
        while (arr[i] != i){
            arr[i] = arr[arr[i]];
            i = arr[i];
        }
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
}
