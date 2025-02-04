## <mark color="#fbc956">List</mark>

### 1. List

- 대괄호 (`[]`) 표현
- **반복 가능(iterable), 가변적(mutable), 순서가 있는(sequence)** 컨테이너 자료형
- 다양한 자료형을 원소로 가짐
- **시간 복잡도**
  - 데이터 접근(인덱스 값 이용) : $O(1)$
  - 내부 데이터 탐색 (`in` 연산자 등) : $O(n)$

```python
nums = [3, 5, 1, 4, 2]
print(nums[0]) # 3

nums[1] = 7
print(nums) # [3, 7, 1, 4, 2]

nums[8] = 9 # IndexError
```

### 2. List Comprehension (리스트 컴프리헨션)

- 코드 한 줄로 간단하게 리스트 만들 수 있는 기법

1. **`append` 메서드 이용 리스트 생성**

   ```python
   nums = []
   for num in range(5):
   	nums.append(num)

   print(nums)
   # [0, 1, 2, 3, 4]
   ```

2. **list comprehension 활용 리스트 생성**

   ```python
   nums = [num for num in range(5)]

   print(nums)
   # [0, 1, 2, 3, 4]
   ```

   - 조건문 활용

   ```python
   nums = [num for num in range(5) if num % 2 == 0]

   print(nums)
   # [0, 2, 4]
   ```

### 3. 리스트 슬라이싱

- **`리스트[start : end : step]`**
  - `start` 인덱스 이상 `end` 인덱스 미만을 `step` 간격으로 잘라냄
- 리스트의 일부 복사하거나 도려내거나 수정 가능

```python
nums = [3, 5, 1, 4, 2]

# 리스트 일부 복사
sliced_nums = nums[2:4]
print(nums, sliced_nums)
# [3, 5, 1, 4, 2] [1, 4]

# 리스트 일부 수정
nums[2:4] = [1, 2, 3, 4, 5]
print(nums)
# [3, 5, 1, 2, 3, 4, 5, 2]
```

- **`start` 빈 경우** : 처음부터
- **`end` 빈 경우** : 끝까지
- **`step` 음수인 경우** : 거꾸로 슬라이싱

```python
nums = [3, 5, 1, 4, 2]

# 리스트 전체 복사 (얕은 복사)
copied_nums = nums[:]
print(copied_nums)
# [3, 5, 1, 4, 2]

# 리스트 뒤집기
reversed_nums = nums[::-1]
print(reversed_nums)
# [2, 4, 1, 5, 3]
```

---

## <mark color="#fbc956">List Method</mark>

### 1. append(item)

- **`리스트.append(item)`**
- 리스트의 맨 뒤에 새로운 데이터 하나만 추가

```python
nums = [3, 5, 1, 4, 2]

nums.append(6)
print(nums)
# [3, 5, 1, 4, 2, 6]
```

### 2. pop(index)

- **`리스트.pop(index)`**
- 리스트의 인덱스 위치에 해당하는 원소 추출 후 반환
- `index` 미지정 시 맨 뒤에서 추출

```python
nums = [3, 5, 1, 4, 2]

num = nums.pop(3)
print(nums, num)
# [3, 5, 1, 2] 4

print(nums.pop())
# 2
```

### 3. sort()

- **`리스트.sort()`**
- 리스트를 오름차순으로 정렬
- 리스트의 모든 원소가 서로 비교 가능해야 함
  (정수형과 문자열이 섞여있는 경우 TypeError 발생)
- **옵션**
  - `reverse=True` : 내림차순 정렬

```python
nums = [3, 5, 1, 4, 2]

nums.sort()
print(nums)
# [1, 2, 3, 4, 5]

nums.sort(reverse=True)
print(nums)
# [5, 4, 3, 2, 1]

# key 옵션에 임시 함수 지정해 구체적인 정렬 기준 지정
nums.sort(key=lambda x: -x)
print(nums)
# [5, 4, 3, 2, 1]
```

### 4. count(item)

- **`리스트.count(item)`**
- 리스트에 있는 `item`의 개수 반환

```python
nums = [3, 5, 1, 4, 2, 5]
print(nums.count(5))
# 2
```

### 5. index(item)

- **`리스트.index(item)`**
- `item`의 인덱스 번호 반환
- `item` 이 여러 개인 경우 가장 앞에 있는 인덱스 번호만 반환
- 리스트에 존재하지 않는 데이터의 경우 ValueError 발생

```python
nums = [3, 5, 1, 4, 2, 1]
print(nums.index(1))
# 2
print(nums.index(9))
# ValueError
```

### 6. insert(index, item)

- **`리스트.insert(index, item)`**
- 리스트의 `index` 위치에 `item` 데이터를 추가

```python
nums = [3, 5, 1, 4, 2]

nums.insert(1, 9)
print(nums)
# [3, 9, 5, 1, 4, 2]
```

### 9. extend(iterable)

- **`리스트.extend(iterable)`**
- 리스트의 맨 끝에 반복 가능한 객체(iterable)의 데이터 전부 추가

```python
nums = [3, 5, 1, 4, 2]

nums.extend([7, 6, 8])
print(nums)
# [3, 5, 1, 4, 2, 7, 6, 8]
```

- 더하기 연산(`+`)으로 리스트 병합 가능

```python
nums = [3, 5, 1, 4, 2]

nums += [7, 6, 8]
print(nums)
# [3, 5, 1, 4, 2, 7, 6, 8]
```
