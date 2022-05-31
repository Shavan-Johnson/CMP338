import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class TreeElementIterator<E extends KeyedElementInterface<K>, K extends Comparable<? super K>> 
	implements Iterator<E> {

	private BinarySearchTree<E, K> bst;
	
	private Vector<TreeNode<E, K>> treeNodes;
	
	public TreeElementIterator(BinarySearchTree<E, K> bst) {
		this.bst = bst;
		this.treeNodes = new Vector<TreeNode<E, K>>();
		this.inOrder(this.bst.getRoot());
	}
	
	public int size() {
		if (this.treeNodes == null) {
			return 0;
		} else {
			return treeNodes.size();
		}
	}
	
	public boolean isEmpty() {
		return (this.size() == 0);
	}
	
	public void setPreOrder() {
		this.treeNodes = new Vector<TreeNode<E, K>>();
		this.preOrder(this.bst.getRoot());
	}
	
	public void setInOrder() {
		this.treeNodes = new Vector<TreeNode<E, K>>();
		this.inOrder(this.bst.getRoot());
	}
	
	public void setPostOrder() {
		this.treeNodes = new Vector<TreeNode<E, K>>();
		this.postOrder(this.bst.getRoot());
	}

	
	
	
	
	@Override
	public boolean hasNext() {
		if (this.isEmpty()) {
			this.treeNodes = null;
			return false;
		} else {
			return true;
		}
	}

	@Override
	public E next() throws NoSuchElementException {
		if (this.isEmpty()) {
			this.treeNodes = null;
			throw new NoSuchElementException("Iterator has no elements to return");
		} else {
			E element = this.treeNodes.get(0).getElement();
			this.treeNodes.remove(0);
			return element;
		}
	}
	
	/******************************/
	/*    Private Methods         */
	/******************************/
	
	private void preOrder(TreeNode<E, K> node) {
		if (node == null) {
			return;
		} else {
			this.treeNodes.add(node);
			this.preOrder(node.getLeftChild());
			this.preOrder(node.getRightChild());   
		}
	}
	
	private void inOrder(TreeNode<E, K> node) {
		if (node == null) {
			return;
		} else {
			this.inOrder(node.getLeftChild());
			this.treeNodes.add(node);
			this.inOrder(node.getRightChild());   
		}
	}
	
	private void postOrder(TreeNode<E, K> node) {
		if (node == null) {
			return;
		} else {
			this.postOrder(node.getLeftChild());
			this.postOrder(node.getRightChild());   
			this.treeNodes.add(node);
		}
	}
	
	
	

}