/*
Given two arrays of integers, find the median value.

Assumptions

The two given array are not null and at least one of them is not empty
The two given array are not guaranteed to be sorted
Examples

A = {4, 1, 6}, B = {2, 3}, median is 3
A = {1, 4}, B = {3, 2}, median is 2.5
 */

package recursion;

// using the quick select to find the kth smallest
public class MedianOfTwoArrays {
	public double median(int[] a, int[] b) {
		// step 1: merge the two array
		int[] array = new int[a.length + b.length];
		int idx = 0;
		for (int i = 0; i < a.length; i++) {
			array[idx++] = a[i];
		}
		for (int i = 0; i < b.length; i++) {
			array[idx++] = b[i];
		}
		
		if (array.length % 2 == 1) {
			return quickSelect(array, 0, array.length - 1, array.length / 2 + 1);
		} else {
			return (double)(quickSelect(array, 0, array.length - 1, array.length / 2) + 
					quickSelect(array, 0, array.length - 1, array.length / 2 + 1)) / 2.0;
		}
		
	}
	
	private int quickSelect(int[] a, int left, int right, int k) {
		if (left < right) {
			// use x = a[left] as pivot
			int i = left, j = right, x = a[left];
			while (i < j) {
				while (i < j && a[j] >= x) j--;				
				if (i < j) a[i++] = a[j];				
				while (i < j && a[i] < x) i++;				
				if (i < j) a[j--] = a[i];
			}
			a[i] = x;
			
			if (i - left + 1 == k) return x;
			else if (i - left + 1 < k) return quickSelect(a, i + 1, right, k - i + left - 1);
			else return quickSelect(a, left, i - 1, k);
		} else {
			return a[left];
		}
	}
}
