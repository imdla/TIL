## <mark color="#fbc956">기본</mark>



### 표현식**(Expression)**

- **표현식(Expression)**
    - 파이썬에서 값을 만들어내는 코드
    - 값 → 숫자, 수식, 문자열 등
- **문장(Statement)**
    - 표현식이 하나 이상일 경우
    - 자체로 어떤 값을 만들 수 없으면 → 문장아님
- **프로그램(Program)**
    - 문장이 모여서 형성

### 키워드(Keyword)

- 특별한 의미 부여된 단어
- 파이썬에서 특정 의미의 사용으로 예약해 놓은 것
- 이름 정할 때 똑같이 사용 불가
- 대소문자 구별 O
- 특정 단어가 파이썬 키워드인지 확인 가능
    
    ```python
    import keyword
    print(keyword.kwlist)
    ```
    

### 식별자(Identifier)

- **식별자(Identifier)**
    - 프로그래밍 언어에 이름 붙일 때 사용
    - 변수, 함수 등 식별에 사용
- **식별자 생성 조건**
    - 대소문자 구분
    - 문자 A~Z 또는 언더바()로 시작 가능
    - 식별자의 첫 시작 제외, 숫자 사용 가능
    - 특수문자 등은 식별자 사용 불가
    - 띄어쓰기 포함 불가
    - 예약어, 내장 함수, 모듈 이름 사용 불가
    - 함수, 변수, Attribute는 관용적으로 소문자로 시작, 단어들 간 밑줄(_)을 사용해 연결
- **식별자 생성**
    - **스네이크 케이스(Snake Case)** : 언더바(_)를 기호 중간에 붙이기
    - **캐멀 케이스(Camel Case)** : 단어들의 첫 글자 대문자로 지정
    - **구분**
        - 클래스 → 대문자로 시작 : 캐멀 케이스
        - 함수 or 변수 → 소문자로 시작 : 스네이크 케이스
            - 함수 → 뒤에 괄호 붙음
            - 변수 → 뒤에 괄호 없음

### 주석(Comment)

- **주석(Comment)**
    - `#` 기호
    - 프로그램 진행에 영향 없음
    - 프로그램 설명위해 사용
    
    ```python
    # print("hello")
    ```
    

### 출력 함수 print()

- **출력 함수**
    - `print()` : 출력 기능
    - 출력하고 싶은 것들 괄호 안 나열
    
    ```python
    print('Hello World')
    # Hello World
    ```
    

### 자료형(Data Type)

- **자료형(Data Type)**
    - 자료를 기능, 역할 따라 구분
    - 숫자, 문자, 불린(Boolean) 등
- **자료의 형식**
    - `type()` 함수로 확인
    
    ```python
    type(32)             # <class 'int'> -> int: 정수
    type('Hello World')  # <class 'str'> -> str: 문자열
    ```
    

### 문자열(String)

- **문자열(String)**
    - 따옴표로 둘러쌓아 입력하는 글자 나열
        
        ```python
        # 큰 따옴표
        print("Hello World")  # Hello World
        
        # 작은 따옴표
        print('Hello World')  # Hello World
        ```
        
    - 문자열 내부 따옴표 넣기
        
        ```python
        print("'Hello' World")  # 'Hello' World
        print('"Hello" World')  # "Hello" World
        ```
        
- **이스케이프 문자(Escape Character)**
    - 역슬래시 기호와 함께 조합해 사용
    - `\”` : 큰 따옴표
    - `\’` : 작은 따옴표
        
        ```python
        print("\"Hello\" World")  # "Hello" World
        ```
        
    - `\n` : 줄바꿈
        
        ```python
        print("Hello World")
        # Hello
        # World
        ```
        
    - `\t` : 탭
        
        ```python
        print("Hello World")  # Hello		World
        ```
        
