/*
Given an array of 2D coordinates of points (all the coordinates are integers), 
find the largest number of points that can form a set 
such that any pair of points in the set can form a line with positive slope. 
Return the size of such a maximal set.

Assumptions

The given array is not null
Note: if there does not even exist 2 points can form a line with positive slope, should return 0.

Examples
<0, 0>, <1, 1>, <2, 3>, <3, 3>, 
the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.

 */

package dp;

import java.util.Arrays;
import java.util.Comparator;

import misc.Point;

public class LargestSetOfPointsWPosSlope {
	// note for two points to form a line with a positive slope, P1<x1, y1>, P2<x2, y2>
	// slope = (y2 - y1) / (x2 - x1) > 0, i.e., when x1 < x2, we must have y1 < y2
	public int largest(Point[] points) {
		if (points == null || points.length <= 1) {
			return 0;
		}
		
		// step 1: sort the array according to the points' x-coordinates
		// Time = O(N * logN)
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				if (o1.x == o2.x) return 0;
				return o1.x < o2.x ? -1 : 1;
			}			
		});
		
		// step 2: the problem is converted to the longest ascending sub-sequence according to y-coordinate
		// Time = O(N ^ 2)
		// Space = O(N)
		int maxLen = 1;
		// m[i] represents the longest ascending sub-sequence from 0-th element to i-th element, including i
		int[] m = new int[points.length];
		
		// base case:
		m[0] = 1;
		
		// induction rule: m[i] = 1 + max(m[j]), if points[i].y > points[j].y, where 0 <= j < i
		for (int i = 1; i < points.length; i++) {
			m[i] = 1;
			for (int j = 0; j < i; j++) {
				if (points[i].y > points[j].y) {
					m[i] = Math.max(m[i], 1 + m[j]);
				}
				maxLen = Math.max(maxLen, m[i]);
			}
		}
		
		// if there are no two points exist that can form a line with positive slope
		if (maxLen == 1) return 0;
		return maxLen;
	}
}
