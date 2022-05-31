
public class LinkedList<I extends Comparable<? super I>> implements ListInterface<I> {

	private LinkedListNode<I> start;
	private LinkedListNode<I> end;
	private int size;
	
	
	public LinkedList() {
		this.start = null;
		this.end = null;
		this.size = 0;
	}
	
	@Override
	public ListInterface<I> copy() {
//		if(size == 0) {
//			return null;
//		}
		LinkedList copy = new LinkedList();
		LinkedListNode newNode = start;
			while(newNode != null) {
			copy.add(newNode.getElement());
			newNode = newNode.getNext();
		}
		return copy;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
	return (this.size == 0);
	}

	/*
	 * create a new node and put the element in it {
	 * if the list is empty
	 *    make new node the start of list
	 *    make new node the end of list
	 * } else {
	 *    link current end node to new node
	 *    make new node the end of list
	 * }
	 * increment size of list by one
	 */
	@Override
	public void add(I element) {
		LinkedListNode<I> newNode = new LinkedListNode<I>(element);
		if (this.isEmpty()) {
			this.start = newNode;
			this.end = newNode;
		} else {
			this.end.setNext(newNode);
			this.end = newNode;
		}
		
		this.size++;
	}

	/*
	 * if specified index is just beyond the end of the list {
	 *    call the add(element) method
	 * } else if specified index is valid {
	 *    create a new node and put the element in it {
	 *    if index is 0 {
	 *       connect the new node with the old start of list
	 *       make new node the start of list
	 *    } else {
	 *       find the node at the specified index and it's predecessor
	 *       connect predecessor to the new node
	 *       connect the new node to the node that used to be at the specified index
	 *    }   
	 *    increment size of list by one
	 * } else {
	 *    throw exception
	 * }
	 */
	@Override
	public void add(I element, int index) throws IndexOutOfBoundsException {
		if (index == this.size) {
			this.add(element);
		} else if ((index >= 0) && (index < this.size)) {
			LinkedListNode<I> newNode = new LinkedListNode<I>(element);
			if (index == 0) {
				newNode.setNext(this.start);
				this.start = newNode;
			} else {
				int curIndex = 0;
				LinkedListNode<I> curNode = this.start;
				LinkedListNode<I> prevNode = null;
				while (curIndex != index) {
					prevNode = curNode;
					curNode = curNode.getNext();
					curIndex++;
				}
				prevNode.setNext(newNode);
				newNode.setNext(curNode);
			}
			this.size++;
		} else {
			throw new IndexOutOfBoundsException("index = " + index + " is invalid");
		}
		
	}

	@Override
	public void addSorted(I element) {
		if((this.start == null) || this.end.getElement().compareTo(element) <= 0) {
			add(element);
		}
		else {
			int i = 0;
			LinkedListNode<I> newNode = start;
			while(newNode.getElement().compareTo(element) < 0) {
				i++;
				newNode = newNode.getNext();
				//newNode.getElement().compareTo(element);
			}
			add(element, i);
		}
	}

	/*
	 * if the specified index is valid {
 	 * 	  if index == 0 {
	 *       get element from start of list
	 *    } else if index == last element {
	 *       get element from end of list
	 *    } else {
	 *       find the node at the specified index
	 *       return the element in the found node
	 *    }
	 * } else {
	 *    throw exception
	 * }
	 */
	@Override
	public I get(int index) throws IndexOutOfBoundsException {
		if ((index >= 0) && (index < this.size)) {
			if (index == 0) {
				return this.start.getElement();
			} else if (index == this.size - 1) {
				return this.end.getElement();
			} else {
				LinkedListNode<I> curNode = this.start;
				int curIndex = 0;
				while (curIndex != index) {
					curNode = curNode.getNext();
					curIndex++;
				}
				return curNode.getElement();
			}
		} else {
			throw new IndexOutOfBoundsException("index = " + index + " is invalid");
		}	
	}

	/*
	 * if the specified index is not valid {
	 *    throw exception
	 * } else {
	 * 	  if index == 0 {
	 *       set curNode to start of list
	 *    } else if index == last element {
	 *       set curNode to end of list
	 *    } else {
	 *       set curNode by finding the node at the specified index
	 *    }
	 *    get old element from found node
	 *    replace element in found node with specified element
	 *    return old element
	 * }
	 */
	@Override
	public I replace(I element, int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= this.size)) {
			throw new IndexOutOfBoundsException("index = " + index + " is invalid");
		} else {
			LinkedListNode<I> curNode = null;
			if (index == 0) {
				curNode = this.start;
			} else if (index == this.size - 1) {
				curNode = this.end;
			} else {
				curNode = this.start;
				int curIndex = 0;
				while (curIndex != index) {
					curNode = curNode.getNext();
					curIndex++;
				}
			}
			I curElement = curNode.getElement();
			curNode.setElement(element);
			return curElement;
		}
	}

	@Override
	/*
	 * if the specified index is not valid {
	 *    throw exception
	 * } else {
	 *    if first node {
	 *       get old element from first node
	 *       make second node be the start of the list
	 *    } else if last node {
	 *       get old element from last node
	 *       find the predecessor node to the last node
	 *       set the successor of predecessor node to null
	 *       make predecessor node the end of the list
	 *    } else {
	 *       find the node at the specified index and it's predecessor
	 *       get old element from found node
	 *       connect predecessor node to successor node
	 *    }
	 *    decrement list size by one
	 *    return the old element
	 * }
	 */
	public I remove(int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= this.size)) {
			throw new IndexOutOfBoundsException("index = " + index + " is invalid");
		} else {
			I oldElement = null;
			if (index == 0) {
				oldElement = this.start.getElement();
				this.start = this.start.getNext();
			} else if (index == (this.size - 1)) {
				oldElement = this.end.getElement();
				LinkedListNode<I> newEnd = this.start;
				while (newEnd.getNext() != this.end) {
					newEnd = newEnd.getNext();
				}
				newEnd.setNext(null);
				this.end = newEnd;
			} else {
				LinkedListNode<I> prevNode = null;
				LinkedListNode<I> curNode = this.start;
				int curIndex = 0;
				while (curIndex != index) {
					prevNode = curNode;
					curNode = curNode.getNext();
					curIndex++;
				}
				oldElement = curNode.getElement();
				prevNode.setNext(curNode.getNext());
			}
			this.size--;
			return oldElement;
		}
	}

	@Override
	public void removeAll() {
		this.start = null;
		this.end = null; 
		this.size = 0;
	}

	@Override
	public String toString() {
		String s = "LinkedList = ";
		LinkedListNode<I> curNode = this.start;
		while (curNode != null) {
			s = s + curNode.getElement();
			if (curNode != this.end) {
				s = s + " -> ";
			}
			curNode = curNode.getNext();
		}
		s = s + "\nSize = " + this.size;
		return s;
	}

}