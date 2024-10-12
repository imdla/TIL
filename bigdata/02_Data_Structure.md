## <mark color="#fbc956">리스트</mark>



### 리스트(List)

- **리스트(List)**
    - 0에서 시작, 순서 있는 목록
    - 자료들을 모아 사용할 수 있게 함
    - 대괄호`[]` 내 자료들 넣어 선언
    - 여러가지 자료 저장 가능
        - 숫자, 문자, 숫자 또는 문자 혼용
- **요소(Element)**
    - 리스트 대괄호 내부에 넣는 자료
    
    ```python
    print([1, 2, 3]) # 숫자 구성
    print(["H", "E", "L", "L", "O"]) # 문자 구성
    print([1, 2, 3, "HELLO", True]) # 여러 자료형 구성
    ```
    
    ⇒ 대괄호 안 각각의 요소 쉼표로 구분
    
- **요소 지정**
    - `리스트 이름[인덱스]`
    - 인덱스 : 리스트 내부 값의 위치
    
    ```python
    a = [1, 2, 3, "HEELO", True]
    print("a[0] : ", a[0]) # a[0] : 1
    print("a[3] : ", a[3]) # a[3] : HELLO
    print("a[4] : ", a[4]) # a[4] : True
    
    # 음수로 뒤에서부터 요소 선택
    print("a[-1] : ", a[-1]) # a[-1] : True
    
    # 슬라이싱으로 일부 자료 추출
    print("a[3:] : ", a[3:]) # a[3:] : HELLO, True
    
    # 특정 요소 변경
    a[0] = "WORLD"
    print("a[0] : ", a[0]) # a[0] : WORLD
    
    # 리스트 길이 < 인덱스 => 오류 발생
    print(a[10])
    
    # 슬라이스 이용한 인덱스 요소 접근 => 오류 발생X
    print(a[5:1000]) # []
    ```
    
- **리스트 접근 연산자의 이중 사용**
    
    ```python
    # 리스트 여러 개를 가지는 리스트
    b = [ [1, 2, 3],
    			[4, 5, 6],
    			[7, 8, 9]
    ]
    
    print(b[0]) # [1, 2, 3]
    print(b[0][1]) # 2
    ```
    

### 리스트 연산자

- **리스트 연산자**
    - `+` : 연결
    - `*` : 반복
    - `len()` : 요소 개수
    - `in` 연산자 : 리스트 내부에 해당 값이 있는지 확인
    - `not in` 연산자 : 리스트 내부에 해당 값이 없는지 확인
    
    ```python
    a = [1, 2, 3]
    b = [4, 5, 6]
    
    # 연결
    print(a + b) # [1, 2, 3, 4, 5, 6]
    
    # 반복
    print(a * 2) # [1, 2, 3, 1, 2, 3]
    
    # 요소 개수
    print(len(a)) # 3
    
    # 해당 값 있는지
    print(10 in a) # False
    
    # 해당 값 없는지
    print(10 not in a) # True
    ```
    
    - 비파괴적 처리 : 연산 실행 전,후 원본에 변화 없음
        
        ```python
        a = [1, 2, 3]
        b = [4, 5, 6]
        print(a + b) # [1, 2, 3, 4, 5, 6]
        
        print(a) # [1, 2, 3]
        print(b) # [4, 5, 6]
        ```
        
- **리스트 요소 추가**
    - `append()` : 리스트 뒤 요소 추가
    - `insert(추가할 위치, 추가할 값)` : 리스트 특정 위치에 요소 추가
    - `extend()` : 리스트 뒤 새로운 리스트 모든 요소 추가
    - 파괴적 처리 : 함수 실행 전, 후 원본 리스트 자체에 변화 있음
    
    ```python
    a = [1, 2, 3]
    a.append(4)
    print(a) # [1, 2, 3, 4]
    
    a.insert(1, 0)
    print(a) # [1, 0, 2, 3, 4]
    
    a. extend([5, 6, 7])
    print(a) # [1, 0, 2, 3, 4, 5, 6, 7]
    ```
    
