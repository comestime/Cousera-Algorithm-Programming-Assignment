/*
Given a string S, find the longest palindromic substring in S.

Assumptions
There exists one unique longest palindromic substring.    
The input S is not null.
Examples
Input:     "abbc"
Output:  "bb"

Input:     "abcbcbd"
Output:  "bcbcb"

 */

package dp;

public class LongestPalindromeSubstring {
	// Time = O(N ^ 2)
	public String longestPalindrome(String s) {
		String result = "";
		int maxLen = 0;
		
		// step 1: preprocessing
		// m[i][j] represents if the substring [i, j] is a palindrome string
		boolean[][] m = new boolean[s.length()][s.length()];
		
		// base case: m[0][0] = m[1][1] = m[2][2] ... = true; size = 1
		for (int i = 0; i < s.length(); i++) {
			m[i][i] = true;
		}
		
		// induction rule: m[i][j] = true if s.charAt(i) == s.charAt(j) && m[i + 1][j - 1]
		for (int size = 2; size <= s.length(); size++) {
			for (int i = 0; i <= s.length() - size; i++) {
				int j = i + size - 1;
				if (s.charAt(i) == s.charAt(j)) {
					m[i][j] = i + 1 >= j - 1 || m[i + 1][j - 1];		// in case of out of bound exception
 				}
			}
		}
		
		// debug
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println("");
		}
		
		// step 2: find the longest palindrome substring
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				if (m[i][j] && maxLen < j - i + 1) {
					maxLen = j - i + 1;
					result = s.substring(i, j + 1);
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		LongestPalindromeSubstring s = new LongestPalindromeSubstring();
		System.out.println(s.longestPalindrome("abacbbcabcbbbcba"));
	}
}
