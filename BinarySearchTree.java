
public class BinarySearchTree<E extends KeyedElementInterface<K>, K extends Comparable<? super K>>
		implements BinarySearchTreeInterface<E, K> {

	private TreeNode<E, K> root;

	public BinarySearchTree() {   
		this.root = null;
	}

	public BinarySearchTree(TreeNode<E, K> root) {
		this.root = root;
	}

	@Override
	public TreeNode<E, K> getRoot() {
		return this.root;
	}

	@Override
	public void setRoot(TreeNode<E, K> root) {
		this.root = root;
	}

	@Override
	public boolean isEmpty() {
		return (this.root == null);
	}

	@Override
	public void makeEmpty() {
		this.root = null;
	}

	@Override
	public BinarySearchTreeInterface<E, K> copy() {
		BinarySearchTree<E, K> copy = new BinarySearchTree<E, K>();
//		
//		TreeElementIterator<E, K> tree = new TreeElementIterator<E, K>(copy);
//		
//		tree.setPreOrder();
//		
//		while(tree.hasNext()) {
//			TreeNode<E, K> newNode = new TreeNode<E, K>(tree.next());
//			copy.insert(newNode.getElement());
//		}
		copy.root = clone(this.root);
		
		return copy;
	}
	
	public TreeNode<E, K> clone(TreeNode<E, K> node) {
		if(node == null) {
			return null;
		}
		TreeNode<E, K> newNode = new TreeNode<E, K>(node.getElement());
		
		newNode.setLeftChild(clone(node.getLeftChild()));
		newNode.setRightChild(clone(node.getRightChild()));
		return newNode;
	}

	@Override
	public E find(K key) {
		TreeNode<E, K> foundNode = this.findNode(key);
		if (foundNode != null) {
			return foundNode.getElement();
		} else {
			return null;
		}

	}

	@Override
	public void insert(E element) {
		// place element in a new node
		TreeNode<E, K> newNode = new TreeNode<E, K>(element);

		if (this.isEmpty()) {
			// the tree is empty, make the new node the root
			this.root = newNode;
		} else {
			// start at the root
			TreeNode<E, K> curNode = this.root;
			K elementKey = element.getKey();
			while (curNode != null) {
				K curKey = curNode.getElement().getKey();
				if (this.compareKeys(curKey, elementKey) > 0) {
					// the new element is smaller than the element in the curNode
					if (curNode.getLeftChild() == null) {
						// curNode does not have a left child add the new node as the left child
						curNode.setLeftChild(newNode);
						newNode.setParent(curNode);
						return;
					} else {
						// update curNode to be the left child of curNode
						curNode = curNode.getLeftChild();
					}
				} else {
					// the new element is equal to or greater that the element in the curNode
					if (curNode.getRightChild() == null) {
						// curNode does not have a right child add the new node as the right child
						curNode.setRightChild(newNode);
						newNode.setParent(curNode);
						return;
					} else {
						// update curNode to be the right child of curNode
						curNode = curNode.getRightChild();
					}
				}
			}
		}
	}

	@Override
	public int height() {
		if(isEmpty()) {
			return 0;
		}
		else {
			TreeNode<E, K> node = this.root;
		    return getHeight(node);
		}
	}
	
	public int getHeight(TreeNode<E, K> node) {
		if(node == null) {
			return 0;
		}
		else {
			int leftHeight = getHeight(node.getLeftChild());
			int rightHeight = getHeight(node.getRightChild());
			return (Math.max(leftHeight, rightHeight) + 1);
		}
	}

	

	@Override
	public void delete(K key) throws TreeException {
		if(this.root == null) {
			throw new TreeException("Unable to delete node from an empty tree");
		}
		// find the node to delete
		TreeNode<E, K> nodeToDelete = this.findNode(key);
		// if there is a node to delete, delete it
		if (nodeToDelete != null) {
			this.deleteNode(nodeToDelete);
		}

	}

	@Override
	public boolean isBalanced() {
		if(isEmpty()) {
			return false;
		}
		else {
			TreeNode<E, K> node = this.root;
		    return getBalance(node);
		}
	}
	
	public boolean getBalance(TreeNode<E, K> node) {
		if(node == null) {
			return true;
		}
		else {
			int LH = getHeight(node.getLeftChild());
			int RH = getHeight(node.getRightChild());
			if (Math.abs(LH - RH) <= 1 && getBalance(node.getLeftChild()) && getBalance(node.getRightChild())) {
				return true;
			}
			return false;
		}
	}

	@Override
	public void balance() {
		if(isEmpty()) {
			return;
		} else {
			this.root = balanceTree(root);
		}
	}
	
	public TreeNode<E, K> balanceTree(TreeNode<E, K> node) {
		int LH = getHeight(node.getLeftChild());
		int RH = getHeight(node.getRightChild());
		
		if(LH == RH) {
			return node;
		}
		else {
			if(LH < RH) {
				node = rotateRight(node);
			}
			else {
				node = rotateLeft(node);
			}
			return node;
		}
	}
	
	public TreeNode<E, K> rotateLeft(TreeNode<E, K> node) {
		TreeNode<E, K> newNode = node.getLeftChild();
		node.setLeftChild(newNode.getRightChild());
		newNode.setRightChild(node);
		return newNode;
	}
	
	public TreeNode<E, K> rotateRight(TreeNode<E, K> node) {
		TreeNode<E, K> newNode = node.getRightChild();
		node.setRightChild(newNode.getLeftChild());
		newNode.setLeftChild(node);
		return newNode;
	}

	public TreeElementIterator<E, K> iterator() {
		return new TreeElementIterator<E, K>(this);
	}

	/*******************************/
	/* Private Methods */
	/*******************************/

	private int compareKeys(K key1, K key2) {
		// key1 > key2 returns positive number
		// key1 == key2 returns 0
		// key1 < keys returns negative number
		return key1.compareTo(key2);
	}

	TreeNode<E, K> findNode(K key) {
		TreeNode<E, K> curNode = this.root;

		while (curNode != null) {
			K curKey = curNode.getElement().getKey();
			if (this.compareKeys(curKey, key) == 0) {
				return curNode;
			} else if (this.compareKeys(curKey, key) > 0) {
				curNode = curNode.getLeftChild();
			} else {
				curNode = curNode.getRightChild();
			}
		}

		return null;
	}

	private void deleteNode(TreeNode<E, K> nodeToDelete) {
		TreeNode<E, K> parentNode = nodeToDelete.getParent();
		if (this.isLeafNode(nodeToDelete)) {
			// deleting a leaf node
			if (parentNode == null) {
				// the leaf is also a root, delete the root
				this.root = null;
			} else if (this.nodeToDeleteIsLeftChildOfParent(parentNode, nodeToDelete)) {
				// the leaf node is the left child of the parent, remove the parent's left child
				parentNode.setLeftChild(null);
			} else {
				// the lead node is the right child of the parent, remove the parent's right
				// child
				parentNode.setRightChild(null);
			}
		} else if (this.nodeToDeleteOnlyHasLeftChild(nodeToDelete)) {
			// deleting a node with a left child only
			if (parentNode == null) {
				// the node being deleted is the root
				// promote the left child to be the root
				this.root = nodeToDelete.getLeftChild();
				this.root.setParent(null);
			} else if (this.nodeToDeleteIsLeftChildOfParent(parentNode, nodeToDelete)) {
				// the node being deleted is the left child of the parent
				// promote the left child of the node to be deleted to be the left child of the parent
				parentNode.setLeftChild(nodeToDelete.getLeftChild());
				nodeToDelete.getLeftChild().setParent(parentNode);
			} else {
				// the node being deleted is the right child of the parent
				// promote the left child of the node to be deleted to be the right child of the parent
				parentNode.setRightChild(nodeToDelete.getLeftChild());
				nodeToDelete.getLeftChild().setParent(parentNode);
			}
		} else if (this.nodeToDeleteOnlyHasRightChild(nodeToDelete)) {
			// deleting a node with a right child only
			if (parentNode == null) {
				// the node being deleted is the root
				// promote the right child to be the root
				this.root = nodeToDelete.getRightChild();
				this.root.setParent(null);
			} else if (this.nodeToDeleteIsLeftChildOfParent(parentNode, nodeToDelete)) {
				// the node being deleted is the left child of the parent
				// promote the right child of the node to be deleted to be the left child of the parent
				parentNode.setLeftChild(nodeToDelete.getRightChild());
				nodeToDelete.getRightChild().setParent(parentNode);
			} else {
				// the node being deleted is the right child of the parent
				// promote the right child of the node to be deleted to be the right child of the parent
				parentNode.setRightChild(nodeToDelete.getRightChild());
				nodeToDelete.getRightChild().setParent(parentNode);
			}
		} else {
			// nodeToDelete has two children
			// find the successor of the node to be deleted
			TreeNode<E, K> successor = this.findSuccessor(nodeToDelete);
			System.out.println("Successor is " + successor.getElement().getKey());
			// put the successor element in the place of the node to be deleted
			nodeToDelete.setElement(successor.getElement());
			// delete the successor node
			this.deleteNode(successor);
		}
	}

	// returns true if the node has no children
	private boolean isLeafNode(TreeNode<E, K> node) {
		return ((node.getLeftChild() == null) && (node.getRightChild() == null));
	}

	// returns true if the node to delete is the left child of the parent 
	private boolean nodeToDeleteIsLeftChildOfParent(TreeNode<E, K> parent, TreeNode<E, K> nodeToDelete) {
		return (parent.getLeftChild() == nodeToDelete);
	}

	// returns true if the node to delete has a left child only 
	private boolean nodeToDeleteOnlyHasLeftChild(TreeNode<E, K> nodeToDelete) {
		return ((nodeToDelete.getLeftChild() != null) && (nodeToDelete.getRightChild() == null));
	}

	// returns true if the node to delete has a right child only
	private boolean nodeToDeleteOnlyHasRightChild(TreeNode<E, K> nodeToDelete) {
		return ((nodeToDelete.getLeftChild() == null) && (nodeToDelete.getRightChild() != null));
	}

	// returns the successor node of the node to delete
	// the successor is the left most child in the right subtree of the node to delete
	private TreeNode<E, K> findSuccessor(TreeNode<E, K> nodeToDelete) {
		TreeNode<E, K> curNode = nodeToDelete.getRightChild();
		while (curNode.getLeftChild() != null) {
			curNode = curNode.getLeftChild();
		}
		return curNode;
	}

//	private int treeHeight(TreeNode<E, K> node) {
//		return 0;
//	}
	
}