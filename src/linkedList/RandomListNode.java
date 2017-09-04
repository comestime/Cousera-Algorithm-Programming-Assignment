/*
 * Each of the nodes in the linked list has another pointer pointing to a random node in the list or null.
 */

package linkedList;

public class RandomListNode extends ListNode {
	public RandomListNode random;
	public RandomListNode next;
	public int value; 
	
	public RandomListNode(int value) {
		super(value);
		// TODO Auto-generated constructor stub
		this.value = value;
	}

}
