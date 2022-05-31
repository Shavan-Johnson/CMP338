
public class HeapSort<T extends Comparable <? super T>> implements SortInterface<T> {

	private T[] heap;
	private int heapSize;
	
	@Override
	public void sort(T[] arrayToSort) {
		this.setHeap(arrayToSort);
		//this.showHeap();
		
		while (heapSize >= 1) {
			int last = this.heapSize - 1;
			this.swap(0, last);
			this.heapSize--;
			this.heapify();
			//this.showHeap();
		}

		// reset the heapSize to see fully sorted array
		this.heapSize = this.heap.length;
	}
	
	/***********************/
	/*     main method     */
	/***********************/
	
//	public static void main(String[] args) {
//		Integer[] array = {5, 17, 22, 4, 18, 40, 72, 15, 90, 45, 19};
//		HeapSort<Integer> hs = new HeapSort();
//		hs.sort(array);
//		System.out.println("Sorted Array");
//		hs.showHeap();
//	}
	
	/***********************/
	/*   private methods   */
	/***********************/
	
	private void setHeap(T[] array) {
		this.heap = array;
		this.heapSize = array.length;
		this.heapify();
	}
	
	private void heapify() {
		int last = this.heapSize - 1;
		int parent = (last - 1) / 2;
		
		while (parent >= 0) {
			siftDown(parent);
			parent--;
		}
	}
	
	private void siftDown(int node) {
		while (node < this.heapSize) {
			int leftChild = (2 * node) + 1;
			int rightChild = (2 * node) + 2;
			
			T nodeElement = this.heap[node];
			int max = node;
			
			if (leftChild < this.heapSize) {
				T leftElement = this.heap[leftChild];
				if (nodeElement.compareTo(leftElement) < 0) {
					max = leftChild;
				}
			}
			
			if (rightChild < this.heapSize) {
				T rightElement = this.heap[rightChild];
				if (this.heap[max].compareTo(rightElement) < 0) {
					max = rightChild;
				}
			}
			
			if (max == node) {
				return;
			} else {
				this.swap(node, max);
				node = max;
			}
		}
	}
	
	private void swap(int i, int j) {
		T temp = this.heap[i];
		this.heap[i] = this.heap[j];
		this.heap[j] = temp;
	}

	
//	public void showHeap() {
//		System.out.print("[");
//		for ( int i = 0 ; i < this.heapSize ; i++ ) {
//			if (i == heapSize - 1) {
//				System.out.print(this.heap[i]);
//			} else {
//				System.out.print(this.heap[i] + ", ");
//			}
//		}
//		System.out.println("]\n\n");
//	}
}