## <mark color="#fbc956">Functional Interface</mark>

### 1. 함수형 인터페이스 (Functional Interface)

- 추상 메서드가 단 하나만 존재하는 인터페이스
- **선언**
  ```java
  @FunctionalInterface
  public interface FunctionalInterface {
  	void method();
  }
  ```
  - 추상 메서드가 2개 이상 존재할 경우 함수형 인터페이스가 아님
  - default 메서드와 static 메서드는 여러 개 존재 가능
  - `@FunctionalInterface` 어노테이션 붙임
    - 필수는 아니지만 붙일 경우, 함수형 인터페이스 규칙을 잘 지키는지 컴파일 단계에서 확인
- **구현**
  1. 인터페이스 구현
  2. 익명 클래스
  3. 람다 표현식

> 함수형 인터페이스 `Adder` 선언

```java
@FunctionalInterface
public interface Adder {
	int add(int number1, int number2);
}
```

### 2. 인터페이스 구현

- 기본적인 인터페이스 구현 통해 함수형 인터페이스 사용 가능

```java
public class MyAdder implements Adder {
	@Override
	public int add(int number1, int number2) {
		return number1 + number2;
	}
}
```

```java
public class Main {
	public static void main(String[] args) {
		Adder adder = new MyAdder();

		System.out.println(adder.add(1, 2)); // 3
	}
}
```

- main 메서드에서 MyAdder의 인스턴스 생성, add 메서드 호출
- 불필요한 추가적인 클래스 파일 생성하는 단점 존재

### 3. 익명 클래스

- 익명 클래스 선언 시 불필요한 클래스 파일 생성하지 않음

```java
public class Main {
	public static void main(String[] args) {
		Adder adder = new Adder() {
			@Override
			public int add(int number1, int number2) {
				return number1 + number2;
			}
		}

		System.out.println(adder.add(1, 2)); // 3
	}
}
```

- 별도 클래스 생성 없이 main 메서드에서 바로 익명 클래스 선언 후 add 메서드 재정의함
- 익명 클래스의 인스턴스 adder 통해 add 메서드 호출 가능

### 4. 람다 표현식

- 람다 표현식 이용 시 익명 클래스 생성하지 않아도 됨

```java
public class Main {
	public static void main(String[] args) {
		Adder adder = (number1, number2) -> number1 + number2;

		System.out.println(adder.add(1, 2)); // 3
	}
}
```

---

## <mark color="#fbc956">Lambda Expression</mark>

### 1. 람다 표현식 (Lambda Expression)

- = 람다식, 익명 함수(annoymous function)
- 메서드를 하나의 간결한 식으로 표현한 것
- 익명 클래스의 인스턴스 변수에 할당한 것과 같이 람다 표현식의 결과 또한 변수에 할당 가능
- 장점 : 전반적으로 코드의 양이 줄고, 더욱 가독성 있는 코드 작성 가능

```java
public class Main {
	public static void main(String[] args) {
		Adder adder = (number1, number2) -> number1 + number2;

		System.out.println(adder.add(1, 2));
	}
}
```

### 2. 문법

```java
타입 변수명 = (매개변수1, 매개변수2, ...) -> 구현부;
```

- 함수형 인터페이스의 추상 메서드에 입력될 매개변수를 소괄호 안에 정의
- 매개변수와 구현부는 화살표(`→`) 통해 이어줌
- 구현부에 추상 메서드의 실제 구현 로직 작성
- **추가 사항**

  1. **구현부가 여러 줄일 경우, 중괄호로 감싸줌**

     ```java
     Adder adder = (number1, number2) -> {
     	int result = number1 + number2;
     	return result;
     }
     ```

  2. **구현부가 한 줄이고 반환 값 존재할 경우, return 키워드 생략 가능**

     ```java
     // before
     Adder adder = (number1, number2) -> { return number1 + number2; };

     // after
     Adder adder = (number1, number2) -> number1 + number2;
     ```

  3. **매개변수 한 개일 경우, 소괄호 생략 가능**

     ```java
     // before
     Adder adder = (number) -> number + 2;

     // after
     Adder adder = number -> number + 2;
     ```

