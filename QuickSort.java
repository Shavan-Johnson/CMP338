
public class QuickSort<T extends Comparable <? super T>> implements SortInterface<T> {

	public static enum PivotType {FirstElement, RandomElement, MidOfFirstMidLastElement}
	
	private PivotType pivotType = PivotType.FirstElement;
	
	public PivotType getPivotType() {
		return pivotType;
	}

	public void setPivotType(PivotType pivotType) {
		this.pivotType = pivotType;
	}

	@Override
	public void sort(T[] arrayToSort) {
//		this.showArray("Before", arrayToSort, 0, arrayToSort.length - 1);
		this.quickSort(arrayToSort, 0, arrayToSort.length - 1);
//		this.showArray("After", arrayToSort, 0, arrayToSort.length - 1);
	}
	
	/***********************/
	/*   private methods   */
	/***********************/

	private void quickSort(T[] array, int first, int last) {
		int pivotIndex;
		if ((first < last)) {
//			this.showArray("Whole", array, first, last);
			pivotIndex = partition(array, first, last);
//			this.showArray(" Left", array, first, pivotIndex - 1);
			quickSort(array, first, pivotIndex - 1);
//			this.showArray("Right", array, pivotIndex + 1, last);
			quickSort(array, pivotIndex + 1, last);
//			System.out.println();
		}
	}
	
	private int partition(T[] array, int first, int last) {
		// ---------------------------------------------------------
		// Partitions an array for quicksort.
		// Precondition: theArray[ first ... last] where first <= last.
		// Postcondition: Returns the index of the pivot element of
		// theArray[ first ... last]. Upon completion of the method,
		// this will be the index value lastS1 such that
		// S1 = theArray[first ... lastS1-1] < pivot
		// theArray[lastS1] == pivot
		// S2 = theArray[lastS1+1 ... last] >= pivot
		// Calls: choosePivot.
		// ---------------------------------------------------------
		choosePivot(array, first, last);
		T pivot = array[first];
		T tempItem;

		// initially, everything but pivot is in unknown
		int lastS1 = first;

		// move one item at a time until unknown region is empty
		for ( int firstUnknown = first + 1 ; firstUnknown <= last ; firstUnknown++ ) {
			// Invariant: theArray[first+1 ... lastS1] < pivot

			// move item from unknown to proper region
			if (array[firstUnknown].compareTo(pivot) < 0) {
				++lastS1;
				tempItem = array[firstUnknown];
				array[firstUnknown] = array[lastS1];
				array[lastS1] = tempItem;
			}
			// else item from unknown belongs in S2
		}
		
		// place pivot in proper position and mark its location
		tempItem = array[first];
		array[first] = array[lastS1];
		array[lastS1] = tempItem;
		
		return lastS1;
	}
	
	public void choosePivot(T[] array, int first, int last) {
		// ---------------------------------------------------------
		// Chooses a pivot for quicksort's partition algorithm and
		// swaps it with the first item in an array.
		// Precondition: theArray[ first ... last] where first <= last.
		// Postcondition: theArray[first] is the pivot.
		// ---------------------------------------------------------
		switch (this.pivotType) {
		case FirstElement:
			// nothing to do, pivot is already the first element
			break;
		case RandomElement:
			// pick random element, place it in first element location
			
			int pivot = (int) (Math.random() * (last - first) + first);
			array[first] = array[pivot];
			break;
		case MidOfFirstMidLastElement:
			// pick the mid-value of first, mid, last and place it in the first element location
			int mid = (first + last) / 2;
			int a = array[first].compareTo(array[last]);
			int b = array[first].compareTo(array[mid]);
			int c = array[last].compareTo(array[mid]);
			if((a > 0) && (b < 0) || (a < 0) && (b > 0)) {
				array[first] = array[first];
			}
			else if((a < 0) && (c < 0) || (a > 0) && (c > 0)) {
				array[first] = array[last];
			}
			else {
				array[first] = array[mid];
			}
			break;
		}
	}
	
	private void showArray(String s, T[] array, int first, int last) {
		System.out.print(s + " - Array = ");
		for ( int i = first ; i <= last ; i++ ) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	
	
	
}