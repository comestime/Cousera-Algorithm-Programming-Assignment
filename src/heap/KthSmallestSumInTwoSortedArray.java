/*
 * Given two sorted arrays A and B, of sizes m and n respectively. Define s = a + b, where a is one element from A and b is one element from B. Find the Kth smallest s out of all possible s'.

Assumptions

A is not null and A is not of zero length, so as B
K > 0 and K <= m * n
Examples

A = {1, 3, 5}, B = {4, 8}

1st smallest s is 1 + 4 = 5
2nd smallest s is 3 + 4 = 7
3rd, 4th smallest s are 9 (1 + 8, 4 + 5) 
5th smallest s is 3 + 8 = 11
 */

package heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class KthSmallestSumInTwoSortedArray {
	public int kthSum(int[] A, int[] B, int k) {
		Queue<Cell> pq = new PriorityQueue<Cell>(k);
		Set<Cell> set = new HashSet<Cell>();
		Cell cur = new Cell(0, 0, A[0] + B[0]);
		pq.offer(cur);
		set.add(cur);
		for (int i = 0; i < k - 1; i++) {
			cur = pq.poll();
			System.out.println(cur.sum);
		      
		    if (cur.row + 1< A.length) {
		    	Cell next = new Cell(cur.row + 1, cur.col, A[cur.row + 1] + B[cur.col]);
		        if (set.add(next)) {
		        	pq.offer(next);
		        }
		    }
		      
		    if (cur.col + 1 < B.length) {
		    	Cell next = new Cell(cur.row, cur.col + 1, A[cur.row] + B[cur.col + 1]);
		        if (set.add(next)) {
		        	pq.offer(next);
		        }
		    }
		}
		return pq.peek().sum;
	}
		  
	private class Cell implements Comparable<Cell> {
		private int row;
		private int col;
		private int sum;
		    
		Cell(int row, int col, int sum) {
			this.row = row;
		    this.col = col;
		    this.sum = sum;
		}
		    
		public int compareTo(Cell another) {
			if (sum == another.sum) return 0;
			return sum < another.sum ? -1 : 1;
		}
		    
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Cell)) {
				return false;
			}
			Cell another = (Cell) obj;
			return this.row == another.row && this.col == another.col && this.sum == another.sum;
		}
		
		@Override
		public int hashCode() {
			return 31 * 31 * this.row + 31 * this.col + this.sum;
		}
	}
	
	public static void main(String[] args) {
		int[] A = new int[]{1,3,5,8,9};
		int[] B = new int[]{2,3,4,7};
		KthSmallestSumInTwoSortedArray solution = new KthSmallestSumInTwoSortedArray();
		System.out.println("Result: " + solution.kthSum(A, B, 11));
	}
}
