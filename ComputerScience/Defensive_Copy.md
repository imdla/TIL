> 💡 **한 줄 요약**
>
> 방어적 복사는 원본과 참조를 끊은 복사본을 만들어 사용하는 방식으로, 원본의 변경에 의한 예상치 못한 사이드 이펙트를 방지해 안전한 코드를 만들 수 있다.

### 1. 🤔 왜 사용하는가

- **방어적 복사(Defensive Copy)**
  - 원본과의 참조를 끊은 복사본을 만들어 사용하는 방식
  - **장점** : 원본의 변경에 의한 예상치 못한 사이드 이펙트를 방지해 안전한 코드 만들 수 있음

### 2. 💡 무엇인지 아는가(특징)

- **방어적 복사의 2가지 시점**
  1. 생성자의 인자로 받은 객체의 복사본을 만들어 내부 필드 초기화
  2. getter 메서드에서 객체 반환 시 복사본을 반들어 반환
- **컬렉션 자료구조를 반환하는 경우**
  - 자바의 Unmodifiable Collection을 사용
    → 외부에서 Collection에 대해 조회만 할 수 있도록 강제 가능 - Unmodifiable Collection
    : `set()`, `add()`, `addAll()` 처럼 컬렉션에 요소 추가 및 변경 메서드를 사용하는 경우, 예외 발생시킴

> **발생가능한 문제점**

```java
public class Lotto {
	private final List<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		validateSize(numbers);
		this.numbers = new ArrayList<>(numbers); // 방어적 복사
	}
}
```

1. 생성자의 파라미터로 주어진 LottoNumber 리스트의 각 요소가 **외부에서 변경될 수 있는 가능성 존재**
   - 문제 이유 : 방어적 복사가 깊은 복사가 아님
   - 해결
     - 생성자의 파라미터에 Integer 리스트 입력 받음
     - 방어적 복사 수행 시 내부 객체까지 깊은 복사 수행
     ```java
     Lotto lotto = new Lotto(numbers);
     numbers.get(0).changeNumber(1);
     ```
2. **검증을 수행하는 시점**에 외부에서 컬렉션이 변경 발생할 수 있는 가능성 존재
   - validateSize 메서드 통과 후 방어적 복사 수행 전, 외부에서 numbers에 값을 추가하는 경우
     → 검증은 성공했지만 객체의 값 유효하지 않을 수 있음
   - 해결 : 방어적 복사가 검증 수행 이전에 이루어지게 함
