---
title: "Linked Listed Deque 추가설명"
---

Array queue와 Array deqeue는 생략 할 것이다 왜냐하면 그 구조자체가 Array List와 비슷한 점이 많고 c++ 이나 자바에서 LinkedListed 구조를 자주 사용
하고 구현또한 aray형식에 비해 쉽기 때문이다. 

우선 dequeue의 interface는 이전의 linkedlist queue의 interface를 사용하였는데 사용하는 함수나 기능이 비슷하기 떄문이다 dequeue(덱)은
double-ended queue의 줋임말이다 queue 나 stack은 단방향 자료구조이다 특히 singly linked list와 그 메커니즘이 비슷하다. 반대로 dequeue는 
양방향 연결리스트와 유사한 메커니즘이다 두개의 종료지점이 있다는 것 한마디로 한 방향의 삽입 삭제가 이루어 진 것에서 양 쪽 방향으로 삽입 삭제가 이루어 
질 수 있도록 구현한 것이다.(양뱡향으로 시간적으로 이득을 볼 수 있지만 그만큼 link해주어야 하기떄문에 공간적 자원으로써 손해다)

![deueu](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbsLBFe%2FbtqXSojq1Oy%2Fl5mBTneWD96MPtbUYhAB31%2Fimg.png)

또한 dequeue는 삽입 삭제가 총 12개가 있다 분류를 나누자면 아래 그림과 같다 

![list](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F2Xpu5%2FbtqP9sGfyMd%2FwkwiYorkaCZwFcNHBoytt0%2Fimg.png)

배열과 구현한 deque와의 차이점은 연결로 이루어진 연결리스트는 index로 관리하는 것이 아닌 Node 단위로 구성 된 객체로 관리하게 되어 상단 그리고 하단
부분에서 입출력과 탐색이 빠르다는 장점이 있다 하지만 array로 이루어진 deque는 중간값을 찾는데 이점이 있지만 데이터를 삽입, 삭제할 경우 배열을 변경해줘야하므로
시간적으로 손해가 생긴다.

구현자체는 쉬우므로 코드는 생략하도록 하겠다.
