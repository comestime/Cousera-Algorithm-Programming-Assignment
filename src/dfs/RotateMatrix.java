/*
Rotate an N * N matrix clockwise 90 degrees.

Assumptions

The matrix is not null and N >= 0
Examples

{ {1,  2,  3}

  {8,  9,  4},

  {7,  6,  5} }

after rotation is

{ {7,  8,  1}

  {6,  9,  2},

  {5,  4,  3} }
 */

package dfs;

public class RotateMatrix {
	public void rotate(int[][] matrix) {
		if (matrix == null || matrix.length <= 1) return;		
		rotateMatrix(matrix, 0, matrix.length - 1);
	}
	
	private void rotateMatrix(int[][] matrix, int lower, int upper) {
		if (lower >= upper) return;
		
		for (int i = 0; i < upper - lower; i++) {
			int temp = matrix[lower][lower + i];
			matrix[lower][lower + i] = matrix[upper - i][lower];
			matrix[upper - i][lower] = matrix[upper][upper - i];
			matrix[upper][upper - i] = matrix[lower + i][upper];
			matrix[lower + i][upper] = temp;
		}
		
		rotateMatrix(matrix, lower + 1, upper - 1);
	}
	
	public static void main(String[] args) {
		RotateMatrix s = new RotateMatrix();
		int [][] test = new int[][] { {1,  2,  3},
		  							  {8,  9,  4},
		  							  {7,  6,  5} };
		s.rotate(test);
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test.length; j++) {
				System.out.print(test[i][j] + " ");
			}
			System.out.println();
		}
	}
}
