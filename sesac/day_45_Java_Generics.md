## <mark color="#fbc956">Generics</mark>

### 1. 제네릭 (Generics)

- 꺽쇠(`<>`) 와 함께 사용
- 다양한 타입의 객체를 다루는 클래스나 메서드의 컴파일 시 타입 체크 해주는 기능
- 타입을 동적으로 처리해, 마치 타입 자체를 변수화 한 기능
- **장점**
  1. 객체의 타입을 컴파일 시에 체크 → 타입 안정성을 높임
     - **타입 안정성** : 의도하지 않은 타입의 객체가 저장하는 것을 맏아 저장된 객체를 꺼낼 때 원래의 타입과 다른 타입으로 잘못 형변환돠어 발생할 수 있는 오류가 적은 정도
  2. 타입 체크와 형 변환을 생략해 코드를 간결하세게 만듦
- **종류**
  1. 제네릭 클래스
  2. 제네릭 메서드

---

## <mark color="#fbc956">제네릭 클래스</mark>

### 1. 제네릭 클래스

- 클래스에서 제네릭을 사용하는 경우

### 2. 문법

- 선언부의 클래스명 앞에 꺽쇠(`<>`) 이용해 제네릭 지정
- **타입 변수(Type Variable)** : `<T>` 와 같은 것, T 이외 자유롭게 지정 가능
  - T를 통해 어떤 타입이 들어오는지에 따라 value의 타입이 동적으로 작동됨

```java
public class Test<T> {
	private T value;

	public T getValue(){
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
```

### 3. 사용

- 제네릭 클래스의 실제 인스턴스 생성 시 `T` 대신 실제 타입 넣어줌
- 타입 매개변수에는 기본 자료형(int, double)은 대입 될 수 없음
  - 기본 자료형의 사용을 위해서는 **래퍼 클래스(Integer, Double)**를 이용
- String으로 지정

```java
public class Main {
	public static void main(String[] args) {
		Test<String> test1 = new Test<String>();
		test1.setValue("Hello");
		System.out.println(test1.getValue()); // Hello
	}
}
```

- Integer로 지정

```java
public class Main {
	public class void main(String[] args) {
		Test<Integer> test2 = new Test<>();
		test2.setValue(100);
		System.out.println(test2.getValue());
	}
}
```

### 4. 주의사항

1. **제네릭 타입으로 객체나 배열 직접 생성 불가**

   ```java
   public class Test<T> {
   	// 직접 개체 생성 불가
   	public T variable = new T();
   	// 직접 배열 생성 불가
   	public T[] array = new T[10];
   }
   ```

2. **static 멤버에 제네릭 타입 사용 불가**

   - static 멤버는 클래스의 인스턴스 생성 되기 전 이미 메모리에 할당 되어 있음
   - 타입변수 T는 클래스의 인스턴스 생성될 때 지정됨
     → static 멤버에서는 T가 어떤 타입 의미하는지 알 수 없음 → 에러 발생 !

   ```java
   public class Test<T> {
   	public static void method1(T variable) {
   		System.out.println(variable);
   	}

   	public static void method2(T[] array) {
   		System.out.println(array);
   	}

   	public static void method3(List<T> list) {
   		System.out.println(list);
   	}
   }
   ```

### 5. 타입 제한

- 제네릭은 타입 안정성을 확보하지만, 너무 자유롭기도 함
- **`extends`** : 제네릭으로 특정 타입으로만 가능하게 제한

> Number라는 타입과 하위 타입만 가능하도록 제한

```java
public class Test<T extends Number> {
	public void show(T number) {
		System.out.println(number);
	}
}
```

```java
public class Main {
	public static void main(String[] args) {
		Test<Integer> test = new Test<>();
		test.show(100); // 100
	}
}
```

---

## <mark color="#fbc956">제네릭 메서드</mark>

### 1. 제네릭 메서드

- 메서드에 제네릭을 사용하는 경우

### 2. 문법

- 메서드 선언부에 **타입 파라미터**(`<T>`)가 선언된 메서드
- 클래스에 선언된 타입 변수 T와 메서드에 선언된 타입 변수 V는 서로 독립적으로 사용됨

```java
public class Test<T> {
	private T value;

	Test(T value) {
		this.value = value;
	}

	// 제네릭 메서드
	public <V> void show(V object1, V object2) {
		System.out.println(object1);
		System.out.println(object2);
	}
}
```

### 3. 주의사항

1. **단순히 클래스의 제네릭을 이용해 매개변수 타입을 지정한 경우, 제네릭 메서드가 아님**

   ```java
   public class Test<T> {
   	public void show(T object1, T object2) {
   		System.out.println(object1);
   		System.out.println(object2);
   	}
   }
   ```

