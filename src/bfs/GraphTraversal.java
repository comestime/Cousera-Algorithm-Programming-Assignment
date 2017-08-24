/*
 * Graph Node breadth first search
 * 
 * Graph is represented by a list of GraphNodes, who are pointing at all their neighbors
 */

package bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphTraversal {
	Set<GraphNode> visited;
	Queue<GraphNode> queue;
	
	public GraphTraversal() {
		visited = new HashSet<>();
		queue = new ArrayDeque<>();
	}
	
	public void GraphTraversalBFS(List<GraphNode> graph) {
		// clear the visited set first
		visited.clear();
		
		for (GraphNode node : graph) {
			// if the node is already in the visited set
			if (!visited.add(node)) {
				continue;
			}
			
			queue.offer(node);
			// if the node was not in the set, it's been added to the set through the if statement; no need to add anymore
			System.out.println(node.val);
			
			while (!queue.isEmpty()) {
				GraphNode cur = queue.poll();
				
				for (GraphNode neighbor : cur.neighbors) {
					if (!visited.add(neighbor)) {
						continue;
					}
					
					queue.offer(neighbor);
					// if the node was not in the set, it's been added to the set through the if statement; no need to add anymore
					System.out.println(neighbor.val);
				}
			}
		}
	}
}
