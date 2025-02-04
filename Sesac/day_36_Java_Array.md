## <mark color="#fbc956">Array</mark>

### 1. Array (배열)

- 동일한 자료형의 여러 변수를 하나로 묶을 수 있는 자료구조

### 2. 배열 선언

- 대괄호(`[]`) 사용

```java
타입[] 변수명;   // 주로 사용
타입 변수명[];   // C언어 스타일 배열 선언
```

```java
int[] numbers;
int numbers[];
```

### 3. 배열 초기화

- `new` 키워드 사용
- 대괄호(`[]`) 내부 : 배열의 길이 작성, 원하는 길이로 배열 초기화 가능
  - 길이 지정 후 초기값 미부여 시 배열 원소들은 기본값으로 초기화
  - 한번 생성 후 길이 늘리거나 줄일 수 없음
  - 길이 변경원할 시 변경한 길이의 새로운 배열 생성 필요

```java
변수명 = new 타입[길이];
```

```java
int[] numbers;        // 선언
numbers = new int[5]; // 초기화
```

- 선언과 초기화 동시에

```java
타입[] 변수명 = new 타입[길이];
```

```java
int[] numbers = new int[5];
```

- 원하는 원소 지정 초기화

```java
타입[] 변수명 = new 타입[] {원소};
타입[] 변수명 = {원소};
```

```java
int[] numbers = new int[] {10, 20, 30, 40, 50};
int[] numbers = {10, 20, 30, 40, 50};
```

- 자료형에 따른 배열 원소의 기본값
  | 자료형  | 설명                    | 배열 원소의 기본값        |
  | ------- | ----------------------- | ------------------------- |
  | byte    | 8비트 정수              | 0                         |
  | short   | 16비트 정수             | 0                         |
  | char    | 16비트 문자 (유니코드)  | ‘\u0000’ (null character) |
  | int     | 32비트 정수             | 0                         |
  | long    | 64비트 정수             | 0                         |
  | float   | 32비트 부동소수점 숫자  | 0.0f                      |
  | double  | 64비트 부동소수점 숫자  | 0.0                       |
  | boolean | 1비트 참/거짓 (boolean) | false                     |
  | String  | 문자열 (참조형)         | null                      |

### 4. 배열 출력

- `Arrays.toString(배열)` : 배열의 모습 출력

```java
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] numbers;
		numbers = new int[5];

		System.out.println(Arrays.toString(numbers)); // [0, 0, 0, 0, 0]
	}
}
```

### 5. 배열 길이

- `.length` : 배열의 길이 알 수 있음

```java
public class Main {
	public static void main(String[] args) {
		int[] numbers = {10, 20, 30, 40, 50};

		System.out.println(numbers.length); // 5
	}
}
```

### 6. 배열 인덱스 (Index)

- 배열 각 원소 위치
- 배열 원소 조회 및 수정 목적 자주 사용

1. 배열 원소 조회

   ```java
   public class Main {
   	public static void main(String[] args) {
   		int[] numbers = {10, 20, 30, 40, 50};

   		System.out.println(numbers[0]); // 10

   		// 배열 맨 뒤 원소 조회
   		System.out.println(numbers[4]); // 50
   		System.out.println(numbers[numbers.length - 1]); // 50
   	}
   }
   ```

2. 배열 원소 수정

   ```java
   import java.util.Arrays;

   public class Main {
   	public static void main(String[] args) {
   		int[] numbers = {10, 20, 30, 40, 50};
   		numbers[0] = -1;

   		System.out.println(Arrays.toString(numbers)); // [-1, 20, 30, 40, 50]
   	}
   }
   ```

### 7. 배열 활용

1. **배열 반복**

   ```java
   public class Main {
   	public static void main(String[] args) {
   		int[] numbers = {10, 20, 30, 40, 50};

   		for (int i = 0; i < numbers.legnth; i++) {
   			System.out.println(numbers[i]); // 10 20 30 40 50
   		}
   	}
   }
   ```

2. **향상된 for문 (enhanced for문)**

   ```java
   for (타입 변수명 : 배열) {
   	// 배열에서 원소를 하나씩 꺼내며 실행하는 동작
   }
   ```

3. **배열 정렬 : `Arrays.sort(배열)`**

   - 오름차순 정렬

   ```java
   import java.util.Arrays;

   public class Main {
   	public static void main(String[] args) {
   		int[] numbers = {50, 30, 10, 40, 20};

   		Arrays.sort(numbers);

   		System.out.println(Arrays.toString(numbers)); // [10, 20, 30, 40, 50]
   	}
   }
   ```
