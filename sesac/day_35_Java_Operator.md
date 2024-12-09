## <mark color="#fbc956">Operator</mark>

### 1. 연산자

- **연산자(Operator)** : 연산 수행 기호
- **피연산자(Operand)** : 연산자의 작업 대상 (숫자)

### 2. 단항 연산자

- **증감 연산자 (`++`, `--`)**
  - 피연산자에 저장된 값을 1 증가 / 감소 시킴
  1. **전위 증감 연산자**

     - 값이 참조되기 전에 증감
     - `++i;`, `--i;`

     ```java
     int i = 1;

     System.out.println(++i); // 2
     System.out.println(i);   // 2
     ```

  2. **후위 증감 연산자**

     - 값이 참조된 후 증감
     - `i++;` , `i--;`

     ```java
     int i = 1;

     System.out.println(i++); // 1
     System.out.println(i);   // 2
     ```
- **부호 연산자(`+` , `-`)**
  - 피연산자의 부호를 반대로 변경시킴
  - 음의 부호 연산자(`-`)만 의미 있음
  ```java
  int i = 1;
  System.out.println(-i);   // -1

  int j = -1;
  System.out.println(-j);   // 1
  ```

### 3. 산술 연산자

- 덧셈, 뺄셈, 곱셈, 나눗셈, 나머지와 같은 사칙 연산 수행
  | 종류 | 설명          |
  | ---- | ------------- |
  | +    | 덧셈 연산자   |
  | -    | 뺄셈 연산자   |
  | \*   | 곱셈 연산자   |
  | /    | 나눗셈 연산자 |
  | %    | 나머지 연산자 |

```java
int i = 3;
int j = 2;

System.out.println(i + j);           // 5
System.out.println(i - j);           // 1
System.out.println(i * j);           // 6
System.out.println(i / j);           // 1
System.out.println((double)i / j);   // 1.5
System.out.println(i % j);           // 1
```

- **나눗셈(`/`) 연산 주의사항**

  - 정수끼리 나눗셈 연산의 경우 소수점 이하는 버려짐
  - 소수점까지 표현 원할 경우 피연산자 중 하나를 실수형으로 형 변환 후 나눗셈 필요

- **거듭제곱과 제곱근**
  - 거듭 제곱 : `Math.pow(double a, double b)`
  - 제곱근(양수) : `Math.sqrt(double a)`

```java
double powResult = Math.pow(5, 2);
System.out.println(powResult);
// 25.0

double sqrtResult = Math.sqrt(25);
System.out.println(sqrtResult);
// 5.0
```

### 4. 비교 연산자

- 두 개 이상의 피연산자 비교 시 사용
- 비교 연산의 결과는 논리형(`boolean`)으로 표현됨
  | 종류 | 설명               |
  | ---- | ------------------ |
  | <    | 미만               |
  | <=   | 이하               |
  | >    | 초과               |
  | >=   | 이상               |
  | ==   | 같음 (동일)        |
  | !=   | 같지 않음 (비동일) |

```java
int i = 3;
int j = 2;

System.out.println(i < j);  // false
System.out.println(i <= j); // false

System.out.println(i > j);  // true
System.out.println(i >= j); // true

System.out.println(i == j); // false
System.out.println(i != j); // true
```

- **동일 연산자(`==`) 주의사항**
  - 정수와 실수 비교할 경우 : 정수가 실수로 자동 형 변환된 후 비교됨
    ```java
     int intNumber = 1;
     double doubleNumber = 1.0;

    System.out.println(intNumber == doubleNumber); // true
    ```
  - 두 실수 비교할 경우 : double을 float으로 형 변환 후 비교해야 함
    ```java
    float floatNumber = 3.1234f;
    double doubleNumber = 3.1234;

    System.out.println(floatNumber == (float)doubleNumber); // true
    ```
    - 같은 실수값이라도 float과 double간 정밀도 차이로 다르다고 판별되는 경우 있음
      - float이 double로 자동 형 변환 후 비교됨
    ```java
    float floatNumber = 3.1234f;
    double doubleNumber = 3.1234;

    System.out.println(floatNumber == doubleNumber); // false
    ```
  - 참조 타입 비교할 경우 : 값이 아닌 참조를 비교하기 때문에 `.equals()` 메서드 사용
    ```java
    String str1 = new String("Hello");
    String str2 = new String("Hello");

    System.out.println(str1 == str2);      // false
    System.out.println(str1.equals(str2)); // true
    ```