- **리스트 요소 제거**
    - `del` : 인덱스로 요소 제거
        
        ```python
        a = [1, 2, 3, 4, 5, 6]
        del a[1]
        print(a) # [1, 3, 4, 5, 6]
        
        del a[0:3]
        print(a) # [5, 6]
        ```
        
    - `pop()`
        
        ```python
        a = [1, 2, 3, 4, 5, 6]
        a.pop(1)
        print(a) # [1, 3, 4, 5, 6]
        
        a.pop()
        print(a) # [1, 3, 4, 5]
        ```
        
    - `remove()` : 값으로 제거
        
        ```python
        a = [1, 2, 3, 5, 5, 6]
        a.remove(3)
        print(a) # [1, 2, 5, 5, 6]
        
        # 동일한 값일 경우 맨 처음 하나만 제거
        a.remove(5)
        print(a) # [1, 2, 5, 6]
        ```
        
    - `clear()` : 내부 요소 모두 제거
        
        ```python
        a = [1, 2, 3, 4, 5, 6]
        a.clear()
        print(a) # []
        ```
        

### For 반복문

- **For 반복문**
    - 특정 코드 반복 실행 시 사용
    - 문자열, 리스트, 딕셔너리 등과 조합해 사용
    
    ```python
    a = [1, 2, 3, 4, 5, 6]
    print(a) # [1, 2, 3, 4, 5, 6]
    
    for i in a:
    	print(i) # 1 2 3 4 5 6
    ```
    
- **조건문과 반복문 결합**
    
    ```python
    a = [100, 21, 43, 22, 214]
    
    for i in a:
    	if i >= 10:
    		print("10이상 수 : {}".format(i))
    		# 10이상의 수 : 100
    		# 10이상의 수 : 21
    		# 10이상의 수 : 43
    		# 10이상의 수 : 22
    		# 10이상의 수 : 214
    ```
    
- **다양한 사용 예**
    - 자리수 구하기
        
        ```python
        a = [100, 21, 43, 214, 9]
        
        for i in a:
        	print("{} : {}자리수".format(i, len(str(i))))
        	# 100 : 3자리수
        	# 21 : 2자리수
        	# 43 : 2자리수
        	# 214 : 3자리수
        	# 9 : 1자리수
        ```
        
    - 홀수 짝수 구분
        
        ```python
        a = [100, 9, 21, 4, 5, 43, 22, 214]
        
        for i in a:
        	if i % 2 == 0:
        		print("{} : 짝수".format(i))
        	else:
        		print("{} : 홀수".format(i))
        		# 100 : 짝수
        		# 9 : 홀수
        		# 21 : 홀수
        		# 4 : 짝수
        		# 5 : 홀수
        		# 43 : 홀수
        		# 22 : 짝수
        		# 214 : 짝수
        ```
        
---

## <mark color="#fbc956">딕셔너리</mark>



### 딕셔너리(Dictionary)

- **딕셔너리(Dictionary)**
    - 키(Key) 기반, 값(Value)을 저장하는 복합 자료형
    - **키** : 딕셔너리 내부 값에 접근 시 사용
    - **값** : 딕셔너리 내부 각 값에 해당하는 내용
    
    | **자료형** | **위치** | **선언 형식** | **개념** |
    | --- | --- | --- | --- |
    | 리스트 | 인덱스 | `a = [ ]` | 인덱스 기반 값 저장 |
    | 딕셔너리 | 키 | `a = { }` | 키 기반 값 저장 |
    
    ```python
    listEx = [1, 2, 3]
    listEx[0] # => 인덱스
    
    dictEx = {
    	"key1" : "value1",
    	"key2" : "value2",
    	"key3" : "value3"
    }
    dictEx["key1"] # => 키
    ```
    
- **딕셔너리 선언**
    - 중괄호`{}` 사용, `키 : 값` 형태를 쉼표로 연결
- **딕셔너리 요소 접근**
    - 특정 키 값 출력 : `딕셔너리 이름[키]`
    
    ```python
    a = { "key1" : "value1", "key2" : "value2" }
    print(a["key1"]) # value1
    print(type(a)) # <class 'dict'>
    ```
    
- **딕셔너리 요소 입력 및 출력**
    - 내부 값 다양한 자료 입력 가능
    
    ```python
    a = {
    	"name" : "대학",
    	"type" : ["자연", "사회", "과학", "국어"],
    	"dept" : [1, 2, 3, 4]
    }
    
    print(a["type"]) # ["자연", "사회", "과학", "국어"]
    print(a["type"][1]) # 사회
    ```
    
