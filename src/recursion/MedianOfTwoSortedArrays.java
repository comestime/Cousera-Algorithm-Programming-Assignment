/*
Given two sorted arrays of integers in ascending order, find the median value.

Assumptions

The two given array are not null and at least one of them is not empty
The two given array are guaranteed to be sorted
Examples

A = {1, 4, 6}, B = {2, 3}, median is 3
A = {1, 4}, B = {2, 3}, median is 2.5
 */

package recursion;

public class MedianOfTwoSortedArrays {
	public double median(int[] a, int[] b) {
		int total = a.length + b.length;
		int k;
		int k1, k2;
		if (total % 2 == 1) {
			k = total / 2 + 1;
			return KthSmallestInTwoSortedArrays.findKthSmallest(a, 0, b, 0, k);
		} else {
			k1 = total / 2;
			k2 = total / 2 + 1;
			return (double)(KthSmallestInTwoSortedArrays.findKthSmallest(a, 0, b, 0, k1) + 
					KthSmallestInTwoSortedArrays.findKthSmallest(a, 0, b, 0, k2)) / 2.0;
		}
	}
}
