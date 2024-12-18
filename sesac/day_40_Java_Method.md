## <mark color="#fbc956">Method</mark>

### 1. 메서드(Method)

- 클래스 내부에 정의된 함수

### 2. 메서드 선언 및 호출

1. **선언**

   - 클래스 내부 작성
   - 메서드명 : 카멜 케이스 사용, 동사로 시작, 예약어 작성 불가
     (메서드는 어떤 동작을 할 수 있는지 정의한 것)

   ```java
   public class 클래스명 {

   	public 반환타입 메서드명(매개변수) {
   		// 메서드 내부 실행 동작;
   	}
   }
   ```

2. **호출(call)**

   - `메서드명(인자)`
   - 메서드에 특정 값을 전달해 결과 반환

   ```java
   public class Mian {
   	public static void main(String[] args) {
   		int bigger = getBigger(1, 2);
   		System.out.println(bigger); // 2
   	}

   	public static int getBigger(int number1, int number2) {
   		if (number1 > number2) {
   			return number1;
   		} else {
   			return number2;
   		}
   	}
   }
   ```

### 3. 매개변수, 인자, 반환값

- **매개변수 (Parameters)**
  - 메서드로 전달된 값이 할당되는 변수
- **인자 (Arguments)**
  - 메서드 호출 시 실제로 전달하는 값
- **반환값**
  - 메서드 안에서 일련의 로직을 통해 계산된 결과값
  - `return` 키워드 통해 지정 가능
  - 메서드 선언 시 작성하는 반환 타입에 맞는 값만 반환 가능

### 4. 지역 변수와 스코프

- **지역 변수 (Local Variable)**
  - 메서드 안에 선언된 변수들은 해당 메서드 안에서만 사용 가능
  - 자료형에 따른 기본값으로 초기화되지 않음
  - 값을 초기화하지 않고 미리 선언 가능
    - 하지만 지역 변수 사용 위해 반드시 값 명시적으로 초기화하는 과정 필요
- **스코프 (Scope)**
  : 변수는 선언된 위치에 따라 서로 다른 영향 범위(스코프) 가짐
  1. **클래스 변수**
     - 클래스 스코프 가짐
     - 변수 변경 시 해당 클래스의 모든 인스턴스가 영향 받음
  2. **인스턴스 변수**
     - 인스턴스 스코프 가짐
     - 변수 변경 시 해당 인스턴스의 모든 메서드가 영향 받음
     - 다른 인스턴스는 영향 받지 않음
  3. **지역 변수**
     - 메서드 스코프 가짐
     - 변수 변경 시 해당 메서드만 영향 받음
     - 다른 메서드는 영향 받지 않음

## <mark color="#fbc956">메서드 형태</mark>

### 1. 매개변수 O, 반환값 O

- 데이터를 넘겨받아 처리한 결과 반환
- 가장 기본적인 경우

```java
public class Main {
	public static void main(String[] args) {
		int result = add(1, 2);
		System.out.println(result); // 3
	}

	public static int add(int x, int y) {
		return x + y;
	}
}
```

### 2. 매개변수 O, 반환값 X

- 데이터를 넘겨받아 처리하지만 결과를 반환할 필요 없는 경우
- `void(비어있는)` : 반환 값이 존재하지 않을 때 사용하는 메서드의 반환 타입

```java
public class Main {
	public static void main(String[] args) {
		greet("john"); // hello John
	}

	public static void greet(String name) {
		System.out.println("hello" + name);
	}
}
```

### 3. 매개변수 X, 반환값O

- 데이터를 넘겨받지 않고 항상 정해진대로 처리한 결과를 반환하는 경우

```java
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] evenNum = getEvenNumUnderTen();
		System.out.println(evenNum); // [2, 4, 6, 8]
	}

	public static int[] getEvenNumUnderTen() {
		int[] evenNums = {2, 4, 6, 8};

		return evenNums;
	}
}
```

### 4. 매개변수 X , 반환값 X

- 데이터를 넘겨받지 않고 항상 정해진대로 처리, 결과를 반환할 필요가 없는 경우

```java
public class Main {
	public static void main(String[] args) {
		greet(); // Hello world
	}

	public static void greet() {
		System.out.println("Hello world");
	}
}
```

## <mark color="#fbc956">클래스 메서드와 인스턴스 메서드</mark>

### 1. 클래스 메서드 (= 정적 메서드)

