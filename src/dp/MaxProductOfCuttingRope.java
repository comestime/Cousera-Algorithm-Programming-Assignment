/*
Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]*p[1]* ... *p[m-1]? m is determined by you and must be greater than 0 (at least one cut must be made). Return the max product you can have.

Assumptions

n >= 2
Examples

n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).
 */

package dp;

public class MaxProductOfCuttingRope {
	public int maxProduct(int length) {
		int[] result = new int[length + 1];
		// base case
		result[1] = 1;
		result[2] = 1;
		// induction rule for rope of length >= 3
		for (int i = 3; i <= length; i++) {
			int max = 0;
			for (int j = 1; j <= i - 1; j++) {
				int firstLen = j;
				int lastLen = i - j;
				int curProduct = Math.max(result[firstLen], firstLen) * Math.max(result[lastLen], lastLen);
				if (curProduct > max) {
					max = curProduct;
				}
			}
			result[i] = max;
		}
		return result[length];
	}
	
	public static void main(String[] args) {
		MaxProductOfCuttingRope s = new MaxProductOfCuttingRope();
		System.out.print(s.maxProduct(12)); // 81
	}
}
