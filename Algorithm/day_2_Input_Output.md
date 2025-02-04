## <mark color="#fbc956">표준 입력</mark>

### 1. input()

- 사용자의 입력을 받는 내장함수
- **특징**
  - 한 줄로 입력 받음
  - 입력값의 타입은 **문자열(str)**
- `int()` , `float()` 등 내장함수 이용해 입력받은 데이터의 타입 변환 가능

```python
num = input()
# 3
print(type(num), num)
# <class 'str'> 3

num = int(input())
# 3
print(type(num), num)
# <class 'int'> 3
```

- **`sys`** : 입력을 빠르게 받을 수 있는 코드

```python
import sys
input = sys.stdin.readline
```

### 2. map(function, iterable) 함수

- 여러 데이터의 타입을 한 번에 변환 가능
- **함수**(function)와 **반복 가능한 자료형**(iterable)을 인자로 받아, 해당 자료형의 각 원소에 해당 함수를 적용한 결과를 묶어서 반환

```python
a, b = map(int, [1.2, 2.5])
c, d, e = map(str, [1, 2, 3])
f, g, h = map(int, "456")
```

---

## <mark color="#fbc956">표준 출력</mark>

### 1. print()

- 데이터 출력 함수, 출력 후 자동적으로 줄바꿈이 이루어짐
- **옵션**
  - `end` : 모든 데이터 출력 후 마지막에 출력할 문자 지정 (기본값: 개행)
  - `sep` : 여러 데이터 출력 시, 각 출력 값들 사이에 넣는 구분 문자 지정 (기본값: 공백)

```python
a, b = 1, 2
c, d, e = 5.0, 3.4, 1.2
f = 'ABC'

# 정수형 변수 1개 출력
print(a)
# 1

# 줄바꿈하지 않고 정수형 변수와 공백을 출력
print(b, end=" ")
# 2

# 실수형 변수 3개 출력 (구분자 : 띄어쓰기)
print(c, d, e)
# 5.0 3.4 1.2

# 실수형 변수 3개 출력 (구분자 : 줄바꿈)
print(c, d, e, sep="\n")
# 5.0
# 3.4
# 1.2

# 문자열 1개 출력
print(f)
# ABC
```

### 2. f-string

- **`f"{동적인 값} 정적인 값"`**
- 파이썬에서 지원하는 문자열 포메팅 기능
- 원하는 형식으로 출력 가능
- 중괄호(`{}`) 변수 또는 수식을 입력해 동적으로 출력값 변경

```python
names = ['john', 'mia', 'marry']

for name in names:
	print(f'나는 {name}입니다.')
# 나는 john입니다.
# 나는 mia입니다.
# 나는 marry입니다.
```
