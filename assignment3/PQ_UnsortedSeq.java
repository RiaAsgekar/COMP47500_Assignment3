package assignment3;

public class PQ_UnsortedSeq {

	Node head = null;

	Node delete() { // removes element with lowest priority (highest p value)
		if (isEmpty()) {
			return null; // return a default value if queue is empty
		}
		Node prev = null;
		Node current = head;
		Node minPrev = null;
		Node minCurrent = head;

		// Find the node with the lowest priority
		while (current != null) {
			if (current.priority < minCurrent.priority) {
				minPrev = prev;
				minCurrent = current;
			}
			prev = current;
			current = current.next;
		}

		// If the lowest priority node is the head
		if (minPrev == null) {
			head = head.next;
		} else {
			minPrev.next = minCurrent.next;
		}

		return minCurrent;
	}

	void insert(Node newNode) { // inserts elements into the end of the queue
		if (head == null) {
			head = newNode;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
	}

	// Function to check if list is empty
	boolean isEmpty() {
		return head == null;
	}
}
