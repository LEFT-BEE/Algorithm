package data_structure;

import java.util.NoSuchElementException;

import Inerface_form.Queue_interface;
import data_structure.Node;



public class list_queue<E> implements Queue_interface<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public list_queue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	
	public boolean offer(E value) {
		
		Node<E> new_node =new Node<E> (value);
		
		if(size==0) {
			head = new_node;
		}
		else {
			tail.next = new_node;
		}
		
		tail = new_node;
		size++;
		return true;
	}
	
	public E poll() {
		if(size==0) {return null;}
		E elem = head.data;
		
		Node<E> nextnode = head.next;
		head.next = null;
		head.data = null;
		 
		head = nextnode;
		size--;
		
		return elem;
	}
	public E remove() {
		E elem = poll();
		
		if(elem == null)
			throw new NoSuchElementException();
		
		return elem;
	}
	
	public E peek() {
		if(size == 0)
			return null;
		return head.data;
	}
	
	public E elemnet() {
		E elem = peek();
		if(elem == null)
			throw new NoSuchElementException();
		
		return elem;
	}
	public int size() {
		return size;
	}
	public boolean is_empty() {
		return size==0;
	}
	public boolean contains(Object value) {
		for(Node<E> x = head; x != null; x=x.next) {
			if(x.data == value)
				return true;
		}
		return false;
	}
	
	public void clear() {
		for(Node<E> x = head; x != null;) {
			Node<E> next = x.next;
			x.data = null;
			x.next = null;
			x=next;
		}
		
	}
	
	public Object clone() {
		
		// super.clone() 은 CloneNotSupportedException 예외를 처리해주어야 한다.
		try {
			@SuppressWarnings("unchecked")
			list_queue<E> clone = (list_queue<E>) super.clone();	// 새로운 큐 객체 생성 
			clone.head = null;
			clone.tail = null;
			clone.size = 0;
			
			// 내부까지 복사되는 것이 아니기 때문에 내부 데이터들을 모두 복사해준다.
			for(Node<E> x = head; x != null; x = x.next) {
				clone.offer(x.data);
			}
			return clone;
			
		} catch (CloneNotSupportedException e) {
			throw new Error(e);	// 예외처리는 여러분들이 자유롭게 구성하면 된다.
		}
	}
		
	
	
}

