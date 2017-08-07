import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by qianpeili on 2017/8/7.
 */
public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLow;
    private final double confidenceHigh;

    public PercolationStats(int n, int trials) {
        double[] stats = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = 1 + StdRandom.uniform(n);
                int col = 1 + StdRandom.uniform(n);
                p.open(row, col);
            }
            stats[i] = (double) p.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(stats);
        stddev = StdStats.stddev(stats);
        double tmp = 1.96 * stddev / Math.sqrt(trials);
        confidenceLow = mean - tmp;
        confidenceHigh = mean + tmp;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        int trials = Integer.parseInt(args[1]);
        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0");
        }
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLo());
        System.out.println(ps.confidenceHi());
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLow;
    }

    public double confidenceHi() {
        return confidenceHigh;
    }
}
