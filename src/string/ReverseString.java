/*
Reverse a given string.

Assumptions
The given string is not null.
 */

package string;

public class ReverseString {
	public static String reverse(String input) {
		// Write your solution here.
		char[] in = input.toCharArray();
		solution2(in, 0, input.length() - 1);
		String result = new String(in);
		return result;
	}
	
	public static void reverse(char[] input) {
		solution2(input, 0, input.length - 1);
	}
	
	// iteration
	public static void solution2(char[] in, int left, int right) {
		for (int i = 0; i < (right - left + 1) / 2; i++) {
			swap(in, left + i, right - i);
		}
	}
	
	// recursion
	public static void solution1(char[] in, int left, int right) {
		if (left >= right) return;
		swap(in, left, right);
		solution1(in, left + 1, right - 1);
	}
		  
	private static void swap(char[] in, int i, int j) {
		char temp = in[i];
		in[i] = in[j];
		in[j] = temp;
	}
	
	public static void main(String[] args) {
		System.out.println(ReverseString.reverse("google"));
		System.out.println(ReverseString.reverse("yahoo"));
	}
}
