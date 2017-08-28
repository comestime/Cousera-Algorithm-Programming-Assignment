/*
Determine if there exists three elements in a given array that sum to the given target number.
Return all the triple of values that sums to target.

Assumptions

The given array is not null and has length of at least 3
No duplicate triples should be returned, order of the values in the tuple does not matter
Examples

A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
 */

package twoThreeFourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	public List<List<Integer>> allTriples(int[] array, int target) {
		// sort the array first
		Arrays.sort(array);

		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < array.length - 2; i++) {
			// make sure array[i] is not duplicated
			if (i > 0 && array[i] == array[i - 1]) {
				continue;
			}
			
			// normalized to 2 sum problem
			int j = i + 1;
			int k = array.length - 1;
			int temp = target - array[i];
			while (j < k) {				
				if (array[j] + array[k] < temp) {
					j++;
				} else if (array[j] + array[k] > temp) {
					k--;
				} else {
					result.add(Arrays.asList(array[i], array[j], array[k]));
					j++;
					k--;
					// ignore all possible duplicated j
					while (j < k && array[j] == array[j - 1]) {
						j++;
					}
				}
			}
		}
				
		return result;
	}
	
	public static void main(String[] args) {
		int[] test = new int[] {1, 2, 2, 3, 2, 4};
		ThreeSum s = new ThreeSum();
		System.out.println(s.allTriples(test, 8));
	}
}