- **딕셔너리 요소 변경**
    
    ```python
    a = {
    	"name" : "대학",
    	"type" : ["자연", "사회", "과학", "국어"],
    	"dept" : [1, 2, 3, 4]
    }
    
    a["name"] = "대학교"
    print(a["name"]) # 대학교
    ```
    
- **딕셔너리와 for 반복문**
    
    ```python
    a = {
    	"name" : "대학",
    	"type" : ["자연", "사회", "과학", "국어"],
    	"dept" : [1, 2, 3, 4]
    }
    
    for k in a:
    	print("key : ", k)
    	# key : name
    	# key : type
    	# key : dept
    	
    for k in a:
    	print(k, ":", a[k])
    	# "name" : "대학",
    	# "type" : ["자연", "사회", "과학", "국어"],
    	# "dept" : [1, 2, 3, 4]
    ```
    

### 딕셔너리 값 추가 · 제거

- **딕셔너리 값 추가**
    - 키 기반 값 입력
    
    ```python
    a = {
    	"name" : "대학",
    	"type" : ["자연", "사회", "과학", "국어"],
    	"dept" : [1, 2, 3, 4]
    }
    
    a["gender"] = ["남자", "여자"]
    
    for k in a:
    	print("{} : {}".format(k, a[k]))
    	# "name" : "대학",
    	# "type" : ["자연", "사회", "과학", "국어"],
    	# "dept" : [1, 2, 3, 4]
    	# "gender" : ["남자", "여자"]
    ```
    
- **딕셔너리 값 제거**
    - `del` : 값 제거
    
    ```python
    a = {
    	"name" : "대학",
    	"type" : ["자연", "사회", "과학", "국어"],
    	"dept" : [1, 2, 3, 4]
    }
    
    a["gender"] = ["남자", "여자"]
    del a["gender"]
    
    for k in a:
    	if type(a[k]) is list:
    		for j in a[k]:
    			print(k, ":", j)
    	else:
    		print(k, ":", a[k])
    	# name : 대학
    	# type : 자연
    	# type : 사회
    	# type : 과학
    	# type : 국어
    	# dept : 1
    	# dept : 2
    	# dept : 3
    	# dept : 4
    ```
    

### 딕셔너리 키 확인

- **In 키워드**
    - 사용자로부터 접근하고자 하는 키 입력 받은 후 → 존재하는 경우에만 접근해 값 출력
    
    ```python
    a = {
    	"name" : "대학",
    	"type" : ["자연", "사회", "과학", "국어"],
    	"dept" : [1, 2, 3, 4],
    	# "gender" : ["남자", "여자"]
    }
    
    if "gender" in a:
    	print(a["gender"])
    else:
    	print("'gender' 키 없음")
    	# 'gender' 키 없음
    ```
    
- **Get() 함수**
    - `get()` : 딕셔너리 키로 값 추출, 존재하지 않는 키 접근 → None 출력
    
    ```python
    a = {
    	"name" : "대학",
    	"type" : ["자연", "사회", "과학", "국어"],
    	"dept" : [1, 2, 3, 4],
    	"gender" : ["남자", "여자"]
    }
    
    print(a["name"]) # 대학
    print(a.get("dept")) # [1, 2, 3, 4]
    print(a.get("num")) # None
    ```
    

### 딕셔너리 활용

- **리스트와 딕셔너리 조합**
    
    ```python
    a = [
    	{ "name" : "홍길동", "type" : "과학" },
    	{ "name" : "유관순", "type" : "국어" },
    	{ "name" : "이순신", "type" : "자연" },
    	{ "name" : "김구", "type" : "사회" }
    ]
    
    for i in a:
    	print("이름 : {}, 학과 : {}".format(i["name"], i["type"]))
    	# 이름 : 홍길동, 분야 : 과학
    	# 이름 : 유관순, 분야 : 국어
    	# 이름 : 이순신, 분야 : 자연 
    	# 이름 : 김구, 분야 : 사회
    ```
    
