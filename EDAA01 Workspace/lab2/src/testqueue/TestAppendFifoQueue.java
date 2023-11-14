package testqueue;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
//import java.util.Queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueue;
	private FifoQueue<Integer> myQ;

	@BeforeEach
	void setUp() throws Exception {
		myIntQueue = new FifoQueue<Integer>();
		myQ = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		myIntQueue = null;
		myQ = null;
	}

	/**
	 * Test if it is possible to append to empty queues.
	 */
	@Test
	void testTwoEmptyQueues() {
		assertThrows(IllegalArgumentException.class, () -> myIntQueue.append(myQ));
		assertEquals(0, myIntQueue.size(), "Wrong size after append");
		assertEquals(0, myQ.size(), "Wrong size after append");
	}
	
	/**
	 * Test to fill a empty queue.
	 */
	@Test
	void testEmptyQueueAppendsNotEmpty() {
		for (int i = 1; i <= 5; i++) {
			myQ.offer(i);
		}
		myIntQueue.append(myQ);
		assertEquals(5, myIntQueue.size(), "Wrong size after append");
		assertEquals(0, myQ.size(), "Wrong size after append");
		
		Iterator<Integer> itr = myIntQueue.iterator();
		for (int i = 1; i <= 5; i++) {
			assertEquals(Integer.valueOf(i), itr.next(), "Wrong result from next");
		}
	}
	
	/**
	 * Test to append a empty queue.
	 */
	@Test
	void testNotEmptyQueAppendsEmpty() {
		for (int i = 1; i <= 5; i++) {
			myIntQueue.offer(i);
		}
		assertThrows(IllegalArgumentException.class, () -> myIntQueue.append(myQ));
		assertEquals(5, myIntQueue.size(), "Wrong size after append");
		assertEquals(0, myQ.size(), "Wrong size after append");
	}
	
	/**
	 * Test to append two not-empty queues.
	 */
	@Test
	void testTwoNotEmptyQueues() {
		for (int i = 1; i <= 5; i++) {
			myIntQueue.offer(i);
			myQ.offer(i + 5);
		}
		myIntQueue.append(myQ);
		assertEquals(10, myIntQueue.size(), "Wrong size after append");
		assertEquals(0, myQ.size(), "Wrong size after append");
		
		Iterator<Integer> itr = myIntQueue.iterator();
		for (int i = 1; i <= 10; i++) {
			assertEquals(Integer.valueOf(i), itr.next(), "Wrong result from next");
		}
	}
	
	/**
	 * Test to append a queue to itself.
	 */
	@Test
	void testAppendingSelf() {
		for (int i = 1; i <= 5; i++) {
			myIntQueue.offer(i);
		}
		
		assertThrows(IllegalArgumentException.class, () -> myIntQueue.append(myIntQueue));
		assertThrows(IllegalArgumentException.class, () -> myQ.append(myQ));
	}
}
