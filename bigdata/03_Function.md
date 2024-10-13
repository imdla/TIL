## <mark color="#fbc956">파이썬 함수</mark>

### 1. 파이썬 함수

- **함수 기본 개념**

  - 함수 호출 : 함수 사용
  - 매개변수 : 함수 호출 시 괄호 내부에 넣는 자료
  - 리턴값 : 함수 호출로 최종 나오는 결과

  ```python
  # 함수 선언
  def func():
  	print('<함수 호출>')

  # 함수 호출
  func() # <함수 호출>
  ```

### 2. 매개변수

- **매개변수** : 함수가 받을 수 있는 인자

  ```python
  def func(a):
  	print(str(a) + "함수 호출")

  func(1)
  # 1함수 호출
  ```

- **기본 매개변수(Default)**

  - 매개변수 값을 입력하지 않았을 경우 매개변수에 들어가는 기본 값
  - 기본 매개변수는 마지막에 입력

  ```python
  def func(a , b = 2):
  	print(str(a) + "함수 호출")
  	print(str(b) + "함수 호출")

  func(1, 3)
  # 1함수 호출
  # 3함수 호출

  func(1)
  # 1함수 호출
  # 2함수 호출
  ```

  - 기본 매개변수가 일반 매개변수 보다 앞설 때, 기본 매개변수 의미 사라짐

- **가변 매개변수**

  - 매개변수를 원하는만큼 받을 수 있는 함수
  - 가변 매개변수 뒤 일반 매개변수 올 수 없음
  - 가변 매개변수는 하나만 사용 가능

  ```python
  def func(a, *b):
  	print(a)
  	print(b)

  func(0, 1, 2, 3)
  # 0
  # (1, 2, 3)
  ```

- **함수 매개변수 생성**

  - 일반 매개변수 - 가변 매개변수 - 기본 매개변수 순서

  ```python
  def func(a, *b, c = 5):
  	print(a)
  	print(b)
  	print(c)

  func(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  # 0
  # (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  # 5
  ```

- **키워드 매개변수**

  - 매개변수 이름을 지정해 입력하는 매개변수

  ```python
  def func(a, *b, c = 5):
  	print(a)
  	print(b)
  	print(c)

  func(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, c = 10)
  # 0
  # (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  # 1
  ```

### 3. Return 키워드

- **Return 키워드**

  - `return` : 함수를 실행했던 위치로 돌아가게 함

  ```python
  def func(a, b, c):
  	print(str(a) + "함수 호출")
  	print(str(b) + "함수 호출")
  	return
  	print(str(c) + "함수 호출")

  func(1, 2, 3)
  # 1함수 호출
  # 2함수 호출
  ```

- **Return Value**

  - **리턴값(Return Value)** : 함수의 결과

  ```python
  def func(a, b, c):
  	print(str(a) + "함수 호출")
  	print(str(b) + "함수 호출")
  	return a + b + c
  	print(str(c) + "함수 호출")

  func(1, 2, 3)
  # 1함수 호출
  # 2함수 호출
  # 6
  ```

- **리턴값 없을 경우**

  ```python
  def func(a, b, c):
  	print(str(a) + "함수 호출")
  	print(str(b) + "함수 호출")
  	# 리턴값 없음
  	return
  	print(str(c) + "함수 호출")

  print(func(1, 2, 3))
  # 1함수 호출
  # 2함수 호출
  # None
  ```

### 4. 튜플(Tuple)

- **튜플(Tuple)**

  - 리스트와 비슷한 자료형으로 소괄호`()` 이용해 선언
  - 한번 결정된 요소 바꿀 수 없음(리스트와 차이점)

  ```python
  # 리스트
  l = [1, 2, 3]

  print(l[0])  # 1
  l[0] = 10
  print(l[0])  # 10

  # 튜플
  t = (1, 2, 3)

  print(t[0])  # 1
  # t[0] = 10 -> Type Error 발생
  ```

- **튜플 사용**

  - 한번에 여러 개 할당
  - 괄호 없이 사용 가능

  ```python
  # 리스트
  [a, b] = [10, 20]
  # 튜플
  (c, d) = [10, 20]

  print(a, b, c, d)  # 10 20 10 20
  ```

