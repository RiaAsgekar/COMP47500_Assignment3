package assignment3;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		PriorityQueueUsingHeap pq = new PriorityQueueUsingHeap();
		PQ_SortedSeq sortedPQ = new PQ_SortedSeq();
		PQ_UnsortedSeq unsortedPQ = new PQ_UnsortedSeq();

		// Read from the CSV file
		String csvFile = "D:\\College\\Advanced Data Structures in Java\\assignment3\\src\\assignment3\\mock_up_patient_data.csv";
		String line;
		String cvsSplitBy = ",";

		ArrayList<Node> nodeList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			br.readLine(); // Skip the header
			while ((line = br.readLine()) != null) {
				// Split the line by commas
				String[] data = line.split(cvsSplitBy);

				// Extract data from CSV
				int patient_id = Integer.parseInt(data[0]);
				String surname = data[1];
				int age = Integer.parseInt(data[2]);
				char gender = data[3].charAt(0); // Assuming gender is a single character
				String financial_class = data[4];
				String condition = data[5];
				String entryTimeStr = data[6].toUpperCase().strip();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a", Locale.ENGLISH);
				LocalTime entryTime = LocalTime.parse(entryTimeStr, formatter);

				// Create a Node object
				Node node = new Node(patient_id, surname, age, gender, financial_class, condition, entryTime);

				// Add the node to the list
				nodeList.add(node);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return; // Terminate the program if an exception occurs while reading the file
		} catch (NumberFormatException e) {
			System.err.println("NumberFormatException: " + e.getMessage());
			return; // Terminate the program if an exception occurs while parsing integers
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
			return; // Terminate the program if an exception occurs due to insufficient data in CSV
		}

		// Sort the nodeList based on entryTime
		Collections.sort(nodeList, Comparator.comparing(node -> node.entryTime));

		long pqTime = 0;
		long sortedPQTime = 0;
		long unsortedPQTime = 0;

		// Perform insertions and measure time
		for (Node node : nodeList) {
			Node n2 = node.deepCopy();
			Node n3 = node.deepCopy();

			try {
				long startTime = System.nanoTime();
				pq.insert(node);
				pqTime += System.nanoTime() - startTime;

				startTime = System.nanoTime();
				sortedPQ.insert(n2);
				sortedPQTime += System.nanoTime() - startTime;

				startTime = System.nanoTime();
				unsortedPQ.insert(n3);
				unsortedPQTime += System.nanoTime() - startTime;
			} catch (Exception e) {
				e.printStackTrace();
				return; // Terminate the program if an exception occurs during insertion
			}
		}

		// Calculate average insertion time
		double avgPQTime = pqTime / (double) nodeList.size();
		double avgSortedPQTime = sortedPQTime / (double) nodeList.size();
		double avgUnsortedPQTime = unsortedPQTime / (double) nodeList.size();

		// Output average insertion time for each priority queue
		System.out.println("Average insertion time for priority queue using heap: " + avgPQTime + " nanoseconds");
		System.out.println(
				"Average insertion time for sorted sequence priority queue: " + avgSortedPQTime + " nanoseconds");
		System.out.println(
				"Average insertion time for unsorted sequence priority queue: " + avgUnsortedPQTime + " nanoseconds");

		// Finding and printing average deletion times:
		pqTime = 0;
		sortedPQTime = 0;
		unsortedPQTime = 0;

		try {
			while (!pq.isEmpty()) {
				long startTime = System.nanoTime();
				pq.delete();
				pqTime += System.nanoTime() - startTime;
			}
			avgPQTime = pqTime / (double) nodeList.size();
			System.out.println("Average deletion time for priority queue using heap: " + avgPQTime + " nanoseconds");

			while (!sortedPQ.isEmpty()) {
				long startTime = System.nanoTime();
				sortedPQ.delete();
				sortedPQTime += System.nanoTime() - startTime;
			}
			avgSortedPQTime = sortedPQTime / (double) nodeList.size();
			System.out.println(
					"Average deletion time for sorted sequence priority queue: " + avgSortedPQTime + " nanoseconds");

			while (!unsortedPQ.isEmpty()) {
				long startTime = System.nanoTime();
				unsortedPQ.delete();
				unsortedPQTime += System.nanoTime() - startTime;
			}
			avgUnsortedPQTime = unsortedPQTime / (double) nodeList.size();
			System.out.println("Average deletion time for unsorted sequence priority queue: " + avgUnsortedPQTime
					+ " nanoseconds");
		} catch (Exception e) {
			e.printStackTrace();
			return; // Terminate the program if an exception occurs during deletion
		}
	}
}