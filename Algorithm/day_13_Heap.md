## <mark color="#fbc956">힙 자료구조</mark>

### 1. 힙 자료구조

- 완전 이진 트리 기반의 자료구조
- 일반적인 리스트의 최대값, 최소값 연산의 시간복잡도 : O(N)
- 힙 자료구조의 최대값, 최소값 연산의 시간복잡도 : O(logN)
  - 완전 이진트리의 성질을 이용하기 때문

### 2. 힙 자료구조 예시

- 언제나 두 가지 조건 만족할 경우, **최소힙** 구현 가능
  1. **완전 이진 트리일 것**
  2. **모든 노드에서 부모 노드는 항상 자식 노드보다 작은 값이 유지될 것**
     ![image.png](/Algorithm/assets/heap_1.png)
- 반대로, 모든 모드에서 부모 노드에 자식 노드보다 큰 숫자가 들어 있는 경우 : **최대힙**
  ![image.png](/Algorithm/assets/heap_2.png)

### 3. 최소힙 연산

- 힙 자료구조 제대로 기능 위해 두 가지 조건 만족 필요

  1. 새로운 데이터가 삽입된 후에도 두 가지 조건이 계속 만족되어야 함
  2. 1번 노드에서 데이터가 추출된 후에도 두 가지 조건이 계속 만족되어야 함

- **힙 삽입 : O(lonN)**

  1. 완전 이진 트리를 유지하며 맨 끝에 새로운 데이터 추가

     ![image.png](/Algorithm/assets/heap_3.png)

  2. 완전 이진 트리는 유지 중이지만, 최소힙의 요건이 깨진 상태

     - 최소힙을 유지하기 위해 조상 노드를 계속 거슬러 올라가며 부모가 더 큰 경우 스왑함
     - 힙 삽입 시간 복잡도 : O(lonN)

     ![image.png](/Algorithm/assets/heap_4.png)

- **힙 삭제 : O(logN)**

  1. 최소힙의 루트 노드에 있는 3 추출 후, 완전 이진 트리의 특징 유지 위해 맨 뒤에 위치한 데이터를 1번 노드 자리로 옮김

     ![image.png](/Algorithm/assets/heap_5.png)

  2. 완전 이진 트리 조건은 만족하고 있지만, 최소힙의 요건을 채우지 못한 상태

     ![image.png](/Algorithm/assets/heap_6.png)

  3. 왼쪽 자식과 오른쪽 자식 둘 작은 수를 택하여 조건을 만족시킬 때까지 스왑하며 내려감

     - 힙 삭제 시간 복잡도 : O(logN)

     ![image.png](/Algorithm/assets/heap_7.png)

---

## <mark color="#fbc956">힙 자료구조 직접 구현</mark>

### 1. 힙 자료구조의 직접 구현 코드 (최소힙)

```python
# 힙 삽입 연산
def heap_push(item):
	heap.append(item) # 일단 힙의 맨 끝에 삽입

	child = len(heap) - 1 # 방금 맨 끝에 삽입한 노드 나타냄
	parent = child // 2

	# 루트 노드가 아니면서, 최소힙을 만족하지 못한 경우 계속 반복
	while parent > 0 and heap[parent] > heap[child]:
		heap[parent], heap[child] = heap[child], heap[parent]

		# 상위로 이동
		child = parent
		parent = child // 2

# 힙 삭제 연산
def heap_pop():
	if len(heap) <= 2:
		return heap.pop() # 루트 노드만 남은 경우 바로 반환

	root = heap[1] # 루트 노드 백업
	heap[1] = heap.pop() # 마지막 노드를 루트 노드로 이동

	parent = 1
	child = parent * 2 # 일단 왼쪽 자식

	while child < len(heap): # 자식이 존재한다면
		# 오른쪽 자식이 있고, 오른쪽 자식이 왼쪽 자식보다 작다면
		if child + 1 len(heap) and heap[child] > heap[child+1]:
			child += 1 # 비교 대상을 오른쪽 자식으로 갱싱

		if heap[parent] <= heap[child]:
			break # 최소힙을 만족하면 break

		# 자식이 더 작으면 최소힙이 아니므로 노드 교환
		heap[parent], heap[child] = heap[child], heap[parent]

		# 하위로 이동
		parent = child
		child = parent * 2

	return root # 백업했던 루트 노드 반환 (= 최소값)

heap = [-1] # 1번째를 루트노드로 하기 위해, 임의의 값을 0번째에 넣고 시작

heap_push(2)
heap_push(5)
heap_push(7)
heap_push(3)
heap_push(4)
heap_push(6)

print(heap) # [-1, 2, 3, 6, 5, 4, 7]

print(heap_pop()) # 2
print(heap_pop()) # 3
print(heap_pop()) # 4
print(heap_pop()) # 5
print(heap_pop()) # 6
print(heap_pop()) # 7

print(heap) # [-1]
```

### 2. 힙큐 모듈 활용

```python
from heapq import heappush, heappop

heap = []

heappush(heap, 2)
heappush(heap, 5)
heappush(heap, 7)
heappush(heap, 3)
heappush(heap, 4)
heappush(heap, 6)

print(heap) # [2, 3, 6, 5, 4, 7]

print(heappop(heap)) # 2
print(heappop(heap)) # 3
print(heappop(heap)) # 4
print(heappop(heap)) # 5
print(heappop(heap)) # 6
print(heappop(heap)) # 7

print(heap) # []
```

```python
from heapq import heappush, heappop

# 문자열도 원소로 넣을 수 있음, 이때는 사전 순으로 가장 앞선 원소가 루트 노드가 됨
heap = []

heappush(heap, 'b')
heappush(heap, 'c')
heappush(heap, 'a')

print(heappop(heap)) # a
print(heappop(heap)) # b
print(heappop(heap)) # c
```

```python
from heapq import heappush, heappop

# 리스트나 튜플도 원소로 넣을 수 있음
# 첫 번째 원소를 비교, 같을 경우 두 번째 원소를 비교
heap = []

heappush(heap, (1, 2))
heappush(heap, (2, 3))
heappush(heap, (1, 3))

print(heappop(heap)) # (1, 2)
print(heappop(heap)) # (1, 3)
print(heappop(heap)) # (2, 3)
```
