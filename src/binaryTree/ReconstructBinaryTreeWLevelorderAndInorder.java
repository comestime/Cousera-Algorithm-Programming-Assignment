/*
Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

Assumptions

The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree
Examples

levelorder traversal = {5, 3, 8, 1, 4, 11}

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bfs.TreeNode;

// worst case, there are n levels, and the time complexity for each level = n, n - 1, n - 2, ...
// Time = O(N ^ 2)
// Space = O(N ^ 2); there are at most n levels, and in each level we need to allocate two temporary lists, levelLeft & levelRight
public class ReconstructBinaryTreeWLevelorderAndInorder {
	public TreeNode reconstruct(int[] in, int[] level) {
		Map<Integer, Integer> inMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			inMap.put(in[i], i);
		}
		
		List<Integer> levelList = new ArrayList<>();
		for (int num : level) {
			levelList.add(num);
		}
		
		return helper(levelList, inMap);
	}
	
	private TreeNode helper(List<Integer> level, Map<Integer, Integer> inMap) {
		if (level.isEmpty()) {
			return null;
		}
		
		TreeNode root = new TreeNode(level.remove(0));
		List<Integer> leftLevel = new ArrayList<>();
		List<Integer> rightLevel = new ArrayList<>();
		
		for (int num : level) {
			if (inMap.get(num) < inMap.get(root.key)) {
				leftLevel.add(num);
			} else {
				rightLevel.add(num);
			}
		}
		
		root.left = helper(leftLevel, inMap);
		root.right = helper(rightLevel, inMap);
		
		return root;
	}
}
