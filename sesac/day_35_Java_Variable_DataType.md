## <mark color="#fbc956">Variable</mark>

### 1. 변수 선언

- 변수의 타입과 이름 지정 및 값 선언

```java
int number2;
```

### 2. 변수 초기화

- **대입 연산자(`=`) : 초기화**
  - `변수 = 값`
- 단순 변수 선언 시 해당 변수에 의도하지 않은 값 들어있을 수 있음
  → 변수 선언 후 사용자가 의도한 값으로 초기화 해야 함

```java
int number;
number = 10;
```

- 선언과 초기화 동시에 가능

```java
int number = 10;

System.out.println(number);
// 10
```

### 3. 변수 선언 시 주의사항

- **필수**
  1. 대소문자 구분됨
  2. 길이에 제한 없음
  3. 예약어 사용 불가
  4. 숫자로 시작 불가
  5. 특수문자는 `_` 와 `$` 만 허용
- **권장**

  1. 변수와 메서드의 이름은 카멜 케이스(Camel Case)

     - 이름의 첫 글자는 소문자로 작성
     - 이름이 여러 단어일 경우, 두 번째 단어부터는 첫 글자를 대문자로 작성

     ```java
     int membersCount = 10;
     ```

  2. 클래스의 이름은 파스칼 케이스(Pascal Case)

     - 이름의 첫 글자 및 각 단어의 첫 글자를 모두 대문자로 작성

     ```java
     public class MainCounter {
     	...
     }
     ```

  3. 상수의 이름은 모두 대문자로 작성, 띄어쓰기는 언더바(`_`) 로 구분

     - 상수 : 변하지 않고 고정되어 있는 값

     ```java
     int MAX_TEAM_MEMBERS_COUNT = 20;
     ```

### 4. 변수 재할당

- 한 번 변수에 값 할당 후, 동일한 변수에 다른 값 다시 할당 가능

```java
int number = 1;
System.out.println(number);
// 1

// 재할당
number = 10;
System.out.println(number);
// 10
```

---

## <mark color="#fbc956">Data Types</mark>

### 1. 자료형

- 기본 자료형
- 참조 자료형

### 2. Primitive Type (기본 자료형)

- 실제 값 자체를 저장하는 자료형
- **종류**
  1. **정수형**
     - 정수 저장에 사용
     - 연산 시 자바 자체적으로 byte와 short를 int로 변환해 주로 int 많이 사용
     - 종류
       - `byte` : 1 byte (-128 ~ 127)
       - `short` : 2 byte (-32,768 ~ 32,767)
       - `int` : 4 byte (-2^31 ~ 2^31-1) → 약 +- 21억
       - `long` : 8 byte (-2^63 ~ 2^63-1)
  2. **실수형**
     - 실수 저장에 사용
     - 종류
       - `float` : 4 byte (1.4 _ 10^-45 ~ 3.4 _ 10^38)
       - `double` : 8 byte (4.9 _ 10^-324 ~ 1.8 _ 10^308)
  3. **문자형**
     - 한 개의 문자 저장에 사용
     - 작은 따옴표(`''`) 이용해 표현
     - `char` : 2 byte (0 ~ 65,535)
  4. **논리형**
     1. true 또는 false 값 가짐
     2. `boolean` : 1 byte (true, false)

```java
byte byteType = 1;
short shortType = 20;
int intType = 300;
long longType = 400000L;         // l 혹은 L 접미사

float floatType = 1.1f;          // f 혹은 F 접미사
double doubleType1 = 2.22345d;   // d 혹은 D 접미사
double doubleType2 = 3.34555;    // double은 접미사 생략 가능

char charType = 'A';

boolean booleanType1 = true;
boolean booleanType2 = false;
```

### 3. Reference Type (참조 자료형)

- 값이 저장된 메모리의 주소를 저장하는 자료형
- 기본 자료형 8개를 제외한 나머지 타입 모두
- 자바에서 미리 지정한 타입도 있고, 사용자가 직접 클래스 선언으로 타입 생성 가능
- 대표적인 참조 자료형 : **문자열 자료형**

  - 큰 따옴표(`""`) 이용해 표현

  ```java
  String stringType = "Hello";
  ```

- **문자형과 문자열**

  - **문자형**
    - 기본 자료형
    - 한 개의 문자 저장
    - 숫자 취급
  - **문자열**
    - 참조 자료형
    - 한 개 이상의 문자 저장
    - 문자 취급

  ```java
  char charType = 'A' + 1;
  System.out.println(charType);
  // B

  String stringType = "Hello" + 1;
  System.out.println(stringType);
  // Helloa
  ```

### 4. Type casting (형 변환)

