package assignment3;

import java.time.*;

public class Node {
	int patient_id;
	String surname;
	int age;
	char gender; // M or F or Unidentified
	String financial_class;
	String condition;
	LocalTime entryTime;

	int priority;
	Node next;

	public Node(int patient_id, String surname, int age, char gender, String financial_class, String condition,
			LocalTime entryTime) {
		this.patient_id = patient_id;
		this.surname = surname;
		this.age = age;
		this.gender = gender;
		this.financial_class = financial_class;
		this.condition = condition;
		this.entryTime = entryTime;

		if (condition.equals("Preventative Care Measures")) {  //assigning priority based on the seriousness of the condition
			this.priority = 3;
		} else if (condition.equals("Emergency Department (ED) Measures")) {
			this.priority = 0;
		} else if (condition.equals("Acute Myocardial Infarction (AMI)")) {
			this.priority = 1;
		} else if (condition.equals("Blood clot prevention and treatment")) {
			this.priority = 2;
		}

		this.next = null;
	}

	public Node(Node original) {
		this.patient_id = original.patient_id;
		this.surname = original.surname;
		this.age = original.age;
		this.gender = original.gender;
		this.financial_class = original.financial_class;
		this.condition = original.condition;
		this.entryTime = original.entryTime;
		this.priority = original.priority;
		this.next = null;
	}

	public Node deepCopy() {
		return new Node(this);
	}
}
