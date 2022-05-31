
public class InsertionSort implements SortInterface {

	@Override
	public void sort(Integer[] arrayToSort) {
		int n = arrayToSort.length;
		for(int i = 1; i < n; ++i) {
			int x = arrayToSort[i];
			int j = i - 1;
			while(j >= 0 && arrayToSort[j] > x) {
				arrayToSort[j + 1] = arrayToSort[j];
				j = j - 1;
			}
			arrayToSort[j + 1] = x;
		}
	}

}
