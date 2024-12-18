## <mark color="#fbc956">Package</mark>

### 1. 패키지 (Package)

- 서로 관련된 클래스들끼리 그룹으로 묶어놓은 단위
- 폴더 혹은 디렉토리 단위 관리
- 패키지 생성 = 폴더 생성
- **장점**
  1. 관련 클래스 그룹화 관리 → 프로젝트 구조 명확, 쉬운 관리
  2. 서로 다른 패키지에 동일한 이름의 클래스가 있어도 패키지가 달라 같은 이름으로 인한 충돌 방지 → 대규모 프로젝트에서 클래스 파일 관리 쉬움
  3. 패키지 통해 공통 기능 가진 클래스 모아둘 경우, 다른 프로젝트나 클래스에서 쉽게 재사용 가능
  4. 패키지 사용 시 클래스 접근 수준 조절 가능 → 특정 클래스나 메서드를 외부에서 접근 못하도록 숨길 수 있음

### 2. 선언

- `package` 키워드 사용

```java
package 패키지 경로;
```

- 규칙
  1. 패키지 선언은 주석과 공백 제외한 가장 첫번째 문장
  2. 클래스에서 단 한 번만 가능
  3. 점(`.`)을 구분자로해 **계층 구조**로 작성
  4. 패키지명은 모두 소문자로 띄어쓰기 없이 작성
  5. 패키지명은 예약어 작성 불가
  6. 띄어쓰기나 예약어로 작성해야 할 경우 오라클에서 제시하는 규칙을 따름
     - 주로 언더바(`_`) 이용해 예외 해결

---

## <mark color="#fbc956">import 문</mark>

### 1. import 필요성

- 하나의 클래스에서 다른 패키지에 소속된 클래스 사용 시 해당 클래스의 패키지 경로 필요함

> **폴더 구조**
>
> 📁 com/test
>
> - 📁 execution
>   - Main.java
> - 📁 mathematics
>   - Calculator.java

- Main 클래스에서 Calculator 클래스를 사용할 경우

```java
package com.test.mathematics;

public class Calculator {
	public static int add(int number1, int number2) {
		return number1 + number2;
	}
}
```

```java
package com.test.excution;

public class Main {
	public static void main(String[] args) {
		int result = com.test.mathmetics.Calculator.add(1, 2);
		System.out.println(result); // 3
	}
}
```

⇒ 직접 경로 명시할 경우 가독성 저하

### 2. 선언

- `import` 키워드 사용
- 다른 패키지의 외부 클래스 불러옴

```java
import 외부 클래스 경로;
```

```java
package com.test.excution;
import com.test.mathematics.Calculator;

public class Main {
	public static void main(String[] args) {
		int result = Calculator.add(1, 2);
		System.out.println(result); // 3
	}
}
```

### 3. 와일드카드(\*) import

- 다른 패키지의 여러 클래스를 불러올 경우 사용
- 정확히 어떤 클래스들을 불러오는지 알 수 없다는 단점 있음

```java
package com.test.mathmatics;

public class Comparator {
	public static int getBigger(int number1, int number2) {
		if (number1 > number2) {
			return number1;
		} else {
			return number2;
		}
	}
}
```

```java
package com.test.excution;
import com.test.mathematics.*;

public class Main {
	public static void main(String[] args) {
		int result1 = Calculator.add(1, 2);
		System.out.println(result1); // 3

		int result2 = Comparator.getBigger(1, 2);
		System.out.println(result2); // 2
	}
}
```

### 4. static import문

- 정적(static) 멤버 호출 시 static import문 사용 시 클래스 이름 생략 가능

```java
import static 외부 클래스의 정적 멤버 경로;
```

```java
package com.test.excution;
import static com.test.mathematics.Calculator.add;
import static com.test.mathematics.Comparator.getBigger;

public class Main {
	public static void main(String[] args) {
		int result1 = add(1, 2);
		System.out.println(result1); // 3

		int result2 = getBigger(1, 2);
		System.out.println(result2); // 2
	}
}
```
