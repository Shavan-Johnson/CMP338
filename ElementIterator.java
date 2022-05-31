import java.util.Iterator;
import java.util.Vector;

public class ElementIterator<E> implements Iterator<E> {

	private Vector<E> vector;
	
	
	public ElementIterator(Vector<E> vector) {
		this.vector = vector;
	}
	
	/*
	 * if the vector has no elements return false, otherwise return true
	 */
	@Override
	public boolean hasNext() {
		return !vector.isEmpty();
	}

	/*
	 * remove and return the first element in the vector
	 */
	@Override
	public E next() {
		return vector.remove(0);
	}

}