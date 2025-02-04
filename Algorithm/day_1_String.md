## <mark color="#fbc956">Python String</mark>

### 1. Python String

- 문자열은 **반복 가능**하고(iterable), **불변적**이며(immutable), **순서가 있는**(sequence) 자료형
- 순서가 있어 인덱스 통해 원소 접근 가능
- 불변적이므로 수정하려고 할 경우 에러 발생

```python
word = 'python'

print(word[2])
# T
word[0] = 'J'
# TypeError
```

### 2. 문자열 슬라이싱

- **`문자열[start : end : step]`**
  - 문자열의 `start` 인덱스 이상 `end` 인덱스 미만을 `step` 간격으로 잘라냄
- 슬라이싱 활용해 문자열을 원하는 범위만큼 자를 수 있음
- 문자열은 불변이므로 원본을 수정하는 것은 불가능

```python
word = 'abcdefghi'

print(word[2:5])
# cde
print(word[2:5:2)
# ce
print(word[5:2:-1])
# fed

print(word[:])
# abcdefghi
print(word[::-1])
# ihgfedcba
```

### 3. 회문 검사하기

- **회문(palindrome)**
  - 거꾸로 읽어도 똑같은 단어

1. **파이써닉 코드**

   ```python
   word = input('단어 입력 : ')

   if word == word[::-1]:
   	print('회문입니다.')
   else:
   	print('회문이 아닙니다')
   ```

2. **투 포인터 알고리즘 활용**

   ```python
   word = input('단어 입력 : ')

   left = 0
   right = len(word)-1

   is_palindrome = True
   while left < right:
   	if word[left] == word[right]:
   		left += 1
   		right -= 1
   		continue
   	else:
   		is_palindrome = False
   		break

   print(is_palindrome)
   ```

---

## <mark color="#fbc956">String Method</mark>

### 1. split(기준 문자)

- **`문자열.split(기준문자)`**
- 문자열을 입력된 문자를 기준으로 나누어 리스트로 반환

```python
email = 'alexalgoedu@gmail.com'

split_email = email.split('@')
print(split_email)
# ['alexalgoedu', 'gmail.com']
```

- **기준 문자 지정하지 않을 경우** : 공백

```python
nums = '1 2 3'
print(nums.split())
# ['1', '2', '3']
```

### 2. 삽입할 문자.join(iterable)

- **`삽입 문자.join(iterable)`**
- iterable의 각각 문자 사이에 특정 문자를 삽입한 결과물을 문자열로 반환
  - iterable 각 원소는 모두 문자열이어야 함

```python
word = 'abcdefg'

join_word = "-".join(word)
# a-b-c-d-e-f-g

numbers = ["1", "2", "3", "4", "5", "6"]

join_word = "".join(numbers)
print(join_word)
# 123456
```

### 3. strip(제거할 문자)

- **`문자열.split(제거할 문자)`**
- 문자열의 양쪽 끝에서 특정 문자 제거

```python
word = 'aHello Worlda'

strip_word = word.strip('a')
print(strip_word)
# "Hello World"
```

- **제거할 문자 지정하지 않을 경우** : 공백
- `readline()` 으로 입력받는 경우 오른쪽에 개행문자가 붙어 입력되기 때문에 `strip` 메서드 이용해 제거 후 사용

```python
word = " Hello World "

strip_word = word.strip()
print(strip_word)
# "Hello World"
```

### 4. count(개수를 셀 문자)

- **`문자열.count(개수를 셀 문자)`**
- 문자열에 특정 문자가 몇 개 포함되어 있는지 반환
- 문자뿐만 아니라 문자열의 개수도 확인 가능

```python
word = "banana"

print(word.count("a"))
# 3
print(word.count("na"))
# 2
print(word.count("ana"))
# 1
```

### 5. find(찾는 문자), index(찾는 문자)

- **`문자열.find(찾는 문자)` , `문자열.index(찾는 문자)`**
- 특정 문자가 처음으로 나타나는 인덱스 반환
- **해당 문자 존재하지 않을 경우**
  - `find` 메서드 : -1 반환
  - `index` 메서드 : ValueError 발생

```python
word = "apple"

print(word.find("p"))
# 1
print(word.find("k"))
# -1

print(word.index("p"))
# 1
print(word.find("k"))
# ValueError
```

### 6. replace(기존 문자, 새로운 문자)

- **`문자열.replace(기존 문자, 새로운 문자)`**
- 해당 문자열의 기존 문자들을 새로운 문자로 치환한 새로운 문자열 반환

```python
word = "aaaa"

print(word.replace('a', 'b'))
# bbbb
```

- **새로운 문자가 빈 경우**
  - 빈 문자열로 치환함으로 마치 기존 문자를 삭제한 것과 같은 효과

```python
word = "abcdefg"

replaced_word = word.replace("b", "")
print(replaced_word)
# acdefg
```
