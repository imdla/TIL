## <mark color="#fbc956">2차원 리스트</mark>

### 1. 2차원 리스트

- **행(row)** : 행
- **열(column)** : 세로축

```python
matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]

print(matrix[2][0])
# 8
```

### 2. 순회와 전치

- **2차원 리스트의 순회**
  - 2차원 리스트의 데이터를 모두 한 번씩 확인
  - 순회 방식에 따라 행, 열 우선순회로 나뉘며 2중 for 반복문을 활용해 구현 가능

1. **행 우선 순회**

   ```python
   matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]
   trails = []

   for r in range(3):
   	for c in range(3):
   		trails.append(matrix[r][c])

   print(trails)
   ```

   - **행 우선 순회 및 옅은 역순**

   ```python
   matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]
   trails = []

   for r in range(3):
   	for c in range(2, -1, -1):
   		trails.append(matrix[r][c])

   print(trails)
   ```

   - **행 우선 순회 및 옅은 지그재그**

   ```python
   matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]
   trails = []

   for r in range(3):
   	if r % 2 == 0:
   		for c in range(3):
   			trails.append(matrix[r][c])
   	elif r % 2 == 1:
   		for c in range(2, -1, -1):
   			trails.append(matrix[r][c])

   print(trails)
   ```

2. **열 우선순회**

   ```python
   matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]
   trails = []

   for c in range(3):
   	for r in range(3):
   		trails.append(matrix[r][c])

   print(trails)
   ```

- **2차원 리스트의 전치**
  - 2차원 리스트의 행과 열을 뒤집는 것
  - 유방향 그래프의 인접 행렬을 전치하면 간선의 방향이 모두 반대가 됨

1. **반복문을 이용한 전치**

   ```python
   matrix = [[3, 7, 9], [4, 2, 6], [8, 1, 5]]

   for r in range(3):
   	for c in range(3):
   		if r > c:
   			matrix[r][c], matrix[c][r] = matrix[c][r], matrix[r][c]

   for i in range(3):
   	print(matrix(i))
   ```

2. **zip 함수를 활용한 전치**

   ```python
   zipped_matrix = list(zip(*matrix))
   print(zipped_matrix)

   transposed_matrix = list(map(list, zip(*matrix)))
   print(transposed_matrix)
   ```

### 3. 2차원 리스트의 활용

- **그래프의 인접 행렬과 인접 리스트**
  - 그래프에서 정점과 간선의 연결관계를 2차원 리스트를 이용해 구조화할 수 있음

1. **인접 행렬(adjacent matrix) 활용**

   ```python
   ways = [[0, 1, 1, 0],
   				[1, 0, 1, 1],
   				[0, 0, 0, 1],
   				[0, 0, 1, 0]]
   ```

2. **인접 리스트(adjacent list) 활용**

   ```python
   ways = [[1, 2],
           [0, 2, 3],
           [3],
           [2]]
   ```

---

## <mark color="#fbc956">델타 탐색</mark>

### 1. 델타 탐색

- 2차원 리스트의 어떤 위치에서 인접한 곳에 위치한 다른 지점을 조회하거나 이동하는 탐색
- 이차원 배열의 순회 문제에서 많이 등장하는 유형, **상하좌우 탐색**
  - 행 인덱스 (`r`), 열 인덱스(`c`), 델타 값(행과 열의 변화 값, `dr` , `dc`)
  - 상(위쪽) : 행 인덱스 - 1
  - 하(아래쪽) : 행 인덱스 + 1
  - 좌(왼쪽) : 열 인덱스 - 1
  - 우(오른쪽) : 열 인덱스 + 1

1. **중간 지점에서 4방향 탐색하기**

   ```python
   M = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
   r = c = 1

   dr = [-1, 1, 0, 0]
   dc = [0, 0, -1, 1]

   for i in range(4):
   	nr = r + dr[i]
   	nc = c + dc[i]
   	print(M[nr][nc], end=' ')
   ```

2. **모서리에서 탐색하기**

   ```python
   M = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
   r = c = 2

   dr = [-1, 1, 0, 0]
   dc = [0, 0, -1, 1]

   for i in range(4):
   	nr = r + dr[i]
   	nc = c + dc[i]

   	if 0 <= nr < 3 and 0 <= nc < 3:
   		print(M[nr][nc]), end=' '
   ```
