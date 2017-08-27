/*
Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.

Assumptions

l, m, n >= 0
Examples

l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]

Restrictions for getting a valid permutation:

1. for the same type of parenthesis, left_added_so_far > right_added_so_far
2. for the same type of parenthesis, its left parenthesis must match right parenthesis, no matter how many other types of prentheses in between
(implying "linear scan, look back" --> the use of stack)

 */

package recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AllValidPermutationOfParenthesesII {
	private static final char[] chars = new char[] {'(', ')', '[', ']', '{', '}'};
	
	public List<String> validParentheses(int l, int m, int n) {
		StringBuilder builder = new StringBuilder();
		List<String> result = new ArrayList<>();
		Deque<Character> stack = new ArrayDeque<>();
		int[] remain = new int[] {l, l, m, m, n, n};
		int tarLen = 2 * l + 2 * m + 2 * n;
		
		helper(builder, result, stack, remain, tarLen);
		
		return result;
	}
	
	private void helper(StringBuilder builder, List<String> result, Deque<Character> stack, int[] remain, int tarLen) {
		if (builder.length() == tarLen) {
			result.add(builder.toString());
			return;
		}
		
		// iterate through all kinds of parentheses
		for (int i = 0; i < remain.length; i++) {
			// left parentheses
			if (i % 2 == 0) {
				// left parenthesis available
				if (remain[i] != 0) {
					builder.append(chars[i]);
					stack.offerFirst(chars[i]);
					remain[i]--;
					helper(builder, result, stack, remain, tarLen);
					remain[i]++;
					stack.pollFirst();
					builder.deleteCharAt(builder.length() - 1);
				}
			} else {
			// right parentheses
				if (!stack.isEmpty() && stack.peekFirst() == chars[i - 1]) {
					builder.append(chars[i]);
					stack.pollFirst();
					remain[i]--;
					helper(builder, result, stack, remain, tarLen);
					remain[i]++;
					stack.offerFirst(chars[i - 1]);
					builder.deleteCharAt(builder.length() - 1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		AllValidPermutationOfParenthesesII s = new AllValidPermutationOfParenthesesII();
		System.out.println(s.validParentheses(1, 1, 0));
	}
}
