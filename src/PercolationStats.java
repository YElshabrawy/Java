import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int T;
    private final double[] fractions;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must be >= 0");

        fractions = new double[trials];
        T = trials;
        for (int i = 0; i < trials; i++){
            Percolation perc = new Percolation(n);
            while (!perc.percolates()){
            int r = StdRandom.uniformInt(1, n + 1);
            int c = StdRandom.uniformInt(1, n + 1);
            perc.open(r, c);
            }
            double fraction = (double) perc.numberOfOpenSites() / (n * n);
            fractions[i] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() { return StdStats.mean(fractions); }

    // sample standard deviation of percolation threshold
    public double stddev() { return StdStats.stddev(fractions);}

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, T);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }

}