### 5. 논리 연산자

- 논리 연산자의 결과는 논리형(`boolean`)으로 표현
  | 종류 | 설명         |
  | ---- | ------------ | --- | --------- |
  | &&   | and (그리고) |
  |      |              |     | or (또는) |
  | !    | not (부정)   |

```java
int i = 1;
int j = 2;
int k = 2;
System.out.println(i == j && j == k); // false
System.out.println(i == j || j == k); // true

boolean booleanType = true;
System.out.println(!booleanType); // false
```

- **논리표**
  | x     | y     | x&&y  | x     |       | y   | !x  |
  | ----- | ----- | ----- | ----- | ----- | --- | --- |
  | true  | true  | true  | true  | false |
  | true  | false | false | true  | false |
  | false | true  | false | true  | true  |
  | false | false | false | false | true  |
- **논리 연산자는 단축평가 지원**
  - 단축 평가 : 효율적인 논리 연산위해 미리 전체 결과가 판별 가능할 경우 더 이상 연산하지 않음

### 6. 기타 연산자

- **삼항 연산자**
  - 조건에 따라 참인 경우와 거짓인 경우에 다른 결과 반환
  - `조건식 ? 참일 때 값 : 거짓일 때 값`
  ```java
  int i = 1;
  int j = 2;
  String result = i == j ? "같음" : "다름";

  System.out.println(result); // 다름
  ```
- **복합 연산자**
  - 스스로에 대해 산술 연산을 진행하는 경우 축약 문법 제공
  | 종류 | 설명       |
  | ---- | ---------- |
  | +=   | i = i + 1  |
  | -=   | i = i -1   |
  | \*=  | i = i \* 1 |
  | /=   | i = i / 1  |
  | %=   | i = i % 1  |
  ```java
  // 덧셈 복합 연산자
  int number = 10;
  number += 1;
  System.out.println(number); // 11

  // 뺄셈 복합 연산자
  int number = 10;
  number -= 1;
  System.out.println(number); // 9

  // 곱셈 복합 연산자
  int number = 10;
  number *= 2;
  System.out.println(number); // 20

  // 나눗셈 복합 연산자
  int number = 10;
  number /= 2;
  System.out.println(number); // 5

  // 나머지 복합 연산자
  int number = 10;
  number %= 2;
  System.out.println(number); // 0
  ```

### 7. 연산자 우선순위

| 우선순위  | 종류              | 연산자                                    |
| --------- | ----------------- | ----------------------------------------- | --- | --- |
| 1 (높음)  | 단항 연산자       | ++, --, +, -, ~, !                        |
| 2         | 산술 연산자       | \*, /, %                                  |
| 3         | 산술 연산자       | +, -                                      |
| 4         | 산술 연산자       | <<, >>                                    |
| 5         | 비교 연산자       | <, >, <=, >=                              |
| 6         | 비교 연산자       | ==, !=                                    |
| 7         | 논리 연산자       | &                                         |
| 8         | 논리 연산자       | ^                                         |
| 9         | 논리 연산자       |                                           |     |
| 10        | 논리 연산자       | &&                                        |
| 11        | 논리 연산자       |                                           |     |     |
| 12        | 삼항 연산자       | ?:                                        |
| 13 (낮음) | 대입, 복합 연산자 | =, +=, -=, \*=, /=, %=, <<=, >>=, &=, ^=, | =   |
