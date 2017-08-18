/*
Given an array of integers (without any duplicates), shuffle the array such that all permutations are equally likely to be generated.

Assumptions

The given array is not null

 */

package probablity;

public class PerfectShuffle {
	public void shuffle(int[] array) {
		if (array == null || array.length <= 1) return;
		
		for (int i = array.length - 1; i > 0; i--) {
			// note Math.random() returns a number in the range [0.0, 1.0)
			// thus add one to make 1.0 inclusive
			// rand = (int) (Math.random() * (max - min + 1)) + min
			int rand = (int)(Math.random() * (i + 1));
			swap(array, i, rand);
		}		
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		PerfectShuffle s = new PerfectShuffle();
		int[] test = new int[] {2, 3, 1};
		s.shuffle(test);
		System.out.println(test);
		System.out.println((int)(0.5 * 52));
		System.out.println((int)(0.5 * 51));
	}
}
