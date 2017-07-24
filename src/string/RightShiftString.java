/*
Right shift a given string by n characters.

Assumptions

The given string is not null.
n >= 0.
 */

package string;

public class RightShiftString {
	public String rightShift(String input, int n) {
		if (input == null || input.length() == 0) return input;
		char[] in = input.toCharArray();
		// n may be larger than input.length();
		n = n % input.length();
		
		// step 1: reverse word 1 and word 2 seperately
		// word 1: [0, input.length() - n - 1]
		// word 2: [input.length() - n, input.length() - 1]
		ReverseString.solution1(in, 0, input.length() - n - 1);
		ReverseString.solution1(in, input.length() - n, input.length() - 1);
		
		// step 2: reverse the entire string
		ReverseString.solution1(in, 0, input.length() - 1);
		
		return new String(in);
	}
	
	public static void main(String[] args) {
		RightShiftString solution = new RightShiftString();
		for (int i = 0; i < 20; i++) {
			System.out.println(solution.rightShift("Google", i));
		}
	}
}