- **여러 줄 만들기**
    
    ```python
    # 1번) "\n" 여러 번 사용
    print("Hello\nWorld\nPython")
    # Hello
    # World
    # Python
    
    # 2번) 큰 따옴표 사용
    print("""Hello
    World
    Pyton""")
    # Hello
    # World
    # Pyton
    ```
    

### 문자열 연산

- **숫자와 문자 연산**
    - 숫자 → 사칙연산대한 연산자 적용 가능
    - 집합 → 여러 집합 연산자 적용 가능
    - 문자열 → 연결 연산자로 ‘+’ 사용 가능 (문자 연결)
    
    ```python
    # 두 문자열 연결 -> 새로운 문자
    print("Hello" + " World")  # Hello World
    
    # 문자열과 숫자 사이 사용 불가
    print("Hello" + 100)       # Error
    
    # 문자열과 숫자 연결 시 따옴표 이용 -> 숫자를 문자열로 인식
    print("Hello" + "100")     # Hello100
    ```
    
- **문자열 반복 연산자 : ***
    - 문자열을 숫자와 ‘`*`’ 연산자로 연결 : 문자열 반복
    
    ```python
    print("Hello" * 3)  # HelloHelloHello
    print(20 * "-") # --------------------
    ```
    
- **인덱싱(Indexing)**
    - `[]` 기호
    - 문자열 내부 문자 하나 선택, 대괄호 안 문자 위치 지정
    - 문자열의 특정 위치에 있는 문자 참조
- **슬라이싱(Slicing)**
    - `[:]` 기호
    - 문자열 일부 추출
    - 문자열 선택 연산자로 슬라이싱 해도 원본은 변하지 않음
- **파이썬의 Zero Index 유형**
    - 제로 인덱스(Zero Index) : 숫자 0부터 카운트
        
        
        | H | E | L | L | O |
        | --- | --- | --- | --- | --- |
        | [0] | [1] | [2] | [3] | [4] |
    - 원 인덱스(One Index) : 숫자 1부터 카운트
- **문자 반대 방향 출력**
    - 대괄호 안 숫자 음수로 입력
        
        
        | H | E | L | L | O |
        | --- | --- | --- | --- | --- |
        | [-5] | [-4] | [-3] | [-2] | [-1] |
- **IndexError : index out of range 예외**
    - 리스트/문자열 수를 넘는 요소/글자 선택할 경우 발생
- **대괄호 안 넣는 숫자 둘 중 하나 생략 하는 경우**
    - `[n:]` 앞의 값 생략 : n번째 부터 끝의 문자까지
    - `[:n]` 뒤의 값 생략 : 0번째부터 뒤의 숫자 n번째 앞의 문자까지
- **len() 함수**
    - `len()` : 문자열 길이 구함
    - 괄호 내부 문자열 입력 시 문자열의 문자 개수 구함
    - 중첩된 구조의 함수는 괄호 안쪽부터 먼저 실행
        
        ```python
        print(len("Hello"))
        # 5
        ```
        
---

## <mark color="#fbc956">자료형과 변수</mark>



### 숫자

- **정수형**
    - 정수(Integer) : 소수점이 없는 숫자
- **실수형**
    - 실수(floating point) : 소수점이 있는 숫자
- **숫자 입력**
    - 일반적으로 프로그래밍 언어에서 두 자료형 구분 사용
    - int : 정수, float : 부동 소수점(실수)
    
    ```python
    type(100)  # <class 'int'>
    type(10.0) # <class 'float'>
    ```
    
- **사칙 연산자**
    - 덧셈(`+`), 뺄셈(`-`), 곱셈(`*`), 나눗셈(`/`)
- **정수 나누기 연산자 :** `//`
    - 숫자를 나누고 소수점 이하 자릿수 삭제 후 정수 부분만 남김
- **나머지 연산자 :** `%`
- **제곱 연산자 : `**`**
- **연산자 우선순위**
    - 곱셈, 나눗셈 > 덧셈, 뺄셈
    - 우선순위 바꿀 경우 괄호 이용
