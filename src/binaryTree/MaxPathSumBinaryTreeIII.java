/*
 * Find max path sum for all paths from leaf to root in a binary tree
 */

package binaryTree;

import bfs.TreeNode;

// 直上直下
public class MaxPathSumBinaryTreeIII {
	public int maxPathSum(TreeNode root) {
		int[] max = new int[]{Integer.MIN_VALUE};
		// return bottomUp(root);
		topDown(root, 0, max);
		return max[0];
	}
	
	// pre-order traversal
	// prefixSum: path sum from root to current node
	// max: global max path sum; only gets updated in leaf node
	private void topDown(TreeNode root, int prefixSum, int[] max) {
		if (root == null) return;
		
		if (root.left == null && root.right == null) {
			max[0] = Math.max(max[0], prefixSum + root.key);
			return;
		}
		
		topDown(root.left, prefixSum + root.key, max);
		topDown(root.right, prefixSum + root.key, max);
	}
	
	// post-order traversal
	private int bottomUp(TreeNode root) {
		if (root == null) return 0;
		
		// max sum of path from one of leaf nodes in left sub tree to current node
		int left = bottomUp(root.left);
		// max sum of path from one of leaf nodes in right sub tree to current node
		int right = bottomUp(root.right);
		
		return Math.max(left, right) + root.key;
	}
}
