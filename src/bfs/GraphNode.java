package bfs;

import java.util.List;

public class GraphNode {
	public int val;
	public List<GraphNode> neighbors;
	
	public GraphNode(int value) {
		val = value;
	}
}
