## <mark color="#fbc956">static</mark>

### 1. `static`

- 클래스 단위의 멤버에 사용
- 클래스가 최초 로딩되는 시점에 메모리에 적재되어 별도의 인스턴스 생성 없이 사용 가능

### 2. 종류

1. **클래스 변수**
   - 모든 인스턴스에서 공통적으로 사용하는 변수
   - 별도의 인스턴스 생성 없이 `클래스명.변수명` 으로 사용 가능
   - 클래스가 최초 로딩되는 시점에 메모리에 적재, 프로그램 종료될 때까지 계속 유지
2. **클래스 초기화 블록**
   - 클래스 변수의 복잡한 초기화 위한 목적으로 사용
   - 클래스가 최초 로딩되는 시점에 단 한 번만 수행
   - 자료형의 기본값 → 명시적인 초기화 → 클래스 초기화 블록에 의한 초기화 순서
3. **클래스 메서드**
   - 별도의 인스턴스 생성 없이 `클래스명.메서드명` 으로 사용 가능
   - 클래스가 최초 로딩되는 시점에 메모리에 적재, 프로그램 종료될 때까지 계속 사용
   - 클래스 메서드에서 인스턴스 변수 사용하거나 인스턴스 메서드 호출 불가

```java
public class Rectangle {
	// 클래스 변수
	public static String color;

	// 클래스 초기화 블록
	static {
		color = "red";
	}

	// 클래스 메서드
	public static void printlnColor() {
		System.out.println("Rectangle Color: " + color)
	}
}
```

---

## <mark color="#fbc956">final</mark>

### 1. `final`

- 변수, 메서드, 클래스에 사용
- 최초 선언 이후 프로그램이 종료될 때까지 변경 불가
- 장점
  1. 재할당, 오버라이딩, 상속 등을 금지해 의도치 않은 코드 변경 방지, 코드 안정성 높임
  2. 해당 변수, 메서드, 클래스가 변경 불가능하다는 의도 명시해 다른 사람이 이를 변경하지 않도록 함

### 2. final 변수

- 클래스 변수, 인스턴스 변수, 지역 변수에 사용 가능
- `final` 키워드와 함께 사용 시 해당 변수는 한 번 초기화된 후 값 재할당 불가능

1. **final 클래스 변수(= 상수)**

   - `static final` 키워드 사용
   - 클래스 최초 로딩 시 초기화됨, 이후 프로그램 종료까지 변경 불가
     - static 통해 메모리에 고정되어 프로그램 종료 시까지 유효
     - final 통해 변경 불가능
       ⇒ static final 변수를 **상수(constant)**라 함
     - 상수는 **대문자**로만 작성, 띄어쓰기는 언더바(`_`)로 표현
   - 명시적인 초기화하지 않을 경우, 반드시 클래스 초기화 블록에서 초기화 필요

   ```java
   public class Rectangle {
   	public static final int MAX_WIDTH = 100;
   }
   ```

2. **final 인스턴스 변수**

   - 각 인스턴스에 고유한 변수
   - 인스턴스 생성될 때 초기화, 이후 인스턴스 소멸할 때까지 변경 불가
   - 명시적인 초기화하지 않을 경우, 반드시 인스턴스 초기화 블록이나 생성자에서 초기화 필요

   ```java
   // 1. 명시적인 초기화
   public class Rectangle {
   	public final int width = 100;
   }
   ```

   ```java
   // 2. 인스턴스 초기화 블록에 의한 초기화
   public class Rectangle {
   	public final int width;

   	{
   		width = 100;
   	}
   }
   ```

   ```java
   // 3. 생성자에 의한 초기화
   public class Rectangle {
   	public final int width;

   	public Rectangle(int width) {
   		this.width = width;
   	}
   }
   ```

3. **final 지역 변수**

   - 메서드 안에서 선언된 final 변수
   - 해당 메서드가 실행될 때마다 초기화, 메서드 종료될 때까지 변경 불가
   - 명시적인 초기화를 하지 않을 경우, 변수 사용 전까지 반드시 값 초기화 필요

   ```java
   public class Rectangle {
   	public void method() {
   		final int width = 100;
   	}
   }
   ```

### 3. final 메서드

- 메서드에 사용 가능
- `final` 키워드와 함께 사용 시 해당 메서드는 상속 관계에서 오버라이딩(Overriding) 불가
- 💡 **상속**
  - 하나의 클래스를 재사용해 새로운 클래스를 작성하는 것
  - 기준이 되는 부모 클래스 존재, 이 부모 클래스를 바탕으로 만들어지는 자식 클래스 존재함
- 💡 **오버라이딩**
  - 상속 관계에서 자식 클래스가 부모 클래스에 작성된 메서드의 내용을 재정의하는 것

```java
public class ParentClass {
	public final void method() {
		// 부모 클래스 메서드 내용
	}
}
```

```java
public class ChildClass extends PrentClass {
	@Override
	public void method() {
		// final 메서드 오버라이딩 불가
	}
}
```

### 4. final 클래스

- 클래스에 사용 가능
- `final` 키워드와 함께 사용 시 해당 클래스는 상속 불가능한 클래스가 됨
- 해당 클래스의 설계 의도를 명확히 함
  - 상속 통해 동작 변경되거나 확장되지 않도록 보호하는 경우 사용

```java
public final class ParentClass {
	// 부모 클래스 내용
}
```

```java
public class ChildClass extends ParentClass {
	// final 클래스 상속 불가
}
```
