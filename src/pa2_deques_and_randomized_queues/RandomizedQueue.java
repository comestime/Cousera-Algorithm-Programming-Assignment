package pa2_deques_and_randomized_queues;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	// using resizing array based stack implementation
	// simpler compared to queue implementation
	private int idx;
	private Item [] q;
	
	public RandomizedQueue() {                // construct an empty randomized queue
		q = (Item []) new Object[2];
		idx = 0;
	}
	
	public boolean isEmpty() {                // is the queue empty?
		return idx == 0;
	}
	
	public int size()  {                      // return the number of items on the queue
		return idx;
	}
	
	private void resize(int capacity) {
		assert capacity >= idx;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < idx; i++) {
			temp[i] = q[i];
		}
		q = temp;
	}
	
	public void enqueue(Item item)  {         // add the item
		if (item == null) throw new java.lang.NullPointerException();
		// double idx of array if necessary
		if (idx == q.length) resize(2 * q.length);
		q[idx++] = item;
	}
	
	public Item dequeue()  {                  // remove and return a random item
		if (isEmpty()) throw new java.util.NoSuchElementException();
		// dequeue item is randomized; move the last item in the array to occupy the removed item's location
		int tempIdx = StdRandom.uniform(idx);
		Item temp = q[tempIdx];
		q[tempIdx] = q[--idx];
		if (idx > 0 && idx == q.length/4) resize(q.length/2);
		return temp;
	}
	
	public Item sample()    {                 // return (but do not remove) a random item
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int tempIdx = StdRandom.uniform(idx);
		return q[tempIdx];
	}
	
	public Iterator<Item> iterator() {        // return an independent iterator over items in random order
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		
		private int cur = idx;
		
		public boolean hasNext() {
			return cur > 0;
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			return q[--cur];
 		}
	}
	
	public static void main(String[] args) {  // unit testing (optional)
		   
	}
}

