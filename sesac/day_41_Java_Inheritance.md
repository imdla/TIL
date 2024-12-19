## <mark color="#fbc956">Inheritance</mark>

### 1. 상속 (Inheritance)

- 기존 클래스(부모 클래스)의 멤버를 새로운 클래스(자식 클래스)가 물려받아 사용하는 기능
- **부모 클래스(상위 클래스)** : 자신의 멤버 변수와 메서드를 물려주는 기존 클래스
- **자식 클래스(하위 클래스)** : 기존 클래스의 멤버 변수와 메서드를 물려받는 새로운 클래스
- `extends` 키워드로 상속 사용
  - 생성자와 초기화 블록은 상속되지 않음
  - 오로지 멤버 변수와 메서드만 상속됨
  - Java는 오직 단일 상속만 지원 (다중 상속 시도할 경우 컴파일 에러 발생)
- **장점 :** 코드의 재사용성 높이고 중복 제거

```java
public class ParentClass {
	// 부모 클래스
}
```

```java
public class ChildClass extends ParentClass {
	// 자식 클래스
}
```

### 2. `super()`

- 자식 클래스에서 부모 클래스의 생성자 호출할 때 사용
- `this()`와 비교
  - `this()` : 같은 클래스의 다른 생성자 호출
  - `super()` : 부모 클래스의 생성자 호출
- 자식 클래스 생성자의 맨 첫 줄에 반드시 `super()` 호출
  - 자식 클래스는 부모 클래스의 모든 것을 포함
    → 부모 클래스의 멤버에 대한 초기화 작업이 우선되어야 함

```java
public class ChildClass extends PrentClass {
	자식 클래스 인스턴스 변수1;
	자식 클래스 인스턴스 변수2;

	public ChildClass() {
		super();
		자식 클래스 인스턴스 변수 초기화 동작1;
		자식 클래스 인스턴스 변수 초기화 동작2;
		...
	}
}
```

- **특징**

  1. **`super()` 생략 시, 컴파일러가 부모 클래스의 기본 생성자를 호출하는 `super()` 자동 삽입함**

     - 자식 클래스의 인스턴스가 생성될 때, 자동으로 부모 클래스의 기본 생성자가 먼저 호출되어 부모 클래스의 인스턴스 먼저 생성
     - 이후 자식 클래스의 생성자의 나머지 동작을 수행

     ```java
     public class ParentClass {
     	// 부모 클래스 기본 생성자
     	public ParentClass() {
     	}
     }
     ```

     ```java
     public class ChildClass extends ParentsClass {
     	// 자식 클래스 기본 생성자
     	public ChildClass() {
     		// 부모 클래스의 기본 생성자를 호출하는 super()를 컴파일러가 자동으로 삽입함
     	}
     }
     ```

  2. **부모 클래스에 기본 생성자가 없다면, 자식 클래스의 생성자에서 매개변수가 있는 `super()` 명시적으로 호출해야 함**

     - 부모 클래스에 매개변수가 있는 생성자만 존재할 경우, 자식 클래스의 생성자에서 부모 클래스의 기본 생성자를 호출할 수 없음
     - 매개변수 없는 `super()` 를 호출할 수 없음
     - 매개변수가 있는 생성자는 컴파일러에 의해 자동으로 삽입되지 않음
     - 매개변수 있는 `super()` 는 반드시 자식 클래스의 생성자 첫 줄에 명시되어야 함

     ```java
     public class ParentClass {
     	private String parentValue;

     	// 부모 클래스에 매개변수가 있는 생성자만 존재 (기본 생성자 없음)
     	public PrentClass(String parentValue) {
     		this.parentValue = parentValue;
     	}
     }
     ```

     ```java
     public class ChildClass extends PrentClass {
     	private String childValue;

     	public ChildClass(String parentValue, String childValue) {
     		super(parentValue);
     		this.childValue = childValue;
     	}
     }
     ```

### 3. `super`

- 부모 클래스의 멤버 변수나 메서드 참조 시 사용
- `this` 와 비교
  - `this` : 인스턴스 자신에 대한 참조 변수
  - `super` : 부모에 대한 참조 변수
- 부모 클래스의 멤버와 자식 클래스의 멤버가 이름이 같게 정의 되는 경우 구별 위해 사용
  - 인스턴스 변수 뿐만 아니라, 부모 클래스의 인스턴스 메서드 호출 시에도 사용

```java
public class ParentClass {
	public String value = "parent value";
}
```

```java
public class ChildClass extends ParentClass {
	public String value = "child value";

	public void show() {
		System.out.println(super.value); // parent value
		System.out.println(this.value);  // child value
	}
}
```

### 4. 메서드 오버라이딩 (Overriding)

- 부모 클래스로부터 상속받은 메서드를 자식 클래스에서 재정의하는 것
- **메서드 오버라이딩 조건**
  1. 기존 메서드와 재정의한 메서드의 **동일한 시그니처(이름과 매개변수 정보)**
  2. 기존 메서드와 재정의한 메서드의 **동일한 반환 타입**
  3. 재정의한 메서드의 **접근 제어자**는 기존 메서드보다 **좁은 범위로 변경 불가**
  4. **static 메서드는 재정의 불가**
     - 자식 클래스에서 동일한 이름으로 static 메서드를 선언 가능
     - 이는 재정의한 것이 아니라 별개의 메서드를 선언한 것

```java
public class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void introduce() {
		System.out.ptintln("Hello ! My name is" + this.name);
		System.out.ptintln("My age is" + this.age + "years old.");
	}
}
```

```java
public class Student extends Person {
	private String major;

	public Person(String name, int age, String major) {
		super(name, age);
		this.major = major;
	}

	@Override
	public void introduce() {
		super.introduce();
		System.out.ptintln("My major is" + this.major);
	}
}
```

