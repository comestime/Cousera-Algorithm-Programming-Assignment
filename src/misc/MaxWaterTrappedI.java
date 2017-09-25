/*
Given a non-negative integer array representing the heights of a list of adjacent bars. 
Suppose each bar has a width of 1. Find the largest amount of water that can be trapped in the histogram.

Assumptions

The given array is not null
Examples

{ 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 
(at index 1, 1 unit of water can be trapped and index 3, 1 unit of water can be trapped)
 */

package misc;

public class MaxWaterTrappedI {
	public int maxTrapped(int[] array) {
		// return max1(array);
		return max3(array);
	}
	
	// method 3: Time = O(N), Space = O(1)
	public int max3(int[] array) {
		int i = 0, j = array.length - 1;
		int leftMax = 0, rightMax = 0;
		int globalMax = 0;
		
		while (i <= j) {
			leftMax = Math.max(leftMax, array[i]);
			rightMax = Math.max(rightMax, array[j]);
			
			if (leftMax < rightMax) {
				globalMax += (leftMax - array[i]);
				i++;
			} else {
				globalMax += (rightMax - array[j]);
				j--;
			}
		}
		
		return globalMax;
	}
	
	// method 2: DP; Time = O(N), Space = O(N)
	// M1[i] represents the highest value from 0-th element to the i-th element (including i-th element)
	// M2[i] represents the highest value from n-1-th element to the i-th element (including i-th element)
	
	// method 1: 中心开花; Time = O(N ^ 2)
	public int max1(int[] array) {
		int max = 0;
		for (int i = 1; i < array.length - 1; i++) {
			int left = i - 1;
			int leftMax = array[i];
			while (left >= 0) {
				leftMax = Math.max(leftMax, array[left--]);
			}
			
			int right = i + 1;
			int rightMax = array[i];
			while (right < array.length) {
				rightMax = Math.max(rightMax, array[right++]);
			}
			
			int limit = Math.min(leftMax, rightMax);
			max += limit > array[i] ? (limit - array[i]) : 0;
		}
		
		return max;
	}
}
