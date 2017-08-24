package bfs;

import java.util.List;

public class TreeNode {
	public int val;
	// to mimic a binary tree
    public TreeNode left;
    public TreeNode right;
    // to mimic the parent pointer
    public TreeNode parent;
    // to mimic a k-nary tree
    public List<TreeNode> children;
    
    public TreeNode(int x) { val = x; }
}
