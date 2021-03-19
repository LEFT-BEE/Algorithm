package data_structure;

import java.util.Arrays;

import Inerface_form.Queue_interface;

public class queue<E> implements Queue_interface<E> {
	
	private static final int DEFAUT_CAPACITY = 64;
	private Object[] array;
	
	private int size;
	
	private int front;
	private int rear;
	//시작 인덱스를 가리키는 변수(빈공간) , 마지막 요소의 인덱스를 가리키는 변수 
	
	public queue() {
		this.array = new Object[DEFAUT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
		
	}
	public queue(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	private void resize(int new_capacity) {
		int array_capacity = array.length;
		//size는 문자의 크기 length를 해야지 capacity가 나온다.... 
		Object[] new_array = new Object[new_capacity];
		
		//i는 new array index j는 original array		
		//index 요소개수 만큼 새배열에 값 복사
		
		for(int i  =1 , j = front +1 ; i <=size ; i++,j++) {
			new_array[i] = array[j%array_capacity];
			//rear가 front 보다 작은 수일지도 모르니 %를 붙혀줘서 이용한다 
		}
		this.array = null;
		this.array = new_array;
		
		front = 0;
		rear = size;
		//어차피 용적을 인자로 받기 때문에 이전처럼 3가지 경우를 생각하지 않아도됨
	}
	
	public boolean offer(E item) {
		if((rear+1)%array.length == front)
			resize(array.length *2);
		
		rear = (rear+1) % array.length;
		array[rear] = item;
		size++;
		
		return true;
	}
	
	public E poll() {//요소 삭제
		 if(size == 0)
			 return null;
		 
		 front = (front+1) % array.length;
		 E item = (E) array[front];
		 array[front] = null;
		 size--;
		 
		 if(array.length > DEFAUT_CAPACITY&& size < (array.length / 4)) {
			// 아무리 작아도 최소용적 미만으로 줄이지는 않도록 한다.
			resize(Math.max(DEFAUT_CAPACITY, array.length / 2));
			}

		return item;
		 
	}
	public E peek() {
		if(size == 0)
			return null;
		
		@SuppressWarnings("unchecked")
		E item = (E) array[(front+1)%array.length];
		return item;
		
	}
	public int size() {
		return size;
		}
	public boolean isEmpty() {
		return size==0;
	}
	public boolean contains(Object value) {
		int start = (front+1) % array.length;
		for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
			if(array[idx].equals(value)) {
			return true;
			
			}
		}

		return false;
	}
	public void empty() {
		for(int i =0; i < array.length; i++) {
			array[i] = null;
		}
		front = rear = size = 0;
	}

	public Object[] toArray() {
		return toArray(new Object[size]);
		}
		@SuppressWarnings("unchecked")
		public <T> T[] toArray(T[] a) {
		final T[] res;
		// 들어오는 배열의 길이가 큐의 요소 개수보다 작은경우
		if(a.length < size) {
		/*
		* front가 rear보다 뒤에 있을 경우 (또는 요소가 없을 경우 f==r)
		* ______________________
		* | | | | | | | |
		* ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		* f r
		*/
		if(front <= rear) {
		return (T[]) Arrays.copyOfRange(array, front + 1, rear + 1, a.getClass());
		}
		/*
		* front가 rear보다 앞에 있을 경우
		* ______________________
		* | | | | | | | |
		* ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		* r f
		*/
		res = (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
		int rearlength = array.length - 1 - front; // 뒷 부분의 요소 개수
		// 뒷 부분 복사
		if(rearlength > 0) {
		System.arraycopy(array, front + 1, res, 0, rearlength);
		}
		// 앞 부분 복사
		System.arraycopy(array, 0, res, rearlength, rear + 1);
		return res;
		}
		/*
		* front가 rear보다 뒤에 있을 경우 (또는 요소가 없을 경우 f==r)
		* ______________________
		* | | | | | | | |
		* ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		* f r
		*/
		if(front <= rear) {
		System.arraycopy(array, front + 1, a, 0, size);
		}
		/*
		* front가 rear보다 앞에 있을 경우
		* ______________________
		* | | | | | | | |
		* ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		* r f
		*/
		else {
		int rearlength = array.length - 1 - front; // 뒷 부분의 요소 개수
		// 뒷 부분 복사
		if(rearlength > 0) {
		System.arraycopy(array, front + 1, a, 0, rearlength);
		}
		// 앞 부분 복사
		System.arraycopy(array, 0, a, rearlength, rear + 1);
		}
		return a;
		}

}



























