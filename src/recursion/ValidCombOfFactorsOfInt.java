/*
 * print all valid combination of factors that form an integer
 * 
 * e.g., 12 = 6 * 2 = 4 * 3 = 3 * 2 * 2 = ...
 * 
 * Assumption: num >= 2
 */

package recursion;

import java.util.ArrayList;
import java.util.List;

public class ValidCombOfFactorsOfInt {
	public List<List<Integer>> factor(int num) {
		// step 1: find all factors of the number
		List<Integer> factors = new ArrayList<>();
		for (int i = num - 1; i > 1; i--) {
			if (num % i == 0) {
				factors.add(i);
			}
		}
		System.out.println("All factors: " + factors);
		
		List<Integer> temp = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		helper(num, factors, 0, temp, result);
		
		return result;
	}
	
	private void helper(int remainder, List<Integer> factors, int index, List<Integer> temp, List<List<Integer>> result) {		
		// base case
		if (index == factors.size() - 1) {
			int curFactor = factors.get(index);
			int i;
			for (i = 1; (int)Math.pow(curFactor, i) <= remainder; i++) {
				temp.add(curFactor);
				System.out.println("i = " + i + " temp: " + temp);
			}
			
			if ((int)Math.pow(curFactor, i - 1) == remainder) {
				result.add(new ArrayList<Integer>(temp));
				System.out.println("added!");
			}
			// restore current level
			while (temp.size() != 0 && temp.get(temp.size() - 1) == curFactor) {
				temp.remove(temp.size() - 1);
			}
			return;
		}
		
		int curFactor = factors.get(index);
		// case 1: don't add any factor in current level
		helper(remainder, factors, index + 1, temp, result);
		
		// case 2: add factors in current level
		for (int i = 1; Math.pow(curFactor, i) <= remainder; i++) {
			if (remainder % Math.pow(curFactor, i) == 0) {
				temp.add(curFactor);
				System.out.println("curFactor: " + curFactor + " remainder: " + (int)(remainder / Math.pow(curFactor, i)) + " temp: " + temp);
				helper((int)(remainder / Math.pow(curFactor, i)), factors, index + 1, temp, result);
			}
		}
		
		// restore the current level
		while (temp.size() != 0 && temp.get(temp.size() - 1) == curFactor) {
			temp.remove(temp.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		ValidCombOfFactorsOfInt s = new ValidCombOfFactorsOfInt();
		System.out.println(s.factor(28));
	}
}
