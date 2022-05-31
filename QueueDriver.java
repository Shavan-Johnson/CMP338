import java.util.Random;

public class QueueDriver implements QueueDriverInterface {

	@Override
	public QueueInterface<String> createQueue(QueueDriverInterface.QueueType queueType,
			QueueDriverInterface.TestType testType) {
		QueueInterface<String> queue = null;
		
		switch (queueType) {
		case ArrayBasedQueue:
			queue = new ArrayBasedQueue<String>();
			break;
		case LinkedQueue:
			queue = new LinkedQueue<String>();
			break;
		}
		
		switch (testType) {
		case Enqueue:
		     return queue;
		case Dequeue:
			for(int i = 0; i < 10_000; i++) {
				String s = "String " + i;
				if(i < 10_000) {
					s = s + " , ";
				}
				queue.enqueue(s);
			}
			break;
		case DequeueRandom:
			for(int i = 0; i < 10_000; i++) {
				String s = "String " + i;
				if(i < 10_000) {
					s = s + " , ";
				}
				queue.enqueue(s);
			}
			break;
		}
		
		return queue;
	}

	@Override
	public Object[] runTestCase(QueueDriverInterface.QueueType queueType, QueueDriverInterface.TestType queueTestType,
			int numberOfTimes) {
		
		int o = 0;
		Object[] obj = new Object[numberOfTimes * 2 + 1];
		TestTimes t = new TestTimes();
		long start;
		long end;
		long testTime;
		for(int i = 0; i < numberOfTimes; i++) {
			QueueInterface<String> queue = null;
			queue = createQueue(queueType, queueTestType);
			QueueInterface<String> copy1 = null;
			QueueInterface<String> copy2 = null;
			copy1 = queue.copy();
			
			switch (queueTestType) {
			case Enqueue:
				start = System.nanoTime();
				for(int j = 0; j < 10_000; j++) {
					String s = "String " + j;
					if(j < 10_000) {
						s = s + " , ";
					}
					queue.enqueue(s);
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case Dequeue:
				start = System.nanoTime();
				for(int j = 0; j < 10_000; j++) {
					queue.dequeue();
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case DequeueRandom:
				start = System.nanoTime();
				for(int j = 0; j < 10_000; j++) {
					Random rand = new Random();
					queue.dequeue(rand.nextInt(queue.size()));
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			}
			copy2 = queue.copy();
			obj[o++] = copy1;
			obj[o++] = copy2;
			
		}
		obj[o] = t;
		return obj;
	}
	
//	public static void main(String[] args) {
//		QueueType q = null;
//		TestType t = null;
//		QueueDriver g = new QueueDriver();
//		for(int i = 0; i < 3; i++) {
//			if(i == 0) {
//				t = TestType.Enqueue;
//			}
//			if(i == 2) {
//				t = TestType.Dequeue;
//			}
//			if(i == 3) {
//				t = TestType.DequeueRandom;
//			}
//			int numTimes = 10;
//			Object[] o1 = g.runTestCase(QueueType.ArrayBasedQueue, t, numTimes);
//			Object[] o2 = g.runTestCase(QueueType.LinkedQueue, t, numTimes);
//			TestTimes tt1 = (TestTimes) o1[o1.length - 1];
//			TestTimes tt2 = (TestTimes) o2[o2.length - 1];
//			tt1.setTimeUnits(TestTimesInterface.TimeUnits.MicroSeconds);
//			tt1.setMemoryUnits(TestTimesInterface.MemoryUnits.KiloBytes);
//			double[] s1 = tt1.getTestTimes();
//			double[] m1 = tt1.getMemoryUsages();
//			tt2.setTimeUnits(TestTimesInterface.TimeUnits.MicroSeconds);
//			tt2.setMemoryUnits(TestTimesInterface.MemoryUnits.KiloBytes);
//			double[] s2 = tt2.getTestTimes();
//			double[] m2 = tt2.getMemoryUsages();
//			
//			for(int j = 0; j < numTimes; j++) {
//				System.out.println("Test Type = " + t);
//				System.out.println("                     Run " + (j + 1) + "     ");
//				System.out.println("                     Micro       ");
//				System.out.println("                     Seconds     ");
//				System.out.println("                    ------------ ");
//				System.out.print("ArrayBasedQueue    ");
//				for(int d = 0; d < s1.length; d++) {
//					System.out.print(s1[d] + "   ");
//				}
//				System.out.println();
//				System.out.print("LinkedQueue    ");
//				for(int d = 0; d < s2.length; d++) {
//					System.out.print(s2[d] + "   ");
//				}
//				System.out.println();
//				System.out.println();
//				
//				System.out.println("Test Type = " + t);
//				System.out.println("                     Run " + (j + 1) + "     ");
//				System.out.println("                     Kilo        ");
//				System.out.println("                     Bytes       ");
//				System.out.println("                    ------------ ");
//				System.out.print("ArrayBasedQueue    ");
//				for(int d = 0; d < m1.length; d++) {
//					System.out.print(m1[d] + "   ");
//				}
//				System.out.println();
//				System.out.print("LinkedQueue    ");
//				for(int d = 0; d < m2.length; d++) {
//					System.out.print(m2[d] + "   ");
//				}
//			}
//		}
//	}
}
