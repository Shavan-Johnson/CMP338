
public class BubbleSort implements SortInterface {

	@Override
	public void sort(Integer[] arrayToSort) {
		int n = arrayToSort.length;
		boolean swapped = true;
		while(swapped != false) {
			swapped = false;
			for(int i = 0; i < n - 1; i++) {
				for (int j = 0; j < n - i - 1; j++) {
					if (arrayToSort[j] > arrayToSort[j + 1]) {
						int b = arrayToSort[j];
						arrayToSort[j] = arrayToSort[j + 1];
						arrayToSort[j + 1] = b;
						swapped = true;
				}
			}
			}
		}
	}

}
