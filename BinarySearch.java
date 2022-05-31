
public class BinarySearch implements SearchInterface {

	@Override
	public int search(int[] listOfNumbers, int target) {
		 int first = 0;
		 int last = listOfNumbers.length - 1;
		 int middle;

		 while (first <= last) {
			 middle = (first + last) / 2;

			 if (listOfNumbers[middle] == target) {
				 return middle;
			 } else if (target < listOfNumbers[middle]) {
				 last = middle - 1;
			 } else {
				 first = middle + 1;
			 }
		 }

		 return -1;
	}

}
