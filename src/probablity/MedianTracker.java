/*
Given an unlimited flow of numbers, keep track of the median of all elements seen so far.

You will have to implement the following two methods for the class

read(int value) - read one value from the flow
median() - return the median at any time, return null if there is no value read so far
Examples

read(1), median is 1
read(2), median is 1.5
read(3), median is 2
read(10), median is 2.5
......
 */

package probablity;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianTracker {
	
	// use max heap to store smaller half
	private PriorityQueue<Integer> smallerHalf;
	// use min heap to store larger half
	private PriorityQueue<Integer> largerHalf;
	
	public MedianTracker() {
		// max heap
		smallerHalf = new PriorityQueue<>(11, Collections.reverseOrder());
		// min heap
		largerHalf = new PriorityQueue<>();
	}
	
	public void read(int value) {
		// maintain the property where 0 <= smallerHalf.size() - largerHalf.size() <= 1;
		if (size() == 0 || value <= smallerHalf.peek()) {
			smallerHalf.offer(value);
		} else {
			largerHalf.offer(value);
		}
		
		if (smallerHalf.size() - largerHalf.size() >= 2) {
			largerHalf.offer(smallerHalf.poll());
		} else if (smallerHalf.size() < largerHalf.size()) {
			smallerHalf.offer(largerHalf.poll());
		}
	}
	
	public Double median() {
		if (size() == 0) {
			return null;
		} else if (size() % 2 == 0) {
			return (smallerHalf.peek() + largerHalf.peek()) / 2.0;
		} else {
			return (double)(smallerHalf.peek());
		}
	}

	public int size() {
		return smallerHalf.size() + largerHalf.size();
	}
}
