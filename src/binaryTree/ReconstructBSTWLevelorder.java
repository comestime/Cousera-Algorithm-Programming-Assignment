/*
Given the levelorder traversal sequence of a binary search tree, reconstruct the original tree.

Assumptions

The given sequence is not null
There are no duplicate keys in the binary search tree
Examples

levelorder traversal = {5, 3, 8, 1, 4, 11}

the corresponding binary search tree is

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

import java.util.ArrayList;
import java.util.List;

import bfs.TreeNode;

public class ReconstructBSTWLevelorder {
	public TreeNode reconstruct(int[] level) {
		List<Integer> list = new ArrayList<>();
 		for (int num : level) {
 				list.add(num);
		}
		
		return helper(list);
	}
	
	private TreeNode helper(List<Integer> list) {
		if (list.isEmpty()) return null;
		
		// use two lists to maintain the level order in left sub tree and right sub tree
		List<Integer> listLeft = new ArrayList<>();
		List<Integer> listRight = new ArrayList<>();
		TreeNode root = new TreeNode(list.get(0));
 		for (int num : list) {
 			if (num < root.key) {
 				listLeft.add(num);
 			} else if (num > root.key) {
 				listRight.add(num);
 			}
		}
		
 		root.left = helper(listLeft);
 		root.right = helper(listRight);
		return root;
	}
}
