package Inerface_form;

public interface Stack<E> {
	
	E push(E item);
	
	E pop();
	
	E peek();
	
	int search(Object o);
	
	int size();
	
	void clear();
	
	boolean empty();
	
}
