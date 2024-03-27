package assignment3;

public class PQ_SortedSeq {

	Node head = null;

	Node delete() { // removes element with highest priority (lowest p value)
		Node temp = head;
		head = head.next;
		return temp;
	}

	void insert(Node temp) { // inserts elements into queue according to their priority
		Node start = head;
		if (head == null) {
			head = temp;
		} else if (head.priority > temp.priority) {
			temp.next = head;
			head = temp;
		} else {
			while (start.next != null && start.next.priority < temp.priority) {
				start = start.next;
			}
			temp.next = start.next;
			start.next = temp;
		}
	}

//Function to check is list is empty 
	boolean isEmpty() {
		return (head == null);
	}
}