## <mark color="#fbc956">정렬 알고리즘</mark>

### 1. 정렬

- **안정 정렬**
  - 버블 정렬
  - 카운팅 정렬
  - 병합 정렬
- **불안정 정렬**
  - 선택 정렬
  - 퀵 정렬

### 2. 버블 정렬

- 자료를 특정 기준에 의해 작은 값부터 큰 값 혹은 그 반대의 순서로 자리를 바꿔가며 재배열
- 시간 복잡도 : **$O(N^2)$**

  ```python
  arr = [2, 4, 1, 3]

  for i in range(len(arr)-1, 0, -1):
  	for j in range(i):
  		if arr[j] > arr[j+1]:
  			arr[j], arr[j+1] = arr[j+1], arr[j]

  print(arr)
  ```

- 2차원 리스트의 기준점을 잡고 버블 정렬

  ```python
  a = [[4, 4, 16], [6, 1, 6], [4, 3, 12], [1, 12, 12], [5, 4, 20], [2, 3, 6], [3, 4, 12]]

  def bubble_sort(idx):

  	for i in range(len(a)-1, 0, -1):
  		for j in range(i):
  			if a[j][idx] > a[j+1][idx]:
  				a[j], a[j+1] = a[j+1], a[j]

  bubble_sort(1)

  sorted_a = sorted(a, key=lambda x: x[1])
  print(sorted_a)
  ```

### 3. 카운팅 정렬

- 주어진 리스트의 값의 범위가 작을 때 유용

> 범위 1~9까지인 카드의 숫자별 개수 세기

```python
nums = [4, 4, 2, 3, 5, 5, 1, 1, 5]

# 갯수 세는 리스트
count = [0] * (max(nums) + 1)
# 정렬된 리스트의 원형 틀
sorted_nums = [0] * len(nums)

# 몇 개씩 있는지 카운트
for num in nums:
	count[num] += 1

# 누적합
for i in range(1, len(count))
	count[i] = count[i] + count[i-1]

# 뒤의 자리부터 뽑아서
for j in range(len(nums)-1, -1, -1):
	# 5가 나오면 5의 위치에 뒤부터 삽입
	sorted_nums[count[nums[j]-1]] = nums[j]
	# 위치 인덱스 하나 깎음
	count[nums[j]] -= 1

print(sorted_nums)
```

> **누적합의 필요성**

- count 배열을 완성했을 때 이미 정렬은 끝남

  - 인덱스 기준으로 1이 두 개, 2가 한 개, 4가 두 개, 5가 세 개라는걸 알고 있다면
  - 정렬 결과가 들어갈 `sorted_list=[]` 를 생성해 1을 두 번, 2를 한 번, 3을 한 번, 4를 두 번, 5를 세 번 append하면 아래와 같은 정렬된 결과를 얻을 수 있음

  ```python
  nums = [4, 4, 2, 3, 5, 5, 1, 1, 5]

  count = [0] * (max(nums) + 1)

  for num in nums:
  	count[num] += 1

  print(count)

  sorted_list = []

  # 하나씩 뽑으며
  for idx, n in enumerate(count):
  	for _ in range(n):
  		# 갯수만큼 순서대로 append
  		sorted_list.append(idx)

  print(sorted_list)
  # [1, 1, 2, 3, 4, 4, 5, 5, 5]
  ```

- 누적합은 안정 정렬 결과를 얻기위함

  - 누적합 결과

  ```python
  # 원래 숫자들
  nums = [4, 4, 2, 3, 5, 5, 1, 1, 5]

  # 누적합 전의 카운트 리스트
  count = [0, 2, 1, 1, 2, 3]

  # 누적합 결과 => 4번의 인덱스의 6의 의미 ?
  count = [0, 2, 3, 4, 6, 9]

  # 빈 틀
  sorted_nums = [0, 0, 0, 0, 0, 0, 0, 0, 0]

  # 되어야 할 결과 => 실제로 6번째(5번 인덱스)에 4라는 숫자가 있다.
  sorted_nums = [1, 1, 2, 3, 4, 4, 5, 5, 5]
  ```

  - 누적합 결과 => 4번의 인덱스의 6의 의미

    - '4번 인덱스' = '4라는 숫자'는 되어야할 결과 기준으로 여섯번째까지를 차지해야 함
    - 안정 정렬 위해, 원래 숫자들 뒤에서부터 뽑으며 차지해야 하는 영역의 오른쪽부터 거꾸로 채워나가면 됨

    ```python
    # 5부터 뽑고, 9번째 위치에(인덱스로 8이니 -1) 5를 할당
    # 이후에 그 다음 5는 8번째 위치에 넣어야 하므로 count 배열에서 한개 깎기
    sorted_nums[9 - 1] = 5
    count[5] -= 1

    # 결과
    sorted_nums = [0, 0, 0, 0, 0, 0, 0, 0, 5]
    ```

    - 원래 숫자들에서 5를 뽑아, 누적합 결과에서 5번 인덱스 보면 9라는 값이 있음
    - 9라는 값은 5가 되어야 할 결과 기준으로 9번째(8번 인덱스)에 있다는 뜻
    - 그런데, 사실 5는 현재 3개가 있으므로 나중에 쓰고나서 깎은 후, count 배열의 모습은 다음과 같음

    ```python
    count = [-, -, -, -, -, 6]
    ```

    - 저 숫자는 이제 오른쪽 어디에 넣느냐만 나타낼 뿐, 6으로 남겨져 있어도 전혀 상관없음
    - 그래서 뒤에서부터 삽입하는 코드는 다음과 같음

    ```python
    # 뒤의 자리부터 뽑아서
    for j in range(len(nums)-1, -1, -1):
    	# 5가 나오면 5의 위치에 뒤부터 삽입
    	sorted_nums[count[nums[j]]-1] = nums[j]
    	# 위치 인덱스 하나 깎음
    	count[nums[j]] -= 1
    ```

    - 해당 프로세스 다 순회할 경우, 정렬 완료됨