### 3. 메서드 참조 (Method reference)

- `클래스명::메서드명`
- 람다 표현식이 단 하나의 메서드만을 호출하는 경우, 람다 표현식에서 매개변수 제거 후 더욱 간결히 사용 가능

> 문자열을 매개변수 → 정수로 변환하는 Parser 함수형 인터페이스

```java
@FunctionalInterface
public interface Parser {
	int parseToInt(String value);
}
```

```java
public class Main {
	public static void main(String[] args) {
		// before
		Parser parser = value -> Integer.parseInt(value);

		// after
		// Integer 클래스의 parseInt 메서드를 참조해 람다 표현식을 구성
		Parser parser = Integer::parseInt;

		System.out.println(parser.parseToInt("100"));
	}
}
```

- **종류**
  | 구분 | 형태 |
  | ---------------------- | ---------------------- |
  | 클래스 메서드 참조 | `클래스명::메서드명` |
  | 인스턴스 메서드 참조 | `인스턴스명::메서드명` |
  | 매개변수의 메서드 참조 | `클래스명::메서드명` |
  | 생성자 참조 | `클래스명::new` |

1. **클래스 메서드 참조**

   - 클래스 메서드는 보통 클래스명을 통해 호출함
   - 메서드 참조로 사용항 때도 마찬가지로 `클래스명::메서드명` 으로 호출

   ```java
   public class Main {
   	public static void main(String[] args) {
   		Parser parser = Integer::parseInt;
   		System.out.println(parser.parseToInt("100"));
   	}
   }
   ```

2. **인스턴스 메서드 참조**

   - `인스턴스명::메서드명`
   - 특정 인스턴스 메서드 참조

   ```java
   import java.util.ArrayList;
   import java.util.List;
   import java.util.function.Consumer;

   public class Main {
   	public static void main(String[] args) {
   		List<Integer> numbers = new ArrayList<>();
   		// before
   		Consumer<Integer> consumer = number -> numbers.add(number);
   		// after
   		// numbers라는 ArrayList의 인스턴스 통해 number add함
   		Consumer<Integer> consumer = numbers::add;
   		consumer.accept(1);
   	}
   }
   ```

3. **매개변수의 메서드 참조**

   - `클래스명::메서드명`
   - 매개변수로 전달된 인스턴스의 메서드 참조

   ```java
   import java.util.ArrayList;
   import java.util.List;
   import java.util.function.BiConsumer;

   public class Main {
   	public static void main(String[] args) {
   		List<Integer> numbers = new ArrayList<>();
   		// before
   		BiConsumer<List<Integer>, Integer> biConsumer = (list, number) -> list.add(number);
   		// after
   		// list의 add 메서드를 호출해 또 다른 매개변수인 number을 넣음
   		BiConsumer<List<Integer>, Integer> biConsumer = List::add
   		biConsumer.accept(numbers, 1);
   	}
   }
   ```

4. **생성자 참조**

   - `클래스명::new`
   - 생성자도 메서드이므로 메서드 참조 사용 가능

   ```java
   import java.util.function.Supplier;

   public class Main {
   	public static void main(String[] args) {
   		// before
   		Supplier<Object> supplier = () -> new Object();
   		// after
   		Supplier<Object> supplier = Object::new;
   	}
   }
   ```

### 4. 제네릭 사용해 여러 타입 다루기

- 제네릭과 함께 함수형 인터페이스 작성 시 람다 표현식에서 여러 타입 다룰 수 있음
  - 함수형 인터페이스도 제네릭 타입 변수 선언 가능
  - 다양한 타입 통해 조작 가능한 람다 표현식 생성 가능

> 제네릭 타입 변수를 선언한 함수형 인터페이스 Mapper 선언

```java
@FunctionalInterface
public interface Mapper<I, O> {
	O map(I input);
}
```

```java
public class Main {
	public static void main(String[] args) {
		Mapper<String, Integer> mapper = string -> Integer.parseInt(string);
		System.out.println(mapper.map("100"));
	}
}
```

---

## <mark color="#fbc956">표준 함수형 인터페이스</mark>

### 1. 표준 함수형 인터페이스

