
public class LinearSearch implements SearchInterface {

	@Override
	public int search(int[] listOfNumbers, int target) {
		for (int i = 0; i < listOfNumbers.length; i++) {
	    	if (listOfNumbers[i] == target) {
		    return i;
		} 
	    	else if (listOfNumbers[i] > target) {
		    return -1;
		}
	    }
	    return -1;
	}

}
