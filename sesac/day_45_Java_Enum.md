## <mark color="#fbc956">Enum</mark>

### 1. 열거형 (Enum)

- 관련있는 상수들의 모임을 클래스로 정의한 것
- Java의 Enum은 항상 일관된 타입의 상수 사용하게 해 값에 대한 타입 안정성 보장
- Enum도 하나의 클래스이지만, class 키워드 대신 `enum` 키워드 사용해 클래스 선언
- `Enum` 선언 시 자동으로 해당 클래스는 `java.lang.enum` 을 상속

### 2. 사용

```java
public enum 클래스명 {
	상수1,
	상수2,
	...,
	상수n;
}
```

- 상수들 쉼표(`,`) 기준 나열
- 마지막 상수에 세미콜론(`;`) 붙임
- 상수들 모두 **대문자** 표기, 띄어쓰기 있는 경우 언더바(`_`) 로 대신 작성
- 상수 각각 해당 `Enum` 클래스의 인스턴스에 해당
  - Enum 클래스의 인스턴스인 상수는 두 번 이상 생성될 수 없어 유일함
  - 비교할 경우, `.equals()` 대신 `==` 으로 비교

### 3. 자주 사용하는 메서드

- `.values()` : 해당 Enum의 모든 상수를 저장한 새로운 배열 생성해 반환
- `valueOf(String name)` : 전달된 name 문자열과 일치하는 Enum의 상수 반환
- `.name()` : 해당 상수의 이름 반환
- `.ordinal()` : 해당 상수의 순서(0부터 시작)를 반환
  - 반환값은 상수의 순서에 따라 자동 증가
  - 상수의 순서 변경 시 기존 로직에 영향을 미칠 수 있음

```java
public enum Day {
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY,
	SUNDAY;
}
```

```java
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		// 1) .values()
		Day[] days = Day.values();
		System.out.println(Array.toString(days));
		// [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]

		// 2) .valueOf()
		Day monday = Day.valueOf("MONDAY");
		System.out.println(monday);
		// MONDAY

		// 3) .name()
		Day friday = Day.FRIDAY;
		String fridayName = friday.name();
		System.out.println(fridayName);
		// FRIDAY

		// 4) .ordinal
		int fridayOrdinal = friday.ordinal();
		System.out.println(fridayOrdinal);
		// 4
	}
}
```

---

## <mark color="#fbc956">Enum 활용</mark>

### 1. switch문

```java
public class Main {
	public static void main(String[] args) {
		Day day = Day.MONDAY;

		switch (day) {
			case MONDAY: {
				System.out.println("Monday");
				break;
			}
			case TUSEDAY: {
				System.out.println("Tuseday");
				break;
			}
			case WEDNESDAY: {
				System.out.println("Wednesday");
				break;
			}
			case THURSDAY: {
				System.out.println("Thursday");
				break;
			}
			case FRIDAY: {
				System.out.println("Friday");
				break;
			}
			case SATURDAY: {
				System.out.println("Saturday");
				break;
			}
			case SUNDAY: {
				System.out.println("Sunday");
				break;
			}
			default: {
				System.out.println("Undefined day");
			}
		}
	}
}
```

### 2. Enum에 변수, 생성자, 메서드 선언

- Enum도 클래스이므로 변수, 생성자, 메서드 선언 가능
- 각 상수 오른쪽에 괄호(`()`) 내부에 인스턴스 변수에 들어갈 값을 순서 맞게 선언
  - 괄호 안의 값들은 선언한 생성자를 따라 각 인스턴스 변수에 할당
  - 직접 생성자를 호출할 수 없음
  - 메서드는 각 상수 인스턴스 통해 호출 가능

```java
public enum Day {
	MONDAY(1, "MON"),
	TUESDAY(2, "TUE"),
	WEDNESDAY(3, "WED"),
	THURSDAY(4, "THU"),
	FRIDAY(5, "FRI"),
	SATURDAY(6, "SAT"),
	SUNDAY(7, "SUN");
}

// 각 상수의 인스턴스 변수
private int order;
private String shortName;

// 생성자
Day(int order, String shortName) {
	this.order = order;
	this.shortName = shortName;
}

// 각 상수의 인스턴스 메서드
public String getShortName() {
	return this.shortName;
}
```

### 3. Enum에 추상 메서드 선언해 각 상수마다 다른 기능 구현

```java
public enum Day {
	MONDAY(1, "MON") {
		@Override
		public show() {
			System.out.println("Monday");
		}
	},
	TUESDAY(2, "TUE") {
		@Override
		public show() {
			System.out.println("Tuesday");
		}
	},
	WEDNESDAY(3, "WED") {
		@Override
		public show() {
			System.out.println("Wednesday");
		}
	},
	THURSDAY(4, "THU") {
		@Override
		public show() {
			System.out.println("Thursday");
		}
	},
	FRIDAY(5, "FRI") {
		@Override
		public show() {
			System.out.println("Friday");
		}
	},
	SATURDAY(6, "SAT") {
		@Override
		public show() {
			System.out.println("Saturday");
		}
	},
	SUNDAY(7, "SUN") {
		@Override
		public show() {
			System.out.println("Sunday");
		}
	};

	private int order;
	private String shortName;

	Day(int order, String shortName) {
		this.order = order;
		this.shortName = shortName;
	}

	public String getShortName() {
		return this.shortName;
	}

	// 각 상수에서 재정의해야 하는 추상 메서드
	public abstract void show();
}
```
