## <mark color="#fbc956">예외 처리</mark>

### 1. 프로그램 오류

- **에러(오류)**
  - 프로그램이 실행 중에 어떤 원인에 의해서 오작동을 하거나 비정상적으로 종료되는 경우
- **종류**
  1. **컴파일 에러** : 컴파일 시에 발생하는 에러
  2. **런타임 에러** : 실행 시에 발생하는 에러
  3. **논리적 에러** : 실행은 되지만, 의도와 다르게 동작하는 것
- **Java는 실행(런타임) 시 발생할 수 있는 프로그램 오류 구분**
  - **에러(Error)** : 메모리 부족이나 스택 오버 플로우와 같이 발생하면 복구할 수 없는 치명적인 오류
  - **예외(Exception)** : 발생하더라도 수습될 수 있는 비교적 덜 치명적인 오류

### 2. 예외 클래스의 계층 구조

- Java에서 실행 시 발생할 수 있는 에러(Error)와 예외(Exception)를 클래스로 정의
- 모든 클래스의 조상은 Object 클래스
  - 에러와 예외 클래스도 Object의 자손
- 모든 예외의 최상위 조상은 Exception 클래스

---

## <mark color="#fbc956">예외 처리 (try ~ catch문)</mark>

### 1. 예외 처리 (Exception Handling)

- 프로그램 실행 시 발생할 수 있는 예외에 대비한 코드 작성하는 것
- 프로그램의 비정상 종료를 막고, 정상적인 실행 상태 유지위해 진행
- 처리하지 못한 예외는 프로그램을 비정상적으로 종료시킴
- JVM의 예외 처리기(UncaughtExceptionHandler)는 예외의 원인을 콘솔에 출력

### 2. `try ~ catch문`

- 예외 처리 관련 문법

```java
try {
	// 예외 발생 가능성 있는 동작
} catch (Exception1 e) {
	// Exception1이 발생한 경우 실행하는 동작
} catch (Exception2 e) {
	// Exception2이 발생한 경우 실행하는 동작
} catch (Exception3 e) {
	// Exception4이 발생한 경우 실행하는 동작
}
```

- **catch 블록**
  - **괄호`()`** : 괄호 내에 처리하고자 하는 예외와 같은 타입의 참조 변수 하나를 선언
  - **블록`{}`** : 괄호`()` 내에 처리된 예외가 발생한 경우 실행할 동작 작성
  - 발생한 예외의 종류와 일치하는 단 한 개의 catch 블록만 수행
- **예외 발생한 경우 발생하지 않은 경우 흐름**
  - **예외 발생한 경우**
    1. 발생한 예외와 일치하는 catch 블록 있는지 확인
    2. 일치하는 catch 블록 찾을 경우, 해당 블록의 동작 수행
    3. 전체 try ~ catch문을 빠져나가 그 다음 동작 계속 수행
    4. 만약 일치하는 catch 블록이 없다면 예외는 처리되지 않음
  - **예외 발생하지 않은 경우**
    1. catch 블록 거치지 않고 전체 try ~ catch문 빠져나가 이후 동작 수행

### 3. 예외 처리 순서

```java
public class Main {
	public static void main(String[] args) {
		System.out.println("try~catch문 전의 동작 실행");

		try {
			System.out.println("예외 발생 전 동작 실행");
			System.out.println(0 / 0); // ArithmeticException 발생
			System.out.println("예외 발생 후 동작 미실행");
		} catch (ArithmeticException e) {
			System.out.println("ArithmeticException catch 블록 실행");
		} catch (Exception e) {
			System.out.println("Exception catch 블록 미실행");
		}

		System.out.println("tray~catch문 후의 동작 실행");
	}
}
```

1. try ~ catch문 이전 동작 수행
2. try문 안에 예외 발생 시 → 발생한 예외 클래스의 인스턴스 생성
3. 맨 위 catch 블록부터 차례대로 내려가며 확인
   - 생성된 예외 인스턴스가 괄호`()` 내에 선언된 예외 클래스에 해당하는지 `isinstanceof` 연산자 통해 검사
   - 모든 예외 클래스는 Exception 클래스의 자손
     - catch의 괄호`()` 에 Exception 클래스 타입 선언 시 어떤 종류의 예외 발생해도 해당 catch문 블록에 의해 처리
   - 여러 catch 블록에서 처리될 수 있는 예외는 가장 먼저 만나는 catch 블록에서 먼저 처리
