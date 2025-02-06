## <mark color="#fbc956">그래프 완전 탐색</mark>

- 그래프의 모든 정점을 **중복 없이 모두 방문**

- **조건**

  1. 그래프의 모든 정점 방문
  2. 한 정점을 두 번 이상 방문 불가 (중복 방문 허용 X)

- **로직**

  ```
  1. 임의의 정점 방문
  2. 방문한 정점 다시 방문하지 않도록 기록지에 표시
  3. 해당 정점에서 갈 수 있는 모든 정점 탐색
  4. 탐색한 정점들 방문 예정지에 담음
  5. 방문 예정지에서 정점을 하나 뽑아서 방문

  위 과정을 방문 예정지가 빌 때까지 반복
  (더 이상 갈 수 있는 곳이 없음)
  ```

  - **방문 예정지 규칙으로 그래프 탐색의 분류**
    - 스택(재귀 함수의 시스템 스택)으로 활용 → **깊이 우선 탐색(DFS)**
    - 큐로 활용 → **너비 우선 탐색(BFS)**

## <mark color="#fbc956">깊이 우선 탐색 (Depth First Search, DFS)</mark>

- 방문 예정지로 스택을 사용하는 완전 탐색 알고리즘
- 특정 정점에서 하나의 경로를 끝까지 우선해 깊게 탐색
- **가장 최근에 탐색된 정점**을 우선적으로 방문
  - 방문 궤적이 경로를 이루며 탐색
- 어떤 정점에서 다른 정점까지 갈 수 있는 모든 경로(경로의 수) 탐색에 효과적

### 1. 스택을 활용한 깊이 우선 탐색

- **인접 행렬 + 스택**

  ```python
  V, E = map(int, input().split())

  # 그래프 구조화

  # 1. 빈 판 만들기
  adj_matrix = [[0] * (V+1) for _ in range(V+1)]

  # 2. 간선 정보 입력받기
  for _ in range(E):
  	s, e = map(int, input().split())
  	adj_matrix[s][e] = 1
  	adj_matrix[e][s] = 1

  ###################################################

  # 세팅

  # 방문 예정지 (스택)
  stack = [1]
  # 방문 기록지 (집합)
  visited = set()
  # 궤적을 담아줄 리스트
  trail = []

  ####################################################

  # 그래프 탐색

  # 스택이 빌 때까지
  while stack:
  	cur = stack.pop()
  	if cur not in visited:
  		trail.append(cur)
  	visited.add(cur)

  	for nxt in range(V+1):
  		if adj_matrix[cur][nxt] and nxt not in visited:
  			stack.append(nxt)

  print(trail)
  ```

- **인접 리스트 + 스택**

  ```python
  V, E = map(int, input().split())

  # 그래프 구조화

  # 1. 빈 판 만들기
  adj_lst =[[] for _ in range(V+1)]

  # 2. 간선 정보 입력받기
  for _ in range(E):
  	s, e = map(int, input().split())
  	adj_lst[s].append(e)
  	adj_lst[e].append(s)

  ############################################

  # 세팅

  # 방문 예정지 (스택)
  stack = [1]
  # 방문 기록지 (집합)
  visited = set()
  # 궤적을 담아줄 리스트
  trail = []

  #############################################

  # 그래프 탐색

  # 스택이 빌 때까지
  while stack:
  	# 방문과
  	cur = stack.pop()
  	if cur not in visited:
  		trail.append(cur)
  	visited.add(cur)

  	# 탐색 반복
  	for nxt in adj_lst[cur]:
  		if nxt not in visited:
  			stack.append(nxt)

  print(trail)

  ```

### 2. 재귀를 활용한 깊이 우선 탐색

- **인접 행렬 + 재귀**

  ```python
  V, E = map(int, input().split())

  # 그래프 구조화

  # 1. 빈 판 만들기
  adj_matrix = [[0] * (V+1) for _ in range(V+1)]

  # 2. 간선 정보 입력받기
  for _ in range(E):
  	s, e = map(int, input().split())
  	adj_matrix[s][e] = 1
  	adj_matrix[e][s] = 1

  ####################################################

  # 세팅

  visited = set()
  trail = []

  ###################################################

  # 그래프 탐색
  def DFS(cur):
  	if cur not in visited:
  		trail.append(cur)
  	visited.add(cur)

  	for nxt in range(V+1):
  		if adj_matrix[cur][nxt] and nxt not in visited:
  			DFS(nxt)

  DFS(1)

  print(trail)
  ```

