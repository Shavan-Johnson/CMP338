import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class LinkedQueue<E> implements QueueInterface<E> {

	private Node<E> front;
	private Node<E> back;
	private int size;
	
	public LinkedQueue() {
		this.front = null;
		this.back = null;
		this.size = 0;
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
		
		Node<E> curNode = this.front;
		while (curNode != null) {
			vector.add(curNode.getElement());
			curNode = curNode.getNext();
		}
		
		ElementIterator<E> ei = new ElementIterator<E>(vector);
		return ei;
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
		LinkedQueue<E> copy = new LinkedQueue<E>();
		Node<E> newNode = front;
		while (newNode != null) {
			copy.enqueue(newNode.getElement());
			newNode = newNode.getNext();
		}
		return copy;
	}

	/*
	 * if the given element is null, throw a null pointer exception
	 * else allocate a new node for the specified element
	 * if the queue is empty, make new node the front and back of the queue
	 * else connect the new node to the back of the queue
	 * increment size
	 */
	@Override
	public void enqueue(E element) throws IllegalStateException, NullPointerException {
		if (element == null) {
			throw new NullPointerException("Unable to enqueue null element to ArrayBasedQueue");
		} else {
			Node<E> newNode = new Node<E>(element);
			if (this.isEmpty()) {
				this.front = newNode;
				this.back = newNode;
			} else {
				this.back.setNext(newNode);
				newNode.setPrevious(this.back);
				this.back = newNode;
			}
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
			return this.front.getElement();
		}
	}

	/*
	 * if queue is empty, return null
	 * else get the element at front of queue
	 * if only one element in queue, queue becomes empty
	 * else, remove front node from the queue and update front of queue
	 * decrement size
	 * return element
	 */
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			return null;
		} else {
			E element = this.front.getElement();
			if (this.front == this.back) {
				this.front = null;
				this.back = null;
			} else {
				this.front = this.front.getNext();
				this.front.setPrevious(null);
			}
			this.size--;
			return element;
		}
	}

	/*
	 * if index is invalid, throw a no such element exception
	 * if index is 0, dequeue from front of queue
	 * else if index is back of queue, get element, remove back node and update back
	 * decrement size and return element
	 * else find the specified node, get element, remove node from queue
	 * decrement size and return element
	 */
	@Override
	public E dequeue(int index) throws NoSuchElementException {
		if ((index < 0) || (index >= this.size)) {
			throw new NoSuchElementException("Index = " + index + " is not a valid index into a queue of size = " + this.size);
		}

		if (index == 0) {
			return this.dequeue();
		} else if (index == this.size - 1) {
			E element = this.back.getElement();
			this.back = this.back.getPrevious();
			this.back.setNext(null);
			this.size--;
			return element;
		} else {
			Node<E> curNode = this.front;
			int curIndex = 0;
			while (curIndex != index) {
				curNode = curNode.getNext();
				curIndex++;
			}
			E element = curNode.getElement();
			curNode.getPrevious().setNext(curNode.getNext());
			curNode.getNext().setPrevious(curNode.getPrevious());
			this.size--;
			return element;
		}
	}
	
	@Override
	public String toString() {
		String s = "Queue = ";
		
		Node<E> curNode = this.front;
		while (curNode != null) {
			if (curNode != this.back) {
				s = s + curNode.getElement() + " -> ";
			} else {
				s = s + curNode.getElement();
			}
			curNode = curNode.getNext();
		}
		s = s + "\n";
		s = s + "Size = " + this.size + "\n";
		s = s + "Front = " + this.front + "\n";
		s = s + "Back = " + this.back + "\n\n";
		return s;
	}

	@Override
	public void removeAll() {
		this.front = null;
		this.back = null;
		this.size = 0;
	}

}