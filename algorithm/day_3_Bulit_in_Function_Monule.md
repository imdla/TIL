## <mark color="#fbc956">내장함수</mark>

### 1. len(iterable), max(iterable), min(iterable), sum(iterable)

- 각각 반복 가능한(iterable) 객체의 길이, 최댓값, 최솟값, 합계 계산

```python
nums = [1, 2, 3, 4, 5]

print(len(nums))
# 5
print(max(nums))
# 5
print(min(nums))
# 1
print(sum(nums))
# 15
```

### 2. sorted(iterable)

- 반복 가능한(iterable) 객체를 오름차순으로 **정렬된 리스트로 반환**

```python
nums = [3, 5, 1, 4, 2]

print(sorted(nums))
# [1, 2, 3, 4, 5]
```

- 서로 비교할 수 없는 원소들 존재 시 TypeError 발생
- **옵션**
  - `reverse=True` : 내림차순 정렬
  - `key=lambda x : x[1]` : `key` 옵션에 함수 지정 시 해당 함수의 반환 값을 기준으로 정렬 수행

> 💡**메서드와 함수**
>
> - **메서드**
>   - 어떤 클래스 혹은 객체에 종속된 함수
> - **함수**
>   - 메서드와 달리 어떤 클래스 혹은 객체에 종속되지 않고 독립적으로 존재
>   - 리스트뿐만 아니라 모든 반복 가능한(iterable) 객체에 적용 가능

### 3. range(start, end, step)

- start 이상 end 미만까지의 step 간격으로 연속된 정수 목록 반환
- start 미지정 시 0부터 시작, step 미지정 시 1 간격, end 미지정 시 목록 끝까지
- step 음수 지정 시 내림차순의 정수 목록 반환

```python
for i in range(4):
	print(i, end=' ')
# 0 1 2 3

for i in range(1, 8, 2):
	print(i, end=' ')
# 1, 3, 5, 7

for i in range(5, 0, -1):
	print(i, end=' ')
# 5, 4, 3, 2, 1
```

### 4. zip(iterable, iteable, …)

- 반복 가능한(iterable) 객체들을 세로로 집어 튜플들로 반환
- 2차원 전치 가능

```python
nums1 = [1, 2, 3]
mums2 = [4, 5]

print(list(zip(nums1, nums2)))
# [(1, 4), (2, 5)]

matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
print(list(zip(*matrix)))
# [(1, 4, 7), (2, 5, 8), (3, 6, 9)]
```

### 5. emumerate(iterable)

- 반복 가능한(iterable) 객체의 각 원소들에 인덱스를 부여하고 (인덱스, 원소) 형태의 튜플 묶음으로 반환

```python
nums = [3, 5, 1, 2, 4]
print(list(enumerate(nums)))
# [(0, 3), (1, 5), (2, 1), (3, 2), (4, 4)]
```

---

## <mark color="#fbc956">주요 모듈</mark>

### 1. sys

- 파이썬 시스템 설정에 대한 정보를 제공하고 변경할 수 있는 모듈

1. **`stdin.readline()`**

   - 입력을 한 줄씩 빠르게 받음

   ```python
   import sys
   imput = sys.stdin.readline
   ```

2. **`setrecursionlimit()`**

   - RecursionError 방지 위해 재귀함수의 한계 깊이 재설정
   - 디폴트 깊이는 1,000 정도

   ```python
   import sys
   sys.setrecursionlimit(10**9)
   ```

### 2. collections

- 여러 가지 유용한 자료구조를 제공하는 모듈

1. **`defaultdict(value)`**

   - `value`의 디폴트 자료형을 정해 KeyError 방지
   - 존재하지 않는 `key` 조회 시 정해진 자료형에 따라 일정한 `value` 가진 `key`를 새로 생성
     - str → `''`
     - list → `[]`
     - dictionary → `{}`

   ```python
   vote = ["red", "orange", "yellow", "red", "blue", "blue", "red"]

   from collections import defaultdict

   result = defaultdict(int)
   for vote in votes:
   	result[vote] += 1

   print(result)
   # defaultdict(<class 'int'>, {'red': 3, 'orange': 1, 'yellow': 1, 'blue': 2}
   ```

