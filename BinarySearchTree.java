import java.util.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class BinarySearchTree {

	int key;
	BinarySearchTree leftChild;
	BinarySearchTree rightChild;

	BinarySearchTree (int value) {
		key = value;
		leftChild = null;
		rightChild = null;
	}

	public static BinarySearchTree search (BinarySearchTree root, int value) {
		if (root == null || root.key == value){
			return root;
		} else if (root.key > value) {
			return search(root.leftChild, value);
		} else {
			return search(root.rightChild, value);
		}
	}

	public static BinarySearchTree generateBSTFromSortedArray (int[] sortedArray, int start, int end) {
		if (start > end) {
			return null;
		} else if (start == end) {
			return new BinarySearchTree(sortedArray[start]);
		} else {
			int	midpoint = (start + end) / 2;
			BinarySearchTree current = new BinarySearchTree (sortedArray[midpoint]);
			current.leftChild = generateBSTFromSortedArray(sortedArray, start, midpoint - 1);
			current.rightChild= generateBSTFromSortedArray(sortedArray, midpoint + 1, end);
			return current;			
		}
	}

	public static BinarySearchTree generateBSTFromSortedArray (int[] sortedArray) {
		int length = sortedArray.length;
		if (length == 0) {
			return null;
		}
		int	midpoint = length / 2;
		BinarySearchTree current = new BinarySearchTree (sortedArray[midpoint]);
		current.leftChild = generateBSTFromSortedArray(sortedArray, 0, midpoint - 1);
		current.rightChild = generateBSTFromSortedArray(sortedArray, midpoint + 1, length - 1);
		return current;
	}

	public static int[] mergeArrays (int[] first, int[] second) {
		int[] merged = new int[first.length + second.length];
		int i = 0, j = 0;
		for (i = 0; i < first.length; i++) {
			merged[i] = first[i];
		}
		for (j = 0; j < second.length; j++) {
			merged[i] = second[j];
			i++;
		}
		return merged;
	}

	public static void printInorder (BinarySearchTree root) {
		if (root == null) {
			return;
		}
		if (root.leftChild != null) {
			printInorder(root.leftChild);			
		}
		System.out.print(root.key + ", ");
		if (root.rightChild != null) {
			printInorder(root.rightChild);
		}
	}

	public static void preOrder (BinarySearchTree root) {
		if (root == null) {
			return;
		}
		System.out.print(root.key + ", ");
		if (root.leftChild != null) {
			preOrder(root.leftChild);			
		}
		if (root.rightChild != null) {
			preOrder(root.rightChild);
		}
	}

	public static void postOrder (BinarySearchTree root) {
		if (root == null) {
			return;
		}
		if (root.leftChild != null) {
			postOrder(root.leftChild);			
		}
		if (root.rightChild != null) {
			postOrder(root.rightChild);
		}
		System.out.print(root.key + ", ");
	}

	public static void main(String[] args) {
		int[] array1 = {0,1,2,3,4,5,6,7,8,9, 10, 11, 12};
		BinarySearchTree tree1 = generateBSTFromSortedArray(array1);
		printInorder(tree1);
		System.out.println();
		preOrder(tree1);
		System.out.println();
		postOrder(tree1);
		System.out.println();
		// BinarySearchTree tree1 = new BinarySearchTree(5);
		// tree1.leftChild = new BinarySearchTree (3);
		// tree1.rightChild = new BinarySearchTree (7);

		// tree1.leftChild.leftChild = new BinarySearchTree (1);
		// tree1.leftChild.rightChild = new BinarySearchTree (4);
		// tree1.rightChild.leftChild = new BinarySearchTree (6);
		// tree1.rightChild.rightChild = new BinarySearchTree (9);

		// System.out.println("output: " + search(tree1, 9).key);
	}
}