- **리스트와 딕셔너리 요소 개별로 분리 출력**
    
    ```python
    a = {
    	"name" : "대학",
    	"grade" : 1,
    	"type" : ["자연", "사회", "과학"],
    	"class" : {
    		"floor" : 2,
    		"place" : "강의실"
    	}
    }
    
    for i in a:
    	if type(a[i]) is list:
    		for j in a[i]:
    			print(i, ":", j)
    	elif type(a[i]) is dict:
    		for j in a[i]:
    			print(f"{i} : {j} = {a[i][j]}")
    	else:
    		print(i, ":", a[i])
    		
    	# name : 대학
    	# grade : 1
    	# type : 자연
    	# type : 사회
    	# type : 과학
    	# class : floor = 2
    	# class : place = 강의실
    ```

---    

## <mark color="#fbc956">범위와 문자열 함수</mark>



### 범위(Range)

- **범위(Range)**
    - 정수의 범위 나타냄
    - 특정 횟수만큼 반복해 사용할 때 for 반복문과 조합해 사용
    
    | **매개변수의 개수** | **의미** | **예** |
    | --- | --- | --- |
    | 1개 | 0부터 A-1까지 정수로 범위 만듦 | `range(A)` |
    | 2개 | A부터 B-1까지 정수로 범위 만듦 | `range(A, B)` |
    | 3개 | A부터 B-1까지 정수로 범위 만들며 숫자가 C만큼의 차이 가짐 | `range(A, B, C)` |
    
    ```python
    # 매개변수의 개수 : 1개
    a = range(6)
    print(a) # range(6)
    print(list(a)) # [0, 1, 2, 3, 4, 5]
    
    # 매개변수의 개수 : 2개
    b = range(1, 6)
    print(list(b)) # [1, 2, 3, 4, 5]
    
    # 매개변수의 개수 : 3개
    c = range(1, 6, 2)
    print(list(c)) # [1, 3, 5]
    ```
    
- **나눗셈을 매개변수로 사용한 경우**
    - 오류 발생
    
    ```python
    # 나눗셈을 매개변수로 사용한 경우 -> 오류 발생
    a = range(0, 10/2)
    print(list(a))
    
    a = range(0, 10//2)
    print(list(a)) # [0, 1, 2, 3, 4]
    ```
    
- **for 반복문 조합**
    
    ```python
    for i in range(5)
    	print(i) # 0, 1, 2, 3, 4
    ```
    
- **범위 활용**
    - 키와 값으로 이루어진 리스트로 딕셔너리 만들기
        
        ```python
        k = ["name", "grade", "type", "class"]
        v = ["대학", 1, "자연", "강의실"]
        d = {}
        
        for i in range(len(k)):
        	d[k[i]] = v[i]
        
        print(d)
        # {'name':'대학', 'grade':1, 'type':'자연', 'class':'강의실'}
        ```
        
    - 리스트 반복 횟수 카운트
        
        ```python
        a = [1, 2, 3, 4, 5]
        
        # 방법 1 : 카운트 변수 활용
        cnt = 1
        for i in a:
        	print(f"{cnt}번째 : {i}")
        	cnt += 1
        	
        # 방법 2 : range
        for i in range(len(a)):
        	print(f"{i+1}번째 : {a[i]}")
        	
        # 방법 3 : 튜플, enumerate() 함수
        for (i, v) in enumerate(a):
        	print(f"{i+1}번째 : {v}")
        ```
        

### 역반복문

- **역반복문**
    - 큰 숫자 → 작은 숫자로 반복문 적용
    - `range()` 함수의 매개변수 3개 이용
    - `reversed()` 함수 사용
    
    ```python
    # 방법 1 : range() 함수의 매개변수 3개 이용
    for i in range(5, -1, -1):
    	print(i) # 5, 4, 3, 2, 1, 0
    	
    # 방법 2 : reserved() 함수 사용
    for i in reversed(range(6)):
    	print(i) # 5, 4, 3, 2, 1, 0
    ```
    

### While 반복문

- **While 반복문**
    - 조건식 기반 특정 코드 반복 실행 시 사용
    - 구성
        - count 변수 : 값 변화시킬 수 있는 변수
        - 종료 조건식 : 반복문 종료
        - 증가 연산자 or 감소 연산자
    
    ```python
    i = 0
    while i < 5:
    	print(i)
    	i += 1
    	
    	# 0, 1, 2, 3, 4
    ```
    
