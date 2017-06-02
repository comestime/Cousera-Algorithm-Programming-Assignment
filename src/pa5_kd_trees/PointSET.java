package pa5_kd_trees;

import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	private Set<Point2D> pointSet;
	
	public PointSET()  {                       // construct an empty set of points
		pointSet = new TreeSet<Point2D>();
	}
	
	public boolean isEmpty()  {                // is the set empty? 
		return pointSet.isEmpty();
	}
	
	public int size()  {                       // number of points in the set 
		return pointSet.size();
	}
	
	public void insert(Point2D p)  {           // add the point to the set (if it is not already in the set)
		if (p == null) throw new java.lang.NullPointerException();
		
		pointSet.add(p);
	}
	
	public boolean contains(Point2D p)  {      // does the set contain point p? 
		if (p == null) throw new java.lang.NullPointerException();
		
		return pointSet.contains(p);
	}
	
	public void draw()  {                      // draw all points to standard draw 
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		for (Point2D point:pointSet) {
			point.draw();
		}
	}
	
	public Iterable<Point2D> range(RectHV rect)  {   // all points that are inside the rectangle
		if (rect == null) throw new java.lang.NullPointerException();
		
		Queue<Point2D> pointQueue = new Queue<Point2D>();
		for (Point2D point:pointSet) {
			if (rect.contains(point)) pointQueue.enqueue(point);
		}
		return pointQueue;
	}
	
	public Point2D nearest(Point2D p)  {       // a nearest neighbor in the set to point p; null if the set is empty
		if (p == null) throw new java.lang.NullPointerException();
		
		double distance = Double.MAX_VALUE;
		Point2D temp = null;
		for (Point2D point:pointSet) {
			if (distance > point.distanceSquaredTo(p)) {
				distance = point.distanceSquaredTo(p);
				temp = point;
			}
		}
		return temp;
	}

	public static void main(String[] args)   {      // unit testing of the methods (optional)
	    // read the points from a file
	    In in = new In(args[0]);
	    PointSET pointSet = new PointSET();
		while (!in.isEmpty()) {
			double x = in.readDouble();
			double y = in.readDouble();
			// System.out.println("x: " + x);
			// System.out.println("y: " + y);
	        Point2D temp = new Point2D(x, y);
	        pointSet.insert(temp);
		}

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 1);
	    StdDraw.setYscale(0, 1);
	    pointSet.draw();
	    StdDraw.show();
	}
}
