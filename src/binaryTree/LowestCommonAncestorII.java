/*
 * Given K nodes in a binary tree, find their lowest common ancestor.

Assumptions

K >= 2

There is no parent pointer for the nodes in the binary tree

The given K nodes are guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2, 3, 14 is 5

The lowest common ancestor of 2, 3, 9 is 9
 */

package binaryTree;

import java.util.Set;

import bfs.TreeNode;

public class LowestCommonAncestorII {
	public TreeNode lCA(TreeNode root, Set<TreeNode> nodes) {
		if (root == null || nodes.contains(root)) return root;
		
		TreeNode left = lCA(root.left, nodes);
		TreeNode right = lCA(root.right, nodes);
		
		if (left != null && right != null) return root;
		return left != null ? left : right;
	}
}
