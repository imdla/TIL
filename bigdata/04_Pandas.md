## <mark color="#fbc956">판다스 패키지</mark>

### 1. 판다스란?

- **판다스(Pandas)**
  : R을 모티브로 만든 파이썬 라이브러리
  - 데이터 분석을 위해 수집, 전처리 등 과정 대부분 데이터프레임 형태 많음
- **Dataframe** : 행과 열로 이루어진 표

### 2. 시리즈(Series)

- **시리즈(Series)**
  : 데이터프레임의 하위 자료형
  - 1개의 열이 시리즈 → 시리즈 모여 데이터프레임 형성
  - 인덱스(index) + 값(value)
- **시리즈 생성**

  - 딕셔너리로 생성
  - 판다스 시리즈 → 딕셔너리 구조와 비슷
    - 딕셔너리 `key 값` → 판다스 시리즈의 `index`,
      딕셔너리 `value 값` → 판다스 시리즈의 `값`

  ```python
  import pandas as pd

  dict_data = {"a":1, "b":2, "c":3}
  series_data = pd.Series(dict_data)

  type(series_data)
  print(series_data)
  # a    1
  # b    2
  # c    3
  # dtype: int64
  ```

- **리스트로 생성**

  - `key`값이 없는 순서형 자료형 → `index`는 자동 0부터 부여

    ```python
    list_data = ['2024-01-01', 3.14, 'ABC', 100, True]
    series_data = pd.Series(list_data)

    print(series_data)
    # 0    2024-01-01
    # 1          3.14
    # 2           ABC
    # 3           100
    # 4          True
    # dtype: object
    ```

  - `index`와 `values` 확인

    ```python
    idx = series_data.index
    val = series_data.values

    print(idx)
    print(val)
    # RangeIndex(start=0, stop=5, step=1)
    # ['2024-01-01' 3.14 'ABC' 100 True]
    ```

### 3. 데이터프레임(Dataframe)

- **데이터프레임(Dataframe)**
  : Series 여러 개가 합쳐진 자료형, 행과 열로 이루어진 형태
  - 데이터 프레임의 하나의 컬럼 ⇒ 시리즈(series)
    - 데이터 프레임 1개의 컬럼 = 1개의 시리즈
    - 데이터 프레임 `key` 값 = `coloumn` 이름
- **딕셔너리로 생성**

  - 데이터프레임의 컬럼을 하나씩 쌓아가는 형태

  ```python
  import pandas as pd

  df = pd.DataFrame({
  	'a': [4, 5, 6],
  	'b': [7, 8, 9],
  	'c': [10, 11, 12]},
  	index = [1, 2, 3]
  )

  print(type(df))
  print(df)
  # <class 'pandas.core.frame.DataFrame'>
  #    a  b   c
  # 1  4  7  10
  # 2  5  8  11
  # 3  6  9  12
  ```

- **리스트로 생성**

  - 데이터프레임의 행을 하나씩 쌓아가는 형태

  ```python
  import pandas as pd

  data = [
  	[20, '남', '자연'],
  	[21, '여', '과학']
  ]
  df = pd.DataFrame(data)
  print(df)
  #   0   1   2
  # 0 20 남 자연
  # 1 21 여 과학

  idx = ['홍길동', '심청이']
  cols = ['나이', '성별', '학과']
  df = pd.DataFrame(data, index = idx, columns = cols)

  print(df)
  #      나이 성별 학과
  # 홍길동 20 남 자연
  # 심청이 21 여 과학

  print(df.index)
  print(df.columns)
  # Index(['홍길동', '심청이'], dtype='object')
  # Index(['나이', '성별', '학과'], dtype='object')
  ```

### 4. 데이터프레임 변경

- **컬럼명 변경**

  1. **`df.columns = []`** : 리스트 형태로 모든 컬럼의 변경 입력

     ```python
     df = pd.DataFrame({
     	'a': [4, 5, 6],
     	'b': [7, 8, 9],
     	'c': [10, 11, 12],
     	index = [1, 2, 3]
     })

     df.columns = ['A', 'B', 'C']
     print(df)
     #    A  B   C
     # 1  4  7  10
     # 2  5  8  11
     # 3  6  9  12
     ```

  2. **`df.rename(columns={})`** : 딕셔너리 형태로 원하는 컬럼만 변경

     ```python
     df.rename(columns = {'A':'가'}, inplace = True)
     print(df)
     #    가  B   C
     # 1  4  7  10
     # 2  5  8  11
     # 3  6  9  12
     ```

     - `inplace=True` : 기존 데이터프레임에 덮어씀

