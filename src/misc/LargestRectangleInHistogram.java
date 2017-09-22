/*
Given a non-negative integer array representing the heights of a list of adjacent bars. 
Suppose each bar has a width of 1. 
Find the largest rectangular area that can be formed in the histogram.

Assumptions

The given array is not null or empty
Examples

{ 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4)

 */

package misc;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
	public int largest(int[] array) {
		// return largest1(array);
		return largest2(array);
	}
	
	// method 2: using a stack; Time = O(N)
	private int largest2(int[] array) {
		int globalMax = 0;
		// use a stack to store "index" instead of "value"
		// maintain the stack such that the "values" whose indices are stored in the ascending order
		Deque<Integer> stack = new ArrayDeque<>();
		
		for (int i = 0; i <= array.length; i++) {
			// need a way to popping out all elements in the stack
			// explicitly add a bar of height 0 at the end of the array
			int cur = i == array.length ? 0 : array[i];
			while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
				int height = array[stack.pollFirst()];
				// determine the left boundary of the largest rectangle with height array[i]
				int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
				// determine the right boundary of the largest rectangle with height of the popped element
				globalMax = Math.max(globalMax, height * (i - left));
			}
			stack.offerFirst(i);
		}
		
		return globalMax;
	}
	
	// method 1: 中心开花; Time = O(N ^ 2)
	private int largest1(int[] array) {
		int globalMax = 0;
		for (int i = 0; i < array.length; i++) {
			int left = i;
			while (left >= 0 && array[left] >= array[i]) {
				left--;
			}
			// restore the left bound
			left++;
			int right = i;
			while (right < array.length && array[right] >= array[i]) {
				right++;
			}
			// restore the right bound
			right--;
			globalMax = Math.max(globalMax, array[i] * (right - left + 1));
		}
		
		return globalMax;
	}
}
