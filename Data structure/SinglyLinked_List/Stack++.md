---
title: "Stack 실제 구현 및 부가설명"
---

기본적으로 java에서 제공하는 Stack 라이브러리는 vector클래스를 상속받아 구현하고 있다, 즉 Stack 클래스에서도 vector클래스의 메소드들을 사용할 수 있게 되어
있는데 vector클래스는 기본적으로 arraylist와 그 구조가 거의 같다 stack클래스는 내부에서 최상위 타입배열인 Object[]배열을 사용하여 데이터 들을 관리 하고 있다.

stack의 가장 큰 특징은 후입선출 즉, 늦게 입력된 것이 가장먼저 출력된다는 점이다 ex(쌓아 놓은 블럭을 다시 꺼낼때 위에서 부터 꺼내야 한다는 것과 비슷하다)
이러한 구조는 컴퓨터에서는 '뒤로가기' , '실행취소' , stack memory등에서 사용된다 스택의 장점은 직전의 데이터를 빠르게 갖고 올수 있댜는 것이다. 

stack을 구현하기전에 모든 자료구조는 동적할당을 전제로한다는 것을 알아야한다 즉 자동적으로 그 capacity를 늘려주어야 한다는 것이다 기본적인 stack의
형태구현은 interface에서 정의한데로 구현하면 된다. 특별한 점을 살펴보도록 하겠다.

먼저 위에서 말했던 capcity를 최적화 하는 방법을 보도록하자 만약 데이터는 적은데 용적(capcaity)가 크면 메모리가 낭비되고 반대로 용적은 적은데 데이터가
많으면 데이터들을 보관 할 수 없게 되는 상황이된다 이때 외부에서 간섭하면 오류가 생길수 도 있으므로 private로 접근을 제한하였다


### resize()
-------------------------------------------------
```
private void resize() {
		
	// 빈 배열일 경우 (capacity is 0)
	if(Arrays.equals(array, EMPTY_ARRAY)) {
		array = new Object[DEFAULT_CAPACITY];
		return;
	}
		
	int arrayCapacity = array.length;	// 현재 용적 크기 
		
	// 용적이 가득 찰 경우
	if(size == arrayCapacity) {
		int newSize = arrayCapacity * 2;
		
		// 배열 복사
		array = Arrays.copyOf(array, newSize);
		return;
	}
		
	// 용적의 절반 미만으로 요소가 차지하고 있을 경우
	if(size < (arrayCapacity / 2)) {
			
		int newCapacity = (arrayCapacity / 2);
			
		// 배열 복사
		array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
		return;
	}
}
```
경우의 수는 3가지로 첫번째는 용적이 0인상태 즉 array가 EMPTY_ARRAY인 경우이다 이때 새로 array의 용적을 할당하기 위해 최소 용적으로 설정해 두었던
DEFAULT_CAPAITY의 크기 만큼 배열을 생성해 주고 메소드를 종료한다

두번째는 데이터가 꽉찬 경우인데 이때 새롭게 배열을 생성해준다 그 size는 이전 용량의 2배를 해준다  이렇게 생성한 배열을 이전의 배열에 있던 요소들을
Arrays.copyOf구문으로 복사해주면 된다

마지막으로 size가 용적에 절반에 못미칠때 메모리낭비가 되므로 그 절반값 or DEFAULT_CAPACITY의 최소값을 size로 주어 다시 배열을 할당한다.

### clone()
----------------------------
```
public Object clone() throws CloneNotSupportedException {
// 새로운 스택 객체 생성
Stack<?> cloneStack = (Stack<?>) super.clone();
// 새로운 스택의 배열도 생성해주어야 함(내부 객체는 깊은 복사가 되지 않기 때문)
cloneStack.array = new Object[size];
// 현재 배열을 새로운 스택의 배열에 값을 복사함
System.arraycopy(array, 0, cloneStack.array, 0, size);
return cloneStack;
}
```
일반적으로 '='를 사용하게되면 얕은복사가 일어나 clone으로 만든 배열을 수정하면 original 배열또한 수정이 된다. 따라서 깊은복사를 하기위한 함수를 
만들어준다. 사실상 자바활용 능력이 부족하여 잘 이해가 되질 않지만 일단 코드라도 가져왔다. 설명이 있자면 uper.clone()자체가 생성자 비슷한 역할이고 shallow copy
를 통해 사실상 new Stack() 를 호출하는 것이라 제대로 완벽하게 복제하려면 clone한 스택의 array또한 생성하여 해당 배열에 copy를 해줘야 한다.




### Stack exam

```
 package data_structure;

public class stack_exam {

	public static void main(String[] args) {
		Stack_code<Student> stack = new Stack_code<>();
		stack.push(new Student("라", 92));
		stack.push(new Student("도", 72));
		stack.push(new Student("파", 98));
		stack.push(new Student("미", 51));
		for(Object a : stack.toArray()) {
		System.out.println(a);
		}
	}
}


		class Student {
		String name;
		int score;
		
		Student(String name, int score){
		this.name = name;
		this.score = score;
		}
		public String toString() {
		return "이름 : " + name + "\t성적 : " + score;
		}
}
```

```
실행결과
-------------------------

이름 : 라	성적 : 92
이름 : 도	성적 : 72
이름 : 파	성적 : 98
이름 : 미	성적 : 51
----------------------
```



