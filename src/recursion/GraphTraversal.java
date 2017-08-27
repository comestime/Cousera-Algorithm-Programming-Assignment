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
	
	// method 2: using a stack and a set
	public void GraphTraversalDFS2(List<GraphNode> graph) {
		// clear visited set first
		visited.clear();
		for (GraphNode node : graph) {
			// if the node is already in the set
			if (!visited.add(node)) {
				continue;
			}
			
			stack.offerFirst(node);
			// if node was not in the set, it's been added to the set after the if statement; no need to add anymore
			System.out.println(node.val);
			
			while (!stack.isEmpty()) {
				GraphNode cur = stack.pollFirst();
				// visit all its neighbor nodes
				for (GraphNode neighbor : cur.neighbors) {
					if (!visited.add(neighbor)) {
						continue;
					}
					
					stack.offerFirst(neighbor);
					// don't add the node anymore; it's been added in the if statement
					System.out.println(node.val);
				}
			}
		}
	}
}
