/*
Given an integer array A and a sliding window of size K, 
find the maximum value of each window as it slides from left to right.

Assumptions

The given array is not null and is not empty

K >= 1, K <= A.length

Examples

A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},

and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
 */

package heap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxValuesOfSizeKSlidingWindow {
	public List<Integer> maxWindows(int[] array, int k) {
		// return heapWindows(array, k);
		return dequeWindows(array, k);
	}
	
	// method 2: using a deque
	private List<Integer> dequeWindows(int[] array, int k) {
		List<Integer> result = new ArrayList<>();
		// use a deque as the sliding window
		Deque<Cell> deque = new ArrayDeque<>();
		
		for (int i = 0; i < array.length; i++) {
			// need to maintain descending order in the deque from first to last
			// when new element comes in, if it is larger and newer than the right most element "r" in the deque
			// then r cannot be the solution whatsoever, so just delete r
			while (!deque.isEmpty() && deque.peekLast().value < array[i]) {
				deque.pollLast();
			}
			deque.offerLast(new Cell(i, array[i]));
			
			// the left most element in the deque is the final result
			if (i >= k - 1) {
				// determine the left most boundary of the sliding window
				int left = i - k + 1;
				// remove elements that are out of left most boundary
				// this is not lazy deletion, thus at most one deletion can happen in each iteration
				if (deque.peekFirst().index < left) {
					deque.pollFirst();
				}
				
				result.add(deque.peekFirst().value);
			}
		}
		
		return result;
	}
	
	// method 1: using a k-sized heap
	private List<Integer> heapWindows(int[] array, int k) {
		List<Integer> result = new ArrayList<>();
		// use a max heap as the sliding window
		Queue<Cell> heap = new PriorityQueue<>(k);
		
		for (int i = 0; i < array.length; i++) {
			heap.offer(new Cell(i, array[i]));
			if (i >= k - 1) {
				// determine the left bound of the sliding window
				int left = i - k + 1;
				// lazy deletion
				while (heap.peek().index < left) {
					heap.poll();
				}				
				result.add(heap.peek().value);
			}
		}
		
		return result;
	}
	
	private class Cell implements Comparable<Cell>{
		private int index;
		private int value;
		
		private Cell(int index, int value) {
			this.index = index;
			this.value = value;
		}
		
		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			if (this.value == o.value) return 0;
			// a max heap
			return this.value > o.value ? -1 : 1;
		}
	}
	
	public static void main(String[] args) {
		MaxValuesOfSizeKSlidingWindow s = new MaxValuesOfSizeKSlidingWindow();
		int[] test = new int[]{1, 2, 3, 2, 4, 2, 1};
		System.out.println(s.maxWindows(test, 3));
	}
}
