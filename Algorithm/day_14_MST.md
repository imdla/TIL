## <mark color="#fbc956">유니온-파인드 알고리즘</mark>

### 1. 유니온-파인드 알고리즘

- 여러 노드가 존재할 때,
  - 두 노드가 같은 집합에 속해 있는지 확인하고 (**Find 연산**),
  - 다른 집합에 속해 있는 두 노드를 하나의 집합으로 묶어주는 (**Union 연산**) 알고리즘

### 2. Make-Set 연산

- 각 노드들의 대표는 자기 자신이라 가정
- `P`(Parent) 배열은 각각 노드들의 대표 노드가 어떤 노드인지 나타냄

```python
# 1번부터 n번까지의 정점이 있다고 가정
# parent 배열은 아래와 같이 초기화할 수 있음
parent = list(range(n + 1))
```

![image.png](/Algorithm/assets/mst_1.png)

### 2. Union 연산

- 노드의 관계를 이어줌
- C의 부모 노드가 A가 됨
  - `Union(x, y)` ⇒ `p[y] = x`
- 이후 트리의 랭크를 기반으로 Union할 경우, 더욱 최적화 가능

![image.png](/Algorithm/assets/mst_2.png)

- **단순 Union 연산**

  ```python
  def union(x, y):
  	x_root, y_root = find(x), find(y)

  	if x_root == y_root:
  		return

  	if x_root < y_root:
  		parent[y_root] = x_root
  	else:
  		parent[x_root] = y_root
  ```

- **rank(랭크)를 이용한 Union 연산**

  ```python
  def union(x, y):
  	x_root, y_root = find(x), find(y)

  	if x_root == y_root:
  		return

  	if rank[x_root] > rank[y_root]:
  		parent[y_root] = x_root
  	elif rank[x_root] < rank[y_root]:
  		parent[x_root] = y_root
  	else:
  		parent[x_root] = y_root
  		rank[y_root] += 1 # 랭크가 같은 것끼리 합한 후에 랭크 + 1 해줌
  ```

### 4. Find 연산

- D의 부모 노드는 B이며, B의 부모 노드는 A를 재귀적으로 찾아가능 과정
- 이후, **Path Compression(경로 압축)** 과정을 거쳐 더욱 최적화 가능
  ![image.png](/Algorithm/assets/mst_3.png)
- `Find`의 결과가 같다면 `Union` 할 수 없음

  ![image.png](/Algorithm/assets/mst_4.png)

- **while 문을 이용한 find 연산**

  ```python
  def find(x):
  	while x != parent[x]:
  		x = parent[x]

  	return x
  ```

- **경로압축(path compression)을 이용한 find 연산 개선**

  ```python
  def find(x):
  	if x != parent[x]:
  		parent[x] = find(parent[x])

  	return parent[x]
  ```

---

## <mark color="#fbc956">최소신장트리(MST, Minimum Spanning Tree)</mark>

### 1. 최소신장트리

- 그래프에서 트리를 뽑을 때, 그 간선의 가중치의 합산이 최소가 되게끔 뽑은 트리
- 그래프의 연결 관계를 유지하는 최소 비용 그래프
- 크루스칼 알고리즘과 프림 알고리즘으로 구현 가능

![image.png](/Algorithm/assets/mst_5.png)

### 2. 크루스칼 알고리즘

- 분리집합 알고리즘을 응용한 최소신장트리 구현
- 시간 복잡도 : `O(ElongE)`
  - E : 간선의 개수

1. **간선의 가중치가 낮은 순서대로 정렬**

   ![image.png](/Algorithm/assets/mst_6.png)

2. 사이클이 생기지 않도록 하나씩 뽑되, 트리를 구성할 수 있게 되는 순간, 중단함

   - 그래서 노드가 4개라면, 3개의 간선까지만 뽑으면 됨

   ![image.png](/Algorithm/assets/mst_7.png)

- **사이클 판별 알고리즘**
  - 유니온-파인드(Union-Find) 자료구조의 연산 이용 시 간편함
  ```
  [RULE]
  1. Find 연산으로 부모가 같은지 살핀 후, (= 같은 집합인지 살핀 후)
  2. 만약, 다르다면 Union 연산으로 이어줌
  3. 같다면, Union 할 수 없음 (같은 집합에 속한 노드를 연결하면 사이클 발생)
  ```

### 3. 크루스칼 알고리즘 문제

```python
import sys
input = sys.stdin.readline

def find(x):
	if x != parent[x]:
		parent[x] = find(parent[x]) # path compression

	return parent[x]

def union(x, y):
	x_root, y_root = find(x), find(y)

	if x_root == y_root:
		return False

	# union by rank
	if rank[x_root] > rank[y_root]:
		parent[y_root] = x_root
	elif rank[x_root] < rank[y_root]:
		parent[x_root] = y_root
	else:
		parent[x_root] = y_root
		rank[y_root] += 1

	return True

n, m = map(int, input().split())
parent = list(range(n + 1))
rank = [0] * (n + 1)
edges = []
total, counts = 0, 0

for _ in range(m):
	a, b, c = map(int, input().split()) # 정점 a, 정점 b, 가중치 c
	edges.append((c, a, b)) # 가중치를 먼저 넣어, 가중치 기준으로 정렬

edges.sort() # 크루스칼 알고리즘을 위한 가중치 기준 정렬

for c, a, b in edges:
	if union(a, b): # 간선을 이으면(같은 집합에 넣으면)
		total += c # 가중치 더하기
		counts += 1

		if counts == n - 1:
			break # 트리의 특성상, 간선의 개수는 (정점 - 1)이므로, 이에 도달할 경우 종료함

print(total)
```

### 4. 프림 알고리즘

- 그리디 기법을 응용한 최소신장트리의 구현
  - **그리디(탐욕) 기법**
    - 현재 상황에서 최선을 선택하다 보면 전체의 최선이 되는 것
    - 부분 최적 결과, 전체 최적이 됨

> **프림 알고리즘 구현**

1. **그래프와 간선의 정보 표현**

   - 각 노드까지 최단 거리 도달을 나타내는 dist 배열을 모두 무한대로 초기화함

   ![image.png](/Algorithm/assets/mst_8.png)

2. 시작점은 어디에서부터 해도 상관없음

   - 시작점의 dist 배열의 값을 0으로 만들면서 방문 체크 후, 알고리즘 로직 시작함

   ![image.png](/Algorithm/assets/mst_9.png)

3. 시작점과 이어진 노드의 간선들을 탐색

   - 방문하지 않은 노드에 대해 만약, 더 작은 값이 가중치로 갈 수 있다면 해당 루트를 갱신함

   ![image.png](/Algorithm/assets/mst_10.png)

4. dist 배열에서 방문하지 않았고, 가장 dist 값이 가장 작은 점을 1개 선택해 새로운 시작점으로 설정하고, 앞의 과정을 반복함

   ![image.png](/Algorithm/assets/mst_11.png)

   - 1번을 새로운 시작점으로 하여 1번 노드에서 갈 수 있고, 방문하지 않은 노드들의 거리 정보를 모두 갱신함
     ![image.png](/Algorithm/assets/mst_12.png)

5. 더 이상 방문할 수 없는 노드가 없다면 dist 배열의 합산이 MST가 됨

   ![image.png](/Algorithm/assets/mst_13.png)

- dist 배열에서 가장 작은 값을 찾는 과정을 최소힙으로 대체할 경우, 프림 알고리즘을 효율화할 수 있음
