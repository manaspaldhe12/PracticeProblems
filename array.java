import java.util.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

class Stack {
	int maxSize = 10; //stack of size 10;
	Object[] stack = new Object [maxSize]; 
	int size = 0;

	void insert (Object newElement) {
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

	Object pop () {
		if (size <= 0) {
			return -1; //error; Should ideally throw exception
		}
		size--;
		return stack[size];
	}

	Object top () {
		if (size <= 0) {
			return -1; //error; Should ideally throw exception
		}
		return stack[size - 1];		
	}
}

class StackOfStacks  {
	int maxSize = 10; //choosing 100 as the size
	Stack[] stack = new Stack [maxSize]; 
	int size = 0;

	void push (Object newElement) {
		if (size == 0 || stack[size - 1].isFull()) {
			if (size == maxSize) {
				System.out.println("error"); // error; should ideally throw exception;
			}
			stack[size] = new Stack();
			size++;
		}
		stack[size-1].insert(newElement);
	}

	boolean isFull () {
		return (size == maxSize);		
	}

	boolean isEmpty () {
		return (size == 0);
	}

	Object top () {
		if (size <= 0) {
			return -1; //error; Should ideally throw exception
		} 
		return stack[size - 1].top();
	}

	Object pop () {
		if (size <= 0) {
			return -1; //error; Should ideally throw exception
		} 
		Object returnElem = stack[size - 1].pop();
		if (stack[size - 1].isEmpty()) {
			size--;
		}
		return returnElem;
	}
}

public class array {

	/* Implement a stack of stacks:
	Imagine a (literal) stack of plates. If the stack gets too high, it might topple. There-
	fore, in real life, we would likely start a new stack when the previous stack exceeds
	some threshold. Implement a data structure SetOfStacks that mimics this. SetOf-
	Stacks should be composed of several stacks, and should create a new stack once
	the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should
	behave identically to a single stack (that is, pop() should return the same values as it
	would if there were just a single stack). */
	public static void testStackOfStacks () {		
		StackOfStacks stacks = new StackOfStacks();
		for (int i = 0; i < 100; i++) {
			stacks.push(i);
			System.out.println("Stacks of stack has size: " + stacks.size);
		}
		for (int i = 0; i < 100; i++) {
			Object val = stacks.pop();
			System.out.println("Stacks of stack returned value: " + val);
			System.out.println("Stacks of stack has size: " + stacks.size);
		}
	}

	



	/*Write a method to shuffle a deck of cards. It must be a perfect shuffle - in other words,
	each 52! permutations of the deck has to be equally likely. Assume that you are given
	a random number generator which is perfect.*/

	public static int[] randomShuffle (int[] inputArray) {
		//select the first number randomly, then shuffle rest
 		Random randomGenerator = new Random();
 		int remainingSize = inputArray.length;
 		for (int i = 0; i <  inputArray.length; i++) {
	 		int randomInt = randomGenerator.nextInt(remainingSize);
 			int temp = inputArray[randomInt];
 			inputArray[randomInt] = inputArray[remainingSize-1];
 			inputArray[remainingSize-1] = temp;
 			remainingSize--;
 		}
 		return inputArray;
	}

	/*Write a method to randomly generate a set of m integers from an array of size n. Each
	element must have equal probability of being chosen.*/
	public static int[] selectRandom (int m, int[] set) {
		if (m > set.length) {
			int[] error = new int[1];
			error[0] = -1;
			return error; // error case! Should ideally throw exception
		}
		int [] randomSet = new int[m];
		set = randomShuffle(set);
		for (int i = 0; i < m; i++) {
			randomSet[i] = set[i];
		}
		return randomSet;
	}

	/*You are given an array of integers (both positive and negative). Find the continuous
	sequence with the largest sum. Return the sub-array.*/	
	public static int[] largestSum (int[] inputArray) {
		int start = 0, previousStart = 0;
		int previousEnd = 0;
		int currentSum = 0, previousSum = 0;

		for (int end = 0; end < inputArray.length; end++) {
			currentSum = currentSum + inputArray[end];
			if (currentSum <= 0) {
				start = end + 1;
				currentSum = 0;
			} else if (currentSum > previousSum) {
				previousStart = start;
				previousEnd = end;
				previousSum = currentSum;
			}
		}

		int[] returnArray = new int[3]; //just returning indices and sum;
		returnArray[0] = previousStart;
		returnArray[1] = previousEnd;
		returnArray[2] = previousSum;
		return returnArray;
	}

	public static void printArray (int[] inputArray) {
		System.out.println("printing array: ");
		for (int i = 0; i < inputArray.length; i++) {
			System.out.print(inputArray[i] + ", ");
		}
	}

	public static void main(String[] args) {
		//int[] array = new int[] {1,2,3,4,5,5,7,7,8,9,10};
		//int[] array = new int[] {1,2,3,4,5,6,7,8};
		//int[] array = new int[] {-5, -2, 0, 1, 5, 9, 10};
		//int[] array = new int[] {-5, -4, -1, 0, 4, 6, 9};
		//int[] array = new int[] {0,6,4,2, -39,34, -1, -43};
		//printArray(largestSum(array));
		//printArray(mergeSort(array));
		testStackOfStacks();
	}
}