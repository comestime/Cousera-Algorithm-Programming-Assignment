/*
Given the postorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

Assumptions

The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree
Examples

postorder traversal = {1, 4, 3, 11, 8, 5}

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

public class ReconstructBinaryTreeWPostorderAndInorder {
	public TreeNode reconstruct(int[] in, int[] post) {
		// based on the assumption that no duplicated elements exist in the tree
		// for fast indexing, constructing a map, key = element in inorder array, value = index in inorder array
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		return helper(in, 0, in.length - 1, post, 0, post.length - 1, map);
	}
	
	private TreeNode helper(int[] in, int inLeft, int inRight, int[] post, int postLeft, int postRight, Map<Integer, Integer> map) {
		if (inLeft > inRight) {
			return null;
		}
		
		TreeNode root = new TreeNode(post[postRight]);
		int rootInInorder = map.get(root.key);
		int leftSize = rootInInorder - inLeft;
		root.left = helper(in, inLeft, rootInInorder - 1, post, postLeft, postLeft + leftSize - 1, map);
		root.right = helper(in, rootInInorder + 1, inRight, post, postLeft + leftSize, postRight - 1, map);
		return root;
	}
}