4. 검사 결과가 true이면 catch의 블록`{}` 에 있는 동작 모두 수행 후 try~catch문 탈출
5. try~catch문 이후의 동작 수행

### 4. 예외 메시지 확인

- 예외 발생 시, 생성되는 예외 클래스의 인스턴스에 발생한 예외에 대한 정보 담겨있음
  - `printStackTrace()` : 스택 영역에 있던 메서드의 정보와 예외 메시지 출력
  - `getMessage()` : 예외 인스턴스에 저장된 메시지 반환

### 5. 멀티 catch 블록

- JDK 1.7부처 여러 catch 블록 `|` 기호 이용해 하나의 catch 블록으로 합칠 수 있음
- 연결할 수 있는 예외 클래스 개수 제한 없음
- 연결된 예외 클래스가 상속 관계에 있을 경우 컴파일 에러 발생
  - 다형성에 의해 동일하다고 인식
- 예외 인스턴스 e가 정확히 어떤 타입의 예외 클래스인지 모르기 때문에, 멀티 catch 블록에서 e의 메서드 사용 시 **나열된 예외 클래스들의 공통 조상에 선언된 메서드만 사용 가능**

```java
try {
	...
} catch (Exception1 | Exception2 e) {
	e.printStackTrace()
}
```

---

## <mark color="#fbc956">예외 발생 (throw)</mark>

### 1. 예외 발생 (throw)

- 의도적으로 예외 발생시키는 경우 `throw` 키워드 사용
  1. `new` 연산자 이용해 발생시키려는 예외 클래스의 인스턴스 생성
     - 생성자의 문자열 : 해당 문자열이 예외 메시지로 저장
     - 해당 메시지는 `getMessage()` 통해 얻을 수 있음
  2. `throw` 키워드 이용해 예외 발생시킴

```java
try {
	Exception e = new Exception("Exception occurred");
	throw e;
} catch (Exception e) {
	System.out.println("Exception: " + e.getMessage());
}
```

- 조건문을 이용한 값의 유효성 검증 위해 throw 키워드 사용
  - 특정 데이터의 값이 정상적이지 않은 경우 예외 발생 시킴
  - 예외 발생 시 프로그램 자체가 종료되어 정상적이지 않은 상태에서 로직이 더 진행되는 경우 방지

```java
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter a number: ");
		int number = scanner.nextInt();

		// 유효성 검증
		if (number < 0) {
			throw new RuntimeException("Only numbers greater than 0 are allowed");
		}

		System.out.println("Number entered: " + number);
	}
}
```

---

## <mark color="#fbc956">Checked & Unchecked</mark>

> **예외 클래스 분류 : Checked와 Unchecked**

### 1. Checked 예외

- 컴파일러가 예외 처리를 확인하는 예외
  - RuntimeException을 제외한 예외
- 반드시 try~catch문을 이용해 발생할 가능성이 있는 예외에 대한 처리 필요
- 예외 처리 하지 않을 경우 컴파일 단계에서 에러 발생시킴 (실행 자체가 안됨)

### 2. Unchecked 예외

- 컴파일러가 예외 처리를 확인하지 않는 예외
  - RuntimeException
- checked 예외와 달리 try~catch문을 통한 예외 처리를 강제하지 않음
- 런타임에서 발생하는 예외들은 프로그래머의 실수에 의해 발생해 실수 없다면 발생하지 않음
  - 예외 발생할 수도, 하지 않을 수도 있어 예외 처리를 강제하지 않음

## 메서드에 예외 선언

---

