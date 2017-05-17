package pa2_deques_and_randomized_queues;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	private static RandomizedQueue<String> queue;
	
	public static void main(String [] args) {
		// # of items to retrieve
		int k = Integer.parseInt(args[0]);
		queue = new RandomizedQueue<String>();
		
		// System.out.println("START!");

		while (!StdIn.isEmpty()) {
			String w = StdIn.readString();
			queue.enqueue(w);
			// System.out.println(w);
		}
		// System.out.println("DONE!");
		
		for (int i = 0; i < k; i++) System.out.println(queue.dequeue());
	}
}
