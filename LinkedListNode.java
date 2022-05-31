
public class LinkedListNode<I extends Comparable<? super I>> {

	private I element;
	private LinkedListNode<I> next;
	
	public LinkedListNode(I element) {
		this.element = element;
		this.next = null;
	}
	
	public LinkedListNode(I element, LinkedListNode<I> next) {
		this.element = element;
		this.next = next;
	}

	public I getElement() {
		return element;
	}

	public void setElement(I element) {
		this.element = element;
	}

	public LinkedListNode<I> getNext() {
		return next;
	}

	public void setNext(LinkedListNode<I> next) {
		this.next = next;
	}
	
	
	
}