- **기본 자료형간 형 변환**

  - **`(타입) 피연산자`**
  - boolean 제외한 7개의 기본 자료형은 서로 형 변환 가능

  1. **정수 ↔ 정수**

     - 작은 타입 → 큰 타입 변환하는 경우 : `(타입)` 선언 없어도 됨

     ```java
     int intType = 10000;
     long longType = (long) intType;
     // long longType = intType; 가능

     System.out.println(intType);  // 10000
     System.out.println(longType); // 10000
     ```

     - 큰 타입 → 작은 타입 변환하는 경우 : 범위가 넘어갈 경우 데이터 손실 발생

     ```java
     long longType = 10000000000L;
     int intType = (int) longType;

     System.out.println(longType); // 10000000000
     System.out.println(intType);  // 1410065408
     ```

  2. **실수 ↔ 실수**

     - `float` 과 `double` 의 임의 정밀도 차이로 인해 같은 값도 다르게 표현됨
     - 작은 타입 → 큰 타입 변환하는 경우 : `(타입)` 선언 없어도 됨

     ```java
     float floatType = 1.1234f;
     double doubleType = (double) floatType;
     // double doubleType = floatType; 가능

     System.out.println(floatType);  // 1.1234
     System.out.println(doubleType); // 1.1233999729156494
     ```

     - 큰 타입 → 작은 타입 변환하는 경우 : 범위가 넘어갈 경우 데이터 손실 발생

     ```java
     double doubleType = 1.1234567891234;
     float floatType = (float) doubleType;

     System.out.println(doubleType); // 1.1234567891234
     System.out.println(floatType);  // 1.1234568
     ```

  3. **정수 ↔ 실수**

     - 실수형 → 정수형으로 변환하는 경우 : 소수점 이하 값 버려짐

     ```java
     double doubleType = 3.5;
     int intType = (int) doubleType;

     System.out.println(doubleType); // 3.5
     System.out.println(intType);    // 3
     ```

     - 정수형 → 실수형으로 변환하는 경우 : 소수점 생성

     ```java
     int intType = 3;
     double doubleType = (double) intType;

     System.out.println(intType);    // 3
     System.out.println(doubleType); // 3.0
     ```

  4. **정수 ↔ 문자**

     - 문자형 → 정수형으로 변환하는 경우
       - `char` : 내부적으로 숫자로 처리됨
       - `int` 가 `char` 보다 범위 넓어 `(타입)` 선언 없어도 됨

     ```java
     char charType = 'A';
     int intType = (int) charType;
     // int intType = charType; 가능

     System.out.println(charType); // A
     System.out.println(intType);  // 65
     ```

     - 정수형 → 문자형으로 변환하는 경우 : 해당 숫자에 맞는 유니코드 문자로 변경

     ```java
     int intType = 65;
     char charType = (char) intType;

     System.out.println(intType);  // 65
     System.out.println(charType); // A
     ```

  5. **자동 형 변환**
     - 범위가 좁은 타입 → 넓은 타입으로 변환하는 경우
       - 값의 손실이 없다고 판단해 범위 넓은 쪽으로 자동 변환해줌
     - 자동 형 변환 가능한 방향
       - `byte` → `short` / `char` → `int` → `long` → `float` → `double`

- **기본 자료형과 참조 자료형간 형 변환**
  - 각각 특별한 문법 존재

1. **정수 ↔ 문자열**

   - 정수형 → 문자열로 변환하는 방법
     - `String.valueOf(정수);` : 정수 부분에 null일 경우 문자열 null로 표현
     - `Integer.toString(정수);` : 정수 부분에 null일 경우 NullPointerException 발생
     - `정수 + "";` : 덧셈 시 정수가 문자열로 자동 형변환됨

   ```java
   int intType = 100;

   String stringType1 = String.valueOf(intType);
   System.out.println(stringType1);  // 100

   String stringType2 = Integer.toString(intType);
   System.out.println(stringType2);  // 100

   String stringType3 = intType + "";
   System.out.println(stringType3);  // 100
   ```

   - 문자열 → 정수형으로 변환하는 경우 : `Integer.parseInt(문자열);`
     - 정수로 변환할 수 있는 형태의 문자열만 가능

   ```java
   String stringType = "100";

   int intType = Integer.parseInt(stringType);
   System.out.println(intType);  // 100
   ```

2. **실수 ↔ 문자열**

   - 실수 → 문자열로 변환하는 경우
     - `String.valueOf(실수);`
     - `Double.toString(실수);`
     - `실수 + "";`

   ```java
   double doubleType = 1.1234;

   String stringType1 = String.valueOf(doubleType);
   System.out.println(stringType1); // 1.1234

   String stringType2 = Double.toString(doubleType);
   System.out.println(stringType2); // 1.1234

   String stringType3 = doubleType + "";
   System.out.println(stringType3); // 1.1234
   ```

   - 문자열 → 실수로 변환하는 경우
     - `Double.parseDouble(문자열);`
     - 실수로 변환할 수 있는 형태의 문자열만 가능

   ```java
   String stringType = "1.1234";

   double doubleType = Double.parseDouble(stringType);
   System.out.println(doubleType); // 1.1234
   ```
