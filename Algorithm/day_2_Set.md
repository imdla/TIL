## <mark color="#fbc956">Python Set</mark>

### 1. Set (집합)

- **반복 가능**(iterable), **가변적**(mutable), **순서가 없는**(Non-sequence) 자료형
- 수합의 집합 개념을 파이썬에서 활용하기 위해 만들어짐
- 해시 테이블을 이용해 구현
- 중괄호(`{}`) 또는 내장함수 이용해 선언
- **사용**

  1. 중복 허용하지 않음

     → **데이터의 중복 제거할 필요성 있을 경우**

     ```python
     my_set = {1, 1, 1, 2, 2, 3, 5}
     print(my_set)
     # {1, 2, 3, 5}
     ```

  2. 해시 테이블 특성상 O(1) 시간만에 검색 · 조회 가능

     → **데이터의 양이 많고 검색 및 조회가 자주 일어나는 경우**

     → 해시 테이블 특성상 리스트에 비해 많은 메모리 사용

### 2. Set 연산자

- **합집합 `|` : `set1 | set2`**
- **교집합 `&` : `set1 & set2`**
- **차집합 `-` : `set1 - set2`**
- **대칭차집합 `^` : `set1 ^ set2`**

```python
set1 = {1, 2, 3, 4, 5}
set2 = {4, 5, 6, 7, 8}

print(set1 | set2)
# {1, 2, 3, 4, 5, 6, 7, 8}

print(set1 & set2)
# {4, 5}

print(set1 - set2)
# {1, 2, 3}

print(set1 ^ set2)
# {1, 2, 3, 6, 7, 8}
```

---

## <mark color="#fbc956">Set Method</mark>

### 1. add(item)

- **`집합.add(item)`**
- 집합에 새로운 원소 하나 추가 (`append`와 구분에 유의)

```python
my_set = {1, 2, 3, 4}
my_set.add(5)
print(my_set)
# {1, 2, 3, 4, 5}
```

### 2. discard(item), remove(item)

- 집합에서 원소 삭제

```python
my_set = {1, 2, 3, 4}

my_set.discard(1)
print(my_set)
# {2, 3, 4}

my_set.remove(3)
print(my_set)
# {2, 4}
```

- **`집합.discard(item)` : 존재하지 않는 원소 삭제 시 오류 발생하지 않음**

```python
my_set = {1, 2, 3, 4}
my_set.discard(5)
print(my_set)
# {1, 2, 3, 4}
```

- **`집합.remove(item)` : 존재하지 않는 원소 삭제 시 KeyError 발생**

```python
my_set = {1, 2, 3, 4}
my_set.remove(5)
print(my_set)
# KeyError: 5
```

### 3. update(iterable)

- **`집합.update(iterable)`**
- 집합에 여러 원소 추가
- 인자를 여러 개 넣어주는 것이 아니라 iterable한 객체로 감싸서 넣어줌

```python
cities = {'Seoul', 'New York'}
cities.update(['Paris', 'London'])
print(cities)
# {'London', 'Paris', 'Seoul', 'New York'}
```

### 4. pop()

- **`집합.pop()`**
- 무작위로 원소 하나 제거 후 반환

```python
cities = {'Seoul', 'New York'}
print(cities.pop())
print(cities)
```
