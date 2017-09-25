/*
Given an array of 2D coordinates of points (all the coordinates are integers), 
find the largest number of points that can be crossed by a single line in 2D space.

Assumptions

The given array is not null and it has at least 2 points
Examples

<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3
(<0, 0>, <1, 1>, <3, 3> are on the same line)

 */

package misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostPointsOnALine {
	// method 1: inside a for for loop, calculate y = k * x + b, and check all other points
	// to see how many points are on this line
	// Time = O(N ^ 3)
	
	// method 2: use a hash map, where key = <k, b>, value = hash set<point>
	// Time = O(N ^ 2)
	// Space = O(N ^ 2), this happens when any of the three points are not on the same line
	// corner case: when k = infinity, which means the lins is perpendicular to x-axis
	// use a special hash map to handle this case
	public int most(Point[] points) {
		Map<Pair, Set<Point>> map = new HashMap<>();
		// special hash map to handle lines of the form x = c, where c is the key
		Map<Integer, Set<Point>> map1 = new HashMap<>();
		
		// step 1
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].x == points[j].x) {
					Set<Point> set = map1.get(points[i].x);
					if (set == null) {
						set = new HashSet<Point>();
						map1.put(points[i].x, set);
					}
					set.add(points[i]);
					set.add(points[j]);
				} else {
					double k = (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);
					double b = points[i].y - k * points[i].x;
					Pair key = new Pair(k, b);
					Set<Point> set = map.get(key) ;
					if (set == null) {
						set = new HashSet<Point>();
						map.put(key, set);
					}
					set.add(points[i]);
					set.add(points[j]);
				}
			}
		}
		
		// step 2:
		int max = 0;
		for (Map.Entry<Pair, Set<Point>> entry : map.entrySet()) {
			max = Math.max(max, entry.getValue().size());
		}
		
		for (Map.Entry<Integer, Set<Point>> entry : map1.entrySet()) {
			max = Math.max(max, entry.getValue().size());
		}
		
		return max;
	}
	
	private class Pair {
		private double k;
		private double b;
		
		private Pair(double k, double b) {
			this.k = k;
			this.b = b;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			long temp;
			temp = Double.doubleToLongBits(b);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(k);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
				return false;
			if (Double.doubleToLongBits(k) != Double.doubleToLongBits(other.k))
				return false;
			return true;
		}

		private MostPointsOnALine getOuterType() {
			return MostPointsOnALine.this;
		}
	}
}
