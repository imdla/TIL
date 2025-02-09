## <mark color="#fbc956">순열</mark>

### 1. 순열

- N개의 데이터 중 M개를 뽑아 줄을 세우는 모든 경우의 수

### 2. for 반복문 통한 구현

```python
arr = ['A', 'B', 'C']

for i in range(3):
	for j in range(3):
		for k in range(3):
			if i != j and j != k and k != i:
				print([arr[i], arr[j], arr[k]])
```

### 2. 재귀함수를 통한 구현(DFS 응용)

```python
# 재료 리스트
arr = ['A', 'B', 'C']
# 뽑기 selection
sel = [0, 0, 0]
# 뽑을지 말지 결정하는 리스트
check = [0, 0, 0]

# 각자 뎁스에서
def perm(depth):
	# 최고 뎁스에 도달할 경우
	if depth == 3:
		print(sel)
		return

	# 3번의 화살표 떨어트릴 기회
	for i in range(3):
		# 각 기회 안에서 check를 통해 멈출 수 있는지 확인
		if not check[i]:
			# 멈출 수 있다면 if 통과했으므로 자리 차지했다고 표시
			check[i] = 1
			# 화살표가 떨어진 위치의 재료 리스트
			sel[depth] = arr[i]
			prem(depth+1)
			# 돌아나오며 다시 다음을 위해 초기화
			ceck[i] = 0

perm(0)
```

---

## <mark color="#fbc956">조합</mark>

### 1. 조합

- N개의 데이터 중 M개를 뽑는 모든 경우의 수

### 2. for 반복문을 통한 구현

```python
# 그냥 조합 2개 뽑기
arr = ['A', 'B', 'C']
for i in range(3):
	for j in range(i+1, 3):
		print(arr[i], arr[j])

# 중복조합 2개 뽑기
arr = ['A', 'B', 'C']
for i in range(3):
	for j in range(i, 3):
		print(arr[i], arr[j])
```

### 2. 재귀를 통한 구현

```python
# 5C3
arr = ['A', 'B', 'C', 'D', 'E']
visited = [0] * 5
sel = [0] * 3

def combi(depth, idx):
	# 3개를 다 뽑을 경우
	if depth == 3:
		# 출력 후 리턴
		print(*sel)
		return

	# 아직 다 뽑지 않았을 경우 이번 뎁스에서 뽑을 원소 찾기
	for i in range(idx, 5):
		# 아직 안 뽑은 원소라면
		if not visited[i]:
			# 이번 뎁스에서 뽑음
			sel[depth] = arr[i]

			# 방문 표시
			visited[i] = 1
			# 다음 뎁스로 넘어가기
			combi(depth+1, i)
			# 돌아나올 때 방문 표시 해제
			visited[i] = 0

combi(0, 0)
```

```python
# 5C3
arr = ['A', 'B', 'C', 'D', 'E']
sel = [0, 0, 0]

def combination(idx, sidx):
	# sel 길이 범위 벗어나면 sel이 확정되었다는 것과 같음
	if sidx == 3:
		print(sel)
		return

	# 이 범위도 벗어나지 않아야 함
	if idx == 5:
		return

	# sidx가 가르키는 위치에 idx가 가르키는 재료 넣음
	sel[sidx] = arr[idx]
	# 첫번째로는 두 개의 화살표가 동시에 오른쪽으로 가보고
	combination(idx+1, sidx+1)
	# 두번째로는 arr쪽 화살표만 혼자 가봄
	combination(idx+1, sidx)

combination(0, 0)
```

---

## <mark color="#fbc956">백트래킹</mark>

### 1. 백트래킹

- 깊이 우선 탐색을 기반으로 한 알고리즘으로 경로 탐색 중 유효하지 않은 경로에 대한 탐색을 중단하는 알고리즘
- 유효한 경로까지 되돌아가 다른 경로를 탐색
- 유효하지 않은 경로는 탐색하지 않기 때문에 효율적으로 탐색 가능
