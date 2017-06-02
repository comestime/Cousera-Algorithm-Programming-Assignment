package pa5_kd_trees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private static class Node {
		   private Point2D p;          // the point
		   private RectHV rect;        // the rectangle to be splitted by this node
		   private Node left;          // the left/bottom subtree
		   private Node right;         // the right/top subtree
		   private boolean isVertical; // true if this node splits the rectangle vertically; false if horizontally
		   
		   private Node(Point2D p, RectHV rect, boolean isVertical) {
			   if (p == null || rect == null) throw new java.lang.NullPointerException();
			   this.p = p;
			   this.rect = rect;
			   this.isVertical = isVertical;
			   left = null;
			   right = null;
		   }
	}
	
	private Node root;
	private int count;
	
	public KdTree() {                          // construct an empty set of points
		root = null;
		count = 0;
	}
	
	public boolean isEmpty() {                 // is the set empty? 
		return root == null;
	}
	
	public int size() {                        // number of points in the set 
		return count;
	}
	
	public void insert(Point2D p) {            // add the point to the set (if it is not already in the set)
		if (p == null) throw new java.lang.NullPointerException();
		// root node will split the unit rectangle vertically
		if (root == null) {
			root = new Node(p, new RectHV(0.0, 0.0, 1.0, 1.0), true);
			count++;
		}
		else {
			Node cur = root;
			while (true) {
				if (cur.p.equals(p)) break;	  // do not add point if it is already in the set
				// go left is point to be inserted is in the left/bottom subtree
				boolean goLeft = cur.isVertical && (Point2D.X_ORDER.compare(p, cur.p) < 0) ||
								  !cur.isVertical && (Point2D.Y_ORDER.compare(p, cur.p) < 0);
				RectHV temp = null;
				if (goLeft) {
					if (cur.left != null) {
						cur = cur.left;
						continue;
					} else if (cur.isVertical) {
						temp = new RectHV(cur.rect.xmin(), cur.rect.ymin(),
												 cur.p.x(), cur.rect.ymax());
					} else {
						temp = new RectHV(cur.rect.xmin(), cur.rect.ymin(),
								 				 cur.rect.xmax(), cur.p.y());
					}
					cur.left = new Node(p, temp, !cur.isVertical);
					count++;
					break;
				} else {
					if (cur.right != null) {
						cur = cur.right;
						continue;
					} else if (cur.isVertical) {
						temp = new RectHV(cur.p.x(), cur.rect.ymin(),
												 cur.rect.xmax(), cur.rect.ymax());

					} else {
						temp = new RectHV(cur.rect.xmin(), cur.p.y(),
				 				 				 cur.rect.xmax(), cur.rect.ymax());
					}
					cur.right = new Node(p, temp, !cur.isVertical);
					count++;
					break;
				}
			}
		}
	}
	
	public boolean contains(Point2D p) {       // does the set contain point p? 
		if (p == null) throw new java.lang.NullPointerException();
		Node cur = root;
		while (cur != null) {
			if (cur.p.equals(p)) return true;
			// go left is point to be inserted is in the left/bottom subtree
			boolean goLeft = cur.isVertical && (Point2D.X_ORDER.compare(p, cur.p) < 0) ||
							  !cur.isVertical && (Point2D.Y_ORDER.compare(p, cur.p) < 0);
			if (goLeft) cur = cur.left;
			else cur = cur.right;
		}
		return false;
	}
	
	private void draw(Node node) {
		if (node == null) return;
		// draw current point first
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		node.p.draw();
		// draw the horizontal/vertical line
		if (node.isVertical) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius();
			StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.setPenRadius();
			StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
		}
		draw(node.left);
		draw(node.right);
	}
	
	public void draw() {                       // draw all points to standard draw
		draw(root);
	}
	
	private void range(RectHV rect, Node node, Queue<Point2D> pointQueue) {
		// in case root is null
		if (node == null) return;
		// check current node, enqueue if found inside range
		if (rect.contains(node.p)) pointQueue.enqueue(node.p);
		// check left subtree recursively only if rect intersects left/bottom subtree
		if (node.left != null && rect.intersects(node.left.rect)) range(rect, node.left, pointQueue);
		// check right subtree recursively only if rect intersects right/upper subtree
		if (node.right != null && rect.intersects(node.right.rect)) range(rect, node.right, pointQueue);
	}
	
	public Iterable<Point2D> range(RectHV rect) {  // all points that are inside the rectangle 
		if (rect == null) throw new java.lang.NullPointerException();
		
		Queue<Point2D> pointQueue = new Queue<Point2D>();
		range(rect, root, pointQueue);
		return pointQueue;
	}
	
	/*
	 * To find a closest point to a given query point, 
	 * start at the root and recursively search in both subtrees using the following pruning rule: 
	 * if the closest point discovered so far is closer than the distance between the query point 
	 * and the rectangle corresponding to a node, there is no need to explore that node (or its subtrees). 
	 * That is, a node is searched only if it might contain a point that is closer than the best one found so far. 
	 * The effectiveness of the pruning rule depends on quickly finding a nearby point. 
	 * To do this, organize your recursive method so that when there are two possible subtrees to go down, 
	 * you always choose the subtree that is on the same side of the splitting line as the query point 
	 * as the first subtree to explore—the closest point found 
	 * while exploring the first subtree may enable pruning of the second subtree.
	 */
	private Point2D nearest(Point2D p, Node node) {
		if (node == null) return null;
		
		// get the nearest point in the left subtree if left/bottom rectangle contains the query point
		// or in the right subtree if right/upper rectangle contains the query point
		// note the result "firstPoint" might be null
		boolean goLeftFirst = (node.left != null) && node.left.rect.contains(p);
		Point2D firstPoint = null;
		if (goLeftFirst) firstPoint = nearest(p, node.left);
		else firstPoint = nearest(p, node.right);
		
		// compare the firstPoint and current node
		// note the "nearestPoint" won't be null since "node" shouldn't be null
		Point2D nearestPoint = null;
		if (firstPoint == null) nearestPoint = node.p;
		else if (p.distanceSquaredTo(firstPoint) < p.distanceSquaredTo(node.p)) nearestPoint = firstPoint;
		else nearestPoint = node.p;
		
		// check the other subtree if necessary (pruning rule implementation)
		// note the result "secondPoint" might be null
		boolean needCheckOtherSubtree = (firstPoint == null) ||
			(goLeftFirst && (node.right != null) && 
					(node.right.rect.distanceSquaredTo(p) < p.distanceSquaredTo(nearestPoint))) ||
			(!goLeftFirst && (node.left != null) && 
					(node.left.rect.distanceSquaredTo(p) < p.distanceSquaredTo(nearestPoint)));
		Point2D secondPoint = null;
		if (needCheckOtherSubtree && goLeftFirst) secondPoint = nearest(p, node.right);
		else if (needCheckOtherSubtree && !goLeftFirst) secondPoint = nearest(p, node.left);
		
		// the final comparison
		if (secondPoint == null) return nearestPoint;
		else if (p.distanceSquaredTo(secondPoint) < p.distanceSquaredTo(nearestPoint)) return secondPoint;
		else return nearestPoint;
	}
	
	public Point2D nearest(Point2D p) {            // a nearest neighbor in the set to point p; null if the set is empty
		if (p == null) throw new java.lang.NullPointerException();
		return nearest(p, root);
	}

	public static void main(String[] args) {       // unit testing of the methods (optional) 
	    // read the points from a file
	    In in = new In(args[0]);
	    KdTree treeSet = new KdTree();
		while (!in.isEmpty()) {
			double x = in.readDouble();
			double y = in.readDouble();
			// System.out.println("x: " + x);
			// System.out.println("y: " + y);
	        Point2D temp = new Point2D(x, y);
	        treeSet.insert(temp);
		}

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 1);
	    StdDraw.setYscale(0, 1);
	    treeSet.draw();
	    StdDraw.show();
	}
}
