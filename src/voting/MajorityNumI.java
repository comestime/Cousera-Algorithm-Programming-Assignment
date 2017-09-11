/*
Given an integer array of length L, find the number that occurs more than 0.5 * L times.

Assumptions

The given array is not null or empty
It is guaranteed there exists such a majority number
Examples

A = {1, 2, 1, 2, 1}, return 1
 */

package voting;

public class MajorityNumI {
	public int majority(int[] array) {
		if (array == null || array.length == 0) return -1;
		
		// maintain a pair of flags
		int value = 0;
		int count = 0;
		
		for (int num : array) {
			if (count == 0) {
				value = num;
				count++;
			} else if (num == value) {
				count++;
			} else {
				count--;
			}
		}
		
		return value;
	}
}
