/*
 Given an array of elements, reorder it as follow:

{ N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }

{ N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }

Try to do it in place.

Assumptions

The given array is not null
Examples

{ 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }

{ 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }

{ 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }
 */
package string;

public class ShuffleArray {
	public int[] reorder(int[] array) {
		// Write your solution here.
		if (array.length <= 3) return array;
		
		if (array.length % 2 == 0) helper(array, 0, array.length - 1);
		else helper(array, 0, array.length - 2);
		return array;
	}
	
	private void helper(int[] array, int left, int right) {
		if (right - left <= 1) {
			return;
		}
		
		int size = right - left + 1;
		int mid = left + size / 2;
		int leftMid = left + size / 4;
		int rightMid = left + 3 * size / 4;
		
		reverse(array, leftMid, mid - 1);
		reverse(array, mid, rightMid - 1);
		reverse(array, leftMid, rightMid - 1);
		
		helper(array, left, left + 2 * (leftMid - left) - 1);
		helper(array, left + 2 * (leftMid - left), right);
	}
	
	private void reverse(int[] array, int left, int right) {
		for (int i = 0; i < (right - left + 1) / 2; i++) {
			int temp = array[left + i];
			array[left + i] = array[right - i];
			array[right - i] = temp;
		}
	}
	
	public static void main(String[] args) {
		ShuffleArray solution = new ShuffleArray();
		int[] test = {1, 2, 3, 4, 5, 6};
		solution.reorder(test);
		for (int i = 0; i < test.length; i++) {
			System.out.print(test[i]);
		}
		System.out.println();
		int[] test1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		solution.reorder(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.print(test1[i]);
		}
		System.out.println();
		int[] test2 = { 1, 2, 3, 4, 5, 6, 7 };
		solution.reorder(test2);
		for (int i = 0; i < test2.length; i++) {
			System.out.print(test2[i]);
		}
	}
}
