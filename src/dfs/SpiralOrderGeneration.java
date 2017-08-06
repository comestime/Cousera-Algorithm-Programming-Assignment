/*
Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

Assumptions

The 2D array is not null and has size of M * N where M, N >= 0
Examples

{ {1,  2,  3,  4},

  {5,  6,  7,  8},

  {9, 10, 11, 12} }

the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
 */

package dfs;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderGeneration {
	public List<Integer> spiral(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		if (matrix.length == 0 || matrix[0].length == 0) return result;
		helper(matrix, 0, matrix[0].length - 1, 0, matrix.length - 1, result);
		return result;
	}
		  
	private void helper(int[][]matrix, int left, int right, int upper, int lower, List<Integer> result) {
		// base case 1: nothing left
		if (left > right || upper > lower) return;
		// base case 2: one column left
		if (left == right) {
			for (int i = upper; i <= lower; i++) {
		        result.add(matrix[i][left]);
		    }
		    return;
		}
		// base case 3: one row left
		if (upper == lower) {
		   for (int i = left; i <= right; i++) {
		       result.add(matrix[lower][i]);
		    }
		    return;
		}
		   
		// step 1: upper edge
		for (int i = left; i < right; i++) {
			result.add(matrix[upper][i]);
		}    
		// step 2: right edge
		for (int i = upper; i < lower; i++) {
		    result.add(matrix[i][right]);
		}
		// step 3: lower edge
		for (int i = right; i > left; i--) {
		    result.add(matrix[lower][i]);
		}
		// step 4: left edge
		for (int i = lower; i > upper; i--) {
		    result.add(matrix[i][left]);
		}
		    
		helper(matrix, left + 1, right - 1, upper + 1, lower - 1, result);
	}
}
