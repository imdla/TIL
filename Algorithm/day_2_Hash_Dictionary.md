## <mark color="#fbc956">Hash Table</mark>

### 1. Hash Table (해시 테이블)

- `key` ,`value` 의 형태로 자료를 저장하는 자료구조
- 특정 `key` 넣었을 때 1 대 1로 매칭되는 `index` 있고, 그 곳에 `value` 저장됨
  ⇒ `value` 가 저장되는 곳 = **해시 테이블**
- `key` 와 `index` 를 1 대 1로 매칭하기 위해서 **해시 함수(Hash Function**) 필요

### 2. Hash Function (해시 함수)

- 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수
- 특정 `key` 를 해시 함수에 넣었을 때 반환되는 `index` 값 = **해시(hash)**
- **해시 함수 통해 `key` , `value` 가 매핑되는 과정**
  1. 입력된 `key` 값이 해시 함수 통해 해시값으로 반환
  2. 해시값은 해시 테이블의 `index` 가 되며 해당 위치에 `value` 값 저장
- `key`는 `index`와 1 대 1 매칭 되어야 하므로 **`key` 에는 가변적인(mutable) 값을 쓸 수 없음**
  → 이러한 `key`의 성질을 **해시 가능하다(hashable)** 라고 함
  - 리스트와 같이 가변적인 객체를 해시 자료구조의 `key` 로 사용하려하면 TypeError 발생

```python
my_set = {1, 2, 3, 4, [1, 2]}
# TypeError
```

### 3. 해시 테이블 특징

- 해시 테이블 기반 자료구조(딕셔너리, 집합)의 중요한 특징

1. **중복 허용하지 않음**
2. **순서가 없음 (인덱스를 이용한 데이터 조회 불가능)**
3. **해시 테이블 특성상 O(1) 시간만에 검색 · 조회 가능**

---

## <mark color="#fbc956">Python Dictionary</mark>

### 1. Python Dictionary (파이썬 딕셔너리)

- 해시 테이블 자료구조 기반으로 구현
- 딕셔너리는 중괄호(`{}`) 표현, `key` 값 이용해 `value` 값 조회 가능
- `key` 를 통해 `value` 를 조회하는 과정이 순회가 아니라 매핑이므로 매우 빠름
- **시간 복잡도**
  - 삽입, 삭제, 조회 탐색 연산 : **O(1)**
- 데이터 양이 많고, 검색과 조회 연산이 많을 때 리스트보다 유용

1. **중괄호(`{}`) 형태로 딕셔너리 선언**

```python
my_dict = {
  "name": "john",
  "age": 20,
  "license": True
}
```

2. **딕셔너리 `[key] = value` 형태로 데이터 삽입 및 수정**

```python
# 데이터 삽입
my_dict["adress"] = "Seoul"
print(my_dict)
# {'name': 'john', 'age': 20, 'license': True, 'adress': 'Seoul'}

# 데이터 수정
my_dict["age"] = 30
print(my_dict)
# {'name': 'john', 'age': 30, 'license': True, 'adress': 'Seoul'}
```

3. **딕셔너리 `[key]` 형태로 데이터 조회**

```python
print(my_dict["name"])
# john

print(my_dict["email"])
# KeyError
```

### 2. 키 에러 방지

- 집계할 딕셔너리 만든 후 리스트 순회하며 이름을 `key` 로, 득표 수를 `value`로 구조화 가능
  → 처음 나온 이름의 득표 수를 올릴 때 KeyError 발생 가능

```python
# 반장 선거의 결과
votes = ["ken", "alex", "jun", "alex", "kyle", "kyle", "kyle"]
```

1. **조건문을 이용해 분기**

```python
votes = ["ken", "alex", "jun", "alex", "kyle", "kyle", "kyle"]

result = {}
for vote in votes:
  if vote not in result:
    result[vote] = 1
  else:
    result[vote] += 1

print(result)
# {'ken': 1, 'alex': 2, 'jun': 1, 'kyle': 3}
```

2. **`defaultdict` 클래스 활용**

- **`defaultdict()`**
- `value` 의 디폴트 자료형을 정해줌으로써 KeyError 방지
- 존재하지 않는 `key` 조회하면 정해진 자료형에 따라 일정한 `value` 가진 `key` 를 새로 생성
  - **int로 정해줄 경우** : value는 `0`
  - **str로 정해줄 경우**
    - value는 `“`
    - list는 `[]`
    - dictionary는 `{}`
      → 해당 자료형의 비어있는 값이 자동으로 지정됨

