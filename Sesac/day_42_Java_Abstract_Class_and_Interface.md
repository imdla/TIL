## <mark color="#fbc956">추상 클래스와 인터페이스</mark>

### 1. 다형성(Polymorphism)

- 상수 타입의 참조 변수로 하위 타입의 객체를 참조할 수 있도록 하는 특성
  (상위 타입을 이용해 하나의 타입으로 여러 하위 타입의 인스턴스 사용 가능)
- **장점**
  1. 다양한 객체를 하나의 타입으로 처리 가능 → 코드의 유연성이 높아짐
  2. 새로운 기능 추가 시 기존 코드 수정하지 않아도 됨 → 코드의 확장성 높아짐
  3. 공통 부모 클래스 활용 가능 → 코드 중복 줄고 재사용성 높아짐

> Shape(도형 의미)을 상속하는 Triangle과 Rectangle로 알아보기

1. 각 클래스의 인스턴스 생성, 이를 `Triangle` 과 `Rectangle` 이라는 하위 타입 변수에 할당

```java
Triangle triangle = new Triangle(10, 5);
triangle.calculateArea();

Rectangle rectangle = new Rectangle(15, 30);
rectangle.calculateArea();
```

1. 하위 타입 변수가 아닌, `Shape` 이라는 **상위 타입 변수에 인스턴스 할당** 가능

```java
Shape triangle = new Triangle(10, 5);
triangle.calculateArea();

Shape rectangle = new Rectangle(15, 30);
rectangle.calculateArea();
```

- 상위 타입의 참조 변수가 하위 타입의 인스턴스 직접 참조하고 있음
  - 다형성의 정의에 부합
- 다형성에 따라 한 가지의 타입을 통해 Triangle과 Rectangle이라는 여러 타입 가질 수 있음

---

## <mark color="#fbc956">추상 클래스</mark>

### 1. 추상 클래스 (Abstract Class)

- `abstract` 키워드 선언
  - 변수, 메서드, 생성자는 일반 클래스와 동일한 방식 선언
  - 추상 클래스를 상속한 자식 클래스에서 추상 클래스에 선언된 변수, 메서드, 생성자 사용 가능
- 완성되지 않은 클래스
- 자체적으로 사용 어려움, 추상 클래스를 상속한 자식 클래스를 실질적으로 사용함
- 다형성을 위한 상위 타입의 역할만을 수행
- 자식 클래스를 어떻게 구현할 것인지에 대한 틀 제시

```java
public abstract class AbstractClass {
	...
}
```

- **특징**
  1. **추상 클래스는 자체 인스턴스 생성 불가능**
  2. **추상 클래스는 구현부가 없는 추상 메서드 통해, 자식 클래스에 구현의 책임 부여함**

### 2. 추상 메서드(Abstract Method)

- `abstract` 키워드 선언
- 그 자체로는 의미가 없는 일종의 명세와 같은 역할
- 추상 메서드는 반드시 **재정의**가 되어야 함
- **구현부가 없는 메서드**
  - 메서드의 시그니처와 반환 타입만 정의, 블록 내부를 정의하지 않음
  - 중괄호(`{}`) 생략, 매개변수 정의한는 소괄호(`()`) 이후 세미콜론(`;`) 붙임

```java
public abstract class AbstractClass {
	...
	public abstract void abstractMethod();
}
```

- **자식 클래스에서 구현**
  - 추상 메서드는 추상 클래스의 상속한 자식 클래스에서 **재정의(오버라이딩)**해 구현부 정의

```java
public abstract class ParentClass {
	piblic abstract void greet();
}
```

```java
public class ChildClass extends ParentClass {
	@Override
	public void greet() {
		System.out.println("Hello World");
	}
}
```

### 3. 필요성

- **추상화 (Abstraction)**
  - 핵심적인 사항만 정해지고 구체적인 사항은 정해지지 않았음
  - 세부적인 부분은 가리고, 중요한 부분만 드러내는 것
