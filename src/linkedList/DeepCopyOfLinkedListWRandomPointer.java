/*
 * Each of the nodes in the linked list has another pointer pointing to a random node in the list or null.
 * Make a deep copy of the original list.
 */

package linkedList;

import java.util.HashMap;
import java.util.Map;

public class DeepCopyOfLinkedListWRandomPointer {
	public RandomListNode copy(RandomListNode head) {
		if (head == null) return null;
		
		RandomListNode newHead = new RandomListNode(head.value);
		RandomListNode cur = newHead;
		// a look up map to avoid duplication. key = original node, value = copied node
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		map.put(head, newHead);
		
		while (head != null) {
			RandomListNode next = map.get(head.next);
			RandomListNode random = map.get(head.random);
			
			// has been copied over previously
			if (next != null) {
				cur.next = next;
			} else if (head.next != null) {	    // copy over, and make sure no NullPointerException
				next = new RandomListNode(head.next.value);
				map.put(head.next, next);
				cur.next = next;
			}
			
			// has been copied over previously
			if (random != null) {
				cur.random = random;
			} else if (head.random != null) {	// copy over, and make sure no NullPointerException
				random = new RandomListNode(head.random.value);
				map.put(head.random, random);
				cur.random = random;
			}
			
			head = head.next;
			cur = cur.next;
		}
		
		return newHead;
	}
}
