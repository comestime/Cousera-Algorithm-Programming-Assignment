/*
Given a string with possible duplicate characters, return a list with all permutations of the characters.

Examples
Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
Set = "aba", all permutations are ["aab", "aba", "baa"]
Set = "", all permutations are [""]
Set = null, all permutations are []
 */

package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
	public List<String> permutations(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) return result;
		if (set.length() == 0) {
			result.add("");
			return result;
		}
		char[] in = set.toCharArray();
		helper(in, 0, result);
		return result;
	}
	
	// helper function to perform permutation
	private void helper(char[] in, int index, List<String> result) {
		if (index == in.length - 1) {
			result.add(new String(in));
			return;
		}
		
		// string may contain duplicated characters
		// use hash set to deduplication
		Set<Character> set = new HashSet<Character>();
		for (int i = index; i < in.length; i++) {
			if (set.contains(in[i])) continue;
			set.add(in[i]);
			swap(in, i, index);
			helper(in, index + 1, result);
			swap(in, i, index);
		}
	}
	
	private void swap(char[] in, int i, int j) {
		char temp = in[i];
		in[i] = in[j];
		in[j] = temp;
	}
	
	public static void main(String[] args) {
		Permutations per = new Permutations();
		System.out.println(per.permutations("abc"));
		System.out.println(per.permutations("aba"));
		System.out.println(per.permutations(""));
	}
}
