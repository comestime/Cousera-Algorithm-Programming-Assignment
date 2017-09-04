/*
Given a binary tree in which each node contains an integer number. Find the maximum possible sum from one leaf node to another leaf node. If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).

Examples

  -15

  /    \

2      11

     /    \

    6     14

The maximum path sum is 6 + 11 + 14 = 31.

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

// 人字形, bottomUp, post-order traversal
public class MaxPathSumBinaryTreeI {
	public int maxPathSum(TreeNode root) {
		int[] max = new int[1];
		max[0] = Integer.MIN_VALUE;
		helper(root, max);
		return max[0];
	}
	
	// int[] max is the global max path sum
	// the return value of the helper function is the max path sum of the sub-tree
	private int helper(TreeNode root, int[] max) {
		if (root == null) return 0;
		
		// max sum of path from one of leaf nodes in left sub tree to current node
		int left = helper(root.left, max);
		// max sum of path from one of leaf nodes in right sub tree to current node
		int right = helper(root.right, max);
		
		// we only care about leaf node to leaf node path
		if (root.left != null && root.right != null) {
			max[0] = Math.max(max[0], left + right + root.key);
			return left > right ? left + root.key : right + root.key;
		}
		
		return root.left == null ? right + root.key : left + root.key;
	}
}
