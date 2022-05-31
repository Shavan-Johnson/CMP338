
public class TestTimes implements TestTimesInterface {
	
    private static final int MAX_TEST_TIMES = 10;
	
	private double[] testTimes = new double[MAX_TEST_TIMES];
	private double[] memoryTimes = new double[MAX_TEST_TIMES];
	
	private int numTestTimes = 0;
	private TestTimesInterface.TimeUnits timeUnits = TimeUnits.NanoSeconds;
	private TestTimesInterface.MemoryUnits memoryUnits = MemoryUnits.Bytes;

	@Override
	public void resetTestTimes() {
 		for ( int i = 0 ; i < MAX_TEST_TIMES ; i++ ) {
 			this.testTimes[i] = 0;
 			this.memoryTimes[i] = 0;
 		}
 		this.numTestTimes = 0;
	}

	@Override
	public void addTestTime(long testTime) {
		if (this.numTestTimes < MAX_TEST_TIMES) {
			this.testTimes[numTestTimes] = testTime;
			this.memoryTimes[numTestTimes] = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			numTestTimes++;
		} else {
			for (int i = 0 ; i < this.testTimes.length - 1 ; i++) {
				this.testTimes[i] = this.testTimes[i+1];
				this.memoryTimes[i] = this.memoryTimes[i+1];
			}
			this.testTimes[MAX_TEST_TIMES - 1] = testTime;
			this.memoryTimes[MAX_TEST_TIMES - 1] = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		}
	}

	@Override
	public double getAverageTestTime() {
		if (this.numTestTimes == 0) {
			return 0.0;
		} else {
			double total = 0.0;
			for ( int i = 0 ; i < this.numTestTimes ; i++ ) {
				total += this.testTimes[i];
			}
			double y = total / this.numTestTimes;
			switch (timeUnits) {
			case NanoSeconds:
				y = y;
				break;
			case Seconds:
				y = y / 1_000_000_000;
				break;
			case MilliSeconds:
				y = y / 1_000_000;
				break;
			case MicroSeconds:
				y = y / 1_000;
				break;
			}
			return y;
		}
	}

	@Override
	public TestTimesInterface.TimeUnits getTimeUnits() {
		return timeUnits;
	}

	@Override
	public void setTimeUnits(TestTimesInterface.TimeUnits timeUnits) {
	     this.timeUnits = timeUnits;
	}

	@Override
	public TestTimesInterface.MemoryUnits getMemoryUnits() {
		return memoryUnits;
	}

	@Override
	public void setMemoryUnits(TestTimesInterface.MemoryUnits memoryUnits) {
		this.memoryUnits = memoryUnits;
	}

	@Override
	public double getLastTestTime() {
		if (this.numTestTimes == 0) {
			return 0.0;
		}
		else {
			double y = this.testTimes[this.numTestTimes - 1];
			switch (timeUnits) {
			case NanoSeconds:
				y = y;
				break;
			case Seconds:
				y = y / 1_000_000_000;
				break;
			case MilliSeconds:
				y = y / 1_000_000;
				break;
			case MicroSeconds:
				y = y / 1_000;
				break;
			}
		return y;
		}
	}

	@Override
	public double getLastMemoryUsage() {
		if(this.numTestTimes == 0) {
     		return 0.0;
		}
		else {
			double y = this.memoryTimes[this.numTestTimes - 1];
			switch (memoryUnits) {
			case Bytes:
				y = y;
				break;
			case KiloBytes:
				y = y / 1_024;
				break;
			case MegaBytes:
				y = y / 1_048_576;
				break;
			}
			return y;
		}
	}

	@Override
	public double[] getTestTimes() {
		double[] copyArray = new double[MAX_TEST_TIMES];
		for (int i = 0 ; i < MAX_TEST_TIMES ; i++) {
			switch (timeUnits) {
			case NanoSeconds:
				copyArray[i] = this.testTimes[i];
				break;
			case Seconds:
				copyArray[i] = this.testTimes[i] / 1_000_000_000;
				break;
			case MilliSeconds:
				copyArray[i] = this.testTimes[i] / 1_000_000;
				break;
			case MicroSeconds:
				copyArray[i] = this.testTimes[i] / 1_000;
				break;
			}
		}
		return copyArray;
	}

	@Override
	public double[] getMemoryUsages() {
		double[] copy = new double[MAX_TEST_TIMES];
		for ( int i = 0 ; i < MAX_TEST_TIMES ; i++ ) {
			switch (memoryUnits) {
			case Bytes:
				copy[i] = this.memoryTimes[i];
				break;
			case KiloBytes:
				copy[i] = this.memoryTimes[i] / 1_024;
				break;
			case MegaBytes:
				copy[i] = this.memoryTimes[i] / 1_048_576;
				break;
			}
		}
		return copy;
	}

	@Override
	public double getAverageMemoryUsage() {
		if(this.numTestTimes == 0) {
		    return 0.0;
		}else {
			double total = 0.0;
			for ( int i = 0 ; i < this.numTestTimes ; i++ ) {
				total += this.memoryTimes[i];
			}
			double y = total / this.numTestTimes;
			switch (memoryUnits) {
			case Bytes:
				y = y;
				break;
			case KiloBytes:
				y = y / 1_024;
				break;
			case MegaBytes:
				y = y / 1_048_576;
				break;
			}
			return y;
		}
	}
}
