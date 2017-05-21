package pa3_collinear_points;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private int numOfSegments;
	private LineSegment[] segments;
	
	public FastCollinearPoints(Point[] points)   {  // finds all line segments containing 4 or more points
		// check the points array reference is null
		if (points == null) throw new java.lang.NullPointerException();
		// sort according to natrual order, to find repeated points
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
		segments = new LineSegment[2];
		
		Point[] temp = Arrays.copyOfRange(points, 0, points.length);
		for (int i = 0; i < points.length; i++) {
			// loop through each point, and make it as the origin
			Point origin = points[i];
			
			// sort the temp array according to slope
			// the origin point will always be in index[0]
			// Arrays.sort() uses merge sort, which is a stable sort algorithm
			Arrays.sort(temp, origin.slopeOrder());
			
			// two pointers
			// j: lower bound for points of equal slope
			// idx: upper bound for points of equal slope
			int j = 1, idx = 2;
			while (idx < temp.length) {
				if (origin.slopeOrder().compare(temp[j], temp[idx]) == 0) {
					idx++;
					continue;
				} else if (idx - j >= 3) {  
					// at lease 3 points have the same slope w.r.t. origin
					// need to test if the segments are duplicated
					// if the origin point is the smallest point in segment, add this segment to LineSegment
					// otherwise, skip it since it's duplicated
					Arrays.sort(temp, j, idx);
					if (origin.compareTo(temp[j]) < 0) {
						if (numOfSegments == segments.length) resize(2 * segments.length);
						segments[numOfSegments++] = new LineSegment(origin, temp[idx - 1]);
					}
				}
				j = idx++;
			}
			if (idx - j >= 3) {  
				// at lease 3 points have the same slope w.r.t. origin
				// need to test if the segments are duplicated
				// if the origin point is the smallest point in segment, add this segment to LineSegment
				// otherwise, skip it since it's duplicated
				Arrays.sort(temp, j, idx);
				if (origin.compareTo(temp[j]) < 0) {
					if (numOfSegments == segments.length) resize(2 * segments.length);
					segments[numOfSegments++] = new LineSegment(origin, temp[idx - 1]);
				}
			}
		}
	}
	
	private void resize(int capacity) {
		LineSegment[] temp = new LineSegment[capacity];
		for (int i = 0; i < segments.length; i++) {
			temp[i] = segments[i];
		}
		segments = temp;
	}
	
	public int numberOfSegments()  {      // the number of line segments
		return numOfSegments;
	}
	
	public LineSegment[] segments()    {   // the line segments
		// System.out.println("inside fast!");
		System.out.println(numOfSegments);
		if (numOfSegments == 0) return new LineSegment[0];
		else return Arrays.copyOfRange(segments, 0, numOfSegments);
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
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}