---

## <mark color="#fbc956">Object 클래스</mark>

### 1. Object 클래스

- 모든 클래스의 **최상위 부모 클래스**
- Java의 모든 클래스는 Object 클래스를 암묵적으로 상속받음
  - 아래의 표와 같은 기본 메서드를 자동으로 상속받음

| Object 클래스의 메서드       | 설명                                                    |
| ---------------------------- | ------------------------------------------------------- |
| `String toString()`          | 객체 자신의 정보를 문자열로 변환                        |
| `boolean equals(Object obj)` | 객체 자신과 obj가 동등한 객체인지 비교                  |
| `int hashCode()`             | 객체 자신의 해시코드를 반환                             |
| `Object clone()`             | 객체 자신의 복사본을 반환                               |
| `void finalize()`            | 가비지 컬렌션에 의해 객체가 소멸될 때 호출됨            |
| `Class getClass()`           | 객체 자신의 클래스 정보를 담고 있는 Class 인스턴스 반환 |
| `void notify()`              | 현재 객체를 사용하려고 기다리는 쓰레드 중 하나를 깨움   |
| `void notifyAll()`           | 현재 객체를 사용하려고 기다리는 모든 쓰레드를 깨움      |

| `void wait()`
`void wait(long timeout)`
`void wait(long timeout, int nanos)` | 다른 쓰레드가 notify()나 notifyAll()을 호출할 때까지, 현재 쓰레드를 무한히 혹은 저장된 시간(timeout, nanos) 동안 대기시킴 |

### 2. `toString()`

- 객체 자신의 정보를 문자열로 반환하는 메서드
- 자식 클래스에서 오버라이딩하지 않을 경우, 객체 출력 시 `클래스명+16진수 해시코드 값` 출력
- 실제 Object 클래스의 `toString()` 메서드는 아래와 같이 작성됨

```java
public class Object {
	...
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode());
	}
	...
}
```

- 자식 클래스에서 재정의해 원하는 형식으로 출력할 경우
  ```java
  public class Person {
  	private String name;
  	private int age;

  	public Person(String name, int age) {
  		this.name = name;
  		this.age = age;
  	}

  	@Override
  	public String toString() {
  		return "Name: " + this.name + ", Age: " + this.age;
  	}
  }
  ```
  - `System.out.println()` 은 내부적으로 인자로 넣은 객체의 `toString()` 값 출력함
  - 재정의하지 않을 경우 `클래스이름 + 16진수 해시코드 값` 이 출력됨

### 3. `equals(Object obj)`

- 객체 자신과 obj가 동등한 객체인지 비교하는 메서드
- 자식 클래스에서 오버라이딩 하지 않을 경우, 동일 연산자(`==`) 를 통해 두 객체가 서로 같은 주소값을 갖는지 비교
- 실제 Object 클래스의 `equals()` 메서드는 아래와 같이 작성됨

```java
public class Object {
	...
	public boolean equals(Object obj) {
		return (this == obj);
	}
	...
}
```

- 자식 클래스에서 재정의해 원하는 형식으로 비교할 경우
  ```java
  public class Person {
  	private String name;
  	private int age;

  	public Person(String name, int age) {
  		this.name = name;
  		this.age = age;
  	}

  	@Override
  	public boolean equals(Object obj) {
  		if (obj == null || getClass() != obj.getClass()) {
  			return false;
  		}

  		Person other = (Person) obj;
  		return this.name.equals(other.name) && this.age == other.age;
  	}
  }
  ```
  - obj가 비교 가능 유효한 개체인지 검사 수행
  - obj는 Object 타입으로 내부 변수의 값 비교위해 Person 타입 형 변환함
- Java의 문자열(String)도 리터럴 값 통해 비교할 수 있도록 equals() 재정의
  ```java
  public final class String
  	implements java.io.Serializable, Comparable<String>, CharSequence, Constable, ConstantDesc {
  		...
  		public boolean equals(Object anObject) {
  			if (this == anObject) {
  				return true;
  			}
  			return (anObject instanceof String aString)
  							&& (!COMPACT_STRINGS || this.coder == aString.coder)
  							&& StringLatin1.equals(value, aString.value);
  		}
  		...
  	}
  ```

### 4. `hashCode()`

- 객체 자신의 해시코드 반환
- 객체 메모리 주소 값을 이용해 해시 코드 만들어 반환
  - 해시코드 : 주소 값을 통해 만들어지는 고유한 값, 각 객체 구별 가능
- 실제 Object 클래스의 `hashCode()` 메서드는 아래와 같이 작성됨

```java
public class Object {
	...
	@IntrinsicCandidate
	public native int hashCode();
	...
}
```

- `equals()` 메서드 재정의 시 반드시 `hashCode()` 메서드도 함께 재정의 필요
  ```java
  @Override
  public int hashCode() {
  	return Objects.hash(name, age);
  }
  ```
  - 재정의하지 않을 경우 Java의 컬렉션 프레임워크에서 해시코드를 사용하는 HashSet, HashMap과 같은 자료구조 사용 시 문제 발생할 수 있음

### ☀️ 오늘의 배움

---

- **값 반환**
  1. 의미 없는 값 return
  2. **에러 핸들링**
- ctrl+alt+v : 클래스 이름 모를 때

- `@Override`
  - 메타데이터를 표현
  - 어노테이션
    : 해당 메소드가 부모 클래스에 있는 메서드를 **_Override_** 했다는 것을 명시적으로 선언
    - 메서드를 재정의 했다는 의도를 나타냄
  - Spring에서는 기능이 존재함
