/*
Reverse the words in a sentence.

Assumptions

Words are separated by single space

There are no heading or tailing white spaces

Examples

“I love Google” → “Google love I”

Corner Cases

If the given string is null, we do not need to do anything.
 */

package string;

public class ReverseWords {
	public String reverseWords(String input) {
		// Write your solution here.
		if (input == null || input.length() == 0) return input;
		
		// step 1:
		// use slow/fast pointers to find each word boundary
		// reverse each word
		int slow = 0;
		int fast = 0;
		char[] in = input.toCharArray();
		while (fast < input.length()) {
			while (fast < input.length()) {
				if (in[fast] != ' ') fast++;
				else break;
			}
			ReverseString.solution2(in, slow, fast - 1);
			fast = fast + 1;
			slow = fast;
		}
		
		// step 2:
		// reverse the whole string
		ReverseString.solution1(in, 0, in.length - 1);
		
		return new String(in);
	}
	
	public static void main(String[] args) {
		ReverseWords reverse = new ReverseWords();
		System.out.println(reverse.reverseWords("I love Google"));
	}
}
