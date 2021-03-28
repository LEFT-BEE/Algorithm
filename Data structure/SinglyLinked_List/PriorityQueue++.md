---
title: "우선순위 큐 추가설명"
---

사실 추가 설명이고 뭐고 이전에 구현한 heap과 거의 같은 코드이다 이렇게 만들어진 우선순위 큐는 당연하게도 최대값 , 최소값을 찾는데 
O(1)의 복잡도를 가지며 추가 그리고 삭제 메소드 호출시 트리의 h(높이)만큼 시간이 걸리므로 O(log n)의 시간 복잡도를 가지게 된다. 마지막으로 예제 코드를
살펴보고 마치도록 하겠다.

```
	private static Comparator<Student> comp = new Comparator<Student>() {
		/*
		 * 과학점수가 높은 순 
		 * 과학점수가 같을 경우 수학 점수가 높은순 
		 * 둘 다 같을 경우 이름순
		 */
		@Override
		public int compare(Student o1, Student o2) {
			if(o1.science == o2.science) {
				if(o1.math == o2.math) {
					return o1.name - o2.name;
				}
				return o2.math - o1.math;
			}
			return o2.science - o1.science;
		}
	};
  ```
  Student라는 객체의 비교를 위해 comparator객체를 정의 해주었다 Stdent의 멤버인수인 science , math , name순으로 크기비교를 하는 구조이다
  
  ```
  	static class Student implements Comparable<Student> {
 
		char name;
		int math;
		int science;
 
		public Student(char name, int math, int science) {
			this.name = name;
			this.math = math;
			this.science = science;
		}
 
		/*
		 * 수학점수가 높은 순 
		 * 수학점수가 같을 경우 과학 점수가 높은순 
		 * 둘 다 같을 경우 이름순
		 */
		@Override
		public int compareTo(Student o) {
			if (this.math == o.math) {
 
				if (this.science == o.science) {
					return this.name - o.name;
				}
				return o.science - this.science;
			}
			return o.math - this.math;
		}
 
		public String toString() {
			return name + "\t" + math + "\t" + science + "\n";
		}
	}
  ```
  Student객체를 정의하는 부분이다 객체 내에서 크기를 비교하는 Comparable을 선언해 주었는데 comparator객체와 반대로 math , science ,name순으로 
  정렬하게 된다 .
  
  ```
  	public static void main(String[] args) {
 
		PriorityQueue<Student> pq1 = new PriorityQueue<>();	// Comparable 정렬 순서
		PriorityQueue<Student> pq2 = new PriorityQueue<>(comp);	// Comparator 정렬 순서 
 
		Random rnd = new Random();
 
		char name = 'A';
		for (int i = 0; i < 10; i++) {
			int math = rnd.nextInt(10);
			int science = rnd.nextInt(10);
			
			pq1.offer(new Student(name, math, science));
			pq2.offer(new Student(name, math, science));
			name++;
		}
 
		// 힙 내부 배열의 요소 상태
		System.out.println("[pq1 내부 배열 상태]");
		for (Object val : pq1.toArray()) {
			System.out.print(val);
		}
		System.out.println();
		System.out.println();
 
		// 힙 내부 배열의 요소 상태
		System.out.println("[pq2 내부 배열 상태]");
		for (Object val : pq2.toArray()) {
			System.out.print(val);
		}
		System.out.println();
		System.out.println();
		
		
		System.out.println("[수학-과학-이름순 뽑기]");
		System.out.println("name\tmath\tscience");
		while(!pq1.isEmpty()) {
			System.out.print(pq1.poll());
		}
		System.out.println();
		System.out.println("[과학-수학-이름순 뽑기]");
		System.out.println("name\tmath\tscience");
		while(!pq2.isEmpty()) {
			System.out.print(pq2.poll());
		}
	}
  ```
