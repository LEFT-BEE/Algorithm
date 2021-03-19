---
title:"QUEUE 예시 및 보충설명"
---

## QUEUE

이번에 구현할 queue는 기본적으로 Object[] 배열을 기본으로 한다 이전에 구현하였던 스택(Stack) 구조는 후입선출을 구현하였다면
queue는 그 반대인 선입선출(先入先出)을 기본으로한다 즉 먼저들어온 요소가 먼저 나간다는 것이다. ex)친숙한 줄서기 

이러한 queue는 자바에서 ArrayDequeue , LinkedList , PrioirtyQueue가 있다 보통은 queue구조는 자바에서 

```
Queue<Integer> q = new LinkedList<>();
```

로 사용된다 하지만 상황에 따라 ArrayDequeue처럼 내부적으로 배열을 사용하여 구현하여 사용하고 있는 큐 자료구조도 있다 따라서 이번에는
배열을 사용하여 구현하고자 한다. 기본적으로는 배열을 사용하여 구현되는 자료구조 클래스는 내부에서 최상위 타입 배열인 Object[]배열을
사용하여 데이터 들을 관리하고 있다.

![queue](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb0BKhB%2FbtqPgIptH8R%2FlwGmphhr9h0KqBqAeo2mzK%2Fimg.png)

이러한 그림에서 index 0부터 점점 채워넣어 poll() 메소드를 통해 맨앞에 요소부터 반환된다. 하지만 이러한 직선 array는 문제가 생긴다.
큐의 삭제가 계속된다면 요소들이 점점 뒤로 치우치게 된다는 것인데 줄을 스고 있는데 앞에 사람이 빠졌는데도 불구하고 그 뒤에 있는 사람들이 움직이
질 않는다면 줄이 멈추게 된다는 것과 유사하다 따라서 queue구조에는 이러한 문제점을 해결하는데 있어 앞의 빈자리에 새로운 요소를 채워 넣는 것이다 
즉 index = 0가 무조건 적으로 앞을 의미하는 것이 아니라 'front , 'rear(뒤)'를 임의적으로 계속 변경하여 마치 배열이 원형으로 사용하는
것 처럼 만든다.

![cype_queue](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F9Cen6%2FbtqPensVRMk%2FchHfdFTxBiK9fNQymmd1wk%2Fimg.png)

이렇게 요소를 삭제하면 front+1의 index 요소가 반환되며 요소를 추가하면 raer에 추가되게된다. 이렇게 채워넣다가 더이상 빈자리가 
없을 경우 배열의 크기를 늘려주면된다 resize()함수를 통해.. 그렇다면 왜 front는 비워놓고 front+1을 첫번째 요소를 넣는 것일까? 

그것은 front와 bear가 같은 index를 가르키면 큐가 비어있는 상황을 만들기 위해서이다 이러한 구조를 circular queue라고 한다. 구현자체는
아래파일에 있으므로 사용에제를 보도록한다.

## Queue_exam

```
package data_structure;

public class queue_exam {

	public static void main(String[] args) {
		queue<Student> q = new queue<>();
		q.offer(new Student("가돌이", 23));
		q.offer(new Student("나순이", 10));
		q.offer(new Student("다돌이", 30));
		q.offer(new Student("라순이", 51));
		for (Object a : q.toArray()) {
			System.out.println(a);
		}
	}
}

실행결과
----------------------
이름 : 가돌이	성적 : 23
이름 : 나순이	성적 : 10
이름 : 다돌이	성적 : 30
이름 : 라순이	성적 : 51
----------------------

```


