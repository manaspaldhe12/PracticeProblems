import java.util.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

class LinkedList {
	int data;
	LinkedList next;

	LinkedList (int value ) {
		data = value;
		next = null;
	}

	LinkedList () {
	}


	LinkedList insertAtHead (int value) {
		LinkedList current = new LinkedList(value);
		current.next = this;
		return current;
	}

	LinkedList insertAtTail (int value) {
		LinkedList current = new LinkedList(value);
		LinkedList head = this;
		if (head == null) {
			return current;
		}
		while (head.next != null) {
			head = head.next;
		}
		head.next = current;
		return this;
	}

	void printList () {
		System.out.println("printing list:  ");
		LinkedList head = this;
		while (head != null) {
			System.out.print(head.data + ", ");
			head = head.next;
		}		
		System.out.println();
	}

}

class Queue {
	LinkedList head = null;
	LinkedList tail = head;

	Queue (int value) {
		head = new LinkedList(value);
		tail = head;
	}

	void enqueue (int data) {
		if (head == null) {
			head = new LinkedList(data);
			tail = head;
		}
		else {
			tail.next = new LinkedList(data);
			tail = tail.next;		
		}
	}

	boolean isEmpty () {
		return (head == null);
	}

	int dequeue () {
		if (head == null) {
			return 0; // should ideally throw exception
		}
		int temp = head.data;
		head = head.next;
		return temp;
	}

	int front () {
		if (head == null) {
			return 0; // should ideally throw exception
		}
		return head.data;		
	}

	void printQueue () {
		System.out.println("Printing queue: ");
		head.printList();
	}

}

class Stack {
	int maxSize = 10; //stack of size 10;
	int[] stack = new int [maxSize]; 
	int size = 0;

	void insert (int newElement) {
		if (size == maxSize) {
			System.out.println("error"); // error; should ideally throw exception;
		}
		stack[size] = newElement;
		size++;
	}

	boolean isFull () {
		return (size == maxSize);		
	}

	boolean isEmpty () {
		return (size == 0);
	}

	int pop () {
		if (size <= 0) {
			return -1; //error; Should ideally throw exception
		}
		size--;
		return stack[size];
	}

	int top () {
		if (size <= 0) {
			return -1; //error; Should ideally throw exception
		}
		return stack[size - 1];		
	}
}

public class graph {
	int numberOfNodes;
	LinkedList[] edges;
	int[] colors;

	graph (int n) {
		numberOfNodes = n;
		edges = new LinkedList[numberOfNodes];
		colors = new int[numberOfNodes];
		for (int i = 0; i < numberOfNodes; i++) {
			edges[i] = new LinkedList(i); // adding self node
			colors[i] = 0; //not visited: used for graph traversal
		}
	}

	public static void breadFirstSearch (graph g, int startNode) {
		Queue q = new Queue(startNode);
		g.colors[startNode] = 1; //visited
		System.out.println("At Node: " + startNode);
		while (!q.isEmpty()) {
			int thisNode = q.dequeue();
			LinkedList edgesForNode = g.edges[thisNode];
			System.out.println("At Node: " + thisNode);
			while (edgesForNode != null) {
				System.out.println("Checking Node: " + edgesForNode.data);
				if (g.colors[edgesForNode.data] == 0) { // Not visited
					q.enqueue(edgesForNode.data);
					g.colors[edgesForNode.data] = 1; //visited
					System.out.println("enqueue: " + edgesForNode.data);
				}
				edgesForNode = edgesForNode.next;
			}
		}

	}

	public static void print (graph g) {
		for (int i = 0; i < g.numberOfNodes; i++) {
			System.out.println("node: " + i);
			g.edges[i].printList();
		}
	}

	public static void main(String[] args) {
		graph testGraph = new graph (5);
		testGraph.edges[0] = testGraph.edges[0].insertAtHead(1);
		testGraph.edges[0] = testGraph.edges[0].insertAtHead(2);
		testGraph.edges[0] = testGraph.edges[0].insertAtHead(4);

		testGraph.edges[1] = testGraph.edges[1].insertAtHead(2);
		testGraph.edges[1] = testGraph.edges[1].insertAtHead(4);
		testGraph.edges[1] = testGraph.edges[1].insertAtHead(0);

		testGraph.edges[2] = testGraph.edges[2].insertAtHead(2);
		testGraph.edges[2] = testGraph.edges[2].insertAtHead(0);

		testGraph.edges[3] = testGraph.edges[3].insertAtHead(4);
		testGraph.edges[3] = testGraph.edges[3].insertAtHead(0);

		testGraph.edges[4] = testGraph.edges[4].insertAtHead(3);
		testGraph.edges[4] = testGraph.edges[4].insertAtHead(2);
		testGraph.edges[4] = testGraph.edges[4].insertAtHead(1);
		testGraph.edges[4] = testGraph.edges[4].insertAtHead(0);

		print(testGraph);
		breadFirstSearch(testGraph, 0);

		// Queue q = new Queue(0);
		// q.enqueue(1);
		// q.printQueue();
		// q.enqueue(2);
		// q.printQueue();
		// q.enqueue(3);
		// q.printQueue();
		// q.enqueue(4);
		// q.enqueue(5);
		// q.printQueue();
		// q.dequeue();
		// q.printQueue();
		// q.dequeue();
		// q.printQueue();


		// LinkedList test = new LinkedList(0);
		// test = test.insertAtHead(-1);
		// test = test.insertAtHead(-2);
		// test = test.insertAtHead(-3);
		// test = test.insertAtTail(1);
		// test = test.insertAtTail(2);
		// test = test.insertAtTail(3);
		// test = test.insertAtHead(-4);
		// test = test.insertAtTail(4);
		// test.printList();
	}
}