- 예외 발생 시 try~catch문으로 직접 처리하는 대신 예외 처리의 책임을 메서드의 바깥으로 넘겨주는 방법
- 메서드 선언부에 `throws` 키워드 : 발생할 가능성이 있는 예외에 대한 처리 강제
  - 메서드 사용하는 사람이 선언부 통해 이 메서드 사용하기 위해 어떤 예외 처리 필요한지 확인
- 메서드에 예외 선언 시 일반적으로 RuntimeException 클래스들 적지 얺음
  - 보통 반드시 처리해야 하는 예외들만 선언 (Checked 예외를 주로 선언)
- throws 통한 예외 선언은 해당 메서드를 호출한 부분에 예외를 넘겨주는 것
  - 어느 한 곳에서는 반드시 try~catch문을 이용해 예외 처리 필요

```java
public void method() throws Exception1, Exception2, ... {
	...
}
```

---

## <mark color="#fbc956">finally 블록</mark>

- 예외 발생여부에 관계없이 실행되어야 할 코드 포함

```java
try {
	// 예외 발생 가능성 있는 동작
} catch (Exception e) {
	// 예외 발생한 경우 실행하는 동작
} finally {
	// 예외 발생 여부 관계없이 항상 실행하는 동작
}
```

- try 블록에 return 있을 경우 finally 블록의 동작 먼저 실행된 후 return됨

```java
public class Main {
	public static void main(String[] args) {
		String result = method();
		System.out.println(result);
	}

	private static String method() {
		try {
			System.out.println("method"); // 1 (실행 순서)
			return "Return inside the block"; // 3
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("finally block");
		}

		return "Return outside the block" // 2
	}
}
```

---

## <mark color="#fbc956">사용자 정의 예외</mark>

- **사용자 정의 예외 (=커스텀 예외 클래스)**
  - Java 표준 예외 클래스 대신 Exception 혹은 RuntimeException 클래스 상속 받아 사용자 정의 예외 클래스 직접 정의 가능

```java
public class CustomException extends RuntimeException {
	CustomException(String message) {
		super(message);
	}
}
```

- 사용자 정의 예외 클래스 생성 시 예외 발생시킬 때 필요한 값을 원하는대로 기록 가능

> 예외 발생시킬 때 별도의 에러 코드 부여위한 사용자 정의 예외 클래스 선언

```java
public class CustomException extends RuntimeException {
	private final int errorCode;

	CustomException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	CustomException(String message) {
		this(message, 400);
	}

	public int getErrorCode() {
		return this.errorCode;
	}
}
```

- 예외 발생 시 예외 메시지와 에러 코드 얻어 예외 상황에 대한 정보 제공 가능
  - `getMessage()` : 예외 메시지
  - `getErrorCode()` : 에러 코드

---

### ☀️ 오늘의 배움

- **JS의 예외처리**
  ```jsx
  try {
  	에러 발생할 여지가 있는 로직
  } catch (어떤 에러인지) {
  	에러에 대한 처리
  } finally {
  	무조건 실행
  }
  ```
  - JavaScript는 어떤 에러인지 명시해주지 않아도 괜찮지만 Java는 어떤 에러인지 명시 필요
- **클래스 사용**
  - 유틸성 메서드들의 모을을 사용할 때 주로 사용
- **예외 처리 필요성**
  - 일관된 처리
  - 문제 상황 강조
- **Checked**

  - 이것은 반드시 처리를 해야해 하는 오류
  - 외부에서 발생할 수 있는 예외일 경우
  - throws : 에러 처리 강제하기 위한 장치

  ```jsx
  public class Main {
      public static void main(String[] args) {
          try {
              func();
          } catch (RuntimeException e) {
              System.out.println("error in func");
          }

      }

      public static void func() throws RuntimeException {
          System.out.println("func 실행");

          throw new RuntimeException("func error !");
      }
  }
  ```

- **unchecked**
  - 예상가능한데 제공하지 않는 에러일 때
- **throw**
  - 에러를 발생시킨다는 동작
- **throws**
  - 메서드가 메서드한테 에러 발생시킬 가능성이 존재한다는 것 알려줌
  - checked일 때 try catch 에러 처리를 강제함
- Liskov Substitution Principle (리스코프 치환 원칙)
  - 다형성 관련
