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
		return recursive(one, two);
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
}
