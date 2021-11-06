---
title: "Heap_exam 추가설명"
---

Heap은 다른 자료구조에 비하여 그 방식이 까다로운 만큼 코드 구현또한 몇몇 이해가 가지 않는 부분들이 많았다. 그 원리는 대충 이해하였지만 다시한번 코드
를 살펴보며 이해할 필요가 있다. 

기본적으로 트리(tree)란 최솟값 또는 최댓값을 빠르게 찾아내기 위해 완전 이진트리형태로 만들어진 자료구조이다. 그렇다면 트리가 무엇인가?

!["title"](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbfi34R%2FbtqV2WnM4Jj%2FMTkKbZMrJDecwvs3Wns8DK%2Fimg.png)

위와 같으 구조를 tree구조 라고 한다 위 그림을 거꾸로 보면 나무같이 생겨서 tree라고 한다. 위 그림에서 이진트리는 leaf 노드가 아닌 내부노드의 단말의 최대차수가
2로 제한된 것을 말한다. 완전 이진트리란 마지막 level을 제외한 모든 노드가 채워져있으면서 모든 노드가 왼쪽처럼 채워져 있어야 한다.

마지막으로 완전이진트리에서 한가지 조건을 더 추가하면 포화 이진트리가 된다 바로 "마지막 레벨을 제외한 모든 노드는 두개의 자식노드를 갖는다" 라는 조건이다.

이전에 구현하였던 리스트에 값을 넣었다가 빼낼려고 할 때, 우선순위가 높은 것부터 빼내려고 하면 대개 정렬을 사용한다. 하지만 원소가 들어올 때 마다 이미 리스트에있던
원소들과 일일이 비교를 해야한다 문제는 이렇게 하면 너무 비효율 적이기 때문에 좀 더 효율이 좋게 만들기 위해 "부모 노드는 자식 노드보다 우선순위가 높다" 라는 조건을 붙힌다.

즉 모든 요소들을 고려하여 우선순위를 정할 필요 없이 부모노드는 항상 자식노드보다 우선순위가 앞선다는 조건만 만족시키며 완전 이진트리 형태로 채워나가는
것이다.

!["title2"](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbXeFO2%2FbtqVTGz4Spk%2FEmiJ4rN545GnSjLddKZnT0%2Fimg.png)
즉, 이는 루트노드에서 우선순위가 가장 높은 원소가 저장되어있고 index == size 일때 가장 우선순위가 낮은 원소를 저장하고 있어 최댓값, 최솟값을 
시간복잡도 O(1) , 삽입, 삭제 연산시 부모노드가 자식노드 보다 우선순위만 높으면 되므로 트리의 깊이 만큼 비교를 한다. 따라서 시간복잡도
O(logN)가질수 있어 매우 빠르게 수행된다. 

위 이미지에서 볼 수 있지만 부모노드와 자식노드간의 관계만 신경쓰면 되기 떄문에 형제간에 우선순위는 고려되지 않는다. 

트리 구조를 구현하는 방식은 '배열'이다 물론 연결리스트로도 구현이 가능하긴 하지만 문제는 특정노드의 '검색' , '이동' 과정이 조금 더 번거롭기 때문이다. 


!["title3"](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FWDMjZ%2FbtqV8GEMhcb%2FM2Wm02OJQhSh7sdW1kSzLK%2Fimg.png)


구현의 용이함을 위해 시작 index는 1부터 시작한다. 위와 같이 트리구조를 구현하게된다면 다음과 같은 특징이 생기는데.

1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 * 2

2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 *2 +1

3. 부모 노드 인덱스 = 자식노드 인덱스 /2

위 세개의 법칙은 절대 변하지 않는다. 예를들어 index 의 왼쪽 자식노드를 찾고 싶다면 3x2를 해주면된다 반대로 index5의 부모노드를 찾고 싶다면
5/2를 해주면 된다 

```
	public Heap() {
		this(null);
	}
	
	public Heap(Comparator<? super E> comparator) {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.comparator = comparator;
	}
    
	// 생성자 Type 2 (초기 공간 할당 O)
	public Heap(int capacity) {
		this(capacity, null);
	}
	
	public Heap(int capacity, Comparator<? super E> comparator) {
		this.array = new Object[capacity];
		this.size = 0;
		this.comparator = comparator;
	}
  ```
  ### 생성자
  
  heap code의 생성자 구현 부분이다. 데이터의 수를 알고있어 capacity를 지정해주는 경우와 정렬방식 comparator를 지정해주는 방식을 조합해
  4가지 형태의 생성자가 만들어진다. 저렇게 this()안에 인자를 넣게되면 code를 줄일수 있어 간편하다.
  
  ### add() 메소드 구현 
  
  heap구조에 추가 방식은 아래 그림과 같다.
  !["title4"](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fci59jf%2FbtqWNbRsmLA%2FScwEtG3n9neieCEJBVeos0%2Fimg.png)
  
  즉 배열의 마지막 부분에 원소를 넣고 부모노드를 찾아가면서 부모노드가 삽입노드보다 작을 때까지 요소를 교환해가면서 올라간다 
  이를 sift-up(상향 선별) 이라고 불린다.
  
  Comparator로 넘겨받는 경우 compare()을 없을 경우 compareTo()를 사용하여 요소를 비교해야 한다.
  
  ### remove() 메소드 구현
  
  remove메소드는 add와 정반대로 하면된다 .
  !["title5"](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb34svU%2FbtqWx5ZYjtI%2FKaMAkGXcQOh5Qe1HIKTpPk%2Fimg.png)
  

  
  

