---
title: "DFS"
---

그래프 탐색이란 하나의 정점으로부터 시작하여 차례대로 모든 정점들을 한 번씩 방문하는 것을 말한다 그중 깊이 우선 탐색(DFS)을 알아보자

루트 노드에서 시작하여 다음 분기로 넘어가기 전에 해당 분기를 완벽하게 탐색하는 방법이다 ex)미로를 탐색할 때 한방향으로 갈 수 있을 때까지 계속 가다가
더 이상 갈 수 없게 되면 다시 가장 가까운 갈림길로 돌아와서 이곳으로부터 다른 방향으로 다시 탐색을 진행하는 방법과 유사하다 

즉 넓게(widely)가 아닌 깊게(Depthly)하게 탐색을 진행한다 특히 모든 노드를 방문하고자 하는 경우 사용하는데 속도는 넓이 우선 탐색(BFS)보다는 느리다.

다음은 백준2178번의 DFS를 활용하는 문제이다 비록 DFS를 사용하면 시간초과로 틀리게 되지만 DFS를 처음 공부하는 입장에서 좋은 문제라고 생각하여 
문제를 풀었다
```
#include <stdio.h>
using namespace std;

/*

4 6
101111
101010
101111
111011

*/

int N, M;
char Map[101][101]; // 맵
int Visit[101][101]; // 방문 표시
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };
int Min = 10001;

void DFS(int x, int y, int depth)
{

	if (x < 0 || y < 0 || x >= N || y >= M) return;   //맵의 범위를 벗어 날때
	if (x == N - 1 && y == M - 1)    //도착할때
	{
		if (depth < Min)
			Min = depth;
		return;
	}

	for (int i = 0; i < 4; i++)
	{
		int Next_x = x + dx[i];
		int Next_y = y + dy[i];
		if (Visit[Next_x][Next_y] == 0 && Map[Next_x][Next_y] == '1')
		{
			Visit[Next_x][Next_y] = 1;//한번간 길로 다시 돌아오지 못하게
			DFS(Next_x, Next_y, depth + 1);
			Visit[Next_x][Next_y] = 0;//다른 경로로는 들어갈 수  
		}
	}

}

int main(void)
{
	scanf("%d %d", &N, &M);    //N세로 M가로

	for (int i = 0; i < N; i++)
	{
		scanf("%s", Map[i]);
	}

	DFS(0, 0, 1);
	printf("%d\n", Min);
}

```
0,0 노드에서 끝 노드 Map[N][M]까지 가는데 있어 모든 방법을 탐색한다. 탐색하는 과정속에서 DEPTH를 늘려나가 끝에 도달하게 되면 이전의 최소 
depth였던 min과의 비교를 통해 탐색하는데 있어 최소길이를 구할 수 있다 

특징으로는 갈림길에 도달할 경우 visit을 1로 바꿔 한쪽의 갈림길이 모두 지나갈때 까지 다른 쪽으로 들어가지 못하게 하였고 모든 갈림길에 도달한 경우 
visit을 0으로 다시 바꾸어 들어갈 수 있게 만들었다.
