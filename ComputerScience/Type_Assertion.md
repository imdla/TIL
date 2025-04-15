> 💡 **한 줄 요약**
>
> 타입 단언은 컴파일러에게 특정 값이 어떤 타입인지 개발자가 더 잘 알고 있다고 알려주는 방법이다.

### 1. 🤔 왜 사용하는가

- **타입 단언(Type Assertion)**
  - 컴파일러에게 특정 값이 어떤 타입인지 개발자가 더 잘 알고 있다고 알려주는 방법
  - 개발자가 `as` 이용해 타입 단언 시, 자동 추론된 타입을 덮어씀
  - 활용
    - 컴파일러가 타입을 자동으로 추론하지 못할 경우
    - 잘못 추론할 경우 사용

### 2. 💡 무엇인지 아는가(특징)

> 예시

- DOM 요소를 가져오는 `document.getElementById()` 메서드 반환 타입은 `HTMLElement | null`로 정의
- 개발자가 해당 요소가 `HTMLDIvElement` 임을 확신할 경우

  - `as HTMLDivElement` 를 사용해 컴파일러에게 명시적으로 알려줄 수 잇음
    → 타입 오류 없이 안전하게 속성 접근 가능

  ```jsx
  const element = document.getElementById("myElement") as HTMLDivElement;

  element.style.backgroundColor = "red";
  ```

### 3. ⚠️ 주의할 점

- **컴파일러 타입 검사를 우회함**
  → 실제 값이 단언한 타입과 다를 경우 런타임 에러 발생 가능
  - 타입 단언은 확실히 알고 있을 때만 사용하는 것이 중요
  - 가능한 한 타입 추론과 타입 가드를 우선적으로 사용
  - 잘못된 사용은 타입 스크립트의 장점 훼손
    - 코드의 안정성 해칠 수 있음

### 4. 💪 활용 원칙

> **타입 단언 시 안전하게 활용위한 원칙**

1. **타입 단언보다는 타입 내로잉(narrowing)을 우선적 적용**

   - 타입스크립트는 조건문과 타입 체크를 통해 자동으로 타입을 좁힐 수 있음
   - 가능한 한 단언 없이 타입을 명확히 하는 방식이 권장

   ```jsx
   function printLength(value: string : string[]) {
   	if (Array.isArray(value)) {
   		console.log(value,length); // 타입 내로잉으로 배열로 안전하게 처리
   	} else {
   		// 문자열로 처리
   	}
   }
   ```

2. **type predicate을 활용항 타입 가드 통해 타입 안전성 높임**

   - type predicate 통해 데이터 구조를 확인해 런타임 오류 줄임

   ```jsx
   function isFinish(pet: Fish | Bird): pet is Fish {
   	return (pet as Fish).swim !== undefined;
   }

   if (isFinished(pet)) {
   	pet.swim();
   }
   ```

3. **타입 단언은 최소한의 범위에서만 사용하는 것이 좋음**

   - 전체 객체보다는 필요한 속성이나 특정 부분만 단언하는 방식으로 불필요한 위험을 줄이는 것이 바람직함

   ```jsx
   const element = document.getElementById("myElement");
   if (element) {
   	(element as HTMLDivElement).style.backgroundColor = "blue";
   }
   ```
