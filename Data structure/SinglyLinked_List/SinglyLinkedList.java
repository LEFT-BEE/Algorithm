package Inerface_form;

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
		public Node<E> search(int index){
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
			newNode = tail;
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
		
		
}






















































