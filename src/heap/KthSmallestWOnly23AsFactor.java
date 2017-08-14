/*
Find the Kth smallest number s such that s = 2 ^ x * 3 ^ y, x >= 0 and y >= 0, x and y are all integers.

Idea: the same concept as finding kth smallest sum in two sorted array

Assumptions

K >= 1
Examples

the smallest is 1
the 2nd smallest is 2
the 3rd smallest is 3
the 5th smallest is 2 * 3 = 6
the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
 
 */

package heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class KthSmallestWOnly23AsFactor {
	public int kth(int k) {
		Queue<Cell> pq = new PriorityQueue<Cell>(k);
		Set<Cell> set = new HashSet<Cell>();
		Cell cur = new Cell(0, 0, pow2(0) * pow3(0));
		pq.offer(cur);
		set.add(cur);
		for (int i = 0; i < k - 1; i++) {
			cur = pq.poll();
			// System.out.println(cur.sum);
		      
		    Cell next = new Cell(cur.row + 1, cur.col, pow2(cur.row + 1) * pow3(cur.col));
		    if (set.add(next)) {
		    	pq.offer(next);
		    }
		     
		    next = new Cell(cur.row, cur.col + 1, pow2(cur.row) * pow3(cur.col + 1));
		    if (set.add(next)) {
		    	pq.offer(next);
		    }
		}
		return pq.peek().sum;
	}
	
	private int pow2(int x) {
		return (int) Math.pow(2, x);
	}
	
	private int pow3(int x) {
		return (int) Math.pow(3, x);
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
		KthSmallestWOnly23AsFactor solution = new KthSmallestWOnly23AsFactor();
		System.out.println("Result: " + solution.kth(1));
		System.out.println("Result: " + solution.kth(2));
		System.out.println("Result: " + solution.kth(3));
		System.out.println("Result: " + solution.kth(4));
		System.out.println("Result: " + solution.kth(5));
		System.out.println("Result: " + solution.kth(6));
	}
}