- **튜플 활용**

  - 변수값 교환 가능

    ```python
    a, b = 10, 20
    print(a, b)  # 10 20

    a, b = b, a
    print(a, b)  # 20 10
    ```

  - 튜플과 함수 함께 활용해 여러 값 리턴

    ```python
    def func():
    	return (10, 20)

    a, b = func()
    print("a = ", a, "b = ", b)  # a =  10 b =  20
    ```

  - 리스트의 반복문 출력

    ```python
    a = [1, 2, 3, 4, 5]

    for (i, v) in enumerate(a):
    	print(f"{i}번째 요소 값 : {v}")

    	# 0번째 요소 값 : 1
    	# 1번째 요소 값 : 2
    	# 2번째 요소 값 : 3
    	# 3번째 요소 값 : 4
    	# 4번재 요소 값 : 5
    ```

### 5. 고급 함수

- **콜백(Callback) 함수**

  - 함수의 매개변수로 함수 전달

  ```python
  def func(func2):
  	func2()

  def test():
  	print("test")

  func(test) # test
  ```

- **람다(Lambda) 함수**

  - `“lambda 입력 매개변수 : 결과 리턴값”`
  - `def`와 같은 함수와 동일 기능
    - `def 함수 이름(변수) : return 결과값` 과 같이 복잡할 필요 없음
    - 복잡한 함수의 경우 → 기존 함수 사용 권장
  - 한줄 내 간편 사용
  - lambda 적용한 `map`, `apply` 함수 사용 효율적

  ```python
  # 기존 함수
  def sumNum(a, b):
  	return a + b
  print(sumNum(1, 2))  # 3

  # lambda 함수
  print((lambda a, b : a + b)(1, 2)) # 3
  ```

---

## <mark color="#fbc956">파일 처리</mark>

### 1. 파일 처리

- **텍스트 파일 처리**
  - 파일 열기(open) - 파일 읽기(read) - 파일 쓰기(write)
- **파일의 열고 닫기**
  - `open()` 함수
    | 모드 | | 설명 |
    | ---- | ----------- | ------------- |
    | w | write 모드 | 새로 쓰기 |
    | a | append 모드 | 뒤에 이어쓰기 |
    | r | read 모드 | 읽기 |
  - `closed()` 함수
- **`with` 키워드**
  - 조건문과 반복문 들어가다보면 파일 열고 닫지 않는 실수 발생 가능
  - `with` 구문 종료 시 파일 자동 닫음
  ```python
  with open("사용할 파일명.txt", "w") as f:
  	f.write("파일에 새로 쓸 내용")
  ```

---

## <mark color="#fbc956">파이썬 모듈</mark>

### 1. 파이썬 모듈

- **모듈(Module)**
  - 코드 분리 및 공유 기능
  - 이미 만들어둔 함수와 변수 사용 가능
    | **표준 모듈** | 파이썬에 기본적으로 내장되어 있는 모듈 |
    | **외부 모듈 (External Module)** | 파이썬이 기본적으로 제공하는 것 아닌 다른 사람들 만들어 배포하는 모듈로 별도 다운 받아 사용 |

### **2. 표준 모듈 읽기**

- **`import` 구문**
  ```python
  import math
  print(math.pi)
  ```
  - **`import as` 구문**
    : 모듈의 이름 길어 짧게 줄여 사용
    ```python
    import math as m
    print(m.pi)
    ```
- **`from` 구문**
  - 다양한 함수 계속 입력하는 것의 비효율성
  - ‘가져오고 싶은 변수/함수’에 여러 개의 변수/함수 입력 가능
  - **`from import` 구문**
    ```python
    from math import pi
    print(pi)
    ```
  - **`from import *` 구문**
    : 모듈을 여러 개 사용하는 경우
    ```python
    from math import *
    print(pi)
    ```

### 3. 표준 모듈

- **Python Runtime Services**

  - sys : System-specific paramenters and functions

  ```python
  import sys
  print(sys.copyright)
  # Copyright (c) 2001-2023 Python Software Foundation.
  # All Rights Reserved.

  # Copyright (c) 2000 BeOpen.com.
  # All Rights Reserved.

  # Copyright (c) 1995-2001 Corporation for National Research Initiatives.
  # All Rights Reserved.

  # Copyright (c) 1991-1995 Stichting Mathematisch Centrum, Amsterdam.
  # All Rights Reserved.

  # 명령 매개변수
  print(sys.argv)
  ```

