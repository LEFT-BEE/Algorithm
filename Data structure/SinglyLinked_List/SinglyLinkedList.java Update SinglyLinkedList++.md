---
title : "싱글리스트 부연설명"
---
LinkedList의 경우 array_list와 큰 차이점이 있는데 바로 노트 객체를 이용한다는 것이다. ArrayList는 배열을 Obejct배열 (Object[])을 사용하여 데이터를
담아두었다면, LinkedList는 배열을 이용하는 것이 아닌 하나의 객체를 두로 그 안에 데이터와 그다음 노트를 가르키는 레퍼런스 데이터로 구성하여 
체인형태로 이어주는 것이다.


```
class Node<E> {
E data;
Node<E> next; // 다음 노드객체를 가리키는 래퍼런스 변수
Node(E data) {
this.data = data;
this.next = null;
}
}

```
기본이 되는 노트 객체이다 generic E타입으로 생성자의 인자를 받아 생성된다 안에는 data와 그다음 노드를 가리키는 next노드가 있다.
기본적인 함수들은 구현하기 쉽다. 하지만 linked_list를 array[]로 바꿔주는 toArray()메소드와 어떠한 객체를 인자로 받아 우선순위를 정의 해주어야 하는
comparator부분은 다시한번 천천히 봐야할 것 같다.

마지막으로 실행결과를 보이겠다.
```
package Inerface_form;

public class list_exam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		S_LinkList<Animal> s_list= new S_LinkList<>();
		
		System.out.println(s_list.is_empty());
		s_list.add(new Animal("강아지", 6));
		s_list.add(new Animal("고양이", 4));
		s_list.add(new Animal("금붕어", 1));
		s_list.add(new Animal("참새", 2));
		
		s_list.sort();

		for(int i = 0 ; i < s_list.size(); i++) {
			s_list.get(i).print_string();
		}	

	}
}

class Animal {
	String name;
	int age;
	
	Animal(String name, int age){
	this.name = name;
	this.age = age;
	}
	public void print_string() {
		System.out.println("이름: " + name + " 나이: " + age);
	}
	
	
	}
  
Console
----------------------
true
이름: 강아지 나이: 6
이름: 고양이 나이: 4
이름: 금붕어 나이: 1
이름: 참새 나이: 2
------------------------

``````
