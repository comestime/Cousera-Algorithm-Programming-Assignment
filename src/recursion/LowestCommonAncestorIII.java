/*
 * Given two nodes in a k-nary tree, find their lowest common ancestor.

Assumptions

There is no parent pointer for the nodes in the k-nary tree

The given two nodes are guaranteed to be in the k-nary tree
 */

package recursion;

import bfs.TreeNode;

public class LowestCommonAncestorIII {
	public TreeNode lCA(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null || root == one || root == two) {
			return root;
		}
		
		TreeNode temp = null;
		int counter = 0;
		
		for (TreeNode node : root.children) {
			TreeNode cur = lCA(node, one, two);
			if (cur != null) {
				temp = cur;
				counter++;
			}
			
			if (counter > 1) {
				return root;
			}
		}
		
		return temp;
	}
}
