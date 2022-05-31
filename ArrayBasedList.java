
public class ArrayBasedList<I extends Comparable<? super I>> implements ListInterface<I>{

	private final int DEFAULT_ARRAY_SIZE = 100;

	private int count;
	private I[] array;

	public ArrayBasedList() {
		this.count = 0;
		this.array = (I[]) new Comparable[DEFAULT_ARRAY_SIZE];
	}

	public ArrayBasedList(int size) {
		this.count = 0;
		if (size > 0) {
			this.array = (I[]) new Comparable[size];
		} else {
			this.array = (I[]) new Comparable[DEFAULT_ARRAY_SIZE];
		}
	}
	
	@Override
	public int size() {
		return this.count;
	}
	
	@Override
	public boolean isEmpty() {
		return (this.count == 0);
	}
	
	@Override
	public void add(I element) {
	 	if (this.count == this.array.length) {
			increaseArraySize();
		}
		this.array[this.count++] = element;
	}
	
	@Override
	public void add(I element, int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index > this.count)) {
			throw new IndexOutOfBoundsException("index = " + index + " is invalid");
		} else if (this.count == this.array.length) {
			increaseArraySize();
		} 
		
		if (index == this.count) {
			this.add(element);
		} else {
			for ( int i = this.count ; i > index ; i-- ) {
				this.array[i] = this.array[i-1];
			}
			this.array[index] = element;
			this.count++;
		}
	}

	@Override
	public I get(int index) throws IndexOutOfBoundsException {
		if ((index >= 0) && (index < this.count)) {
			return this.array[index];
		} else {
			throw new IndexOutOfBoundsException("index = " + index + " is invalid");
		}
	}

	@Override
	public I replace(I element, int index) throws IndexOutOfBoundsException {
		if ((index >= 0) && (index < this.count)) {
			I oldElement = this.array[index];
			this.array[index] = element;
			return oldElement;
		} else {
			throw new IndexOutOfBoundsException("index = " + index + " is invalid");
		}		
	}
	
	@Override
	public I remove(int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index > (this.count - 1))) {
			throw new IndexOutOfBoundsException("index = " + index + " is invalid");
		} else {
			I element = this.array[index];
			for ( int i = index ; i < (this.count - 1) ; i++ ) {
				this.array[i] = this.array[i+1];
			}
			this.count--;
			return element;
		}
	}
	
	@Override
	public void removeAll() {
		for ( int i = 0 ; i < this.array.length ; i++ ) {
			this.array[i] = null;
		}
		this.count = 0;
	}
	
	
	private void increaseArraySize() {
		I[] newArray = (I[]) new Comparable[this.array.length + this.DEFAULT_ARRAY_SIZE];
		for ( int i = 0 ; i < this.array.length ; i++ ) {
			newArray[i] = this.array[i];
		}
		this.array = newArray;
	}

	@Override
	public String toString() {
		String s = "array = {";
		for ( int i = 0 ; i < this.count ; i++ ) {
			if ( i < this.count - 1) {
				s = s + this.array[i] + ", ";
			} else {
				s = s + this.array[i] + "}";
			}
		}
		return s;
	}

	@Override
	public ListInterface<I> copy() {
		if(array.length == 0) {
			return null;
		}
		ArrayBasedList copy = null;
		if((this.array.length != DEFAULT_ARRAY_SIZE)) {
			copy = new ArrayBasedList(this.array.length);
		}
		else {
			copy = new ArrayBasedList();
		}
		for(int i = 0; i < this.count; i++) {
			copy.add(array[i]);
		}
		return copy;
	}

	@Override
	public void addSorted(I element) {
		if (this.count == this.array.length) {
			increaseArraySize();
		}
		int g = element.compareTo(array[count - 1]);
		if(g > 0) {
			add(element);
		}
		else {
			for(int i = 0; i < count; i++) {
				if(element.compareTo(array[i]) <= 0) {
					add(element, i);
					return;
				}
			}
		}
		count++;
	}

	
	
}