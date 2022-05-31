/**
 *
 * <p>
 * This is the DoubleEndedQueueInterface for the Midterm Exam of CMP-338.
 * </p>
 *
 * <p>
 * The DoubleEndedQueue ADT is a double-ended queue. Users are allowed to add and 
 * remove elements from both the front and the back of the DoubleEndedQueue.
 * </p>
 * 
 * @author Sameh Fakhouri
 *
 * @param <E>	The generic parameter that specifies the type of elements in the DoubleEndedQueue.
 */
public interface DoubleEndedQueueInterface<E> extends Iterable<E> {

	/**
	 * This method is called to determine if the DoubleEndedQueue is empty.
	 * 
	 * @return 	Returns <b>true</b> if the DoubleEndedQueue is empty, otherwise it returns <b>false</b>. 
	 */
	public boolean isEmpty();

	/**
	 * This method is called to obtain the count of elements in the list.
	 * 
	 * @return 	Returns the numbers of elements that are currently in the DoubleEndedQueue.
	 */
	public int size();

	/**
	 * 
	 * Adds the specified element to the <b><code>front</code></b> of the DoubleEndedQueue. If the specified element is <b><code>null</code></b>
	 * the method will throw a NullPointerException.
	 * 
	 * @param	element	The element to add.
	 * 
	 * @throws 	NullPointerException	If the specified element being added is null. 
	 * 
	 */
	public void addFront(E element) throws NullPointerException;

	/**
	 * 
	 * Adds the specified element to the <b><code>back</code></b> of the DoubleEndedQueue. If the specified element is <b><code>null</code></b>
	 * the method will throw a NullPointerException.
	 * 
	 * @param	element	The element to add.
	 * 
	 * @throws 	NullPointerException	If the specified element being added is null. 
	 * 
	 */
	public void addBack(E element) throws NullPointerException;
	
	/**
	 * 
	 * Retrieves and removes the element at the <b><code>front</code></b> of the DoubleEndedQueue.
	 * 
	 * @return	The element at the front of the DoubleEndedQueue or <b><code>null</code></b> if the DoubleEndedQueue is empty.
	 * 
	 */
	public E removeFront();
	
	/**
	 * 
	 * Retrieves and removes the element at the <b><code>back</code></b> of the DoubleEndedQueue.
	 * 
	 * @return	The element at the back of the DoubleEndedQueue or <b><code>null</code></b> if the DoubleEndedQueue is empty.
	 * 
	 */
	public E removeBack();
	
	/**
	 * 
	 * Retrieves, but does not remove element at the <b><code>front</code></b> of the DoubleEndedQueue.
	 * 
	 * @return	The element at the front of the DoubleEndedQueue or <b><code>null</code></b> if the DoubleEndedQueue is empty.
	 * 
	 */
	public E peekFront();
	
	/**
	 * 
	 * Retrieves, but does not remove element at the <b><code>back</code></b> of the DoubleEndedQueue.
	 * 
	 * @return	The element at the back of the DoubleEndedQueue or <b><code>null</code></b> if the DoubleEndedQueue is empty.
	 * 
	 */
	public E peekBack();

	/**
	 * 
	 * Removes all elements from the DoubleEndedQueue making it empty.
	 * 
	 */
	public void removeAll();
}
