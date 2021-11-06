package data_structure;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import Inerface_form.Queue_interface;

public class PriorityQueue<E> implements  Queue_interface<E> {
	
	private Comparator<? super E> comparator; // <? super E>타입을 가지는 Comparator 객체타입
	// 이후로 인자로 주게된다.
	private static int DEFAULT_CAPACITY = 10;
	private Object[] array;
	private int size;
	
	public PriorityQueue() {
		this(null);
	}
	public PriorityQueue(Comparator<? super E> comparator) {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.comparator = comparator;
	}
	
	public PriorityQueue(int capacity) {
		this(capacity, null); //comparator자리에 null을 넣어줘야지 overloading 가능
	}
	public PriorityQueue(int capacity , Comparator<? super E> comparator) {
		this.array = new Object[capacity];
		this.size = 0;
		this.comparator = comparator;
	}
	
	private int getParent(int index) {
		return index/2;
	}
	private int getLeftchild(int index) {
		return index*2;
	}
	private int getRightchild(int index) {
		return index*2 +1;
	}
	
	private void resize(int newcapacity) {
		Object[] newarray = new Object[newcapacity];
		for(int i=0; i <= size; i++) {
			newarray[i] = array[i];
		}
		this.array = null;
		this.array = newarray;
	}
	
	public boolean offer(E value) {
		if(size+1 == array.length) {
			resize(array.length *2);
		}
		
		siftUp(size+1 , value); // 인자로 수가 들어갈 index와 값이 들어간다
		size++;
		return true;
	}
	
	public void siftUp(int index , E target) {
		if(comparator!=null) {
			siftUpcomparator(index , target , comparator);
		}
		else {
			siftUpcomparable(index , target);
		}
	}
	
	private void siftUpcomparator(int idx , E target , Comparator<? super E> comp){
		while(idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
			
			if((comp.compare(target, (E) parentVal)) >= 0){
				break;
			}
			
			array[idx] =parentVal;
			idx = parent;
		}
		array[idx] = target;
	}
	
	private void siftUpcomparable(int idx , E target) {
		
		Comparable<? super E> comp = (Comparable <?super E>) target; 
		
		while(idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
			
			if(comp.compareTo((E)parentVal) >= 0){
				break;
			}
			
			array[idx] =parentVal;
			idx = parent;
		}
		
		array[idx]  = target;
	}
	
	public E poll() {
		if(array[1] ==null) {
			return null;
		}
		return remove();
	}
	
	public E remove() {
		if(array[1] == null) {
			throw new NoSuchElementException();
		}
	
		E result = (E) array[1];
		E target = (E) array[size]; //맨 첫번째 요소와 마지막 요소 
		
		array[size] = null;
		size--;
		siftDown(1 , target);
		
		return result;
	}
	
	private void siftDown(int idx , E target) {
		if(comparator != null) {
			siftDownComparator(idx , target , comparator);
		}
	}
	
	private void siftDownComparator(int idx , E target , Comparator<? super E> comp) {
		array[idx] = null;
		
		int parent = idx;
		int child;
		
		while((child = getLeftchild(parent)) <= size) {
			int right = getRightchild(child);
			
			Object childVal = array[child];
			
			if(right <= size && comp.compare((E) childVal, (E)array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
			
			if(comp.compare(target, (E) childVal) <= 0) { //break 조건달아주기
				break;
			}
			array[parent] = childVal;
			parent = child;
		}
		
		array[parent] = target;
		
		if(array.length > DEFAULT_CAPACITY && size < array.length /4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length /2));
		}
	}
	
	public int size() {
		return this.size;
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public E peek() {
		if(array[1] == null) {	// root 요소가 null일 경우 예외 던짐
			throw new NoSuchElementException();
		}
		return (E)array[1];
	}
		
	public boolean isEmpty() {
		return size == 0;
	}
		
	public boolean contains(Object value) {
		for(int i = 1; i <= size; i++) {
			if(array[i].equals(value)) {
				return true;
			}
		}
		return false;
	}
		
	public void clear() {
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
			
		size = 0;
	}
	
@SuppressWarnings("unchecked")

	public Object[] toArray() {
		return toArray(new Object[size]);
	}
	public <T> T[] toArray(T[] a) {
		if(a.length <= size) {
			return (T[])Arrays.copyOfRange(array, 1, size+1 , a.getClass());
		}
		System.arraycopy(array, 1, a,0,size);
		return a;
	}
	

	
	
	
	
}
