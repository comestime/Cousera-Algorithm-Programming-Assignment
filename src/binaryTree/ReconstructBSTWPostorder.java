/*
Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.

Assumptions

The given sequence is not null
There are no duplicate keys in the binary search tree
Examples

postorder traversal = {1, 4, 3, 11, 8, 5}

the corresponding binary search tree is

        5

      /    \

    3        8

  /   \        \

1      4        11

How is the binary tree represented?

We use the pre order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, #, #, 3, 4, #, #, #] represents the following binary tree:

    1

  /   \

 2     3

      /

    4

 */

package binaryTree;

import bfs.TreeNode;

public class ReconstructBSTWPostorder {
	public TreeNode reconstruct(int[] post) {
		if (post == null || post.length == 0) return null;
		return helper(post, 0, post.length - 1);
	}
	
	// current elements include [left, right]
	private TreeNode helper(int[] post, int left, int right) {
		if (left > right) {
			return null;
		}
		
		TreeNode root = new TreeNode(post[right]);
		// find the boundary which seperates the left sub tree and right sub tree
		int leftSize = 0;
		for (int i = left; i < right; i++) {
			if (post[i] > root.key) {
				break;
			}
			leftSize++;
		}
		
		root.left = helper(post, left, left + leftSize - 1);
		root.right = helper(post, left + leftSize, right - 1);
		
		return root;
	}
}
