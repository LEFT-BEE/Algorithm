package Inerface_form;

import java.util.Arrays;
import java.util.Comparator;

class Node<E> {
	E data;
	Node<E> next;
	// 생성자 
	Node(E data){
		this.data = data;
		this.next = null; 	
	}
}

public class S_LinkList<E> implements List<E> {

		private Node<E> head; //노드의 첫부분
		private Node<E> tail;
		private int size;
		
		
		public S_LinkList(){
			this.head = null;
			this.tail = null;
			this.size = 0;			
		}
		private Node<E> search(int index){
			//잘못된 범위일시
			if(index < 0 || index >= size) {throw new IndexOutOfBoundsException();}
			Node<E> x =head;
			for(int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;			
		}
		// add종류 3가지
		public void addFirst(E value) {
			Node<E> newNode = new Node<E>(value);
			newNode.next = head;
			head = newNode;
			size++;
			
			if(tail == null) {tail = head;}
		}
		public boolean add(E value) {
			addLast(value);
			
			return true;
			
			}
		public void addLast(E value) {
			Node<E> newNode = new Node<E>(value);
			
			if(size ==0) {
				addFirst(value);
				return;
			}
			tail.next = newNode;
			tail = newNode;
			size++;
		}
		public void add(int index, E value) {
			if(index < 0 || index > size)
				{throw new IndexOutOfBoundsException();}
			
			if(index == 0) {
				addFirst(value);
				return;}
			if(index == size) {
				addLast(value);
				return;}
			
			Node<E> newNode=  new Node<E>(value);
			Node<E> prevNode = search(index-1);
			Node<E> nextNode = prevNode.next;
			
			prevNode.next = null;//왜 굳이 null를 넣을까 바로 그냥 
			prevNode.next = newNode;//prevNode.next = newNode; 하면되는거 아닌가?
			newNode.next = nextNode;
			size++;
		}
		public E remove() {
			
			Node<E> headNode = head;
			if(head == null) {throw new IndexOutOfBoundsException();}
			
			E element = headNode.data;
			Node<E> nextNode = head.next;
			head.data = null;
			head.next = null;
			
			head = nextNode;
			size--;
			
			if(size == 0) {tail = null;}
			
			return element;
		}
		public E remove(int index) {
			if(index == 0) {
				return remove();

			}
			if(head == null) {throw new IndexOutOfBoundsException();}
			
			Node<E> prevNode = search(index-1);
			Node<E> removedNode = prevNode.next;
			Node<E> nextNode = removedNode.next;
			
			E element  = removedNode.data;
			prevNode.next = nextNode;
			removedNode.data = null;
			removedNode.next = null;
			size --;
			
			return element;	
		}
		public boolean remove(Object value) {
			Node<E> prevNode = head; //
			boolean hasValue = false;
			Node<E> x = head; // removedNode
			for(; x != null; x = x.next) {
				if(value.equals(x.data)){
					hasValue = true;
					break;
				}
				prevNode = x;
			}
			if(x.equals(head)) {
				remove();
				return true;
			}
			else if(!hasValue) {return false;}
			else {
				prevNode.next = x.next;
				x.next = null;
				x.data = null;
				size --;
				return true;
			}
		}
		
		public E get(int index) {
			return search(index).data;
		}
		
		public void set(int index , E value) {
			Node<E> replace = search(index);
			replace.data = null;
			replace.data = value;			
		}
		public int indexOf(Object value) {
			int index = 0;
			for(Node<E> x = head; x.data != null; x = x.next) {
				if(value.equals(x.data)) {
					return index;
				}
				index++;
			}
			return -1;			
		}
		public boolean contains(Object value) {
			return indexOf(value) >= 0 ;
		}
		public int size() {
			return size;
		}
		public boolean is_empty() {return size==0;}
		public void clear() {
			for(Node<E> x = head; x!=null;) {
				Node<E> nextNode = x.next;
				x.data = null;
				x.next = null;
				x = nextNode;
				
			}
			head = tail = null;
			size = 0;
		}
		public Object[] toArray() {
			Object[] array = new Object[size];
			int idx = 0;
			for(Node<E> x =head; x != null;x = x.next ) {
				array[idx++] = (E)x.data;
			}
			return array;
		}
		public <T> T[] toArray(T[] a){//상위 타입을 담기 위해 generic T type을 사욜한다.
			if(a.length < size) {
				a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
				//a가 리스트의 size보다 작으면 a공간을 재할당하면서 리스트에 있던 모든 요소를 복사한다 
				//인자로 준 generic array에 담아서 이를 반환해주는 함수라고할 수 있다.
				//위 함수는 리스트를 복사하여 size에 알맞은 배열에 넣어준다.
			}
		int i = 0;
		Object[] result = a;
		
		for (Node<E> x = head; x != null; x = x.next) {
			result[i++] = x.data;
			}
			return a;
		
		}
		//배열로 만든것을 array.sort()함수로 정렬해준다.
		public void sort() {
			sort(null);
		}
		
		public void sort(Comparator<? super E> c)//이쪽은 잘 모르겟음 {
			Object[] a = this.toArray();
			Arrays.sort(a , (comparator) c);
			int i = 0;
			for (Node<E> x = head; x != null; x = x.next, i++) {
			x.data = (E) a[i];
		}
}






















































