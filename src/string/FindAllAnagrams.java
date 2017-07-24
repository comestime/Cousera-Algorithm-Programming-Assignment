/*
Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

Assumptions

s is not null or empty.
l is not null.

Examples
l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
 */

package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagrams {
	public List<Integer> allAnagrams(String s, String l) {
		List<Integer> result = new ArrayList<Integer>();
		if (l.length() == 0) return result;
		if (s.length() > l.length()) return result;
		
		// use a hash map to track string s's pattern
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), 1);
			} else {
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			}
		}
		
		// right bound of the fixed window
		int right = 0;
		// types of characters to match
		int typesOfChar2Match = map.size();
		
		while (right < l.length()) {
			char tmp = l.charAt(right);
			Integer count = map.get(tmp);
			
			// handle the right bound of the window
			if (count != null) {
				map.put(tmp, count - 1);
				if (count == 1) typesOfChar2Match--;
				if (count == 0) typesOfChar2Match++;
			}
			
			// handle the left bound of the window
			if (right >= s.length()) {
				tmp = l.charAt(right - s.length());
				count = map.get(tmp);
				if (count != null) {
					map.put(tmp, count + 1);
					if (count == -1) typesOfChar2Match--;
					if (count == 0) typesOfChar2Match++;
				}
			}
			
			if (typesOfChar2Match == 0) result.add(right - s.length() + 1);
			right++;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		FindAllAnagrams solution = new FindAllAnagrams();
		System.out.println(solution.allAnagrams("ab", "abcabcaaaaaabcaaabbbcc"));
	}
}
