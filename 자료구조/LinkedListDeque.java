package data_structure;

import java.util.NoSuchElementException;

import Inerface_form.Queue_interface;
import data_structure.Node;

public class LinkedlistDeque<E> implements Queue_interface<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public LinkedlistDeque() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public boolean offerFirst(E value) {
		Node<E> newnode = new Node<E>(value);
		newnode.next = head;
		
		if(head != null) {
			head.prev = newnode;
		}
		
		head = newnode;
		size++;
		
		if(head.next==null) {
			tail = head;
		}
		return true;
	}
	
	public boolean offer(E value) {
		return offerLast(value);
	}
	
	public boolean offerLast(E value) {
		if(size==0) {
			return offerFirst(value);
		}
		Node<E> newNode = new Node<E>(value);
		newNode.prev = tail;
		tail.next = newNode;
		
		tail = newNode;
		size++;
		
		return true;
	}
	public E poll() {
		return pollFirst();
	}
	public E pollFirst() {
		
		E elem = head.data;
		
		Node<E> nextNode = head.next;
		head.data=  null;
		head.next = null;
		if(head.next != null) {
			nextNode.prev = null;
		}
		
		head = null; // 굳이 해줘야 하는가?
		head = nextNode;
		size--;
		
		if(size==0)
			tail = null;
		
		return elem;	
	}
	public E remove() {
		return poll();
	}
	public E removeFirst() {
		E elem = poll();
		if(elem == null) {
			throw new NoSuchElementException();
		}
		return elem;
	}
	
	public E pollLast() {
		if(size==0)
			return poll();
		
		E elem = tail.data;
		Node<E> prevNode = tail.prev;
		
		tail.data = null;
		tail.prev =null;
		if(prevNode!= null)
			prevNode.next =null;
		
		tail = null;
		tail = prevNode;
		size--;
		
		if(size==0){
			head = null;
		}
		return elem;
		
	}
	public E removeLast() {
		E elem = pollLast();
		if(elem == null)
			throw new NoSuchElementException();
		
		return elem;
	}
	
	@Override // 개발자의 실수를 잡아준다. 
	public E peek() {
		return peekFirst();
	}
	public E peekFirst() {
		if(size==0)
			return null;
		
		return head.data;
	}
	public E peekLast() {
		if(size==0)
			return null;
		
		return tail.data;
	}
	
	
	public E element() {
		return getFirst();
	}
		
	public E getFirst() {
		E item = peek();
			
		// 앞의 원소 null 이라면(size = 0) 예외를 던진다. 
		if(item == null) {
			throw new NoSuchElementException();
		}
		return item;	
	}
	public E getLast() {
		E item = tail.data;
		if(item == null)
			throw new NoSuchElementException();
		return item;
	}
	public int size() {
		return size;
	}
	public boolean is_empty() {
		return size==0;
	}
	
	public boolean contains(Object value) {
		for(Node<E> x = head; x!=null;) {
			x = x.next;
			if(x.data == value)
				return true;
		}
		return false;
	}
	
	public Object[] toArray() {
		Object[] array = new Object[size]; //size만큼의 배열선언
		int idx=0;
		for(Node<E> x = head; x!=null;x=x.next) {
			array[idx++] = (E) x.data;
		}
		return array;
	}
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {//상위객체에 대해서도 데이터를 담을 수 있도록 별도의 제너릭 메소드를 구성
		if(a.length <size) {
			//대충 array.newinstance - 그 객체에 맞는 배열을 생성해준다는 의미
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		int i = 0;
		Object[] result = a;
		for(Node<E> x = head; x!=null; x= x.next) {
			result[i++] = x.data; // T객체라서 어떤 타입이든지 괜찬은건가?
		}
		return a;
	}
	
	
	

}
