package data_structure;

import java.util.Arrays;
import java.util.EmptyStackException;

import Inerface_form.Stack;

public class Stack_code<E> implements Stack<E> {
	
	private static final int DEFAULT_SIZE = 10;
	private static final Object[] EMPTY_ARRAY = {};
	
	private Object[] array;
	private int size;
	
	public Stack_code() {
		this.size = 0;
		this.array = EMPTY_ARRAY;
		
	}
	public Stack_code(int capacity) {
		this.size = 0;
		this.array = new Object[capacity];
	}
	
	private void resize() {
		//Arrays메소드를 이용해서 구현한다 
		if(Arrays.equals(array, EMPTY_ARRAY)) {
			array = new Object[DEFAULT_SIZE];
			return;
		}
		int arrayCapacity = array.length;
		
		if(size == arrayCapacity) {
			int newSize = arrayCapacity *2;
			array = Arrays.copyOf(array, newSize);
			return ;
		}
		if(size<arrayCapacity/2) {
			int newSize = arrayCapacity /2;
			array = Arrays.copyOf(array, Math.max(DEFAULT_SIZE, newSize));
			return ;
		}
		
	}
	
	public E push(E item) {
		if(size== array.length) {
			resize();
		}
		array[size] = item;
		size++;
		
		return item;
	}
	 @SuppressWarnings("unchecked")
	 @Override
	public E pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		//내보내느것은 Object타입이라서 E value라고 명시를 해주어야 한다 .
		E obj = (E) array[size-1];
		array[size-1] = null;
		size--;
		resize();
		
		return obj;
	}
	 @SuppressWarnings("unchecked")
	 @Override
	public E peek() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		E obj = (E) array[size-1];
		return obj;
	}
	public int search(Object value) {
		for(int idx = size-1; idx>=0; idx--) {
			if(array[idx] == value) {
				return size - idx;
			}
		}
		return -1;
	}
	@Override
	public int size() {
		return size;
		}
	
	public void clear() {
		for(int i=0; i<size; i++) {
			array[i] = null;
		}
		size = 0;
		resize();
	}
	
	public boolean empty() {
		
		return size==0;
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		Stack_code<?> clone_stack = (Stack_code) super.clone();
		//super clone자체가 생성자 비슷한 역할이고 shallow copy를 통해 사실 살 new Stack()을 호출하는것이라
		clone_stack.array = new Object[size];
		
		System.arraycopy(array, 0, clone_stack.array, 0, size);
		return clone_stack;
	}
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length < size) {
			return (T[]) Arrays.copyOf(array, size, a.getClass()); //알아서 크기가 재할당된다
		}
		System.arraycopy(array, 0, a, 0, size);

		return a;
		
	}
	//해닫객체의 기본정렬방법ㅂ을 설정할 때는 comparable cpmparator의 경우는 특정한 경우에 임시적으로 쓸수 있게 정렬을정의
	

	

}
