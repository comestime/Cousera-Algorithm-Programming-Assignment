/*
Given N pairs of parentheses “()”, return a list with all the valid permutations.

Assumptions

N >= 0
Examples

N = 1, all valid permutations are ["()"]
N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
N = 0, all valid permutations are [""]

 */

package recursion;

import java.util.ArrayList;
import java.util.List;

public class AllValidPermutationOfParenthesesI {
	public List<String> validParentheses(int n) {
		StringBuilder builder = new StringBuilder();
		List<String> result = new ArrayList<>();
		helper(n, 0, 0, builder, result);
		return result;
	}
	
	private void helper(int n, int left, int right, StringBuilder builder, List<String> result) {
		if (left == n && right == n) {
			result.add(builder.toString());
		}
		
		if (left < n) {
			builder.append('(');
			helper(n, left + 1, right, builder, result);
			builder.deleteCharAt(builder.length() - 1);
		}
		
		if (right < left) {
			builder.append(')');
			helper(n, left, right + 1, builder, result);
			builder.deleteCharAt(builder.length() - 1);
		}
	}
}
