/*
Reverse pairs of elements in a singly-linked list.

Examples

L = null, after reverse is null
L = 1 -> null, after reverse is 1 -> null
L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
 */

package linkedList;

public class ReverseInPairs {
	public ListNode reversePair(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode prev = head;
		ListNode cur = prev.next;
		ListNode next = cur.next;
		// perform reverse in pairs
		next = reversePair(next);
		cur.next = prev;
		prev.next = next;
		return cur;
	}
}
