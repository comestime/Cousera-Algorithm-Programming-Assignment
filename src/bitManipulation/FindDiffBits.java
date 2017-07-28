/*
how to determine the number of bits that are different between two integers?
 */

package bitManipulation;

public class FindDiffBits {
	public int findDiffBits1(int a, int b) {
		int c = a ^ b;
		// count number of 1s in c
		int mask = 1;
		int count = 0;
		while (mask != 0) {
			if ((mask & c) != 0) count++;
			mask <<= 1;
		}
		return count;
	}
	
	// note this is not the right way to code if one of the numbers is negative
	// c might be less than 0, so that its MSB is 1
	// keep left shifting the number will never change its value to 0
	public int findDiffBits2(int a, int b) {
		int count = 0;
		for (int c = a ^ b; c != 0; c >>= 1){
			count += (c & 1);
		}
		return count;
	}
	
	public static void main(String[] args) {
		FindDiffBits solution = new FindDiffBits();
		System.out.println(solution.findDiffBits1(5, 8));
		System.out.println(solution.findDiffBits1(5, 8));
	}
}
