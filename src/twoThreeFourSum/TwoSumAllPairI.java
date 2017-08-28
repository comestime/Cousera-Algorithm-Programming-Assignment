/*
Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.

Assumptions

The given array is not null and has length of at least 2.

Examples

A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]

A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
 */

package twoThreeFourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumAllPairI {
	public List<List<Integer>> allPairs(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<>();
		// key = remainder, value = list of all possible indices
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		for (int i = 0; i < array.length; i++) {
			List<Integer> indices = map.get(array[i]);
			if (indices != null) {
				// index < i
				for (Integer index : indices) {
					result.add(Arrays.asList(index, i));
				}
			}
			
			int remainder = target - array[i];
			if (!map.containsKey(remainder)) {
				map.put(remainder, new ArrayList<Integer>());
			}
			map.get(remainder).add(i);
		}
		
		return result;
	}
}
