package pa2_deques_and_randomized_queues;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
	// use double linked list implementation
	private Node head;
	private Node tail;
	
	private class Node {
		private Item item;
		private Node prev;
		private Node next;
		
		private Node(Item item) {
			this.item = item;
			prev = null;
			next = null;
		}
	}

	public Deque() {                          // construct an empty deque
		head = null;
		tail = null;
	}
	
	public boolean isEmpty()  {               // is the deque empty?
		return head == null;
	}
	   
	public int size()    {                    // return the number of items on the deque
		Node cur = head;
		int count = 0;
		while (cur != null) {
			count++;
			cur = cur.next;
		}
		return count;
	}
	   
	public void addFirst(Item item)    {      // add the item to the front
		if (item == null) throw new java.lang.NullPointerException();
		Node temp = new Node(item);
		if (isEmpty()) {
			head = temp;
			tail = temp;
		} else {
			temp.next = head;
			head.prev = temp;
			head = temp;
		}
	}
	   
	public void addLast(Item item)   {        // add the item to the end
		if (item == null) throw new java.lang.NullPointerException();
		Node temp = new Node(item);
		if (isEmpty()) {
			head = temp;
			tail = temp;
		} else {
			temp.prev = tail;
			tail.next = temp;
			tail = temp;
		}
	}
	   
	public Item removeFirst()   {             // remove and return the item from the front
		Node temp = head;
		if (isEmpty()) throw new java.util.NoSuchElementException();
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		return temp.item;
	}
	   
	public Item removeLast()     {            // remove and return the item from the end
		Node temp = tail;
		if (isEmpty()) throw new java.util.NoSuchElementException();
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		return temp.item;
	}
	   
	public Iterator<Item> iterator()    {     // return an iterator over items in order from front to end
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node cur = head;
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		
		public boolean hasNext() {
			return cur != null;
		}
		
		public Item next() {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			} else {
				Node temp = cur;
				cur = cur.next;
				return temp.item;
			}
		}
	}
	   
	public static void main(String[] args) {  // unit testing (optional)
		   
	}
}

