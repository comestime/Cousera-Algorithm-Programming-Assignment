/*
Merge K sorted lists into one big sorted list in ascending order.

Assumptions

ListOfLists is not null, and none of the lists is null.
Each list is sorted in ascending order
 */

package heap;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import linkedList.ListNode;

public class MergeKSortedLists {
	public ListNode merge(List<ListNode> listOfLists) {
		if (listOfLists == null) return null;
		ListNode dummyHead = new ListNode(1);
		ListNode tail = dummyHead;
		Queue<ListNode> queue = new PriorityQueue<>(11, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode one, ListNode two) {
				// TODO Auto-generated method stub
				if (one.val == two.val) return 0;
				return one.val < two.val ? -1 : 1;
			}
		});
		
		for (ListNode node : listOfLists) {
			if (node != null) {
				queue.offer(node);
			}
		}
		
		while (!queue.isEmpty()) {
			ListNode cur = queue.poll();
			if (cur.next != null) {
				queue.offer(cur.next);
			}
			cur.next = null;
			tail.next = cur;
			tail = cur;
		}
		
		return dummyHead.next;
	}
}
