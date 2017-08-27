/*
Given a string with possible duplicate characters, return a list with all permutations of the characters.

Examples

Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
Set = "aba", all permutations are ["aab", "aba", "baa"]
Set = "", all permutations are [""]
Set = null, all permutations are []
 */

package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationWDup {
	public List<String> permutation(String set) {
		List<String> result = new ArrayList<String> ();
		if (set == null) return result;
		if (set.length() == 0) {
			result.add(new String(""));
			return result;
		}
		char[] input = set.toCharArray();
		dfs(input, 0, result);
		return result;
	}

	private void dfs(char[] set, int index, List<String> result) {
		if (index == set.length - 1) {
			result.add(new String(set));
			return;
		}
		
		// use a set to deduplication
		Set<Character> charSet = new HashSet<Character>();
		for (int i = index; i < set.length; i++) {
			if (charSet.contains(set[i])) continue;
			charSet.add(set[i]);
			swap(set, i, index);
			dfs(set, index + 1, result);
			swap(set, i, index);
		}
	}
	
	private void swap(char[] input, int i, int j) {
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	public static void main(String[] args){
		PermutationWDup solution = new PermutationWDup();
		System.out.println(solution.permutation(null));
		System.out.println(solution.permutation(""));
		System.out.println(solution.permutation("abc"));
		System.out.println(solution.permutation("aba"));
	}
}
