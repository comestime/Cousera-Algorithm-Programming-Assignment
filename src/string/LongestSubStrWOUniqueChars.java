/*
Given a string, find the longest substring without any repeating characters and return the length of it. 
The input string is guaranteed to be not null.

For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", 
we should return 4 in this case.
 */

package string;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStrWOUniqueChars {
	public int longest(String input) {
		// use a hash map to track number of charater occurrence
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		// when the string b/w [slow, fast] does not satisfy uniqueness requirement
		// slow pointer will increment
		int slow = 0;
		// when the string b/w [slow, fast] satisfies uniqueness requirement
		// fast pointer will increment
		int fast = 0;
		// solution
		int left = 0;
		int right = 0;
		int global_max = 0;
		
		while (fast < input.length()) {
			if (!map.containsKey(input.charAt(fast)) || map.get(input.charAt(fast)) == 0) {
				map.put(input.charAt(fast), 1);
				if (fast - slow + 1 > global_max) {
					global_max = fast - slow + 1;
					left = slow;
					right = fast;
				}
				fast++;
			} else {
				while (map.get(input.charAt(fast)) == 1) {
					map.put(input.charAt(slow), 0);
					slow++;
				}
			}
		}
		
		System.out.println(input.substring(left, right + 1));
		return right - left + 1;
	}
	
	public static void main(String args[]) {
		LongestSubStrWOUniqueChars solution = new LongestSubStrWOUniqueChars();
		System.out.println(solution.longest("bcdfbd"));
		System.out.println(solution.longest("bcdfbdghijklmn"));
		System.out.println(solution.longest("abcabcbbc"));
	}
}