- **카운팅 정렬 프로세스**
  1. 몇 개씩 존재하는지 집계
  2. 집계 배열 기준으로 누적합 배열 생성
  3. 원본 배열 뒤에서부터 확인하며 정렬된 배열 생성
  - 각 프로세스는 독립적으로 이루어짐
  - 시간 복잡도 : O(N)
  - 특정한 조건 하에서 정렬 내장함수보다 빠른 시간복잡도 가짐

### 4. 선택 정렬

```python
nums = [10, 15, 2, 19, 6, 14]

for i in range(len(nums)-1):
	# 일단 가장 작다고 초기화 해둠
	min_idx = i

	# i 다음부터 순회하며
	for j in range(i+1, len(nums)):
		# i의 값보다 작으면
		if nums[i] < nums[min_idx]:
			# j로 갱신
			min_idx = j

	# 최종적으로 한번 스왑
	nums[i], nums[min_idx] = nums[min_idx], nums[i]

print(nums)
```

- 선택 정렬이 불안정 정렬인 이유

```python
# 앞의 2는 2-A, 뒤의 2는 2-B라 생각하면
nums = [2, 2, 1, 3]

# 처음 시행의 경우 2-A와 1을 바꾸게 되는 순간 2-B 뒤에 2-A가 오기 때문
```

### 5. 병합 정렬

```python
arr = [6, 3, 7, 2, 5, 8, 11, 13]

# 분할
def merge_sort(input_list):
	# 1개면 더 분할할 필요 없음
	if len(input_list) == 1:
		return input_list

	# 기준점 (+1 하면 무한루프)
	mid = len(input_list) // 2
	# 기준점 전까지 분리
	left_half = input_list[:mid]
	# 기준점부터 분리
	right_half = input_list[mid:]

	# 분할정복 재귀호출
	left = merge_sort(left_half)
	right = merge_sort(right_half)

	# 이미 아이디값 분리된 값이 인자로
	return merge(left, right)

# 병합
def merge(left, right):
	# 들어갈 틀
	result = [0] * (len(left) + len(right))
	# 포인터 전부 0으로 초기화
	l = r = idx = 0

	while l < len(left) and r < len(right):
		if left[l] <= right[r]:
			result[idx] = left[l]
			l += 1
			idx += 1
		else:
			result[idx] = right[r]
			r += 1
			idx += 1
	# 하나만 돌게될 것
	while l < len(left):
		result[idx] = left[l]
		l += 1
		idx += 1
	while r < len(right):
		result[idx] = right[r]
		r += 1
		idx += 1

	# 아이디값 새로 생성한것을 리턴
	return result

print(merge_sort(arr))
# 원본 건드리지 않음
```

### 6. 퀵 정렬

1. **로무토 (맨 오른쪽 피봇)**

   ```python
   a = [6, 3, 7, 2, 5, 8, 11, 13]

   def lomuto(low, high):
   	def partition(low, high):
   		# 로무토는 맨 오른쪽을 초기 피봇으로 삼음
   		pivot = a[hight]
   		# 우선 제일 왼쪽은 low값으로 초기화
   		left = low

   		# right : 이동하는 포인터
   		for right in range(low, high):
   			# 순회하며 피봇보다 작을 때
   			if a[right] < pivot:
   				a[left], a[right] = a[right], a[left]
   				left += 1

   		# 순회 후, 칸막이 위치와 맨 오른쪽 피봇값을 스왑
   		a[left], a[high] = a[high], a[left]

   		# 칸막이 인덱스를 반환
   		return left

   	if low < high:
   		# 반환된 칸막이 기준으로 나눔
   		pivot = partition(low, high):
   		lomuto(low, pivot-1)
   		lomuto(pivot+1, high)

   lomuto(0, len(a)-1)
   print(a)
   ```

1. **호어 (중간 피봇)**

   ```python
   a = [6, 3, 7, 2, 5, 8, 11, 13]

   def hoare(low, high):
   	def partition(low, high):
   		pivot = (low + high) // 2
   		L = low
   		H = high

   		while L < R:
   		 # 피봇보다 작으면 다음 것
   		 while a[L] < a[pivpt] and L < R:
   			 # 왼쪽 영역은 피봇보다 큰 것을 찾는 것이 목표
   			 L += 1
   			while a[R] >= a[pivot] and L < R:
   				R -= 1
   			# 2번 왼쪽에서 피봇보다 큰 것을 찾음, 오른쪽에서도 피봇보다 작은 것을 찾은 경우
   			if L < R:
   				# 3번, 둘이 좁혀졌을 때
   				if L == pivot:
   					pivot = R
   				# 2-1번, 그러면 L, R 포인터 위치 스왑
   				# 피봇의 왼쪽에서 큰 것을 오른쪽으로 할당, 오른쪽에서 작은 것을 왼쪽으로 할당
   				a[L], a[R] = a[R], a[L]

   		# 1번이 만날 경우, 피봇과 R 포인터 위치 교환
   		a[pivot], a[R] = a[R], a[pivot]

   		return R

   	if low < high:
   		pivot = partition(low, high)
   		hoare(low, pivot-1)
   		hoare(pivot+1, high)

   hoare(0, len(a)-1)
   print(a)
   ```
