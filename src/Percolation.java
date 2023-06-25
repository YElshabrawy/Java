import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final WeightedQuickUnionUF grid;
    private final int[] stateArr; // 0 => blocked, 1 => opened
    private final int N;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        N = n;
        openSites = 0;
        grid = new WeightedQuickUnionUF(n * n + 2);
        stateArr = new int[n * n + 2]; // initialized to zeros
        // Connect virtual nodes to the first row and last row
        for (int i = 1; i <= n; i++) {
            grid.union(0, i);
            grid.union((n * n + 1), (n * n) - n + i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (!isOpen(row, col)) {
            stateArr[getIndexFromRC(row, col)] = 1;
            openSites++;
            fixUnion(row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return (stateArr[getIndexFromRC(row, col)] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int idx = getIndexFromRC(row, col);
        return grid.connected(0, idx) && isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(0, N * N + 1);
    }

    // validate that p is a valid index
    private void validate(int p) {
        if (p < 1 || p > N) {
            throw new IllegalArgumentException("index " + p + " is not between 1 and " + (N));
        }
    }

    // it checks the up, down, left, and right of the current "opened" site
    // and union if they are open as well
    private int getIndexFromRC(int row, int col) {
        if (row < 1 || col < 1 || row > N || col > N)
            return -1;
        return ((row - 1) * N) + col;
    }

    private void fixUnion(int row, int col) {
        int currIdx = getIndexFromRC(row, col);
        int upIdx = getIndexFromRC(row - 1, col);
        if (upIdx >= 0 && isOpen(row - 1, col))
            grid.union(currIdx, upIdx);

        int downIdx = getIndexFromRC(row + 1, col);
        if (downIdx >= 0 && isOpen(row + 1, col))
            grid.union(currIdx, downIdx);

        int leftIdx = getIndexFromRC(row, col - 1);
        if (leftIdx >= 0 && isOpen(row, col - 1))
            grid.union(currIdx, leftIdx);

        int rightIdx = getIndexFromRC(row, col + 1);
        if (rightIdx >= 0 && isOpen(row, col + 1))
            grid.union(currIdx, rightIdx);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}