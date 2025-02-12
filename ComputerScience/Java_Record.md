> 💡 **한 줄 요약**
>
> Record는 모든 필드가 final로 선언되어 객체 생성 후 변경할 수 없르며, 필드 선언만으로 생성자, `getter` 등 메서드를 생성해주어 멀티 스레드 환경에서 데이터가 의도치 않게 변경되지 않고 안전하게 전달할 수 있다. Record의 모든 객체가 DTO인 것은 아니다. Record는 단순히 데이터를 캡슐화하는 역할을 하지만, DTO외에도 값 객체 등 다양한 용도로 사용될 수 있다.

### 1. 🤔 왜 사용하는가

- **Record**
  - Java16 에서 정식 출시된 특별한 유형의 클래스
  - 불변성(Immutable)을 기본으로 함

### 2. 💡 무엇인지 아는가(특징)

- **Record의 특성**
  - 모든 필드가 `final` 키워드로 선언됨
  - 객체 생성 후 변경할 수 없음
  - 필드 선언만으로 자동으로 생성자, `getter`, `equals()` , `hashCode()` , `toString()` 등 메서드 생성해줌
    ⇒ 보일러 플레이트 코드 줄일 수 있음

> **Record로 생성한 모든 객체 = DTO 인가 ?**

- 모든 Record 객체가 DTO인 것은 아님
- **Record의 역할**
  - 단순히 데이터를 캡슐화하는 역할
  - DTO 외에도 값 객체(Value Objects) 등 다양한 용도로 사용 가능
    ```java
    // 값 객체로 사용
    public record Coordinates(double x, double y) {}
    ```
- **DTO와 VO(Value Objects)**
  - **DTO :** 계층 간 데이터 전송 목적의 객체
  - **VO :** 도메인 모델 내에서 특정 값을 표현하는 객체로 사용
  - Record는 위 두 가지 모두에 적합하게 사용 가능
    - 목적에 따라 사용 방법 달라짐

```java
// 기존 클래스 기반 DTO
public class MemberDto {
	private final String name;
	private final String email;
	private final int age;

	public MemberDto(String name, String email, int age) {
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}
}
```

```java
// Record. 생성자, getter, hashCode(), equls(), toString() 자동 완성
public record MemberDto(String name, String email, int age) {}
```

> **Record와 VO**

- **공통점**

  - 모두 객체의 상태가 변경되지 않음을 보장함
  - 데이터를 캡슐화해 표현에 초점 맞춤
  - 동등성
    - **VO** : 값 기반의 동등성 가짐
    - **Record** : 동일한 필드 값 가지면 동일한 객체로 간주됨

- **차이점**
  - **VO**
    - 도메인 모델 내에서 특정 개념 표현
    - 도메인 로직과 밀접한 관련 있음
    - 비즈니스 로직이나 규칙 가질 수 있음
    - 보다 더 넓은 도메인 맥락에서 사용됨
  - **Record**
    - 단순히 데이터를 캡슐화해 전달하는데 의미 있음
    - VO를 구현하기에 적합하지만, VO의 모든 특성을 완벽히 대체하지 않음

### 3. ✅ 장점

- 멀티 스레드 환경에서 데이터가 의도치 않게 변경되지 않고 안전하게 전달 가능

### 4. ⚠️ 한계

> **Record의 한계**

- extends 사용해 다른 클래스 상속 불가
- 필드가 final로 선언되어 확장 어려움
- 주로 데이터를 전달하려는 목적으로 설계됨 → 비즈니스 로직 포함하기에 적절하지 않음
- Java 14 또는 16 버전에서 호환이 불가능함
