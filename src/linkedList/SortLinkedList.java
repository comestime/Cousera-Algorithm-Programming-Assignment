/*
 * Sort linked list using quick sort, merge sort and selection sort
 * The sorted list will be in ascending order from head to tail

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
 */

package linkedList;

public class SortLinkedList {
	public ListNode sortList(ListNode head) {
		// return quickSortList(head);
		// return mergeSortList(head);
		return selectionSortList(head);
	}
	
    public ListNode quickSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        // step 1, select a pivot
        ListNode pivot = head;
        head = head.next;
        pivot.next = null;
        
        // step 2, partition list, all nodes with values less than pivot goes to subList1
        // all nodes with values larger than pivot goes to subList2
        // all nodes with values equal to pivot goes with pivot
        ListNode sub1 = null;
        ListNode sub2 = null;
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            if (cur.val < pivot.val) {
                cur.next = sub1;
                sub1 = cur;
            } else if (cur.val > pivot.val) {
                cur.next = sub2;
                sub2 = cur;
            } else {
                cur.next = pivot;
                pivot = cur;
            }
        }
        
        // step 3, recusively sort two sub linked lists
        sub1 = quickSortList(sub1);
        sub2 = quickSortList(sub2);
        
        // step 4.1 find the tail of pivot list
        ListNode pivotTail = pivot;
        while (pivotTail.next != null) {
            pivotTail = pivotTail.next;
        }
        // step 4.2 merge two sub lists
        if (sub1 == null) {
            pivotTail.next = sub2;
            return pivot;
        } else {
            ListNode sub1Tail = sub1;
            while (sub1Tail.next != null) {
                sub1Tail = sub1Tail.next;
            }
            sub1Tail.next = pivot;
            sub1Tail = pivotTail;
            sub1Tail.next = sub2;
            return sub1;
        }
    }
    
    public ListNode mergeSortList(ListNode head) {
    	if (head == null || head.next == null) return head;
    	
    	// step 1: find the middle point of the linked list
    	ListNode slow = head;
    	ListNode fast = head;
    	while (fast.next != null && fast.next.next != null) {
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	ListNode mid = slow.next;
    	// decouple the first half of the list from the second half
    	slow.next = null;
    	
    	// step 2: recursively invoke the function to sort the two halves
    	ListNode first = mergeSortList(head);
    	ListNode second = mergeSortList(mid);
    	
    	// step 3: merge the two sorted list
    	// use a dummy head
    	ListNode newHead = new ListNode(0);
    	ListNode tail = newHead;
    	// step 3.1
    	while (first != null && second != null) {
    		ListNode cur;
    		if (first.val < second.val) {
    			cur = first;
    			first = first.next;
    		} else {
    			cur = second;
    			second = second.next;
    		}
			tail.next = cur;
			tail = cur;
    	}
    	// step 3.2: if either of the two halves has nodes left
    	if (first != null) {
    		tail.next = first;
    	}
    	if (second != null) {
    		tail.next = second;
    	}
    		
    	return newHead.next;
    }
    
    public ListNode selectionSortList(ListNode head) {
    	if (head == null || head.next == null) return head;
    	
    	// for each iteration, we find the node with the largest value in the list
    	// insert the node to the head of the new head
    	ListNode dummyHead = new ListNode(0);
    	dummyHead.next = head;
    	
    	ListNode newHead = null;
    	// keep iterating if the old list has nodes remaining
    	while (dummyHead.next != null) {
    		// prev and cur are two pointers to scan through the linked list
        	ListNode prev = dummyHead;
        	ListNode cur = dummyHead.next;
        	// node2del and prevNode2del are two pointers to index the current global max
        	ListNode node2del = cur;
        	ListNode prevNode2del = prev;
        	// step 1: find the global max
        	while (cur != null) {
        		if (cur.val > node2del.val) {
        			node2del = cur;
        			prevNode2del = prev;
        		}
        		// keep updating the pointers to scan through the linked list
        		prev = cur;
        		cur = cur.next;
        	}
        	// step 2: delete the global max from the old list, and insert it to the new list
        	prevNode2del.next = node2del.next;
        	node2del.next = newHead;
        	newHead = node2del;
    	}
    	return newHead;
    }
    
    public static void main(String[] args) {
    	SortLinkedList solution = new SortLinkedList();
    	ListNode head = new ListNode(2);
    	head.next = new ListNode(1);
    	head.next.next = new ListNode(0);
    	head = solution.sortList(head);
    	ListNode.print(head);
    }
}
