import java.util.Random;
public class SortDriver implements SortDriverInterface {

	@Override
	public Integer[] createArray(SortDriverInterface.ArrayType arrayType, int arraySize) {
		Integer[] array = new Integer[arraySize];
		switch (arrayType) {
		case Equal:
			Random r = new Random();
			int l = r.nextInt();
			for(int i = 0; i < arraySize; i++) {
				array[i] = l;
			}
			break;
		
		case Random:
			for(int i = 0; i < arraySize; i++) {
				Random rand = new Random();
				array[i] = rand.nextInt();
			}
			break;
			
		case Increasing:
			for(int i = 0; i < arraySize; i++) {
				array[i] = i;
			}
			break;
		
		case Decreasing:
			for(int i = 0; i < arraySize; i++) {
				array[i] = arraySize - i;
			}
			break;
			
		case IncreasingAndRandom:
			double limit = ((double) arraySize * 0.9);
			for(int i = 0; i < limit; i++) {
				array[i] = i;
			}
			for(int i = (int) limit; i < arraySize; i++) {
				Random ran = new Random();
				array[i] = ran.nextInt();
			}
			break;
		}
		return array;
	}

	@Override
	public Object[] runSort(SortDriverInterface.SortType sortType, SortDriverInterface.ArrayType arrayType,
			int arraySize, int numberOfTimes) {
		int o = 0;
		Object[] obj = new Object[numberOfTimes * 2 + 1];
		TestTimes t = new TestTimes();
		long start;
		long end;
		long testTime;
		for(int i = 0; i < numberOfTimes; i++) {
			Integer[] array = new Integer[arraySize];
			array = createArray(arrayType, arraySize);
			Integer[] copy1 = new Integer[arraySize];
			Integer[] copy2 = new Integer[arraySize];
			for(int j = 0; j < array.length; j++) {
				copy1[j] = array[j];
			}
			switch (sortType) {
			
			case BubbleSort:
				BubbleSort b = new BubbleSort();
			    start = System.nanoTime();
				b.sort(array);
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
//				for(int j = 0; j < array.length; j++) {
//					copy2[j] = array[j];
//				}
				break;
				
			case InsertionSort:
				InsertionSort e = new InsertionSort();
				start = System.nanoTime();
				e.sort(array);
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
//				for(int j = 0; j < array.length; j++) {
//					copy2[j] = array[j];
//				}
				break;
				
			case SelectionSort:
				SelectionSort s = new SelectionSort();
				start = System.nanoTime();
				s.sort(array);
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				
				break;
			}
			for(int j = 0; j < array.length; j++) {
					copy2[j] = array[j];
				}
			obj[o++] = copy1;
			obj[o++] = copy2;
		}
		obj[o] = t;		
		return obj;
	}
//	public static void main(String[] args) { 
//		SortType s = null;
//		ArrayType a = null;
//		int size = 0;
//		SortDriver r = new SortDriver();
//		for (int i = 0; i < 30; i++) {
//			if (i == 0) {
//				s = SortType.BubbleSort;
//				a = ArrayType.Equal;
//				size = 1_000;
//			}
//			if (i == 1) {
//				s = SortType.BubbleSort;
//				a = ArrayType.Equal;
//				size = 10_000;
//			}
//			if (i == 2) {
//				s = SortType.BubbleSort;
//				a = ArrayType.Random;
//				size = 1_000;
//			}
//			if (i == 3) {
//				s = SortType.BubbleSort;
//				a = ArrayType.Random;
//				size = 10_000;
//			}
//			if (i == 4) {
//				s = SortType.BubbleSort;
//				a = ArrayType.Increasing;
//				size = 1_000;
//			}
//			if (i == 5) {
//				s = SortType.BubbleSort;
//				a = ArrayType.Increasing;
//				size = 10_000;
//			}
//			if (i == 6) {
//				s = SortType.BubbleSort;
//				a = ArrayType.Decreasing;
//				size = 1_000;
//			}
//			if (i == 7) {
//				s = SortType.BubbleSort;
//				a = ArrayType.Decreasing;
//				size = 10_000;
//			}
//			if (i == 8) {
//				s = SortType.BubbleSort;
//				a = ArrayType.IncreasingAndRandom;
//				size = 1_000;
//			}
//			if (i == 9) {
//				s = SortType.BubbleSort;
//				a = ArrayType.IncreasingAndRandom;
//				size = 10_000;
//			}
//			if (i == 10) {
//				s = SortType.SelectionSort;
//				a = ArrayType.Equal;
//				size = 1_000;
//			}
//			if (i == 11) {
//				s = SortType.SelectionSort;
//				a = ArrayType.Equal;
//				size = 10_000;
//			}
//			if (i == 12) {
//				s = SortType.SelectionSort;
//				a = ArrayType.Random;
//				size = 1_000;
//			}
//			if (i == 13) {
//				s = SortType.SelectionSort;
//				a = ArrayType.Random;
//				size = 10_000;
//			}
//			if (i == 14) {
//				s = SortType.SelectionSort;
//				a = ArrayType.Increasing;
//				size = 1_000;
//			}
//			if (i == 15) {
//				s = SortType.SelectionSort;
//				a = ArrayType.Increasing;
//				size = 10_000;
//			}
//			if (i == 16) {
//				s = SortType.SelectionSort;
//				a = ArrayType.Decreasing;
//				size = 1_000;
//			}
//			if (i == 17) {
//				s = SortType.SelectionSort;
//				a = ArrayType.Decreasing;
//				size = 10_000;
//			}
//			if (i == 18) {
//				s = SortType.SelectionSort;
//				a = ArrayType.IncreasingAndRandom;
//				size = 1_000;
//			}
//			if (i == 19) {
//				s = SortType.SelectionSort;
//				a = ArrayType.IncreasingAndRandom;
//				size = 10_000;
//			}
//			if (i == 20) {
//				s = SortType.InsertionSort;
//				a = ArrayType.Equal;
//				size = 1_000;
//			}
//			if (i == 21) {
//				s = SortType.InsertionSort;
//				a = ArrayType.Equal;
//				size = 10_000;
//			}
//			if (i == 22) {
//				s = SortType.InsertionSort;
//				a = ArrayType.Random;
//				size = 1_000;
//			}
//			if (i == 23) {
//				s = SortType.InsertionSort;
//				a = ArrayType.Random;
//				size = 10_000;
//			}
//			if (i == 24) {
//				s = SortType.InsertionSort;
//				a = ArrayType.Increasing;
//				size = 1_000;
//			}
//			if (i == 25) {
//				s = SortType.InsertionSort;
//				a = ArrayType.Increasing;
//				size = 10_000;
//			}
//			if (i == 26) {
//				s = SortType.InsertionSort;
//				a = ArrayType.Decreasing;
//				size = 1_000;
//			}
//			if (i == 27) {
//				s = SortType.InsertionSort;
//				a = ArrayType.Decreasing;
//				size = 10_000;
//			}
//			if (i == 28) {
//				s = SortType.InsertionSort;
//				a = ArrayType.IncreasingAndRandom;
//				size = 1_000;
//			}
//			if (i == 29) {
//				s = SortType.InsertionSort;
//				a = ArrayType.IncreasingAndRandom;
//				size = 10_000;
//			}
//			int numberOfTimes = 10;
//			Object[] o = r.runSort(s, a, size, numberOfTimes);
//			TestTimes t = (TestTimes) o[o.length - 1];
//			System.out.println(s + ", " + a + ", " + size);
//			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//			long[] c = t.getTestTimes();
//			for (int j = 0; j < c.length; j++) {
//				System.out.print(c[j] + "      ");
//			}
//			System.out.println(t.getAverageTestTime());
//		}
//	}
}