### 5. 데이터프레임 삭제

- **삭제 : `df.drop()`**

  - **행 삭제**

    - `df.drop( {행 이름}, axis = 0 )` → axis 사용
    - `df.drop( index=[] )` → index 사용

    ```python
    # **방법1 : axis 사용**
    df.drop(1, axis = 0)

    # **방법 2 : index 사용**
    df.drop(index=[1])

    # **기존**
    #    A  B   C
    # 1  4  7  10
    # 2  5  8  11
    # 3  6  9  12

    # **결과**
    #    A  B   C
    # 2  5  8  11
    # 3  6  9  12
    ```

  - **열 삭제**

    - `df.drop( {열 이름}, asix = 1 )` → asix 사용
    - `df.drop( columns = [] )` → columns 사용

    ```python
    # **방법 1 : axis 사용**
    df.drop('A', asix = 1)

    # **방법 2 : columns 사용**
    df.drop(columns = 'A')

    # **기존**
    #    A  B   C
    # 1  4  7  10
    # 2  5  8  11
    # 3  6  9  12

    # **결과**
    #    B   C
    # 1  7  10
    # 2  8  11
    # 3  9  12
    ```

### 6. 데이터프레임 추가

- **컬럼 추가**

  - **`df.assign( {새 컬럼명} = {내용} )`**

    ```python
    df = pd.assign(D = lambda x : x.C * 5)
    print(df)

    # **기존**
    #    A  B   C
    # 1  4  7  10
    # 2  5  8  11
    # 3  6  9  12

    # **결과**
    #    A  B   C   D
    # 1  4  7  10  50
    # 2  5  8  11  55
    # 3  6  9  12  60
    ```

  - **`df[{새 컬럼명}] = df.[{내용}]`**

    ```python
    df['D'] = df.C * 5
    print(df)

    # **기존**
    #    A  B   C
    # 1  4  7  10
    # 2  5  8  11
    # 3  6  9  12

    # **결과**
    #    A  B   C   D
    # 1  4  7  10  50
    # 2  5  8  11  55
    # 3  6  9  12  60
    ```

### 7. 고급 함수

- **`map(func, list)` 함수**

  - series 데이터 또는 dataframe의 단일 컬럼에 함수 일괄 적용
  - 함수와 리스트가 인자
  - 리스트의 원소 하나씩 꺼내 → 함수 적용 → 결과를 새로운 리스트로 만듦
  - 입력 여러개일 경우 for문 사용할 수 있지만 map으로 쉽게 적용 가능

  ```python
  import pandas as pd

  def grade_func(num):
  	if num >= 90:
  		grade = 'A'
  	elif num >= 80:
  		grade = 'B'
  	elif num >= 70:
  		grade = 'C'
  	else:
  		grade = 'D'
  	return grade

  test = pd.Series([65, 97, 24, 56, 87, 73])
  print(test.map(grade_func))

  # 0    D
  # 1    A
  # 2    D
  # 3    D
  # 4    B
  # 5    C
  # dtype: object
  ```

- **`(lambda {입력 매개변수} : {결과 리턴값})` 함수**
  - `lambda` 는 `def` 와 같은 기능
  - 한 줄 내에서 간편 사용
    - 단순 사칙연산, 문자열 추출 등 `lambda` 적용한 `apply` 함수 사용이 효율적
  ```python
  # **사용자 정의 함수 생성**
  def sum(a, b):
  	return a + b
  sum(1, 2)
  ```
  ```python
  # **lambda 함수**
  (lambda a, b : a + b)(1, 2)
  ```
