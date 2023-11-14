package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import java.util.Comparator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBinarySearchTree {
	private BinarySearchTree<Integer> tree;
	private BinarySearchTree<String> tree2;
//	private Comparator<String> comp;
	
	@BeforeEach
	void setUp() {
		tree = new BinarySearchTree<Integer>();
		tree2 = new BinarySearchTree<String>((e1, e2) -> e1.compareTo(e2));
	}

	@AfterEach
	void tearDown() {
		tree = null;
		tree2 = null;
	}

	@Test
	void testHeightEmptyTree() {
		assertEquals("Wrong height of empty tree", 0, tree.height());
		assertEquals("Wrong size of empty tree", 0, tree.size());
	}
	
	@Test
	void testIntegerTree() {
		tree.add(10);
		assertEquals("Wrong size of tree", 1, tree.size());
		assertEquals("Wrong height of tree", 0, tree.height());
		assertFalse("add returns wrong value", tree.add(10));
		
		tree.add(20);
		int stl = tree.size();
		assertEquals("Wrong size of tree", 2, stl);
		assertEquals("Wrong height of tree", 2, tree.height());
		assertFalse("add returns wrong value", tree.add(20));

		assertTrue("add returns wrong value", tree.add(30));
	}
	
	@Test
	void testStringAndConstructorTree() {
		tree2.add("Hej");
		assertEquals("Wrong size of tree", 1, tree2.size());
		assertEquals("Wrong height of tree", 0, tree2.height());
		assertFalse("add returns wrong value", tree2.add("Hej"));
		
		tree2.add("Hej Då");
		assertEquals("Wrong size of tree", 2, tree2.size());
		assertEquals("Wrong height of tree", 2, tree2.height());
		assertFalse("add returns wrong value", tree2.add("Hej Då"));
		
		assertTrue("add returns wrong value", tree2.add("Tjo"));
	}
	
	@Test
	void testClear() {
		tree.add(10);
		tree.add(20);
		tree.add(30);
		tree.clear();
		
		assertEquals("List is not empty", 0, tree.size());
	}

}
