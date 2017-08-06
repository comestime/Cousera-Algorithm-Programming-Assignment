/*
 * reverse a linked list
 */

package linkedList;

public class ReverseLinkedList {
	// method 1: reverse linked list recursively
	public ListNode reverseRecursive(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode nextNode = head.next;
		ListNode newHead = reverseRecursive(nextNode);
		nextNode.next = head;
		// decouple the current node from previous one
		head.next = null;
		return newHead;
	}
	
	// method2: reverse linked list iteratively
	public ListNode reverseIterative(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode newHead = null;
		while (head != null) {
			ListNode cur = head;
			head = head.next;
			cur.next = newHead;
			newHead = cur;
		}
		return newHead;
	}
}
