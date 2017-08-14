/*
Given a matrix that contains integers, find the submatrix with the largest sum.

Return the sum of the submatrix.

Assumptions

The given matrix is not null and has size of M * N, where M >= 1 and N >= 1
Examples

{ {1, -2, -1, 4},

  {1, -1,  1, 1},

  {0, -1, -1, 1},

  {0,  0,  1, 1} }

the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
 */

package dp;

public class LargestSubMatrixSum {
	public int largest(int[][] matrix) {
		// return primitive(matrix);
		// return dp1(matrix);
		// return dp2(matrix);
		return dp3(matrix);
	}
	
	// method 4: DP3; Time complexity: O(n^3)
	public int dp3(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		// step 1: preprocessing, calcualte 1D prefix-sum
		// preSum[i][j] represents the sum from matrix[i][j] to matrix[0][j]
		int[][] preSum = new int[row][col];
		for (int j = 0; j < col; j++) {
			preSum[0][j] = matrix[0][j];
			for (int i = 1; i < row; i++) {
				preSum[i][j] = preSum[i - 1][j] + matrix[i][j];
			}
		}
		
		// step 2: find the globalMax
		// key concept: only constraint the row, and then convert the problem to find 1D sub array with max sum
		int globalMax = Integer.MIN_VALUE;
		int[] OneDArray = new int[col];
		for (int i = 0; i < row; i++) {
			for (int j = i; j < row; j++) {
				// step 2.1: get the 1D array
				for (int m = 0; m < col; m++) {
					OneDArray[m] = preSum[j][m] - preSum[i][m] + matrix[i][m];
				}
				
				// step 2.2: get the largest sub array sum
				int curMax = FindLargestSubArraySum(OneDArray);
				if (curMax > globalMax) {
					globalMax = curMax;
				}
			}
		}
		
		return globalMax;
	}
	
	private int FindLargestSubArraySum(int[] array) {
		if (array == null || array.length == 0) return 0;
		
		int lastMax = array[0];
		int globalMax = array[0];
		for (int i = 1; i < array.length; i++) {
			if (lastMax < 0) {
				lastMax = array[i];
			} else {
				lastMax += array[i];
			}
			
			if (globalMax < lastMax) {
				globalMax = lastMax;
			}
		}
		
		return globalMax;
	}
	
	// method 3: DP2, prefix-sum in 2D. Time complexity: O(n^4)
	public int dp2(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		// step 1: preprocessing, calculate 2D prefix-sum
		// preSum[i][j] represents the sum of all elements inside the rectangle, defined by the points:
		// (0, 0), (0, j), (i, 0), (i, j)
		int[][] preSum = new int[row][col];
		preSum[0][0] = matrix[0][0];
		for (int i = 1; i < row; i++) {
			preSum[i][0] = preSum[i - 1][0] + matrix[i][0];
		}
		
		for (int j = 1; j < col; j++) {
			preSum[0][j] = preSum[0][j - 1] + matrix[0][j];
		}
		
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i][j];
			}
		}
		
		/*
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(preSum[i][j] + " ");
			}
			System.out.println();
		}
		*/
		
		// step 2: find the globalMax
		int globalMax = Integer.MIN_VALUE;
		for (int i = 0; i < row; i++) {
			for (int j = i; j < row; j++) {
				for (int m = 0; m < col; m++) {
					for (int n = m; n < col; n++) {
						// corner case, i == 0 || m == 0
						int term1 = i > 0 ? preSum[i - 1][n] : 0;
						int term2 = m > 0 ? preSum[j][m - 1] : 0;
						int term3 = i > 0 && m > 0 ? preSum[i - 1][m - 1] : 0;
						int cur = preSum[j][n] - term1 - term2 + term3;
						if (cur > globalMax) {
							globalMax = cur;
						}
					}
				}
			}
		}
		
		return globalMax;
	}
	
	// method 2: DP1, prefix-sum in 1D. Time complexity: O(n^5)
	public int dp1(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		// Step 1: preprocessing, calculate 1D prefix-sum
		// preSum[i][j] represents the sum from matrix[i][0] to matrix[i][j]
		int[][] preSum = new int[row][col];
		for (int i = 0; i < row; i++) {
			preSum[i][0] = matrix[i][0];
			for (int j = 1; j < col; j++) {
				preSum[i][j] = preSum[i][j - 1] + matrix[i][j];
			}
		}
		
		// Step 2: find globalMax
		int globalMax = Integer.MIN_VALUE;
		for (int i = 0; i < row; i++) {
			for (int j = i; j < row; j++) {
				for (int m = 0; m < col; m++) {
					for (int n = m; n < col; n++) {
						int cur = 0;
						for (int x = i; x <= j; x++) {
							cur += preSum[x][n] - preSum[x][m] + matrix[x][m];
						}
						if (cur > globalMax) {
							globalMax = cur;
						}
					}
				}
			}
		}
		
		return globalMax;
	}
	
	// method 1: primitive/bruto force way, time complexity: O(n^6)
	public int primitive(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int globalMax = Integer.MIN_VALUE;
		for (int i = 0; i < row; i++) {
			for (int j = i; j < row; j++) {
				for (int m = 0; m < col; m++) {
					for (int n = m; n < col; n++) {
						int cur = 0;
						for (int x = i; x <= j; x++) {
							for (int y = m; y <= n; y++) {
								cur += matrix[x][y];
							}
						}
						if (cur > globalMax) {
							globalMax = cur;
						}
					}
				}
			}
		}
		
		return globalMax;
	}
	
	public static void main(String[] args) {
		LargestSubMatrixSum s = new LargestSubMatrixSum();
		int[][] test = new int[][] {{1, -2, -1, 4},
			  						{1, -1,  1, 1},
			  						{0, -1, -1, 1},
			  						{0,  0,  1, 1}};
		System.out.println(s.largest(test));
	}
}
