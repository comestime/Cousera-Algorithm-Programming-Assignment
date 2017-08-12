/*
Given a matrix that contains only 1s and 0s, find the largest X shape which contains only 1s, with the same arm lengths and the four arms joining at the central point.

Return the arm length of the largest X shape.

Assumptions

The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
Examples

{ {0, 0, 0, 0},

  {1, 1, 1, 1},

  {0, 1, 1, 1},

  {1, 0, 1, 1} }

the largest X of 1s has arm length 2.
 */

package dp;

public class LargestXOf1s {

	public int largest(int[][] matrix) {
		int row = matrix.length;
		if (row == 0) return 0;
		int col = matrix[0].length;
		if (col == 0) return 0;
		
		// step 1: preprocessing
		// 1st direction: for each element in ur2ll[i][j], how many consecutive 1s it sees in the upper right side, including itself
		int[][] ur2ll = new int[row][col];
		for (int i = 0; i < row; i++) {
			ur2ll[i][col - 1] = matrix[i][col - 1];
		}
		for (int j = 0; j < col; j++) {
			ur2ll[0][j] = matrix[0][j];
		}
		for (int i = 1; i < row; i++) {
			for (int j = col - 2; j >= 0; j--) {
				ur2ll[i][j] = matrix[i][j] == 0 ? 0 : ur2ll[i - 1][j + 1] + 1;
			}
		}
		// System.out.println("Step 1.1 done!");
		
		// 2nd direction: for each element in ll2ur[i][j], how many consecutive 1s it sees in the lower left side, including itself
		int[][] ll2ur = new int[row][col];
		for (int i = row - 1; i >= 0; i--) {
			ll2ur[i][0] = matrix[i][0];
		}
		for (int j = 0; j < col; j++) {
			ll2ur[row - 1][j] = matrix[row - 1][j];
		}
		for (int i = row - 2; i >= 0; i--) {
			for (int j = 1; j < col; j++) {
				ll2ur[i][j] = matrix[i][j] == 0 ? 0 : ll2ur[i + 1][j - 1] + 1;
			}
		}
		// System.out.println("Step 1.2 done!");
		
		// 3rd direction: for each element in ul2lr[i][j], how man consecutive 1s it sees in the upper left side, including itself
		int[][] ul2lr = new int[row][col];
		for (int i = 0; i < row; i++) {
			ul2lr[i][0] = matrix[i][0];
		}
		for (int j = 0; j < col; j++) {
			ul2lr[0][j] = matrix[0][j];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				ul2lr[i][j] = matrix[i][j] == 0 ? 0 : ul2lr[i - 1][j - 1] + 1;
			}
		}
		// System.out.println("Step 1.3 done!");
		
		// 4th direction: for each element in lr2ul[i][j], how many consecutive 1s it sees in the lower right side, including itself
		int[][] lr2ul = new int[row][col];
		for (int i = 0; i < row; i++) {
			lr2ul[i][col - 1] = matrix[i][col - 1];
		}
		for (int j = 0; j < col; j++) {
			lr2ul[row - 1][j] = matrix[row - 1][j];
		}
		for (int i = row - 2; i >= 0; i--) {
			for (int j = col - 2; j >= 0; j--) {
				lr2ul[i][j] = matrix[i][j] == 0 ? 0 : lr2ul[i + 1][j + 1] + 1;
			}
		}
		// System.out.println("Step 1.4 done!");
		
		// step 2: find the largest cross of 1s for each element
		int globalMax = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int cur = Math.min(Math.min(ll2ur[i][j], ur2ll[i][j]), Math.min(ul2lr[i][j], lr2ul[i][j]));
				if (globalMax < cur) {
					globalMax = cur;
				}
			}
		}
		
		return globalMax;
	}
	
	public static void main(String[] args) {
		LongestCrossOf1s s = new LongestCrossOf1s();
		int[][] test = new int[][]{{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 0, 1, 1}};
		System.out.println(s.largest(test));
	}
}
