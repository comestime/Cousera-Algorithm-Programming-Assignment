/*
Merge K sorted array into one big sorted array in ascending order.

Assumptions

The input arrayOfArrays is not null, none of the arrays is null either.
 */

package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedArray {
	public int[] merge(int[][] arrayOfArrays) {
		if (arrayOfArrays == null || arrayOfArrays.length == 0) {
			return new int[]{};
		}
		
		int length = 0;
		Queue<Cell> queue = new PriorityQueue<>();
		for (int i = 0; i < arrayOfArrays.length; i++) {
			int[] array = arrayOfArrays[i];
			length += array.length;
			if (array.length != 0) {
				queue.offer(new Cell(i, 0, array[0]));
			}
		}
		
		int[] newArray = new int[length];
		int cur = 0;
		while (!queue.isEmpty()) {
			Cell temp = queue.poll();
			newArray[cur++] = temp.value;
			if (temp.col < arrayOfArrays[temp.row].length - 1) {
				// reuse the same object but advance the index by 1: more efficient
				// queue.offer(new Cell(temp.row, temp.col + 1, arrayOfArrays[temp.row][temp.col + 1]));
				temp.col++;
				temp.value = arrayOfArrays[temp.row][temp.col];
				queue.offer(temp);
			}
		}
		
		return newArray;
	}
	
	public class Cell implements Comparable<Cell> {
		private int row;
		private int col;
		private int value;
		
		public Cell(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
		
		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			if (value == o.value) return 0;
			return value < o.value ? -1 : 1;
		}
		
	}
}
