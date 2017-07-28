package bitManipulation;

public class PowerOfTwo {
	// method 1: mathematical way
	public boolean isPowerOfTwo1(int x) {
		// corner case, -2^31 contains only 1 bit of one, but it's negative
		// all power of two numbers are positive
		return (x > 0) && ((x & (x - 1)) == 0) ? true : false;
	}
	
	// method 2: count number of 1s, and not -2^31
	public boolean isPowerOfTwo2(int x) {
		int count = 0;
		int mask = 1;
		// corner case, power of two number is always positive
		if (x <= 0) return false;
		while (mask != 0) {
			if ((x & mask) != 0) count++;
			mask <<= 1;
		}
		return count == 1;
	}
	
	public static void main(String[] args) {
		PowerOfTwo solution = new PowerOfTwo();
		System.out.println(solution.isPowerOfTwo1(4));
		System.out.println(solution.isPowerOfTwo1(5));
		System.out.println(solution.isPowerOfTwo2(4));
		System.out.println(solution.isPowerOfTwo2(5));
	}
}
