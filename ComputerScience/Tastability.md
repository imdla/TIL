> 💡 **한 줄 요약**
>
> 테스트 용이성을 높이기 위한 조건은 순수 함수, 단일 책임 원칙을 준수한 코드, 예측하기 쉬운 코드가 테스트하기 용이하다.

### 1. 🤔 왜 사용하는가

> **테스트 용이성을 높이기 위한 조건**

1. **순수 함수**
   - 순수 함수 : 동일한 입력에 대해 항상 동일한 출력을 반환 하는, 부수 효과가 없는 함수
   - 부수 효과를 일으키는 비순수 함수를 테스트하는 것은 까다로움
     - ex. 함수 외부 상태에 의존, API 호출, DOM 직접 조작 등
       - 외부의 영향을 받아 테스트의 정확성을 보장받기 어려움
   - 입력을 받아 단순히 계산한 값을 반환하는 순수함수는 독립적으로 테스트하기 좋음
2. **단일 책임 원칙 준수한 코드**
   - 하나의 함수나 모듈이 한 번에 많은 역할 수행 시 테스트 작성하기 어려움
     - ex. 데이터를 가져오는 함수가 UI 렌더링하는 기능 포함 시
       - 테스트 작성 불리함
       - 테스트 코드의 복잡도 증가
       - 테스트 실패 시 원인 파악 어려움
   - 역할을 명확히 분리해 한 가지 기능만 담당할 경우, 테스트 코드가 간결하고 명확해짐
3. **예측하기 쉬운 코드**
   - 코드가 복잡하고 내부 동작을 쉽게 이해할 수 없을 경우 테스트 작성도 어려움
   - 명확한 변수명과 일관된 코드 스타일을 유지해 예상치 못한 동작이 발생하지 않도록 함

### 2. 💡 무엇인지 아는가(특징)

> **테스트 용이성을 높이기 위해 코드 수정 ?**

- 테스트를 위해 과도하게 수정하는 것은 유지보수성을 해칠 수 있음
  - ex. 테스트 위해 `private` 메서드를 `public` 으로 바꾸거나
    테스트만을 위한 메서드를 만드는 것
    → 코드의 품질 저하시킬 수 있음
- 테스트 작성이 어려울 경우 코드 품질에도 문제가 있을 수 있음
  - ex. 함수가 너무 많은 역할하거나, 의존성이 강하게 결합되어 있는 경우
    - 테스트가 어려워질 수 있음
      → 테스트 쉽게 만들기 위해 코드 개선 고려하는 것이 좋음
      → 개선은 테스트 용이성 높임, 궁극적으로 코드 품질 높임
