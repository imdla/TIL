> 💡 **한 줄 요약**
>
> `(String) value` 와 `String.valueOf()` 는 모두 String 타입으로 변환하는 것이지만, 동작 방식과 예외 처리에서 차이가 있다.

### 1. 🤔 왜 사용하는가

- 자바에서 `Object` 타입인 value를 `String`으로 타입 캐스팅 vs `String.valueOf()`
  - 공통점 : 모두 String 타입으로 변환
  - 차이점 : 동작 방식, 예외 처리

### 2. 💡 무엇인지 아는가(특징)

- **`(String) value` 로 타입 캐스팅**

  1. value가 `String` 타입이 아닌 경우
     - ClassCastException 발생
  2. value가 `null`인 경우
     - 그대로 null 반환
       → 메서드 호출 시 NullPointerException 발생

  ⇒ 타입 캐스팅은 타입 안정성이 부족해 캐스팅하는 타입이 확실할때만 사용

  ```java
  Object intValue = 10;
  String str1 = (String) intValue; // ClassCastException

  Object = nullValue = null;
  String str2 = (String) nullValue; // null
  str2.concat("hello"); // NullPointerException
  ```

- **`String.valueOf(value)`**

  1. value가 `String` 타입이 아닌 경우
     - `value.toString()` 호출해 String으로 변환
  2. value가 `null` 인 경우
     - `“null”` 문자열 반환

  ```java
  Object intValue = 10;
  String str1 = String.valueOf(intValue); // "10"

  Object nullValue = null;
  String str2 = String.valueOf(nullValue); // "null"
  str2.concat("hello"); // "nullhello"
  ```

  ⇒ 타입 캐스팅에서 발생하는 예외는 런타임 시점에 발생해 `String.valueOf()` 가 더 안전, 예외 방지 가능

### 3. 🔄 개선 방법(최근 기술 동향)

> **타입 캐스팅 시 ClassCastException 방지 방법**

- 캐스팅할 타입과 맞는지 먼저 확인 후 캐스팅 시 방지 가능

  - `instanceof` 사용 시 안전하게 변환 가능

  ```java
  Object intValue = 0;

  if (intValue instanceof String str) {
  	System.out.println(str);
  } else {
  	// ...
  }
  ```

> **`String.valueOf(null)` 이 “null”을 반환하는 것**

- “null”이라는 문자열과 `null` 자체는 다른 의미를 가져 주의 필요
- JSON 변환 및 데이터베이스 저장 시 `null`이 “null” 문자열로 저장되어 오류 발생 가능
- “null” 문자열 방지
  - 미리 null 여부 검증 및 따로 처리
  - `Objects.toString()` 을 사용해 `null`일 경우 다른 문자열로 처리
