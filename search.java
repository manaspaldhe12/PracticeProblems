import java.util.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class search {

	/*Sorted array:
	Write a method that takes a sorted array A of integers and a key k and
	retums the index of first occurrence of k in A.Return -1 if k does not
	appear in A.*/

	public static int getFirstOccurence (int[] inputArray, int key) {
		int firstIndex = 0;
		int lastIndex = inputArray.length - 1;

		while (firstIndex < lastIndex) {
			int midIndex = (firstIndex + lastIndex) / 2;
			int midElement = inputArray[midIndex];
			if (key == inputArray[firstIndex]) {
				return firstIndex;
			} else if (key == inputArray[midIndex]) {
				return midIndex;
			} else if (key == inputArray[lastIndex]) {
				return lastIndex;
			} else{
				if (key > midElement) {
					firstIndex = midIndex + 1;
				} else {
					lastIndex = midIndex - 1;
				}
			}
		}
		return -1;
	} 

	/*In case of sorted array:
	Design an efficient algorithm that finds the index of first occurence of an 
	element larger than given key k; return -1 if all elements are less than or
	equal to k*/
	public static int findFirstLarger (int[] inputArray, int key) {
		int firstIndex = 0;
		int lastIndex = inputArray.length - 1;
		if (key >= inputArray[lastIndex]) {
			return -1;
		}
		int midIndex = (firstIndex + lastIndex) / 2;

		while (firstIndex != midIndex) {
			if (inputArray[midIndex] > key) {
				lastIndex = midIndex;
			} else {
				firstIndex = midIndex;
			}
			midIndex = (firstIndex + lastIndex) / 2;
		}
		return lastIndex;
	}

	/*Search a sorted array for an i such that A[i] = i
	  Assume A is sorted and has distinct integers
	*/
	public static int searchMatchingIndexValue (int[] inputArray) {
		int startIndex = 0;
		int lastIndex = inputArray.length - 1;
		int midIndex = (startIndex + lastIndex) / 2;

		while (startIndex != midIndex) {
			if (inputArray[midIndex] == midIndex) {
				return midIndex;
			}
			else if (inputArray[midIndex] > midIndex) { 
				lastIndex = midIndex;
			} else {
				startIndex = midIndex;
			}
			midIndex = (startIndex + lastIndex) / 2;
		}
		return -1;
	}

	public static int[] mergeSort (int[] inputArray) {
		return mergeSort (inputArray, 0, inputArray.length - 1);
	}

	public static int[] mergeSort (int[] inputArray, int startIndex, int endIndex) {
		int midIndex = (startIndex + endIndex) / 2;
		if (startIndex == endIndex) {
			int[] returnArray = new int[1];
			returnArray[0] = inputArray[startIndex];
			return returnArray;
		} else {
			int [] leftSorted = mergeSort(inputArray, startIndex, midIndex);
			int [] rightSorted = mergeSort(inputArray, midIndex+1, endIndex);
			return merge(leftSorted, rightSorted); 
		}
	}

	public static int[] merge (int[] left, int[] right) {
		int[] mergedArray = new int[left.length + right.length];
		int mergedCounter = 0;
		int leftCounter = 0;
		int rightCounter = 0;
		while(leftCounter < left.length && rightCounter < right.length) {
			if (left[leftCounter] < right[rightCounter]) {
				mergedArray[mergedCounter] = left[leftCounter];
				leftCounter++;
			} else {
				mergedArray[mergedCounter] = right[rightCounter];
				rightCounter++;
			}
			mergedCounter++;
		}

		while (leftCounter < left.length) {
			mergedArray[mergedCounter] = left[leftCounter];
			mergedCounter++;
			leftCounter++;			
		}

		while (rightCounter < right.length) {
			mergedArray[mergedCounter] = right[rightCounter];
			mergedCounter++;
			rightCounter++;			
		}
		return mergedArray;
	}

	/*Determine whether or not there exist two elements in Set S
	 whose sum is exactly x */
	public static int sumExists (int[] set, int desiredSum) {
		int[] sortedSet = mergeSort(set);
		for (int i = 0; i < set.length; i++) {
			int current = sortedSet[i];
			int found = getFirstOccurence(sortedSet, desiredSum - current);
			if (found != -1) {
				return current;
			}
		}
		return -9999; // not found
	}

	public static int fasterSumExists (int[] set, int desiredSum) {
		 Hashtable numbers = new Hashtable();
		 for (int i = 0; i < set.length; i++) {
		 	numbers.put(set[i], true);
		 	if (numbers.get(desiredSum - set[i]) != null) {
		 		return set[i];
		 	}
		 }
		 return -9999; //not found
	}

	public static void printArray (int[] inputArray) {
		System.out.println("Printing the array: ");
		for (int i = 0; i < inputArray.length; i++) {
			System.out.print(inputArray[i]+ ", ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		//int[] array = new int[] {1,2,3,4,5,5,7,7,8,9,10};
		//int[] array = new int[] {1,2,3,4,5,6,7,8};
		//int[] array = new int[] {-5, -2, 0, 1, 5, 9, 10};
		//int[] array = new int[] {-5, -4, -1, 0, 4, 6, 9};
		int[] array = new int[] {0,6,4,2, 39,34, -1, -43, 2323};
		int key = 8;
		System.out.println(fasterSumExists(array, 73));
		//printArray(mergeSort(array));
	}
}