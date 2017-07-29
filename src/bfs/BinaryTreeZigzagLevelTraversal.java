/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

 */

package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeZigzagLevelTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        // odd level, each node traverse its left sub node first, then right sub node
        // even level, each node traverse its right sub node first, then left sub node
        int level = 1;
        // odd level, using pollFirst() & offerLast() API
        // even level, using pollLast() & offerFirst() API
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        deque.offerFirst(root);
        
        // need BFS to traverse the tree, and need layer-by-layer ordering
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> tempResult = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                // even level
                if ((level & 1) == 0) {
                    TreeNode cur = deque.pollFirst();
                    tempResult.add(cur.val);
                    if (cur.right != null)  deque.offerLast(cur.right);
                    if (cur.left != null) deque.offerLast(cur.left);
                } else {
                // odd level
                    TreeNode cur = deque.pollLast();
                    tempResult.add(cur.val);
                    if (cur.left != null) deque.offerFirst(cur.left);
                    if (cur.right != null) deque.offerFirst(cur.right);
                }
            }
            level++;
            result.add(tempResult);
        }
        
        return result;
    }
}
