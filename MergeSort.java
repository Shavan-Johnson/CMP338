
public class MergeSort<T extends Comparable <? super T>> implements SortInterface<T> {

	@Override
	public void sort(T[] arrayToSort) {
		Object[] tempArray = new Object[arrayToSort.length];
		mergeSort(arrayToSort, tempArray, 0, arrayToSort.length-1);
	}
	
	/***********************/
	/*   private methods   */
	/***********************/

	private void mergeSort(T[] array, Object[] temp, int first, int last) {
		if (first < last) {
			int mid = (first + last) / 2;
			mergeSort(array, temp, first, mid);
			mergeSort(array, temp, mid+1, last);
			merge(array, temp, first, mid, last);
		}
	}
	
	
	private void merge(T[] array, Object[] temp, int first, int mid, int last) {
		int first1 = first;
		int last1 = mid;
		int first2 = mid+1;
		int last2 = last;
		
//		System.out.println("Left Side");
//		this.showArray(array, first1, last1);
//		
//		System.out.println("Right Side");
//		this.showArray(array, first2, last2);
		
		int index = first1;
		
		while ((first1 <= last1) && (first2 <= last2)) {
			if (array[first1].compareTo(array[first2]) <= 0) {
				temp[index++] = array[first1++];
			} else {
				temp[index++] = array[first2++];
			}
		}
		
		while (first1 <= last1) {
			temp[index++] = array[first1++];
		}
		
		while (first2 <= last2) {
			temp[index++] = array[first2++];
		}
		
		for ( int i = first ; i <= last ; i++ ) {
			array[i] = (T) temp[i];
		}
		
//		System.out.println("Merged");
//		this.showArray(array, first, last);
//		
//		System.out.println("\n\n");
	}
	
	private void showArray(T[] array, int first, int last) {
		System.out.print("Array = ");
		for ( int i = first ; i <= last ; i++ ) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
}