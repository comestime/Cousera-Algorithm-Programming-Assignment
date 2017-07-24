/*
Given an original string input, and two strings S and T, replace all occurrences of S in input with T.

Assumptions

input, S and T are not null, S is not empty string
Examples

input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
input = "dodododo", S = "dod", T = "a", input becomes "aoao"
*/

package string;

import java.util.ArrayList;
import java.util.List;

public class ReplaceString {
	public String replace(String input, String s, String t) {
		// Write your solution here.
		return solution2(input, s, t);
	}
	
	// method 2: use "in-place" replacement instead
	private String solution2(String input, String s, String t) {
		String result;
		char[] in = input.toCharArray();
		if (s.length() >= t.length()) {
			result = replaceLongWithShort(in, s, t);
		} else {
			result = replaceShortWithLong(in, s, t);
		}
		return result;
	}
	
	private String replaceLongWithShort(char[] in, String s, String t) {
		int slow = 0;
		int fast = 0;
		while (fast <= in.length - s.length()) {
			if (checkEqualString(in, fast, s)) {
				for (int i = 0; i < t.length(); i++) {
					in[slow++] = t.charAt(i);
				}
				fast = fast + s.length();
			} else {
				in[slow++] = in[fast++];
			}
		}
		// copy the rest of the string
		while (fast < in.length) {
			in[slow++] = in[fast++];
		}
		return new String(in, 0, slow);
	}
	
	private boolean checkEqualString(char[] in, int fromIndex, String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != in[fromIndex + i]) return false;
		}
		return true;
	}
	
	private String replaceShortWithLong(char[] in, String s, String t) {
		// step 1: find the occurrence of "s" in "in"; store the endIndex of each occurrence
		List<Integer> occ = new ArrayList<Integer>();
		int fast = 0;
		// constrain the fast, to avoid IndexOutOfBoundException
		while (fast <= in.length - s.length()) {
			if (checkEqualString(in, fast, s)) {
				occ.add(fast + s.length() - 1);
				fast = fast + s.length();
			} else {
				fast++;
			}
		}
		
		// step 2: reserve a new space big enough for holding new string after replacement
		char[] result = new char[in.length + occ.size() * (t.length() - s.length())];
		
		// step 3: perform the replacement from right to left
		// fast: operate on the new string
		// slow: operate on the old string
		fast = result.length - 1;
		int slow = in.length - 1;
		// constrain the slow to avoid IndexOutOfBoundException
		while (slow >= 0) {
			if (occ.contains(slow)) {
				for (int i = t.length() - 1; i >= 0; i--) {
					result[fast--] = t.charAt(i);
				}
				slow = slow - s.length();
			} else {
				result[fast--] = in[slow--];
			}
		}
		return new String(result);
	}
	
	// method 1: use string builder
	private String solution1(String input, String s, String t) {
		StringBuilder builder = new StringBuilder();
		int fromIndex = 0;
		int matchIndex = input.indexOf(s, fromIndex);
		while (matchIndex != -1) {
			builder.append(input, fromIndex, matchIndex);
			builder.append(t);
			fromIndex = matchIndex + s.length();
			matchIndex = input.indexOf(s, fromIndex);
		}
		builder.append(input, fromIndex, input.length());
		return builder.toString();
	}
	
	public static void main(String[] args) {
		ReplaceString solution = new ReplaceString();
		System.out.println(solution.replace("appledogapple", "apple", "cat"));
		System.out.println(solution.replace("appledogapple", "apple", "apples"));
		System.out.println(solution.replace("dodododo", "dod", "a"));
		System.out.println(solution.replace("dodododo", "dod", "doddy"));
		System.out.println(solution.replace("aaa", "aa", "b"));
		System.out.println(solution.replace("aaa", "aa", "bbb"));
	}
}
