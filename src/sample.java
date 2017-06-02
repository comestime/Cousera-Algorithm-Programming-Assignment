
public class sample {
	private class Node {
		private int val;
		private int key;
		private Node left;
		private Node right;
	}
	private Node root;
	
	public Node floor(int key) {
		Node cur = root;
		Node prev = null;
		while (true) {
			if (cur == null) return prev;
			if (cur.key == key) return cur;
			if (cur.key < key) {
				prev = cur;
				cur = cur.right;
				continue;
			}
			if (cur.key > key) {
				cur = cur.left;
				continue;
			}
		}
	}
}
