/*
Find all pairs of elements in a given array that sum to the pair the given target number. Return all the distinct pairs of values.

Assumptions

The given array is not null and has length of at least 2
The order of the values in the pair does not matter
Examples

A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
 */

package twoThreeFourSum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumAllPairII {
	
	// method 1: sort the array first and use two pointers
	public List<List<Integer>> allPairs(int[] array, int target) {
		Arrays.sort(array);
		List<List<Integer>> result = new ArrayList<>();
		int left = 0;
		int right = array.length - 1;
		
		while (left < right) {
			if (left > 0 && array[left] == array[left - 1]) {
				left++;
				continue;
			}
			
			int cur = array[left] + array[right];
			if (cur < target) {
				left++;
			} else if (cur > target) {
				right--;
			} else {
				result.add(Arrays.asList(array[left], array[right]));
				left++;
				right--;
			}
		}
		
		return result;
	}
	
	// method 2: use hash map
	public List<List<Integer>> allPairs1(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<>();
		// track the number of occurrence of each distinct value
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : array) {
			// case 1: if 2 * x == target, and we need to make sure there's no duplication
			// case 2: if x + y == target, and it's the first time both x & y are present
			// 		   thus we can make sure there's no duplication
			Integer count = map.get(num);
			if (2 * num == target && count != null && count == 1) {
				result.add(Arrays.asList(num, num));
			} else if (map.containsKey(target - num) && count == null) {
				result.add(Arrays.asList(target - num, num));
			}
			
			if (count == null) {
				map.put(num, 1);
			} else {
				map.put(num, count + 1);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		TwoSumAllPairII s = new TwoSumAllPairII();
		int[] test = new int[] {2, 1, 3, 2, 4, 3, 4, 2};
		System.out.println(s.allPairs(test, 6));
		System.out.println(s.allPairs1(test, 6));
	}
}
