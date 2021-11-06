package data_structure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class heap<E> {

	private final Comparator<? super E> comparator; 
	//객체를 정렬하고자 할때 임의로 순서를 정렬하고 싶을 때 comparator를 파라미터로 받아 설정가능한 변수 
	private static final int DEFAULT_CAPACITY = 10;
	
	private int size;
	private Object[] array;
	
	//생성자 
	public heap() {
		this(null);	
	}//앤 왜만들어 준거임? -초기 공간 할당 x
	
	public heap(Comparator<? super E> comparator) {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.comparator = comparator;
	}
	
	public heap(int capacity) {
		this(capacity ,null);
	}
	
	public heap(int capacity , Comparator<? super E> comparator) {
		this.array = new Object[capacity];
		this.size = 0;
		this.comparator =  comparator;
	}
	
	private int getParent(int index) {
		return index/2;
	}
	private int getLeftchild(int index) {
		return index *2;
	}
	private int getRightchild(int index) {
		return index*2 +1;
	}
	
	private void resize(int newcapacity) {
		Object[] newarray = new Object[newcapacity];
		
		for(int i=0; i<size; i++) {
			newarray[i] = array[i];
		}
		this.array = null;
		this.array = newarray;
	}
	//상향 선별 해준다
	public void add(E value) {
		if(size+1 == array.length){
			resize(array.length*2);	
		}
		//위로 올리는 함수 값을 
		siftUp(size+1 , value);
		size++;
	}
	private void siftUp(int idx , E target) {
		if(comparator!=null)
			siftUpComparator(idx , target , comparator);
		else {
			siftUpComparable(idx , target);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void siftUpComparator(int idx , E target , Comparator<? super E > comp) {
		//root 노드 보다 클 때까지만 탐색한다.
		//size+1 을 idx로 넘겨준다
		while(idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
			
			if(comp.compare(target , (E) parentVal) >= 0) {
				//compare는 comparator인자를 준 경우
				break;
			}
			array[idx] = parentVal;
			idx = parent;
		}
		
		array[idx] = target;
	}
	
	private void siftUpComparable(int idx,  E target) {
		//comparator 인자를 주지 않은경우 
		// target을 비교가능한 객체로 만들어 준다 
		Comparable<? super E> comp = (Comparable<? super E>) target;
		
		while(idx >1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
			
			if(comp.compareTo((E)parentVal) >= 0) {
				break;
			}
			
			array[idx] =parentVal;
			idx = parent;
		}
		array[idx] = comp;
	}
	
	public E remove() {
		if(array[1] == null) {
			throw new NoSuchElementException();
		}
		E result = (E) array[1];
		E target = (E) array[size];
		array[size] = null; //마지막 노드를 지운다
		
		siftDown(1 ,target);
		return result;
	}
	
	private void siftDown(int idx , E target) {
		if(comparator != null) {
			siftDownComparator(idx , target , comparator);
		}
		else {
			siftDownComparable(idx , target);
		}
			
	}
	
	@SuppressWarnings("uncheked")
	private void siftDownComparator(int idx, E target ,Comparator<? super E> comp) {
		array[idx] = null;
		size--;
		
		int parent = idx; // 시작할 부모노드
		int child; //변경되는 자식노드
		
		while((child = getLeftchild(parent)) <=size) {
			int right = getRightchild(parent);
			
			Object childVal = array[child];
			
			if(right <= size && comp.compare((E) childVal , (E) array[right]) > 0) {
				child = right;
				childVal = array[child];//오른쪽 왼쪽 비교해서 작은 자식노드 고르기
			}
			
			if(comp.compare(target, (E) childVal) <= 0) { //자식노드가 target보다 크면 종료
				break;
			}
			
			array[parent] = childVal;
			parent = child;
		}
		
		
	}

	private void siftDownComparable(int idx, E target) {
		Comparable<? super E> comp = (Comparable<? super E>) target;
		//
		array[idx] = null; //초기화?
		size --;
		
		int parent =idx;
		int child;
		
		while((child = getLeftchild(parent)) <=size) {
			int right = getRightchild(parent);
			Object childVal = array[child]; //일단 왼쪽값
			
			if(right <= size && ((Comparable<? super E>)childVal).compareTo((E)array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
			
			if(comp.compareTo((E) childVal) <= 0){
				break;
			}
			array[parent] = childVal;
			parent = child;	
		}
		array[parent] = comp;
		if(array.length > DEFAULT_CAPACITY && size < array.length /4) {
			resize(Math.max(DEFAULT_CAPACITY , array.length/2));
		}
	}
	
	public int size() {
		return this.size;
	}
	public E peek() {
		if(array[1] ==null) {
			throw new NoSuchElementException();
		}
		return (E)array[1]; // 첫번째 값
	}
	public boolean isEmpty() {
		return size==0;
	}
	public Object[] toArray() {
		return Arrays.copyOf(array, size+1);
	}
}