- **TypeError 예외**
    - 서로 다른 자료를 연산할 경우

### 변수

- **변수**
    - 값 지정 시 사용하는 식별자
    - 숫자 및 모든 자료형 저장 가능
    - 선언 : 식별자를 변수로 사용
    - 할당 : 해당 변수에 값을 넣음
    - 초기화 : 선언과 할당을 처음 해줌
- **변수 선언**
    - 변수 생성
- **변수 할당**
    - 변수에 값 넣음
    - ‘`=`’ 우변의 값을 좌변에 할당
- **변수 참조**
    - 변수에서 값 꺼내 변수 안에 있는 값 사용

### 연산자

- **복합 대입 연산자**
    - 기본 연산자와 `=` 연산자 함께 구성해 사용
        
        
        | += | 숫자 덧셈 후 대입 |
        | --- | --- |
        | -= | 숫자 뺄셈 후 대입 |
        | *= | 숫자 곱셈 후 대입 |
        | /= | 숫자 나눗셈 후 대입 |
        | %= | 숫자의 나머지 구한 후 대입 |
        | **= | 숫자 제곱 후 대입 |
    - 문자열 역시 복합 대입 연산자 사용 가능
        
        
        | += | 문자열 연결 후 대입 |
        | --- | --- |
        | *= | 문자열 반복 후 대입 |

### Input() 함수

- **Input() 함수**
    - `input()` : 명령 프롬프트에서 사용자로부터 데이터 입력 받을 때 사용
- **사용자 입력 받기**
    - 프롬프트 함수 : `input()` 함수 괄호 안 입력 내용
    - 블록(block) : 프로그램이 실행 중 잠시 멈추는 것
    - 명령 프롬프트에서 글자 입력 후 [Enter] 클릭
- **결과 산출(리턴값)**
    - 다른 변수에 대입해 사용 가능
- **입력 자료형**
    - 결과는 무조건 문자열 자료형
    
    ```python
    a = input("값 입력: ")  # 값 입력: 100
    print("a= ", a)         # a= 100
    print(type(a))          # <class 'str'>
    ```
    

### 자료형 변환

- **형 변환(Type Casting)**
    - `int()` 함수 : 문자열 → 정수 자료형으로 변환
    - `float()` 함수 : 문자열 → 부동소수점 자료형으로 변환
        
        ```python
        a = int(input("a 값: "))  # a 값: 100
        b = int(input("b 값: "))  # b 값: 200
        print("a + b = ", a+b)    # a + b = 300
        ```
        
- **ValueError 예외**
    - 변환할 수 없는 것을 변환하려 한 경우
    - ex. 문자 → 숫자로 변환, 소수점 있는 숫자 형식의 문자열 → 숫자 변환

### Str() 함수

- **Str() 함수**
    - `Str()` : 숫자 → 문자열로 변환
    
    ```python
    a = int(input("a 값: "))  # a 값: 100
    b = str(input("b 값: "))  # b 값: 200
    print("a = ", a, ", 자료형 = ", type(a))  # a = 100, 자료형 = <class 'int'>
    print("b = ", b, ", 자료형 = ", type(b))  # b = 200, 자료형 = <class 'str'>
    ```
    
---

## <mark color="#fbc956">연산문과 조건문</mark>



### Format() 함수

- **format() 함수**
    - `format()` : 숫자 → 문자열로 변환
    - 중괄호{} 포함한 문자열 뒤  `.format()` 함수 사용
    - 중괄호 개수 = format() 함수 안 매개변수 개수
        
        ⇒ 중괄호 기호는 fotmat() 함수 안 매개변수와 차례대로 대치, 숫자 → 문자열로 변환
        
    - IndexError 예외 : 중괄호 기호 개수 > format() 함수 매개변수일 경우
    
    ```python
    a = "{}".format(10)
    print("a = ", a, ", 자료형 = ", type(a)) # a = 10, 자료형 = <class 'str'>
    ```
    
