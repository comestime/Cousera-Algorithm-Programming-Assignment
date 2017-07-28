/*
 determine whether a word contains all letters that are unique (no duplicate letters in the words)
 */

package bitManipulation;

import java.util.HashSet;
import java.util.Set;

public class UniqueLettersInWords {
	// method 1, explicitly use a set
	public boolean isUnique1(String input) {
		if (input == null) return true;
		Set<Character> set = new HashSet<Character> ();
		for (int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			if (!set.contains(cur)) set.add(cur);
			else return false;
		}
		return true;
	}
	
	// method 2, implicitly use a set by manipulating bits in an integer
	// to distinguish ASCII charset, we need 256 bits, or 8 integers
	public boolean isUnique2(String input) {
		int[] bitVector = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
		for (int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			int bitVectorIdx = cur / 32;
			int bitVectorBit = cur % 32;
			if ((bitVector[bitVectorIdx] & (1 << bitVectorBit)) != 0) return false;
			else {
				bitVector[bitVectorIdx] |= (1 << bitVectorBit);
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		UniqueLettersInWords solution = new UniqueLettersInWords();
		System.out.println(solution.isUnique1("aab"));
		System.out.println(solution.isUnique1("abc"));
		System.out.println(solution.isUnique2("aab"));
		System.out.println(solution.isUnique2("abc"));
	}
}
