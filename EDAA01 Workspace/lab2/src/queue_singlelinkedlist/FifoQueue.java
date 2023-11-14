package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		if (e == null) {
			return false;
		}
		
		QueueNode<E> n = new QueueNode<>(e);
		if (last == null) {
			last = n;
			last.next = last;
		} else {
			n.next = last.next;
			last.next = n;
			last = n;
		}
		
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (last == null) {
			return null;
		}
		
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (size == 0) {
			return null;
		}
		
		E n = peek();
		if (size == 1) {
			last = null;
			size--;
			return n;
		}
		
		last.next = last.next.next;
		size--;
		return n;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if (this.equals(q)) {
			throw new IllegalArgumentException();
		} else if (this.size == 0 && q.size == 0) {
			throw new IllegalArgumentException();
		} else if (q.size == 0) {
			throw new IllegalArgumentException();
		} 
		
		if (this.size == 0 && q.size != 0) {
			this.last = q.last;
			this.size = q.size;
			q.last = null;
			q.size = 0;
		} 
		
		if (this.size != 0 && q.size != 0) {
			QueueNode<E> temp = this.last.next;
			this.last.next = q.last.next;
			q.last.next = temp;
			this.size += q.size;
			last = q.last;
		}
		
		q.last = null;
		q.size = 0;
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int count;
		
		/* Konstruktor */
		private QueueIterator() {
			pos = last;
			count = size;
		}
		public boolean hasNext() {
			if (count == 0) {
				return false;
			}
			return pos != null;
		}
		public E next() {
			if (hasNext()) {
				pos = pos.next;
				QueueNode<E> temp = pos;
				count--;
				return temp.element;
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}