package Inerface_form;

//<E> 는 어떤 객체타입이든 받을 수 있다. 
public interface List<E> {
	
	//리스트에서 중복을 허용하지 않을경우 중복값이 있을시 false 없을시 true를 반환
	boolean add(E value);
	
	//지정된 위치에 값 입력
	void add(int index , E value);
	
	//index위치에 있는 요소를 삭제한다 그리고 삭제한 요소를 반환한다 
	E remove(int index);
	
	//리스트에서 특정요소를 삭제한다 동일한 요소가 여러개일경우 가장 처음 발견한 요소만 삭제한다 
	//리스트에 삭제할 요소가 없거나 실패하면 false 성공하면 ture를 반환
	//java.lang.Object 클래스는 자바 api의 모든 클래스와 사용자가 정의한 모든 클래스의 최상위 클래스이다 즉 .
	// 모든 클래스들은 Object클래스로 상속받는다!! E value 와 Object value의 차이점은?
	boolean remove(Object value);
	
	//리스트에 있는 특정위치의 요소를 반환한다 
	E get(int index);
	
	//리스트에서 특정위치에 있는 요소를 새요소로 대체한다 
	void set(int index , E value);
	
	//리스트가 특정요소가 리스트에있는지찾곻 그 위치반환
	int contain(Object value);
	
	int size();
	
	boolean is_empty();
	
	public void clear();
	
}
