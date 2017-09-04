/*
 * Given k nodes in a k-nary tree, find their lowest common ancestor.

Assumptions

There is no parent pointer for the nodes in the k-nary tree

The given k nodes are guaranteed to be in the k-nary tree
 */

package binaryTree;

import java.util.Set;

import bfs.TreeNode;

public class LowestCommonAncestorIV {
	
	public TreeNode lCA(TreeNode root, Set<TreeNode> nodes) {
		if (root == null || nodes.contains(root)) {
			return root;
		}
		
		TreeNode temp = null;
		int counter = 0;
		
		for (TreeNode node : root.children) {
			TreeNode cur = lCA(node, nodes);
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
