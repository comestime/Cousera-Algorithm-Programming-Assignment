/*
Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.

Assumptions

Both strings are not null
Examples

string one: “sigh”, string two : “asith”

the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).
 */

package dp;

public class EditDistance {
	public int editDistance(String one, String two) {
		// return recursive(one, two);
		return dp(one, two);
	}
	
	// method 1: recursion
	private int recursive(String one, String two) {
		if (one.length() == 0) return two.length();
		if (two.length() == 0) return one.length();
		
		// option 1: do nothing if chars are the same
		int doNothing = Integer.MAX_VALUE;
		if (one.charAt(0) == two.charAt(0)) {
			doNothing = recursive(one.substring(1), two.substring(1));
		}
		
		// option 2: replace current char
		int replace = 1 + recursive(one.substring(1), two.substring(1));
		
		// option 3: delete current char
		int delete = 1 + recursive(one.substring(1), two);
		
		// option 4: insert char matching String two's current char
		int insert = 1 + recursive(one, two.substring(1));
		
		return Math.min(Math.min(doNothing, replace), Math.min(delete, insert));
	}
	
	// method 2: DP
	// result[i][j] represents the minimum # of actions to transform substring
	// i.e., the first i letters of string one to the first j letters of string two
	private int dp(String one, String two) {
		// corner case: strings may be empty, but not null
		if (one.length() == 0) return two.length();
		if (two.length() == 0) return one.length();
		
		int row = one.length() + 1;
		int col = two.length() + 1;
		int[][] result = new int[row][col];
		
		for (int j = 0; j < col; j++) {
			result[0][j] = j;
		}
		
		for (int i = 0; i < row; i++) {
			result[i][0] = i;
		}
		
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				// case 1: do nothing
				int doNothing = Integer.MAX_VALUE;
				if (one.charAt(i - 1) == two.charAt(j - 1)) {
					doNothing = result[i - 1][j - 1];
				}
				
				// case 2: replace
				int replace = 1 + result[i - 1][j - 1];
				
				// case 3: delete
				int delete = 1 + result[i - 1][j];
				
				// case 4: insert
				int insert = 1 + result[i][j - 1];
				
				result[i][j] = Math.min(Math.min(doNothing, replace), Math.min(delete, insert));
			}
		}
		
		return result[row - 1][col - 1];
	}
}
