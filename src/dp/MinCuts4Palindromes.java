/*
Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. 
Determine the fewest cuts needed for a palindrome partitioning of a given string.

Assumptions

The given string is not null
Examples

“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.

The minimum number of cuts needed is 3.
 */

package dp;

public class MinCuts4Palindromes {
	public int minCuts(String input) {
		if (input == null || input.length() == 0) return 0;
		char[] in = input.toCharArray();
		// return DP1(in);
		return DP2(in);
	}
	
	// Method 1: 1-D DP (Time = O(N ^ 3), Space = O(N))
	// M[i] represents the min cuts for sub string [0, i) to be palindrome
	// base case: M[1] = 0, since it's already palindrome
	// induction rule: similar to dictionary
	// case 1: M[i] = 0 if string [0, i) is palindrome;
	// case 2: M[i] = M[j] + 1 if string[j + 1, i) is palindrome, where 1 <= i < strLen and 1 <= j < i
	// return value = M[strLen]
	private int DP1(char[] in) {
		int[] M = new int[in.length + 1];
		M[1] = 0;
		for (int i = 2; i <= in.length; i++) {
			// case 1
			if (isPalindrome(in, 0, i - 1)) {
				M[i] = 0;
			} else {
				// case 2
				// initialize to worse case, cut every single character
				M[i] = i - 1;
				for (int j = 1; j < i; j++) {
					if (isPalindrome(in, j, i - 1)) {
						M[i] = Math.min(M[i], M[j] + 1);
					}
				}
			}
		}
		
		return M[in.length];
	}
	
	// Method 2: 2-D dp (Time = O(N ^ 2), Space = O(N ^ 2))
	// Do a preprocessing to get a matrix[N][N], where matrix[i][j] represents if the substring [i, j] is palindrome
	private int DP2(char[] in) {
		// preprocessing, a DP sub problem
		// base case: matrix[0][0] = matrix[1][1] = ... = matrix[N - 1][N - 1] = true since they are already palindrome
		// induction rule: matrix[i][j] = (array[i] == array[j]) && (i + 1 >= j - 1 || matrix[i + 1][j - 1])
		boolean[][] matrix = new boolean[in.length][in.length];
		// base case
		for (int i = 0; i < in.length; i++) {
			matrix[i][i] = true;
		}
		// induction rule
		for (int size = 2; size <= in.length; size++) {
			for (int i = 0; i < in.length - size + 1; i++) {
				int j = i + size - 1;
				matrix[i][j] = (in[i] == in[j]) && (i + 1 >= j - 1 || matrix[i + 1][j - 1]);
			}
		}
		
		// debug
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
		
		// pretty much the same as method 1
		int[] M = new int[in.length + 1];
		M[1] = 0;
		for (int i = 2; i <= in.length; i++) {
			// case 1
			if (matrix[0][i - 1]) {
				M[i] = 0;
			} else {
				// case 2
				// worse case, cut every single character
				M[i] = i - 1;
				for (int j = 1; j < i; j++) {
					if (matrix[j][i - 1]) {
						M[i] = Math.min(M[i], M[j] + 1);
					}
				}
			}
		}
		
		return M[in.length];
	}
	
	private boolean isPalindrome(char[] in, int left, int right) {
		while (left < right) {
			if (in[left] != in[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		MinCuts4Palindromes s = new MinCuts4Palindromes();
		s.minCuts("bbb");
	}
}
