/*
Repeatedly remove all adjacent, repeated characters in a given string from left to right.

No adjacent characters should be identified in the final string.

Examples

"abbbaaccz" → "aaaccz" → "ccz" → "z"
"aabccdc" → "bccdc" → "bdc"
 */

package string;

import java.util.ArrayDeque;
import java.util.Deque;

public class DeDupRep {
	// solution 1: in-place
	public String deDup(String input) {
		if (input == null || input.length() == 0) return input;
		    char[] in = input.toCharArray();
		    int slow = 0;
		    int fast = 1;
		    for (; fast < input.length(); fast++) {
		    	boolean add = slow == -1 && in[fast] != in[fast - 1] ||
		            slow != -1 && in[fast] != in[fast - 1] && in[slow] != in[fast];
		    	boolean del = slow != -1 && in[fast] == in[slow];
		    	if (add) {
		    		in[++slow] = in[fast];
		    	}
		    	if (del) {
		    		slow--;
		    	}
		    }
		    return new String(in, 0, slow + 1);
	}
	
	// solution 2: use stack
	public String deDup1(String input) {
		// Write your solution here.
		if (input == null || input.length() == 0) return input;
		Deque<Character> stack = new ArrayDeque<Character>();
		for (int i = 0; i < input.length(); i++) {
		  boolean offer = i == 0 ||
		          stack.isEmpty() && input.charAt(i - 1) != input.charAt(i) ||
		          !stack.isEmpty() && input.charAt(i) != stack.peekFirst() && input.charAt(i - 1) != input.charAt(i);
		  boolean poll = !stack.isEmpty() && input.charAt(i) == stack.peekFirst();
		  if (offer) stack.offerFirst(input.charAt(i));
		  else if (poll) stack.pollFirst();
		}
		    
		char[] result = new char[stack.size()];
		for (int i = stack.size() - 1; i >= 0; i--) {
		  result[i] = stack.pollFirst();
		}
		return new String(result);
	}
	
	public static void main(String args[]) {
		DeDupRep solution = new DeDupRep();
		System.out.println(solution.deDup("aabbbc"));
		System.out.println(solution.deDup("aabbbcccc"));
		System.out.println(solution.deDup("abcccbbaad"));
		System.out.println(solution.deDup("abcdddcbbae"));
	}
}
