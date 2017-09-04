/*
 Given a binary tree in which each node contains an integer number. 
 Find the maximum possible sum from any node to any node (the start node and the end node can be the same). 

Assumptions

​The root of the given binary tree is not null
Examples

   -1

  /    \

2      11

     /    \

    6    -14

one example of paths could be -14 -> 11 -> -1 -> 2

another example could be the node 11 itself

The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18

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
public class MaxPathSumBinaryTreeII {
	public int maxPathSum(TreeNode root) {
		int[] max = new int[] {Integer.MIN_VALUE};
		helper(root, max);
		return max[0];
	}
	
	private int helper(TreeNode root, int[] max) {
		if (root == null) return 0;
		
		// prev sub array sum of left sub tree
		int left = helper(root.left, max);
		// prev sub array sum of right sub tree
		int right = helper(root.right, max);
		
		// similar to max sub array sum problem (DP)
		// if previous sub array sum is negative, update the previous sub array sum to current element;
		// otherwise, update the previous sub array sum to previous sub array sum + current element
		left = left < 0 ? 0 : left;
		right = right < 0 ? 0 : right;
		// update global max sub array sum, or global max path sum in this case
		max[0] = Math.max(max[0], left + right + root.key);
		
		return left > right ? left + root.key : right + root.key;
	}
}
