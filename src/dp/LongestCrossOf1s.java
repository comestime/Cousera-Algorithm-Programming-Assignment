/*
Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s, with the same arm lengths and the four arms joining at the central point.

Return the arm length of the largest cross.

Assumptions

The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
Examples

{ {0, 0, 0, 0},

  {1, 1, 1, 1},

  {0, 1, 1, 1},

  {1, 0, 1, 1} }

the largest cross of 1s has arm length 2.
 */

package dp;

public class LongestCrossOf1s {
	public int largest(int[][] matrix) {
		int row = matrix.length;
		if (row == 0) return 0;
		int col = matrix[0].length;
		if (col == 0) return 0;
		
		// step 1: preprocessing
		// 1st direction: for each element in r2l[i][j], how many consecutive 1s it sees in the right hand side, including itself
		int[][] r2l = new int[row][col];
		for (int i = 0; i < row; i++) {
			r2l[i][col - 1] = matrix[i][col - 1];
			for (int j = col - 2; j >= 0; j--) {
				if (matrix[i][j] != 0) {
					r2l[i][j] = r2l[i][j + 1] + 1;
				} else {
					r2l[i][j] = 0;
				}
			}
		}
		System.out.println("Step 1.1 done!");
		
		// 2nd direction: for each element in l2r[i][j], how many consecutive 1s it sees in the left hand side, including itself
		int[][] l2r = new int[row][col];
		for (int i = 0; i < row; i++) {
			l2r[i][0] = matrix[i][0];
			for (int j = 1; j < col; j++) {
				if (matrix[i][j] != 0) {
					l2r[i][j] = l2r[i][j - 1] + 1;
				} else {
					l2r[i][j] = 0;
				}
			}
		}
		System.out.println("Step 1.2 done!");
		
		// 3rd direction: for each element in u2d[i][j], how man consecutive 1s it sees above it, including itself
		int[][] u2d = new int[row][col];
		for (int j = 0; j < col; j++) {
			u2d[0][j] = matrix[0][j];
			for (int i = 1; i < row; i++) {
				if (matrix[i][j] != 0) {
					u2d[i][j] = u2d[i - 1][j] + 1;
				} else {
					u2d[i][j] = 0;
				}
			}
		}
		System.out.println("Step 1.3 done!");
		
		// 4th direction: for each element in d2u[i][j], how many consecutive 1s it sees below it, including itself
		int[][] d2u = new int[row][col];
		for (int j = 0; j < col; j++) {
			d2u[row - 1][j] = matrix[row - 1][j];
			for (int i = row - 2; i >= 0; i--) {
				if (matrix[i][j] != 0) {
					d2u[i][j] = d2u[i + 1][j] + 1;
				} else {
					d2u[i][j] = 0;
				}
			}
		}
		System.out.println("Step 1.4 done!");
		
		// step 2: find the largest cross of 1s for each element
		int globalMax = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int cur = Math.min(Math.min(l2r[i][j], r2l[i][j]), Math.min(u2d[i][j], d2u[i][j]));
				if (globalMax < cur) {
					globalMax = cur;
				}
			}
		}
		
		return globalMax;
	}
	
	public static void main(String[] args) {
		LongestCrossOf1s s = new LongestCrossOf1s();
		int[][] test = new int[][]{{0,1,1,1,1},{1,0,1,1,1},{0,0,0,1,1}};
		System.out.println(s.largest(test));
	}
}
