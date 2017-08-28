/*
Determine if there exist two elements in a given array, the sum of which is the given target number.

Assumptions

The given array is not null and has length of at least 2
​Examples

A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)

A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)

A = {2, 4, 1}, target = 4, return false
 */

package twoThreeFourSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	// method 1: if the array is not sorted, use a hash map
	public boolean existSum(int[] array, int target) {
		// key = remainder, value = its index
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				return true;
			}
			map.put(target - array[i], i);
		}
		
		return false;
	}
	
	// method 2: if the array is sorted, use two pointers
	public boolean existSum1(int[] array, int target) {
		Arrays.sort(array);
		int i = 0;
		int j = array.length - 1;
		while (i < j) {
			if (array[i] + array[j] > target) {
				j--;
			} else if (array[i] + array[j] < target) {
				i++;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		TwoSum s = new TwoSum();
		int[] a = new int[] {1, 2, 3, 4};
		int[] b = new int[] {2, 4, 2, 1};
		int[] c = new int[] {2, 4, 1};
		
		System.out.println(s.existSum(a, 5));
		System.out.println(s.existSum1(a, 5));
		System.out.println(s.existSum(b, 4));
		System.out.println(s.existSum1(b, 4));
		System.out.println(s.existSum(c, 4));
		System.out.println(s.existSum1(c, 4));
	}
}
