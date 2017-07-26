package linkedList;

public class SortLinkedList {
    public ListNode sortList(ListNode head) {
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
        sub1 = sortList(sub1);
        sub2 = sortList(sub2);
        
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
    
    public static void main(String[] args) {
    	SortLinkedList solution = new SortLinkedList();
    	ListNode head = new ListNode(2);
    	head.next = new ListNode(1);
    	head = solution.sortList(head);
    	ListNode.print(head);
    }
}
