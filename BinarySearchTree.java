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


	public static void main(String[] args) {
		BinarySearchTree tree1 = new BinarySearchTree(5);
		tree1.leftChild = new BinarySearchTree (3);
		tree1.rightChild = new BinarySearchTree (7);

		tree1.leftChild.leftChild = new BinarySearchTree (1);
		tree1.leftChild.rightChild = new BinarySearchTree (4);
		tree1.rightChild.leftChild = new BinarySearchTree (6);
		tree1.rightChild.rightChild = new BinarySearchTree (9);

		System.out.println("output: " + search(tree1, 9).key);
	}
}