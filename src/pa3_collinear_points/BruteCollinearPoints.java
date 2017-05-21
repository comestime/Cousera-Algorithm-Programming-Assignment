package pa3_collinear_points;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private LineSegment[] segment;
	private int numOfSegments;
	public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
		// check the points array reference is null
		if (points == null) throw new java.lang.NullPointerException();
		Arrays.sort(points);
		for (int i = 0; i < points.length - 1; i++) {
			// check if any element is null
			if (points[i] == null || points[i + 1] == null)
				throw new java.lang.NullPointerException();
			// no repeated points should be allowed
			if (points[i].compareTo(points[i + 1]) ==  0)
				throw new java.lang.IllegalArgumentException();
		}
		
		numOfSegments = 0;
		segment = new LineSegment[2];
		for (int i = 0; i < points.length; i++)
			for (int j = i + 1; j < points.length; j++)
				for (int k = j + 1; k < points.length; k++)
					for (int l = k + 1; l < points.length; l++) {
						if (points[i].slopeOrder().compare(points[j], points[k]) == 0 &&
							points[i].slopeOrder().compare(points[j], points[l]) == 0) {
							
							if (numOfSegments == segment.length) resize(2 * segment.length);
							LineSegment temp = new LineSegment(points[i], points[l]);
							segment[numOfSegments++] =  temp;
						}
					}
	}
	
	private void resize(int capacity) {
		LineSegment[] temp = new LineSegment[capacity];
		for (int i = 0; i < segment.length; i++) {
			temp[i] = segment[i];
		}
		segment = temp;
	}
	
	public int numberOfSegments()   { // the number of line segments
		return numOfSegments;
	}
	
	public LineSegment[] segments()   { // the line segments
		// System.out.println("inside brute!");
		// System.out.println(numOfSegments);
		if (numOfSegments == 0) return new LineSegment[0];
		else return Arrays.copyOfRange(segment, 0, numOfSegments);
	}
	
	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}