- **`apply(func)` 함수**

  - `DataFrame([cols].apply(func))`
  - 데이터프레임에서 복수 컬럼에 함수 적용

  ```python
  # **1. 데이터프레임 생성**
  df = pd.DataFrame([[1, 2, 3], [4, 5, 6], [7, 8, 9]],
  		 columns=['A', 'B', 'C'])
  print(df)
  #    A  B  C
  # 0  1  2  3
  # 1  4  5  6
  # 2  7  8  9

  # **2. 사용자 정의 함수 생성**
  def plus_one(x):
  	x += 1
  	return x

  # **3. 특정 단일 컬럼 함수 적용**
  print(df['A'].map(plus_one))
  print(df['A'].apply(plus_one))
  # 0    2
  # 1    5
  # 2    8
  # Name: A, dtype: int64

  # **4. 다중 컬럼 함수 적용**
  print(df[['A', 'C']].apply(plus_one))
  print(df[['A', 'C']].map(plus_one))
  #    A   C
  # 0  2   4
  # 1  5   7
  # 2  8  10

  # **5. 전체 데이터프레임 함수 적용**
  print(df.apply(plus_one))
  print(df.map(plus_one))
  #    A  B   C
  # 0  2  3   4
  # 1  5  6   7
  # 2  8  9  10
  ```

### 8. 데이터프레임 복사

- **데이터프레임 복사**

  - **얇은 복사(Shallow Copy)**
    - **`a=b`**
    - 원본 데이터 수정 → 복사본도 똑같이 수정됨
      (복사본 수정 → 원본 수정됨)
    - 원본과 데이터(index) 공유
  - **깊은 복사(Deep Copy)**
    - **`pandas.DataFrame.copy()`**
    - 복사 당시 데이터프레임 상태로 복사
    - 자신만의 데이터(index) 가짐
    - 복사본 수정 → 원본에 반영 X

  ```python
  import pandas as pd

  # **0. 데이터프레임 생성**
  data = [['apple', 20], ['banana', 21], ['peer', 22]]
  cols = ['name', 'age']
  df = pd.DataFrame(data, columns = cols)
  print(df)
  #   name   age
  # 0 apple  20
  # 1 banana 21
  # 2 peer   22

  # **1. 얇은 복사**
  df_s = df

  # **2. 깊은 복사**
  df_d = df.copy()

  # **3. 원본 데이터 수정**
  df.loc[1, 'name'] = 'monkey'
  print(df)
  #     name  age
  # 0   apple   20
  # 1  monkey   21
  # 2    peer   22

  print(df_s)
  #     name  age
  # 0   apple   20
  # 1  monkey   21
  # 2    peer   22

  print(df_d)
  #   name   age
  # 0 apple  20
  # 1 banana 21
  # 2 peer   22
  ```

---

## <mark color="#fbc956">데이터프레임 조회와 서브셋 생성</mark>

### 1. 데이터프레임 조회

- **`loc`**
  : 레이블(조건) 기반, 행과 열의 이름 지정
  - **컬럼 기준**
    - **`df["컬럼명"]` or `df.{컬럼명}`** : pandas series 형태 결과 반환
      - `df[["컬럼명"]]` : dateframe 형태 결과 반환
    - **`df[[컬럼목록]]`** : 2개 이상 컬럼 가져올 경우 리스트 형태로 묶어 지정, dataframe 형태 결과 반환
  - **행 기준**
    - **`df.loc[행]` or `df.loc[행, 열]`**
    - **`df.loc[행 이름 or 번호]`** : pandas series 형태 결과 반환
    - **`df.loc[[행목록]]`** : dataframe 형태 결과 반환
  - **행의 특정 컬럼 기준**
    - **`df.loc[행 번호, 컬럼명]` or `df[행 번호][컬럼명]`**
- **`iloc`**
  : 정수 슬라이싱 기반, 행과 열 지정
  - **`df.iloc[:]`** : 전체 데이터 검색
  - **`df.iloc[행, 열]`** : 인덱스 번호 지정

