package assignment3;

import java.util.ArrayList;
import java.util.List;

class PriorityQueueUsingHeap {

	List<Node> heap = new ArrayList<>();

	boolean isEmpty() {
		return heap.isEmpty();
	}

// Adds a patient to the priority queue
	public void insert(Node n) {
		heap.add(n);
		int idx = heap.size() - 1;
		while (idx != 0) {
			int parentIdx = (idx - 1) / 2;
			if (heap.get(parentIdx).priority < heap.get(idx).priority) {
				swap(parentIdx, idx);
				idx = parentIdx;
			} else {
				break;
			}
		}
	}

// Selects the patient with the highest priority to be dequeued
	public Node delete() {
		Node maxNode = heap.get(0);
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);

		int idx = 0;
		while (idx < heap.size()) {
			int leftChildIdx = idx * 2 + 1;
			int rightChildIdx = idx * 2 + 2;
			int largerChildIdx = leftChildIdx;

			if (rightChildIdx < heap.size() && heap.get(rightChildIdx).priority > heap.get(leftChildIdx).priority) {
				largerChildIdx = rightChildIdx;
			}

			if (largerChildIdx < heap.size() && heap.get(largerChildIdx).priority > heap.get(idx).priority) {
				swap(largerChildIdx, idx);
				idx = largerChildIdx;
			} else {
				break;
			}
		}
		return maxNode;
	}

	private void swap(int i, int j) {
		Node temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
}