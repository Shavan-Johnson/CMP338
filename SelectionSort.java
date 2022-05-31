
public class SelectionSort implements SortInterface {

	@Override
	public void sort(Integer[] arrayToSort) {
	int n = arrayToSort.length;
	int i;
	int j;
	int iMin = -1;
	
	for(j = 0; j < n - 1; j++) {
		iMin = j;
		for(i = j + 1; i < n; i++) {
			if(arrayToSort[i] < arrayToSort[iMin]) {
				iMin = i;
			}
		}

		int b = arrayToSort[iMin];
		arrayToSort[iMin] = arrayToSort[j];
		arrayToSort[j] = b;

	}

	}

}
