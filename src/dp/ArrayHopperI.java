/*
Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). Determine if you are able to reach the last index.

Assumptions

The given array is not null and has length of at least 1.
Examples

{1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)

{2, 1, 1, 0, 2}, we are not able to reach the end of array
 */

package dp;

public class ArrayHopperI {
	public boolean canJump(int[] array) {
		// return canJump1(array);
		return canJump2(array);
	}
	
	// method 1: DP
	// Base case: mark[array.length - 1= = true
	// Induction rule: mark[i] represents whether we can reach the target from the i-th index
	// mark[i] == true if there exists a mark[i + j] = true where j <= array[j]
	// mark[j] == false otherwise
	// Time complexity: O(n ^ 2) or even better O(n * k) assuming k < n and k is the largest jump value
	// Space complexity: O(n)
	public boolean canJump1(int[] array) {
		boolean[] mark = new boolean[array.length];
		// from target, and mark target as true
		mark[array.length - 1] = true;
		for (int i = array.length - 2; i >= 0; i--) {
			for (int j = 1; j <= array[i]; j++) {
				if (i + j >= array.length || mark[i + j]) {
					mark[i] = true;
					break;
		        }
		    }
		}
		return mark[0];
	}
	
	// method 2: greedy
	// from 0th index we scan through the array and calculate the current reachable index = current index + array[current_index]
	// update the maxReachableIdx accordingly
	// case 1: when maxReachableIdx == array.length - 1, we guarantee that we can jump to the last element, and return true
	// case 2: when current index == maxReachableIdx && current reachable index <= maxReachableIdx
	// meaning we can jump to the last element, and return false
	// Time complexity: O(n)
	// space complexity: O(1)
	public boolean canJump2(int[] array) {
		int maxReachableIdx = 0;
		for (int i = 0; i < array.length; i++) {
			int currentReachableIdx = array[i] + i;
			// update the maxReachableIdx
			if (currentReachableIdx > maxReachableIdx) {
				maxReachableIdx = currentReachableIdx;
			}
			// case 1
			if (maxReachableIdx >= array.length - 1) {
				return true;
			}
			// case 2
			if (i == maxReachableIdx && currentReachableIdx <= maxReachableIdx) {
				return false;
			}
		}
		return true;
	}
}