```python
# **0. 데이터프레임 생성**
import pandas as pd

data = {
	"name" : ["apple", "banana", "mango", "durian", "lemon", "peer", "cherry"],
	"city" : ["seoul", "busan", "seoul", "seoul", "jeju", "jeju", "busan"],
	"year" : [2024, 2024, 2024, 2020, 2021, 2023, 2020],
	"amount" : [100, 200, 150, 500, 300, 470, 240]
}
cols = ["name", "city", "year", "amount"]
df = pd.DataFrame(data, columns = cols)
print(df)
#      name   city  year  amount
# 0   apple  seoul  2024     100
# 1  banana  busan  2024     200
# 2   mango  seoul  2024     150
# 3  durian  seoul  2020     500
# 4   lemon   jeju  2021     300
# 5    peer   jeju  2023     470
# 6  cherry  busan  2020     240

# **1. df["컬럼명"] / df.컬럼명**
print(df.name)
# 0     apple
# 1    banana
# 2     mango
# 3    durian
# 4     lemon
# 5      peer
# 6    cherry
# Name: name, dtype: object

# **2. df[[컬럼목록]]**
print(df[["name", "city"]])
#      name   city
# 0   apple  seoul
# 1  banana  busan
# 2   mango  seoul
# 3  durian  seoul
# 4   lemon   jeju
# 5    peer   jeju
# 6  cherry  busan

# **3. df.loc[행] / df.loc[행, 열]**
print(df.loc[0])
# name      apple
# city      seoul
# year       2024
# amount      100
# Name: 0, dtype: object

# **4. df.loc[[행목록]]**
print(df.loc[[0, 1, 2]])
#      name   city  year  amount
# 0   apple  seoul  2024     100
# 1  banana  busan  2024     200
# 2   mango  seoul  2024     150

# **5. df.loc[행번호, 컬럼명] / df.loc[행번호][컬럼명]**
print(df.loc[[0, 1, 2], "name"])
# 0     apple
# 1    banana
# 2     mango
# Name: name, dtype: object

# **6. dfiloc[:] : 슬라이싱 통한 조회**
print(df.iloc[:5])
#      name   city  year  amount
# 0   apple  seoul  2024     100
# 1  banana  busan  2024     200
# 2   mango  seoul  2024     150
# 3  durian  seoul  2020     500
# 4   lemon   jeju  2021     300

# **7. df.iloc[행, 열]**
print(df.iloc[:5, :2])
     name   city
# 0   apple  seoul
# 1  banana  busan
# 2   mango  seoul
# 3  durian  seoul
# 4   lemon   jeju
```

### 2. 데이터프레임 조회 - 특정 조건

- **Boolean indexing**
  - `true` , `false` 의 값 나타냄
- **`isin` 함수**
  - 특정한 리스트 형태의 값들 포함하는 행 추출

```python
# 위의 데이터프레임 이용
# **1. boolean indexing**
print(df["city"] == "seoul")
# 0     True
# 1    False
# 2     True
# 3     True
# 4    False
# 5    False
# 6    False
# Name: city, dtype: bool

# **1-2. 위의 값을 df[] 감싸면 dataframe 형태 반환**
print(df[df["city"] == "seoul"])
#      name   city  year  amount
# 0   apple  seoul  2024     100
# 2   mango  seoul  2024     150
# 3  durian  seoul  2020     500

# **1-3. 조건 여러개일 경우**
print( df[ (df["city"] == "seoul") | (df["year"] == 2024) ] )
#     name   city  year  amount
# 0   apple  seoul  2024     100
# 1  banana  busan  2024     200
# 2   mango  seoul  2024     150
# 3  durian  seoul  2020     500

# **2. isin 함수**
print( df["city"].isin(["busan", "jeju"]) )
# 0    False
# 1     True
# 2    False
# 3    False
# 4     True
# 5     True
# 6     True
# Name: city, dtype: bool
```

### 3. 데이터 요약

- **`pd.series.unique()`** : 유일한 값 검색
- **`series.value_counts()`** : 유니크한 값들의 빈도 수 구함
- **`df.groupby().count()`** : 단일 컬럼의 빈도 계산
- **`df.groupby().size()`** : 여러 컬럼에 동시 적용

```python
# **0. 데이터프레임 생성**
import pandas as pd

data = {
	"A" : ["apple", "apple", "apple", "apple","lemon", "lemon", "lemon"],
	"B" : ["a", "a", "b", "a", "b", "a", "a"]
}
df = pd.DataFrame(data)

# **1. df[].unique()**
print(df["A"].unique)
# bound method Series.unique of 0    apple
# 1    apple
# 2    apple
# 3    apple
# 4    lemon
# 5    lemon
# 6    lemon
# Name: A, dtype: object>

# **2. df[].value_counts()**
print(df["A"].value_counts())
# A
# apple    4
# lemon    3
# Name: count, dtype: int64

# **3. df.groupby().count()**
print(df.groupby(["A"]).count())
#        B
# A
# apple  4
# lemon  3

# **4. df.groupby().size()**
print(df.groupby(["A", "B"]).size())
# A      B
# apple  a    3
#        b    1
# lemon  a    2
#        b    1
# dtype: int64
```