- **인접 리스트 + 재귀**

  ```python
  from collections import deque

  V, E = map(int, input().split())

  # 그래프 구조화

  # 1. 빈 판 만들기
  adj_lst = [[] for _ in range(V+1)]

  # 2. 간선 정보 입력받기
  for _ in range(E):
  	s, e = map(int, input().split())
  	adj_lst[s].append(e)
  	adj_lst[e].append(s)

  #######################################

  # 세팅

  visited = set()
  trail = []

  #######################################

  # 그래프 탐색
  def DFS(cur):
  	if cur not in visited:
  		trail.append(cur)
  	visited.add(cur)

  	for nxt in adj_lst[cur]:
  		if nxt not in visited:
  			DFS(nxt)

  DFS(1)

  print(trail)
  ```

## <mark color="#fbc956">너비 우선 탐색 (Breath First Search, BFS)</mark>

- 방문 예정지로 큐를 사용하는 그래프 완전 탐색 알고리즘
- **가장 먼저 탐색한 정점**을 우선적으로 방문
  - 특정 정점에서 **인접한 정점** 우선 탐색
- 어떤 정점에서 다른 정점까지의 거리를 계산하는데 효과적 (최단 거리)

### 1. 큐를 활용한 너비 우선 탐색

- **인접 행렬 + 큐**

  ```python
  from collections import deque

  V, E = map(int, input().split())

  # 그래프 구조화

  # 1. 빈 판 만들기
  adj_matrix = [[0] * (V+1) for _ in range(V+1)]

  # 2. 간선 정보 만들기
  for _ in range(E):
  	s, e = map(int, input().split())
  	adj_matrix[s][e] = 1
  	asj_matrix[e][s] = 1

  ################################################

  # 세팅

  queue = deque([1])
  visited = set()
  trail = []

  ################################################

  # 그래프 탐색
  while queue:
  	cur = queue.popleft()
  	if cur not in visited:
  		trail.append(cur)
  	visited.add(cur)

  	for nxt in range(V+1):
  		if adj_matrix[cur][nxt] and nxt not in visited:
  			queue.append(nxt)

  print(trail)
  ```

- **인접 리스트 + 큐**

  ```python
  from collections import deque

  V, E = map(int, input().split())

  # 그래프 구조화

  # 1. 빈 판 만들기
  adj_lst = [[] for _ in range(V+1)]

  # 2. 간선 정보 입력받기
  for _ in range(E):
  	s, e = map(int, input().split())
  	adj_lst[s].append(e)
  	adj_lst[e].append(s)

  ###############################################

  # 세팅
  queue = deque([1])
  visited = set()
  trail = []

  # 그래프 탐색
  while queue:
  	cur = queue.popleft()
  	if cur not in visited:
  		trail.append(cur)
  	visited.add(cur)

  	for nxt in adj_lst[cur]:
  		if nxt not in visited:
  			queue.append(nxt)

  print(trail)
  ```

## <mark color="#fbc956">비교</mark>

- **시간 복잡도**

  - 인접 행렬 : O(V\*\*2)
  - 인접 리스트 : O(V+E)

- **경로의 특징 - 깊이 우선 탐색(DFS)**

  - 경로의 형태를 그리며 탐색
  - 특정 정점에서 다른 정점까지 갈 수 있는 경로의 수를 계산하는데 활용
    (순열 / 조합의 직접 구)

- **최단 거리 - 너비 우선 탐색(BFS)**
  - 출발점 중심으로 동심원 그리며 탐색
  - 카운팅 변수를 추가해 어떤 정점에서 다른 정점까지 최단거리 계산 가능
  - 가중치가 없는 그래프에서 최단 거리 알고리즘
