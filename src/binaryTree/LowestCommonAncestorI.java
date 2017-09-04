/*
 * Given two nodes in a binary tree, find their lowest common ancestor.

Assumptions

There is no parent pointer for the nodes in the binary tree

The given two nodes are guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9
 */

package binaryTree;

import bfs.TreeNode;

public class LowestCommonAncestorI {	
	public TreeNode lCA(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null || root == one || root == two) return root;
		
		TreeNode left = lCA(root.left, one, two);
		TreeNode right = lCA(root.right, one, two);
		
		if (left != null && right != null) return root;
		return left != null ? left : right;
	}

}