```python
votes = ["ken", "alex", "jun", "alex", "kyle", "kyle", "kyle"]

from collections import defaultdict

result = defaultdict(int)
for vote in votes:
  result[vote] += 1

print(result)
# defaultfict(<class 'int'>, {'ken': 1, 'alex': 2, 'jun': 1, 'kyle': 3})
```

3. **`.get()` 메서드 활용**

- **`딕셔너리.get(key)`**
- 해당 `key` 에 대응되는 `value` 를 반환(조회)
  - **`key` 가 존재하지 않을 경우** : KeyError가 발생하지 않고 `None` 반환
- **옵션**
  - 두 번째 인자를 지정해 `key` 가 없을 때 `None` 대신 반환 값 지정 가능

```python
votes = ["ken", "alex", "jun", "alex", "kyle", "kyle", "kyle"]

result = {}
for vote in votes:
  result[vote] = result.get(vote, 0) + 1

print(result)
# {'ken': 1, 'alex': 2, 'jun': 1, 'kyle': 3}
```

4. **`Counter` 모듈 활용**

- 반복 가능한(iterable) 객체의 원소들의 숫자(빈도)를 집계

```python
votes = ["ken", "alex", "jun", "alex", "kyle", "kyle", "kyle"]

from collections import Counter

result = Counter(votes)
print(result)
# Counter({'ken': 1, 'alex': 2, 'jun': 1, 'kyle': 3})
```

### 3. 딕셔너리의 반복과 정렬

- 딕셔너리도 반복 가능한 개체로 반복문 활용 가능

1. **`fot ... in 딕셔너리` : `key` 값 확인**

```python
my_dict = {
	"name": "john",
	"age": 20,
	"license": True
}

for key in my_dict:
	print(key)
# name
# age
# license
```

2. **`for ... in 딕셔너리.values()` : `vlaue` 값 확인**

```python
for value in my_dict.values():
	print(value)
# john
# 20
# True
```

3. **`for ... in 딕셔너리.items()` : `key`와 `value` 값 동시 확인**

```python
for key, value in my_dict.items():
	print(key, value)
# name john
# age 20
# license True
```

- **`sorted()` : 딕셔너리 정렬**

```python
result = {
	"ken": 1,
	"alex": 2,
	"jun": 1,
	"kyle": 3
}

sorted_result1 = sorted(result)
print(sorted_result1)
# ['alex', 'jun', 'ken', 'kyle']

sorted_result2 = sorted(result.items(), key=lambda x: -x[1])
print(sorted_result2)
# [('kyle', 3), ('alex', 2), ('ken', 1), ('jun', 1)]
```

---

## <mark color="#fbc956">Dictionary Method</mark>

### 1. get(key, any)

- **`딕셔너리.get(key, any)`**
- `key` 에 해당하는 `value` 값 조회
  - **`key` 가 없을 경우** : `None` 반환
- **옵션**
  - 두 번째 인자 지정해 `key` 없는 경우 반환 값 지정 가능

```python
user_info = {"name": "john"}
print(user_info.get("name"))
# john
print(user_info.get("age"))
# None
print(user_info.get("age", "발견하지 못함"))
# 발견하지 못함
```

### 2. key(), values(), items()

- **`딕셔너리.key()` , `딕셔너리.value()` , `딕셔너리.items()`**
- 각각 `key` 들의 집합, `value` 들의 집합, `(key, value)` 쌍의 목록 생성

```python
my_dict = {'name': 'john', 'address': 'Seoul'}

print(my_dict.key())
# dict_keys(['name', 'address'])

print(my_dict.values())
# dict_values(['john', 'Seoul'])

print(my_dict.items())
# dict_items([('name', 'john'), ('address', 'Seoul')])
```

### 3. pop(key, any)

- **`key` 가 존재할 경우**
  - `key` 삭제, `value` 값 반환
- **`key` 가 존재하지 않을 경우**
  - KeyError 발생
- **옵션**
  - 두 번째 인자 지정해 `key` 없는 경우 에러 없이 반환 값 설정

```python
my_dict = {'name': 'john', 'age': 20, 'address': 'Seoul'}

print(my_dict.pop('name'))
# john

print(my_dict)
# {'age': 20, 'address': 'Seoul'}

print(my_dict.pop('license', -1))
# -1

print(my_dict.pop('license'))
# KeyError
```

- `del` 명령어 통해 `key` 삭제 가능

```python
my_dict = {'name': 'john', 'age': 20, 'address': 'Seoul'}

del my_dict['age']
print(my_dict)
# {'name': 'john', 'address': 'Seoul'}
```
