/*
Given a binary tree in which each node contains an integer number. 
Determine if there exists a path (the path can only be from one node to itself or to any of its descendants),
the sum of the numbers on the path is the given target number.

Examples

    5

  /    \

2      11

     /    \

    6     14

  /

 3

If target = 17, There exists a path 11 + 6, the sum of the path is target.

If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.

If target = 10, There does not exist any paths sum of which is target.

If target = 11, There exists a path only containing the node 11.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

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

public class BinaryTreePathSum2TargetIII {
	public boolean exist(TreeNode root, int target) {
		// prefix sum = sum of all node values from root to current node
		List<Integer> prefixSum = new ArrayList<>();
		return helper(root, target, prefixSum);
	}
	
	private boolean helper(TreeNode root, int target, List<Integer> prefixSum) {
		if (root == null) return false;
		
		// calculate current prefix sum
		// in case of ArrayIndexOutOfBoundException
		int prev = prefixSum.size() != 0 ? prefixSum.get(prefixSum.size() - 1) : 0;
		int cur = prev + root.key;
		
		// case 1: the sum of the path from root to current node equals to target
		if (cur == target) return true;
		
		// case 2: the sum of the path from non-root to current node equals to target 
		for (Integer curSum : prefixSum) {
			if (cur - curSum == target) {
				return true;
			}
		}
		
		prefixSum.add(cur);		
		boolean left = helper(root.left, target, prefixSum);
		boolean right = helper(root.right, target, prefixSum);
		prefixSum.remove(prefixSum.size() - 1);
		
		return left || right;
	}
}
