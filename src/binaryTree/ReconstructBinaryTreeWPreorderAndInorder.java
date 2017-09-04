/*
Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

Assumptions

The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree
Examples

preorder traversal = {5, 3, 1, 4, 8, 11}

inorder traversal = {1, 3, 4, 5, 8, 11}

the corresponding binary tree is

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

import java.util.HashMap;
import java.util.Map;

import bfs.TreeNode;

public class ReconstructBinaryTreeWPreorderAndInorder {
	public TreeNode reconstruct(int[] in, int[] pre) {
		// use an hash map to track each node in inorder array to its index in inorder array
		// assuming no duplicated keys in binary tree
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		
		return helper(in, 0, in.length - 1, pre, 0, pre.length - 1, map);
	}
	
	private TreeNode helper(int[] in, int inLeft, int inRight, int[] pre, int preLeft, int preRight, Map<Integer, Integer> map) {
		if (inLeft > inRight) {
			return null;
		}
		
		TreeNode root = new TreeNode(pre[preLeft]);
		int leftSize = map.get(root.key) - inLeft;
		
		root.left = helper(in, inLeft, inLeft + leftSize - 1, pre, preLeft + 1, preLeft + leftSize, map);
		root.right = helper(in, inLeft + leftSize + 1, inRight, pre, preLeft + leftSize + 1, preRight, map);
		
		return root;
	}
}