2. **제네릭 메서드의 타입 변수는 클래스의 타입 변수와 독립적으로 사용됨 (이름은 동일할 수 있음)**

   ```java
   public class Test<T> {
   	private T value;

   	Test(T value) {
   		this.value = value;
   	}

   	public <T> void show(T object1, T object2) {
   		System.out.println(object1);
   		System.out.println(object2);
   	}
   }
   ```

   ```java
   public class Main {
   	public static void main(String[] args) {
   		Test<Integer> test = new Test<>(1);

   		test.show("hello", "world");
   		// hello
   		// world
   	}
   }
   ```

### 4. 와일드 카드

- 제네릭 메서드에서 와일드 카드 이용해 매새변수 타입에 대한 제한 설정 가능
  (제네릭 클래스에서 타입 제한 사용해 특정 타입과 그의 하위 타입만 허용한 것과 같이)
- static 메서드에서도 타입 제한 이용해 특정 타입들의 매개변수만 허용 가능
  - static 메서드에 클래스 단위 제네릭 사용 불가 → 와일드 카드 이용해 문제 해결
- **와일드 카드 : `?`**
  - 종류
    1. **`<? extends T>`** : 상한 제한(T가 상한), 특정 타입과 그의 하위 타입만 허용
    2. `<? super T>` : 하한 제한(T가 하한), 특정 타입과 그의 상위 타입만 허용
    3. `<?>` : 제한 없음, 모든 타입 허용, `<? extends Object>` 와 동일한 개념

```java
import java.util.List;

public class Test {
	public static void show(List<? extends Number> numberList) {
		System.out.println(numberList);
	}
}
```

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// Integer List 생성
		List<Integer> intList = new ArrayList<>();
		intList.add(1);
		intList.add(2);

		// Doble List 생성
		List<Double> doubleList = new ArrayList<>();
		doubleList.add(1.0)
		doubleList.add(2.0);

		// Test 클래스의 static 메서드 호출
		Test.show(intList);
		Test.show(doubleList);
	}
}
```

---

### ☀️ 오늘의 배움

- **컬렉션 프레임워크를 필드로 지정할 경우**

  - `접근제어자 타입 변수명;` 에서 `타입` 부분에 `인터페이스<래퍼 클래스>` 로 지정
  - 생성자에서 `this.변수명` 은 `new 컬렉션 프레임워크 구현체<>();` 로 초기화
    > Student 클래스 생성 (이름, 나이, 성적을 가지고 성적을 저장할 수 있)

  ```java
  package org.example.collectionprac.management;

  import java.util.HashMap;
  import java.util.Map;

  public class Student {
      private String name;
      private int age;
      // 접근제어자 타입 변수명;
      private Map<String, Integer> grade;

      public Student(String name, int age) {
          this.name = name;
          this.age = age;
          this.grade = new HashMap<>();
      }

      // 성적 저장
      public Map<String, Integer> addGrade(String subject, Integer score) {
          grade.put(subject, score);
          return grade;
      }
  }

  ```

- **쓰레드**
  - 동시에 여러가지 작업을 할 수 있는 것
  - 프로세스(process) 내에서 실제로 작업을 수행하는 주체
- **프로세스 (process)**
  - 실행 중인 프로그램(program)
  - 사용자가 작성한 프로그램이 운영체제에 의해 메모리 공간을 할당받아 실행 중인 것
  - 프로세스는 프로그램에 사용되는 데이터와 메모리 등의 자원 그리고 스레드로 구성
- **캡슐화**
  - 메서드는 객체의 내부 상태에서 작동하며 객체 간 통신을 위한 기본 메커니즘 역할 함
  - 내부 상태를 숨기고 모든 상호 작용을 객체의 메서드를 통해 수행하도록 요구하는 것
- **열거형**
  - 상수들의 모음을 클래스로 정의
- **자바**
  - JVM이 읽을 수 있는 Byte 코드로 변경 (컴파일)
    - 이 때 오류나 에러 확인 가능
  - 코드를 한 줄씩 읽어나가는 인터프리터 과정 한번 더 거침
- **함수형 프로그래밍**
  - 함수를 통과시켜 나오는 return 값 활용
  - 순수 함수라 항상 값이 동일함
  - (배열 → 함수 → 리턴은 배열 → 함수 → 리턴은 배열) 계속 연쇄할 수 있으면 → **메서드 체이닝**
- **`stream`**

  ```java
  public class Main {
      public static void main(String[] args) {
          ArrayList<Integer> list = new ArrayList<Integer>(List.of(3, 1, 2, 2, 4));
          System.out.println(list);

          List<Integer> over1List = list.stream() // List를 stream으로 변경
                  .filter(x -> x > 1)             // filter 적용
                  .collect(Collectors.toList());  // List로 변경

          System.out.println(over1List);
     }
  }
  ```
