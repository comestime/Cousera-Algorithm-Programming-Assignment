/*
Given the preorder traversal sequence of a binary search tree, reconstruct the original tree.

Assumptions

The given sequence is not null
There are no duplicate keys in the binary search tree
Examples

preorder traversal = {5, 3, 1, 4, 8, 11}

The corresponding binary search tree is

        5

      /    \

    3        8

  /   \        \

1      4        11

How is the binary tree represented?

We use the pre order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
 */

package binaryTree;

import bfs.TreeNode;

public class ReconstructBSTWPreorder {
	public TreeNode reconstruct(int[] pre) {
		if (pre == null) return null;
		return helper(pre, 0, pre.length - 1);
	}
	
	private TreeNode helper(int[] pre, int left, int right) {
		if (left > right) return null;
		
		TreeNode root = new TreeNode(pre[left]);
		int leftSize = 0;
		for (int i = left + 1; i <= right; i++) {
			if (pre[i] < root.key) {
				leftSize++;
			} else {
				break;
			}
		}
		
		root.left = helper(pre, left + 1, left + leftSize);
		root.right = helper(pre, left + leftSize + 1, right);
		return root;
	}
}
