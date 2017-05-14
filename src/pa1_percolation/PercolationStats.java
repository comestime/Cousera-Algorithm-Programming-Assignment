package pa1_percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int n;
	private int trials;
	private double [] results;
	private Percolation [] percolation;
	
	public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) { 
			throw new java.lang.IllegalArgumentException("Illegal n or trial value!");
		}
		this.n = n;
		this.trials = trials;
		this.results = new double [trials];
		percolation = new Percolation [trials];
		for (int i = 0; i < trials; i++) {
			percolation[i] = new Percolation(n);
		}
	}
	
	public double mean() {                         // sample mean of percolation threshold
		return StdStats.mean(results);
	}
	
	public double stddev()  {                      // sample standard deviation of percolation threshold
		return StdStats.stddev(results);
	}
	
	public double confidenceLo()  {                // low  endpoint of 95% confidence interval
		return mean() - 1.96 * Math.sqrt(stddev() / trials);
	}
	
	public double confidenceHi()  {                // high endpoint of 95% confidence interval
		return mean() + 1.96 * Math.sqrt(stddev() / trials);
	}

	public static void main(String[] args)  {      // test client (described below)
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		if (n <= 0 || trials <= 0) { 
			throw new java.lang.IllegalArgumentException("Illegal n or trial value!");
		}
		PercolationStats stats = new PercolationStats(n, trials);
		
		// perform tests
		for (int i = 0; i < trials; i++) {
			// stats.percolation[i] = new Percolation(n);
			while (!stats.percolation[i].percolates()) {
				int rand = StdRandom.uniform(n * n);
				int row = rand / stats.n + 1;
				int col = rand % stats.n + 1;
				if (stats.percolation[i].isOpen(row, col)) continue;
				stats.percolation[i].open(row, col);
			}
			stats.results[i] = (double) stats.percolation[i].numberOfOpenSites() / (double) (n * n);
		}
		System.out.println("mean = " + stats.mean());
		System.out.println("stddev = " + stats.stddev());
		System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
	}
}
