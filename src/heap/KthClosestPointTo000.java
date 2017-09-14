/*
Given three arrays sorted in ascending order. Pull one number from each array to form a coordinate <x,y,z> in a 3D space. Find the coordinates of the points that is k-th closest to <0,0,0>.

We are using euclidean distance here.

Assumptions

The three given arrays are not null or empty
K >= 1 and K <= a.length * b.length * c.length
Return

a size 3 integer list, the first element should be from the first array, the second element should be from the second array and the third should be from the third array
Examples

A = {1, 3, 5}, B = {2, 4}, C = {3, 6}

The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)

The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)
 */

package heap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class KthClosestPointTo000 {
	public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
		Set<Cell> set = new HashSet<>();
		Queue<Cell> heap = new PriorityQueue<>();
		
		Cell cell = new Cell(0, 0, 0, a[0], b[0], c[0]);
		heap.offer(cell);
		set.add(cell);
		
		for (int i = 0; i < k - 1; i++) {
			cell = heap.poll();
			// note the expansion rule below!
			Cell cell1 = cell.x < a.length - 1 ? 
					new Cell(cell.x + 1, cell.y, cell.z, a[cell.x + 1], b[cell.y], c[cell.z]) : null;
			Cell cell2 = cell.y < b.length - 1 ? 
					new Cell(cell.x, cell.y + 1, cell.z, a[cell.x], b[cell.y + 1], c[cell.z]) : null;
			Cell cell3 = cell.z < c.length - 1 ? 
					new Cell(cell.x, cell.y, cell.z + 1, a[cell.x], b[cell.y], c[cell.z + 1]) : null;
			if (cell1 != null && set.add(cell1)) {
				heap.offer(cell1);
			}
			if (cell2 != null && set.add(cell2)) {
				heap.offer(cell2);
			}
			if (cell3 != null && set.add(cell3)) {
				heap.offer(cell3);
			}
		}
		
		return Arrays.asList(heap.peek().ax, heap.peek().ay, heap.peek().az);
	}
	
	private class Cell implements Comparable<Cell>{
		int x;
		int y;
		int z;
		int ax;
		int ay;
		int az;
		double s;
		
		private Cell(int x, int y, int z, int ax, int ay, int az) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.ax = ax;
			this.ay = ay;
			this.az = az;
			this.s = Math.pow(ax, 2) + Math.pow(ay, 2) + Math.pow(az, 2);
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
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			if (z != other.z)
				return false;
			return true;
		}

		private KthClosestPointTo000 getOuterType() {
			return KthClosestPointTo000.this;
		}
	}
}
