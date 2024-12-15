## <mark color="#fbc956">Access Modifier</mark>

### 1. 접근 제어자 (Access Modifier)

- 변수나 메서드, 클래스를 외부에서 접근하지 못하도록 제한하는 키워드
- **클래스, 클래스 변수, 인스턴스 변수, 메서드, 생성자**에 붙여 접근 제어함
- 장점
  1. 클래스 내부에 선언된 데이터 보호
  2. 클래스 내에서만 사용되는 변수나 메서드를 클래스 내부에 감춰, 외부에서 사용되지 않는 불필요한 정보들을 드러내지 않아 복잡성을 줄임

### 2. 종류

1. `private` : 같은 클래스에서만 접근 가능
2. `default` : 같은 패키지에서만 접근 가능 (접근 제어자 생략 시 기본 default 취급)
3. `protected` : 같은 패키지에서 접근 가능, 다른 패키지라도 자식 클래스라면 접근 가능
4. `public` : 접근 제한 없음

### 3. 사용 가능 대상

- 접근 제어자는 대상에 따라 사용 가능 여부 달라짐
  1. **클래스** : `public`, `default` 사용 가능
  2. **클래스 변수, 인스턴스 변수, 메서드** : `public`, `protected`, `default`, `private` 사용 가능
  3. **지역 변수** : 접근 제어자 사용 불가

### 4. `private`

- 같은 클래스에서만 대상 접근 가능
- `private` 변수를 사용하기 위해 `getter` , `setter` 이용

```java
public class PrivateClass {
	private int number = 1;

	public int getNumber() {
		return this.number;
	}
}
```

```java
public class Main {
	public static void main(String[] args) {
		PrivateClass privateClass = new PrivateClass();

		// System.out.println(privateClass.number); -> 접근 불가
		System.out.println(privateClass.getNumber()); // 1
	}
}
```

### 5. `default`

- 같은 패키지에서만 접근 가능
- 접근 제어자 생략 시 기본적으로 `default`로 취급

```java
package com.test.p2;

public class DafaultClass {
	int number = 1;
}
```

```java
package com.test.p1;
import com.test.p2.DefaultClass;

public class Main {
	public static void main(String[] args) {
		DefaultClass dafaultClass = new DefaultClass();

		// System.out.println(dafaultClass.number); -> 접근 불가
	}
}
```

### 6. `protected`

- 같은 패키지에서 접근 가능
- 다른 패키지이더라도 자식 클래스일 경우 접근 가능

```java
package com.test.p2;

public class ParentClass {
	protected int patentNum = 1;
}
```

```java
package com.test.p1;
import com.test.p2.ParentClass;

public class ChildClass extends ParentClass {
	public int childNum = patentNum + 1;
}
```

### 7. `public`

- 모든 곳에서 접근 가능
- 같은 패키지의 여부나 상속 관계와 상관없이 어디서든 해당 클래스의 변수나 메서드에 접근 가능

| 접근 제어자 | 같은 클래스 | 같은 패키지 | 자식 클래스
(다른 패키지) | 다른 패키지 |
| --- | --- | --- | --- | --- |
| `private` | O | X | X | X |
| `default` | O | O | X | X |
| `protected` | O | O | O | X |
| `public` | O | O | O | O |

---

### ☀️ 오늘의 배움


- 접근제어자
  - 메서드 내에서만 사용되는 변수들(내부 local 변수들)은 언제나 접근 불가해 접근 제어자가 필요없음
  - `public`
  - `private`
    - `getter` / `setter`- 데이터 변화에 대한 트래킹 가능

> **파일 구조**
>
> - 📁 javaIntro
>   - Main.java
>   - PrivateModifier.java

- 접근제어자 `private` , `getter`와 `setter`

```java
package org.example.javaIntro;

public class PrivateModifier {
		// public 인스턴스 변수
    public int publicNum = 1;

    // getter
    public int getPrivateNum() {
        return privateNum;
    }

    // setter
    public void setPrivateNum(int privateNum) {
        this.privateNum = privateNum;
    }

		// private 인스턴스 변수
    private int privateNum = 10;

		// public 메서드
    public void publicHi() {
        System.out.println("hi, public");
    }

		// private 메서드
    private void privateHi() {
        System.out.println("hi, private");
    }

    public void sayHi() {
        privateHi();
    }
}

```

```java
package org.example.javaIntro;

public class Main {
    public static void main(String[] args) {
        PrivateModifier pm = new PrivateModifier();

        System.out.println(pm.publicNum);       // -> public 인스턴스 변수 접근 가능
//        System.out.println(pm.privateNum);       -> private 인스턴스 변수 접근 불가
        System.out.println(pm.getPrivateNum()); // -> getter로 private 변수 접근 가능
        pm.setPrivateNum(100);                  // -> setter로 private 변수 정의 가능
        System.out.println(pm.getPrivateNum());

        pm.publicHi();                         // -> public 메서드 접근 가능
//        pm.privateHi();                         -> private 메서드 접근 불가
        pm.sayHi();                           // public 메서드에 private 메서드를 호출해
																						 // private 메서드 사용 가능

    }
}
```