- **`groupby(by=[열이름], as_index = False)`**

  - min, max, count, sum 함수 사용 집계

  ```python
  # **0. 데이터프레임 생성**
  import pandas as pd

  data = {
  	"name" : ["apple", "banana", "mango", "durian", "lemon", "peer", "cherry"],
  	"city" : ["seoul", "busan", "seoul", "seoul", "jeju", "jeju", "busan"],
  	"amount" : [100, 200, 150, 500, 300, 470, 240]
  }
  df = pd.DataFrame(data)

  # **1. groupby().min()**
  print( df.groupby(by=["city"], as_index=False).min() )
  #     city    name  amount
  # 0  busan  banana     200
  # 1   jeju   lemon     300
  # 2  seoul   apple     100

  # **2. groupby().count()**
  print( df.groupby(by=["city"], as_index=False).count() )
  #    city  name  amount
  # 0  busan     2       2
  # 1   jeju     2       2
  # 2  seoul     3       3

  # **3. groupby().sum()**
  print( df.groupby(by=["city"], as_index=False).sum() )
  #     city              name  amount
  # 0  busan      bananacherry     440
  # 1   jeju         lemonpeer     770
  # 2  seoul  applemangodurian     750

  print( df.groupby(by=["city", "name"], as_index=False).sum() )
  #     city    name  amount
  # 0  busan  banana     200
  # 1  busan  cherry     240
  # 2   jeju   lemon     300
  # 3   jeju    peer     470
  # 4  seoul   apple     100
  # 5  seoul  durian     500
  # 6  seoul   mango     150

  ```

---

## <mark color="#fbc956">데이터 재구조화</mark>

### 1. Melt

- **`pd.melt(df, id_vars=[기준 컬럼], value_vars=[녹일 컬럼])`**
  : 컬럼 → 행으로 구조 변경 및 병합 (데이터의 형태 변경)

  - **`id_vars`** : 기준 컬럼 지정
  - **`value_vars`** : 행으로 녹여지는 컬럼 지정, 값과 같이 매칭
    ⇒ 녹여진 컬럼 : `variable`, 컬럼 값 : `value`

  ```python
  # **0. 데이터프레임 생성**
  import pamdas as pd

  df = pd.DataFrame({
  	'A' : ['a', 'b', 'c'],
  	'B' : [1, 2, 3],
  	'C' : [4, 5, 6]
  })

  # **pd.melt(df, id_vars=[], value_vars=[])**
  print( pd.melt(df, id_vars=['A'], value_vars=['B']) )
  #    A variable  value
  # 0  a        B      1
  # 1  b        B      2
  # 2  c        B      3
  ```

- **필드명 변경**
  - **`var_name`** : 녹여진 컬럼(`variable`) 이름 변경
  - **`value_name`** : 컬럼 값(`value`) 이름 변경
  ```python
  # **위의 동일 데이터프레임 사용**
  print( pd.melt(df, id_vars=['A'], var_name="cols", value_name="val") )
  #    A cols  val
  # 0  a    B    1
  # 1  b    B    2
  # 2  c    B    3
  # 3  a    C    4
  # 4  b    C    5
  # 5  c    C    6
  ```

### 2. Concat

- **`pd.concat( [df1, df2] )`**
  : 데이터프레임의 행 추가

  - `ignore_index` : `True` 일 경우 병합 후 인덱스 순서대로 설정
  - `join` : `inner` 일 경우 겹치는 컬럼만 결합
  - 데이터프레임 교차점 외부 컬럼은 `NaN` 으로 채움

  ```python
  # **1-1. 시리즈 생성**
  import pandas as pd
  s1 = pd.Series(['a', 'b'])
  s2 = pd.Series(['c', 'd'])

  print(s1)
  # 0    a
  # 1    b
  # dtype: object

  print(s2)
  # 0    c
  # 1    d
  # dtype: object

  # **1-2. pd.concat() - 시리즈 결합**
  print( pd.concat([s1, s2]) )
  # 0    a
  # 1    b
  # 0    c
  # 1    d
  # dtype: object

  # =========================================================

  # **2-1. 데이터프레임 생성**
  df1 = pd.DataFrame([['a', 1], ['b', 2]], columns = ['str', 'num'])
  df2 = pd.DataFrame([['c', 3], ['d', 4]], columns = ['str', 'num'])

  # **2-2. pd.concat() - 데이터프레임 결합**
  print( pd.concat([df1, df2], ignore_index = True) )
  #   str  num
  # 0   a    1
  # 1   b    2
  # 2   c    3
  # 3   d    4

  # =========================================================

  # **3-1. 데이터프레임 추가 생성**
  df3 = pd.DataFrame([['c', 3, 'hello'], ['d', 4, 'hi']], columns = ['str', 'num', 'greet'])

  # **3-2. 데이터프레임 교차 외 컬럼은 'NaN'**
  print( pd.concat([df1, df3]) )
  #   str  num  greet
  # 0   a    1    NaN
  # 1   b    2    NaN
  # 0   c    3  hello
  # 1   d    4     hi

  # **4. join="inner" 옵션**
  print( pd.concat([df1, df3], join = "inner") )
  #   str  num
  # 0   a    1
  # 1   b    2
  # 0   c    3
  # 1   d    4
  ```

