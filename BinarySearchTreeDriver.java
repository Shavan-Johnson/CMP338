import java.util.Vector;
import java.util.Random;

public class BinarySearchTreeDriver implements BinarySearchTreeDriverInterface {

	@Override
	public Vector<Person<Integer>> createPersons(int numberOfPersons) {
	    Vector<Person<Integer>> persons = new Vector<Person<Integer>>();
	    
	    for(int i = 0; i < numberOfPersons; i++) {
	    	Random rand = new Random();
	    	Person<Integer> p = new Person<Integer>(rand.nextInt());
	    	persons.add(p);
	    }
	    
		return persons;
	}

	@Override
	public BinarySearchTree<Person<Integer>, Integer> createBinarySearchTree(BinarySearchTreeDriverInterface.TestType testType, Vector<Person<Integer>> persons) {
		BinarySearchTree<Person<Integer>, Integer> bst = new BinarySearchTree<Person<Integer>, Integer>();
		
		switch (testType) {
		case Insert:
			break;
		case Find:
			for(int i = 0; i < persons.size(); i++) {
				bst.insert(persons.elementAt(i));
			}
			break;
		case Delete:
			for(int i = 0; i < persons.size(); i++) {
				bst.insert(persons.elementAt(i));
			}
			break;
		case Balance:
			for(int i = 0; i < persons.size(); i++) {
				bst.insert(persons.elementAt(i));
			}
			break;
		case FindBalanced:
			for(int i = 0; i < persons.size(); i++) {
				bst.insert(persons.elementAt(i));
			}
			while (bst.isBalanced() != true) {
				bst.balance();
			}
			break;
		case DeleteBalanced:
			for(int i = 0; i < persons.size(); i++) {
				bst.insert(persons.elementAt(i));
			}
			while (bst.isBalanced() != true) {
				bst.balance();
			}
			break;
		}
		
		return bst;
	}

	@Override
	public Object[] runTestCase(BinarySearchTreeDriverInterface.TestType testType, int numberOfPersons,
			int numberOfTimes) {
		int o = 0;
		Object[] obj = new Object[numberOfTimes * 2 + 1];
		TestTimes t = new TestTimes();
		long start;
		long end;
		long testTime;
		for(int i = 0; i < numberOfTimes; i++) {
			
			Vector<Person<Integer>> p = createPersons(numberOfPersons);
			
			BinarySearchTreeInterface bst = createBinarySearchTree(testType, p);
			
			BinarySearchTreeInterface copy1, copy2;
			
			copy1 = bst.copy();
			
			switch (testType) {
			case Insert:
				start = System.nanoTime();
				for(int j = 0; j < numberOfPersons; j++) {
					bst.insert(p.elementAt(j));
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case Find:
				start = System.nanoTime();
				for(int j = 0; j < numberOfPersons; j++) {
					bst.find(p.elementAt(j).getKey());
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case Delete:
				start = System.nanoTime();
				for(int j = 0; j < numberOfPersons; j++) {
					try {
						bst.delete(p.elementAt(j).getKey());
					} catch (TreeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case Balance:
				start = System.nanoTime();
				while(bst.isBalanced() != true) {
					bst.balance();
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case FindBalanced:
				start = System.nanoTime();
				for(int j = 0; j < numberOfPersons; j++) {
					bst.find(p.elementAt(j).getKey());
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			case DeleteBalanced:
				start = System.nanoTime();
				for(int j = 0; j < numberOfPersons; j++) {
					try {
						bst.delete(p.elementAt(j).getKey());
					} catch (TreeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				end = System.nanoTime();
				testTime = end - start;
				t.addTestTime(testTime);
				break;
			}
			
			copy2 = bst.copy();
			
			obj[o++] = copy1;
			obj[o++] = copy2;
			
		}
		obj[o] = t;
		return obj;
	}

}