- `static` 키워드 메서드
- 클래스 자체에 속해있는 메서드
- 특징
  1. 별도의 인스턴스 생성없이 사용 가능
  2. 클래스 메서드에서 인스턴스 변수 사용하거나 인스턴스 메서드 호출 불가
- 호출 : `클래스명.메서드명()`

### 2. 인스턴스 메서드

- 클래스의 인스턴스에 속해있는 메서드
- 인스턴스 변수와 관련한 작업 수행
  - 반드시 인스턴스를 생성해야만 사용 가능
- 호출 : `인스턴스명.메서드명()`

```java
public class Rectangle {
	// 클래스 변수
	public static String color = "red";

	// 인스턴스 변수
	public int width;
	public int height;

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// 클래스 메서드
	public static void printColor() {
		System.out.println("Rectangle Color: " + color);
	}

	// 인스턴스 메서드
	public void printArea() {
		int area = this.width * this.height;
		System.out.println("Reactangle Area: " + area);
	}
}
```

### 3. 주의사항

- 클래스 메서드는 인스턴스 여부와 상관없이 처음 클래스 로딩 시 메모리의 static 영역에 고정됨
  - 클래스 메서드는 인스턴스 생성하지 않아도 사용 가능
  - 클래스 메서드에서는 인스턴스 변수를 사용하거나 인스턴스 메서드를 호출할 수 없음
    (= 아직 존재하지 않는 인스턴스의 변수나 메서드를 사용할 수 없다는 것)

---

## <mark color="#fbc956">메서드 시그니처와 오버로딩</mark>

### 1. 메서드 시그니처 (Signature)

- **메서드 시그니처**
  : **메서드의 이름, 매개변수 타입, 매개변수 개수** 의미
  - 메서드 시그니처에 해당하는 요소들이 모두 같을 경우 동일한 메서드로 취급
  - 해당 요소들 중 하나라도 같지 않으면 다른 메서드로 취급
- 시그니처
  : 해당 메서드를 식별할 수있는 고유의 서명

### 2. 메서드 오버로딩 (Overloading)

- 하나의 클래스에서 이름이 같은 메서드를 여러 개 선언하는 것
  - 이름은 같아도 매개변수 타입, 개수 중 다른 것 있을 경우 다른 메서드로 취급해 같은 이름의 메서드 여러 개 선언 가능
- **반환 타입은 메서드 시그니처에 포함되지 않음**
  - 반환 타입이 다른 것은 오버로딩 불가능
- 오버로딩의 필요성
  - 오버로딩이 없을 경우 매개변수의 타입만 다르고 내부 동일한 기능 동작하는 메서드들은 각자 다른 이름을 가져야함
    → 타입이 다르다는 이유로 이름 구분 시 기능 자체가 다르다고 오해할 수 있음
    → 메서드 생성 시 계속 새로운 이름으로 만들어야 함
    → 메서드 사용 시 각기 다른 이름을 모두 기억해야 함
    → 불편함

### 3. 가변인자 (Variable Arguments)

- **`타입… 매개변수명`**
- 메서드의 매개변수의 개수를 동적으로 설정할 수 있는 기능
- 가변인자 매개변수는 **배열**로 취급

```java
public class Main {
	public static void main(String[] args) {
		int result1 = add(1, 2);
		int result2 = add(1, 2, 3, 4, 5);

		System.out.println(result1); // 3
		System.out.println(result2); // 15
	}

	public static int add(int... numbers) {
		int result = 0;

		for (int number : numbers) {
			result += number;
		}

		return result;
	}
}
```

---

### ☀️ 오늘의 배움

- class
  - 메서드
    - 인스턴스 메서드
    - 클래스 메서드
  - 변수
    - 인스턴스 변수
    - 클래스 변수(`static` 키워드)
- class
  - 비슷한걸 모음(군집) → 공통점 → 클래스 변수(static 변수)
  - 클래스, 인스턴스 모두 클래스 변수에 접근 가능함
    - 하지만 클래스에서 접근하는 것이 바람직함
  - 인스턴스 변수는 인스턴스만 접근 가능
- 같은 이름 가진 다른 함수 가질 수 있음
  - 메서드 오버로딩
    : 메서드 이름 / 매개변수 타입 / 매개변수 개수 다르면 → 다른 메서드로 인식
- 스코프(큼 ↔ 작음)
  - class → 인스턴스 → function → block 변수
