## <mark color="#fbc956">완전 탐색 알고리즘</mark>

### 1. 완전 탐색 알고리즘

- **완전 탐색(브루트 포스, brute force)**
  - 가능한 경우의 수를 모두 확인하는 알고리즘

### 2. 모든 경우의 수 확인하기

- 9개의 숫자 중 2개를 골라내는 모든 경우릐 수를 하나씩 검토해 나머지 숫자들을 더했을 때 100이 되는 경우

1. **반복문으로 확인하기**

   - 2중 for 반복문을 이용해 두 숫자 골라냄

   ```python
   nums = [20, 7, 23, 19, 10, 15, 25, 8, 13]
   sum_nums = sum(nums)

   flag = False
   for i in range(8):
   	for j in range(i+1, 9):
   		if sum_num - nums[i] - nums[j] == 100:
   			print(nums[i], nums[j])
   			flag = True
   			break
   	if flag:
   		break
   ```

2. **조합 모듈 활용하기**

   ```python
   from itertools import combinations

   nums = [20, 7, 23, 19, 10, 15, 25, 8, 13]
   sum_num = sum(nums)

   for i, j in combinations(nums, 2)
   	if sum_num - i - j == 100:
   		print(i, j)
   		break
   ```

---

## <mark color="#fbc956">투 포인터 알고리즘</mark>

### 1. 투 포인터 알고리즘

- 1차원 배열에서 인덱스를 가리키는 두 개의 포인터를 조작해 조건에 적합한 값을 찾는 알고리즘

### 2. 모든 경우의 수 확인하기

- 10개의 수로 이루어진 수열의 i부터 j번째 수까지의 합이 5가되는 경우의 수

1. **반복문으로 확인하기**

   - 2중 for 반복문을 활용한 모든 경우의 수 확인하기

   ```python
   nums = [1, 2, 3, 4, 2, 5, 3, 1, 1, 2]

   cnt = 0

   for i in range(10):
   	tmp = 0
   	for j in range(i, 10):
   		tmp += nums[j]
   		if tmp == 5:
   			cnt += 1
   		elif tmp > 5:
   			break

   print(xnt)
   ```

2. **투 포인터 알고리즘**

   ```python
   nums = [1, 2, 3, 4, 2, 5, 3, 1, 1, 2]

   s = e = 0
   tmp = 0
   cnt = 0

   while True:
   	if tmp < 5:
   		if e >= 10:
   			break
   		else:
   			tmp += nums[e]
   			e += 1
   	elif tmp > 5:
   		tmp -= nums[s]
   		s += 1
   	else:
   		cnt += 1
   		tmp -= nums[s]
   		s += 1

   print(cnt)
   ```