- **추상 클래스**

  - 추상화를 위한 의도로 만들어진 클래스
  - 추상 클래스로 정의 후, 추상 메서드에 대한 정의를 통해 중요한 부분은 먼저 드러낼 수 있음
  - 추상 클래스도 자체적 변수, 생성자, 메서드를 가질 수 있지만, 인스턴스 생성은 불가
    → 멤버들을 사용하기 위해 반드시 상속을 통해 자식 클래스 구현해야 함
    > 추상 클래스 `Shape` 과 자식 클래스 `Triangle`, `Rectangle`

  ```java
  public abstract class Shape {
  	// 인스턴스 변수
  	protected int width;
  	protected int height;

  	// 생성자
  	protected Shape(int width, int height) {
  		this.width = width;
  		this.height = height;
  	}

  	// 인스턴스 메서드
  	public void show() {
  		System.out.println("width: " + this.width + " height: " + this.height);
  	}

  	// 추상 메서드
  	public abstract int calculateArea();
  }
  ```

  ```java
  public class Triangle extends Shape {
  	public Triangle (int width, int height) {
  		super(width, height);
  	}

  	@Override
  	public int calculateArea() {
  		return (this.width * this.height) / 2;
  	}
  }
  ```

  ```java
  public class Rectangle extends Shape {
  	public Rectangle (int width, int height) {
  		super(width, height);
  	}

  	@Override
  	public int calculateArea() {
  		return this.width * this.height;
  	}
  }
  ```

  > 다형성 이용

  ```java
  import java.util.Scanner;

  public class Main {
  	public static void main(String[] args) {
  		Scanner scanner = new Scanner(System.in);

  		System.out.println("Shape name input : ");
  		String shapeType = scanner.nextLine();

  		Shape shape = null;

  		if (shapeType.equals("triangle")) {
  			shape = new Triangle(3, 5);
  		} else if (shapeType.equals("rectangle")) {
  			shape = new Rectangle(10, 20);
  		}

  		int area = shape.calculateArea();
  		System.out.println("Area : " + area);
  	}
  }
  ```

---

## <mark color="#fbc956">인터페이스</mark>

### 1. 인터페이스 (Interface)

- 일종의 명세, 각 구현 클래스들이 어떤 기능을 필수로 구현해야 하는지 정의한 추상 클래스
- 각 클래스들이 **어떤 메서드를 구현해야 하는지 선언부만 작성**
  - 메서드를 사용하는 외부 대상은 명세에 따라 호출만 진행, 실제 세부 구현은 알지 못함
  - 세부 구현은 각 구현 클래스에서만 알고 있음
- **특징**
  1. 인터페이스의 변수는 `public static final` 취급
  2. 인터페이스의 메서드는 `public abstract` 취급
  3. 인터페이스는 다중 구현 지원

```java
public interface Interface {
	int NUMBER = 2;

	void absractMethod();
}
```

### 2. 구현 (Implementation)

- 인터페이스
  - 추상 클래스처럼 자체 인스턴스 변수나 메서드 가질 수 없음 → 추상체에 가까움
- 인터페이스의 명세를 실제로 구현하는 클래스
  - 작성된 명세의 내용을 실제로 채우는 느낌
- 인터페이스의 메서드는 모두 추상메서드
  - 구현 클래스에서 이를 반드시 재정의 해야 함
- 메서드 재정의 시 접근 제어자의 범위
  - **부모 클래스의 접근 제어자 ≤ 자식 클래스의 접근 제어자**

```java
public interface ParentClass {
	void greet();
}
```

```java
public class ChildClass implements ParentClass {
	@Override
	public void greet() {
		System.out.println("Hello World");
	}
}
```

### 3. 다중 구현

- 인터페이스는 다중 구현 가능
- `extends` 와 `implements` 키워드 동시에 사용해 여러 클래스 상속 및 구현 가능

```java
public interface ParentClass1 {
	void parent1Method();
}
```

```java
public interface ParentClass2 {
	void parent2Method();
}
```

```java
public class ChildClass implemetns ParentClass1, ParentClass2 {
	@Override
	public void parent1Method() {
		// 메서드 구현
	}

	@Override
	public void parent2Method() {
		// 메서드 구현
	}
}
```

### 4. static 메서드

- 인터페이스의 static 메서드도 public 접근 제어자를 가진 것으로 취급
- 인터페이스를 구현한 클래스들에서 공통으로 필요할 경우 사용

```java
public interface Interface {
	static void staticMethod() {
		// 메소드 구현
	}
}
```

- `Interface.staticMethod()` 형태로 호출 가능

### 5. default 메서드

- 인터페이스의 default 메서드도 public 접근 제어자를 가진 것으로 취급
- **자체 구현부를 가질 수 있음**
- 구현 클래스를 통해서만 호출 가능
- **구현 클래스에서 default 메서드를 반드시 재정의하지 않아도 됨**
  - 기존 로직 그대로 사용 가능 → 만약, 새로운 로직 필요 시 그 때 재정의 가능
  - **기존 구현 클래스에 대한 수정 없이도 인터페이스에 새로운 메서드 추가 가능**

