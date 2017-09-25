/*
Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

Assumptions

A is not null
Examples
Input: A = {5, 2, 6, 3, 4, 7, 5}
Output: 4
Because [2, 3, 4, 5] is the longest ascending subsequence.
 */

package dp;

public class LongestAscendingSubseq {
	// Time = O(N ^ 2)
	public int longest(int[] array) {
		if (array == null || array.length == 0) return 0;
		// base case: M[0] = 1;
		// m[i] represents the length of the longest ascending subsequence from 0-th to i-th element, including i
		// m[i] = 1 + max(m[j]), where array[j] < array[i] and 0 <= j < i; 1 if there is no such j
		int[] m = new int[array.length];
		int globalMax = 1;
		m[0] = 1;
		for (int i = 1; i < array.length; i++) {
			m[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i]) {
					m[i] = Math.max(m[i], m[j] + 1);
				}
			}
			globalMax = Math.max(globalMax, m[i]);
		}
		
		return globalMax;
	}
	
	public static void main(String[] args) {
		int[] test = new int[]{1, 1, 1, 3, 5, 2};
		LongestAscendingSubseq s = new LongestAscendingSubseq();
		System.out.print(s.longest(test));
	}
}
