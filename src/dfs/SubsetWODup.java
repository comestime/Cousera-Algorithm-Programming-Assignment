/*
Given a set of characters represented by a String, return a list containing all subsets of the characters.

Assumptions

There are no duplicate characters in the original set.
​Examples

Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
Set = "", all the subsets are [""]
Set = null, all the subsets are []
 */

package dfs;

import java.util.ArrayList;
import java.util.List;

public class SubsetWODup {
	public List<String> subSets(String set) {
		// Write your solution here.
		List<String> result = new ArrayList<String>();
		if (set == null) {
		  return result;
		}
		char[] stringArray = set.toCharArray();
		StringBuilder builder = new StringBuilder();
		  
		helper(stringArray, 0, builder, result);
		return result;
	}
		  
	private void helper(char[] stringArray, int index, StringBuilder builder,
						List<String> result) {
		if (index == stringArray.length) {
		  result.add(builder.toString());
		  return;
		}
		  
		// case 1: don't add anything in this level
		helper(stringArray, index + 1, builder, result);
		  
		// case 2: add element in this level
		builder.append(stringArray[index]);
		helper(stringArray, index + 1, builder, result);
		builder.deleteCharAt(builder.length() - 1);
	}
	
	public static void main(String[] args) {
		SubsetWODup solution = new SubsetWODup();
		System.out.println(solution.subSets("abc"));
	}
}
