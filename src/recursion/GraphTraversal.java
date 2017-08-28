/*
 * Graph Node depth first search
 * 
 * Graph is represented by a list of GraphNodes, who are pointing at all their neighbors
 */

package recursion;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bfs.GraphNode;

public class GraphTraversal {
	Set<GraphNode> visited;
	Deque<GraphNode> stack;
	
	public GraphTraversal() {
		visited = new HashSet<>();
		stack = new ArrayDeque<>();
	}
	
	// method 1: recursion, time complexity = O(V * (1 + E/V)) = O(V + E)
	public void GraphTraversalDFS1(List<GraphNode> graph) {
		// clear visited set first
		visited.clear();
		for (GraphNode node : graph) {
			if (visited.add(node)) {
				System.out.println(node.val);
				recursion(node);
			}
		}
	}
	
	private void recursion(GraphNode node) {
		for (GraphNode neighbor : node.neighbors) {
			if (visited.add(node)) {
				System.out.println(node.val);
				recursion(node);
			}
		}
	}
}
