package Inerface_form;

public interface Queue_interface<E> {
	
	boolean offer(E e);
	
	E poll();
	
	E peek();

}
