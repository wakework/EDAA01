package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	private int size; // Används också i BSTVisaulizer
	private Comparator<E> comparator;
	private ArrayList<E> list;

	public static void main(String[] args) {
		BSTVisualizer pic = new BSTVisualizer("IntegerTräd", 200, 150);
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(30);
		tree.add(10);
		tree.add(20);
		tree.add(40);
		tree.add(50);
		tree.add(60);
		tree.add(70);
		tree.add(80);
//		pic.drawTree(tree);
//		tree.printTree();

		tree.rebuild();
		pic.drawTree(tree);
	}

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		comparator = (e1, e2) -> e1.compareTo(e2);
		list = new ArrayList<E>();
		size = 0;
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		this.comparator = comparator;
		list = new ArrayList<E>();
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			list.add(x);
			size = list.size();
			return true;
		}

		return add(root, x);
	}

	// x.compareTo(node.element) == 0, x.compareTo(node.element) < 0
	
	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param BinaryNode node is the root of the tree and x element to be inserted
	 * @return true if the the element was inserted correctly
	 */
	private boolean add(BinaryNode<E> node, E x) {
		if (comparator.compare(x, node.element) == 0) {
			return false;
		} else if (comparator.compare(x, node.element) < 0) {
			if (node.left != null) {
				return add(node.left, x);
			} else {
				node.left = new BinaryNode<E>(x);
				list.add(x);
				size = list.size();
				return true;
			}
		} else {
			if (node.right != null) {
				return add(node.right, x);
			} else {
				node.right = new BinaryNode<E>(x);
				list.add(x);
				size = list.size();
				return true;
			}
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		if (root == null) {
			return 0;
		} else if (root.left == null && root.right == null) {
			return 0;
		}

		return height(root);
	}

	/**
	 * Computes the height of tree from the specified node n.
	 * 
	 * @param BinaryNode n the root of the tree
	 * @return the height of the tree
	 */
	private int height(BinaryNode<E> n) {
		if (n.left == null && n.right == null) {
			return 1;
		} else if (n.right == null) {
			return 1 + height(n.left);
		} else {
			return 1 + height(n.right);
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		list.clear();
		size = list.size();
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	/**
	 * Print tree contents in inorder from node n.
	 * @param node is the root of the tree.
	 */
	private void printTree(BinaryNode<E> node) {
		if (node != null) {
			printTree(node.left);
			System.out.println(node.element);
			printTree(node.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		list.clear();
		toArray(root, list);
//		System.out.println(list.toString());
		root = buildTree(list, 0, size - 1);

	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, list);
			list.add(n.element);
			toArray(n.right, list);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) {
			return null;
		}
		int mid = first + ((last - first) / 2);
		BinaryNode<E> root2 = new BinaryNode<E>(sorted.get(mid));
		root2.left = buildTree(sorted, first, mid - 1);
		root2.right = buildTree(sorted, mid + 1, last);
		return root2;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}
}
