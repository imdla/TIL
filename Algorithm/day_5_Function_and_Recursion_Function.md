## <mark color="#fbc956">함수 (function)</mark>

### 1. 함수 (function)

- `def 함수 이름(인자)` 선언, namespace에 등록
- sum, list 등 built-in 예약어를 덮어 씌우지 않도록 주의
- 선언된 함수는 호출 시(`함수이름()`) 내부 로직 실행됨
- return이 없는 경우 None 반환

### 2. 재귀함수

- 함수 내에서 동일한 함수가 실행되는 함수
- 인자만 변화, 동일 로직이 실행되는 경우 재귀함수 이용해 표현
- 재귀가 로직에 맞게 종료되도록 기저사례 (더 이상 재귀가 이루어지지 않는 경우)를 잘 정의해주어야 함

### 3. 재귀함수 구현- 피보나치 수열

- 피보나치 수열
  : 1,1 에서 시작해 세 번째 항부터 직전에 나온 두 항의 합으로 정의되는 수열
- `fibo(n) = fibo(n-2) + fibo(n-1)`

```python
def fibo(n):
	if n < 3:
		return 1
	else:
		return fibo(n-1) + fibo(n-2)

print(fibo(10))
```

- **메모이제이션 기법**
  - 피보나치 수열을 재귀함수 이용해 구현 시 시간복잡도 : **O(2^N)**
    → 호출 과정에서 수많은 중복된 계산이 발생
  - 계산된 결과값을 기록(캐싱)으로 공간복잡도를 희생하는 대신 이후 해당 결과값을 바로 연산에 사용해 큰 시간 복잡도 개선을 의도하는 기법

```python
memo = [0, 1]

def fibo(n):
	if n >= 2 and n >= len(memo):
		memo.append(fibo(n-1) + fibo(n-2))
	return memo[n]

print(fibo(10))
```

### 4. 이진탐색 알고리즘

- 정렬되어 있는 배열에서 원하는 데이터를 빠르게 찾아낼 수 있는 알고리즘
- 로직
  1. 배열의 가운데에 위치한 수 선택
  2. 탐색중인 데이터와 비교
     1. 해당 숫자 > 탐색중인 데이터 : 오른쪽 절반 덜어냄
     2. 해당 숫자 < 탐색중인 데이터 : 왼쪽 절반 덜어냄
     3. 해당 숫자 = 탐색중인 데이터 : 탐색 종료
  3. 탐색에 성공하거나 배열의 길이 0이 될 때까지 위의 과정 반복
- 시간 복잡도 : **O(logN)**
  - 이진탐색은 한 번 실행마다 탐색 번위가 절반으로 줄어듦

```python
nums = [1, 2, 3, 4, 5, 6, 7, 8, 9]

def binary_search(low, high, target):
	if low > high:
		return '찾지 못함'

	mid = (low + high) // 2

	if target == nums[mid]:
		return mid
	elif target < nums[mid]:
		return binary_search(low, high-1, target)
	elif target > nums[mid]:
		return binary_search(mid+1, high, target)

print(binary_search0, len(nums)-1, 7)
```

- **while 반복문 활용 이진탐색 알고리즘 구현**
  ```python
  nums = [1, 2, 3, 4, 5, 6, 7, 8, 9]

  def binary_search(low, high, target):
  	while low <= high:
  		mid = (low + high) // 2

  		if target == nums[mid]:
  			return mid
  		elif target < nums[mid]
  			hight = mid-1
  		elif target > nums[mid]
  			low = mid+1

  	return "찾지 못함"

  print(binary_search(0, len(nums)-1, 7))
  ```
