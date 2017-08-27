/*
Given a string with no duplicate characters, return a list with all permutations of the characters.

Examples

Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
Set = "", all permutations are [""]
Set = null, all permutations are []
 */

package recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationWODup {
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
		
		for (int i = index; i < set.length; i++) {
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
		PermutationWODup solution = new PermutationWODup();
		System.out.println(solution.permutation(null));
		System.out.println(solution.permutation(""));
		System.out.println(solution.permutation("abc"));
	}
}
