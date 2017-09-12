/*
Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.

Assumptions

There is parent pointer for the nodes in the binary tree

The given two nodes are not guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
 */

package binaryTree;

import bfs.TreeNode;

public class LowestCommonAncestorV {
	public TreeNode lowestCommonAncestor(TreeNode one, TreeNode two) {
		int oneLen = getH(one);
		int twoLen = getH(two);
		// calculate the height differerence between the two nodes
		// move the deeper node to the same height as the shallower node
		if (oneLen <= twoLen) {
			return getLCA(two, one, twoLen - oneLen);
		} else {
			return getLCA(one, two, oneLen - twoLen);
		}
	}
	
	private TreeNode getLCA(TreeNode longer, TreeNode shorter, int diff) {
		while (diff > 0) {
			longer = longer.parent;
			diff--;
		}
		
		while (longer != shorter) {
			longer = longer.parent;
			shorter = shorter.parent;
		}
		
		return longer;
	}
	
	private int getH(TreeNode root) {
		int len = 0;
		while (root != null) {
			root = root.parent;
			len++;
		}
		return len;
	}
}
