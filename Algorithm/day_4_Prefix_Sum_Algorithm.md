## <mark color="#fbc956">구간합 문제</mark>

### 1. 구간합 문제

- 1차원 배열의 일정 구간의 합을 구하는 문제

  - 고속도로의 통행량 계산
  - 특정 기간 매출액 계산
  - 특정 기간 강수량 계산

- 5개의 원소를 갖는 1차원 배열에서 길이가 3인 인접한 구간의 합 중 크기가 가장 큰 값?
  (배열의 길이 N, 구하고자하는 구간의 길이 M)
  ```python
  nums = [3, 5, 1, 4, 2]
  ```

### 2. 나이브한 방법

- 각 경우의 수를 모두 처음부터 계산하는 방법

```python
nums = [3, 5, 1, 4, 2]

max_num = -float("INF")

for idx in range(3):
	tmp = sum(nums[idx:idx+3])
	max_num = max(max_num, tmp)

print(max_num)
```

- 시간 복잡도 : **O(NM)**
  - M번의 연산 (N-M+1)회 진행
- 문제점
  - 모든 구간의 합을 처음부터 새롭게 구성 → 정보의 낭비2. 발생

### 3. 슬라이딩 윈도우

- N+1번째 구간합 = N번째 구간합 - 가장 왼쪽 구간의 값 + 새로운 구간의 합
- 기존 M번의 연산이 걸렸던 새로운 구간의 합을 2번의 연산만으로 조회 가능

```python
nums = [3, 5, 1, 4, 2]

tmp = max_num = sum(nums[:3])

for idx in range(2):
	tmp += nums[idx+3] - nums[idx]
	max_num = max(max_num, tmp)

print(max_num)
```

- 시간 복잡도 : **O(N)**
  - 첫 구간합에서 M번의 연산, 이후 N-M회 동안 2번의 연산 진행
- 장점
  - 고정된 길이의 구간합을 처음부터 끝까지 빠르게 구할 수 있음
- 문제점
  - 단순히 특정한 구간의 합만 필요할 경우
  - 구하고자 하는 구간의 길이가 계속 변화할 경우
  - 위의 경우에서 사용하기 적합하지 않음

### 4. 누적합 알고리즘

- 각 구간의 합에 대한 정보가 모두 저장되어 있는 누적합 배열을 미리 생성
- 누적합 배열의 i번 인덱스의 값 = 처음부터 i번째 숫자까지 더한 구간합
  - i번째 숫자부터 j번째 숫자까지 구간 합 = `acc_nums[j] - acc_nums[i-1]`

1. **반복문** 활용 → **누적합 배열 생성**

   ```python
   nums = [3, 5, 1, 4, 2]

   acc_nums = [0]
   for num in nums:
   	acc_nums.append(acc_nums[-1]+num)

   print(acc_nums)
   ```

2. **accumulate 모듈** 활용 → **누적합 배열 생성**

   ```python
   from itertools import accumulate

   nums = [3, 5, 1, 4, 2]
   acc_nums = [0] + list(accumulate(nums))

   print(acc_nums)
   ```

- 시간복잡도
  - 구간합 배열 제작 : **O(N)**
  - 실제 구간합 조회 : **O(1)**
