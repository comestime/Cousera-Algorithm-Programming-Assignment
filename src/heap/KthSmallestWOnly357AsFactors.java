/*
Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and y > 0 and z > 0, x, y, z are all integers.

Assumptions

K >= 1
Examples

the smallest is 3 * 5 * 7 = 105
the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
the 5th smallest is 3 ^ 3 * 5 * 7 = 945
 */

package heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class KthSmallestWOnly357AsFactors {
	
	public long kth(int k) {
		Set<Cell> set = new HashSet<>();
		Queue<Cell> heap = new PriorityQueue<>();
		
		Cell cell = new Cell(1, 1, 1);
		heap.offer(cell);
		set.add(cell);
		
		for (int i = 0; i < k - 1; i++) {
			cell = heap.poll();
			// note the expansion rule below!
			Cell cell1 = new Cell(cell.x + 1, cell.y, cell.z);
			Cell cell2 = new Cell(cell.x, cell.y + 1, cell.z);
			Cell cell3 = new Cell(cell.x, cell.y, cell.z + 1);
			if (set.add(cell1)) {
				heap.offer(cell1);
			}
			if (set.add(cell2)) {
				heap.offer(cell2);
			}
			if (set.add(cell3)) {
				heap.offer(cell3);
			}
		}
		
		return (long) heap.peek().s;
	}
	
	private class Cell implements Comparable<Cell>{
		int x;
		int y;
		int z;
		double s;
		
		private Cell(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.s = Math.pow(3, x) * Math.pow(5, y) * Math.pow(7, z);
		}

		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			if (this.s == o.s) return 0;
			// a mean heap
			return this.s < o.s ? -1 : 1;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			long temp;
			temp = Double.doubleToLongBits(s);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + x;
			result = prime * result + y;
			result = prime * result + z;
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
			if (Double.doubleToLongBits(s) != Double.doubleToLongBits(other.s))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			if (z != other.z)
				return false;
			return true;
		}

		private KthSmallestWOnly357AsFactors getOuterType() {
			return KthSmallestWOnly357AsFactors.this;
		}
	}
	
	public static void main(String[] args) {
		KthSmallestWOnly357AsFactors s = new KthSmallestWOnly357AsFactors();
		System.out.println(s.kth(2));
		System.out.println(s.kth(3));
		System.out.println(s.kth(5));
	}
}