### 3. Pivot

- **`Pivot`**
  : 데이터프레임에서 두 개의 열 이용해 행/열 인덱스 reshape된 테이블

  - 방법 1 : **`df.pivot(index, columns, values)`** - `groupby` 필요
    - `index` : 행
    - `columns` : 열
    - `values` : 데이터에 사용할 컬럼
  - 방법 2 : **`df.pivot_table(data, values, index, columns, aggfunc)`** - `groupby` 불필요
    - `groupby` 처럼 그룹 분석하지만 최종적으로 `pivot` 처럼 테이블 생성
    - `groupby` 결과에 `unstack` 자동 적용해 2차원 형태로 변형
    - **2개 이상**
      **:** `df.pivot_table(values(컬럼, index, columns, aggfunc))`
      - `aggfunc` : default는 평균값
    - `margins=True` : 총계와 같은 산식 삽입

  ```python
  # **0. 데이터프레임 생성**
  import pandas as pd

  data = {
  	"name" : ["apple", "banana", "mango", "durian", "lemon", "peer", "cherry"],
  	"city" : ["seoul", "busan", "seoul", "seoul", "jeju", "jeju", "busan"],
  	"year" : [2024, 2024, 2019, 2020, 2021, 2023, 2020],
  	"amount" : [100, 200, 150, 500, 300, 470, 240]
  }
  cols = ["name", "city", "year", "amount"]
  df = pd.DataFrame(data, columns = cols)

  # **1. df.pivot(index, columns, values)**
  print( df.pivot(index="city", columns="year", values="amount") )
  # year    2019   2020   2021   2023   2024
  # city
  # busan    NaN  240.0    NaN    NaN  200.0
  # jeju     NaN    NaN  300.0  470.0    NaN
  # seoul  150.0  500.0    NaN    NaN  100.0

  # **1-1. 다중 인덱스 피벗 테이블**
  print( df.pivot(index=["name", "city"], columns="year", values="amount") )
  # year           2019   2020   2021   2023   2024
  # name   city
  # apple  seoul    NaN    NaN    NaN    NaN  100.0
  # banana busan    NaN    NaN    NaN    NaN  200.0
  # cherry busan    NaN  240.0    NaN    NaN    NaN
  # durian seoul    NaN  500.0    NaN    NaN    NaN
  # lemon  jeju     NaN    NaN  300.0    NaN    NaN
  # mango  seoul  150.0    NaN    NaN    NaN    NaN
  # peer   jeju     NaN    NaN    NaN  470.0    NaN

  # **2. df.pivot_table(values, index, columns)**
  print( df.pivot_table(values="amount", index="city", columns="year") )
  # year    2019   2020   2021   2023   2024
  # city
  # busan    NaN  240.0    NaN    NaN  200.0
  # jeju     NaN    NaN  300.0  470.0    NaN
  # seoul  150.0  500.0    NaN    NaN  100.0

  # **2-2. pivot_table 그룹핑 - default로 평균값**
  print( df.pivot_table(index=["name", "city"]) )
  #               amount    year
  # name   city
  # apple  seoul   100.0  2024.0
  # banana busan   200.0  2024.0
  # cherry busan   240.0  2020.0
  # durian seoul   500.0  2020.0
  # lemon  jeju    300.0  2021.0
  # mango  seoul   150.0  2019.0
  # peer   jeju    470.0  2023.0

  # **2-2. pivot_table 그룹핑 - 연산식**
  # **(합계, 개수, 표준편차, 분산 등)**
  print( df.pivot_table(index=["name", "city"], aggfunc='sum') )
  #               amount  year
  # name   city
  # apple  seoul     100  2024
  # banana busan     200  2024
  # cherry busan     240  2020
  # durian seoul     500  2020
  # lemon  jeju      300  2021
  # mango  seoul     150  2019
  # peer   jeju      470  2023

  # **2-3. pivot_table 그룹핑 - margins 옵션**
  print( df.pivot_table(index=["name", "city"], aggfunc='sum', margins=True) )
  #               amount   year
  # name   city
  # apple  seoul     100   2024
  # banana busan     200   2024
  # cherry busan     240   2020
  # durian seoul     500   2020
  # lemon  jeju      300   2021
  # mango  seoul     150   2019
  # peer   jeju      470   2023
  # All             1960  14151
  ```