2. **`deque(iterable)`**

   - 반복 가능한(iterable) 객체를 deque 자료형으로 변환, 생성
   - 양쪽 끝에서 자료의 삽입과 삭제가 발생하는 양방향 연결리스트

   ```python
   from collections import deque

   nums = [1, 2, 3, 4, 5]
   queue = deque(nums)
   print(queue)
   # deque([1, 2, 3, 4, 5])

   print(queue.pop())
   # 5
   print(queue)
   # deque([1, 2, 3, 4])

   queue.append(100)
   print(queue)
   # deque([1, 2, 3, 4, 100])

   print(queue.popleft())
   # 1
   print(queue)
   # deque([2, 3, 4, 100])

   queue.appendLeft(200)
   print(queue)
   # deque([200, 2, 3, 4, 100])
   ```

3. **`Counter(iterable)`**

   - 반복 가능한(iterable) 객체에서 원소들의 빈도를 집계해 반환
   - Counter 객체는 딕셔너리와 비슷한 형태 및 성질 가짐

   ```python
   from collections import Counter

   colors = ["red", "orange", "yellow", "red", "blue", "blue", "red"]
   counting_colors = Counter(colors)

   print(counting_colors)
   # Counter({'red': 3, 'orange': 1, 'yellow': 1, 'blue': 2})
   ```

### 3. itertools

- 데이터 처리를 위한 다양한 도구를 제공하는 모듈

1. **`permutations(iterable, n)`**

   - 순열 제작 모듈
   - 반복 가능한 객체에서 n개를 뽑아 줄을 세우는 모든 경우의 수를 반환

   ```python
   from itertools import pernumtations

   arr = ['A', 'B', 'C']
   print(list(permutations(arr, 2)))

   # [('A', 'B'), ('A', 'C'), ('B', 'A'), ('B', 'C'), ('C', 'A'), ('C', 'B')]
   ```

2. **`comninations(iterable, n)`**

   - 조합 제작 모듈
   - 반복 가능한 객체에서 n개를 뽑는 모든 경우의 수를 반환

   ```python
   from itertools import combinations

   arr = ['A', 'B', 'C']
   print(list(combinations(arr, 2)))
   # [('A', 'B'), ('A', 'C'), ('B', 'C')]
   ```

3. **`accumulate(iterable)`**

   - 누적합 배열을 만들어 반환

   ```python
   from itertools import accumulate

   nums = [1, 2, 3, 4, 5]
   print(list(accumulate(nums)))

   # [1, 3, 6, 10, 15]
   ```

### 4. heapq

- 힙 자료구조와 관련된 기능을 제공하는 라이브러리

1. **`heapify(iterable)`**

   - 반복 가능한 객체를 힙 자료구조로 만들어 반환

   ```python
   from heapq import heapify, heappush, heappop

   nums = [3, 5, 2, 4, 1]
   heapify(nums)

   print(nums)
   # [1, 3, 2, 4, 5]
   ```

2. **`heappush(iterable, item)`**

   - 힙 자료구조에 원소 삽입

   ```python
   from heapq import heapify, heappush, heppop

   nums = [3, 5, 2, 4, 1]
   heapify(nums)

   heappush(nums, 6)
   print(nums)
   # [1, 3, 2, 4, 5, 6]
   ```

3. **`heappop(iterable)`**

   - 힙 자료구조에서 원소를 뽑아 반환

   ```python
   form heapq import heapify, heappush, heappop

   nums = [3, 5, 2, 4, 1]
   heapify(nums)

   min_num = heappop(nums)
   print(min_num, nums)
   # 1 [2, 3, 5, 4]
   ```
