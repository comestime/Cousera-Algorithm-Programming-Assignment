/*
Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.

Assumptions

The given array is not null
Examples

A = {1, 2, 1, 2, 1}, return [1, 2]
A = {1, 2, 1, 2, 3, 3, 1}, return [1]
A = {1, 2, 2, 3, 1, 3}, return []
 */

package voting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MajorityNumII {
	public List<Integer> majority(int[] array) {
		if (array == null) return null;
		
		// maintain two pairs of flags
		int value1 = 0;
		int count1 = 0;
		int value2 = 0;
		int count2 = 0;
		
		// step 1
		for (int num : array) {
			if (num == value1) {
				count1++;
			} else if (num == value2) {
				count2++;
			} else if (count1 == 0) {
				value1 = num;
				count1++;
			} else if (count2 == 0) {
				value2 = num;
				count2++;
			} else {
				count1--;
				count2--;
			}
		}
		
		// step 2: iterate the array for the 2nd time, 
		// and count the number of occurrence of the two possible candidates
		count1 = 0;		// reset counter
		count2 = 0;		// reset counter
		for (int num : array) {
			if (num == value1) {
				count1++;
			}
			if (num == value2) {
				count2++;
			}
		}
		
		List<Integer> result = new ArrayList<>();
		if (count1 > array.length / 3) {
			result.add(value1);
		}
		if (count2 > array.length / 3) {
			result.add(value2);
		}
		Collections.sort(result);
		return result;
	}
}
