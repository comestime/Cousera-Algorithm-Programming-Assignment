/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */

package heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Search2DMatrix {
	
	// method 2: similar concept of search 2D sorted matrix
	// this method may exceed time limit if the 2D matrix is too huge
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row_count = matrix.length;
        int col_count = matrix[0].length;
        Queue<Cell> pq = new PriorityQueue<Cell>();
        Set<Cell> set = new HashSet<Cell>();
        Cell newCell = new Cell(0, 0, matrix[0][0]);
        pq.offer(newCell);
        set.add(newCell);
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            if (cur.val == target) return true;
            if (cur.row + 1 < row_count) {
                newCell = new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]);
                if (set.add(newCell)) {
                    pq.offer(newCell);                   
                }
            }
            if (cur.col + 1 < col_count) {
                newCell = new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]);
                if (set.add(newCell)) {
                    pq.offer(newCell);
                }
            }
        }
        return false;
    }
    
    private class Cell implements Comparable<Cell>{
        private int row;
        private int col;
        private int val;
    
        public Cell(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
        
        public int compareTo(Cell another) {
            if (this.val == another.val) return 0;
            return this.val < another.val ? -1 : 1;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + col;
			result = prime * result + row;
			result = prime * result + val;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			if (val != other.val)
				return false;
			return true;
		}

		private Search2DMatrix getOuterType() {
			return Search2DMatrix.this;
		}        
    }
}
