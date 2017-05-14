package pa1_percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	// QuickUnion Model
	private WeightedQuickUnionUF quickUnion;
	// N x N grid
	private boolean [][] grid;
	private int n;
	
	public Percolation(int n)  {              // create n-by-n grid, with all sites blocked
		if (n <= 0) { throw new java.lang.IllegalArgumentException("grid size n should be larger than 0!"); }
		quickUnion = new WeightedQuickUnionUF(n * n + 2);	// virtual sites for top row and bottom row
		this.n = n;
		// connect the vitual sites to top row and bottom row
		for (int i = 1; i <= n; i++) {
			quickUnion.union(0, i);
			quickUnion.union(n * n + 1, n * n + 1 - i);
		}
		// initialize the N x N grid
		grid = new boolean [n][n];
	}
	
	private int checkIndex(String String, int index) {
		if (index < 1 || index > n) { 
			throw new java.lang.IndexOutOfBoundsException(String + " index out of bound!");
		}
		return index - 1;
	}
	
	public void open(int row, int col) {   // open site (row, col) if it is not open already
		row = checkIndex("Row", row);
		col = checkIndex("Column", col);
		// update grid
		grid[row][col] = true;
		// update quickUnion array
		if (row != 0 && grid[row - 1][col]) {
			quickUnion.union(row * n + col + 1, (row - 1) * n + col + 1);
		}
		if (row != (n - 1) && grid[row + 1][col]) {
			quickUnion.union(row * n + col + 1, (row + 1) * n + col + 1);
		}
		if (col != 0 && grid[row][col - 1]) {
			quickUnion.union(row * n + col + 1, row * n + col);
		}
		if (col != (n - 1) && grid[row][col + 1]) {
			quickUnion.union(row * n + col + 1, row * n + col + 2);
		}
	}
	   
	public boolean isOpen(int row, int col) { // is site (row, col) open?
		row = checkIndex("Row", row);
		col = checkIndex("Column", col);
		return grid[row][col];
	}
	   
	public boolean isFull(int row, int col) {  // is site (row, col) full?
		row = checkIndex("Row", row);
		col = checkIndex("Column", col);
		return grid[row][col] && quickUnion.connected(0, row * n + col + 1);
	}
	   
	public int numberOfOpenSites() {      // number of open sites
		int count = 0;
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < n; j++) {
				if (grid[i][j]) count++;
			}
		return count;
	}
	   
	public boolean percolates() {             // does the system percolate?
		// test the connectivity of the two virtual sites
		return quickUnion.connected(0, n * n + 1);
	}
	   
	public static void main(String[] args) {   // test client (optional)
	   
	}
}
