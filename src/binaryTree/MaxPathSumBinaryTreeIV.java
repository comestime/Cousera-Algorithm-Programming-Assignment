/*
Given a binary tree in which each node contains an integer number. Find the maximum possible subpath sum(both the starting and ending node of the subpath should be on the same path from root to one of the leaf nodes, and the subpath is allowed to contain only one node).

Assumptions

The root of given binary tree is not null
Examples

   -5

  /    \

2      11

     /    \

    6     14

           /

        -3

The maximum path sum is 11 + 14 = 25

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
 */

package binaryTree;

import bfs.TreeNode;

// 直上直下
public class MaxPathSumBinaryTreeIV {
	public int maxPathSum(TreeNode root) {
		int[] max = new int[]{Integer.MIN_VALUE};
		// bottomUp(root, max);
		topDown(root, 0, max);
		return max[0];
	}
	
	// pre-order traversal
	private void topDown(TreeNode root, int prefixSum, int[] max) {
		if (root == null) return;
		
		// similar to find max sub array sum problem (DP)
		// prefix sum = previous max sub array sum
		prefixSum = Math.max(prefixSum, 0);
		
		// update global max
		max[0] = Math.max(max[0], prefixSum + root.key);
		
		topDown(root.left, prefixSum + root.key, max);
		topDown(root.right, prefixSum + root.key, max);
	}
	
	// post-order traversal
	private int bottomUp(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		
		// similar to max sub array sum problem (DP)
		// if previous max sub array sum is negative, update the previous max sub array sum to current element;
		// otherwise, update the previous max sub array sum to previous max sub array sum + current element
		int left = bottomUp(root.left, max);
		left = left < 0 ? 0 : left;
		int right = bottomUp(root.right, max);
		right = right < 0 ? 0 : right;
		
		int temp = Math.max(left, right);
		max[0] = Math.max(max[0], temp + root.key); 
		
		return temp + root.key;
	}
}