- Java에서 자주 사용되는 형식의 메서드를 함수형 인터페이스로 미리 정의함

| 함수형 인터페이스 | 추상 메서드       | 설명                                                          |
| ----------------- | ----------------- | ------------------------------------------------------------- |
| Runnable          | void run()        | 매개변수와 반환값이 모두 없을 때 사용                         |
| Supplier<T>       | T get()           | 매개변수는 없고, 반환값만 있을 때 사용 (Consumer와 반대)      |
| Consumer<T>       | void accept(T t)  | 매개변수는 있고, 반환값이 없을 때 사용 (Supplier와 반대)      |
| Function<T, R>    | R apply(T t)      | 매개변수와 반환값이 모두 있을 때 사용                         |
| Predicate<T>      | boolean test(T t) | 매개변수를 받아 조건을 검사하고 boolean 결과를 반환할 때 사용 |

### 2. Runnable 인터페이스

- 메서드의 매개변수와 반환값이 모두 없는 작업을 정의하는 함수형 인터페이스
- 추상 메서드 : `void run()`
- 주로 Thread 실행 시 사용, 별도의 값 반환하지 않고 단순 동작 실행할 경우 유용

```java
public class Main {
	public static void main(String[] args) {
		Runnable runnable = () -> System.out.println("Hello world");
		runnable.run(); // Hello world
	}
}
```

### 3. Supplier 인터페이스

- 메서드의 매개변수는 없고, 반환값만 있는 작업을 정의하는 함수형 인터페이스
- 추상 메서드 : `T get()`
- 새로운 데이터 생성 및 외부에서 얻어오는 로직을 캡슐화할 경우 유용

```java
impoer java.util.function.Supplier;

public class Main {
	public static void main(String[] args) {
		Supplier<String> supplier = () -> "Hello world";
		System.out.println(supplier.get()); // Hello world
	}
}
```

### 4. Consumer 인터페이스

- 메서드의 매개변수만 있고, 반환값이 없는 작업을 정의하는 함수형 인터페이스
- 추상 메서드 : `void accept(T t)`
- 전달받은 값을 기반으로 로그를 남기거나, 화면에 출력하거나, 내부 상태 변경 시 사용

```java
import java.util.function.Consumer;

public class Main {
	public static void main(String[] args) {
		Consumer<String> consumer = string -> System.out.println(string);
		consumer.accept("Hello world"); // Hello world
	}
}
```

- **BiConsumer 인터페이스**

  - 두 개의 매개변수를 받을 수 있는 인터페이스
  - 추상 메서드 : `void accept(T t, U u)`

  ```java
  import java.util.function.BiConsumer;

  public class Main {
  	public static void main(String[] args) {
  		BiConsumer<String, String> biconsumer = (string1, string2) -> System.out.println(string1 + " " + string2);
  		biconsumer.accept("Hello", "world"); // Hello world
  	}
  }
  ```

### 5. Function 인터페이스

- 메서드의 매개변수와 반환값이 모두 있는 작업을 정의하는 함수형 인터페이스
- 추상 메서드 : `R apply(T t)`

```java
import java.util.function.Function;

public class Main {
	public class void main(String[] args) {
		Function<String, String> function = string -> string;
		System.out.println(function.apply("Hello world")); // Hello world
	}
}
```

- **BiFunction 인터페이스**

  - 두 개의 매개변수 받는 인터페이스
  - 추상 메서드 : `R apply(T t, U u)`

  ```java
  import java.util.function.BiFunction;

  public class Main {
  	public class void main(String[] args) {
  		BiFunction<String, String, String> bifunction = (string1, string2) -> string1 + " " + string2;
  		System.out.println(bifunction.apply("Hello", "world")); // Hello world
  	}
  }
  ```

### 6. Predicate 인터페이스

- Function 인터페이스의 변형
- 매개변수를 받아 조건을 검사하고 boolean 결과를 받환하는 작업을 정의하는 함수형 인터페이스
- 추상 메서드 : `boolean test(T t)`

```java
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {
		Predicate<String> predicate = string -> string.isEmpty();
		System.out.println(predicate.test("Hello world")); // false
	}
}
```