- **`datetime` 모듈**
  : 날짜, 시간 모듈

  ```python
  import datetime

  now = datetime.datetime.now()
  print(now)
  ```

  ```python
  from datetime import datetime

  now = datetime.now()
  print(now)
  ```

- **`time` 모듈**
  : 시간 관련 기능

  - **`time.sleep(정지할 초 단위)`** : 특정 시간 동안 코드 진행 정지

    ```python
    import time
    print('2초간 대기')

    # 대기
    time.sleep(2)
    print('작동')
    ```

- **URL 다루는 라이브러리**

  - **`urlopen()`** : URL 주소의 페이지 열기

    ```python
    from urllib import request

    target = request.urlopen("https://www.google.com/")
    content = target.read()
    print(content)
    ```

### 4. 모듈 생성

- **모듈(Module)**
  - 변수, 함수, 클래스 등 모아 놓은 스크립트 파일로 간단한 기능 담을 때 사용
- **패키지(Package)**

  - 여러 모듈을 묶은 것
    → 기능들이 모듈 여러개로 분할 및 관련 모듈끼리 모인 형태
  - 코드 많고 복잡할 때 사용
    → 공통되는 부분 빼내 모듈과 패키지로 생성

  ```python
  # 파일명 : square2

  # 변수
  a = 1

  # 함수
  def square(n):
  	return a ** n
  ```

  ```python
  import square2

  # 모듈.변수 : 모듈 변수 사용
  print(square2.a)
  # 모듈.함수() : 모듈 함수 사용
  print(square2.square(3))

  # from import로 변수, 함수 가져옴 -> 모듈 이름없이 사용
  from square2 import a,square
  print(a)
  print(square(3))
  ```

- **`__name__`**

  - 모듈 이름 저장되는 변수
  - `import`로 모듈 가져올 때 모듈 이름 들어감
    - python interpreter로 파일 실행 시 모듈 이름이 아니라 `__main__`
  - 스크립트 파일이 시작점인지 모듈인지 판단

  ```python
  print('hello 모듈 시작')

  # __name__ 변수 출력
  print('hello.py __name__ : ', __name__)
  print('hello 모듈 끝')
  # hello 모듈 시작
  # hello.py __name__: hello
  # hello 모듈 끝
  ```

  ```python
  # import 모듈 가져오면 스크립트 한 번 실행
  import hello

  print('main __name__ : ', __name__)
  # main __name__: main
  ```

### 5. 외부 모듈

- **PIP (Python Installs Packages)**
  - 특정 버전 모듈 설치 및 제거
- **Beautifulsoup4 모듈** : 웹 사이트 태그 해석

  - `HTML` 과 `XML` 오부터 데이터 가져오기 위한 파이썬 라이브러리
  - **수집 방법**

    1. 수집 페이지의 URL 확인
    2. 파이썬의 `requests` 이용해 URL 접근
    3. `response.status_code` 가 `200 OK` 라면 정상 응답
    4. `request` 의 `response` 값에서 `response.text` 만 받아옴
    5. `html` 텍스트 받아왔을 경우 `bs(response.text, ‘html.parser’)` 로 `html` 해석
    6. `soup.select` 통해 원하는 태그 및 정보 접근, 구분해 목록 먼저 받아옴
    7. 목록헤서 행 하나씩 가져와 행 모아 dataframe으로 생성

    ```python
    # url의 데이터 가져오기
    from urllib import request
    from bs4 import BeautifulSoup

    content = request.urlopen('{url}')
    soup = BeautifulSoup(content, 'html.parser')

    for i in soup.select('{data}'):
    	print(i)
    	print('-' * 20)
    ```

  - **설치 방법**
    - `pip install beautifulsoup4`
    - `easy_install BeutifulSoup4`
    - pip version 업그레이드
      - `python -m pip install --upograde pip`

- **RSS (Really Simple Syndicatuin, Rich Site Summary)**
  - 컨텐츠 업데이트 자주 일어나는 웹 사이트에서 쉽게 제공위해 XML기초로 만들어진 데이터 형식
  - RSS 이용 시 업데이트 된 정보 찾기 위해 일일히 방문하지 않아도 업데이트 될 때마다 편리하게 확인 가능
