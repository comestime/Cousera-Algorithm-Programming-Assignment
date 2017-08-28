/*
Determine if there exists a set of four elements in a given array that sum to the given target number.

Assumptions

The given array is not null and has length of at least 4
Examples

A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)

A = {1, 2, 2, 3, 4}, target = 12, return false

 */

package twoThreeFourSum;

import java.util.HashMap;
import java.util.Map;

public class FourSum {
	public boolean exist(int[] array, int target) {
		Map<Integer, Pair> map = new HashMap<>();
		
		for (int j = 1; j < array.length; j++) {
			for (int i = 0; i < j; i++) {
				int pairSum = array[i] + array[j];
				
				if (map.containsKey(target - pairSum) && map.get(target - pairSum).j < i) {
					return true;
				}
				
				if (!map.containsKey(pairSum)) {
					map.put(pairSum, new Pair(i, j));
				}
			}
		}
		
		return false;
	}
	
	private class Pair {
		private int i;
		private int j;
		
		private Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
