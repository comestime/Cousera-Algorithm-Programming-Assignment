/*
Word “book” can be abbreviated to 4, b3, b2k, etc. 
Given a string and an abbreviation, return if the string matches the abbreviation.

Assumptions:

The original string only contains alphabetic characters.
Both input and pattern are not null.
Examples:

pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
 */

package string;

public class StringAbbreviationMatch {
	public boolean match(String input, String pattern) {
		return matchIterative(input, pattern);
		// return matchRecursive(input, pattern, 0, 0);
	}
	
	// method 1: iterative
	public boolean matchIterative(String input, String pattern) {
		int inIdx = 0;
		int paIdx = 0;
		while (inIdx < input.length() && paIdx < pattern.length()) {
			if (pattern.charAt(paIdx) > '9' || pattern.charAt(paIdx) < '0') {
				if (input.charAt(inIdx) != pattern.charAt(paIdx)) {
					return false;
				}
				inIdx++;
				paIdx++;
			} else {
				int count = 0;
				while (paIdx < pattern.length() && pattern.charAt(paIdx) >= '0' && pattern.charAt(paIdx) <= '9') {
					count = count * 10 + (pattern.charAt(paIdx) - '0');
					paIdx++;
				}
				inIdx += count;
			}
		}
		return inIdx == input.length() && paIdx == pattern.length();
	}
	
	// method 2: recursive
	public boolean matchRecursive(String input, String pattern, int inIdx, int paIdx) {
		// base case
		if (paIdx == pattern.length()) {
			if (inIdx == input.length()) return true;
			else return false;
		}
		
		if (pattern.charAt(paIdx) < '0' || pattern.charAt(paIdx) > '9') {
			if (pattern.charAt(paIdx) != input.charAt(inIdx)) return false;
			else return matchRecursive(input, pattern, inIdx + 1, paIdx + 1);
		} else {
			int count = 0;
			while (paIdx < pattern.length() && pattern.charAt(paIdx) >= '0' && pattern.charAt(paIdx) <= '9') {
				count = count * 10 + pattern.charAt(paIdx) - '0';
				paIdx++;
			}
			return matchRecursive(input, pattern, inIdx + count, paIdx);
		}
	}
	
	public static void main(String[] args) {
		StringAbbreviationMatch s = new StringAbbreviationMatch();
		System.out.println(s.match("sophisticated", "s11d"));
		System.out.println(s.match("sophisticated", "s11"));
	}
}
