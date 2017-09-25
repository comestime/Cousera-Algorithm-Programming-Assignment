/*
Find the longest common substring of two given strings.

Assumptions

The two given strings are not null
Examples

S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”
 */

package dp;

public class LongestCommonSubstring {
	// Time = O(m * n); m = length of string 1, n = length of string 2
	public String longestCommon(String s, String t) {
		// m[i][j] represents the longest common substring between the first i letters from string 1
		// and the first j letters from string 2 (including the i-th letter of string 1 and j-th letter of 
		// string 2

		int[][] m = new int[s.length() + 1][t.length() + 1];
		int maxLen = 0;
		String result = "";
		
		// Base case: M[0][j] = 0 for all j; M[i][0] = 0 for all i
		
		// Induction rule: M[i][j] = 1 + M[i-1][j-1] if input[i] == input[j]; else 0
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < t.length(); j++) {
				if (s.charAt(i) == t.charAt(j)) {
					m[i + 1][j + 1] = 1 + m[i][j];
					if (maxLen < m[i + 1][j + 1]) {
						maxLen = m[i + 1][j + 1];
						result = s.substring(i + 1 - maxLen, i + 1);
					}
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		LongestCommonSubstring s = new LongestCommonSubstring();
		System.out.println(s.longestCommon("abcde", "cdf"));
	}
}
