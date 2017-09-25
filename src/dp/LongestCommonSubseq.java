/*
Find the length of longest common subsequence of two given strings.

Assumptions

The two given strings are not null
Examples

S = “abcde”, T = “cbabdfe”, the longest common subsequence of s and t is {‘a’, ‘b’, ‘d’, ‘e’}, 
the length is 4.

 */

package dp;

public class LongestCommonSubseq {
	// Time = O(N ^ 2)
	public int longest(String s, String t) {
		// m[i][j] represents the longest common sub sequence between the first i letters from string s
		// and the first j letters from string t (might not including the i-th letter from string s
		// and j-th letter from string t
		int[][] m = new int[s.length() + 1][t.length() + 1];
		
		// base case: m[0][j] = 0 for all j and m[i][0] = 0 for all i
		
		// induction rule: m[i][j] = 1 + m[i - 1][j - 1] if input[i] == input[j]
		// m[i][j] = max(m[i - 1][j], m[i][j - 1])
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < t.length(); j++) {
				if (s.charAt(i) == t.charAt(j)) {
					m[i + 1][j + 1] = 1 + m[i][j];
				} else {
					m[i + 1][j + 1] = Math.max(m[i][j + 1], m[i + 1][j]);
				}
			}
		}
		
		return m[s.length()][t.length()];
	}
}
