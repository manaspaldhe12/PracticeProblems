import java.util.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class array {

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
		int[] array = new int[] {0,6,4,2, -39,34, -1, -43};
		printArray(largestSum(array));
		//printArray(mergeSort(array));
	}
}