```java
public interface ParentClass {
	void method();

	default void additionalMethod() {
		// ParentClass에서 메소드 구현
	}
}
```

```java
public class ChildClass1 implements ParentClass {

	@Override
	public void method() {
		// ChildClass1메서 메소드 구현
	}

	// ChildClass1에서는 additionalMethod 구현하지 않음
}
```

```java
public class ChildClass2 implements ParentClass {

	@Override
	public void method() {
		// ChildClass2메서 메소드 구현
	}

	@Override
	public void additionalMethod() {
		// ChildClass1에서 메소드 재정의
	}

}
```

### 6. private 메서드

- static과 default 메서드의 중복을 줄이는 목적으로 사용 가능
- private 접근 제한을 가져 인터페이스 내부에서만 가용 가능
- private 접근 제한은 default 메서드, static 메서드에 적용 가능

```java
public interface Interface {
	default void defaultMethod1() {
		printDefaultMethod(1);
	}

	default void defaultMethod2() {
		printDefaultMethod(2);
	}

	private void printDefaultMethod(int number) {
		System.out.println("This is Default message.")
		System.out.println(number + " message");
	}
}
```

- private 메서드 없을 경우 중복된 코드

  ```java
  public interface Interface {
  	default void defaultMethod1() {
  		System.out.println("This is Default message.");
  		System.out.println("1 message");
  	}

  	default void defaultMethod2() {
  		System.out.println("This is Default message.");
  		System.out.println("2 message");
  	}
  }
  ```

### 7. 추상 클래스와 인터페이스 비교

|  | **추상 클래스** | **인터페이스** |
| --- | --- | --- |
| **키워드** | `abstract class` | `interface` |
| **목적** | - 상위 클래스에서 공통 속성 · 메서드 제공 - 일부만 추상화 | 클래스가 어떤 기능(메서드 집합)을 가져야 하는지에 대한 명세 역할 |
| **설계 관점** | - 미완성된 클래스 - 상속 계층 내 공통 구조 제공 | - 기능 명세서 - 다양한 구현체를 하나의 타입으로 다루기 위한 규약 제공 |
| **멤버 구성** | 필드, 생성자, 추상 메서드, 일반 메서드 | 상수, 추상 메서드, default 메서드, static 메서드, private 메서드 |
| **변수** | 일반 변수 선언 가능 | `public static final` 로 취급 (상수만 가능) |
| **메서드** | 추상 메서드, 일반 메서드 모두 선언 가능 | `public abstract` 로 취급 (default, static, private 메서드 제외) |
| **인스턴스 생성** | 직접 생성 불가 | 직접 생성 불가 |
| **다형성 지원** | 가능 | 가능 |
| **상속 (구현)** | 단일 상속만 가능 | 다중 구현 가능 |
| **추가 메서드 정의 시 영향** | 하위 클래스가 영향을 받지 않음 | 기존 구현 클래스가 영향을 받을 수 있으나 default 메서드로 해결 가능 |

---

### ☀️ 오늘의 배움

- 명세 : 부모가 가지고 있는 것을 자식은 반드시 만들어야 해
  1. **인터페이스**
     - 어떤 것을 구현할지 적어놓은 명세
     - `implements`
     - 메서드만 가짐 (기능적인 것을 부여)
     - 인터페이스는 여러 개 받을 수 있음 (다중 구현 지원)
     - `특정 기능 추가에 대한 느낌`
  2. **추상 클래스**
     - `abstract` 키워드 사용
     - 변수와 메서드 가짐
     - 구현이 없는 메서드를 가짐 → 이를 자식에 강제함
     - 인스턴스 생성 불가능
     - `아직 완성되지 않은 클래스 느낌`
- **클래스**

  - 메서드에 대한 구현
  - 인스턴스 생성

- 부모 클래스의 어떤 메서드들은 내부의 로직이 필요없고

  - 매번 자식 클래스에서 부모 클래스의 메서드를 오버라이드해서 재정의 한다면

    1. 부모 클래스의 메서드를 이름만 지정해주는 것이 필요하지 않을까 ? → 명세만 알려줘

       (`이거 만들어`라고 하는 강제하기 위한 장치)

    2. 상속 자체를 하지말자 (어차피 바꿀 것이니까 자식에서 그냥 처음부터 만들자)

- **다형성**

  - 다양한 형태를 가진 성질
  - 각자 메서드 가짐 → 실행 주체마다 다른 기능
  - spring의 DI와 관련

- **오버라이딩과 오버로딩**

  - **오버로딩** : 같은 메서드명 가지지만 다른 매개변수 가짐

- **동적 바인딩**