- **활용**
    - 리스트 내부 해당 값 여러 개 제거
        
        ```python
        a = [1, 2, 3, 1, 2, 3, 1, 2, 3]
        
        while 1 in a:
        	a.remove(1)
        print(a) # [2, 3, 2, 3, 2, 3]
        ```
        
- **Timestamp : 시간 관련 기능**
    - 1970년 1월 1일 0시 0분 0초부터 경과시간
    
    ```python
    import time
    
    a = time.time()
    print(a)
    
    while a + 3 > time.time():
    	pass
    
    print('대기 종료')
    ```
    
- **Break  키워드**
    - 반복문 벗어날 때 사용
    
    ```python
    i = 0
    
    while True:
    	print(i, "번째 반복")
    	i += 1
    	a = input("종료(y) : ")
    	if a in ["Y", "y"]:
    		print("반복 종료")
    		break
    ```
    
- **Continue 키워드**
    - 현재 반복 생략, 다음 반복으로 이동
    - 들여쓰기 단계 줄일 수 있음
    
    ```python
    a = [1, 45, 8, 32, 89, 64, 27]
    
    # 방법 1 : continue 사용
    for i in a:
    	if i < 10:
    		continue
    	print(i)
    
    # 방법 2 : 직접 범위 설정
    for i in a:
    	if i >= 10:
    		print(i)
    		
    # 방법 3 : pass 사용
    for i in a:
    	if i < 10:
    		pass
    	else:
    		print(i)
    ```
    

### 문자열, 리스트, 딕셔너리의 기본 함수

- **리스트에 적용 가능한 함수**
    - `min()`, `max()`, `sum()` 함수
    
    ```python
    a = [58, 24, 1, 19, 145]
    
    print('최소값 : ', min(a)) # 최소값 : 1
    print('최대값 : ', max(a)) # 최대값 : 145
    print('합계 : ', sum(a))   # 합계 : 247
    ```
    
- **Reversed() 함수**
    - `reversed()` : 리스트에서 요소의 순서 뒤집음, 일회용
    
    ```python
    a = [0, 1, 2, 3, 4, 5]
    
    b = reversed(a)
    print(b)          # <list_reverseiterator object at 0x00000191E10EAB30>
    print(list(b))    # [5, 4, 3, 2, 1, 0]
    
    # 재실행 시 빈 리스트 출력 -> 일회용
    print(list(b))    # []
    
    ```
    
- **일회용 함수**
    - 변수에 넣을 경우 미작동
    
    ```python
    a = [0, 1, 2, 3, 4, 5]
    b = reversed(a)
    
    # 작동
    for i in b:
    	print(i, end=' ')
    	# 5 4 3 2 1 0
    	
    # 미작동
    for i in b:
    	print(i, end=' ')
    ```
    
- **Enumerate() 함수**
    - `enumerate()` : 리스트 요소 반복 시 인덱스와 값을 함께 사용해 반복문 활용 가능
    
    ```python
    a = [100, 50, 20, 10, 5]
    
    print(enumerate(a))         # <enumerate object at 0x000001DA9A1F1E40>
    print(list(enumerate(a)))   # [(0, 100), (1, 50), (2, 20), (3, 10), (4, 5)]
    
    for (i, e) in enumerate(a):
    	print(f"{i}번째 요소 : {e}")
    	
    	# 0번째 요소 : 100
    	# 1번째 요소 : 50
    	# 2번째 요소 : 20
    	# 3번째 요소 : 10
    	# 4번째 요소 : 5
    ```
    
- **딕셔너리와 items() 함수의 조합**
    - `items()` : 키와 값을 함께 사용해 반복문 사용 가능한 딕셔너리 함수
    - 키와 값을 조합해 반복문 작성 가능
    
    ```python
    a = {
    	"k1" : "v1",
    	"k2" : "v2",
    	"k3" : "v3"
    }
    
    for k, v in a.items():
    	print("{}키 값 : {}".format(k, v))
    	
    	# k1키 값 : v1
    	# k2키 값 : v2
    	# k3키 값 : v3
    ```