/*
Given a string, remove all leading/trailing/duplicated empty spaces.

Assumptions:

The given string is not null.
Examples:

“  a” --> “a”
“   I     love MTV ” --> “I love MTV”
 */

package string;

public class RemoveSpaces {
	public String removeSpaces(String input) {
		// Write your solution here.
		if (input.length() == 0) return new String();
		char[] in = input.toCharArray();
		// state indicating copy opeartion performed in the this iteration
		boolean copy = true;
		int slow = 0;
		int fast = 0;
		// first round, removing all leading spaces
		while (fast < in.length) {
		  if (in[fast] == ' ') fast++;
		  else break;
		}
		// second round, keep only one space after each word
		while (fast < in.length) {
		  if (in[fast] != ' ' || copy) {
		    if (in[fast] == ' ') copy = false;
		    else copy = true;
		    in[slow++] = in[fast++];
		  } else if (!copy) {
		    fast++;
		  }
		}
		// third round, remove the last possible trailing space
		if (slow > 0 && in[slow - 1] == ' ') slow--;
		return new String(in, 0, slow);
	}
	
	public static void main(String[] args) {
		RemoveSpaces x = new RemoveSpaces();
		System.out.println(x.removeSpaces("I Love Yahoo a"));
	}
}
