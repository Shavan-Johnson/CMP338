public class TreeNode<E extends KeyedElementInterface<K>, K extends Comparable<? super K>> {
	
	private TreeNode<E, K> leftChild;
	private TreeNode<E, K> rightChild;
	private TreeNode<E, K> parent;
	private E element;
	
	public TreeNode() {
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
		this.element = null;
	}
	
	public TreeNode(E element) {
		this();
		this.element = element;
	}

	public TreeNode<E, K> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(TreeNode<E, K> leftChild) {
		this.leftChild = leftChild;
	}

	public TreeNode<E, K> getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeNode<E, K> rightChild) {
		this.rightChild = rightChild;
	}

	public TreeNode<E, K> getParent() {
		return parent;
	}

	public void setParent(TreeNode<E, K> parent) {
		this.parent = parent;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}
}