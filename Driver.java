
public class Driver implements DriverInterface {

	@Override
	public DoubleEndedQueue<Integer> fillDoubleEndedQueue(DriverInterface.TestType testType) {
		DoubleEndedQueue<Integer> doubleQueue = new DoubleEndedQueue<Integer>();
		
		switch(testType) {
		case AddAllToFront:
			for(int i = 0; i <= 1000; i++) {
				doubleQueue.addFront(i);
			}
			break;
		case AddAllToBack:
			for(int i = 0; i <= 1000; i++) {
				doubleQueue.addBack(i);
			}
			break;
		case AddEvenFrontOddBack:
			for(int i = 0; i <= 1000; i++) {
				if ((i % 2) == 0) {
					doubleQueue.addFront(i);
				} else {
					doubleQueue.addBack(i);
				}
			}
			break;
		}
		
 		return doubleQueue;
	}

}