- **F-string**
    - `f”문자열 {변수} 문자열”`
    - 문자열 맨 앞 f + 중괄호 {} 안에 직접 변수/값 넣음
    
    ```python
    a = 2024
    b = 01
    c = 02
    str = f"{a}년 {b}월 {c}일"
    print(str) # 2024년 01월 02일
    ```
    

### 대소문자 변환

- **Upper() 함수**
    - `upper()` : 문자의 알파벳을 대문자로 변경
- **Lower() 함수**
    - `lower()` : 문자의 알파벳 소문자로 변경
    
    ```python
    a = "Hello".upper()
    print("a = ", a)    # HELLO
    
    b = "World".lower()
    print("b = ", b)    # world
    ```
    

### Strip() 함수

- **Strip() 함수**
    - `strip()` : 문자열 양 옆 공백 제거
    - `lstrip()` : 문자열 왼쪽 공백 제거
    - `rstrip()` : 문자열 오른쪽 공백 제거

### Is~ 함수

- **Is~ 함수**
    - 문자열이 소문자/알파벳/숫자로만 구성되어 있는지 확인
    
    | isalnum() | 문자열이 알파벳 또는 숫자로만 구성되어있는지 확인 |
    | --- | --- |
    | isalpha() | 문자열이 알파벳으로만 구성되어 있는지  확인 |
    | isdentifier() | 문자열이 식별자로 사용할 수 있는 것인지 확인 |
    | isdecimal() | 문자열이 정수 형태인지 확인 |
    | isdigit() | 문자열이 숫자로 인식될 수 있는 것인지 확인 |
    | isspace() | 문자열이 공백으로만 구성되어 있는지 확인 |
    | islower() | 문자열이 소문자로만 구성되어 있는지 확인 |
    | isupprer() | 문자열이 대문자로만 구성되어 있는지 확인 |

### Find()와 Rfind()

- **Find()**
    - `find()` : 왼쪽부터 찾아 첫 등장 위치 찾음
    
    ```python
    a = "가나다라마바사"
    index = a.find("다")
    print(index)          # 2
    ```
    
- Rfind()
    - `rfind()` : 오른쪽부터 찾아 첫 등장 위치 찾음
    
    ```python
    a = "가나다라마바사"
    index = a.rfind("다")
    print(index)          # 5
    ```
    

### In 연산자

- **In 연산자**
    - “`찾을 문자열 in 문자열`”
    - 문자열 내부 어떤 문자열 있는지 여부 확인 시 사용 → 결과 `True`, `False`로 출력
    
    ```python
    a = "가나다라마바사"
    print("다" in a)          # True
    ```
    

### Split() 함수

- **Split() 함수**
    - `split()` : 문자열을 특정한 문자로 자름 → 결과 리스트(list)로 출력
    
    ```python
    str = "a b c d e"
    print(str.split(""))
    # ['a', 'b', 'c', 'd', 'e']
    ```
    

### 연산자

- **Boolean**
    - True와 False 값만 가질 수 있음
    
    | == | 같다 |
    | --- | --- |
    | != | 다르다 |
    | < | 작다 |
    | > | 크다 |
    | <= | 작거나 같다 |
    | >= | 크거나 같다 |
- **논리 연산자**
    - Boolean끼리 논리 연산자 사용 가능
    - `not` 연산자 : 단항 연산자, True와 False를 반대로 바꿈
    
    | not | 아니다 |
    | --- | --- |
    | and | 그리고 |
    | or | 또는 |
- **And 연산자**
    - `and` 연산자 : 양쪽 변의 값이 모두 참일 때만 결과 True
        
        ```python
        print(True and True)   # True
        print(True and False)  # False
        ```
        
    - `or` 연산자 : 양쪽 변의 값 중 하나라도 참일 때 결과 True
        
        ```python
        print(True or True)   # True
        print(True or False)  # True
        ```
        

