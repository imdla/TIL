> 💡 **한 줄 요약**
>
> String 객체는 내부적으로 final 키워드가 선언된 byte[] 필드를 사용해 문자열을 저장해 불변하다.

### 1. 🤔 왜 사용하는가

> **String 객체 → 불변(Immutable)임**

- **String 클래스**
  - 내부적으로 final 키워드가 선언된 byte[] 필드 사용해 문자열 저장함
  - String은 참조 타입(Reference Type)
    - `concat()`, `replace()`, `toUpperCase()` 와 같은 String 메서드를 호출하면 새로운 String 객체를 참조하고 기존 객체를 수정하지 않음
      → String 객체를 불변하게 유지할 수 있음

### 2. 💡 무엇인지 아는가(특징)

> **String을 불변으로 설계한 이유**

1. **String Constant Pool 사용**
   - 동일한 문자열의 String 변수들은 같은 객체를 공유
     → 메모리 효율적 사용 가능
2. **불변한 객체는 멀티 스레드 환경에서 thread-safe함**
   - 문자열을 변경하면 String Constant Pool에 새로운 객체 생성함
     → 동기화를 신경쓸 필요 없음
3. **해시코드를 한 번만 계산하고 이를 캐싱**

   → 재사용 가능

4. **비밀번호, 토큰, URL 등 민감 정보 안전하게 다룸**
   - 불변한 객체는 변경할 수 없어 민감한 정보가 예기치않게 수정되는 것을 방지함

> **리터럴로 생성한 String 객체 vs 생성자로 생성한 String 객체**

- 두 방식으로 생성한 객체 → 같은 문자열 갖더라도 메모리 상 다르게 처리됨

  ```java
  String first = "hello"; // 리터럴로 생성
  String second = new String("hello"); // 생성자로 생성
  String third = "hello";

  System.out.println(System.identityHashCode(first));
  System.out.println(System.identityHashCode(second));
  System.out.println(System.identityHashCode(third));
  // first와 third만 동일
  ```

- **리터럴로 생성한 String 객체**
  - Heap 영역의 String Constant Pool에 저장되어 동일한 문자열 재사용 가능
  - 문자열이 String Constant Pool에 이미 존재하면 같은 주소 참조
- **생성자로 생성한 String 객체**
  - Heap 영역에 저장되어 동일한 문자열이라도 항상 새로운 객체 생성

```java
String first = "hello"; // 리터럴로 생성
String second = new String("hello"); // 생성자로 생성
String third = second.intern(); // intern() 메서드 사용

System.out.println(System.identityHashCode(first));
System.out.println(System.identityHashCode(second));
System.out.println(System.identityHashCode(third));
// first와 third만 동일
```

- **`intern()` 메서드**
  - Heap 영역에 저장된 String 객체를 Strign Constant Pool에 저장 가능
  - 해당 문자열이 String Constant Pool에 존재할 경우 그 주소값을 반환, 없을 경우 String Constant Pool에 추가 후 새로운 주소값 반환함
