import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class ArrayBasedQueue<E> implements QueueInterface<E> {

	private final int MAX_ARRAY_SIZE = 10_000;
	
	private E[] queue;
	private int front;
	private int back;
	private int size;
	
	
	public ArrayBasedQueue() {
		this.queue = (E[]) new Object[MAX_ARRAY_SIZE];
		this.size = 0;
		this.front = -1;
		this.back = -1;
	}
	
	/*
	 * Create an instance of a Vector<E>
	 * Add all the elements in the Queue to the vector
	 * Create an instance of an ElementIterator<E> and give it the vector of elements
	 * Return the ElementIterator<E>
	 */
	@Override
	public Iterator<E> iterator() {
		Vector<E> vector = new Vector<E>();
		
		
		int curIndex;
		int endIndex = this.incrementIndex(this.back);
		
		for ( curIndex = this.front ; 
		      curIndex != endIndex ; 
		   	  curIndex = this.incrementIndex(curIndex) ) {
			vector.add(this.queue[curIndex]);
		}
		
		ElementIterator<E> elementIterator = new ElementIterator<E>(vector);
		return elementIterator;
	}

	@Override
	public boolean isEmpty() {
		return (this.size == 0);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public QueueInterface<E> copy() {
		ArrayBasedQueue copy = new ArrayBasedQueue();
		
		for (int i = 0; i < queue.length; i++) {
			if (queue[i] != null) {
				copy.enqueue(queue[i]);
			}
		}
		
		return copy;
	}

	/*
	 * if element is null, throw a null pointer exception
	 * else if queue is empty, place element at front of queue
	 * else if array is full, throw an illegal state exception
	 * else add element at back of queue
	 */
	@Override
	public void enqueue(E element) throws IllegalStateException, NullPointerException {
		if (element == null) {
			throw new NullPointerException("Unable to enqueue null element to ArrayBasedQueue");
		} else if (this.isEmpty()) {
			this.front = 0;
			this.back = 0;
			this.queue[front] = element;
		} else if (this.size == MAX_ARRAY_SIZE) {
			throw new IllegalStateException("The ArrayBasedQueue is already full, unable to enqueue new element");
		} else {
			this.back = this.incrementIndex(this.back);
			this.queue[back] = element;
		}

		this.size++;
	}

	/*
	 * If queue is empty, return null
	 * else return element at front of queue
	 */
	@Override
	public E peek() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.queue[front];
		}
	}

	/*
	 * If queue is empty, return null
	 * else get element at front of queue
	 * move front of queue to next element in the queue
	 * decrement size
	 * return element
	 */
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			return null;
		} else {
			E element = this.queue[front];
			this.front = this.incrementIndex(this.front);
			this.size--;
			return element;
		}
	}

	/*
	 * If index is not valid, throw a no such element exception
	 * If index is 0 dequeue from the front of the queue
	 * Calculate the real index of the element
	 * Get the element at that index
	 * Move all the remaining elements beyond real index up one space
	 * Decrement size
	 * return element 
	 */
	@Override
	public E dequeue(int index) throws NoSuchElementException {
		if ((index < 0) || (index >= this.size)) {
			throw new NoSuchElementException("Index = " + index + " is not a valid index into a queue of size = " + this.size);
		}
		
		if (index == 0) {
			return this.dequeue();
		}
		
		int realIndex = this.calculateIndex(index);
		E element = this.queue[realIndex];

		int curIndex = realIndex;
		while (curIndex != this.back) {
			int nextIndex = this.incrementIndex(curIndex);
			this.queue[curIndex] = this.queue[nextIndex];
			curIndex = this.incrementIndex(curIndex);
		}
		
		this.back = this.decrementIndex(this.back);
		this.size--;
		return element;
	}

	/*
	 * Reallocate the array queue
	 * Set Size to 0
	 * Initialize front and back
	 */
	@Override
	public void removeAll() {
		this.queue = (E[]) new Object[MAX_ARRAY_SIZE];
		this.size = 0;
		this.front = -1;
		this.back = -1;
	}
	
	@Override
	public String toString() {
		String s = "Queue = [";
		
		int curIndex = this.front;
		while (curIndex != this.back) {
			s = s + this.queue[curIndex] + ", ";
			curIndex = this.incrementIndex(curIndex);
		}
		s = s + this.queue[curIndex] + "]\n";
		s = s + "Size = " + this.size + "\n";
		s = s + "Front = " + this.front + "\n";
		s = s + "Back = " + this.back + "\n\n";
		return s;
	}

	/************************/
	/*   private methods    */
	/************************/

	/*
	 * Increment and return the given index modulo array size
	 */
	private int incrementIndex(int index) {
		return (index + 1) % this.queue.length;
	}
	
	/*
	 * Decrement and return the given index modulo array size
	 */
	private int decrementIndex(int index) {
		index--;
		if (index >= 0) {
			return index;
		} else {
			return this.queue.length - 1;
		}
	}
	
	/*
	 * Add given index to front index modulo array size and return re
	 */
	private int calculateIndex(int index) {
		return (this.front + index) % this.queue.length;
	}
}