### 조건문

- **IF 조건문**
    - 조건에 따라 코드 실행하거나 실행하지 않도록 할 때 사용
    - 조건을 분기하는 방식
    
    ```python
    if True:
    	print("참")
    	# 참
    
    if False:
    	print("거짓")
    ```
    

### Datetime

- **날짜, 시간 활용**
    
    ```python
    import datetime
    
    now = datetime.datetime.now()
    print(now)
    # 2024-01-02 14:37:03.951614 (현재 날짜, 시간 출력)
    
    # year : 연도 출력
    print(now.year)   # 2024
    # month : 월 출력
    print(now.month)  # 1
    # day : 일 출력
    print(now.day)    # 2
    # hour : 시 출력
    print(now.hour)   # 14
    # minute : 분 출력
    print(now.minute) # 37
    # second : 초 출력
    print(now.second) # 3
    ```
    
- **오전, 오후 구분**
    
    ```python
    import datetime
    
    now = datetime.datetime.now()
    print(now)
    # 2024-01-02 14:37:03.951614 (현재 날짜, 시간 출력)
    
    if now.hour < 12:
    	print('오전 : 현재 시간 {}시 {}분'.format(now.hour, now.minute))
    if now.hout >= 12:
    	print('오전 : 현재 시간 {}시 {}분'.format(now.hour, now.minute))
    ```
    
- **계절 구분**
    
    ```python
    import datetimr
    now = datetime.datetime.now()
    nm = now.month
    
    if 3 <= nm <= 5:
    	print("이번 달은 {}월, 봄입니다.".format(nm))
    if 6 <= nm <= 8:
    	print("이번 달은 {}월, 여름입니다.".format(nm))
    if 9 <= nm <= 11:
    	print("이번 달은 {}월, 가을입니다.".format(nm))
    if 12 = nm or 1 <= nm <= 2:
    	print("이번 달은 {}월, 겨울입니다.".format(nm))	
    ```
    

### Else 구문

- **Else 구문**
    - if 조건문 뒤에 사용
    - if 조건문이 거짓일 때 실행
    
    → 조건문이 오로지 두 가지로만 if else 구문을 사용하면 조건 비교를 한번만 하므로 이전 코드보다 효과적
    
    ```python
    a = input("정수 입력: ")
    a = int(a)
    
    # 홀수/짝수 알아보기
    if a % 2 == 0:
    	print("짝수")
    else:
    	print("홀수")
    ```
    
- **Elif 구문**
    - 세 개 이상의 조건 연결해 사용
    - if 조건문과 else 구문 사이에 입력
    
    ```python
    import datetimr
    now = datetime.datetime.now()
    nm = now.month
    
    if 3 <= nm <= 5:
    	print("이번 달은 {}월, 봄입니다.".format(nm))
    elif 6 <= nm <= 8:
    	print("이번 달은 {}월, 여름입니다.".format(nm))
    elif 9 <= nm <= 11:
    	print("이번 달은 {}월, 가을입니다.".format(nm))
    else:
    	print("이번 달은 {}월, 겨울입니다.".format(nm))
    ```
    

### Pass 키워드

- **Pass 키워드**
    - 일반적으로 if문 다음에 실행문 넣지 않아도 에러 발생하지 않음
    - 파이썬에서는 Indentation Error 발생
    - 조건문 다음 빈칸 놔두고자 할 때 → Pass 키워드 넣어 에러 나지 않도록 함
    - 나중에 구현하고자 구문 비워둘 때 사용
    
    ```python
    a = int(input("첫번째 값 : "))
    b = int(input("두번째 값 : "))
    print(a, b)
    
    if a > b:
    	# 나중에 구현
    	pass
    else:
    	# 나중에 구현
    	pass
    ```