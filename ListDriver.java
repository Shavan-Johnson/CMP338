
public class ListDriver implements ListDriverInterface {

	@Override
	public ListInterface<Integer> createList(ListDriverInterface.ListType listType,
			ListDriverInterface.TestType forTestType) {
		ListInterface list = null;
		switch (listType) {
		
		case ArrayBasedList:
			list = new ArrayBasedList();
			break;
		case LinkedList:
			list = new LinkedList();
			break;
		}
		
		switch (forTestType) {
		
		case AddAll:
			return list;
		case AddAllAtIndexZero:
			return list;
		case AddSortedEven:
			initializeList(list, 1, 9_999, 2);
			break;
		case AddSortedOdd:
			return list;
		case RemoveAllEven:
			initializeList(list, 1, 10_000, 1);
			break;
		case RemoveAllOdd:
			initializeList(list, 1, 10_000, 1);
			break;
		}
		return list;
	}

	@Override
	public ListInterface<Integer> initializeList(ListInterface<Integer> list, int firstNumber, int lastNumber,
			int increment) {
		if(increment == 0) {
			return list;
		}
		for(int i = firstNumber; i <= lastNumber; i+=increment) {
			list.add(i);
		}
		return list;
	}

	@Override
	public double memoryUsage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] runTestCase(ListDriverInterface.ListType listType, ListDriverInterface.TestType testType,
			int numberOfTimes) {
		int o = 0;
		Object[] obj = new Object[numberOfTimes * 2 + 1];
		TestTimes t = new TestTimes();
		long start;
		long end;
		long testTime;
		for(int i = 0; i < numberOfTimes; i++) {
			ListInterface list = null;
			list = createList(listType, testType);
			ListInterface copy1 = null; 
			ListInterface copy2 = null;
			copy1 = list.copy();
			switch (testType) {
			case AddSortedOdd:
				start = System.nanoTime();
				for(int j = 1; j <= 9_999; j = j + 2) {
					list.addSorted(j);
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
				
			case AddSortedEven:
				start = System.nanoTime();
				for(int j = 2; j <= 10_000; j = j + 2) {
					list.addSorted(j);
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case AddAll:
				start = System.nanoTime();
				for(int j = 1; j <= 10_000; j++) {
					list.add(j);
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case AddAllAtIndexZero:
				start = System.nanoTime();
				for(int j = 1; j <= 10_000; j++) {
					list.add(j, 0);
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case RemoveAllEven:
				start = System.nanoTime();
				for(int j = 2; j <= 10_000; j = j + 2) {
					list.remove(j);
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case RemoveAllOdd:
				start = System.nanoTime();
				for(int j = 9_999; j >= 0; j = j - 2) {
					list.remove(j);
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
				}
			copy2 = list.copy();
			obj[o++] =copy1;
			obj[o++] = copy2;
		}
		obj[o] = t;
		return obj;
	}

}
