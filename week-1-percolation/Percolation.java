/**
 * Created by qianpeili on 2017/8/7.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int size;
    private final int topSite;
    private final int bottomSite;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF fullUf;
    private boolean[] sites;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        fullUf = new WeightedQuickUnionUF(n * n + 2);
        size = n;
        topSite = 0;
        bottomSite = n * n + 1;
        sites = new boolean[n * n + 2];
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if (StdRandom.uniform() <= 1) {
                    p.open(i, j);
                }
            }
        }
        System.out.println(p.percolates());
    }

    private boolean argumentCheck(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            return false;
        }
        return true;
    }

    public boolean isOpen(int row, int col) {
        if (!argumentCheck(row, col)) {
            return false;
        }
        int index = getIndex(row, col);
        return sites[index];
    }

    private int getIndex(int row, int col) {
        return col + (row - 1) * size;
    }

    private void checkUnion(int row, int col) {
        int index = getIndex(row, col);
        // top
        if (row == 1) {
            uf.union(index, 0);
            fullUf.union(index, 0);
        }
        //  up
        if (isOpen(row - 1, col)) {
            uf.union(index, getIndex(row - 1, col));
            fullUf.union(index, getIndex(row - 1, col));
        }
        //  down
        if (isOpen(row + 1, col)) {
            uf.union(index, getIndex(row + 1, col));
            fullUf.union(index, getIndex(row + 1, col));
        }
        //  left
        if (isOpen(row, col - 1)) {
            uf.union(index, getIndex(row, col - 1));
            fullUf.union(index, getIndex(row, col - 1));
        }
        //  right
        if (isOpen(row, col + 1)) {
            uf.union(index, getIndex(row, col + 1));
            fullUf.union(index, getIndex(row, col + 1));
        }
        //  bottom
        if (row == size) {
            uf.union(index, bottomSite);
        }
    }

    public void open(int row, int col) {
        if (!argumentCheck(row, col)) {
            throw new IllegalArgumentException("illegal arguments.");
        }
        if (isOpen(row, col)) {
            return;
        }

        int index = getIndex(row, col);
        sites[index] = true;

        checkUnion(row, col);
    }

    public boolean isFull(int row, int col) {
        if (!argumentCheck(row, col)) {
            throw new IllegalArgumentException("illegal arguments.");
        }
        int index = getIndex(row, col);
        return fullUf.connected(index, topSite);
    }

    public int numberOfOpenSites() {
        int sum = 0;
        for (int i = 1; i <= size * size; i++) {
            if (sites[i]) {
                sum++;
            }
        }
        return sum;
    }

    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }

}
