/*
Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences. If the character does not has any adjacent, repeated occurrences, it is not changed.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

There are no adjacent repeated characters with length > 9

Examples

“abbcccdeee” → “ab2c3de3”

 */

package string;

public class CompressString {
	public String compress(String input) {
		if (input == null || input.length() == 0) return input;
		char[] in = input.toCharArray();
		int slow = 0;
		int fast = 1;
		while (fast < input.length()) {
			if (in[slow] != in[fast]) {
		        in[++slow] = in[fast++];
		    } else {
		        int count = 1;
		        while (fast < input.length() && in[slow] == in[fast]) {
		          fast++;
		          count++;
		        }
		        in[++slow] = (char) ('0' + count);
		    }
		}
		return new String(in, 0, slow + 1);
	}
}