### 4. Stack, Unstack

- `stack()` : 컬럼 → 행으로 재구조화
  - 빈 괄호일 경우 제일 하단 레벨의 컬럼 → 행으로 피벗
- `unstack()` : 행 → 컬럼으로 재구조화
  - 빈 괄호일 경우 제일 우측 레벨의 행 → 컬럼으로 피벗

```python
# **위와 동일 예제 사용**
# **0. 데이터프레임 그룹핑**
pt1 = df.pivot_table(index=["name", "city", "year"], values="amount", aggfunc="sum")
print(pt1)
#                   amount
# name   city  year
# apple  seoul 2024     100
# banana busan 2024     200
# cherry busan 2020     240
# durian seoul 2020     500
# lemon  jeju  2021     300
# mango  seoul 2019     150
# peer   jeju  2023     470

# **1. unstack()**
ust1 = pt1.unstack()
print(ust1)
#              amount
# year           2019   2020   2021   2023   2024**
# name   city
# apple  seoul    NaN    NaN    NaN    NaN  100.0
# banana busan    NaN    NaN    NaN    NaN  200.0
# cherry busan    NaN  240.0    NaN    NaN    NaN
# durian seoul    NaN  500.0    NaN    NaN    NaN
# lemon  jeju     NaN    NaN  300.0    NaN    NaN
# mango  seoul  150.0    NaN    NaN    NaN    NaN
# peer   jeju     NaN    NaN    NaN  470.0    NaN

# **2. stack()**
st1 = ust1.stack()
print(st1)
#                    amount
# name   city  year
# apple  seoul 2024   100.0
# banana busan 2024   200.0
# cherry busan 2020   240.0
# durian seoul 2020   500.0
# lemon  jeju  2021   300.0
# mango  seoul 2019   150.0
# peer   jeju  2023   470.0
```

### 5. 기타

- **`set_index()`**
  : 컬럼의 레이블(레이블 리스트) 입력

  ```python
  # **위와 동일 예제 사용**
  # **df**
  #      name   city  year  amount
  # 0   apple  seoul  2024     100
  # 1  banana  busan  2024     200
  # 2   mango  seoul  2019     150
  # 3  durian  seoul  2020     500
  # 4   lemon   jeju  2021     300
  # 5    peer   jeju  2023     470
  # 6  cherry  busan  2020     240

  print( df.set_index(["name", "city", "year"])["amount"] )
  # name    city   year
  # apple   seoul  2024    100
  # banana  busan  2024    200
  # mango   seoul  2019    150
  # durian  seoul  2020    500
  # lemon   jeju   2021    300
  # peer    jeju   2023    470
  # cherry  busan  2020    240
  # Name: amount, dtype: int64
  ```

- **`reset_index()`**
  : 인덱스를 처음부터 재배열

  ```python
  # **위와 동일 예제 사용**
  # **pt1**
  #                    amount
  # name   city  year
  # apple  seoul 2024     100
  # banana busan 2024     200
  # cherry busan 2020     240
  # durian seoul 2020     500
  # lemon  jeju  2021     300
  # mango  seoul 2019     150
  # peer   jeju  2023     470

  print( pt1.reset_index() )
  #      name   city  year  amount
  # 0   apple  seoul  2024     100
  # 1  banana  busan  2024     200
  # 2  cherry  busan  2020     240
  # 3  durian  seoul  2020     500
  # 4   lemon   jeju  2021     300
  # 5   mango  seoul  2019     150
  # 6    peer   jeju  2023     470
  ```

- **데이터셋 결합**
  - `merge()` : 일반적 데이터베이스에서 join
  - `set(집합)` 자료형 : 교집합, 합집합, 차집합 구하는 경우
